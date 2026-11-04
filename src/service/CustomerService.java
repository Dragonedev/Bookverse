package service;

import entities.Customer;
import exceptions.DuplicateResourceException;
import exceptions.InvalidDataException;
import exceptions.ResourceNotFoundException;
import repository.CustomerRepository;

public class CustomerService {

	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public void save(Customer customer) {
		if (customer == null) {
			throw new InvalidDataException("Customer cannot be null.");
		}

		if (customer.getCustomerCode() == null || customer.getCustomerCode().isBlank()) {
			throw new InvalidDataException("Customer code is required.");
		}

		if (customer.getName() == null || customer.getName().isBlank()) {
			throw new InvalidDataException("Customer name is required.");
		}

		if (customer.getCpf() == null || !customer.getCpf().matches("\\d{11}")) {
			throw new InvalidDataException("CPF must contain 11 digits.");
		}

		if (customer.getEmail() == null || !customer.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
			throw new InvalidDataException("Invalid email.");
		}

		if (customerRepository.existsByCode(customer.getCustomerCode())) {
			throw new DuplicateResourceException("Customer already exists.");
		}

		customerRepository.save(customer);
	}

	public Customer findByCode(String customerCode) {
		if (customerCode == null || customerCode.isBlank()) {
			throw new InvalidDataException("Customer code is required.");
		}

		return customerRepository.findByCode(customerCode)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found."));
	}

	public void delete(String customerCode) {
		findByCode(customerCode);
		customerRepository.delete(customerCode);
	}

	public boolean existsByCode(String customerCode) {
		if (customerCode == null || customerCode.isBlank()) {
			throw new InvalidDataException("Customer code is required.");
		}

		return customerRepository.existsByCode(customerCode);
	}
}