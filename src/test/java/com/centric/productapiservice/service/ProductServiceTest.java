package com.centric.productapiservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.centric.productapiservice.model.Product;
import com.centric.productapiservice.repository.ProductRepository;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

	@TestConfiguration
	static class ProductServiceTestContextConfiguration {
		@Bean
		public ProductService productService() {
			return new ProductServiceImpl();
		}
	}

	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;
	

	@Before
	public void setUp() {
	}

	@Test
	public void whenValidProduct_thenProductShouldBeSave() {
		String id= "product1";
		Product product1 = new Product();
		product1.setName("productname1");
		product1.setCategory("category1");

		Product savedProduct1 = new Product();
		savedProduct1.setId("product1");
		savedProduct1.setName("productname1");
		savedProduct1.setCategory("category1");

		when(productRepository.save(product1)).thenReturn(savedProduct1);

		Product savedProduct = productService.save(product1);
		assertThat(savedProduct.getId()).isEqualTo(id);
	}	
	
	
	@Test
	public void giveProductList_WhenGetByProductCategory() {
		String category = "product1";
		Pageable pageable = PageRequest.of(0, 10);
		
		Product product1 = new Product();
		product1.setId("product1");
		product1.setName("productname1");
		product1.setCategory("category1");
		Product product2 = new Product();
		product2.setId("product2");
		product2.setName("productname2");
		product2.setCategory("category2");
		Product product3 = new Product();
		product3.setId("product3");
		product3.setName("productname3");
		product3.setCategory("category3");
		List<Product> allProducts = new ArrayList<>();
		allProducts.add(product1);
		allProducts.add(product2);
		allProducts.add(product3);
		when(productRepository.findByCategory(category, pageable)).thenReturn(allProducts);

		List<Product> productList = productService.getProductsByCategory(category, pageable);
		assertThat(productList.size()).isEqualTo(3);
	}
}
