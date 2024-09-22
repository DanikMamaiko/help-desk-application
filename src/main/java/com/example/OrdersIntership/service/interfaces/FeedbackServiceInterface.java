package com.example.OrdersIntership.service.interfaces;

import com.example.OrdersIntership.dto.FeedbackDto;

import java.util.List;

public interface FeedbackServiceInterface {

    List<FeedbackDto> findAllFeedbacks();

    FeedbackDto findFeedbackById(Long id);

    void createFeedback(FeedbackDto entity);

    void updateFeedback(FeedbackDto entity);

    void deleteFeedback(Long id);

}
