package com.example.OrdersIntership.controllers;

import com.example.OrdersIntership.TestBeans;
import com.example.OrdersIntership.dto.AttachmentDto;
import com.example.OrdersIntership.dto.TicketDto;
import com.example.OrdersIntership.service.AttachmentServiceImpl;
import com.example.OrdersIntership.service.TicketServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest(classes = TestBeans.class)
@AutoConfigureMockMvc
class AttachmentControllerTest {

    @Autowired
    AttachmentServiceImpl attachmentService;

    @Autowired
    TicketServiceImpl ticketService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllAttachments() throws Exception {

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/attachment"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        var attachments = attachmentService.findAllAttachments();

        assertThat(attachments, is(not(empty())));

        assertThat(result.getResponse().getContentAsString(), equalTo(new ObjectMapper().writeValueAsString(attachments)));

    }

    @Test
    void getAttachmentById() throws Exception {

        TicketDto ticketDto = ticketService.findTicketById(1L);

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/attachment/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.blobb").value("firstAttachmentBlobb"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ticket").value(ticketDto))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("firstAttachmentName"))
                .andReturn();


        var attachment = attachmentService.findAttachmentById(1L);

        assertThat(attachment, notNullValue());

        assertThat(result.getResponse().getContentAsString(), equalTo(new ObjectMapper().writeValueAsString(attachment)));

    }

    @Test
    void saveAttachment() throws Exception {

        TicketDto ticketDto = ticketService.findTicketById(1L);
        AttachmentDto attachmentDto = new AttachmentDto(2L, "str", ticketDto, "name");
        String attachmentDtoString = objectMapper.writeValueAsString(attachmentDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/attachment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(attachmentDtoString))
                        .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void updateAttachment() throws Exception {

        TicketDto ticketDto = ticketService.findTicketById(1L);
        AttachmentDto attachmentDto = new AttachmentDto(2L, "str", ticketDto, "name");
        String attachmentDtoString = objectMapper.writeValueAsString(attachmentDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/attachment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(attachmentDtoString))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void deleteAttachment() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/attachment/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isNoContent());

    }


}