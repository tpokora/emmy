package org.tpokora.weather.model;

import org.tpokora.weather.common.CoordinatesConverter;

public class Coordinates {
    
    private Double longitude;
    private Double latitude;
    private Double longitudeDM;
    private Double latitudeDM;
    
    public Coordinates() {
        this.setLongitude(0.0);
        this.setLatitude(0.0);
    }

    public Coordinates(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.longitudeDM = CoordinatesConverter.convertDecimalDegreeToDM(this.longitude);
        this.latitudeDM = CoordinatesConverter.convertDecimalDegreeToDM(this.longitude);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", longitudeDM=" + longitudeDM +
                ", latitudeDM=" + latitudeDM +
                '}';
    }
}
