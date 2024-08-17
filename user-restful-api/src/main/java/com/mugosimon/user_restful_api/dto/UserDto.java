package com.mugosimon.user_restful_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "User Details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    @Schema(description = "Unique identifier of the user", example = "1", required = true)
    private Long id;

    @Schema(description = "First name of the user", example = "John", required = true)
    private String firstName;

    @Schema(description = "Last name of the user", example = "Doe", required = true)
    private String lastName;

    @Schema(description = "Email address of the user", example = "john.doe@example.com", required = true)
    private String email;
}
