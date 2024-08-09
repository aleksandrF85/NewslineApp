package com.example.Newsline.web.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsResponseDetailed {

    private Long id;

    private String newsAuthor;

    private String header;

    private String content;

    private List<CommentResponse> comments = new ArrayList<>();


}
