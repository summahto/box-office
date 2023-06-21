package com.sbu.boxoffice.utils;

import java.util.HashMap;
import java.util.Map;

import com.sbu.boxoffice.entities.Cinema;
import com.sbu.boxoffice.entities.Customer;
import com.sbu.boxoffice.entities.Movie;
import com.sbu.boxoffice.entities.Screen;
import com.sbu.boxoffice.entities.Ticket;

public class IdGenerator {

    private static Map<Class<?>, Integer> counters = new HashMap<>();

    public static String generateId(Class<?> entityType) {

        int counter = counters.getOrDefault(entityType, 0) + 1;
        counters.put(entityType, counter);
        String prefix = getPrefix(entityType);
        return prefix + String.format("%04d", counter);
    }

    private static String getPrefix(Class<?> entityType) {

        if (entityType == Customer.class) {
            return "CUS";
        } else if (entityType == Movie.class) {
            return "MOV";
        } else if (entityType == Screen.class) {
            return "SCR";
        } else if (entityType == Ticket.class) {
            return "TKT";
        } else if (entityType == Cinema.class) {
            return "CNMA";
        }

        return "UNKNOWN";
    }
}
