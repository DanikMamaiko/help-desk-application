package com.example.OrdersIntership.services;

import com.example.OrdersIntership.dto.CategoryDto;
import com.example.OrdersIntership.dto.TicketDto;
import com.example.OrdersIntership.dto.UserDto;
import com.example.OrdersIntership.entity.Category;
import com.example.OrdersIntership.entity.Ticket;
import com.example.OrdersIntership.entity.User;
import com.example.OrdersIntership.entity.StateEnum;
import com.example.OrdersIntership.entity.UrgencyEnum;
import com.example.OrdersIntership.mapper.TicketMapper;
import com.example.OrdersIntership.repository.TicketRepository;
import com.example.OrdersIntership.service.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @InjectMocks
    TicketServiceImpl ticketService;

    @Mock
    TicketRepository ticketRepository;

    @Mock
    TicketMapper ticketMapper;

    Ticket ticket1, ticket2;
    TicketDto ticketDto1, ticketDto2;

    @BeforeEach
    void setUp(){

        LocalDate date = LocalDate.now();
        UserDto userDto = UserDto.builder().id(1L).build();
        User user = User.builder().id(1L).build();
        CategoryDto categoryDto = CategoryDto.builder().id(1L).build();
        Category category = Category.builder().id(1L).build();

        ticketDto1 = new TicketDto(1L, "nameOne", "descriptionOne", date, date, userDto, userDto,
                                    StateEnum.APPROVED, categoryDto, UrgencyEnum.AVERAGE, userDto);

        ticketDto2 = new TicketDto(2L, "nameTwo", "descriptionTwo", date, date, userDto, userDto,
                StateEnum.APPROVED, categoryDto, UrgencyEnum.AVERAGE, userDto);

        ticket1 = Ticket.builder()
                .id(1L)
                .name("nameOne")
                .description("descriptionOne")
                .createdOn(date)
                .desiredResolutionDate(date)
                .assignee(user)
                .owner(user)
                .state(StateEnum.APPROVED)
                .category(category)
                .urgency(UrgencyEnum.AVERAGE)
                .approver(user)
                .build();

        ticket2 = Ticket.builder()
                .id(2L)
                .name("nameTwo")
                .description("descriptionTwo")
                .createdOn(date)
                .desiredResolutionDate(date)
                .assignee(user)
                .owner(user)
                .state(StateEnum.APPROVED)
                .category(category)
                .urgency(UrgencyEnum.AVERAGE)
                .approver(user)
                .build();

    }

    @Test
    void findAllTickets() {

        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);

        when(ticketRepository.getAllTickets()).thenReturn(ticketList);
        when(ticketMapper.toTicketDto(ticket1)).thenReturn(ticketDto1);
        when(ticketMapper.toTicketDto(ticket2)).thenReturn(ticketDto2);

        List <TicketDto> ticketDtoList = ticketService.findAllTickets();

        assertThat(ticketDtoList, notNullValue());
        assertThat(ticketDtoList, equalTo(List.of(ticketDto1, ticketDto2)));
        assertThat(ticketDtoList, hasSize(2));

        verify(ticketRepository, times(1)).getAllTickets();
        verify(ticketMapper, times(1)).toTicketDto(ticket1);
        verify(ticketMapper, times(1)).toTicketDto(ticket2);

    }

    @Test
    void findTicketById() {

        Long id = 1L;

        when(ticketRepository.getTicketById(id)).thenReturn(Optional.of(ticket1));
        when(ticketMapper.toTicketDto(ticket1)).thenReturn(ticketDto1);

        TicketDto ticketDto = ticketService.findTicketById(id);

        assertThat(ticketDto, notNullValue());
        assertThat(ticketDto, equalTo(ticketDto1));

        Throwable exception = assertThrows(NoSuchElementException.class, () -> ticketService.findTicketById(2L));
        assertThat(exception, instanceOf(NoSuchElementException.class));

        verify(ticketRepository, times(1)).getTicketById(id);
        verify(ticketMapper, times(1)).toTicketDto(ticket1);

    }

    @Test
    void createTicket() {

        when(ticketMapper.toTicket(ticketDto1)).thenReturn(ticket1);

        ticketService.createTicket(ticketDto1);

        verify(ticketRepository, times(1))
                .saveTicket(ticket1.getName(), ticket1.getDescription(), ticket1.getCreatedOn(), ticket1.getDesiredResolutionDate(),
                ticket1.getAssignee().getId(), ticket1.getOwner().getId(), ticket1.getState(), ticket1.getUrgency(), ticket1.getApprover().getId());


    }

    @Test
    void updateTicket() {

        when(ticketMapper.toTicket(ticketDto1)).thenReturn(ticket1);

        ticketService.updateTicket(ticketDto1);

        verify(ticketRepository, times(1))
                .updateTicket(ticket1.getId(), ticket1.getName(), ticket1.getDescription(), ticket1.getCreatedOn(), ticket1.getDesiredResolutionDate(),
                        ticket1.getAssignee(), ticket1.getOwner(), ticket1.getState(), ticket1.getUrgency(), ticket1.getApprover());

    }

    @Test
    void deleteTicket() {

        ticketService.deleteTicket(1L);

        verify(ticketRepository, times(1)).deleteTicket(1L);

    }
}