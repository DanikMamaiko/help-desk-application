package com.example.OrdersIntership.service.interfaces;

import com.example.OrdersIntership.dto.HistoryDto;

import java.util.List;

public interface HistoryServiceInterface {

    List<HistoryDto> findAllHistories();

    HistoryDto findHistoryById(Long id);

    void createHistory(HistoryDto entity);

    void updateHistory(HistoryDto entity);

    void deleteHistory(Long id);

}
