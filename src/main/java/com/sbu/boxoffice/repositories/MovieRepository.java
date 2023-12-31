package com.sbu.boxoffice.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbu.boxoffice.entities.Movie;

public class MovieRepository implements IMovieRepository {
    private final Map<String, Movie> movieMap;

    public MovieRepository() {
        movieMap = new HashMap<>();
    }

    public MovieRepository(Map<String, Movie> movieMap) {
        this.movieMap = movieMap;
    }

    @Override
    public List<Movie> getAllMovies() {
        return new ArrayList<>(movieMap.values());
    }

    @Override
    public void saveMovie(Movie movie) {
        movieMap.put(movie.getId(), movie);
    }

    @Override
    public Movie getMovieById(String id) {
        return movieMap.get(id);
    }

}
