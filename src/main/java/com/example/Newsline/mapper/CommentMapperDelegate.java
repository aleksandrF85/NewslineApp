package com.example.Newsline.mapper;


import com.example.Newsline.model.Comment;
import com.example.Newsline.service.NewsService;
import com.example.Newsline.service.UserService;
import com.example.Newsline.web.model.UpsertCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommentMapperDelegate implements CommentMapper {

    @Autowired
    private UserService databaseUserService;

    @Autowired
    private NewsService databaseNewsService;

    @Override
    public Comment requestToComment(UpsertCommentRequest request) {
        Comment comment = new Comment();
        comment.setComment(request.getComment());
        comment.setCommentAuthor(databaseUserService.findById(request.getAuthorId()));
        comment.setNews(databaseNewsService.findById(request.getNewsId()));

        return comment;
    }

    @Override
    public Comment requestToComment(Long commentId, UpsertCommentRequest request) {
        Comment comment = requestToComment(request);
        comment.setId(commentId);


        return comment;
    }


}
