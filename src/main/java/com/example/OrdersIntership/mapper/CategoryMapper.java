package com.example.OrdersIntership.mapper;

import com.example.OrdersIntership.dto.CategoryDto;
import com.example.OrdersIntership.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toCategoryDto(Category category);

    Category toCategory(CategoryDto categoryDto);

}
