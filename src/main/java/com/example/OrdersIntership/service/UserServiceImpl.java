package com.example.OrdersIntership.service;

import com.example.OrdersIntership.dto.UserDto;
import com.example.OrdersIntership.entity.User;
import com.example.OrdersIntership.mapper.UserMapper;
import com.example.OrdersIntership.repository.UserRepository;
import com.example.OrdersIntership.service.interfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findUserById(Long id) {

        return userRepository.getUserById(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new NoSuchElementException("User not found with id " + id));

    }

    @Override
    @Transactional
    public void createUser(UserDto entity) {
        User user = userMapper.toUser(entity);
        userRepository.saveUser(user.getFirstName(), user.getLastName(), user.getRole(), user.getEmail(), user.getPassword());
    }

    @Override
    @Transactional
    public void updateUser(UserDto entity) {
        User user = userMapper.toUser(entity);
        userRepository.updateUser(user.getId(), user.getFirstName(), user.getLastName(), user.getRole(), user.getEmail(), user.getPassword());
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserByEmail(String email) {

        return userRepository.getUserByUsername(email)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));

    }

//    @Transactional
//    public boolean userIsExists(String email, String password) {
//
//        boolean flag = false;
//
//        Optional<UserDto> userFromDb = getUserByEmail(email);
//
//        if(userFromDb.isEmpty()){
//            return false;
//        }
//
//        String emailFromDb = userFromDb.get().getEmail();
//        String passwordFromDb = userFromDb.get().getPassword();
//
//
//        if(emailFromDb.equals(email) && passwordFromDb.equals(password)){
//            flag =  true;
//        }
//
//        return flag;
//
//    }


}
