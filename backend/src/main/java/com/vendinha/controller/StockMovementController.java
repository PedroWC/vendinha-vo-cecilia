package com.vendinha.controller;

import com.vendinha.dto.StockMovementDTO;
import com.vendinha.service.StockMovementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
@Tag(name = "Movimentos de Estoque", description = "Gerenciamento de movimentos de estoque")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    @Autowired
    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @Operation(summary = "Listar todos os movimentos de estoque", description = "Retorna uma lista com todos os movimentos de estoque cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimentos de estoque listados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<StockMovementDTO>> getAllStockMovements() {
        List<StockMovementDTO> stockMovements = stockMovementService.getAllStockMovements();
        return ResponseEntity.ok(stockMovements);
    }

    @Operation(summary = "Buscar movimento de estoque por ID", description = "Retorna um movimento de estoque específico com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimento de estoque encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Movimento de estoque não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StockMovementDTO> getStockMovementById(@PathVariable Long id) {
        StockMovementDTO stockMovement = stockMovementService.getStockMovementById(id);
        return ResponseEntity.ok(stockMovement);
    }

    @Operation(summary = "Criar novo movimento de estoque", description = "Cria um novo movimento de estoque.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimento de estoque criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação do movimento de estoque"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<StockMovementDTO> createStockMovement(@RequestBody StockMovementDTO stockMovementDTO) {
        StockMovementDTO createdStockMovement = stockMovementService.createStockMovement(stockMovementDTO);
        return ResponseEntity.ok(createdStockMovement);
    }

    @Operation(summary = "Atualizar movimento de estoque", description = "Atualiza um movimento de estoque existente com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimento de estoque atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Movimento de estoque não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização do movimento de estoque"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<StockMovementDTO> updateStockMovement(@PathVariable Long id, @RequestBody StockMovementDTO stockMovementDTO) {
        StockMovementDTO updatedStockMovement = stockMovementService.updateStockMovement(id, stockMovementDTO);
        return ResponseEntity.ok(updatedStockMovement);
    }

    @Operation(summary = "Deletar movimento de estoque", description = "Deleta um movimento de estoque existente com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movimento de estoque deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Movimento de estoque não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockMovement(@PathVariable Long id) {
        stockMovementService.deleteStockMovement(id);
        return ResponseEntity.noContent().build();
    }
}