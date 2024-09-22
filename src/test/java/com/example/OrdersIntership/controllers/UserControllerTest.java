package com.example.OrdersIntership.controllers;

import com.example.OrdersIntership.TestBeans;
import com.example.OrdersIntership.dto.UserDto;
import com.example.OrdersIntership.entity.User;
import com.example.OrdersIntership.entity.RoleEnum;
import com.example.OrdersIntership.mapper.UserMapper;
import com.example.OrdersIntership.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = TestBeans.class)
@AutoConfigureMockMvc
@Transactional
@Slf4j
class UserControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserMapper userMapper;

    @Test
    void allUsers() throws Exception {

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var users = userService.findAllUsers();

        assertThat(result, notNullValue());

        assertThat(result.getResponse().getContentAsString(), equalTo(objectMapper.writeValueAsString(users)));

    }

    @Test
    void getUserById() throws Exception {

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Danik"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Mamaiko"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("EMPLOYEE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("user1_mogilev@yopmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("P@ssword1"))
                .andReturn();

        var userById = userService.findUserById(1L);

        assertThat(result, notNullValue());

        assertThat(result.getResponse().getContentAsString(), equalTo(objectMapper.writeValueAsString(userById)));

    }

    @Test
    void saveUser() throws Exception {

        UserDto userDto = new UserDto(7L, "Sergey", "Mamaiko", RoleEnum.MANAGER, "ss@kk.kkk", "S@kdf34");
        String userJsonString = objectMapper.writeValueAsString(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJsonString))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void updateUser() throws Exception {

        UserDto userDto = userService.findUserById(1L);
        User user = userMapper.toUser(userDto);
        user.setFirstName("NewFirstName");
        String userJsonString = objectMapper.writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders.put("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJsonString))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void deleteUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}", 1L)).andExpect(MockMvcResultMatchers.status().isNoContent());

    }
}