package com.example.Newsline.service.impl;

import com.example.Newsline.model.User;
import com.example.Newsline.repository.UserRepository;
import com.example.Newsline.service.UserService;
import com.example.Newsline.utils.BeanUtils;
import com.example.Newsline.web.model.filters.PageFilter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll(PageFilter pageFilter) {
        return userRepository.findAll(PageRequest.of(pageFilter.getPageNumber(), pageFilter.getPageSize())).getContent();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Пользователь с таким ID {0} не найден!", id
                )));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());

        BeanUtils.copyNonNullProperties(user, existedUser);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
