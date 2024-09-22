package com.example.OrdersIntership.dto;

import com.example.OrdersIntership.entity.StateEnum;
import com.example.OrdersIntership.entity.UrgencyEnum;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private Long id;
    private String name;
    private String description;
    private LocalDate createdOn;
    private LocalDate desiredResolutionDate;
    private UserDto assignee;
    private UserDto owner;
    private StateEnum state;
    private CategoryDto category;
    private UrgencyEnum urgency;
    private UserDto approver;

}
