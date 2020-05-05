package org.tpokora.storms.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Warning {
    private String name;
    private int level;
    protected Period period;

    private Warning() {}

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Period getPeriod() {
        return period;
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
            Objects.requireNonNull(name, "Name cannot be null!");
            this.name = name;
            return this;
        }

        public Builder level(int level) {
            this.level = level;
            return this;
        }

        public Builder period(LocalDateTime from, LocalDateTime to) {
            Objects.requireNonNull(from, "LocalDateTime from is null!");
            Objects.requireNonNull(to, "LocalDateTime to is null!");
            this.period = new Period(from, to);
            return this;
        }

        public Builder period(Period period) {
            Objects.requireNonNull(period, "Period cannot be null!");
            this.period = period;
            return this;
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
