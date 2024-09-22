package com.example.OrdersIntership.mapper;

import com.example.OrdersIntership.dto.FeedbackDto;
import com.example.OrdersIntership.entity.Feedback;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    FeedbackDto toFeedbackDto(Feedback feedback);

    Feedback toFeedback(FeedbackDto feedbackDto);

}
