package org.tpokora.persistance.entity.weather;

import org.tpokora.common.utils.DateUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "FORECAST")
public class ForecastEntity {

    @Id
    @SequenceGenerator(name="forecast_id_seq",
            sequenceName="forecast_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forecast_id_seq")
    @Column(name = "ID")
    private int id;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "TEMP", nullable = false)
    private double temp;

    @Column(name = "FEELTEMP", nullable = false)
    private double feelTemp;

    @Column(name = "MINTEMP", nullable = false)
    private double minTemp;

    @Column(name = "MAXTEMP", nullable = false)
    private double maxTemp;

    @Column(name = "PRESSURE", nullable = false)
    private int pressure;

    @Column(name = "HUMIDITY", nullable = false)
    private int humidity;

    @Column(name = "WIND", nullable = false)
    private double wind;

    @Column(name = "RAIN1H")
    private double rain1h;

    @Column(name = "RAIN3H")
    private double rain3h;

    @Column(name = "LONGITUDE", nullable = false)
    private double longitude;

    @Column(name = "LATITUDE", nullable = false)
    private double latitude;

    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDateTime timestamp;

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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ForecastEntity{" +
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
                ", timestamp=" + DateUtils.parseDateToString(timestamp) +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
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
        private LocalDateTime timestamp;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.name = description;
            return this;
        }

        public Builder temp(double temp) {
            this.temp = temp;
            return this;
        }

        public Builder feelTemp(double feelTemp) {
            this.feelTemp = feelTemp;
            return this;
        }

        public Builder minTemp(double minTemp) {
            this.minTemp = minTemp;
            return this;
        }

        public Builder maxTemp(double maxTemp) {
            this.maxTemp = maxTemp;
            return this;
        }

        public Builder pressure(int pressure) {
            this.pressure = pressure;
            return this;
        }

        public Builder humidity(int humidity) {
            this.humidity = humidity;
            return this;
        }

        public Builder wind(double wind) {
            this.wind = wind;
            return this;
        }

        public Builder rain1h(double rain1h) {
            this.rain1h = wind;
            return this;
        }

        public Builder rain3h(double rain3h) {
            this.rain3h = rain3h;
            return this;
        }

        public Builder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        // Build using setters
        public ForecastEntity build() {
            ForecastEntity forecastEntity = new ForecastEntity();
            forecastEntity.setId(this.id);
            forecastEntity.setLocation(this.location);
            forecastEntity.setName(this.name);
            forecastEntity.setDescription(this.description);
            forecastEntity.setTemp(this.temp);
            forecastEntity.setFeelTemp(this.feelTemp);
            forecastEntity.setMinTemp(this.minTemp);
            forecastEntity.setMaxTemp(this.maxTemp);
            forecastEntity.setPressure(this.pressure);
            forecastEntity.setHumidity(this.humidity);
            forecastEntity.setWind(this.wind);
            forecastEntity.setRain1h(this.rain1h);
            forecastEntity.setRain3h(this.rain3h);
            forecastEntity.setLongitude(this.longitude);
            forecastEntity.setLatitude(this.latitude);
            forecastEntity.setTimestamp(this.timestamp);

            return forecastEntity;
        }
    }
}
