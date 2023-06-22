package com.sbu.boxoffice.services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.sbu.boxoffice.entities.Customer;
import com.sbu.boxoffice.entities.Movie;
import com.sbu.boxoffice.entities.Screen;
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

    private static Integer totalTicketsSold = 0;

    private final Map<Screen, Integer> screenSeatsSoldMap;
    private final Map<Show, Integer> showSeatsSoldMap;
    private final Map<Movie, Integer> movieSeatsSoldMap;

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

        this.screenSeatsSoldMap = new TreeMap<>(new Comparator<Screen>() {

            @Override
            public int compare(Screen s1, Screen s2) {
                return s1.getName().compareToIgnoreCase(s2.getName());
            }
        });

        this.showSeatsSoldMap = new TreeMap<>(new Comparator<Show>() {

            @Override
            public int compare(Show s1, Show s2) {
                return s1.getScreenName().compareToIgnoreCase(s2.getScreenName());
            }

        });

        this.movieSeatsSoldMap = new TreeMap<>(new Comparator<Movie>() {

            @Override
            public int compare(Movie m1, Movie m2) {
                return m1.getTitle().compareToIgnoreCase(m2.getTitle());
            }

        });

    }

    public static Integer getTotalTicketsSold() {
        return totalTicketsSold;
    }

    public Map<Screen, Integer> getScreenSeatsSoldMap() {
        return screenSeatsSoldMap;
    }

    public Map<Show, Integer> getShowSeatsSoldMap() {
        return showSeatsSoldMap;
    }

    public Map<Movie, Integer> getMovieSeatsSoldMap() {
        return movieSeatsSoldMap;
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

        if (ticket != null) {
            totalTicketsSold += seatList.size();
            updateShowSeatsSoldMap(ticket, seatList.size());
        }

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
            ShowSeat showSeat = iShowSeatRepository.getShowSeat(ticket.getShowId(), seat.getId());
            showSeat.unlock();
            iShowSeatRepository.updateShowSeat(showSeat);
        }
        iTicketRepository.removeTicket(ticketId);
    }

    private void updateMovieSeatsSoldMap(Ticket ticket, int numOfSeats) {

        Show show = iShowRepository.getShowById(ticket.getShowId());
        movieSeatsSoldMap.put(show.getMovie(), movieSeatsSoldMap.getOrDefault(show.getMovie(), 0) + numOfSeats);

    }

    private void updateShowSeatsSoldMap(Ticket ticket, int numOfSeats) {

        Show show = iShowRepository.getShowById(ticket.getShowId());
        showSeatsSoldMap.put(show, showSeatsSoldMap.getOrDefault(show, 0) + numOfSeats);

    }

    private void updateScreenSeatsSoldMap(Ticket ticket, int numOfSeats) {

        Show show = iShowRepository.getShowById(ticket.getShowId());
        screenSeatsSoldMap.put(show.getScreen(), screenSeatsSoldMap.getOrDefault(show.getScreenName(), 0) + numOfSeats);

    }
}
