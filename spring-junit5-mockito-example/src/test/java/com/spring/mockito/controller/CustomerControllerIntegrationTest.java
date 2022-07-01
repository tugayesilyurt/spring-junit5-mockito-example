package com.spring.mockito.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mockito.entity.Customer;
import com.spring.mockito.request.CustomerRequest;
import com.spring.mockito.service.CustomerService;

@WebMvcTest(value = CustomerController.class)
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	@DisplayName("Test Create Customer")
	void createCustomer_WhenRequestValid_ReturnSuccess() throws Exception {
		
		CustomerRequest request = CustomerRequest.builder().firstName("tugay").lastName("yesilyurt")
				.email("htyesilyurt@gmail.com").build();
		
		Customer customer = Customer.builder().firstName("tugay").lastName("yesilyurt")
				.email("htyesilyurt@gmail.com").build();

		when(customerService.saveCustomer(request)).thenReturn(customer);

		mockMvc.perform(post("/v1/customer").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", Matchers.equalTo("tugay")))
				.andDo(MockMvcResultHandlers.print());

	}
	
	@Test
	@DisplayName("Test Get Customer By FirstName")
	void getCustomerByFirstName_WhenRequestValid_ReturnSuccess() throws Exception {

		String firstName = "tugay";
		
		Customer customer = Customer.builder().firstName("tugay").lastName("yesilyurt")
				.email("htyesilyurt@gmail.com").build();
		
		when(customerService.getCustomerByFirstName(firstName)).thenReturn(customer);
		
		this.mockMvc.perform(MockMvcRequestBuilders
			      .get("/v1/customer/name/{name}", "tugay")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("tugay"));


	}

}
