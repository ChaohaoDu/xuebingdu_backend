package com.xuebingdu.frontend.dao;

import com.xuebingdu.frontend.entity.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmail(String theEmail);

	@Override
	Optional<Customer> findById(Long aLong);
}
