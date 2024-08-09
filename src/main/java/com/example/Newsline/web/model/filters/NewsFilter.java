package com.example.Newsline.web.model.filters;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class NewsFilter {

    private String category;

    private String author;

    private Instant createdBefore;

    private Instant updatedBefore;

}
