package repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import entities.Customer;

public class CustomerRepository {

    private Map<String, Customer> customers = new HashMap<>();

    public void save(Customer customer) {

        customers.put(customer.getCustomerCode(), customer);

    }

    public Optional<Customer> findByCode(String customerCode) {

        return Optional.ofNullable(customers.get(customerCode));

    }

    public void delete(String customerCode) {

        customers.remove(customerCode);

    }

    public boolean existsByCode(String customerCode) {

        return customers.containsKey(customerCode);

    }

}