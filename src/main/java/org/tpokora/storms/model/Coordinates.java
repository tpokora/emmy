package org.tpokora.storms.model;

public class Coordinates {
    
    private Double x;
    private Double y;
    
    public Coordinates() {
        this.setX(0.0);
        this.setY(0.0);
    }

    public Coordinates(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
