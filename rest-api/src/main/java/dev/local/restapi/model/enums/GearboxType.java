package dev.local.restapi.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GearboxType {
    MANUAL("manual"),
    AUTOMATIC("automatic"),
    SEMI_AUTOMATIC("semi-automatic");

    private final String value;

    GearboxType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static GearboxType fromValue(String value) {
        for (GearboxType gearboxType : values()) {
            if (gearboxType.value.equalsIgnoreCase(value)) {
                return gearboxType;
            }
        }
        throw new IllegalArgumentException("Invalid gearbox type: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
