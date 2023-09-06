package com.wolroys.todorest.controller;

import com.wolroys.todorest.dto.NoteDto;
import com.wolroys.todorest.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/v1/api/notes/{id}")
    public List<NoteDto> findAll(@PathVariable int id){
        return noteService.findAll(id);
    }

    @GetMapping("/v1/api/note/{id}")
    public NoteDto findById(@PathVariable int id){
        return noteService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/v1/api/note")
    public ResponseEntity<NoteDto> create(@RequestBody NoteDto noteDto){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.create(noteDto, userDetails.getUsername()));
    }

    @PatchMapping("/v1/api/note/{id}/update")
    public NoteDto update(@PathVariable int id, NoteDto dto){
        return noteService.update(id, dto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/v1/api/note/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        if (!noteService.delete(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
