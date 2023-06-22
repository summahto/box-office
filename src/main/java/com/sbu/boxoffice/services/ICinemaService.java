package com.sbu.boxoffice.services;

public interface ICinemaService {

    int getTotalSeats(String cinemaId);

    int getTotalSeatsByScreenID(String cinemnaId, String screenId);

}
