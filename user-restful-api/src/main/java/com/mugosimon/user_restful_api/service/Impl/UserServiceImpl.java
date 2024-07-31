package com.mugosimon.user_restful_api.service.Impl;

import com.mugosimon.user_restful_api.dto.UserDto;
import com.mugosimon.user_restful_api.entity.User;
import com.mugosimon.user_restful_api.mapper.AutoUserMapper;
import com.mugosimon.user_restful_api.repo.UserRepository;
import com.mugosimon.user_restful_api.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<String> createUser(UserDto userDto) {
        log.info("createUser method called with user: {}", userDto.getFirstName());
        try {
            Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
            if (existingUser.isPresent()) {
                return ResponseEntity.badRequest().body("User with this email already exists.");
            }

            User user = AutoUserMapper.MAPPER.mapToUser(userDto);
            userRepository.save(user);

            log.info("User created successfully: {}", user);
            return ResponseEntity.status(HttpStatus.OK).body("User created successfully.");
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message: Error creating user.");
        }
    }

    @Override
    public ResponseEntity<List<UserDto>> fetchAllUsers() {
        log.info("fetchAllUsers method called");
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                return ResponseEntity.ok().body(List.of());
            }
            log.info("Fetched all users");
            List<UserDto> userDtos = users.stream()
                    .map(AutoUserMapper.MAPPER::maptoUserDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(userDtos);
        } catch (Exception e) {
            log.error("Error fetching all users: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    @Override
    public ResponseEntity<UserDto> fetchUserById(Long id) {
        log.info("fetchUserById method called with ID: {}", id);
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            log.info("Fetched user by ID: {}", id);
            return ResponseEntity.ok(AutoUserMapper.MAPPER.maptoUserDto(user.get()));
        } catch (Exception e) {
            log.error("Error fetching user by ID: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<List<UserDto>> fetchUserByName(String name) {
        log.info("fetchUserByName method called with name: {}", name);
        try {
            List<User> users = userRepository.findByFirstnameOrLastname(name);
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
            }
            log.info("Fetched users by name: {}", name);
            List<UserDto> userDtos = users.stream()
                    .map(AutoUserMapper.MAPPER::maptoUserDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(userDtos);
        } catch (Exception e) {
            log.error("Error fetching users by name: {}", name, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    @Override
    public ResponseEntity<UserDto> fetchUserByEmail(String email) {
        log.info("fetchUserByEmail method called with email: {}", email);
        try {
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            log.info("Fetched user by email: {}", email);
            return ResponseEntity.ok(AutoUserMapper.MAPPER.maptoUserDto(user.get()));
        } catch (Exception e) {
            log.error("Error fetching user by email: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<String> modifyUser(Long id, UserDto updatedUserDto) {
        log.info("modifyUser method called with ID: {}", id);
        try {
            Optional<User> existingUser = userRepository.findById(id);
            if (existingUser.isEmpty()) {
                return ResponseEntity.badRequest().body("User not found.");
            }
            User user = existingUser.get();
            user = AutoUserMapper.MAPPER.mapToUser(updatedUserDto);
            user.setId(id); // Ensure the ID remains unchanged
            userRepository.save(user);
            log.info("User modified successfully: {}", user);
            return ResponseEntity.ok("User modified successfully.");
        } catch (Exception e) {
            log.error("Error modifying user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error modifying user.");
        }
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id) {
        log.info("deleteUser method called with ID: {}", id);
        try {
            Optional<User> existingUser = userRepository.findById(id);
            if (existingUser.isEmpty()) {
                return ResponseEntity.badRequest().body("User not found.");
            }
            userRepository.deleteById(id);
            log.info("User deleted successfully: {}", id);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (Exception e) {
            log.error("Error deleting user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user.");
        }
    }
}
