package com.centric.productapiservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.centric.productapiservice.common.exception.InvalidRequestException;
import com.centric.productapiservice.common.util.ValidationUtils;
import com.centric.productapiservice.model.Product;
import com.centric.productapiservice.service.ProductService;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@MockBean
	private ProductService productService;
	
	@MockBean
	private ValidationUtils validationUtils;
	
	private final String baseUrl = "/v1/products";
	 
	@Test
	public void testPostProductSuccess() {
		Product product = createProductSuccess();
		when(productService.save(product)).thenReturn(product);
		
		HttpEntity<Product> entity = new HttpEntity<>(product,null);
		ResponseEntity<Product> response = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, Product.class);
	    assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());
	}
	
	@Test
	public void testPostProductError() {
		Product product = createProductError();
		doThrow(new InvalidRequestException("Product name is required")).when(validationUtils).verifyProductParameter(any(Product.class));
		
		HttpEntity<Product> entity = new HttpEntity<>(product,null);		
		ResponseEntity<Product> response = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, Product.class);
	    assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void testGetProductsSuccess() {
		List<Product> productList = new ArrayList<Product>();
		Pageable pageable = PageRequest.of(0, 10 ,Sort.by(Sort.Direction.DESC, "createdDate"));
		when(productService.getAllProducts(pageable)).thenReturn(productList);
		
		ResponseEntity<Product[]> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null, Product[].class);
	    assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	public void testGetProductsByCategorySuccess() {
		List<Product> productList = new ArrayList<Product>();
		String category = "productcategory";	
		Pageable pageable = PageRequest.of(0, 10 ,Sort.by(Sort.Direction.DESC, "createdDate"));
		when(productService.getProductsByCategory(category, pageable)).thenReturn(productList);
		
		ResponseEntity<Product[]> response = restTemplate.exchange(baseUrl + "?category="+ category, HttpMethod.GET, null, Product[].class);
	    assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
	}
	
	
	private Product createProductSuccess() {
		Product product = new Product();
		product.setName("productname");
		product.setBrand("productbrand");
		product.setDescription("productdescription");
		product.setCategory("productcategory");
		return product;
	}
	
	private Product createProductError() {
		Product product = new Product();
		product.setBrand("productbrand");
		product.setDescription("productdescription");
		return product;
	}
}
