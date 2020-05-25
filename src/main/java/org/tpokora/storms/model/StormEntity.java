package org.tpokora.storms.model;


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

    @Column(name = "X", nullable = false)
    private String x;

    @Column(name = "Y", nullable = false)
    private String y;

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

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
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
        private String x;
        private String y;
        private int amount;
        private double distance;
        private String direction;
        private int time;
        private LocalDateTime timestamp = LocalDateTime.now();

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder x(String x) {
            Objects.requireNonNull(x, "X can't be null!!");
            this.x = x;
            return this;
        }

        public Builder y(String y) {
            Objects.requireNonNull(y, "Y can't be null!!");
            this.y = y;
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
            stormEntity.x = this.x;
            stormEntity.y = this.y;
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
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", amount=" + amount +
                ", distance=" + distance +
                ", direction='" + direction + '\'' +
                ", time=" + time +
                ", timestamp=" + DateUtils.parseDateToString(timestamp) +
                '}';
    }
}
