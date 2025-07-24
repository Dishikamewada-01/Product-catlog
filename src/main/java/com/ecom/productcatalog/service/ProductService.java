package com.ecom.productcatalog.service;

import com.ecom.productcatalog.model.Category;
import com.ecom.productcatalog.model.Product;
import com.ecom.productcatalog.repository.CategoryRepository;
import com.ecom.productcatalog.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository , CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository=categoryRepository;
    }
    
    
    // To save Products in a particular category
    public List<Product> saveProductsByCategoryName(String categoryName, List<Product> products) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found with name: " + categoryName));

        for (Product product : products) {
            product.setCategory(category);
        }

        return productRepository.saveAll(products);
    }
    
    
    

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsByCategoryName(String categoryName) {
        return productRepository.findByCategory_NameIgnoreCase(categoryName);
    }

    public List<Product> getProductsByPrice(Double price) {
        return productRepository.findByPrice(price);
    }
    
    public List<Product> getProductsByPriceRange(Double min, Double max) {
        return productRepository.findByPriceBetween(min, max);
    }

    public List<Product> getProductsLessThan(Double price) {
        return productRepository.findByPriceLessThan(price);
    }

    public List<Product> getProductsGreaterThan(Double price) {
        return productRepository.findByPriceGreaterThan(price);
    }
    
    
    public Product updateProduct(Product product) {
    	return productRepository.save(product);
    }
    
    
    public void deleteProduct(Long id) {
    	productRepository.deleteById(id);
    }
    
}
