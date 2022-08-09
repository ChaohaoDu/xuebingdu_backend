package com.xuebingdu.frontend.dao;

import com.xuebingdu.frontend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmail(String theEmail);

}
