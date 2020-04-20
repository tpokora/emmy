package org.tpokora.storms.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Warning {
    private String name;
    private int level;
    protected Period period;

    public Warning() {}

    public Warning(String name) {
        this.name = name;
    }

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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private int level;
        protected Period period;

        public Builder name(String name) {
            Objects.requireNonNull(this.name, "Name cannot be null!");
            this.name = name;
            return this;
        }

        public Builder level(int level) {
            this.level = level;
            return this;
        }

        public Builder period(Period period) {
            Objects.requireNonNull(this.period, "Period cannot be null!");
            this.period = period;
            return this;
        }

        public static Builder builder() {
            return new Builder();
        }

        public Warning build() {
            Warning warning = new Warning();
            warning.name = this.name;
            warning.level = this.level;
            warning.period = this.period;

            return warning;
        }
    }
}
