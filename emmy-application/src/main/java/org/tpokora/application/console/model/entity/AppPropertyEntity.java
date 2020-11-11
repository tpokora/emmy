package org.tpokora.application.console.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "APP_CONFIG", schema = "public",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"PROPERTY"}))
public class AppPropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name="PROPERTY")
    private String property;

    @Column(name="VALUE")
    private String value;

    @Column(name="DESCRIPTION")
    private String description;

    public AppPropertyEntity() {
    }

    public AppPropertyEntity(String property, String value, String description) {
        this.property = property;
        this.value = value;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AppPropertyEntity{" +
                "id=" + id +
                ", property='" + property + '\'' +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}