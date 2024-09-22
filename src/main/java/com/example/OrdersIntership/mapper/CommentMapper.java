package com.example.OrdersIntership.mapper;

import com.example.OrdersIntership.dto.CommentDto;
import com.example.OrdersIntership.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto toCommentDto(Comment comment);

    Comment toComment(CommentDto commentDto);

}
