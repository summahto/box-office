package com.sbu.boxoffice.commands;

import java.util.List;

import com.sbu.boxoffice.entities.Movie;
import com.sbu.boxoffice.services.IMovieService;

public class DisplayMovieCommand implements ICommand {

    private final IMovieService iMovieService;

    public DisplayMovieCommand(IMovieService iMovieService) {
        this.iMovieService = iMovieService;
    }

    @Override
    public void execute(List<String> tokens) {
        List<Movie> movieList = iMovieService.getAllMovies();
        movieList.stream()
                .forEach(movie -> {
                    System.out.println("Movie ID - " + movie.getId());
                    System.out.println("Title - " + movie.getTitle());
                    System.out.println("Duration - " + movie.getDurationInMins());
                    System.out.println();
                });
    }
}
