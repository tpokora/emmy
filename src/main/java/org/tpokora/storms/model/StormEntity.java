package org.tpokora.storms.model;


import org.tpokora.common.utils.DateUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="STORM")
public class StormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "CITY_NAME", nullable = false)
    private String cityName;

    @Column(name = "X", nullable = false)
    private String x;

    @Column(name = "Y", nullable = false)
    private String y;

    @Column(name = "AMOUNT", nullable = false)
    protected int amount;

    @Column(name = "DISTANCE", nullable = false)
    protected double distance;

    @Column(name = "DIRECTION", nullable = false)
    protected String direction;

    @Column(name = "TIME", nullable = false)
    protected int time;

    @Column(name = "TIMESTAMP", nullable = false)
    protected LocalDateTime timestamp;

    public StormEntity() {
    }

    public StormEntity(String cityName, String x, String y, int amount, double distance, String direction, int time) {
        initializeFields(cityName, x, y, amount, distance, direction, time, LocalDateTime.now());
    }

    private void initializeFields(String cityName, String x, String y, int amount, double distance, String direction, int time, LocalDateTime timestamp) {
        this.cityName = cityName;
        this.x = x;
        this.y = y;
        this.amount = amount;
        this.distance = distance;
        this.direction = direction;
        this.time = time;
        this.timestamp = timestamp;
    }

    public StormEntity(String cityName, String x, String y, StormResponse stormResponse) {
        initializeFields(cityName, x, y, stormResponse.getAmount(), stormResponse.getDistance(), stormResponse.getDirection(), stormResponse.getTime(), LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
    
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private String cityName;
        private String x;
        private String y;
        private int amount;
        private double distance;
        private String direction;
        private int time;
        private LocalDateTime timestamp = LocalDateTime.now();

        public Builder cityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder x(String x) {
            this.x = x;
            return this;
        }

        public Builder y(String y) {
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
            this.direction = direction;
            return this;
        }
        
        public Builder time(int time) {
            this.time = time;
            return this;
        }
        
        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        
        public StormEntity build() {
            StormEntity stormEntity = new StormEntity();
            stormEntity.id = this.id;
            stormEntity.cityName = this.cityName;
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
                ", cityName='" + cityName + '\'' +
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
