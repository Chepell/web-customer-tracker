package com.voytenko.springdemo.dao;

import com.voytenko.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Artem Voytenko
 * 07.01.2019
 */

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		// create query
		Query<Customer> query = session.createQuery("from Customer", Customer.class);
		// execute query and get result list
		List<Customer> list = query.getResultList();
		// return the result
		return list;
	}
}
