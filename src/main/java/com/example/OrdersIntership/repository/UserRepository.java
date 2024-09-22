package com.example.OrdersIntership.repository;

import com.example.OrdersIntership.entity.User;
import com.example.OrdersIntership.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u")
    public List<User> getAllUsers();

    @Query("SELECT u FROM User u WHERE u.id = :id")
    public Optional <User> getUserById(@Param("id") Long id);

    @Modifying
    @Query(value = "INSERT INTO user (first_name, last_name, role_id, email, password) VALUES (:firstName, :lastName, :role, :email, :password)", nativeQuery = true)
    public void saveUser(@Param("firstName") String firstName,
                            @Param("lastName") String lastName,
                            @Param("role") RoleEnum role,
                            @Param("email") String email,
                            @Param("password") String password);

    @Modifying
    @Query("UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName, u.role = :role, u.email = :email, u.password = :password" +
            " WHERE u.id = :id")
    public void updateUser(@Param("id") Long id,
                         @Param("firstName") String firstName,
                         @Param("lastName") String lastName,
                         @Param("role") RoleEnum role,
                         @Param("email") String email,
                         @Param("password") String password);

    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :id")
    public void deleteUser(@Param("id") Long id);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public Optional <User> getUserByUsername(@Param("email") String email);

}
