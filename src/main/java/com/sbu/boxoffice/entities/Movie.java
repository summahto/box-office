package com.sbu.boxoffice.entities;

import java.util.Objects;

import com.sbu.boxoffice.utils.IdGenerator;

public class Movie extends BaseEntity {

    private final String title;
    private final int durationInMins;

    public Movie(String id, String title, int durationInMins) {
        this.id = id;
        this.title = title;
        this.durationInMins = durationInMins;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationInMins() {
        return durationInMins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Movie))
            return false;
        Movie movie = (Movie) o;
        return getId().equals(movie.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Movie [title=" + title + ", durationInMins=" + durationInMins + "]";
    }

}
