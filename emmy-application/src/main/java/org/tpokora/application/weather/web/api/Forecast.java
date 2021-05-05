package org.tpokora.application.weather.web.api;

public class Forecast {

    private int id;
    private String location;
    private String name;
    private String description;
    private double temp;
    private double feelTemp;
    private double minTemp;
    private double maxTemp;
    private int pressure;
    private int humidity;
    private double wind;
    private double rain1h;
    private double rain3h;
    private double longitude;
    private double latitude;
    private String timestamp;

    public Forecast(int id, String location, String name, String description, double temp, double feelTemp,
                    double minTemp, double maxTemp, int pressure, int humidity, double wind, double rain1h,
                    double rain3h, double longitude, double latitude, String timestamp) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.description = description;
        this.temp = temp;
        this.feelTemp = feelTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind = wind;
        this.rain1h = rain1h;
        this.rain3h = rain3h;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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

    public double getRain1h() {
        return rain1h;
    }

    public void setRain1h(double rain1h) {
        this.rain1h = rain1h;
    }

    public double getRain3h() {
        return rain3h;
    }

    public void setRain3h(double rain3h) {
        this.rain3h = rain3h;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", temp=" + temp +
                ", feelTemp=" + feelTemp +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", wind=" + wind +
                ", rain1h=" + rain1h +
                ", rain3h=" + rain3h +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
