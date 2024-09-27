package com.vendinha.util.mapper;

import com.vendinha.dto.UserDTO;
import com.vendinha.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO userDto);
}