package com.sbu.boxoffice.repositories.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sbu.boxoffice.entities.Cinema;
import com.sbu.boxoffice.entities.Movie;
import com.sbu.boxoffice.entities.Screen;
import com.sbu.boxoffice.entities.Seat;
import com.sbu.boxoffice.entities.Show;
import com.sbu.boxoffice.repositories.ICinemaRepository;
import com.sbu.boxoffice.repositories.IMovieRepository;
import com.sbu.boxoffice.repositories.IShowRepository;
import com.sbu.boxoffice.repositories.IShowSeatRepository;

public class ShowData implements IData {

    private final IShowRepository iShowRepository;
    private final ICinemaRepository iCinemaRepository;
    private final IMovieRepository iMovieRepository;
    private final IShowSeatRepository iShowSeatRepository;

    public ShowData(IShowRepository iShowRepository, ICinemaRepository iCinemaRepository,
            IMovieRepository iMovieRepository, IShowSeatRepository iShowSeatRepository) {
        this.iShowRepository = iShowRepository;
        this.iCinemaRepository = iCinemaRepository;
        this.iMovieRepository = iMovieRepository;
        this.iShowSeatRepository = iShowSeatRepository;
    }

    @Override
    public void loadData(String dataPath, String delimiter) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(dataPath));
            String line = reader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(delimiter));
                addShow(
                        tokens.get(0),
                        tokens.get(1),
                        tokens.get(2),
                        tokens.get(3),
                        new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(tokens.get(4)),
                        new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(tokens.get(5)));
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    public void addShow(String showId, String movieId, String cinemaId, String screenId, Date start, Date end) {

        Cinema cinema = iCinemaRepository.getCinemaById(cinemaId);
        Screen screen = cinema.getScreenById(screenId);
        Movie movie = iMovieRepository.getMovieById(movieId);
        iShowRepository.saveShow(new Show(showId, start, end, screen, cinema, movie));
        Show show = iShowRepository.getShowById(showId);
        List<Seat> seatList = screen.getSeatList();
        iShowSeatRepository.addShowSeats(show, seatList);
    }

}
