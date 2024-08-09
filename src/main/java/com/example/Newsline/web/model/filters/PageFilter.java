package com.example.Newsline.web.model.filters;

import com.example.Newsline.validation.PageFilterValidation;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@PageFilterValidation
public class PageFilter {

    private int pageSize;

    private int pageNumber;


}
