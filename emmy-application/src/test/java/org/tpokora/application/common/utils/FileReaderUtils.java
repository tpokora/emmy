package org.tpokora.application.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FileReaderUtils {

    public static String fileToString(String path) {
        ClassLoader classLoader = FileReaderUtils.class.getClassLoader();

        String readString = null;
        try {
            readString = Files.readString(Path.of(Objects.requireNonNull(classLoader.getResource(path)).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return readString;
    }

    @Test
    void loadJSONTest() {
        String fileName = "weather/location/openCageDataResponse.json";

        String fileContent = fileToString(fileName);
        Assertions.assertNotNull(fileContent, "File is empty!");
    }
}
