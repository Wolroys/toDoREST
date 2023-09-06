package com.wolroys.todorest.service;

import com.wolroys.todorest.dto.NoteDto;
import com.wolroys.todorest.entity.Note;
import com.wolroys.todorest.entity.User;
import com.wolroys.todorest.mapper.NoteMapper;
import com.wolroys.todorest.repository.NoteRepository;
import com.wolroys.todorest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper mapper;
    private final UserRepository userRepository;

    public List<NoteDto> findAll(UserDetails userDetails){
        int userId = userRepository.findByUsername(userDetails.getUsername()).get().getId();
        return noteRepository.findAllByUserId(userId)
                .stream().map(mapper::toDto)
                .toList();
    }

    public Optional<NoteDto> findById(int id){
        return noteRepository.findById(id)
                .map(mapper::toDto);
    }

    @Transactional
    public NoteDto create(NoteDto noteDto, String username){
        Note note = Optional.of(noteDto)
                .map(mapper::toEntity).orElse(null);
        note.setUser(userRepository.findByUsername(username).orElse(null));
        return Optional.of(note)
                .map(noteRepository::saveAndFlush)
                .map(mapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<NoteDto> update(int id, NoteDto noteDto){
        Optional<Note> exist = noteRepository.findById(id);

        if (exist.isPresent()){
            mapper.map(noteDto, exist.get());
            return exist
                    .map(noteRepository::saveAndFlush)
                    .map(mapper::toDto);
        }
        return Optional.empty();
    }

    @Transactional
    public boolean delete(int id){
        return noteRepository.findById(id)
                .map(entity -> {
                   noteRepository.delete(entity);
                   noteRepository.flush();
                   return true;
                }).orElse(false);
    }
}
