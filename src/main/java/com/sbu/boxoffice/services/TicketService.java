package com.sbu.boxoffice.services;

import java.util.List;

import com.sbu.boxoffice.entities.Customer;
import com.sbu.boxoffice.entities.Seat;
import com.sbu.boxoffice.entities.Show;
import com.sbu.boxoffice.entities.ShowSeat;
import com.sbu.boxoffice.entities.Ticket;
import com.sbu.boxoffice.exceptions.NoSuchTicketFoundException;
import com.sbu.boxoffice.exceptions.SeatNotAvailableException;
import com.sbu.boxoffice.repositories.ICustomerRepository;
import com.sbu.boxoffice.repositories.IShowRepository;
import com.sbu.boxoffice.repositories.IShowSeatRepository;
import com.sbu.boxoffice.repositories.ITicketRepository;

public class TicketService implements ITicketService {

    private final ICustomerRepository iCustomerRepository;
    private final IShowSeatRepository iShowSeatRepository;
    private final IShowRepository iShowRepository;
    private final ITicketRepository iTicketRepository;

    public TicketService(ICustomerRepository iCustomerRepository, IShowSeatRepository iShowSeatRepository,
            IShowRepository iShowRepository, ITicketRepository iTicketRepository) {
        this.iCustomerRepository = iCustomerRepository;
        this.iShowSeatRepository = iShowSeatRepository;
        this.iShowRepository = iShowRepository;
        this.iTicketRepository = iTicketRepository;
    }

    @Override
    public Ticket bookTicket(String customerName, String customerEmail, String showId, List<Seat> seatList)
            throws SeatNotAvailableException {
        Customer customer = iCustomerRepository.getCustomerByName(customerName);

        if (customer == null) {
            customer = new Customer(customerName, customerEmail);
        }

        Show show = iShowRepository.getShowById(showId);
        for (Seat seat : seatList) {
            ShowSeat showSeat = iShowSeatRepository.getShowSeat(showId, seat.getId());
            if (showSeat.isLocked()) {
                throw new SeatNotAvailableException();
            }
        }
        for (Seat seat : seatList) {
            ShowSeat showSeat = iShowSeatRepository.getShowSeat(showId, seat.getId());
            showSeat.lock();
            iShowSeatRepository.updateShowSeat(showSeat);
        }

        Ticket ticket = iTicketRepository.saveTicket(customer, show, seatList);
        return ticket;
    }

    @Override
    public void cancelTicket(Integer ticketId) throws NoSuchTicketFoundException {
        Ticket ticket = iTicketRepository.getTicketById(ticketId);
        if (ticket == null) {
            throw new NoSuchTicketFoundException();
        }
        List<Seat> seatList = ticket.getSeats();
        for (Seat seat : seatList) {
            ShowSeat showSeat = iShowSeatRepository.getShowSeat(ticket.getId(), seat.getId());
            showSeat.unlock();
            iShowSeatRepository.updateShowSeat(showSeat);
        }
        iTicketRepository.removeTicket(ticketId);
    }
}
