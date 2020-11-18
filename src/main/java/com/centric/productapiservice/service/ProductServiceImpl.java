package com.centric.productapiservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.centric.productapiservice.model.Product;
import com.centric.productapiservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRespository;
	
    public List<Product> getAllProducts(Pageable pageable) {
        return productRespository.findAll(pageable).getContent();
    }

    public Optional<Product> getProductById(String id) {
        return productRespository.findById(id);
    }

    public Product save(Product product) {
        return productRespository.save(product);
    }

    public void deleteProductById(String id) {
        productRespository.deleteById(id);
    }
    
    public List<Product> getProductsByCategory(String category,Pageable pageable) {
        return productRespository.findByCategory(category, pageable);
    }
}
