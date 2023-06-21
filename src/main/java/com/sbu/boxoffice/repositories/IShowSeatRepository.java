package com.sbu.boxoffice.repositories;

import java.util.List;

import com.sbu.boxoffice.entities.Seat;
import com.sbu.boxoffice.entities.Show;
import com.sbu.boxoffice.entities.ShowSeat;

public interface IShowSeatRepository {
    List<ShowSeat> getShowSeatsByShowId(String id);

    ShowSeat getShowSeat(String showId, String seatId);

    void addShowSeats(Show show, List<Seat> seatList);

    void updateShowSeat(ShowSeat showSeat);
}
