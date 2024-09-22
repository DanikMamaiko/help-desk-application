package com.example.OrdersIntership.repository;

import com.example.OrdersIntership.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c")
    public List<Category> getAllCategories();

    @Query("SELECT c FROM Category c WHERE c.id = :id")
    public Optional <Category> getCategoryById(@Param("id") Long id);

    @Modifying
    @Query(value = "INSERT INTO category (name) VALUES (:name)", nativeQuery = true)
    public void saveCategory(@Param("name") String name);

    @Modifying
    @Query("UPDATE Category c SET c.name = :name WHERE c.id = :id")
    public void updateCategory(@Param("id") Long id, @Param("name") String name);

    @Modifying
    @Query("DELETE FROM Category c WHERE c.id = :id")
    public void deleteCategory(@Param("id") Long id);


}
