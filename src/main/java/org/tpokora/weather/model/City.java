package org.tpokora.weather.model;

public class City {
    private String name;
    private Coordinates coordinates;

    public City() {
        this.coordinates = new Coordinates();
        this.name = "";
    }

    public City(Double longitude, Double latitude) {
        this.coordinates = new Coordinates();
        this.coordinates.setLongitude(longitude);
        this.coordinates.setLatitude(latitude);
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
