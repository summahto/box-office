package com.sbu.boxoffice.repositories;

import java.util.List;

import com.sbu.boxoffice.entities.Movie;

public interface IMovieRepository {
    List<Movie> getAllMovies();

    void saveMovie(Movie movie);

    Movie getMovieById(String id);
}
