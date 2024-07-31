package com.mugosimon.user_restful_api.mapper;

import com.mugosimon.user_restful_api.dto.UserDto;
import com.mugosimon.user_restful_api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto maptoUserDto(User user);

    User mapToUser(UserDto userDto);

}
