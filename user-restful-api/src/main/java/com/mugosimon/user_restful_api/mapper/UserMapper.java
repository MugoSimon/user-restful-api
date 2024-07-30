package com.mugosimon.user_restful_api.mapper;

import com.mugosimon.user_restful_api.dto.UserDto;
import com.mugosimon.user_restful_api.entity.User;

public class UserMapper {

    // converting from User to UserDto Jpa Entity
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    // converting from UserDto to User Jpa Entity
    public static User mapToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return user;
    }

    // converting from UserDto to User Jpa Entity
    public static User mapToUser(UserDto userDto, User user) {
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public static UserDto mapToUserDto(User user, UserDto userDto) {
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
