package com.example.OrdersIntership.mapper;

import com.example.OrdersIntership.dto.UserDto;
import com.example.OrdersIntership.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "firstName", target = "firstName")
    UserDto toUserDto(User user);

    @Mapping(source = "firstName", target = "firstName")
    User toUser(UserDto userDto);

}
