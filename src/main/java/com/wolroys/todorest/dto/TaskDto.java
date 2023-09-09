package com.wolroys.todorest.dto;

import com.wolroys.todorest.validation.annotation.NotNullAndBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TaskDto {
    @NotNullAndBlank(message = "The title field must be filled in")
            @Schema(description = "title of task")
    String title;

    @NotNullAndBlank(message = "The description field must be filled in")
            @Schema(description = "description of task")
    String description;

    @NotNull(message = "You must specify the deadline date")
            @Future(message = "Incorrect date")
            @Schema(description = "deadline date")
    LocalDate dueDate;

    public TaskDto() {
    }

    public TaskDto(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
