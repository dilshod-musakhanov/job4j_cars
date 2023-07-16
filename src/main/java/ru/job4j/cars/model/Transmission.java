package ru.job4j.cars.model;

import java.util.Arrays;

public enum Transmission {
    AUTOMATIC("Automatic"),
    MANUAL("Manual");

    private final String value;

    Transmission(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Transmission fromValue(String value) {
        return Arrays.stream(values())
                .filter(transmission -> transmission.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid transmission value: " + value));
    }
}
