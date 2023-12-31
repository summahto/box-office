package com.sbu.boxoffice.utils;

import com.sbu.boxoffice.commands.*;
import com.sbu.boxoffice.repositories.*;
import com.sbu.boxoffice.repositories.data.*;
import com.sbu.boxoffice.services.*;

public class ApplicationConfig {
    private final IShowRepository iShowRepository = new ShowRepository();
    private final IShowSeatRepository iShowSeatRepository = new ShowSeatRepository();
    private final IMovieRepository iMovieRepository = new MovieRepository();
    private final ICustomerRepository iCustomerRepository = new CustomerRepository();
    private final ICinemaRepository iCinemaRepository = new CinemaRepository();
    private final ITicketRepository iTicketRepository = new TicketRepository();

    private final IShowService iShowService = new ShowService(iShowRepository, iShowSeatRepository);
    private final IMovieService iMovieService = new MovieService(iMovieRepository);
    private final ITicketService iTicketService = new TicketService(iCustomerRepository, iShowSeatRepository,
            iShowRepository, iTicketRepository);
    private final ICinemaService iCinemaService = new CinemaService(iCinemaRepository);

    private final ICommand bookTicketCommand = new BookTicketCommand(iTicketService);
    private final ICommand displayMovieCommand = new DisplayMovieCommand(iMovieService);
    private final ICommand displayShowCommand = new DisplayShowCommand(iShowService);
    private final ICommand displayShowSeatCommand = new DisplayShowSeatCommand(iShowService);
    private final ICommand generateReportCommand = new GenerateReportCommand(iTicketService, iCinemaService);
    private final ICommand swapMovieCommand = new SwapMovieCommand(iShowService);
    // private final ICommand cancelTicketCommand = new
    // CancelTicketCommand(iTicketService);

    private final CommandInvoker commandInvoker = new CommandInvoker();
    private final DataLoader dataLoader = new DataLoader();

    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("DISPLAY-MOVIES", displayMovieCommand);
        commandInvoker.register("DISPLAY-SHOWS", displayShowCommand);
        commandInvoker.register("DISPLAY-SEATS", displayShowSeatCommand);
        commandInvoker.register("BOOK-TICKETS", bookTicketCommand);
        commandInvoker.register("SWAP-MOVIES", swapMovieCommand);
        commandInvoker.register("GENERATE-REPORT", generateReportCommand);
        // commandInvoker.register("CANCEL-TICKET", cancelTicketCommand);
        return commandInvoker;
    }

    public DataLoader getDataLoader() {
        dataLoader.register("CINEMA-DATA", new CinemaData(iCinemaRepository));
        dataLoader.register("SCREEN-DATA", new ScreenData(iCinemaRepository));
        dataLoader.register("SEAT-DATA", new SeatData(iCinemaRepository));
        dataLoader.register("CUSTOMER-DATA", new CustomerData(iCustomerRepository));
        dataLoader.register("MOVIE-DATA", new MovieData(iMovieRepository));
        dataLoader.register("SHOW-DATA",
                new ShowData(iShowRepository, iCinemaRepository, iMovieRepository, iShowSeatRepository));
        return dataLoader;
    }
}
