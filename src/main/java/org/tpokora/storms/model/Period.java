package org.tpokora.storms.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Period {
    private LocalDateTime from;
    private LocalDateTime to;

    public Period(LocalDateTime from, LocalDateTime to) {
        Objects.requireNonNull(from, "LocalDateTime from is null!");
        Objects.requireNonNull(to, "LocalDateTime to is null!");
        this.from = from;
        this.to = to;
    }

    public Period(String from, String to, String dateFormat) {
        Objects.requireNonNull(from, "String from is null!");
        Objects.requireNonNull(to, "String to is null!");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public String getFromString() {
        return from.format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT));
    }

    public String getToString() {
        return to.format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT));
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Period{" +
                "from=" + from.format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT)) +
                ", to=" + to.format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT)) +
                '}';
    }
}
