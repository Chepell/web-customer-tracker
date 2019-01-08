package com.voytenko.springdemo.controller;

import com.voytenko.springdemo.entity.Customer;
import com.voytenko.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Artem Voytenko
 * 07.01.2019
 */

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject CustomerService
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomer(Model model) {
		// get the customers from CustomerService
		List<Customer> customers = customerService.getCustomers();
		// add customers to model
		model.addAttribute("customers", customers);
		return "list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		// create model attribute to bind form data
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		// save the customer using CustomerService
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
		// get customer from DB
		Customer customer = customerService.getCustomer(id);
		// set customer as a model attribute
		model.addAttribute("customer", customer);
		// send over to our form
		return "customer-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int id) {
		// delete the customer with id using CustomerService
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}

	@PostMapping("/search")
	public String searchCustomers(@RequestParam("searchName") String searchName, Model model) {
		// search customers from the service
		List<Customer> customers = customerService.searchCustomers(searchName);
		// add the customers to the model
		model.addAttribute("customers", customers);
		return "list-customers";
	}
}