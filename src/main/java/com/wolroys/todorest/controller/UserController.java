package com.wolroys.todorest.controller;

import com.wolroys.todorest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wolroys.todorest.entity.User;

import java.util.List;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAll(){
        return userService.findAllUsers();
    }
}
