package service;

import entities.Customer;
import repository.CustomerRepository;

public class CustomerService {

	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public void save(Customer customer) {
		customerRepository.save(customer);
	}

	public Customer findByCode(String customerCode) {
		return customerRepository.findByCode(customerCode);
	}

	public void delete(String customerCode) {
		customerRepository.delete(customerCode);
	}

	public boolean existsByCode(String customerCode) {
		return customerRepository.existsByCode(customerCode);
	}
}