package com.vendinha.util.mapper;

import com.vendinha.dto.ProductDTO;
import com.vendinha.dto.StockMovementDTO;
import com.vendinha.model.Product;
import com.vendinha.model.StockMovement;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-27T11:39:36-0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class StockMovementMapperImpl implements StockMovementMapper {

    @Override
    public StockMovementDTO toDto(StockMovement stockMovement) {
        if ( stockMovement == null ) {
            return null;
        }

        StockMovementDTO stockMovementDTO = new StockMovementDTO();

        stockMovementDTO.setId( stockMovement.getId() );
        stockMovementDTO.setProduct( productToProductDTO( stockMovement.getProduct() ) );
        stockMovementDTO.setQuantity( stockMovement.getQuantity() );
        stockMovementDTO.setMovementDate( stockMovement.getMovementDate() );
        stockMovementDTO.setType( stockMovement.getType() );

        return stockMovementDTO;
    }

    @Override
    public StockMovement toEntity(StockMovementDTO stockMovementDTO) {
        if ( stockMovementDTO == null ) {
            return null;
        }

        StockMovement stockMovement = new StockMovement();

        stockMovement.setId( stockMovementDTO.getId() );
        stockMovement.setProduct( productDTOToProduct( stockMovementDTO.getProduct() ) );
        stockMovement.setQuantity( stockMovementDTO.getQuantity() );
        stockMovement.setMovementDate( stockMovementDTO.getMovementDate() );
        stockMovement.setType( stockMovementDTO.getType() );

        return stockMovement;
    }

    protected ProductDTO productToProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setDescription( product.getDescription() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setQuantityInStock( product.getQuantityInStock() );

        return productDTO;
    }

    protected Product productDTOToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDTO.getId() );
        product.setName( productDTO.getName() );
        product.setDescription( productDTO.getDescription() );
        product.setPrice( productDTO.getPrice() );
        product.setQuantityInStock( productDTO.getQuantityInStock() );

        return product;
    }
}
