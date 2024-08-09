package com.example.Newsline.web.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpsertCommentRequest {

    private Long authorId;

    private Long newsId;

    @NotBlank(message = "Комментарий должен быть заполнен")
    private String comment;


}
