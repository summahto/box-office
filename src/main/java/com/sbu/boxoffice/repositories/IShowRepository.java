package com.sbu.boxoffice.repositories;

import java.util.List;

import com.sbu.boxoffice.entities.Show;

public interface IShowRepository {
    List<Show> getAllShowsForMovieName(String title);

    List<Show> getAllShows();

    Show getShowById(String id);

    void saveShow(Show show);
}
