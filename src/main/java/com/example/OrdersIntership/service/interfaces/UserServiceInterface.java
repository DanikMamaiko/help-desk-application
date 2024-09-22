package com.example.OrdersIntership.service.interfaces;

import com.example.OrdersIntership.dto.UserDto;
import com.example.OrdersIntership.exception.NoSuchUserException;

import java.util.List;

public interface UserServiceInterface{

    List<UserDto> findAllUsers();

    UserDto findUserById(Long id) throws NoSuchUserException;

    void createUser(UserDto entity);

    void updateUser(UserDto entity);

    void deleteUser(Long id);

    public UserDto getUserByEmail(String email);

}
