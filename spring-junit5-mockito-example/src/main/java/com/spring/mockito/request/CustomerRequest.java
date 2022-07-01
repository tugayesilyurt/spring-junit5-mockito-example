package com.spring.mockito.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRequest {
	
	private String firstName;
	private String email;
	private String lastName;

}
