package controller;

import entities.Customer;
import service.CustomerService;

public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void createCustomer(Customer customer) {
        customerService.save(customer);
    }

    public Customer findCustomer(String customerCode) {
        return customerService.findByCode(customerCode);
    }

    public void deleteCustomer(String customerCode) {
        customerService.delete(customerCode);
    }

    public boolean customerExists(String customerCode) {
        return customerService.existsByCode(customerCode);
    }
}