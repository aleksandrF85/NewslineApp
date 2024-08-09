package com.example.Newsline.mapper;

import com.example.Newsline.model.User;
import com.example.Newsline.web.model.UpsertUserRequest;
import com.example.Newsline.web.model.UserListResponse;
import com.example.Newsline.web.model.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User requestToUser(UpsertUserRequest request);

    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, UpsertUserRequest request);

    @Mapping(expression = "java(user.getNews().stream().map(news -> news.getHeader()).toList())", target = "news")
    UserResponse userToResponse(User user);

    default UserListResponse userListToUserResponseList(List<User> users) {
        UserListResponse response = new UserListResponse();

        response.setUsers(users.stream()
                .map(this::userToResponse).collect(Collectors.toList()));

        return response;
    }

}
