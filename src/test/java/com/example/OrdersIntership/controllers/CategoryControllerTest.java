package com.example.OrdersIntership.controllers;

import com.example.OrdersIntership.TestBeans;
import com.example.OrdersIntership.dto.CategoryDto;
import com.example.OrdersIntership.service.CategoryServiceImpl;
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
public class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    void getAllCategoriesTest() throws Exception {

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/category"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var categories = categoryService.findAllCategories();

        assertThat(result, notNullValue());

        assertThat(result.getResponse().getContentAsString(), equalTo(new ObjectMapper().writeValueAsString(categories)));

    }

    @Test
    void getCategoryByIdTest() throws Exception {

        Long id = 1L;

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/category/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("firstCategory"))
                .andReturn();

        var category = categoryService.findCategoryById(id);

        assertThat(result, notNullValue());

        assertThat(result.getResponse().getContentAsString(), equalTo(new ObjectMapper().writeValueAsString(category)));

    }

    @Test
    void saveCategoryTest() throws Exception {

        CategoryDto categoryDto = new CategoryDto(1L, "CategoryName");
        String categoryDtoJson = objectMapper.writeValueAsString(categoryDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryDtoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void updateCategoryTest() throws Exception {

        CategoryDto categoryDto = new CategoryDto(1L, "CategoryName");
        String categoryDtoJson = objectMapper.writeValueAsString(categoryDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryDtoJson))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void deleteCategoryTest() throws Exception {

        CategoryDto categoryDto = new CategoryDto(1L, "CategoryName");
        categoryService.createCategory(categoryDto);

        mockMvc.perform(MockMvcRequestBuilders.delete("/category/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isNoContent());

    }


}
