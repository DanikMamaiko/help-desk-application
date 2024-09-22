package com.example.OrdersIntership.api;

import com.example.OrdersIntership.dto.FeedbackDto;
import com.example.OrdersIntership.service.FeedbackServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackServiceImpl feedbackService;

    @GetMapping("/feedback")
    public ResponseEntity<List<FeedbackDto>> allFeedbacks(){

        return new ResponseEntity<>(feedbackService.findAllFeedbacks(), HttpStatus.OK);
    }

    @GetMapping("/feedback/{id}")
    public ResponseEntity<FeedbackDto> getFeedbackById(@PathVariable("id") Long id){

        return new ResponseEntity<>(feedbackService.findFeedbackById(id), HttpStatus.OK);
    }

    @PostMapping("/feedback")
    public ResponseEntity<HttpStatus> saveFeedback(@RequestBody FeedbackDto feedback){

        feedbackService.createFeedback(feedback);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/feedback")
    public ResponseEntity<HttpStatus> updateFeedback(@RequestBody FeedbackDto feedback){

        feedbackService.updateFeedback(feedback);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/feedback/{id}")
    public ResponseEntity<HttpStatus> deleteFeedback(@PathVariable("id") Long id){

        feedbackService.deleteFeedback(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
