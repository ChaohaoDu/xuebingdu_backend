package com.xuebingdu.frontend.controller;

import com.xuebingdu.frontend.dao.ProductRepository;
import com.xuebingdu.frontend.dto.NewProduct;
import com.xuebingdu.frontend.entity.Product;
import com.xuebingdu.frontend.service.ProductService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-service")
public class ProductController {
	private final ProductService productService;
	private final ProductRepository productRepository;

	public ProductController(ProductService productService, ProductRepository productRepository) {
		this.productService = productService;
		this.productRepository = productRepository;
	}

	@PostMapping()
	public ResponseEntity<String> createProduct(@RequestBody NewProduct newProduct) {
		productService.createProduct(newProduct);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public List<Product> getAll() {
		return productRepository.findAll();
	}
}
