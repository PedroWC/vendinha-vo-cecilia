package com.vendinha.service;

import com.vendinha.dto.CategoryDTO;
import com.vendinha.model.Category;
import com.vendinha.repository.CategoryRepository;
import com.vendinha.util.mapper.CategoryMapper;
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

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicialização dos mocks
    }

    @Test
    void testGetAllCategories() {
        // Simulação dos dados
        Category category1 = new Category(1L, "Bebidas");
        Category category2 = new Category(2L, "Alimentos");

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        // Execução do método
        List<CategoryDTO> categories = categoryService.getAllCategories();

        // Verificação dos resultados
        assertEquals(2, categories.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testGetCategoryById() {
        // Criação da categoria e DTO
        Category category = new Category(1L, "Categoria A");
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Categoria A");

        // Configuração dos mocks
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(categoryDTO);

        // Execução do método
        CategoryDTO result = categoryService.getCategoryById(1L);

        // Verificação do resultado
        assertNotNull(result);
        assertEquals("Categoria A", result.getName());
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCategoryByIdNotFound() {
        // Simulação de categoria não encontrada
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        // Execução e verificação da exceção
        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.getCategoryById(1L);
        });

        assertEquals("Categoria não encontrada!", exception.getMessage());
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveCategory() {
        // Criação da categoria e DTO simulados
        Category category = new Category(1L, "Categoria A");
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Categoria A");

        // Configuração dos mocks
        when(categoryMapper.toEntity(categoryDTO)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(categoryDTO);

        // Execução do método
        CategoryDTO savedCategory = categoryService.saveCategory(categoryDTO);

        // Verificação do resultado
        assertNotNull(savedCategory);
        assertEquals("Categoria A", savedCategory.getName());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void testSaveCategoryInvalid() {
        // Criação de DTO inválido (nome ausente)
        CategoryDTO invalidCategoryDTO = new CategoryDTO(); // Nome está ausente

        // Execução e verificação da exceção
        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.saveCategory(invalidCategoryDTO);
        });

        assertEquals("Dados inválidos para categoria!", exception.getMessage());
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    void testDeleteCategory() {
        // Simulação de categoria existente
        Category category = new Category(1L, "Categoria A");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).deleteById(1L);

        // Execução do método
        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCategoryNotFound() {
        // Simulação de categoria não encontrada
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        // Execução e verificação da exceção
        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.deleteCategory(1L);
        });

        assertEquals("Categoria não encontrada!", exception.getMessage());
        verify(categoryRepository, never()).deleteById(1L);
    }
}