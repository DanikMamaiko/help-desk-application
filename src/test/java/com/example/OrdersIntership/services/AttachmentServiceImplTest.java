package com.example.OrdersIntership.services;


import com.example.OrdersIntership.dto.AttachmentDto;
import com.example.OrdersIntership.dto.TicketDto;
import com.example.OrdersIntership.entity.Attachment;
import com.example.OrdersIntership.entity.Ticket;
import com.example.OrdersIntership.mapper.AttachmentMapper;
import com.example.OrdersIntership.repository.AttachmentRepository;
import com.example.OrdersIntership.service.AttachmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AttachmentServiceImplTest {

    @InjectMocks
    private AttachmentServiceImpl attachmentService;

    @Mock
    private AttachmentRepository attachmentRepository;

    @Mock
    private AttachmentMapper attachmentMapper;

    Attachment attachment1, attachment2;
    AttachmentDto attachmentDto1, attachmentDto2;

    @BeforeEach
    public void setUp(){

        attachment1 = new Attachment(1L, "www", new Ticket(), "www");
        attachment2 = new Attachment(2L, "sss", new Ticket(), "sss");

        attachmentDto1 = new AttachmentDto(1L, "www", new TicketDto(), "www");
        attachmentDto2 = new AttachmentDto(2L, "sss", new TicketDto(), "sss");

    }

    @Test
    void findAllAllAttachmentsTest() {

        List<Attachment> mockAttachments = List.of(attachment1, attachment2);
        Mockito.when(attachmentRepository.getAllAttachments()).thenReturn(mockAttachments);

        Mockito.when(attachmentMapper.toAttachmentDto(attachment1)).thenReturn(attachmentDto1);
        Mockito.when(attachmentMapper.toAttachmentDto(attachment2)).thenReturn(attachmentDto2);

        List <AttachmentDto> listDtoService = attachmentService.findAllAttachments();

        assertThat(listDtoService, notNullValue());
        assertThat(listDtoService, equalTo(List.of(attachmentDto1, attachmentDto2)));
        assertThat(listDtoService, hasSize(2));

        assertThat(attachmentDto1, equalTo(listDtoService.get(0)));
        assertThat(attachmentDto2, equalTo(listDtoService.get(1)));

        Mockito.verify(attachmentRepository, times(1)).getAllAttachments();
        Mockito.verify(attachmentMapper, times(1)).toAttachmentDto(attachment1);
        Mockito.verify(attachmentMapper, times(1)).toAttachmentDto(attachment2);

    }

    @Test
    void findAttachmentByIdTest(){

        Long id = 1L;

        Mockito.when(attachmentRepository.getAttachmentById(id)).thenReturn(Optional.of(attachment1));
        Mockito.when(attachmentMapper.toAttachmentDto(attachment1)).thenReturn(attachmentDto1);

        AttachmentDto attachmentDtoFromService = attachmentService.findAttachmentById(id);

        assertThat(attachmentDtoFromService, equalTo(attachmentDto1));
        assertThat(attachmentDtoFromService, is(notNullValue()));

        Throwable exception = assertThrows(NoSuchElementException.class, () -> attachmentService.findAttachmentById(2L));
        assertThat(exception, instanceOf(NoSuchElementException.class));

        Mockito.verify(attachmentRepository, times(1)).getAttachmentById(id);
        Mockito.verify(attachmentMapper, times(1)).toAttachmentDto(attachment1);

    }

    @Test
    void createAttachmentTest(){

        when(attachmentMapper.toAttachment(attachmentDto1)).thenReturn(attachment1);

        attachmentService.createAttachment(attachmentDto1);

        Mockito.verify(attachmentRepository,
                Mockito.times(1)).saveAttachment(attachment1.getBlobb(), attachment1.getTicket().getId(), attachment1.getBlobb());

    }

    @Test
    void updateAttachmentTest(){

        when(attachmentMapper.toAttachment(attachmentDto1)).thenReturn(attachment1);

        attachmentService.updateAttachment(attachmentDto1);

        Mockito.verify(attachmentRepository,
                Mockito.times(1)).updateAttachment(attachment1.getId(), attachment1.getBlobb(), attachment1.getTicket(), attachment1.getBlobb());

    }

    @Test
    void deleteAttachmentTest(){

        attachmentService.deleteAttachment(1L);

        Mockito.verify(attachmentRepository, Mockito.times(1)).deleteAttachment(1L);

    }



}