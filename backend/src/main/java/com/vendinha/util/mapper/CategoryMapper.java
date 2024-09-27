package com.vendinha.util.mapper;

import com.vendinha.dto.CategoryDTO;
import com.vendinha.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDto(Category stockMovement);
    Category toEntity(CategoryDTO stockMovementDTO);
}