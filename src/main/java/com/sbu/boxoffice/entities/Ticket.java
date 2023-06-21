package com.sbu.boxoffice.entities;

import java.util.List;
import java.util.Objects;

import com.sbu.boxoffice.utils.IdGenerator;

public class Ticket extends BaseEntity {

    private final Customer customer;
    // Not required as of now
    // private final Show show;
    private final List<Seat> seats;

    public Ticket(Customer customer, List<Seat> seats) {
        this.customer = customer;
        this.seats = seats;
        this.id = IdGenerator.generateId(Ticket.class);
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Ticket))
            return false;
        Ticket ticket = (Ticket) o;

        return getId().equals(ticket.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public String toString() {
        return "Ticket [customer=" + customer + ", seats=" + seats + "]";
    }
}