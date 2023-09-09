package com.wolroys.todorest.dto;


import com.wolroys.todorest.validation.annotation.NotNullAndBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public class NoteDto {

    @NotNullAndBlank(message = "The title field must be filled in")
            @Schema(description = "title of note")
    String title;

    @NotNullAndBlank(message = "The content field must be filled in")
            @Schema(description = "content of note")
    String content;

    public NoteDto() {
    }

    public NoteDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
