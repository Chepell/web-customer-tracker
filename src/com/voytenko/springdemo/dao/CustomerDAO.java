package com.voytenko.springdemo.dao;

import com.voytenko.springdemo.entity.Customer;

import java.util.List;

/**
 * Artem Voytenko
 * 07.01.2019
 */

public interface CustomerDAO {
	List<Customer> getCustomers();

	void saveCustomer(Customer customer);

	Customer getCustomer(int id);
}
