package org.tpokora.storms.model;

public class StormRequest {
    private Coordinates coordinates;
    private float distance;
    private int time;

    public StormRequest() {
        this.coordinates = new Coordinates();
        this.distance = 0;
        this.time = 0;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "StormRequest{" +
                "x=" + coordinates.getX() +
                ", y=" + coordinates.getY() +
                ", distance=" + distance +
                ", time=" + time +
                '}';
    }
}