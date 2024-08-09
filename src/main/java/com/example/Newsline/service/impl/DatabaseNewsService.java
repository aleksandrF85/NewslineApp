package com.example.Newsline.service.impl;

import com.example.Newsline.model.News;
import com.example.Newsline.repository.NewsRepository;
import com.example.Newsline.repository.NewsSpecification;
import com.example.Newsline.service.NewsService;
import com.example.Newsline.utils.BeanUtils;
import com.example.Newsline.web.model.filters.NewsFilter;
import com.example.Newsline.web.model.filters.PageFilter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseNewsService implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public List<News> findAll(PageFilter pageFilter) {
        return newsRepository.findAll(PageRequest.of(pageFilter.getPageNumber(), pageFilter.getPageSize())).getContent();
    }

    @Override
    public List<News> filterBy(NewsFilter filter) {
        return newsRepository.findAll(NewsSpecification.withFilter(filter));
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Новость с данным ID {0} не найдена!", id
                )));
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News update(News news) {
        News existedNews = findById(news.getId());

        BeanUtils.copyNonNullProperties(news, existedNews);

        return newsRepository.save(news);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

}
