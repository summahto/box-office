package com.sbu.boxoffice.services;

import java.util.List;

import com.sbu.boxoffice.entities.Movie;
import com.sbu.boxoffice.repositories.IMovieRepository;

public class MovieService implements IMovieService {
    private final IMovieRepository iMovieRepository;

    public MovieService(IMovieRepository iMovieRepository) {
        this.iMovieRepository = iMovieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return iMovieRepository.getAllMovies();
    }
}
