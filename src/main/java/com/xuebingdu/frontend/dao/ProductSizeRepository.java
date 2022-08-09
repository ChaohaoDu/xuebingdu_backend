package com.xuebingdu.frontend.dao;

import com.xuebingdu.frontend.entity.ProductSize;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "productSize", path = "product_size")
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {
	List<ProductSize> findByProductId(@Param("id") Long id);
}

