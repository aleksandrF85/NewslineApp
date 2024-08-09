package com.example.Newsline.web.controllers;

import com.example.Newsline.aop.Loggable;
import com.example.Newsline.mapper.NewsMapper;
import com.example.Newsline.model.News;
import com.example.Newsline.service.NewsService;
import com.example.Newsline.web.model.NewsListResponse;
import com.example.Newsline.web.model.NewsResponseDetailed;
import com.example.Newsline.web.model.UpsertNewsRequest;
import com.example.Newsline.web.model.filters.NewsFilter;
import com.example.Newsline.web.model.filters.PageFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService databaseNewsService;

    private final NewsMapper newsMapper;

    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(@Valid PageFilter pageFilter) {
        return ResponseEntity.ok(
                newsMapper.newsListToNewsListResponse(
                        databaseNewsService.findAll(pageFilter)
                )
        );
    }

    @GetMapping("/filter")
    public ResponseEntity<NewsListResponse> filterBy(NewsFilter filter){
        return ResponseEntity.ok(
                newsMapper.newsListToNewsListResponse(
                databaseNewsService.filterBy(filter)
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDetailed> findById(@PathVariable Long id) {
        return ResponseEntity.ok(newsMapper.newsToDetailedResponse(
                databaseNewsService.findById(id)
        ));
    }


    @PostMapping
    public ResponseEntity<NewsResponseDetailed> create(@RequestBody @Valid UpsertNewsRequest request) {
        News freshNews = databaseNewsService.save(newsMapper.requestToNews(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.newsToDetailedResponse(freshNews));
    }

    @PostMapping("/{newsId}")
    @Loggable
    public ResponseEntity<NewsResponseDetailed> update(@PathVariable Long newsId, @RequestParam Long userId, @RequestBody @Valid UpsertNewsRequest request) {
        News updatedNews = databaseNewsService.update(newsMapper.requestToNews(newsId, request));

        return ResponseEntity.ok(newsMapper.newsToDetailedResponse(updatedNews));
    }

    @DeleteMapping("/{newsId}")
    @Loggable
    public ResponseEntity<Void> delete(@PathVariable Long newsId, @RequestParam Long userId) {
        databaseNewsService.deleteById(newsId);

        return ResponseEntity.noContent().build();
    }

}
