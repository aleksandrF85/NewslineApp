package com.example.Newsline.service;

import com.example.Newsline.model.News;
import com.example.Newsline.web.model.filters.NewsFilter;
import com.example.Newsline.web.model.filters.PageFilter;

import java.util.List;

public interface NewsService {

    List<News> findAll(PageFilter pageFilter);

    List<News> filterBy(NewsFilter filter);

    News findById(Long id);

    News save(News news);

    News update(News news);

    void deleteById(Long id);
}
