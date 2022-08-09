package com.xuebingdu.frontend.dao;

import com.xuebingdu.frontend.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// path: url中的名称
//@RepositoryRestResource(collectionResourceRel = "product", path = "products")
@RepositoryRestResource(path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findById(Long id);
}
