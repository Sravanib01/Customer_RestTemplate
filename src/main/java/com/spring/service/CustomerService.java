package com.spring.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.CustomerDao;
import com.spring.entity.Customer;
import com.spring.request.RequestEntity;
import com.spring.response.CustomerResponseObj;

public interface CustomerService {

	public CustomerResponseObj saveData(RequestEntity req);

	public CustomerResponseObj responseGetById(int custId);

	public List<CustomerResponseObj> getAllData();
	
	public List<Customer> getAllDataFromCustomer();

}
