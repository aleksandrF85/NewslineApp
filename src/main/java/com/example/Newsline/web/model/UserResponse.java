package com.example.Newsline.web.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {

    Long id;

    String fullName;

    private List<String> news = new ArrayList<>();

}
