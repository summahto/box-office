package com.sbu.boxoffice.commands;

import java.util.List;

import com.sbu.boxoffice.entities.ShowSeat;
import com.sbu.boxoffice.services.IShowService;

public class DisplayShowSeatCommand implements ICommand {

    private final IShowService iShowService;

    public DisplayShowSeatCommand(IShowService iShowService) {
        this.iShowService = iShowService;
    }

    @Override
    public void execute(List<String> tokens) {
        String showId = tokens.get(1);
        List<ShowSeat> showSeatList = iShowService.getAllShowSeats(showId);
        showSeatList.stream()
                .forEach(showSeat -> {
                    System.out.println("SeatRow - " + showSeat.getSeatRow());
                    System.out.println("SeatColumn - " + showSeat.getSeatColumn());
                    System.out.println("Status - " + showSeat.getShowSeatStatus());
                    System.out.println();
                });
    }
}
