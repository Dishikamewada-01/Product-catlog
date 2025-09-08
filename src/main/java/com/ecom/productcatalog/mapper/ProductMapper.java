package com.ecom.productcatalog.mapper;

import com.ecom.productcatalog.dto.response.ProductDto;
import com.ecom.productcatalog.model.Product;

public class ProductMapper {
    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getName(),
                product.getDescription(),
                product.getImageUrl(),
                product.getPrice(),
                product.getCategory().getName()
        );
    }
}
