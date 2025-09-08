package com.ecom.productcatalog.service;

import com.ecom.productcatalog.dto.request.ProductRequestDto;
import com.ecom.productcatalog.dto.response.ProductDto;
import com.ecom.productcatalog.mapper.ProductMapper;
import com.ecom.productcatalog.model.Category;
import com.ecom.productcatalog.model.Product;
import com.ecom.productcatalog.repository.CategoryRepository;
import com.ecom.productcatalog.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    
    // Save a single product
    public ProductDto saveProduct(ProductRequestDto dto) {
        Category category = categoryRepository.findByName(dto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategoryName()));

        Product product = ProductMapper.toEntity(dto, category);
        Product saved = productRepository.save(product);
        return ProductMapper.toDto(saved);
    }

   
    // Save multiple products in a category
    public List<ProductDto> saveProductsByCategoryName(String categoryName, List<ProductRequestDto> dtos) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found: " + categoryName));

        List<Product> products = new ArrayList<>();
        for (ProductRequestDto dto : dtos) {
            Product p = ProductMapper.toEntity(dto, category);
            products.add(p);
        }

        List<Product> savedProducts = productRepository.saveAll(products);

        List<ProductDto> result = new ArrayList<>();
        for (Product p : savedProducts) {
            result.add(ProductMapper.toDto(p));
        }
        return result;
    }

    
    // Get all products
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> result = new ArrayList<>();
        for (Product p : products) {
            result.add(ProductMapper.toDto(p));
        }
        return result;
    }

    
    // Get product by ID
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return ProductMapper.toDto(product);
    }

    
    // Get products by category name
    public List<ProductDto> getProductsByCategoryName(String categoryName) {
        List<Product> products = productRepository.findByCategory_NameIgnoreCase(categoryName);
        List<ProductDto> result = new ArrayList<>();
        for (Product p : products) {
            result.add(ProductMapper.toDto(p));
        }
        return result;
    }

    // Get products by price range
    public List<ProductDto> getProductsByPriceRange(Double min, Double max) {
        List<Product> products = productRepository.findByPriceBetween(min, max);
        List<ProductDto> result = new ArrayList<>();
        for (Product p : products) {
            result.add(ProductMapper.toDto(p));
        }
        return result;
    }

    // Get products less than price
    public List<ProductDto> getProductsLessThan(Double price) {
        List<Product> products = productRepository.findByPriceLessThan(price);
        List<ProductDto> result = new ArrayList<>();
        for (Product p : products) {
            result.add(ProductMapper.toDto(p));
        }
        return result;
    }

    // Get products greater than price
    public List<ProductDto> getProductsGreaterThan(Double price) {
        List<Product> products = productRepository.findByPriceGreaterThan(price);
        List<ProductDto> result = new ArrayList<>();
        for (Product p : products) {
            result.add(ProductMapper.toDto(p));
        }
        return result;
    }

    // Update product
    public ProductDto updateProduct(Long id, ProductRequestDto dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        Category category = categoryRepository.findByName(dto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategoryName()));

        ProductMapper.updateEntity(dto, existing, category);
        Product updated = productRepository.save(existing);
        return ProductMapper.toDto(updated);
    }

    // Delete product
    public void deleteProduct(Long id) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(existing);
    }
}
