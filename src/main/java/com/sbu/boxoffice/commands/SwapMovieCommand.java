package com.sbu.boxoffice.commands;

import java.util.List;

import com.sbu.boxoffice.services.IMovieService;
import com.sbu.boxoffice.services.IShowService;

public class SwapMovieCommand implements ICommand {

    private final IShowService iShowService;

    public SwapMovieCommand(IShowService iShowService) {
        this.iShowService = iShowService;
    }

    @Override
    public void execute(List<String> tokens) {

        String sourceShowId = tokens.get(0);
        String destinationShowId = tokens.get(1);

        iShowService.swapMovies(sourceShowId, destinationShowId);

    }

}
