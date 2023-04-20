package com.spring.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Customer;
import com.spring.response.CustomerResponseObj;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Serializable> {

}
