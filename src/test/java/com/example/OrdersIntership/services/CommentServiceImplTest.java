package com.example.OrdersIntership.services;

import com.example.OrdersIntership.dto.CommentDto;
import com.example.OrdersIntership.dto.TicketDto;
import com.example.OrdersIntership.dto.UserDto;
import com.example.OrdersIntership.entity.Comment;
import com.example.OrdersIntership.entity.Ticket;
import com.example.OrdersIntership.entity.User;
import com.example.OrdersIntership.mapper.CommentMapper;
import com.example.OrdersIntership.repository.CommentRepository;
import com.example.OrdersIntership.service.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThrows;


@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @InjectMocks
    CommentServiceImpl commentService;

    @Mock
    CommentRepository commentRepository;

    @Mock
    CommentMapper commentMapper;

    CommentDto commentDto1, commentDto2;
    Comment comment1, comment2;

    @BeforeEach
    void setUp(){

        LocalDate date = LocalDate.now();

        User user = User.builder().id(1L).build();
        Ticket ticket = Ticket.builder().id(1L).build();
        UserDto userDto = UserDto.builder().id(1L).build();
        TicketDto ticketDto = TicketDto.builder().id(1L).build();

        commentDto1 = new CommentDto(1L, userDto, "textOne", date, ticketDto);
        commentDto2= new CommentDto(2L, userDto, "textTwo", date, ticketDto);

        comment1 = Comment.builder()
                .id(1L)
                .user(user)
                .text("textOne")
                .date(date)
                .ticket(ticket)
                .build();

        comment2 = Comment.builder()
                .id(2L)
                .user(user)
                .text("textTwo")
                .date(date)
                .ticket(ticket)
                .build();

    }

    @Test
    void findAllComments() {

        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment1);
        commentList.add(comment2);

        Mockito.when(commentRepository.getAllComments()).thenReturn(commentList);
        Mockito.when(commentMapper.toCommentDto(comment1)).thenReturn(commentDto1);
        Mockito.when(commentMapper.toCommentDto(comment2)).thenReturn(commentDto2);

        List <CommentDto> commentDtoList = commentService.findAllComments();

        assertThat(commentDtoList, notNullValue());
        assertThat(commentDtoList, equalTo(List.of(commentDto1, commentDto2)));
        assertThat(commentDtoList, hasSize(2));

        Mockito.verify(commentRepository, Mockito.times(1)).getAllComments();
        Mockito.verify(commentMapper, Mockito.times(1)).toCommentDto(comment1);
        Mockito.verify(commentMapper, Mockito.times(1)).toCommentDto(comment2);


    }

    @Test
    void findCommentById() {

        Long id = 1L;

        Mockito.when(commentRepository.getCommentById(id)).thenReturn(Optional.of(comment1));
        Mockito.when(commentMapper.toCommentDto(comment1)).thenReturn(commentDto1);

        CommentDto commentDto = commentService.findCommentById(id);

        assertThat(commentDto, notNullValue());
        assertThat(commentDto, equalTo(commentDto1));

        Throwable exception = assertThrows(NoSuchElementException.class, () -> commentService.findCommentById(2L));
        assertThat(exception, instanceOf(NoSuchElementException.class));

        Mockito.verify(commentRepository, Mockito.times(1)).getCommentById(id);
        Mockito.verify(commentMapper, Mockito.times(1)).toCommentDto(comment1);

    }

    @Test
    void createComment() {

        Mockito.when(commentMapper.toComment(commentDto1)).thenReturn(comment1);

        commentService.createComment(commentDto1);

        Mockito.verify(commentRepository, Mockito.times(1))
                .saveComment(comment1.getUser().getId(), comment1.getText(), comment1.getDate(), comment1.getTicket().getId());

    }

    @Test
    void updateComment() {

        Mockito.when(commentMapper.toComment(commentDto1)).thenReturn(comment1);

        commentService.updateComment(commentDto1);

        Mockito.verify(commentRepository, Mockito.times(1))
                .updateComment(comment1.getId(), comment1.getUser(), comment1.getText(), comment1.getDate(), comment1.getTicket());

    }

    @Test
    void deleteComment() {

        commentService.deleteComment(1L);

        Mockito.verify(commentRepository, Mockito.times(1)).deleteComment(1L);

    }
}