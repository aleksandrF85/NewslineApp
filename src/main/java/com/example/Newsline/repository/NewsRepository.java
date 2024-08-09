package com.example.Newsline.repository;

import com.example.Newsline.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

    @Override
    Page<News> findAll(Pageable pageable);

    List<News> findAllByCategory(String category);
}
