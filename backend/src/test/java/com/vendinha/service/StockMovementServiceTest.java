package com.vendinha.service;

import com.vendinha.dto.StockMovementDTO;
import com.vendinha.model.StockMovement;
import com.vendinha.repository.StockMovementRepository;
import com.vendinha.util.mapper.StockMovementMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockMovementServiceTest {

    @Mock
    private StockMovementRepository stockMovementRepository;

    @Mock
    private StockMovementMapper stockMovementMapper;

    @InjectMocks
    private StockMovementService stockMovementService;

    private StockMovement stockMovement;
    private StockMovementDTO stockMovementDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        stockMovement = new StockMovement();
        stockMovement.setId(1L);
        stockMovement.setType("ENTRADA");
        stockMovement.setMovementDate(LocalDateTime.now());

        stockMovementDTO = new StockMovementDTO();
        stockMovementDTO.setId(1L);
        stockMovementDTO.setType("ENTRADA");
        stockMovementDTO.setMovementDate(LocalDateTime.now());
    }

    @Test
    void testGetAllStockMovements() {
        List<StockMovement> stockMovements = Arrays.asList(stockMovement);
        List<StockMovementDTO> stockMovementDTOs = Arrays.asList(stockMovementDTO);

        when(stockMovementRepository.findAll()).thenReturn(stockMovements);
        when(stockMovementMapper.toDto(stockMovement)).thenReturn(stockMovementDTO);

        List<StockMovementDTO> result = stockMovementService.getAllStockMovements();

        assertEquals(stockMovementDTOs, result);
        verify(stockMovementRepository, times(1)).findAll();
        verify(stockMovementMapper, times(1)).toDto(stockMovement);
    }

    @Test
    void testGetStockMovementById() {
        when(stockMovementRepository.findById(1L)).thenReturn(Optional.of(stockMovement));
        when(stockMovementMapper.toDto(stockMovement)).thenReturn(stockMovementDTO);

        StockMovementDTO result = stockMovementService.getStockMovementById(1L);

        assertEquals(stockMovementDTO, result);
        verify(stockMovementRepository, times(1)).findById(1L);
        verify(stockMovementMapper, times(1)).toDto(stockMovement);
    }

    @Test
    void testCreateStockMovement() {
        when(stockMovementMapper.toEntity(stockMovementDTO)).thenReturn(stockMovement);
        when(stockMovementRepository.save(stockMovement)).thenReturn(stockMovement);
        when(stockMovementMapper.toDto(stockMovement)).thenReturn(stockMovementDTO);

        StockMovementDTO result = stockMovementService.createStockMovement(stockMovementDTO);

        assertEquals(stockMovementDTO, result);
        verify(stockMovementMapper, times(1)).toEntity(stockMovementDTO);
        verify(stockMovementRepository, times(1)).save(stockMovement);
        verify(stockMovementMapper, times(1)).toDto(stockMovement);
    }

    @Test
    void testUpdateStockMovement() {
        when(stockMovementRepository.findById(1L)).thenReturn(Optional.of(stockMovement));
        when(stockMovementRepository.save(stockMovement)).thenReturn(stockMovement);
        when(stockMovementMapper.toDto(stockMovement)).thenReturn(stockMovementDTO);

        StockMovementDTO result = stockMovementService.updateStockMovement(1L, stockMovementDTO);

        assertEquals(stockMovementDTO, result);
        verify(stockMovementRepository, times(1)).findById(1L);
        verify(stockMovementRepository, times(1)).save(stockMovement);
        verify(stockMovementMapper, times(1)).toDto(stockMovement);
    }

    @Test
    void testDeleteStockMovement() {
        when(stockMovementRepository.existsById(1L)).thenReturn(true);

        stockMovementService.deleteStockMovement(1L);

        verify(stockMovementRepository, times(1)).existsById(1L);
        verify(stockMovementRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetStockMovementById_NotFound() {
        when(stockMovementRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            stockMovementService.getStockMovementById(1L);
        });

        assertEquals("Movimento de estoque não encontrado!", exception.getMessage());
        verify(stockMovementRepository, times(1)).findById(1L);
    }
}