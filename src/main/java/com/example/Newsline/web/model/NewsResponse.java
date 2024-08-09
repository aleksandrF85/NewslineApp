package com.example.Newsline.web.model;

import lombok.Data;


@Data
public class NewsResponse {

    private Long id;

    private String newsAuthor;

    private String header;

    private String content;

    private int commentsAmount;

}
