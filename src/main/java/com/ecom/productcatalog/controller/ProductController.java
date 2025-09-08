package com.ecom.productcatalog.controller;

import com.ecom.productcatalog.dto.response.ProductDto;
import com.ecom.productcatalog.mapper.ProductMapper;
import com.ecom.productcatalog.model.Product;
import com.ecom.productcatalog.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Save products in a particular category
    @PostMapping("/category/{categoryName}")
    public ResponseEntity<?> saveProductsForCategory(@PathVariable String categoryName,
                                                 @RequestBody List<Product> products) {
    	
    	try {
            List<Product> savedProducts = productService.saveProductsByCategoryName(categoryName, products);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProducts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found: " + categoryName);
        }
    	
    }
    
    
    // Get all Products with base url
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products");
        }

        List<ProductDto> dtos = products.stream()
                                        .map(ProductMapper::toDto)
                                        .toList();

        return ResponseEntity.ok(dtos);
    }

    
    // Fetch product by productId
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
    	
    	Optional<Product> product= productService.getProductById(id);
    	if(product.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No product with given id " + id);
    	}
    	return ResponseEntity.ok(product.get());
    	
    }

    
    // Fetch Products by CategoryName
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable String categoryName) {
    	
    	List<Product> products= productService.getProductsByCategoryName(categoryName);
    	if(products.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product in " + categoryName);
    	}
    	
    	return ResponseEntity.ok(products);
    	
    }

    
    //Fetch Products by price
    @GetMapping("/price/{price}")
    public ResponseEntity<?> getProductsByPrice(@PathVariable Double price) {
    	List<Product> products = productService.getProductsByPrice(price);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("No products found with price: " + price);
        }
        return ResponseEntity.ok(products);
    }
    
    
    //Fetch products Between price Range
    @GetMapping("/price-range")
    public ResponseEntity<?> getProductsByPriceRange(@RequestParam Double min, @RequestParam Double max) {
    	List<Product> products = productService.getProductsByPriceRange(min, max);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("No products found between price range " + min + " - " + max);
        }
        return ResponseEntity.ok(products);
    }

    
    // Fetch products of price less than
    @GetMapping("/price-less-than/{price}")
    public ResponseEntity<?> getProductsLessThan(@PathVariable Double price) {
        List<Product> products = productService.getProductsLessThan(price);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("No products found with price less than: " + price);
        }
        return ResponseEntity.ok(products);
    }

    
    // Fetch products of Price greater than
    @GetMapping("/price-greater-than/{price}")
    public ResponseEntity<?> getProductsGreaterThan(@PathVariable Double price) {
        List<Product> products = productService.getProductsGreaterThan(price);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("No products found with price greater than: " + price);
        }
        return ResponseEntity.ok(products);
    }
    
    
    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id , @RequestBody Product updatedProduct){
    	Optional<Product> existingProduct = productService.getProductById(id);
    	
    	if(existingProduct.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with id not found");
    	}
    	
    	
    	// Or
    	
//    	Product product = existingProduct.orElseThrow(() -> 
//        new RuntimeException("Product not found"));
   	
    	Product product= existingProduct.get();
    	product.setName(updatedProduct.getName());
    	product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
    	product.setImageUrl(updatedProduct.getImageUrl());
        product.setCategory(updatedProduct.getCategory()); // Or set category by name if needed

        Product savedProduct = productService.updateProduct(product);
   	    return ResponseEntity.ok(savedProduct);
    	
    }
    
  
     // Delete product by particular id
     @DeleteMapping("/{id}")
     public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
    	 Optional<Product> product = productService.getProductById(id);
    	 if(product.isEmpty()) {
    		 ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not Found!! Cant be delete");
    	 }
    	 
    	productService.deleteProduct(id);
    	return ResponseEntity.of(product);
     }
    
    
}
