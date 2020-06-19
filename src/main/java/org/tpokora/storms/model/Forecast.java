package org.tpokora.storms.model;

import java.io.Serializable;

public class Forecast implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
