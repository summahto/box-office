package com.sbu.boxoffice.commands;

import java.util.ArrayList;
import java.util.List;

import com.sbu.boxoffice.entities.Seat;
import com.sbu.boxoffice.entities.Ticket;
import com.sbu.boxoffice.exceptions.SeatNotAvailableException;
import com.sbu.boxoffice.services.ITicketService;

public class BookTicketCommand implements ICommand {

    private final ITicketService iTicketService;

    public BookTicketCommand(ITicketService iTicketService) {
        this.iTicketService = iTicketService;
    }

    @Override
    public void execute(List<String> tokens) {

        String showId = tokens.get(1);
        String customerName = tokens.get(2);
        String customerEmail = tokens.get(3);
        String[] seats = tokens.get(4).split(" ");
        List<Seat> seatList = new ArrayList<>();

        for (int i = 0; i < seats.length; i++) {
            String[] words = seats[i].split("#");
            Seat seat = new Seat(seats[i], Integer.parseInt(words[0]), Integer.parseInt(words[1]));
            seatList.add(seat);
        }

        try {
            Ticket ticket = iTicketService.bookTicket(customerName, customerEmail, showId, seatList);
            System.out.println("Successfully booked your seats.");
            System.out.println("Ticket ID - " + ticket.getId());
            System.out.println();
        } catch (SeatNotAvailableException sne) {
            sne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
