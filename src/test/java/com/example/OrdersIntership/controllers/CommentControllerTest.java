package com.example.OrdersIntership.controllers;

import com.example.OrdersIntership.TestBeans;
import com.example.OrdersIntership.dto.CommentDto;
import com.example.OrdersIntership.dto.TicketDto;
import com.example.OrdersIntership.dto.UserDto;
import com.example.OrdersIntership.service.CommentServiceImpl;
import com.example.OrdersIntership.service.TicketServiceImpl;
import com.example.OrdersIntership.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestBeans.class)
@AutoConfigureMockMvc
@Transactional
class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    TicketServiceImpl ticketService;

    @Test
    public void allComments() throws Exception {

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/comment"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var comments = commentService.findAllComments();

        assertThat(result, notNullValue());

        assertThat(result.getResponse().getContentAsString(), equalTo(new ObjectMapper().writeValueAsString(comments)));

    }

    @Test
    public void getCommentById() throws Exception {

        Long id = 1L;

        UserDto userDtoInd2 = userService.findUserById(2L);
        TicketDto ticketDtoInd1 = ticketService.findTicketById(1L);

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/comment/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user").value(userDtoInd2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("commentText"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ticket").value(ticketDtoInd1))
                .andReturn();

        var commentById = commentService.findCommentById(id);

        assertThat(result, notNullValue());

        assertThat(result.getResponse().getContentAsString(), equalTo(new ObjectMapper().writeValueAsString(commentById)));

    }

    @Test
    public void saveComment() throws Exception {

        CommentDto comment = commentService.findCommentById(1L);
        String commentString = objectMapper.writeValueAsString(comment);

        mockMvc.perform(MockMvcRequestBuilders.post("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(commentString))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void updateComment() throws Exception {

        CommentDto comment = commentService.findCommentById(1L);
        comment.setText("UpdateComment");
        String commentString = objectMapper.writeValueAsString(comment);

        mockMvc.perform(MockMvcRequestBuilders.put("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(commentString))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    public void deleteComment() throws Exception {

        mockMvc.perform(delete("/comment/{id}", 1L))
                .andExpect(status().isNoContent());

    }
}