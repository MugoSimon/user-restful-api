package com.mugosimon.user_restful_api.controller;

import com.mugosimon.user_restful_api.dto.UserDto;
import com.mugosimon.user_restful_api.exception.ErrorDetails;
import com.mugosimon.user_restful_api.exception.ResourceNotFoundException;
import com.mugosimon.user_restful_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/addMultiple")
    public ResponseEntity<List<UserDto>> addMultipleUsers(@RequestBody List<UserDto> usersDto) {
        List<UserDto> addedUsers = userService.addMultipleUsers(usersDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedUsers);

    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> fetchAllUsers() {

        List<UserDto> users = userService.fetchAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/fetchById")
    public ResponseEntity<UserDto> fetchUserById(@RequestParam Long id) {

            UserDto user = userService.fetchUserById(id);
            return ResponseEntity.ok(user);
    }

    @GetMapping("/fetchByName")
    public ResponseEntity<List<UserDto>> fetchUserByName(@RequestParam String name) {

            List<UserDto> users = userService.fetchUserByName(name);
            return ResponseEntity.ok(users);
    }

    @GetMapping("/fetchByEmail")
    public ResponseEntity<UserDto> fetchUserByEmail(@RequestParam String email) {

            UserDto user = userService.fetchUserByEmail(email);
            return ResponseEntity.ok(user);
    }

    @PutMapping("/modify")
    public ResponseEntity<UserDto> modifyUser(@RequestParam Long id, @RequestBody UserDto updatedUser) {

            UserDto modifiedUser = userService.modifyUser(id, updatedUser);
            return ResponseEntity.ok(modifiedUser);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam Long id) {

            String response = userService.deleteUser(id);
            return ResponseEntity.ok(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest != null ? webRequest.getDescription(false) : "No request description",
                "NOT_FOUND"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(
            Exception exception,
            WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest != null ? webRequest.getDescription(false) : "No request description",
                "INTERNAL_SERVER_ERROR"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
