package com.spring.mockito.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mockito.entity.Customer;
import com.spring.mockito.request.CustomerRequest;
import com.spring.mockito.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@PostMapping
	public ResponseEntity<?> saveCustomer(@RequestBody CustomerRequest customerRequest) {

		Customer customer = customerService.saveCustomer(customerRequest);
		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);

	}

	@GetMapping("/name/{name}")
	public ResponseEntity<?> getCustomerByFirstName(@PathVariable String name) {

		Customer customer = customerService.getCustomerByFirstName(name);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);

	}

}
