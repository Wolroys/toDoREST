package com.wolroys.todorest.dto;

import com.wolroys.todorest.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UserCreateEditDto {

    @UniqueUsername
    String username;

    @Email
    String email;

    @NotNull
            @Size(min = 3)
    String password;
}
