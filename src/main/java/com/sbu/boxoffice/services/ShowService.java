package com.sbu.boxoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sbu.boxoffice.dto.ShowResponse;
import com.sbu.boxoffice.entities.Movie;
import com.sbu.boxoffice.entities.Show;
import com.sbu.boxoffice.entities.ShowSeat;
import com.sbu.boxoffice.repositories.IShowRepository;
import com.sbu.boxoffice.repositories.IShowSeatRepository;

public class ShowService implements IShowService {
    private final IShowRepository iShowRepository;
    private final IShowSeatRepository iShowSeatRepository;

    public ShowService(IShowRepository iShowRepository, IShowSeatRepository iShowSeatRepository) {
        this.iShowRepository = iShowRepository;
        this.iShowSeatRepository = iShowSeatRepository;
    }

    public List<ShowResponse> getAllShowsByMovieTitle(String movieTitle) {
        List<ShowResponse> getShowResponseList = new ArrayList<>();
        List<Show> showList = iShowRepository.getAllShowsForMovieName(movieTitle);
        for (Show show : showList) {

            getShowResponseList.add(new ShowResponse(
                    show.getId(),
                    show.getMovieTitle(),
                    show.getCinemaName(),
                    show.getScreenName(),
                    show.getStart(),
                    show.getEnd()));
        }
        return getShowResponseList;

    }

    @Override
    public List<ShowSeat> getAllShowSeats(String showId) {
        return iShowSeatRepository.getShowSeatsByShowId(showId);
    }

    @Override
    public void swapMovies(String show1Id, String show2Id) {

        Show show1 = iShowRepository.getShowById(show1Id);
        Show show2 = iShowRepository.getShowById(show2Id);

        System.out.println("Before Swapping :");
        displayShowDetail(show1Id, show1);

        displayShowDetail(show2Id, show2);

        Movie movie1 = show1.getMovie();
        show1.setMovie(show2.getMovie());
        show2.setMovie(movie1);

        System.out.println("After Swapping :");
        displayShowDetail(show1Id, show1);

        displayShowDetail(show2Id, show2);

    }

    private void displayShowDetail(String show1Id, Show show1) {
        System.out.println("Show ID - " + show1Id +
                "\nTitle - " + show1.getMovieTitle() +
                "\nStart - " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(show1.getStart()) +
                "\nEnd - " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(show1.getEnd()) +
                "\nScreen - " + show1.getScreenName());
        System.out.println();
    }

    @Override
    public List<ShowResponse> getAllShows() {

        List<ShowResponse> getShowResponseList = new ArrayList<>();
        List<Show> showList = iShowRepository.getAllShows();
        for (Show show : showList) {

            getShowResponseList.add(new ShowResponse(
                    show.getId(),
                    show.getMovieTitle(),
                    show.getCinemaName(),
                    show.getScreenName(),
                    show.getStart(),
                    show.getEnd()));
        }
        return getShowResponseList;
    }

}
