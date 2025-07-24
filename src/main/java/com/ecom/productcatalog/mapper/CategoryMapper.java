package com.ecom.productcatalog.mapper;

import com.ecom.productcatalog.dto.response.CategoryDto;
import com.ecom.productcatalog.model.Category;

public class CategoryMapper {
    public static CategoryDto toDto(Category category) {
        return new CategoryDto(category.getName());
    }
}
