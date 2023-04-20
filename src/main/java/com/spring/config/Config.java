package com.spring.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
	
	@Value("${servicesurl.customer-address}")
	private String addressBaseUrl;
	
	@Bean
	public ModelMapper getMappper() {
		return new ModelMapper();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
