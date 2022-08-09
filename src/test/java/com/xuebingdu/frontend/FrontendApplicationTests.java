package com.xuebingdu.frontend;

import com.xuebingdu.frontend.dao.ProductRepository;
import com.xuebingdu.frontend.entity.Product;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FrontendApplicationTests {

	@Test
	void contextLoads() {
		Optional<Product> byId = pr.findById(1L);
		System.out.println(byId);
	}

	@Autowired
	private ProductRepository pr;

}
