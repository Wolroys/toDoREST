package com.wolroys.todorest.dto;

import com.wolroys.todorest.validation.annotation.NotNullAndBlank;
import com.wolroys.todorest.validation.annotation.UniqueUsername;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UserCreateEditDto {

    @NotNullAndBlank(message = "You must fill in the username field")
            @UniqueUsername(message = "This username is already taken")
            @Schema(description = "user's name")
    String username;

    @Email(message = "You must use correct email")
    @NotBlank(message = "You must fill in the email field")
            @Schema(description = "user's email")
    String email;

    @NotNullAndBlank(message = "You must fill in the password field")
            @Size(min = 3, message = "The password length must be at least 3")
            @Schema(description = "user's password")
    String password;
}
