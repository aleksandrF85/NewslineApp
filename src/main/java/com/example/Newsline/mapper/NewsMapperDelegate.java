package com.example.Newsline.mapper;

import com.example.Newsline.model.News;
import com.example.Newsline.service.UserService;
import com.example.Newsline.web.model.NewsResponse;
import com.example.Newsline.web.model.NewsResponseDetailed;
import com.example.Newsline.web.model.UpsertNewsRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class NewsMapperDelegate implements NewsMapper {

    @Autowired
    private UserService databaseUserService;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public NewsResponseDetailed newsToDetailedResponse(News news) {
        NewsResponseDetailed response = new NewsResponseDetailed();
        response.setId(news.getId());
        response.setNewsAuthor(news.getNewsAuthor().getFullName());
        response.setHeader(news.getHeader());
        response.setContent(news.getContent());
        response.setComments(commentMapper.commentListToResponseList(news.getComments()));
        return response;
    }

    @Override
    public News requestToNews(UpsertNewsRequest request) {
        News news = new News();
        news.setCategory(request.getCategory());
        news.setHeader(request.getHeader());
        news.setContent(request.getContent());
        news.setNewsAuthor(databaseUserService.findById(request.getAuthorId()));

        return news;
    }

    @Override
    public News requestToNews(Long newsId, UpsertNewsRequest request) {
        News news = requestToNews(request);
        news.setId(newsId);

        return news;
    }

    @Override
    public List<NewsResponse> newsListToResponseList(List<News> news) {
        List<NewsResponse> newsResponseList = new ArrayList<>();

        for (News n :
                news) {
            NewsResponse response = new NewsResponse();
            response.setNewsAuthor(n.getNewsAuthor().getFullName());
            response.setContent(n.getContent());
            response.setHeader(n.getHeader());
            response.setCommentsAmount(n.getComments().size());
            response.setId(n.getId());
            newsResponseList.add(response);
        }

        return newsResponseList;
    }
}
