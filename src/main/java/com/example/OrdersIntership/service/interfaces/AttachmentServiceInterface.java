package com.example.OrdersIntership.service.interfaces;

import com.example.OrdersIntership.dto.AttachmentDto;

import java.util.List;

public interface AttachmentServiceInterface{

    List<AttachmentDto> findAllAttachments();

    AttachmentDto findAttachmentById(Long id);

    void createAttachment(AttachmentDto entity);

    void updateAttachment(AttachmentDto entity);

    void deleteAttachment(Long id);

}
