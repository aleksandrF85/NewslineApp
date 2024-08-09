package com.example.Newsline.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpsertUserRequest {
    @NotBlank(message = "Имя должно быть указано")
    @Size(min = 5, max = 20, message = "Имя не должно быть меньше {min} и больше {max} символов!")
    private String fullName;

}
