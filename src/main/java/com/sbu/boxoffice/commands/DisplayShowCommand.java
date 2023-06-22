package com.sbu.boxoffice.commands;

import java.text.SimpleDateFormat;
import java.util.List;

import com.sbu.boxoffice.dto.ShowResponse;
import com.sbu.boxoffice.services.IShowService;

public class DisplayShowCommand implements ICommand {

    private final IShowService iShowService;

    public DisplayShowCommand(IShowService iShowService) {
        this.iShowService = iShowService;
    }

    @Override
    public void execute(List<String> tokens) {

        List<ShowResponse> showResponseList;

        if (!tokens.isEmpty()) {

            String movieTitle = tokens.get(0);
            showResponseList = iShowService.getAllShowsByMovieTitle(movieTitle);

        } else {
            showResponseList = iShowService.getAllShows();

        }

        showResponseList.stream()
                .forEach(showResponse -> {
                    System.out.println("Show ID - " + showResponse.getShowId());
                    System.out.println("Title - " + showResponse.getMovieTitle());
                    System.out.println(
                            "Start - " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(showResponse.getStart()));
                    System.out
                            .println("End - "
                                    + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(showResponse.getEnd()));
                    System.out.println("Cinema - " + showResponse.getCinemaName());
                    System.out.println("Screen - " + showResponse.getScreenName());
                    System.out.println();
                });
    }
}
