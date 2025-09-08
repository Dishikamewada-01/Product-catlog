package com.ecom.productcatalog.mapper;

import com.ecom.productcatalog.dto.request.CategoryRequestDto;
import com.ecom.productcatalog.dto.response.CategoryDto;
import com.ecom.productcatalog.model.Category;

public class CategoryMapper {

    // Entity -> Response DTO
    public static CategoryDto toDto(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }

    // Request DTO -> Entity
    public static Category toEntity(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    // Update existing entity
    public static void updateEntity(CategoryRequestDto dto, Category category) {
        category.setName(dto.getName());
    }
}
