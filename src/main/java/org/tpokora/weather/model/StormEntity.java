package org.tpokora.weather.model;


import org.tpokora.common.utils.DateUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "STORM")
public class StormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "LONGITUDE", nullable = false)
    private double longitude;

    @Column(name = "LATITUDE", nullable = false)
    private double latitude;

    @Column(name = "AMOUNT", nullable = false)
    private int amount;

    @Column(name = "DISTANCE", nullable = false)
    private double distance;

    @Column(name = "DIRECTION", nullable = false)
    private String direction;

    @Column(name = "TIME", nullable = false)
    private int time;

    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDateTime timestamp;

    public StormEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private double longitude;
        private double latitude;
        private int amount;
        private double distance;
        private String direction;
        private int time;
        private LocalDateTime timestamp = LocalDateTime.now();

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder longitude(double longitude) {
            Objects.requireNonNull(longitude, "Longitude can't be null!!");
            this.longitude = longitude;
            return this;
        }

        public Builder latitude(double latitude) {
            Objects.requireNonNull(latitude, "Latitude can't be null!!");
            this.latitude = latitude;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder distance(double distance) {
            this.distance = distance;
            return this;
        }

        public Builder direction(String direction) {
            Objects.requireNonNull(direction, "Direction can't be null!!");
            this.direction = direction;
            return this;
        }

        public Builder time(int time) {
            this.time = time;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            Objects.requireNonNull(timestamp, "Timestamp can't be null!!");
            this.timestamp = timestamp;
            return this;
        }

        public StormEntity build() {
            StormEntity stormEntity = new StormEntity();
            stormEntity.id = this.id;
            stormEntity.longitude = this.longitude;
            stormEntity.latitude = this.latitude;
            stormEntity.amount = this.amount;
            stormEntity.distance = this.distance;
            stormEntity.direction = this.direction;
            stormEntity.time = this.time;
            stormEntity.timestamp = this.timestamp;

            return stormEntity;
        }
    }

    @Override
    public String toString() {
        return "StormEntity{" +
                "id=" + id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", amount=" + amount +
                ", distance=" + distance +
                ", direction='" + direction + '\'' +
                ", time=" + time +
                ", timestamp=" + DateUtils.parseDateToString(timestamp) +
                '}';
    }
}
