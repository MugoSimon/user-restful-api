package com.mugosimon.user_restful_api.service;

import com.mugosimon.user_restful_api.entity.User;
import com.mugosimon.user_restful_api.repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> createUser(User user) {
        log.info("createUser method called with user: {}", user);
        try {
            Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                return ResponseEntity.badRequest().body("Status Code: 400, Message: User with this email already exists.");
            }
            userRepository.save(user);
            log.info("User created successfully: {}", user);
            return ResponseEntity.ok("Status Code: 200, Message: User created successfully.");
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            return ResponseEntity.status(500).body("Status Code: 500, Message: Error creating user.");
        }
    }

    public ResponseEntity<List<User>> fetchAllUsers() {
        log.info("fetchAllUsers method called");
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                return ResponseEntity.ok().body(List.of(new User()));
            }
            log.info("Fetched all users");
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            log.error("Error fetching all users: {}", e.getMessage());
            return ResponseEntity.status(500).body(List.of());
        }
    }

    public ResponseEntity<User> fetchUserById(Long id) {
        log.info("fetchUserById method called with ID: {}", id);
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isEmpty()) {
                return ResponseEntity.status(404).body(null);
            }
            log.info("Fetched user by ID: {}", id);
            return ResponseEntity.ok(user.get());
        } catch (Exception e) {
            log.error("Error fetching user by ID: {}", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    public ResponseEntity<List<User>> fetchUserByName(String name) {
        log.info("fetchUserByName method called with name: {}", name);
        try {
            List<User> users = userRepository.findByFirstnameOrLastname(name);
            if (users.isEmpty()) {
                return ResponseEntity.status(404).body(List.of());
            }
            log.info("Fetched users by name: {}", name);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            log.error("Error fetching users by name: {}", name, e);
            return ResponseEntity.status(500).body(List.of());
        }
    }

    public ResponseEntity<User> fetchUserByEmail(String email) {
        log.info("fetchUserByEmail method called with email: {}", email);
        try {
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isEmpty()) {
                return ResponseEntity.status(404).body(null);
            }
            log.info("Fetched user by email: {}", email);
            return ResponseEntity.ok(user.get());
        } catch (Exception e) {
            log.error("Error fetching user by email: {}", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    public ResponseEntity<String> modifyUser(Long id, User updatedUser) {
        log.info("modifyUser method called with ID: {}", id);
        try {
            Optional<User> existingUser = userRepository.findById(id);
            if (existingUser.isEmpty()) {
                return ResponseEntity.badRequest().body("Status Code: 400, Message: User not found.");
            }
            User user = existingUser.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            userRepository.save(user);
            log.info("User modified successfully: {}", user);
            return ResponseEntity.ok("Status Code: 200, Message: User modified successfully.");
        } catch (Exception e) {
            log.error("Error modifying user: {}", e.getMessage());
            return ResponseEntity.status(500).body("Status Code: 500, Message: Error modifying user.");
        }
    }

    public ResponseEntity<String> deleteUser(Long id) {
        log.info("deleteUser method called with ID: {}", id);
        try {
            Optional<User> existingUser = userRepository.findById(id);
            if (existingUser.isEmpty()) {
                return ResponseEntity.badRequest().body("Status Code: 400, Message: User not found.");
            }
            userRepository.deleteById(id);
            log.info("User deleted successfully: {}", id);
            return ResponseEntity.ok("Status Code: 200, Message: User deleted successfully.");
        } catch (Exception e) {
            log.error("Error deleting user: {}", e.getMessage());
            return ResponseEntity.status(500).body("Status Code: 500, Message: Error deleting user.");
        }
    }
}
