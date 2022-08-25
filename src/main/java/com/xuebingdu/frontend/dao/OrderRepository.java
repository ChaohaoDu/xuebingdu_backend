package com.xuebingdu.frontend.dao;

import com.xuebingdu.frontend.entity.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "order")
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Override
	Optional<Order> findById(Long id);


}
