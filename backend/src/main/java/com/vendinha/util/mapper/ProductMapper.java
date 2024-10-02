package com.vendinha.util.mapper;

import com.vendinha.dto.ProductDTO;
import com.vendinha.model.Product;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ProductMapper {

    public ProductDTO toDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantityInStock(product.getQuantityInStock());

        // Converte o BLOB da imagem para Base64
        if (product.getImage() != null) {
            productDTO.setImageBase64(Base64.getEncoder().encodeToString(product.getImage()));
        }
        return productDTO;
    }

    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantityInStock(productDTO.getQuantityInStock());

        return product;
    }
}
