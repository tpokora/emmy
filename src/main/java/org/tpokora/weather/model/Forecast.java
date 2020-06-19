package org.tpokora.weather.model;

import java.io.Serializable;

public class Forecast implements Serializable {

    private String name;
    private String description;
    private double temp;
    private double feelTemp;
    private double minTemp;
    private double maxTemp;
    private int pressure;
    private int humidity;
    private double wind;
    private double longitude;
    private double latitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelTemp() {
        return feelTemp;
    }

    public void setFeelTemp(double feelTemp) {
        this.feelTemp = feelTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", temp=" + temp +
                ", feelTemp=" + feelTemp +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", wind=" + wind +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
