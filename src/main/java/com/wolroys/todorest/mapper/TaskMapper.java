package com.wolroys.todorest.mapper;

import com.wolroys.todorest.dto.NoteDto;
import com.wolroys.todorest.dto.TaskDto;
import com.wolroys.todorest.entity.Note;
import com.wolroys.todorest.entity.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final ModelMapper mapper;

    public Task toEntity(TaskDto dto){
        return mapper.map(dto, Task.class);
    }

    public TaskDto toDto(Task task){
        return mapper.map(task, TaskDto.class);
    }

    public <T> void map(T from, T to){
        mapper.map(from, to);
    }
}
