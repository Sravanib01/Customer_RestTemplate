package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.Customer;
import com.spring.request.RequestEntity;
import com.spring.response.CustomerResponseObj;
import com.spring.service.ServiceImpl;

@RestController
@RequestMapping("/custData")
public class CustomerController {
	@Autowired
	public ServiceImpl service;
	
	// test git push
	// test added
	// test three added
	
	@PostMapping("/info")
	public ResponseEntity<?> saveData(@RequestBody RequestEntity obj) {
		CustomerResponseObj saveData = service.saveData(obj);
		return new ResponseEntity<>(saveData, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getById/{custId}", produces = "application/json")
	public ResponseEntity<?> getDataById(@PathVariable("custId") int custId) {
		CustomerResponseObj responseGetById = service.responseGetById(custId);
		return new ResponseEntity<>(responseGetById, HttpStatus.OK);
	}

	@GetMapping("/getAllData")
	public ResponseEntity<?> getAllData() {
 List<CustomerResponseObj> allData = service.getAllData();
		return new ResponseEntity<>(allData, HttpStatus.OK);
	}

	@GetMapping("/getData")
	public ResponseEntity<List<?>> getAllDataByCustomer() {
		List<Customer> allDataFromCustomer = service.getAllDataFromCustomer();
		return new ResponseEntity<>(allDataFromCustomer, HttpStatus.OK);
	}

}
