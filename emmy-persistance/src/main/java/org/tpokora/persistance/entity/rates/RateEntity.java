package org.tpokora.persistance.entity.rates;

import org.tpokora.persistance.entity.common.AbstractEntity;

import java.time.LocalDateTime;

public class RateEntity extends AbstractEntity {

    private String name;
    private String from;
    private String to;
    private Double value;
    private LocalDateTime timestamp;

    public RateEntity() {}

    public RateEntity(String name, String from, String to, Double value, LocalDateTime timestamp) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.value = value;
        this.timestamp = timestamp;
    }

    public RateEntity(Integer id, String name, String from, String to, Double value, LocalDateTime timestamp) {
        super(id);
        this.name = name;
        this.from = from;
        this.to = to;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "RateEntity{" +
                "name='" + name + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", value=" + value +
                ", timestamp=" + timestamp +
                ", id=" + id +
                '}';
    }
}
