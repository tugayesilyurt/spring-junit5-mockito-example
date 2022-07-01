package com.spring.mockito.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.mockito.entity.Customer;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerRepositoryTest {

	@Autowired
	CustomerRepository customerRepository;

	@BeforeEach
	void initUseCase() {
		Customer customer = Customer.builder().firstName("default").lastName("default").email("default").build();
		customerRepository.save(customer);
	}

	@AfterEach
	public void destroyAll() {
		customerRepository.deleteAll();
	}

	@Test
	void save_success() {
		
		Customer customer = Customer.builder().firstName("tugay").lastName("yesilyurt").email("htyesilyurt@gmail.com")
				.build();

		Customer customerResponse = customerRepository.save(customer);

		Assert.assertEquals(customer, customerResponse);
		
	}

	@Test
	void findByFirstName_success() {
		
		Optional<Customer> customer = customerRepository.findByFirstName("default");
		assertTrue(customer.isPresent());
		
	}

}
