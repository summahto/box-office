package com.sbu.boxoffice.services;

import java.util.Map;

import com.sbu.boxoffice.entities.Cinema;
import com.sbu.boxoffice.entities.Screen;
import com.sbu.boxoffice.repositories.ICinemaRepository;

public class CinemaService implements ICinemaService {

    private final ICinemaRepository cinemaRepository;

    public CinemaService(ICinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public int getTotalSeats(String cinemaId) {

        int totalSeats = 0;
        Cinema cinema = cinemaRepository.getCinemaById(cinemaId);

        for (Map.Entry<String, Screen> entry : cinema.getScreenMap().entrySet()) {
            totalSeats += entry.getValue().getSeatList().size();
        }

        return totalSeats;

    }

    public int getTotalSeatsByScreenID(String cinemnaId, String screenId) {
        return cinemaRepository.getCinemaById(cinemnaId).getScreenMap().get(screenId).getSeatList().size();
    }

}
