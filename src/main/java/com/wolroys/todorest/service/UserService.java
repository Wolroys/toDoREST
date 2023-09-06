package com.wolroys.todorest.service;

import com.wolroys.todorest.configuration.PasswordEncoderConfiguration;
import com.wolroys.todorest.dto.UserCreateEditDto;
import com.wolroys.todorest.dto.UserReadDto;
import com.wolroys.todorest.entity.User;
import com.wolroys.todorest.mapper.UserMapper;
import com.wolroys.todorest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

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
                .map(entity -> {
                    entity.setPassword(passwordEncoder.encode(entity.getPassword()));
                    return entity;
                })
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(entity -> new org.springframework.security.core.userdetails.User(
                        entity.getUsername(),
                        entity.getPassword(),
                        Collections.emptyList()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }

    public boolean checkingUser(String username){
        return userRepository.existsByUsername(username);
    }
}
