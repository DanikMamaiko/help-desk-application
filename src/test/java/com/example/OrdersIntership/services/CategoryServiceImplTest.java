package com.example.OrdersIntership.services;

import com.example.OrdersIntership.dto.CategoryDto;
import com.example.OrdersIntership.entity.Category;
import com.example.OrdersIntership.mapper.CategoryMapper;
import com.example.OrdersIntership.repository.CategoryRepository;
import com.example.OrdersIntership.service.CategoryServiceImpl;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @InjectMocks
    CategoryServiceImpl categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryMapper categoryMapper;

    Category category1, category2;
    CategoryDto categoryDto1, categoryDto2;

    @BeforeEach
    void setUp(){

        categoryDto1 = new CategoryDto(1L, "firstCategory");
        categoryDto2 = new CategoryDto(2L, "secondCategory");

        category1 = Category.builder()
                .id(1L)
                .name("firstCategory")
                .build();

        category2 = Category.builder()
                .id(2L)
                .name("secondCategory")
                .build();

    }

    @Test
    void findAllCategories() {

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category2);

        when(categoryRepository.getAllCategories()).thenReturn(categoryList);
        when(categoryMapper.toCategoryDto(category1)).thenReturn(categoryDto1);
        when(categoryMapper.toCategoryDto(category2)).thenReturn(categoryDto2);

        List <CategoryDto> categoryDtoList = categoryService.findAllCategories();

        assertThat(categoryDtoList, notNullValue());
        assertThat(categoryDtoList, equalTo(List.of(categoryDto1, categoryDto2)));
        assertThat(categoryDtoList, hasSize(2));

        Mockito.verify(categoryRepository, times(1)).getAllCategories();
        Mockito.verify(categoryMapper, times(1)).toCategoryDto(category1);
        Mockito.verify(categoryMapper, times(1)).toCategoryDto(category2);


    }

    @Test
    void findCategoryById() {

        Long id = 1L;

        when(categoryRepository.getCategoryById(id)).thenReturn(Optional.of(category1));
        when(categoryMapper.toCategoryDto(category1)).thenReturn(categoryDto1);

        CategoryDto categoryDto = categoryService.findCategoryById(id);

        assertThat(categoryDto, notNullValue());
        assertThat(categoryDto, equalTo(categoryDto1));

        assertThatThrownBy(() -> categoryService.findCategoryById(2L))
                .isInstanceOf(NoSuchElementException.class);

        Mockito.verify(categoryRepository, times(1)).getCategoryById(id);
        Mockito.verify(categoryMapper, times(1)).toCategoryDto(category1);

    }

    @Test
    void createCategory() {

        when(categoryMapper.toCategory(categoryDto1)).thenReturn(category1);

        categoryService.createCategory(categoryDto1);

        Mockito.verify(categoryRepository, times(1))
                .saveCategory(category1.getName());

    }

    @Test
    void updateCategory() {

        when(categoryMapper.toCategory(categoryDto1)).thenReturn(category1);

        categoryService.updateCategory(categoryDto1);

        Mockito.verify(categoryRepository, times(1))
                .updateCategory(category1.getId(), category1.getName());

    }

    @Test
    void deleteCategory() {

        categoryService.deleteCategory(1L);

        Mockito.verify(categoryRepository, times(1)).deleteCategory(1L);

    }
}