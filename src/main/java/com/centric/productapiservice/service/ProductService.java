package com.centric.productapiservice.service;

import org.springframework.data.domain.Pageable;
import com.centric.productapiservice.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
	
    public List<Product> getAllProducts(Pageable pageable);
    
    public Optional<Product> getProductById(String id);

    public Product save(Product product);

    public void deleteProductById(String id);
    
    public List<Product> getProductsByCategory(String category,Pageable pageable);

}
