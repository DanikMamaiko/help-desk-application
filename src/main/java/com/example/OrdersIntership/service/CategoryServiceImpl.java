package com.example.OrdersIntership.service;

import com.example.OrdersIntership.dto.CategoryDto;
import com.example.OrdersIntership.entity.Category;
import com.example.OrdersIntership.mapper.CategoryMapper;
import com.example.OrdersIntership.repository.CategoryRepository;
import com.example.OrdersIntership.service.interfaces.CategoryServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryServiceInterface {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> findAllCategories() {
        return categoryRepository.getAllCategories()
                .stream()
                .map(categoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto findCategoryById(Long id) {
        return categoryRepository.getCategoryById(id)
                .map(categoryMapper::toCategoryDto)
                .orElseThrow(() -> new NoSuchElementException("Category not found"));
    }

    @Override
    @Transactional
    public void createCategory(CategoryDto entity) {
        Category category = categoryMapper.toCategory(entity);
        categoryRepository.saveCategory(category.getName());
    }

    @Override
    @Transactional
    public void updateCategory(CategoryDto entity) {
        Category category = categoryMapper.toCategory(entity);
        categoryRepository.updateCategory(category.getId(), category.getName());
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteCategory(id);
    }
}
