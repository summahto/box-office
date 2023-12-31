package com.sbu.boxoffice.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sbu.boxoffice.entities.Seat;
import com.sbu.boxoffice.entities.Show;
import com.sbu.boxoffice.entities.ShowSeat;

public class ShowSeatRepository implements IShowSeatRepository {

    private final Map<String, ShowSeat> showSeatMap;

    public ShowSeatRepository() {
        showSeatMap = new HashMap<>();
    }

    public ShowSeatRepository(Map<String, ShowSeat> showSeatMap) {
        this.showSeatMap = showSeatMap;
    }

    @Override
    public List<ShowSeat> getShowSeatsByShowId(String id) {

        // System.out.println(showSeatMap);
        return showSeatMap.values()
                .stream()
                .filter(s -> {
                    String showId = s.getId().split("#")[0];
                    return id.equals(showId);
                })
                .collect(Collectors.toList());
    }

    @Override
    public ShowSeat getShowSeat(String showId, String seatId) {
        String id = showId + "#" + seatId;
        return showSeatMap.get(id);
    }

    @Override
    public void addShowSeats(Show show, List<Seat> seatList) {
        seatList.stream()
                .forEach(seat -> {
                    String id = show.getId() + "#" + seat.getId();
                    showSeatMap.put(id, new ShowSeat(id, seat, show));
                });
    }

    @Override
    public void updateShowSeat(ShowSeat showSeat) {
        showSeatMap.put(showSeat.getId(), showSeat);
    }
}
