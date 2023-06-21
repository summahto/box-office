package com.sbu.boxoffice.entities;

import java.util.Objects;

import com.sbu.boxoffice.utils.IdGenerator;

public class Customer extends BaseEntity {

    private final String name;
    private final String email;

    public Customer(String id, String name, String email) {

        this.id = IdGenerator.generateId(Customer.class);
        this.name = name;
        this.email = email;
    }

    public Customer(String name, String email) {

        this.id = IdGenerator.generateId(Customer.class);
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Customer))
            return false;

        Customer inCustomer = (Customer) obj;
        return this.id.equals(inCustomer.getId());

    }

    @Override
    public String toString() {
        return "Customer [name=" + name + ", email=" + email + "]";
    }

}
