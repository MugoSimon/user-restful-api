package com.mugosimon.user_restful_api.service.Impl;

import com.mugosimon.user_restful_api.dto.UserDto;
import com.mugosimon.user_restful_api.entity.User;
import com.mugosimon.user_restful_api.exception.ResourceNotFoundException;
import com.mugosimon.user_restful_api.mapper.AutoUserMapper;
import com.mugosimon.user_restful_api.repo.UserRepository;
import com.mugosimon.user_restful_api.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser method called with user: {}", userDto.getFirstName());
        try {
            Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
            if (existingUser.isPresent()) {
                log.warn("User with this email already exists: {}", userDto.getEmail());
                throw new RuntimeException("User with this email already exists.");
            }

            User user = AutoUserMapper.MAPPER.mapToUser(userDto);
            userRepository.save(user);

            log.info("User created successfully: {}", user);
            return AutoUserMapper.MAPPER.maptoUserDto(user);
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            throw new RuntimeException("Error creating user.", e); // or another custom exception
        }
    }

    @Override
    public List<UserDto> addMultipleUsers(List<UserDto> usersDto) {
        log.info("addMultipleUsers method called with users: {}", usersDto.size());
        List<UserDto> createdUsers = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        try {
            for (UserDto userDto : usersDto) {
                log.info("Processing user: {}", userDto.getFirstName());

                Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
                if (existingUser.isPresent()) {
                    String errorMsg = "User with email " + userDto.getEmail() + " already exists.";
                    log.warn(errorMsg);
                    errors.add(errorMsg);
                    continue;
                }

                User user = AutoUserMapper.MAPPER.mapToUser(userDto);
                userRepository.save(user);
                createdUsers.add(AutoUserMapper.MAPPER.maptoUserDto(user));
                log.info("User created successfully: {}", user);
            }

            if (!errors.isEmpty()) {
                log.warn("Some users were not created: {}", errors);
                return createdUsers; // Return successfully created users even if some errors occurred
            }

            return createdUsers;
        } catch (Exception e) {
            log.error("Error adding multiple users: {}", e.getMessage());
            throw new RuntimeException("Error adding multiple users.", e); // or another custom exception
        }
    }

    @Override
    public List<UserDto> fetchAllUsers() {
        log.info("fetchAllUsers method called");
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                log.info("No users found");
                return Collections.emptyList();
            }
            log.info("Fetched all users");
            return users.stream()
                    .map(AutoUserMapper.MAPPER::maptoUserDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error fetching all users: {}", e.getMessage());
            throw new RuntimeException("Error fetching all users.", e); // or another custom exception
        }
    }

    @Override
    public UserDto fetchUserById(Long id) {
        log.info("fetchUserById method called with ID: {}", id);
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

            log.info("Fetched user by ID: {}", id);
            return AutoUserMapper.MAPPER.maptoUserDto(user);
        } catch (ResourceNotFoundException e) {
            log.error("User not found with ID: {}", id);
            throw e; // Re-throwing the custom exception for handling at a higher level
        } catch (Exception e) {
            log.error("Error fetching user by ID: {}", e.getMessage());
            throw new RuntimeException("Error fetching user by ID.", e); // or another custom exception
        }
    }

    @Override
    public List<UserDto> fetchUserByName(String name) {
        log.info("fetchUserByName method called with name: {}", name);
        try {
            List<User> users = userRepository.findByFirstnameOrLastname(name);
            if (users.isEmpty()) {
                log.info("No users found with name: {}", name);
                return Collections.emptyList(); // Return an empty list if no users are found
            }

            log.info("Fetched users by name: {}", name);
            return users.stream()
                    .map(AutoUserMapper.MAPPER::maptoUserDto)
                    .collect(Collectors.toList()); // Map and collect to List<UserDto>
        } catch (Exception e) {
            log.error("Error fetching users by name: {}", e.getMessage());
            throw new RuntimeException("Error fetching users by name.", e); // or another custom exception
        }
    }

    @Override
    public UserDto fetchUserByEmail(String email) {
        log.info("fetchUserByEmail method called with email: {}", email);
        try {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (optionalUser.isEmpty()) {
                log.info("No user found with email: {}", email);
            }

            User user = optionalUser.get();
            log.info("Fetched user by email: {}", email);
            return AutoUserMapper.MAPPER.maptoUserDto(user);
        } catch (ResourceNotFoundException e) {
            log.error("User not found with email: {}", email);
            throw e; // Re-throwing the custom exception for handling at a higher level
        } catch (Exception e) {
            log.error("Error fetching user by email: {}", e.getMessage());
            throw new RuntimeException("Error fetching user by email.", e); // or another custom exception
        }
    }

    @Override
    public UserDto modifyUser(Long id, UserDto updatedUserDto) {
        log.info("modifyUser method called with ID: {}", id);
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

            // Update the user details
            user = AutoUserMapper.MAPPER.mapToUser(updatedUserDto);
            user.setId(id);  // Ensure the ID remains unchanged
            userRepository.save(user);

            log.info("User modified successfully: {}", user);
            return AutoUserMapper.MAPPER.maptoUserDto(user);
        } catch (ResourceNotFoundException e) {
            log.error("User not found with ID: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Error modifying user with ID: {}", id, e);
            throw new RuntimeException("Error modifying user.", e);
        }
    }

    @Override
    public String deleteUser(Long id) {
        log.info("deleteUser method called with ID: {}", id);
        try {
            if (!userRepository.existsById(id)) {
                log.info("User not found with ID: {}", id);
                throw new ResourceNotFoundException("User", "id", id);
            }

            userRepository.deleteById(id);
            log.info("User deleted successfully with ID: {}", id);
            return "User deleted successfully.";
        } catch (ResourceNotFoundException e) {
            log.error("User not found with ID: {}", id);
            throw e; // Re-throwing the custom exception for handling at a higher level
        } catch (Exception e) {
            log.error("Error deleting user with ID: {}", id, e);
            throw new RuntimeException("Error deleting user.", e); // or another custom exception
        }
    }
}
