package com.xuebingdu.frontend.dao;

import com.xuebingdu.frontend.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// path: url中的名称
//@RepositoryRestResource(collectionResourceRel = "product", path = "products")
@RepositoryRestResource(path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findById(Long id);


	//	@Query(value = "SELECT id.nextval FROM dual", nativeQuery = true)
//	@Query(value = "SELECT nextval('productSequence')", nativeQuery = true)
//	@Query(value = "SELECT id.nextval FROM dual", nativeQuery = true)
//	@Query(value = "SELECT productSequence.nextval FROM tableName", nativeQuery = true)
	@Query(value = "select AUTO_INCREMENT from information_schema.TABLES where TABLE_SCHEMA = \"xuebingdu\" and TABLE_NAME = \"product\";\n", nativeQuery = true)
	Long getNextSeriesId();
}
