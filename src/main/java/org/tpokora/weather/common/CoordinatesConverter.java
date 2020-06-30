package org.tpokora.weather.common;

public class CoordinatesConverter {

    private CoordinatesConverter() {}

    public static double convertDecimalDegreeToDM(double input) {
        int wholeNumberPart = (int) input;
        double fracture = input - wholeNumberPart;
        int minutesFromFracture = (int)(fracture * 60);
        return Double.parseDouble(String.format("%s.%s", wholeNumberPart, minutesFromFracture));
    }
}
