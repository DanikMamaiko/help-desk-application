package com.example.OrdersIntership.api;

import com.example.OrdersIntership.dto.CategoryDto;
import com.example.OrdersIntership.service.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<CategoryDto>> allCategories(){

        return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id){

        return new ResponseEntity<>(categoryService.findCategoryById(id), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<HttpStatus> saveCategory(@RequestBody CategoryDto category){

        categoryService.createCategory(category);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/category")
    public ResponseEntity<HttpStatus> updateCategory(@RequestBody CategoryDto category){

        categoryService.updateCategory(category);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") Long id){

        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
