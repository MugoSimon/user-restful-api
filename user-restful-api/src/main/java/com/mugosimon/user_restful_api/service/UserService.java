package com.mugosimon.user_restful_api.service;

import com.mugosimon.user_restful_api.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    List<UserDto> addMultipleUsers(List<UserDto> usersDto);

    List<UserDto> fetchAllUsers();

    UserDto fetchUserById(Long id);

    List<UserDto> fetchUserByName(String name);

    UserDto fetchUserByEmail(String email);

    UserDto modifyUser(Long id, UserDto updatedUser);

    String deleteUser(Long id);
}