package com.ecom.productcatalog.service;

import com.ecom.productcatalog.dto.request.CategoryRequestDto;
import com.ecom.productcatalog.dto.response.CategoryDto;
import com.ecom.productcatalog.mapper.CategoryMapper;
import com.ecom.productcatalog.model.Category;
import com.ecom.productcatalog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Get all categories as DTOs
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> dtos = new ArrayList<>();
        for (Category c : categories) {
            dtos.add(CategoryMapper.toDto(c));
        }
        return dtos;
    }

    // Save new category
    public CategoryDto saveCategory(CategoryRequestDto dto) {
        Category category = CategoryMapper.toEntity(dto);
        Category saved = categoryRepository.save(category);
        return CategoryMapper.toDto(saved);
    }

    // Optional: Update existing category
    public CategoryDto updateCategory(Long id, CategoryRequestDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        CategoryMapper.updateEntity(dto, category);
        Category updated = categoryRepository.save(category);
        return CategoryMapper.toDto(updated);
    }
}
