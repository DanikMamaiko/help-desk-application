package com.example.OrdersIntership.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {

    private Long id;
    private UserDto user;
    private String rate;
    private LocalDate date;
    private String text;
    private TicketDto ticket;

}
