package org.tpokora.storms.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "WARNING")
public class WarningEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LEVEL", nullable = false)
    private int level;

    @Column(name = "TO", nullable = false)
    private LocalDateTime to;

    @Column(name = "FROM", nullable = false)
    private LocalDateTime from;

    public WarningEntity() {}

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public static WarningEntity valueOf(Warning warning) {
        WarningEntity warningEntity = new WarningEntity();
        warningEntity.setName(warning.getName());
        warningEntity.setLevel(warning.getLevel());
        warningEntity.setFrom(warning.getPeriod().getFrom());
        warningEntity.setTo(warning.getPeriod().getTo());
        return warningEntity;
    }

    @Override
    public String toString() {
        return "WarningEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", to=" + to.format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT)) +
                ", from=" + from.format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT)) +
                '}';
    }
}
