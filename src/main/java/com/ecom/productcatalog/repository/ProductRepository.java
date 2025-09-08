package com.ecom.productcatalog.repository;

import com.ecom.productcatalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    
    // Find products by category name (auto-generated SQL)
    List<Product> findByCategory_NameIgnoreCase(String name);

    // Find products by price
    List<Product> findByPrice(Double price);
    
 // Find products with price between min and max
    List<Product> findByPriceBetween(Double min, Double max);

    // Find products with price less than a value
    List<Product> findByPriceLessThan(Double price);

    // Find products with price greater than a value
    List<Product> findByPriceGreaterThan(Double price);

    // Find products with price less than or equal to a value
    List<Product> findByPriceLessThanEqual(Double price);

    // Find products with price greater than or equal to a value
    List<Product> findByPriceGreaterThanEqual(Double price);
    
    
    
}
