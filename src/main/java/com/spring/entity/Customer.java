package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customers_dataa")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Id
	@Column(name = "cust_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int custId;
	@Column(name = "cust_name",nullable = false)
	private String custName;
	@Column(name = "cust_address")
	private String custAddress;

}
