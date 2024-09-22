package com.example.OrdersIntership.controllers;

import com.example.OrdersIntership.TestBeans;
import com.example.OrdersIntership.dto.TicketDto;
import com.example.OrdersIntership.dto.UserDto;
import com.example.OrdersIntership.service.FeedbackServiceImpl;
import com.example.OrdersIntership.service.TicketServiceImpl;
import com.example.OrdersIntership.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
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
class FeedbackControllerTest {

    @Autowired
    FeedbackServiceImpl feedbackService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    TicketServiceImpl ticketService;

    @Test
    void allFeedbacks() throws Exception {

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/feedback"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var feedbacks = feedbackService.findAllFeedbacks();

        assertThat(result, notNullValue());

        assertThat(result.getResponse().getContentAsString(), equalTo(objectMapper.writeValueAsString(feedbacks)));

    }

    @Test
    void getFeedbackById() throws Exception {

        UserDto userDtoInd2 = userService.findUserById(2L);
        TicketDto ticketDtoInd1 = ticketService.findTicketById(1L);

        Long id = 1L;

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/feedback/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("feedbackText"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user").value(userDtoInd2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ticket").value(ticketDtoInd1))
                .andReturn();

        var feedbackById = feedbackService.findFeedbackById(id);

        assertThat(result, notNullValue());

        assertThat(result.getResponse().getContentAsString(), equalTo(objectMapper.writeValueAsString(feedbackById)));

    }

    @Test
    void saveFeedback() throws Exception {

        var feedback = feedbackService.findFeedbackById(1L);
        String feedbackJsonString = objectMapper.writeValueAsString(feedback);

        mockMvc.perform(MockMvcRequestBuilders.post("/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(feedbackJsonString))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void updateFeedback() throws Exception {

        var feedback = feedbackService.findFeedbackById(1L);
        String feedbackJsonString = objectMapper.writeValueAsString(feedback);

        mockMvc.perform(MockMvcRequestBuilders.put("/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(feedbackJsonString))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void deleteFeedback() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/feedback/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }
}