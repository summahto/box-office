package com.sbu.boxoffice.entities;

import java.util.Objects;

import com.sbu.boxoffice.utils.IdGenerator;

public class Seat extends BaseEntity {

    private final int seatRow;
    private final int seatColumn;

    public Seat(String id, int seatRow, int seatColumn) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.id = id;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Seat))
            return false;
        Seat seat = (Seat) o;
        return getId().equals(seat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Seat [seatRow=" + seatRow + ", seatColumn=" + seatColumn + "]";
    }

}
