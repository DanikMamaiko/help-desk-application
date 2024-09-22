package com.example.OrdersIntership.service.interfaces;

import com.example.OrdersIntership.dto.CommentDto;

import java.util.List;

public interface CommentServiceInterface{

    List<CommentDto> findAllComments();

    CommentDto findCommentById(Long id);

    void createComment(CommentDto entity);

    void updateComment(CommentDto entity);

    void deleteComment(Long id);

}
