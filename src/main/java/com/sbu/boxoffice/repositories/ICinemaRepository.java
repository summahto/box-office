package com.sbu.boxoffice.repositories;

import com.sbu.boxoffice.entities.Cinema;

public interface ICinemaRepository {
    Cinema getCinemaById(String id);

    void saveCinema(Cinema cinema);

    void updateCinema(Cinema cinema);
}
