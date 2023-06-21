package com.sbu.boxoffice.repositories;

import java.util.List;

import com.sbu.boxoffice.entities.Customer;
import com.sbu.boxoffice.entities.Seat;
import com.sbu.boxoffice.entities.Show;
import com.sbu.boxoffice.entities.Ticket;

public interface ITicketRepository {
    Ticket getTicketById(Integer id);

    Ticket saveTicket(Customer customer, Show show, List<Seat> seatList);

    void removeTicket(Integer id);
}
