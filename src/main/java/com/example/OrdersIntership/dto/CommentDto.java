package com.example.OrdersIntership.dto;

import lombok.*;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private UserDto user;
    private String text;
    private LocalDate date;
    private TicketDto ticket;

}
