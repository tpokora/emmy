package org.tpokora.storms.model;


import org.tpokora.common.utils.DateUtils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class StormEntity extends StormResponse {

    private String cityName;
    private String x;
    private String y;

    public StormEntity() {
    }

    public StormEntity(String cityName, String x, String y, int amount, double distance, String direction, int time) {
        super(amount, distance, direction, time);
        initializeFields(cityName, x, y);
    }

    public StormEntity(String cityName, String x, String y, StormResponse stormResponse) {
        super(stormResponse.getAmount(), stormResponse.getDistance(), stormResponse.getDirection(), stormResponse.getTime());
        initializeFields(cityName, x, y);
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

    private void initializeFields(String cityName, String x, String y) {
        this.cityName = cityName;
        this.x = x;
        this.y = y;
        timestamp = LocalDateTime.now();
    }
    
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String cityName;
        private String x;
        private String y;
        private int amount;
        private double distance;
        private String direction;
        private int time;
        protected LocalDateTime timestamp;

        public Builder cityName(String cityName) {
            this.cityName = cityName;
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
                "cityName='" + cityName + '\'' +
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
