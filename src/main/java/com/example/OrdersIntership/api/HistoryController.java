package com.example.OrdersIntership.api;

import com.example.OrdersIntership.dto.HistoryDto;
import com.example.OrdersIntership.service.interfaces.HistoryServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryServiceInterface historyService;

    @GetMapping("/history")
    public ResponseEntity<List<HistoryDto>> allHistories(){

        return new ResponseEntity<>(historyService.findAllHistories(), HttpStatus.OK);
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<HistoryDto> getHistoryById(@PathVariable("id") Long id){

        return new ResponseEntity<>(historyService.findHistoryById(id), HttpStatus.OK);
    }

    @PostMapping("/history")
    public ResponseEntity<HttpStatus> saveHistory(@RequestBody HistoryDto history){

        historyService.createHistory(history);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/history")
    public ResponseEntity<HttpStatus> updateHistory(@RequestBody HistoryDto history){

        historyService.updateHistory(history);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/history/{id}")
    public ResponseEntity<HttpStatus> deleteHistory(@PathVariable("id") Long id){

        historyService.deleteHistory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
