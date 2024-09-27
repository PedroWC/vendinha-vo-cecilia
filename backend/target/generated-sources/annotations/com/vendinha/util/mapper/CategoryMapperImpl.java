package com.vendinha.util.mapper;

import com.vendinha.dto.CategoryDTO;
import com.vendinha.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-27T11:39:36-0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO toDto(Category stockMovement) {
        if ( stockMovement == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( stockMovement.getId() );
        categoryDTO.setName( stockMovement.getName() );

        return categoryDTO;
    }

    @Override
    public Category toEntity(CategoryDTO stockMovementDTO) {
        if ( stockMovementDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( stockMovementDTO.getId() );
        category.setName( stockMovementDTO.getName() );

        return category;
    }
}
