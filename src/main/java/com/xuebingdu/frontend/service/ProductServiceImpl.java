package com.xuebingdu.frontend.service;

import com.xuebingdu.frontend.dao.ProductRepository;
import com.xuebingdu.frontend.dao.ProductSizeRepository;
import com.xuebingdu.frontend.dto.NewProduct;
import com.xuebingdu.frontend.entity.Product;
import com.xuebingdu.frontend.entity.ProductSize;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	private ProductRepository productRepository;
	private ProductSizeRepository productSizeRepository;

	public ProductServiceImpl(ProductRepository productRepository, ProductSizeRepository productSizeRepository) {
		this.productRepository = productRepository;
		this.productSizeRepository = productSizeRepository;
	}

	@Override
	@Transactional
	public void createProduct(NewProduct newProduct) {
		Product product = newProduct.getProduct();
		Long nextProductId = productRepository.getNextSeriesId();
		productRepository.save(product);

		for (ProductSize productSize : newProduct.getProductSize()) {
			productSize.setProductId(nextProductId);
			productSize.setActive(Boolean.TRUE);
			productSize.setDateCreated(new Date());
			productSize.setLastUpdated(new Date());
			productSizeRepository.save(productSize);
		}
	}
}
