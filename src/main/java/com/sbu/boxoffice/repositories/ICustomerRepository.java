package com.sbu.boxoffice.repositories;

import com.sbu.boxoffice.entities.Customer;

public interface ICustomerRepository {
    Customer getCustomerById(String id);

    Customer getCustomerByName(String Name);

    void saveCustomer(Customer customer);
}
