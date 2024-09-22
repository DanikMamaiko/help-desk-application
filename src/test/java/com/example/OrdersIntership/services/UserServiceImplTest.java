package com.example.OrdersIntership.services;

import com.example.OrdersIntership.dto.UserDto;
import com.example.OrdersIntership.entity.User;
import com.example.OrdersIntership.entity.RoleEnum;
import com.example.OrdersIntership.mapper.UserMapper;
import com.example.OrdersIntership.repository.UserRepository;
import com.example.OrdersIntership.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    UserDto userDto1, userDto2;
    User user1, user2;

    @BeforeEach
    void setUp(){

        userDto1 = new UserDto(1L, "Danik", "Mamaiko", RoleEnum.EMPLOYEE, "user1_mogilev@yopmail.com", "P@ssword1");
        userDto2 = new UserDto(2L, "Ivan", "Borisov", RoleEnum.EMPLOYEE, "user2_mogilev@yopmail.com", "P@ssword1");

        user1 = User.builder()
                .id(1L)
                .firstName("Danik")
                .lastName("Mamaiko")
                .role(RoleEnum.EMPLOYEE)
                .email("user1_mogilev@yopmail.com")
                .password("P@ssword1").build();

        user1 = User.builder()
                .id(2L)
                .firstName("Ivan")
                .lastName("Borisov")
                .role(RoleEnum.EMPLOYEE)
                .email("user2_mogilev@yopmail.com")
                .password("P@ssword1").build();

    }

    @Test
    void findAllUsers() {

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(userMapper.toUserDto(user1)).thenReturn(userDto1);
        Mockito.when(userMapper.toUserDto(user2)).thenReturn(userDto2);

        List <UserDto> usersDto = userService.findAllUsers();

        assertThat(usersDto, notNullValue());
        assertThat(usersDto, equalTo(List.of(userDto1, userDto2)));
        assertThat(usersDto, hasSize(2));

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        Mockito.verify(userMapper, Mockito.times(1)).toUserDto(user1);
        Mockito.verify(userMapper, Mockito.times(1)).toUserDto(user2);

    }

    @Test
    void findUserById() {

        Long id = 1L;

        Mockito.when(userRepository.getUserById(id)).thenReturn(Optional.of(user1));
        Mockito.when(userMapper.toUserDto(user1)).thenReturn(userDto1);

        UserDto userDto = userService.findUserById(id);

        assertThat(userDto, notNullValue());
        assertThat(userDto, equalTo(userDto1));

        Throwable exception = assertThrows(NoSuchElementException.class, () -> userService.findUserById(2L));
        assertThat(exception, instanceOf(NoSuchElementException.class));

        Mockito.verify(userRepository, Mockito.times(1)).getUserById(1L);
        Mockito.verify(userMapper, Mockito.times(1)).toUserDto(user1);


    }

    @Test
    void createUser() {

        when(userMapper.toUser(userDto1)).thenReturn(user1);

        userService.createUser(userDto1);

        Mockito.verify(userRepository, times(1))
                .saveUser(user1.getFirstName(), user1.getLastName(), user1.getRole(), user1.getEmail(), user1.getPassword());

    }

    @Test
    void updateUser() {

        when(userMapper.toUser(userDto1)).thenReturn(user1);

        userService.updateUser(userDto1);

        Mockito.verify(userRepository, times(1))
                .updateUser(user1.getId(), user1.getFirstName(), user1.getLastName(), user1.getRole(), user1.getEmail(), user1.getPassword());

    }

    @Test
    void deleteUser() {

        userService.deleteUser(1L);

        Mockito.verify(userRepository, times(1)).deleteUser(1L);

    }

    @Test
    void getUserByEmail() {

        when(userRepository.getUserByUsername("user1_mogilev@yopmail.com")).thenReturn(Optional.of(user1));
        when(userMapper.toUserDto(user1)).thenReturn(userDto1);

        UserDto userDto = userService.getUserByEmail("user1_mogilev@yopmail.com");

        assertThat(userDto, notNullValue());
        assertThat(userDto, equalTo(userDto1));

        Throwable exception = assertThrows(NoSuchElementException.class, () -> userService.getUserByEmail("ivan"));
        assertThat(exception, instanceOf(NoSuchElementException.class));

        Mockito.verify(userRepository, times(1)).getUserByUsername("user1_mogilev@yopmail.com");
        Mockito.verify(userMapper, times(1)).toUserDto(user1);



    }
}