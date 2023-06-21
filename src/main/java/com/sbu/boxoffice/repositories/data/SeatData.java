package com.sbu.boxoffice.repositories.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.sbu.boxoffice.entities.Cinema;
import com.sbu.boxoffice.entities.Screen;
import com.sbu.boxoffice.entities.Seat;
import com.sbu.boxoffice.repositories.ICinemaRepository;

public class SeatData implements IData {

    private final ICinemaRepository iCinemaRepository;

    public SeatData(ICinemaRepository iCinemaRepository) {
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
                addSeat(tokens.get(0), tokens.get(1), Integer.parseInt(tokens.get(2)), Integer.parseInt(tokens.get(3)));
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addSeat(String cinemaId, String screenId, int numRows, int numCols) {
        Cinema cinema = iCinemaRepository.getCinemaById(cinemaId);
        Screen screen = cinema.getScreenById(screenId);
        for (int i = 1; i <= numRows; i++) {
            for (int j = 1; j <= numCols; j++) {
                screen.addSeat(new Seat(i + "#" + j, i, j));
            }
        }
        cinema.addScreen(screen);
        iCinemaRepository.updateCinema(cinema);
    }
}
