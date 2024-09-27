package com.vendinha.util.mapper;

import com.vendinha.dto.StockMovementDTO;
import com.vendinha.model.StockMovement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {
    StockMovementDTO toDto(StockMovement stockMovement);
    StockMovement toEntity(StockMovementDTO stockMovementDTO);
}