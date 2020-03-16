package org.explorer.commandcenter.model;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum PlanetStatus {
    OK("OK"),
    NOT_OK("!OK"),
    TO_DO("TODO"),
    EN_ROUTE("En route");

    private String value;

    PlanetStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PlanetStatus find(String value) {
        return Arrays.stream(values()).filter(e -> e.getValue().equalsIgnoreCase(value)).findAny().orElseThrow(NoSuchElementException::new);
    }
}
