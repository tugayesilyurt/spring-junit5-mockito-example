package com.spring.mockito.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mockito.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	
	Optional<Customer> findByFirstName(String name);

}
