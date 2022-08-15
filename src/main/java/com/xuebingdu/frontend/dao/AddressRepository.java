package com.xuebingdu.frontend.dao;

import com.xuebingdu.frontend.entity.Address;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "address")
public interface AddressRepository extends JpaRepository<Address, Long> {
	@Override
	Optional<Address> findById(Long aLong);
}
