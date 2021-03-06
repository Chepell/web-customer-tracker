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
		// create query and sort by last name
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
		// execute query and get result list
		List<Customer> list = query.getResultList();
		// return the result
		return list;
	}

	@Override
	public void saveCustomer(Customer customer) {
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		// save/update objects
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		// get customer by id
		Customer customer = session.get(Customer.class, id);
		// return the result
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();

//		// 1st method
//		// get customer by id
//		Customer customer = session.get(Customer.class, id);
//		// delete customer
//		session.delete(customer);

		// 2nd method
		// delete object with primary key
		Query query = session.createQuery("DELETE FROM Customer WHERE id=:customerId");
		query.setParameter("customerId", id);
		query.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String searchName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		Query query;

		// only search by name if theSearchName is not empty
		if (searchName != null && searchName.trim().length() > 0) {
			// search for firstName or lastName ... case insensitive
			query = currentSession.
					createQuery("FROM Customer WHERE LOWER(firstName) LIKE :name OR LOWER(lastName) LIKE :name",
							Customer.class);
			query.setParameter("name", "%" + searchName.toLowerCase() + "%");
		} else {
			// theSearchName is empty ... so just get all customers
			query = currentSession.createQuery("FROM Customer", Customer.class);
		}

		// execute query and get result list
		List<Customer> customers = query.getResultList();

		// return the results
		return customers;
	}
}
