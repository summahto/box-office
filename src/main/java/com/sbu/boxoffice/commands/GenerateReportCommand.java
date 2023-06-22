package com.sbu.boxoffice.commands;

import java.util.List;
import java.util.Map;

import com.sbu.boxoffice.entities.Movie;
import com.sbu.boxoffice.entities.Screen;
import com.sbu.boxoffice.entities.Show;
import com.sbu.boxoffice.services.ICinemaService;
import com.sbu.boxoffice.services.ITicketService;
import com.sbu.boxoffice.services.TicketService;

public class GenerateReportCommand implements ICommand {

    private final ITicketService iTicketService;
    private final ICinemaService iCinemaService;

    public GenerateReportCommand(ITicketService iTicketService, ICinemaService iCinemaService) {
        this.iTicketService = iTicketService;
        this.iCinemaService = iCinemaService;
    }

    @Override
    public void execute(List<String> tokens) {

        // Map<Screen, Integer> screenSeatsSoldMap =
        // iTicketService.getScreenSeatsSoldMap();
        // Map<Movie, Integer> movieSeatsSoldMap =
        // iTicketService.getMovieSeatsSoldMap();
        Map<Show, Integer> showSeatsSoldMap = iTicketService.getShowSeatsSoldMap();

        iCinemaService.getTotalSeats(tokens.get(0));
        int totalTicketsSold = TicketService.getTotalTicketsSold();

        System.out.println("Ticket Sales Report for ");
        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-30s %-25s %-15s %-30s %-25s\n",
                "Show ", "Movie", "Total Tickets Available", "Total Tickets Sold",
                "Total Tickets Sold for Show", "Percentage Share of Tickets Sold for Show");
        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Map.Entry<Show, Integer> entry : showSeatsSoldMap.entrySet()) {

            Show show = entry.getKey();
            int totalTicketsSoldForShow = entry.getValue();
            double shareOfTicketsSold = totalTicketsSoldForShow / (double) totalTicketsSold;

            System.out.printf("%-15s %-30s %-25s %-15s %-30s %-25s\n",
                    show.getId(), show.getMovie().getTitle(), show.getScreen().getSeatList().size(),
                    entry.getValue(), totalTicketsSoldForShow, shareOfTicketsSold);
        }

        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
        System.out.println("Total Tickets Sold Overall: " + totalTicketsSold);
        System.out.println("\n");
    }

}
