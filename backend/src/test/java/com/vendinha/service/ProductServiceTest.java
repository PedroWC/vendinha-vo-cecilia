package com.vendinha.service;

import com.vendinha.dto.ProductDTO;
import com.vendinha.model.Product;
import com.vendinha.repository.ProductRepository;
import com.vendinha.util.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Dado que o repositório retorna uma lista de produtos mockados
        Product product1 = new Product(1L, "Produto A", "Descrição A", 10.0, 5);
        Product product2 = new Product(2L, "Produto B", "Descrição B", 20.0, 10);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Quando chamarmos o método getAllProducts
        List<ProductDTO> products = productService.getAllProducts();

        // Então esperamos que ele retorne os produtos corretos
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        Product product = new Product(1L, "Produto A", "Descrição A", 10.0, 5);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Produto A");
        productDTO.setDescription("Descrição A");
        productDTO.setPrice(10.0);
        productDTO.setQuantityInStock(5);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(productDTO);

        ProductDTO result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals("Produto A", result.getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductById_ProductNotFound() {
        // Simule que o produto não existe
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Verifique se uma exceção é lançada ao tentar buscar um produto inexistente
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(1L);
        });

        // Verifique se a mensagem de erro está correta
        assertEquals("Produto não encontrado!", exception.getMessage());

        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveProduct() {
        Product product = new Product(null, "Produto C", "Descrição C", 15.0, 7);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Produto C");
        productDTO.setDescription("Descrição C");
        productDTO.setPrice(15.0);
        productDTO.setQuantityInStock(7);

        // Configure o mock para converter de Product para ProductDTO e vice-versa
        when(productMapper.toDto(product)).thenReturn(productDTO);
        when(productMapper.toEntity(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        ProductDTO savedProduct = productService.saveProduct(productDTO);

        assertNotNull(savedProduct);
        assertEquals("Produto C", savedProduct.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testSaveProduct_InvalidProduct() {
        // Produto com nome vazio e preço negativo
        ProductDTO invalidProductDTO = new ProductDTO();
        invalidProductDTO.setName("");
        invalidProductDTO.setPrice(-10.0);

        // Verifique se uma exceção é lançada ao tentar salvar um produto inválido
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.saveProduct(invalidProductDTO);
        });

        assertEquals("Dados do produto inválidos!", exception.getMessage());
        verify(productRepository, never()).save(any(Product.class)); // Certifique-se de que o save não foi chamado
    }

    @Test
    void testDeleteProduct() {
        // Simule que o produto existe
        Product product = new Product(1L, "Produto A", "Descrição A", 10.0, 5);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Simule o comportamento de delete
        doNothing().when(productRepository).deleteById(1L);

        // Quando o método delete for chamado, verifique se ele foi executado corretamente
        productService.deleteProduct(1L);

        // Verifique se o método deleteById foi chamado corretamente
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProduct_ProductNotFound() {
        // Simule que o produto não existe
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Verifique se uma exceção é lançada ao tentar deletar um produto inexistente
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.deleteProduct(1L);
        });

        // Verifique se a mensagem de erro está correta
        assertEquals("Produto não encontrado!", exception.getMessage());

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, never()).deleteById(1L); // Certifique-se de que o delete não foi chamado
    }
}