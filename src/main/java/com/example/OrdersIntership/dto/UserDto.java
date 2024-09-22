package com.example.OrdersIntership.dto;

import com.example.OrdersIntership.entity.RoleEnum;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private RoleEnum role;
    private String email;
    private String password;

}
