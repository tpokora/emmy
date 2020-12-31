package org.tpokora.domain.rates;

import org.tpokora.common.utils.DateUtils;

import java.time.LocalDateTime;

public class Rate {
    private String name;
    private LocalDateTime timestamp;
    private Double value;
    private String from;
    private String to;

    public Rate() { }

    public Rate(String name, LocalDateTime timestamp, Double value, String from, String to) {
        this.name = name;
        this.timestamp = timestamp;
        this.value = value;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "name='" + name + '\'' +
                ", timestamp=" + DateUtils.parseDateToString(timestamp) +
                ", value=" + value +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
