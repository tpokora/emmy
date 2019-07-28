package org.tpokora.storms.model;

public class Warning {

    private String name;
    private int level;
    protected Period period;


    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Warning{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", period=" + period +
                '}';
    }
}
