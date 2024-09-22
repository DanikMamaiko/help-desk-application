package com.example.OrdersIntership.services;

import com.example.OrdersIntership.dto.FeedbackDto;
import com.example.OrdersIntership.dto.TicketDto;
import com.example.OrdersIntership.dto.UserDto;
import com.example.OrdersIntership.entity.Feedback;
import com.example.OrdersIntership.entity.Ticket;
import com.example.OrdersIntership.entity.User;
import com.example.OrdersIntership.mapper.FeedbackMapper;
import com.example.OrdersIntership.repository.FeedbackRepository;
import com.example.OrdersIntership.service.FeedbackServiceImpl;
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
class FeedbackServiceImplTest {

    @InjectMocks
    FeedbackServiceImpl feedbackService;

    @Mock
    FeedbackRepository feedbackRepository;

    @Mock
    FeedbackMapper feedbackMapper;

    Feedback feedback1, feedback2;
    FeedbackDto feedbackDto1, feedbackDto2;

    @BeforeEach
    void setUp(){

        LocalDate date = LocalDate.now();
        User user = User.builder().id(1L).build();
        Ticket ticket = Ticket.builder().id(1L).build();
        UserDto userDto = UserDto.builder().id(1L).build();
        TicketDto ticketDto = TicketDto.builder().id(1L).build();

        feedbackDto1 = new FeedbackDto(1L, userDto, "5", date, "feedbackOne", ticketDto);
        feedbackDto2 = new FeedbackDto(2L, userDto, "5", date, "feedbackTwo", ticketDto);

        feedback1 = Feedback.builder()
                .id(1L)
                .user(user)
                .rate("5")
                .date(date)
                .text("feedbackOne")
                .ticket(ticket)
                .build();

        feedback2 = Feedback.builder()
                .id(2L)
                .user(user)
                .rate("5")
                .date(date)
                .text("feedbackTwo")
                .ticket(ticket)
                .build();



    }

    @Test
    void findAllFeedbacks() {

        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList.add(feedback1);
        feedbackList.add(feedback2);

        when(feedbackRepository.getAllFeedbacks()).thenReturn(feedbackList);
        when(feedbackMapper.toFeedbackDto(feedback1)).thenReturn(feedbackDto1);
        when(feedbackMapper.toFeedbackDto(feedback2)).thenReturn(feedbackDto2);

        List <FeedbackDto> feedbackDtoList = feedbackService.findAllFeedbacks();

        assertThat(feedbackDtoList, notNullValue());
        assertThat(feedbackDtoList, equalTo(List.of(feedbackDto1, feedbackDto2)));
        assertThat(feedbackDtoList, hasSize(2));

        verify(feedbackRepository, times(1)).getAllFeedbacks();
        verify(feedbackMapper, times(1)).toFeedbackDto(feedback1);
        verify(feedbackMapper, times(1)).toFeedbackDto(feedback2);

    }

    @Test
    void findFeedbackById() {

        Long id = 1L;

        when(feedbackRepository.getFeedbackById(id)).thenReturn(Optional.of(feedback1));
        when(feedbackMapper.toFeedbackDto(feedback1)).thenReturn(feedbackDto1);

        FeedbackDto feedbackDto = feedbackService.findFeedbackById(id);

        assertThat(feedbackDto, notNullValue());
        assertThat(feedbackDto, equalTo(feedbackDto1));

        Throwable exception = assertThrows(NoSuchElementException.class, () -> feedbackService.findFeedbackById(2L));
        assertThat(exception, instanceOf(NoSuchElementException.class));

        verify(feedbackRepository, times(1)).getFeedbackById(id);
        verify(feedbackMapper, times(1)).toFeedbackDto(feedback1);

    }

    @Test
    void createFeedback() {

        when(feedbackMapper.toFeedback(feedbackDto1)).thenReturn(feedback1);

        feedbackService.createFeedback(feedbackDto1);

        verify(feedbackRepository, times(1))
                .saveFeedback(feedback1.getUser().getId(), feedback1.getRate(), feedback1.getDate(), feedback1.getText(), feedback1.getTicket().getId());

    }

    @Test
    void updateFeedback() {

        when(feedbackMapper.toFeedback(feedbackDto1)).thenReturn(feedback1);

        feedbackService.updateFeedback(feedbackDto1);

        verify(feedbackRepository, times(1))
                .updateFeedback(feedback1.getId(), feedback1.getUser(), feedback1.getRate(), feedback1.getDate(), feedback1.getText(), feedback1.getTicket());

    }

    @Test
    void deleteFeedback() {

        feedbackService.deleteFeedback(1L);

        verify(feedbackRepository, times(1)).deleteFeedback(1L);

    }
}