package com.mugosimon.user_restful_api.controller;

import com.mugosimon.user_restful_api.dto.UserDto;
import com.mugosimon.user_restful_api.exception.ErrorDetails;
import com.mugosimon.user_restful_api.exception.ResourceNotFoundException;
import com.mugosimon.user_restful_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "User - Restful - Api",
        description = "This controller provides CRUD operations for managing users, including creating users, fetching users by various criteria, modifying user information, and deleting users. It also handles exceptions related to resource not found and other global exceptions."
)
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Create a new user", description = "Adds a new user to the system.")
    @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Add multiple users", description = "Adds multiple users to the system at once.")
    @ApiResponse(responseCode = "201", description = "Users added successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    @PostMapping("/addMultiple")
    public ResponseEntity<List<UserDto>> addMultipleUsers(@RequestBody @Valid List<UserDto> usersDto) {
        List<UserDto> addedUsers = userService.addMultipleUsers(usersDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedUsers);
    }

    @Operation(summary = "Fetch all users", description = "Retrieves a list of all users in the system.")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully", content = @Content(mediaType = "application/json"))
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> fetchAllUsers() {
        List<UserDto> users = userService.fetchAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Fetch user by ID", description = "Retrieves the details of a user by their ID.")
    @ApiResponse(responseCode = "200", description = "User retrieved successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    @GetMapping("/fetchById")
    public ResponseEntity<UserDto> fetchUserById(@RequestParam Long id) {
        UserDto user = userService.fetchUserById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Fetch users by name", description = "Retrieves a list of users by their name.")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully", content = @Content(mediaType = "application/json"))
    @GetMapping("/fetchByName")
    public ResponseEntity<List<UserDto>> fetchUserByName(@RequestParam String name) {
        List<UserDto> users = userService.fetchUserByName(name);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Fetch user by email", description = "Retrieves the details of a user by their email.")
    @ApiResponse(responseCode = "200", description = "User retrieved successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    @GetMapping("/fetchByEmail")
    public ResponseEntity<UserDto> fetchUserByEmail(@RequestParam String email) {
        UserDto user = userService.fetchUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Modify user", description = "Updates the details of an existing user.")
    @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    @PutMapping("/modify")
    public ResponseEntity<UserDto> modifyUser(@RequestParam Long id, @RequestBody UserDto updatedUser) {
        UserDto modifiedUser = userService.modifyUser(id, updatedUser);
        return ResponseEntity.ok(modifiedUser);
    }

    @Operation(summary = "Delete user", description = "Deletes a user by their ID.")
    @ApiResponse(responseCode = "200", description = "User deleted successfully", content = @Content(mediaType = "text/plain"))
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam Long id) {
        String response = userService.deleteUser(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Handle ResourceNotFoundException", description = "Handles cases where a requested resource (e.g., a user) is not found.")
    @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(mediaType = "application/json"))
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

    @Operation(summary = "Handle global exceptions", description = "Handles general exceptions that occur in the system.")
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
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
