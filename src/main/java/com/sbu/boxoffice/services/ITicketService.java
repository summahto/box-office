package com.sbu.boxoffice.services;

import java.util.List;
import java.util.Map;

import com.sbu.boxoffice.entities.Movie;
import com.sbu.boxoffice.entities.Screen;
import com.sbu.boxoffice.entities.Seat;
import com.sbu.boxoffice.entities.Show;
import com.sbu.boxoffice.entities.Ticket;
import com.sbu.boxoffice.exceptions.NoSuchTicketFoundException;
import com.sbu.boxoffice.exceptions.SeatNotAvailableException;

public interface ITicketService {

    Ticket bookTicket(String customerName, String customerEmail, String showId, List<Seat> seatList)
            throws SeatNotAvailableException;

    void cancelTicket(Integer ticketId) throws NoSuchTicketFoundException;

    Map<Screen, Integer> getScreenSeatsSoldMap();

    Map<Show, Integer> getShowSeatsSoldMap();

    Map<Movie, Integer> getMovieSeatsSoldMap();

}
