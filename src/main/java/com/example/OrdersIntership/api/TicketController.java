package com.example.OrdersIntership.api;

import com.example.OrdersIntership.dto.TicketDto;
import com.example.OrdersIntership.service.TicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketServiceImpl ticketService;

    @GetMapping("/ticket")
    public ResponseEntity<List<TicketDto>> allTickets(){

        return new ResponseEntity<>(ticketService.findAllTickets(), HttpStatus.OK);
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable("id") Long id){

        return new ResponseEntity<>(ticketService.findTicketById(id), HttpStatus.OK);
    }

    @PostMapping("/ticket")
    public ResponseEntity<HttpStatus> saveTicket(@RequestBody TicketDto ticket){

        ticketService.createTicket(ticket);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/ticket")
    public ResponseEntity<HttpStatus> updateTicket(@RequestBody TicketDto ticket){

        ticketService.updateTicket(ticket);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable("id") Long id){

        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
