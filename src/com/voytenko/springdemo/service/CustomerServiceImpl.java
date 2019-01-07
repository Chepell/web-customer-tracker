package com.voytenko.springdemo.service;

import com.voytenko.springdemo.dao.CustomerDAO;
import com.voytenko.springdemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Artem Voytenko
 * 07.01.2019
 */

@Service
public class CustomerServiceImpl implements CustomerService {
	// need to dependency injection DAO
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		// delegate calls to DAO
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		// delegate save to DAO
		customerDAO.saveCustomer(customer);
	}
}
