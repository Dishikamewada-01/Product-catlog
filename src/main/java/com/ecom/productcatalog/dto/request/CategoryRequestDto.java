package com.ecom.productcatalog.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CategoryRequestDto {

    @NotBlank(message = "Category name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
