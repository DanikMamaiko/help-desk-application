package com.example.OrdersIntership.service;

import com.example.OrdersIntership.dto.HistoryDto;
import com.example.OrdersIntership.entity.History;
import com.example.OrdersIntership.mapper.HistoryMapper;
import com.example.OrdersIntership.repository.HistoryRepository;
import com.example.OrdersIntership.service.interfaces.HistoryServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryServiceInterface {

    @Autowired
    private final HistoryRepository historyRepository;

    @Autowired
    private final HistoryMapper historyMapper;

    @Override
    @Transactional(readOnly = true)
    public List<HistoryDto> findAllHistories() {
        return historyRepository.getAllHistories()
                .stream()
                .map(historyMapper::toHistoryDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public HistoryDto findHistoryById(Long id) {
        return historyRepository.getHistoryById(id)
                .map(historyMapper::toHistoryDto)
                .orElseThrow(() -> new NoSuchElementException("History not found"));
    }

    @Override
    @Transactional
    public void createHistory(HistoryDto entity) {
        History history = historyMapper.toHistory(entity);
        historyRepository.saveHistory(history.getTicket().getId(), history.getDate(), history.getAction(), history.getUser().getId(), history.getDescription());
    }

    @Override
    @Transactional
    public void updateHistory(HistoryDto entity) {
        History history = historyMapper.toHistory(entity);
        historyRepository.updateHistory(history.getId(), history.getTicket(), history.getDate(), history.getAction(), history.getUser(), history.getDescription());
    }

    @Override
    @Transactional
    public void deleteHistory(Long id) {
        historyRepository.deleteHistory(id);
    }
}
