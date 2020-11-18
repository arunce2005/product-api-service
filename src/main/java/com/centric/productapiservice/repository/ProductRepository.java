package com.centric.productapiservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.centric.productapiservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	
	List<Product> findByCategory(String category, Pageable pageable);
	
	Page<Product> findAll(Pageable pageable);
	
}
