package ru.job4j.cars.model;

public enum Transmission {
    AUTOMATIC("Automatic"),
    MANUAL("Manual");

    private final String value;

    Transmission(String value) {
        this.value = value;
    }

    public String getName() {
        return value;
    }
}
