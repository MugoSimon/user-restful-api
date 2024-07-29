package com.mugosimon.user_restful_api.controller;

import com.mugosimon.user_restful_api.entity.User;
import com.mugosimon.user_restful_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> fetchAllUsers() {
        return userService.fetchAllUsers();
    }

    @GetMapping("/fetchById")
    public ResponseEntity<User> fetchUserById(@RequestParam Long id) {
        return userService.fetchUserById(id);
    }

    @GetMapping("/fetchByName")
    public ResponseEntity<List<User>> fetchUserByName(@RequestParam String name) {
        return userService.fetchUserByName(name);
    }

    @GetMapping("/fetchByEmail")
    public ResponseEntity<User> fetchUserByEmail(@RequestParam String email) {
        return userService.fetchUserByEmail(email);
    }

    @PutMapping("/modify")
    public ResponseEntity<String> modifyUser(@RequestParam Long id, @RequestBody User updatedUser) {
        return userService.modifyUser(id, updatedUser);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam Long id) {
        return userService.deleteUser(id);
    }
}
