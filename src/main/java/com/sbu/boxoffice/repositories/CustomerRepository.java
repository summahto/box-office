package com.sbu.boxoffice.repositories;

import java.util.HashMap;
import java.util.Map;

import com.sbu.boxoffice.entities.Customer;

public class CustomerRepository implements ICustomerRepository {
    private final Map<String, Customer> customerMap;
    private final Map<String, Customer> customerNameMap;

    public CustomerRepository() {
        customerMap = new HashMap<>();
        customerNameMap = new HashMap<>();
    }

    public CustomerRepository(Map<String, Customer> customerMap, Map<String, Customer> customerNameMap) {
        this.customerMap = customerMap;
        this.customerNameMap = customerNameMap;
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerMap.get(id);
    }

    @Override
    public Customer getCustomerByName(String name) {
        return customerNameMap.get(name);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerMap.put(customer.getId(), customer);
        customerNameMap.put(customer.getName(), customer);
    }
}
