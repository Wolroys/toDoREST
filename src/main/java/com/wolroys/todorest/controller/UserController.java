package com.wolroys.todorest.controller;

import com.wolroys.todorest.dto.UserCreateEditDto;
import com.wolroys.todorest.dto.UserReadDto;
import com.wolroys.todorest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.wolroys.todorest.entity.User;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserReadDto> findAll(){
        return userService.findAllUsers();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserReadDto findById(@PathVariable int id){
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@RequestBody UserCreateEditDto user){
        return userService.create(user);
    }

    @PatchMapping("/{id}/update")
    public UserReadDto update(@PathVariable int id, @RequestBody UserCreateEditDto userCreateEditDto){
        return userService.update(id, userCreateEditDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        if (!userService.delete(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
