package com.example.OrdersIntership.service;

import com.example.OrdersIntership.dto.AttachmentDto;
import com.example.OrdersIntership.entity.Attachment;
import com.example.OrdersIntership.mapper.AttachmentMapper;
import com.example.OrdersIntership.repository.AttachmentRepository;
import com.example.OrdersIntership.service.interfaces.AttachmentServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentServiceInterface{

    @Autowired
    private final AttachmentRepository attachmentRepository;

    @Autowired
    private final AttachmentMapper attachmentMapper;

    @Override
    @Transactional(readOnly = true)
    public List<AttachmentDto> findAllAttachments() {
        return attachmentRepository.getAllAttachments()
                .stream()
                .map(attachmentMapper::toAttachmentDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AttachmentDto findAttachmentById(Long id) {
        return attachmentRepository.getAttachmentById(id)
                .map(attachmentMapper::toAttachmentDto)
                .orElseThrow(() -> new NoSuchElementException("Attachment not found"));
    }

    @Override
    @Transactional
    public void createAttachment(AttachmentDto entity) {
        Attachment attachment = attachmentMapper.toAttachment(entity);
        attachmentRepository.saveAttachment(attachment.getBlobb(),
                                            attachment.getTicket().getId(),
                                            attachment.getName());

        // if Native Query doesn't work
        // attachmentRepository.saveAndFlush(attachment);
    }

    @Override
    @Transactional
    public void updateAttachment(AttachmentDto entity) {
        Attachment attachment = attachmentMapper.toAttachment(entity);

        attachmentRepository.updateAttachment(attachment.getId(),
                attachment.getBlobb(),
                attachment.getTicket(),
                attachment.getName());

    }

    @Override
    @Transactional
    public void deleteAttachment(Long id) {
        attachmentRepository.deleteAttachment(id);
    }

}
