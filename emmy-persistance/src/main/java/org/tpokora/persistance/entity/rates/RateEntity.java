package org.tpokora.persistance.entity.rates;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="RATE")
public class RateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "RATE_FROM")
    private String from;

    @Column(name = "RATE_TO")
    private String to;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "TIMESTAMP", nullable = false)
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
        this.id = id;
        this.name = name;
        this.from = from;
        this.to = to;
        this.value = value;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
