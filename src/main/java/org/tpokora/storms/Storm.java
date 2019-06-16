package org.tpokora.storms;

public class Storm {
    private int x;
    private int y;
    private int quantity;
    private float distance;
    private String direction;
    private int time;

    public Storm() {
        this.x = 0;
        this.y = 0;
        this.quantity = 0;
        this.distance = 0;
        this.direction = "";
        this.time = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
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

    @Override
    public String toString() {
        return "Storm{" +
                "quantity=" + quantity +
                ", distance=" + distance +
                ", direction=" + direction +
                ", time=" + time +
                '}';
    }
}
