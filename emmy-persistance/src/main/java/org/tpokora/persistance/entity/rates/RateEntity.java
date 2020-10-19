package org.tpokora.persistance.entity.rates;

import org.tpokora.persistance.entity.common.AbstractEntity;

import java.time.LocalDateTime;

public class RateEntity extends AbstractEntity {

    private String from;
    private String to;
    private Double value;
    private LocalDateTime timestamp;

    public RateEntity() {}

    public RateEntity(String from, String to, Double value, LocalDateTime timestamp) {
        this.from = from;
        this.to = to;
        this.value = value;
        this.timestamp = timestamp;
    }

    public RateEntity(Integer id, String from, String to, Double value, LocalDateTime timestamp) {
        super(id);
        this.from = from;
        this.to = to;
        this.value = value;
        this.timestamp = timestamp;
    }
}
