package org.tpokora.config.model;

import org.tpokora.config.properties.PropertiesEnum;

public class Property {
    private String property;
    private String value;
    private PropertiesEnum type;

    public Property() {}

    public Property(String property, String value, PropertiesEnum type) {
        this.property = property;
        this.value = value;
        this.type = type;
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

    public PropertiesEnum getType() {
        return type;
    }

    public void setType(PropertiesEnum type) {
        this.type = type;
    }
}