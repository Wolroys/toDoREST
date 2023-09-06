package com.wolroys.todorest.service;

import com.wolroys.todorest.dto.NoteDto;
import com.wolroys.todorest.dto.TaskDto;
import com.wolroys.todorest.entity.Note;
import com.wolroys.todorest.entity.Task;
import com.wolroys.todorest.mapper.NoteMapper;
import com.wolroys.todorest.mapper.TaskMapper;
import com.wolroys.todorest.repository.TaskRepository;
import com.wolroys.todorest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper mapper;
    private final UserRepository userRepository;

    public List<TaskDto> findAll(int userId){
        return taskRepository.findAllByUserId(userId)
                .stream().map(mapper::toDto)
                .toList();
    }

    public Optional<TaskDto> findById(int id){
        return taskRepository.findById(id)
                .map(mapper::toDto);
    }

    @Transactional
    public TaskDto create(TaskDto taskDto, String username){
        Task task = Optional.of(taskDto)
                .map(mapper::toEntity).orElse(null);

        task.setUser(userRepository.findByUsername(username).orElse(null))

        return Optional.of(task)
                .map(taskRepository::saveAndFlush)
                .map(mapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<TaskDto> update(int id, TaskDto taskDto){
        Optional<Task> exist = taskRepository.findById(id);

        if (exist.isPresent()){
            mapper.map(taskDto, exist.get());
            return exist
                    .map(taskRepository::saveAndFlush)
                    .map(mapper::toDto);
        }
        return Optional.empty();
    }

    @Transactional
    public boolean delete(int id){
        return taskRepository.findById(id)
                .map(entity -> {
                   taskRepository.delete(entity);
                   taskRepository.flush();
                   return true;
                }).orElse(false);
    }
}
