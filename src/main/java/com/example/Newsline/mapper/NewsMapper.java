package com.example.Newsline.mapper;

import com.example.Newsline.model.News;
import com.example.Newsline.web.model.NewsListResponse;
import com.example.Newsline.web.model.NewsResponse;
import com.example.Newsline.web.model.NewsResponseDetailed;
import com.example.Newsline.web.model.UpsertNewsRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommentMapper.class})
public interface NewsMapper {

    News requestToNews(UpsertNewsRequest request);

    @Mapping(source = "newsId", target = "id")
    News requestToNews(Long newsId, UpsertNewsRequest request);

    @Mapping(expression = "java(news.getNewsAuthor().getFullName())", target = "newsAuthor")
    NewsResponse newsToResponse(News news);

    @Mapping(expression = "java(news.getNewsAuthor().getFullName())", target = "newsAuthor")
    NewsResponseDetailed newsToDetailedResponse(News news);

    List<NewsResponse> newsListToResponseList(List<News> news);

    default NewsListResponse newsListToNewsListResponse(List<News> news) {
        NewsListResponse response = new NewsListResponse();
        response.setNews(newsListToResponseList(news));
        return response;
    }

}
