package com.spring.mockito.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spring.mockito.request.CustomerRequest;
import com.spring.mockito.service.CustomerService;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerUnitTest {

	@InjectMocks
	private CustomerController customerController;

	@Mock
	private CustomerService customerService;

	@Test
	void whenSaveNewCustomer_shouldReturnCustomer() {

		CustomerRequest request = CustomerRequest.builder().firstName("tugay").lastName("yesilyurt")
				.email("htyesilyurt@gmail.com").build();

		ResponseEntity<?> customer = customerController.saveCustomer(request);

		assertEquals(HttpStatus.valueOf(201), customer.getStatusCode());
		
		verify(customerService, times(1)).saveCustomer(request);

	}
	
	@Test
	void whenGetCustomerByFirstName_shouldReturnCustomer() {
		
		ResponseEntity<?> customerByName = customerController.getCustomerByFirstName("tugay");
		assertEquals(HttpStatus.valueOf(200),customerByName.getStatusCode());
        verify(customerService, times(1)).getCustomerByFirstName("tugay");
        
	}

}
