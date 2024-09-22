package com.example.OrdersIntership.controllers;

import com.example.OrdersIntership.TestBeans;
import com.example.OrdersIntership.dto.CategoryDto;
import com.example.OrdersIntership.dto.UserDto;
import com.example.OrdersIntership.service.CategoryServiceImpl;
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
class TicketControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TicketServiceImpl ticketService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    void allTickets() throws Exception {

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/ticket"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var tickets = ticketService.findAllTickets();

        assertThat(result, notNullValue());

        assertThat(result.getResponse().getContentAsString(), equalTo(objectMapper.writeValueAsString(tickets)));

    }

    @Test
    void getTicketById() throws Exception {

        UserDto assignee = userService.findUserById(3L);
        UserDto owner = userService.findUserById(1L);
        UserDto approver = userService.findUserById(5L);
        CategoryDto category = categoryService.findCategoryById(1L);

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/ticket/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ticketName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("ticketDescription"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.assignee").value(assignee))
                .andExpect(MockMvcResultMatchers.jsonPath("$.owner").value(owner))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value("NEW"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value(category))
                .andExpect(MockMvcResultMatchers.jsonPath("$.urgency").value("AVERAGE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.approver").value(approver))
                .andReturn();

        var ticketById = ticketService.findTicketById(1L);

        assertThat(result, notNullValue());

        assertThat(result.getResponse().getContentAsString(), equalTo(objectMapper.writeValueAsString(ticketById)));

    }

    @Test
    void saveTicket() throws Exception {

        var ticketById = ticketService.findTicketById(1L);
        String ticketJsonString = objectMapper.writeValueAsString(ticketById);

        mockMvc.perform(MockMvcRequestBuilders.post("/ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ticketJsonString))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void updateTicket() throws Exception {

        var ticketById = ticketService.findTicketById(1L);
        ticketById.setName("testTicketName");
        String ticketJsonString = objectMapper.writeValueAsString(ticketById);

        mockMvc.perform(MockMvcRequestBuilders.put("/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ticketJsonString))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void deleteTicket() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/ticket/{id}", 1L)).andExpect(MockMvcResultMatchers.status().isNoContent());

    }
}