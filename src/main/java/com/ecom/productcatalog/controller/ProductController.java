package com.ecom.productcatalog.controller;

import com.ecom.productcatalog.dto.request.ProductRequestDto;
import com.ecom.productcatalog.dto.response.ProductDto;
import com.ecom.productcatalog.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ---------------- Create ----------------

    // Save a single product
    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductRequestDto dto) {
        ProductDto saved = productService.saveProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Save multiple products for a category
    @PostMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductDto>> saveProductsForCategory(
            @PathVariable String categoryName,
            @RequestBody List<ProductRequestDto> dtos) {

        // Note: categoryName is validated in service
        List<ProductDto> savedProducts = productService.saveProductsByCategoryName(categoryName, dtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProducts);
    }

    
    // ---------------- Read ----------------

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

   
    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

   
    // Get products by category name
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable String categoryName) {
        List<ProductDto> products = productService.getProductsByCategoryName(categoryName);
        return ResponseEntity.ok(products);
    }

   
    // Get products by price range
    @GetMapping("/price-range")
    public ResponseEntity<List<ProductDto>> getProductsByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max) {

        List<ProductDto> products = productService.getProductsByPriceRange(min, max);
        return ResponseEntity.ok(products);
    }

    
    // Get products less than price
    @GetMapping("/price-less-than/{price}")
    public ResponseEntity<List<ProductDto>> getProductsLessThan(@PathVariable Double price) {
        List<ProductDto> products = productService.getProductsLessThan(price);
        return ResponseEntity.ok(products);
    }

    
    // Get products greater than price
    @GetMapping("/price-greater-than/{price}")
    public ResponseEntity<List<ProductDto>> getProductsGreaterThan(@PathVariable Double price) {
        List<ProductDto> products = productService.getProductsGreaterThan(price);
        return ResponseEntity.ok(products);
    }

   
    // ---------------- Update ----------------

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDto dto) {

        ProductDto updated = productService.updateProduct(id, dto);
        return ResponseEntity.ok(updated);
    }

   
    // ---------------- Delete ----------------

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
