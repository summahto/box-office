package com.sbu.boxoffice.entities;

import java.util.List;
import java.util.Objects;

public class Ticket {

    private final Integer id;
    private final Customer customer;
    private final Show show;
    private final List<Seat> seats;

    public Ticket(Integer id, Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = seats;
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getShowId() {
        return show.getId();
    }

    public Integer getId() {
        return id;
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

    @Override
    public String toString() {
        return "Ticket [customer=" + customer + ", show=" + show + ", seats=" + seats + "]";
    }

}