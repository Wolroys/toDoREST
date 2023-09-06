package com.wolroys.todorest.controller;

import com.wolroys.todorest.dto.TaskDto;
import com.wolroys.todorest.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class TaskController {

    private final TaskService taskService;

    @GetMapping("/v1/api/tasks/{id}")
    public List<TaskDto> findAll(@PathVariable int id){
        return taskService.findAll(id);
    }

    @GetMapping("/v1/api/task/{id}")
    public TaskDto findById(@PathVariable int id){
        return taskService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/v1/api/task")
    public TaskDto create(@RequestBody TaskDto taskDto){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskService.create(taskDto, userDetails.getUsername());
    }

    @PatchMapping("/v1/api/task/{id}/update")
    public TaskDto update(@PathVariable int id, TaskDto dto){
        return taskService.update(id, dto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/v1/api/task/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        if (!taskService.delete(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
