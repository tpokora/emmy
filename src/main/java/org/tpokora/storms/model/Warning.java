package org.tpokora.storms.model;

import java.time.LocalDateTime;

public class Warning {

    private String name;
    private int level;
    protected Period period;

    public Warning() {}

    public Warning(String name, int level, Period period) {
        this.name = name;
        this.level = level;
        this.period = period;
    }

    public Warning(String name, int level, LocalDateTime from, LocalDateTime to) {
        this(name, level, new Period(from, to));
    }


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
