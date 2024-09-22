package com.example.OrdersIntership.mapper;

import com.example.OrdersIntership.dto.TicketDto;
import com.example.OrdersIntership.entity.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketDto toTicketDto(Ticket ticket);

    Ticket toTicket(TicketDto ticketDto);

}
