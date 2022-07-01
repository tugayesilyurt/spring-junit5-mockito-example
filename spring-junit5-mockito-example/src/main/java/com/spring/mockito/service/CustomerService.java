package com.spring.mockito.service;

import org.springframework.stereotype.Service;

import com.spring.mockito.entity.Customer;
import com.spring.mockito.repository.CustomerRepository;
import com.spring.mockito.request.CustomerRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;

	public Customer saveCustomer(CustomerRequest customerRequest) {

		Customer customer = Customer.builder().firstName(customerRequest.getFirstName())
				.lastName(customerRequest.getLastName()).email(customerRequest.getEmail()).build();

		customer = customerRepository.save(customer);

		return customer;

	}

	public Customer getCustomerByFirstName(String firstName) {

		Customer customer = customerRepository.findByFirstName(firstName).orElseThrow(() -> new RuntimeException("Customer not found!"));
		
		return customer;

	}

}
