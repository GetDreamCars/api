package dev.local.restapi.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FuelType {
    PETROL("petrol"),
    DIESEL("diesel"),
    ELECTRIC("electric"),
    HYBRID("hybrid");

    private final String value;

    FuelType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static FuelType fromValue(String value) {
        for (FuelType fuelType : values()) {
            if (fuelType.value.equalsIgnoreCase(value)) {
                return fuelType;
            }
        }
        throw new IllegalArgumentException("Invalid fuel type: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
