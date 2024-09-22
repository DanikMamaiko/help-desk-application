package com.example.OrdersIntership.mapper;

import com.example.OrdersIntership.dto.HistoryDto;
import com.example.OrdersIntership.entity.History;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoryMapper {
    HistoryDto toHistoryDto(History history);

    History toHistory(HistoryDto historyDto);

}
