package com.spring.mockito.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.spring.mockito.entity.Customer;
import com.spring.mockito.repository.CustomerRepository;
import com.spring.mockito.request.CustomerRequest;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Test
	void whenSaveNewCustomer_shouldReturnCustomer() {

		CustomerRequest request = CustomerRequest.builder().firstName("tugay").lastName("yesilyurt")
				.email("htyesilyurt@gmail.com").build();

		Customer customer = Customer.builder().firstName("tugay").lastName("yesilyurt")
				.email("htyesilyurt@gmail.com").build();

		 when(customerRepository.save(any(Customer.class))).thenReturn(customer);

		Customer customerResponse = customerService.saveCustomer(request);

		Assert.assertEquals(customerResponse, customer);
		Mockito.verify(customerRepository).save(any(Customer.class));


	}	

	@Test
	void whenGetCustomerByFirstName_requestValid_shouldReturnCustomer() {

		Customer customer = Customer.builder().firstName("tugay").lastName("yesilyurt").email("htyesilyurt@gmail.com")
				.build();

		Mockito.when(customerRepository.findByFirstName("tugay")).thenReturn(Optional.of(customer));

		Customer customerResponse = customerService.getCustomerByFirstName("tugay");

		Assert.assertEquals(customer, customerResponse);
		Mockito.verify(customerRepository).findByFirstName("tugay");

	}

	@Test
	void whenGetCustomerByFirstName_requestInvalid_itShouldThrowRuntimeException() {

		Mockito.when(customerRepository.findByFirstName("tugay-unknown")).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> customerService.getCustomerByFirstName("tugay-unknown"));

	}

}
