package com.sbu.boxoffice.repositories.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.sbu.boxoffice.entities.Cinema;
import com.sbu.boxoffice.entities.Screen;
import com.sbu.boxoffice.repositories.ICinemaRepository;

public class ScreenData implements IData {

    private final ICinemaRepository iCinemaRepository;

    public ScreenData(ICinemaRepository iCinemaRepository) {
        this.iCinemaRepository = iCinemaRepository;
    }

    @Override
    public void loadData(String dataPath, String delimiter) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(dataPath));
            String line = reader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(delimiter));
                addScreen(tokens.get(0), tokens.get(1), tokens.get(2));
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addScreen(String screenId, String screenName, String cinemaId) {
        Cinema cinema = iCinemaRepository.getCinemaById(cinemaId);
        cinema.addScreen(new Screen(screenId, screenName));
        iCinemaRepository.updateCinema(cinema);
    }
}
