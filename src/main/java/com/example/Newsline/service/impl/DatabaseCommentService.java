package com.example.Newsline.service.impl;

import com.example.Newsline.model.Comment;
import com.example.Newsline.model.News;
import com.example.Newsline.repository.CommentRepository;
import com.example.Newsline.repository.NewsRepository;
import com.example.Newsline.service.CommentService;
import com.example.Newsline.utils.BeanUtils;
import com.example.Newsline.web.model.filters.CommentFilter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseCommentService implements CommentService {

    private final CommentRepository commentRepository;

    private final NewsRepository newsRepository;

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Комментарий с данным ID {0} не найден!", id
                )));
    }

    @Override
    public List<Comment> findAllByNewsId(CommentFilter commentFilter) {
        News news = newsRepository.findById(commentFilter.getNewsId())
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Новость с данным ID {0} не найдена!", commentFilter.getNewsId()
                )));
        return commentRepository.findAllByNews(news);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        Comment existedComment = findById(comment.getId());

        BeanUtils.copyNonNullProperties(comment, existedComment);

        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
