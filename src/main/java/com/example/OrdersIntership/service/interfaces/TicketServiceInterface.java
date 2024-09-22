package com.example.OrdersIntership.service.interfaces;

import com.example.OrdersIntership.dto.TicketDto;

import java.util.List;

public interface TicketServiceInterface {

    List<TicketDto> findAllTickets();

    TicketDto findTicketById(Long id);

    void createTicket(TicketDto entity);

    void updateTicket(TicketDto entity);

    void deleteTicket(Long id);

}
