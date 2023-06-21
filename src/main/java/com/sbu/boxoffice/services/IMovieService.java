package com.sbu.boxoffice.services;

import java.util.List;

import com.sbu.boxoffice.entities.Movie;

public interface IMovieService {
    List<Movie> getAllMovies();
}
