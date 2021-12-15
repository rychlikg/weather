package pl.rychlik.weather.model;

import lombok.Getter;

public enum WeatherUnit {
    //standard, metric and imperial
    STANDARD("standard"),METRIC("metric"),IMPERIAL("imperial");
    @Getter
    private final String label;

    WeatherUnit(String label) {
        this.label = label;
    }
}
