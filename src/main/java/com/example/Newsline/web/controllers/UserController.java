package com.example.Newsline.web.controllers;

import com.example.Newsline.mapper.UserMapper;
import com.example.Newsline.model.User;
import com.example.Newsline.service.UserService;
import com.example.Newsline.web.model.UpsertUserRequest;
import com.example.Newsline.web.model.UserListResponse;
import com.example.Newsline.web.model.UserResponse;
import com.example.Newsline.web.model.filters.PageFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService databaseUserService;

    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll(@Valid PageFilter pageFilter) {
        return ResponseEntity.ok(
                userMapper.userListToUserResponseList(
                        databaseUserService.findAll(pageFilter)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.userToResponse(
                databaseUserService.findById(id)
        ));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UpsertUserRequest request) {
        User newUser = databaseUserService.save(userMapper.requestToUser(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(newUser));
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @Valid UpsertUserRequest request) {
        User updatedUser = databaseUserService.update(userMapper.requestToUser(id, request));

        return ResponseEntity.ok(userMapper.userToResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        databaseUserService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
