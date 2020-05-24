package org.tpokora.storms.model;

import org.tpokora.common.utils.DateUtils;

import java.time.LocalDateTime;

public class StormResponse {
    private int amount;
    private double distance;
    private String direction;
    private int time;
    private LocalDateTime timestamp;

    public StormResponse() {
        this.amount = 0;
        this.distance = 0.0;
        this.direction = "";
        this.time = 0;
        this.timestamp = LocalDateTime.now();
    }

    public StormResponse(int amount, double distance, String direction, int time) {
        this.amount = amount;
        this.distance = distance;
        this.direction = direction;
        this.time = time;
        this.timestamp = LocalDateTime.now();
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

    @Override
    public String toString() {
        return "StormResponse{" +
                "amount=" + amount +
                ", distance=" + distance +
                ", direction='" + direction + '\'' +
                ", time=" + time +
                ", timestamp=" + DateUtils.parseDateToString(timestamp) +
                '}';
    }
}
