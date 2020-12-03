package com.centric.productapiservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.centric.productapiservice.common.exception.ProductNotFoundException;
import com.centric.productapiservice.common.util.ValidationUtils;
import com.centric.productapiservice.model.Product;
import com.centric.productapiservice.service.ProductService;
import com.google.common.base.Strings;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ValidationUtils validationUtils;

    @Autowired
    public ProductController(ProductService productService, ValidationUtils validationUtils) {
        this.productService = productService;
        this.validationUtils = validationUtils;
    }
    
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    	validationUtils.verifyProductParameter(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
    		@RequestParam(required = false) String category,
    		@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
    	
    	Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));    		
    	if(!Strings.isNullOrEmpty(category)) {
    		return ResponseEntity.ok(productService.getProductsByCategory(category, pageable));
    	} else {
    		return ResponseEntity.ok(productService.getAllProducts(pageable));    		
    	}
    }
    
    //Not required
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(required = true) String id) {
    	Product product = null;
    	try {
    		product = productService.getProductById(id).get();
    	} catch(NoSuchElementException e) {
    		throw new ProductNotFoundException("Requested Product not found.");
    	}
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(required = true) String id, @RequestBody Product product) {
    	validationUtils.verifyProductParameter(product);
        return ResponseEntity.accepted().body(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(required = true) String id) {
        productService.deleteProductById(id);
        return ResponseEntity.accepted().build();
    }
}
