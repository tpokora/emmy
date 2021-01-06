package org.tpokora.application.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileReaderUtils {

    public static String fileToString(String path) {
        ClassLoader classLoader = FileReaderUtils.class.getClassLoader();

        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = classLoader.getResourceAsStream(path);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Test
    void loadJSONTest() {
        String fileName = "weather/location/openCageDataResponse.json";

        String fileContent = fileToString(fileName);
        Assertions.assertNotNull(fileContent, "File is empty!");
    }
}
