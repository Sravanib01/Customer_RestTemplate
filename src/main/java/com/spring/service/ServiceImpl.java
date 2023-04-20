package com.spring.service;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.dao.CustomerDao;
import com.spring.entity.Customer;
import com.spring.request.RequestEntity;
import com.spring.response.AddressResponse;
import com.spring.response.CustomerResponseObj;


/* 
 * application.properties
   ========================
 
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.datasource.url=jdbc:oracle:thin:@Localhost:1521:XE
spring.datasource.username=system
spring.datasource.password=manager
server.port=8026
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.webservices.path=http://localhost:8021/addressdata
	*/

@Service
public class ServiceImpl implements CustomerService {

	@Autowired
	public CustomerDao dao;

	@Autowired
	public ModelMapper mapper;

	@Autowired
	public RestTemplate template;

	
	@Value("${servicesurl.customer-address}")
	private String addressBaseUrl;
	
	
	@Override
	public CustomerResponseObj saveData(RequestEntity req) {
		Customer cust = mapper.map(req, Customer.class);
		Customer custObj = dao.save(cust);
		CustomerResponseObj responseObj = mapper.map(custObj, CustomerResponseObj.class);
		return responseObj;
	}

	@Override
	public CustomerResponseObj responseGetById(int custId) {
		System.out.println("AddressUrl : "+addressBaseUrl);

		Optional<Customer> id = dao.findById(custId);
		if (id.isPresent()) {
			Customer customerIds = id.get();
			CustomerResponseObj addressByCustId = mapper.map(customerIds, CustomerResponseObj.class);

			/*
			 * getAddressResponseByRestTemplate insted of placing the code for getting the
			 * Address by using restTemplate we create seperate method for that and call
			 * that method into this responseGetById mathod..
			 */

			AddressResponse addressResponseByRestTemplate = getAddressResponseByRestTemplate(custId);
			addressByCustId.setAddressResponse(addressResponseByRestTemplate);
			return addressByCustId;

			/*
			 * AddressResponse address = new AddressResponse();
			 * responseById.setAddressResponse(address); ResponseEntity<AddressResponse>
			 * customerWithAddress = template.getForEntity(
			 * "http://localhost:8021/addressdata/getByCustId/{custId}",
			 * AddressResponse.class, custId);
			 * 
			 * // ResponseEntity<AddressResponse> customerWithAddress = template.exchange(
			 * // "http://localhost:8021/addressdata/getByCustId/4", HttpMethod.GET, null,
			 * // AddressResponse.class, // custId); if (customerWithAddress.getStatusCode()
			 * == HttpStatus.OK) { if (customerWithAddress.hasBody()) { AddressResponse
			 * AddressData = customerWithAddress.getBody();
			 * responseById.setAddressResponse(AddressData);
			 * 
			 * } else { responseById.setAddressResponse(address); }
			 * System.out.println(responseById); return responseById; }
			 */

		} else {
			return null;
		}
	}

	public AddressResponse getAddressResponseByRestTemplate(int custId) {
		//ResponseEntity<AddressResponse> AddressResponse = template.getForEntity("http://localhost:8021/addressdata/getByCustId/{custId}",AddressResponse.class, custId);
		//ResponseEntity<AddressResponse> AddressResponse = template.getForEntity(addressBaseUrl+"getById/{custId}", AddressResponse.class,custId);
		//ResponseEntity<AddressResponse> AddressResponse = template.exchange("http://localhost:8021/addressdata/getByCustId/{custId}", HttpMethod.GET,null , AddressResponse.class, custId);
		
		
		ResponseEntity<AddressResponse> AddressResponse = template.exchange(addressBaseUrl+"/getByCustId/{custId}", HttpMethod.GET, null, AddressResponse.class, custId); 
		
		AddressResponse address = null;
		if (AddressResponse.getStatusCode() == HttpStatus.OK) {
			if (AddressResponse.hasBody()) {
				address = AddressResponse.getBody();
				return address;
			}
		}
		return null;

	}

	@Override
	public List<CustomerResponseObj> getAllData() {
		List<Customer> findAll = dao.findAll();
		List<CustomerResponseObj> collect = findAll.stream()
				.map(customer -> mapper.map(customer, CustomerResponseObj.class)).collect(Collectors.toList());
		return collect;

	}

	@Override
	public List<Customer> getAllDataFromCustomer() {
		List<Customer> findAll = dao.findAll();
		findAll.forEach(c -> System.out.println(c));
		return findAll;
	}

}
