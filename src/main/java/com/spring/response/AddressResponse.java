package com.spring.response;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

	private Integer addressId;
	private String street;
	private String district;
	private int pin;

}
