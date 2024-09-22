package com.example.OrdersIntership.service;

import com.example.OrdersIntership.dto.FeedbackDto;
import com.example.OrdersIntership.entity.Feedback;
import com.example.OrdersIntership.mapper.FeedbackMapper;
import com.example.OrdersIntership.repository.FeedbackRepository;
import com.example.OrdersIntership.service.interfaces.FeedbackServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackServiceInterface {

    @Autowired
    private final FeedbackRepository feedbackRepository;

    @Autowired
    private final FeedbackMapper feedbackMapper;

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackDto> findAllFeedbacks() {
        return feedbackRepository.getAllFeedbacks()
                .stream()
                .map(feedbackMapper::toFeedbackDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public FeedbackDto findFeedbackById(Long id) {
        return feedbackRepository.getFeedbackById(id)
                .map(feedbackMapper::toFeedbackDto)
                .orElseThrow(() -> new NoSuchElementException("Feedback not found"));
    }

    @Override
    @Transactional
    public void createFeedback(FeedbackDto entity) {
        Feedback feedback = feedbackMapper.toFeedback(entity);
        feedbackRepository.saveFeedback(feedback.getUser().getId(), feedback.getRate(), feedback.getDate(), feedback.getText(), feedback.getTicket().getId());
    }

    @Override
    @Transactional
    public void updateFeedback(FeedbackDto entity) {
        Feedback feedback = feedbackMapper.toFeedback(entity);
        feedbackRepository.updateFeedback(feedback.getId(), feedback.getUser(), feedback.getRate(), feedback.getDate(), feedback.getText(), feedback.getTicket());
    }

    @Override
    @Transactional
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteFeedback(id);
    }
}
