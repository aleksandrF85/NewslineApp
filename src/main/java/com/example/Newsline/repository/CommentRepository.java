package com.example.Newsline.repository;

import com.example.Newsline.model.Comment;
import com.example.Newsline.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByNews(News news);
}
