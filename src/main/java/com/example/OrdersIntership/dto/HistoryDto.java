package com.example.OrdersIntership.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryDto {

    private Long id;
    private TicketDto ticket;
    private LocalDate date;
    private String action;
    private UserDto user;
    private String description;

}
