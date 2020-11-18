package com.centric.productapiservice.common.util;

import org.springframework.stereotype.Component;

import com.centric.productapiservice.common.exception.InvalidRequestException;
import com.centric.productapiservice.model.Product;
import com.google.common.base.Strings;

@Component
public class ValidationUtils {

	public void verifyProductParameter(Product product) {
		if(product == null) {
			throw new InvalidRequestException("Product is required");
		}
		if(Strings.isNullOrEmpty(product.getName())) {
			throw new InvalidRequestException("Product name is required");
		}
		if(Strings.isNullOrEmpty(product.getCategory())) {
			throw new InvalidRequestException("Product category is required");
		}
	}	
}
