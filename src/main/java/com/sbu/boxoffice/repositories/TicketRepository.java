package com.sbu.boxoffice.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbu.boxoffice.entities.Customer;
import com.sbu.boxoffice.entities.Seat;
import com.sbu.boxoffice.entities.Show;
import com.sbu.boxoffice.entities.Ticket;

public class TicketRepository implements ITicketRepository {

    private static Integer counter = 0;
    private final Map<Integer, Ticket> ticketMap;

    public TicketRepository() {
        ticketMap = new HashMap<>();
    }

    public TicketRepository(Map<Integer, Ticket> ticketMap, Integer counter) {
        this.ticketMap = ticketMap;
        TicketRepository.counter = counter;
    }

    @Override
    public Ticket getTicketById(Integer id) {
        return ticketMap.get(id);
    }

    @Override
    public void removeTicket(Integer id) {
        ticketMap.remove(id);
    }

    @Override
    public Ticket saveTicket(Customer customer, Show show, List<Seat> seatList) {
        counter++;
        Ticket ticket = new Ticket(customer, seatList);
        ticketMap.put(counter, ticket);
        return ticket;
    }
}
