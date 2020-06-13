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

    @Column(name = "END", nullable = false)
    private LocalDateTime end;

    @Column(name = "START", nullable = false)
    private LocalDateTime start;

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

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        end = end.minusNanos(end.getNano());
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        start = start.minusNanos(start.getNano());
        this.start = start;
    }

    public static WarningEntity valueOf(Warning warning) {
        WarningEntity warningEntity = new WarningEntity();
        warningEntity.setName(warning.getName());
        warningEntity.setLevel(warning.getLevel());
        warningEntity.setStart(warning.getPeriod().getFrom());
        warningEntity.setEnd(warning.getPeriod().getTo());
        return warningEntity;
    }

    public static WarningEntity valueOf(WarningEntity warningEntity) {
        WarningEntity newWarningEntity = new WarningEntity();
        newWarningEntity.setName(warningEntity.getName());
        newWarningEntity.setLevel(warningEntity.getLevel());
        newWarningEntity.setStart(warningEntity.getStart());
        newWarningEntity.setEnd(warningEntity.getEnd());
        return newWarningEntity;
    }

    @Override
    public String toString() {
        return "WarningEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", start=" + start.format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT)) +
                ", end=" + end.format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT)) +
                '}';
    }
}
