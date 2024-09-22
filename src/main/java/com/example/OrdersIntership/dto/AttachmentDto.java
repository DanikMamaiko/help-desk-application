package com.example.OrdersIntership.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDto {

    private Long id;
    private String blobb;
    private TicketDto ticket;
    private String name;

}
