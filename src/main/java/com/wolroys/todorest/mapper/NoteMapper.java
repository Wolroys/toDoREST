package com.wolroys.todorest.mapper;

import com.wolroys.todorest.dto.NoteDto;
import com.wolroys.todorest.entity.Note;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteMapper {

    private final ModelMapper mapper;

    public Note toEntity(NoteDto dto){
        return mapper.map(dto, Note.class);
    }

    public NoteDto toDto(Note note){
        return mapper.map(note, NoteDto.class);
    }

    public <T> void map(T from, T to){
        mapper.map(from, to);
    }
}
