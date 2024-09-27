package com.vendinha.service;

import com.vendinha.dto.StockMovementDTO;
import com.vendinha.model.StockMovement;
import com.vendinha.repository.StockMovementRepository;
import com.vendinha.util.mapper.StockMovementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final StockMovementMapper stockMovementMapper;

    @Autowired
    public StockMovementService(StockMovementRepository stockMovementRepository, StockMovementMapper stockMovementMapper) {
        this.stockMovementRepository = stockMovementRepository;
        this.stockMovementMapper = stockMovementMapper;
    }

    public List<StockMovementDTO> getAllStockMovements() {
        return stockMovementRepository.findAll().stream()
                .map(stockMovementMapper::toDto)
                .collect(Collectors.toList());
    }

    public StockMovementDTO getStockMovementById(Long id) {
        StockMovement stockMovement = stockMovementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimento de estoque não encontrado!"));
        return stockMovementMapper.toDto(stockMovement);
    }

    public StockMovementDTO createStockMovement(StockMovementDTO stockMovementDTO) {
        StockMovement stockMovement = stockMovementMapper.toEntity(stockMovementDTO);
        StockMovement savedStockMovement = stockMovementRepository.save(stockMovement);
        return stockMovementMapper.toDto(savedStockMovement);
    }

    public StockMovementDTO updateStockMovement(Long id, StockMovementDTO stockMovementDTO) {
        Optional<StockMovement> stockMovementOptional = stockMovementRepository.findById(id);

        if (stockMovementOptional.isPresent()) {
            StockMovement stockMovement = stockMovementOptional.get();
            stockMovement.setType(stockMovementDTO.getType());
            stockMovement.setMovementDate(stockMovementDTO.getMovementDate());
            // Atualizar outros campos conforme necessário
            StockMovement updatedStockMovement = stockMovementRepository.save(stockMovement);
            return stockMovementMapper.toDto(updatedStockMovement);
        } else {
            throw new RuntimeException("Movimento de estoque não encontrado!");
        }
    }

    public void deleteStockMovement(Long id) {
        if (stockMovementRepository.existsById(id)) {
            stockMovementRepository.deleteById(id);
        } else {
            throw new RuntimeException("Movimento de estoque não encontrado!");
        }
    }
}