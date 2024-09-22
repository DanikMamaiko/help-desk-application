package com.example.OrdersIntership.mapper;

import com.example.OrdersIntership.dto.AttachmentDto;
import com.example.OrdersIntership.entity.Attachment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    AttachmentDto toAttachmentDto(Attachment attachment);

    Attachment toAttachment(AttachmentDto attachmentDto);

}
