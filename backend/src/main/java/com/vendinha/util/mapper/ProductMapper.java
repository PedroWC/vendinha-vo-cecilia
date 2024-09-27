package com.vendinha.util.mapper;

import com.vendinha.dto.ProductDTO;
import com.vendinha.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDTO);
}