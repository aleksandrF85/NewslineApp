package com.example.Newsline.web.model;

import com.example.Newsline.model.NewsCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpsertNewsRequest {

    private Long authorId;

    private NewsCategory category;
    @NotBlank(message = "Заголовок должен быть заполнен")
    private String header;
    @NotBlank(message = "Контент должен быть заполнен")
    private String content;

}
