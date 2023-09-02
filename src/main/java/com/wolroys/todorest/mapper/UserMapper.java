package com.wolroys.todorest.mapper;


import com.wolroys.todorest.dto.UserCreateEditDto;
import com.wolroys.todorest.dto.UserReadDto;
import com.wolroys.todorest.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public User toEntity(UserCreateEditDto dto){
        return dto == null ? null : mapper.map(dto, User.class);
    }

    public UserReadDto toDto(User user){
        return user == null ? null : mapper.map(user, UserReadDto.class);
    }

    public <T> void map(T from, T to){
        mapper.map(from, to);
    }

}
