package com.wolroys.todorest.service;

import com.wolroys.todorest.dto.UserCreateEditDto;
import com.wolroys.todorest.dto.UserReadDto;
import com.wolroys.todorest.entity.User;
import com.wolroys.todorest.mapper.UserMapper;
import com.wolroys.todorest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public List<UserReadDto> findAllUsers(){
        return userRepository.findAll()
                .stream().map(mapper::toDto)
                .toList();
    }

    public Optional<UserReadDto> findById(int id){
        return userRepository.findById(id)
                .map(mapper::toDto);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto dto){
        return Optional.of(dto)
                .map(mapper::toEntity)
                .map(userRepository::save)
                .map(mapper::toDto)
                .orElseThrow();
    }
    
    @Transactional
    public Optional<UserReadDto> update(int id, UserCreateEditDto userCreateEditDto){
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()){
            mapper.map(userCreateEditDto, existingUser.get());

            return existingUser
                    .map(userRepository::saveAndFlush)
                    .map(mapper::toDto);
        }
        return Optional.empty();
    }

    @Transactional
    public boolean delete(int id){
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    userRepository.flush();
                    return true;
                }).orElse(false);
    }
}
