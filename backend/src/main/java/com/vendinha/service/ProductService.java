package com.vendinha.service;

import com.vendinha.dto.ProductDTO;
import com.vendinha.model.Product;
import com.vendinha.repository.ProductRepository;
import com.vendinha.util.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        return productMapper.toDto(product);
    }

    public ProductDTO saveProduct(String name,
                                  String description,
                                  Double price,
                                  Integer quantityInStock,
                                  MultipartFile image) throws IOException {
        if (name == null || name.isEmpty() || price <= 0) {
            throw new IllegalArgumentException("Dados do produto inválidos!");
        }
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantityInStock(quantityInStock);

        // Armazena a imagem como BLOB no banco de dados
        if (image != null && !image.isEmpty()) {
            long maxSize = 1024 * 1024 * 16; // Limite de 16MB para MEDIUMBLOB, por exemplo
            if (image.getSize() > maxSize) {
                throw new IllegalArgumentException("Imagem muito grande! O limite é 16MB.");
            }
            product.setImage(image.getBytes());
        }

        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    public ProductDTO updateProduct(Long id,
                                    String name,
                                    String description,
                                    Double price,
                                    Integer quantityInStock,
                                    MultipartFile image) throws IOException {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setQuantityInStock(quantityInStock);

            // Atualiza a imagem
            if (image != null && !image.isEmpty()) {
                product.setImage(image.getBytes());
            }

            Product updatedProduct = productRepository.save(product);
            return productMapper.toDto(updatedProduct);
        } else {
            throw new RuntimeException("Produto não encontrado!");
        }
    }

    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        productRepository.deleteById(id);
    }
}
