package com.example.OrdersIntership.controllers;

import com.example.OrdersIntership.TestBeans;
import com.example.OrdersIntership.dto.TicketDto;
import com.example.OrdersIntership.dto.UserDto;
import com.example.OrdersIntership.service.HistoryServiceImpl;
import com.example.OrdersIntership.service.TicketServiceImpl;
import com.example.OrdersIntership.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
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
class HistoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    HistoryServiceImpl historyService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    TicketServiceImpl ticketService;

    @Test
    void allHistories() throws Exception {

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/history"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var histories = historyService.findAllHistories();

        assertThat(result, notNullValue());

        assertThat(result.getResponse().getContentAsString(), equalTo(objectMapper.writeValueAsString(histories)));

    }

    @Test
    void getHistoryById() throws Exception {

        TicketDto ticket = ticketService.findTicketById(1L);
        UserDto user = userService.findUserById(2L);

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/history/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ticket").value(ticket))
                .andExpect(MockMvcResultMatchers.jsonPath("$.action").value("INPROGRESS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user").value(user))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("historyDescription"))
                .andReturn();

        var historyById = historyService.findHistoryById(1L);

        assertThat(result, notNullValue());

        assertThat(result.getResponse().getContentAsString(), equalTo(objectMapper.writeValueAsString(historyById)));

    }

    @Test
    void saveHistory() throws Exception {

        var history = historyService.findHistoryById(1L);
        String historyJsonString = objectMapper.writeValueAsString(history);

        mockMvc.perform(MockMvcRequestBuilders.post("/history")
                .contentType(MediaType.APPLICATION_JSON)
                .content(historyJsonString))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @SneakyThrows
    void updateHistory() {

        var history = historyService.findHistoryById(1L);
        history.setDescription("newDescription");
        String historyJsonString = objectMapper.writeValueAsString(history);

        mockMvc.perform(MockMvcRequestBuilders.put("/history")
                .contentType(MediaType.APPLICATION_JSON)
                .content(historyJsonString))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void deleteHistory() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/history/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }
}