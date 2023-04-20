package com.spring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseObj {
	private String custId;
	private String custName;
	private String custAddress;
	private AddressResponse addressResponse;

}
