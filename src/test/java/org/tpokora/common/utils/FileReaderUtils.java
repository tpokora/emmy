package org.tpokora.common.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderUtils {

    public static String fileToString(String path) {
        ClassLoader classLoader = FileReaderUtils.class.getClassLoader();

        String readString = null;
        try {
            readString = Files.readString(Path.of(classLoader.getResource(path).toURI()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return readString;
    }
}
