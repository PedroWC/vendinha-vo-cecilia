package com.vendinha.service;

import com.vendinha.dto.CategoryDTO;
import com.vendinha.model.Product;
import com.vendinha.util.mapper.CategoryMapper;
import com.vendinha.model.Category;
import com.vendinha.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    // Obtém todas as categorias e as converte para DTOs
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)  // Usando o método toDto do mapper
                .collect(Collectors.toList());
    }

    // Obtém uma categoria por ID e a converte para DTO
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
        return categoryMapper.toDto(category);  // Usando o método toDto
    }

    // Cria uma nova categoria a partir de um DTO
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        if (categoryDTO.getName() == null || categoryDTO.getName().isEmpty()) {
            throw new RuntimeException("Dados inválidos para categoria!");
        }
        Category category = categoryMapper.toEntity(categoryDTO);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    // Atualiza uma categoria existente com os dados do DTO
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setName(categoryDTO.getName());  // Atualiza os dados da entidade
            Category updatedCategory = categoryRepository.save(category);
            return categoryMapper.toDto(updatedCategory);  // Converte a entidade atualizada para DTO
        } else {
            throw new RuntimeException("Categoria não encontrada!");
        }
    }

    // Exclui uma categoria pelo ID
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
        categoryRepository.deleteById(id);
    }
}