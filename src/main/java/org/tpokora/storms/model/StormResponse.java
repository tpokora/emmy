package org.tpokora.storms.model;

public class StormResponse {
    private int amount;
    private double distance;
    private String direction;
    private int time;

    public StormResponse() {
        this.amount = 0;
        this.distance = 0.0;
        this.direction = "";
        this.time = 0;
    }

    public StormResponse(int amount, double distance, String direction, int time) {
        this.amount = amount;
        this.distance = distance;
        this.direction = direction;
        this.time = time;
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
}
