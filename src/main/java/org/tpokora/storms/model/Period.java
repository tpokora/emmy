package org.tpokora.storms.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Period {
    private LocalDateTime from;
    private LocalDateTime to;

    public Period(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public Period(String from, String to, String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public String getFromString() {
        if (from != null) {
            return from.format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT));
        }
        return null;
    }

    public String getToString() {
        if (to != null) {
            return to.format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT));
        }
        return null;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Period{" +
                "from=" + from.format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT)) +
                ", to=" + to.format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT)) +
                '}';
    }
}
