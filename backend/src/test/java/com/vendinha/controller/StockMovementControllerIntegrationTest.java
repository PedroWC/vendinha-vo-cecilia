package com.vendinha.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendinha.dto.StockMovementDTO;
import com.vendinha.service.StockMovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StockMovementController.class)
class StockMovementControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockMovementService stockMovementService;

    @Autowired
    private ObjectMapper objectMapper;

    private StockMovementDTO stockMovementDTO;

    @BeforeEach
    void setUp() {
        stockMovementDTO = new StockMovementDTO();
        stockMovementDTO.setId(1L);
        stockMovementDTO.setType("Entrada");
        stockMovementDTO.setMovementDate(LocalDateTime.parse("2024-09-26")); // Coloque a data como String ou outro formato que você usa
        // Defina outros campos conforme necessário
    }

    @Test
    void testGetAllStockMovements() throws Exception {
        List<StockMovementDTO> stockMovements = Collections.singletonList(stockMovementDTO);

        Mockito.when(stockMovementService.getAllStockMovements()).thenReturn(stockMovements);

        mockMvc.perform(get("/api/stock-movements")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].type", is("Entrada")));
    }

    @Test
    void testGetStockMovementById() throws Exception {
        Mockito.when(stockMovementService.getStockMovementById(1L)).thenReturn(stockMovementDTO);

        mockMvc.perform(get("/api/stock-movements/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.type", is("Entrada")));
    }

    @Test
    void testGetStockMovementById_NotFound() throws Exception {
        Mockito.when(stockMovementService.getStockMovementById(1L)).thenThrow(new RuntimeException("Movimento de estoque não encontrado"));

        mockMvc.perform(get("/api/stock-movements/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateStockMovement() throws Exception {
        Mockito.when(stockMovementService.createStockMovement(Mockito.any(StockMovementDTO.class)))
                .thenReturn(stockMovementDTO);

        mockMvc.perform(post("/api/stock-movements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stockMovementDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.type", is("Entrada")));
    }

    @Test
    void testUpdateStockMovement() throws Exception {
        Mockito.when(stockMovementService.updateStockMovement(Mockito.eq(1L), Mockito.any(StockMovementDTO.class)))
                .thenReturn(stockMovementDTO);

        mockMvc.perform(put("/api/stock-movements/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stockMovementDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.type", is("Entrada")));
    }

    @Test
    void testDeleteStockMovement() throws Exception {
        mockMvc.perform(delete("/api/stock-movements/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteStockMovement_NotFound() throws Exception {
        Mockito.doThrow(new RuntimeException("Movimento de estoque não encontrado")).when(stockMovementService).deleteStockMovement(1L);

        mockMvc.perform(delete("/api/stock-movements/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}