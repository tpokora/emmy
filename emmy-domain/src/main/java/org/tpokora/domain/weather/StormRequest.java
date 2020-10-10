package org.tpokora.domain.weather;

public class StormRequest {
    private Coordinates coordinates;
    private double distance;
    private int time;

    public StormRequest() {
        this.coordinates = new Coordinates();
        this.distance = 0;
        this.time = 0;
    }

    public StormRequest(Coordinates coordinates, double distance, int time) {
        this.coordinates = coordinates;
        this.distance = distance;
        this.time = time;
    }

    public StormRequest(double longitude, double latitude, double distance, int time) {
        this.coordinates = new Coordinates(longitude, latitude);
        this.distance = distance;
        this.time = time;
    }


    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setLongitude(double longitude) {
        this.coordinates.setLongitude(longitude);
    }

    public double getLongitude() {
        return this.coordinates.getLongitude();
    }

    public void setLatitude(double latitude) {
        this.coordinates.setLongitude(latitude);
    }

    public double getLatitude() {
        return this.coordinates.getLatitude();
    }

    public double getLongitudeDM() {
        return this.coordinates.getLongitudeDM();
    }

    public double getLatitudeDM() {
        return this.coordinates.getLatitudeDM();
    }

    @Override
    public String toString() {
        return "StormRequest{" +
                "coordinates=" + coordinates +
                ", distance=" + distance +
                ", time=" + time +
                '}';
    }
}
