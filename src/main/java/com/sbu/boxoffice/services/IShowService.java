package com.sbu.boxoffice.services;

import java.util.List;

import com.sbu.boxoffice.dto.ShowResponse;
import com.sbu.boxoffice.entities.ShowSeat;

public interface IShowService {
    List<ShowResponse> getAllShowsByMovieTitle(String movieTitle);

    List<ShowResponse> getAllShows();

    List<ShowSeat> getAllShowSeats(String showId);

    void swapMovies(String show1Id, String show2Id);

}
