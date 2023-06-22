package com.sbu.boxoffice.entities;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Cinema extends BaseEntity {

    private final String name;
    private final Map<String, Screen> screenMap;

    public Cinema(String id, String name) {
        this.id = id;
        this.name = name;
        this.screenMap = new TreeMap<>();
    }

    public void addScreen(Screen screen) {
        screenMap.put(screen.getId(), screen);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Screen getScreenByName(String screenName) {
        return screenMap.values()
                .stream().filter(screen -> screenName.equals(screen.getName()))
                .findAny()
                .orElse(null);
    }

    public Screen getScreenById(String screenId) {
        return screenMap.get(screenId);
    }

    public Map<String, Screen> getScreenMap() {
        return screenMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Cinema))
            return false;
        Cinema cinema = (Cinema) o;
        return getId().equals(cinema.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Cinema [name=" + name + ", screenMap=" + screenMap + "]";
    }

}
