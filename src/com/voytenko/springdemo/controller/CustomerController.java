package com.voytenko.springdemo.controller;

import com.voytenko.springdemo.dao.CustomerDAO;
import com.voytenko.springdemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Artem Voytenko
 * 07.01.2019
 */

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject customer DAO
	@Autowired
	private CustomerDAO customerDAO;

	@GetMapping("/list")
	public String listCustomer(Model model) {
		// get the customers from DAO
		List<Customer> customers = customerDAO.getCustomers();
		// add customers to model
		model.addAttribute("customers", customers);
		return "list-customers";
	}
}
