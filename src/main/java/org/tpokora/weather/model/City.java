package org.tpokora.weather.model;

public class City {
    private String name;
    private Coordinates coordinates;

    public City() {
        this.coordinates = new Coordinates();
        this.name = "";
    }

    public City(Double x, Double y) {
        this.coordinates = new Coordinates();
        this.coordinates.setLongitude(x);
        this.coordinates.setLatitude(y);
        this.name = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
