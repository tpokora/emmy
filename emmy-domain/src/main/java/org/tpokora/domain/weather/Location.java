package org.tpokora.domain.weather;

public class Location {
    private String name;
    private Coordinates coordinates;

    public Location() {
        this.coordinates = new Coordinates();
        this.name = "";
    }

    public Location(Double longitude, Double latitude) {
        this.coordinates = new Coordinates(longitude, latitude);
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
        return "Location{" +
                "name='" + name + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
