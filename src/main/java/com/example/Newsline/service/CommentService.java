package com.example.Newsline.service;

import com.example.Newsline.model.Comment;
import com.example.Newsline.web.model.filters.CommentFilter;

import java.util.List;

public interface CommentService {

    Comment findById(Long id);

    List<Comment> findAllByNewsId(CommentFilter commentFilter);

    Comment save(Comment comment);

    Comment update(Comment comment);

    void deleteById(Long id);
}
