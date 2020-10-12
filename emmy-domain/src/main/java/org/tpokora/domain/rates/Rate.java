package org.tpokora.domain.rates;

import org.tpokora.common.utils.DateUtils;

import java.time.LocalDateTime;

public class Rate {
    private String name;
    private LocalDateTime timestamp;
    private Double value;
    private String currency;

    public Rate() { }

    public Rate(String name, LocalDateTime timestamp, Double value, String currency) {
        this.name = name;
        this.timestamp = timestamp;
        this.value = value;
        this.currency = currency;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "name='" + name + '\'' +
                ", timestamp=" + DateUtils.parseDateToString(timestamp) +
                ", value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}
