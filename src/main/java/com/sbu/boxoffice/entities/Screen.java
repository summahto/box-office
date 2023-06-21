package com.sbu.boxoffice.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Screen extends BaseEntity {

    private final String name;
    private final List<Seat> seatList;

    public Screen(String id, String name) {
        this.id = id;
        this.name = name;
        this.seatList = new ArrayList<>();

        // addInitialSeats(seatList);

    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Screen))
            return false;
        Screen screen = (Screen) o;
        return getId().equals(screen.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Screen [name=" + name + ", seatList=" + seatList + "]";
    }

    public void addSeat(Seat seat) {
        seatList.add(seat);
    }

}
