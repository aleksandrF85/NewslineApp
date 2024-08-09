package com.example.Newsline.service;

import com.example.Newsline.model.User;
import com.example.Newsline.web.model.filters.PageFilter;

import java.util.List;

public interface UserService {
    List<User> findAll(PageFilter pageFilter);

    User findById(Long id);

    User save(User user);

    User update(User user);

    void deleteById(Long id);

}
