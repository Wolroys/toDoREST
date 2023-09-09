package com.wolroys.todorest.controller;

import com.wolroys.todorest.dto.UserCreateEditDto;
import com.wolroys.todorest.dto.UserReadDto;
import com.wolroys.todorest.service.UserService;
import com.wolroys.todorest.validation.exception.UsernameExistError;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.wolroys.todorest.entity.User;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Operations that help to work with users")
public class UserController {

    private final UserService userService;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Users found")
    public List<UserReadDto> findAll(){
        return userService.findAllUsers();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public UserReadDto findById(@PathVariable int id){
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User was created"),
            @ApiResponse(responseCode = "400", description = "Data entered incorrectly")
    })
    public UserReadDto create(@RequestBody @Valid UserCreateEditDto user){
        return userService.create(user);
    }

    @PatchMapping(value = "/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was updated"),
            @ApiResponse(responseCode = "400", description = "Data entered incorrectly")
    })
    public UserReadDto update(@PathVariable int id, @RequestBody @Valid UserCreateEditDto userCreateEditDto){
        return userService.update(id, userCreateEditDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User was deleted"),
            @ApiResponse(responseCode = "404", description = "User wasn't found")
    })
    public void delete(@PathVariable int id){
        if (!userService.delete(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
