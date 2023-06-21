package com.sbu.boxoffice.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileUtils {

    public Map<String, String> loadFileAsResource(String filename) throws IOException {

        Properties properties = new Properties();
        Map<String, String> propertyMap = new HashMap<>();

        try (InputStream inputStream = getClass().getResourceAsStream(filename)) {

            properties.load(inputStream);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return propertyMap;
    }

}
