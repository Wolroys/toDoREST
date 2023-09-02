package com.wolroys.todorest.dto;

import lombok.Value;

@Value
public class UserCreateEditDto {

    String username;

    String email;

    String password;
}
