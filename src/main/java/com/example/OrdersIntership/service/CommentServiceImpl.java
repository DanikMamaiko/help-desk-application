package com.example.OrdersIntership.service;

import com.example.OrdersIntership.dto.CommentDto;
import com.example.OrdersIntership.entity.Comment;
import com.example.OrdersIntership.mapper.CommentMapper;
import com.example.OrdersIntership.repository.CommentRepository;
import com.example.OrdersIntership.service.interfaces.CommentServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentServiceInterface {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final CommentMapper commentMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> findAllComments() {
        return commentRepository.getAllComments()
                .stream()
                .map(commentMapper::toCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto findCommentById(Long id) {
        return commentRepository.getCommentById(id)
                .map(commentMapper::toCommentDto)
                .orElseThrow(() -> new NoSuchElementException("Comment not found"));
    }

    @Override
    @Transactional
    public void createComment(CommentDto entity) {
        Comment comment = commentMapper.toComment(entity);
        commentRepository.saveComment(comment.getUser().getId(), comment.getText(), comment.getDate(), comment.getTicket().getId());
    }

    @Override
    @Transactional
    public void updateComment(CommentDto entity) {
        Comment comment = commentMapper.toComment(entity);
        commentRepository.updateComment(comment.getId(), comment.getUser(), comment.getText(), comment.getDate(), comment.getTicket());
    }

    @Override
    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteComment(id);
    }
}
