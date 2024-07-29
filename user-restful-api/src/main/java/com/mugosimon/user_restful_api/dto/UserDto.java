package com.mugosimon.user_restful_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
