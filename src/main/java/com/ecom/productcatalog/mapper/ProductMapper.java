package com.ecom.productcatalog.mapper;

import com.ecom.productcatalog.dto.request.ProductRequestDto;
import com.ecom.productcatalog.dto.response.ProductDto;
import com.ecom.productcatalog.model.Category;
import com.ecom.productcatalog.model.Product;

public class ProductMapper {

    // Entity -> DTO
    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            dto.setCategoryName(product.getCategory().getName());
        }
        return dto;
    }

    // Request DTO -> Entity
    public static Product toEntity(ProductRequestDto dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImageUrl(dto.getImageUrl());
        product.setCategory(category);
        return product;
    }

    // Update existing entity
    public static void updateEntity(ProductRequestDto dto, Product product, Category category) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImageUrl(dto.getImageUrl());
        product.setCategory(category);
    }
}
