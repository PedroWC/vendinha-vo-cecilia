package com.vendinha.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StockMovementDTO {

    private Long id;
    private ProductDTO product; // Referência ao DTO do produto
    private Integer quantity;
    private LocalDateTime movementDate;
    private String type; // Ex.: "entrada", "saída"
}