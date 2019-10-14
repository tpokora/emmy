package org.tpokora.config.properties;

public enum PropertiesEnum {

    FIREBASE("firebase"),
    NOTIFICATIONS("notifications"),
    STORM("storm");

    private String value;

    PropertiesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
