package com.example.OrdersIntership.service.interfaces;

import com.example.OrdersIntership.dto.CategoryDto;

import java.util.List;

public interface CategoryServiceInterface{

    List<CategoryDto> findAllCategories();

    CategoryDto findCategoryById(Long id);

    void createCategory(CategoryDto entity);

    void updateCategory(CategoryDto entity);

    void deleteCategory(Long id);

}
