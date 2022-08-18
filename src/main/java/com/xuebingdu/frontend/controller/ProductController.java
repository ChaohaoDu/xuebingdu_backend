package com.xuebingdu.frontend.controller;

import com.xuebingdu.frontend.dto.NewProduct;
import com.xuebingdu.frontend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-service")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping()
	public ResponseEntity<String> createProduct(@RequestBody NewProduct newProduct) {
		productService.createProduct(newProduct);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
