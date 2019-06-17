package org.tpokora.storms;

public class StormRequest {
    private String x;
    private String y;
    private float distance;
    private int time;

    public StormRequest() {
        this.x = "";
        this.y = "";
        this.distance = 0;
        this.time = 0;
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

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "StormRequest{" +
                "x=" + x +
                ", y=" + y +
                ", distance=" + distance +
                ", time=" + time +
                '}';
    }
}
