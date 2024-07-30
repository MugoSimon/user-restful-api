package com.mugosimon.user_restful_api.service;

import com.mugosimon.user_restful_api.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<String> createUser(UserDto user);

    ResponseEntity<List<UserDto>> fetchAllUsers();

    ResponseEntity<UserDto> fetchUserById(Long id);

    ResponseEntity<List<UserDto>> fetchUserByName(String name);

    ResponseEntity<UserDto> fetchUserByEmail(String email);

    ResponseEntity<String> modifyUser(Long id, UserDto updatedUser);

    ResponseEntity<String> deleteUser(Long id);
}
