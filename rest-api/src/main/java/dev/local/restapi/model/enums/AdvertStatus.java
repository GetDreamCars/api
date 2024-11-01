package dev.local.restapi.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AdvertStatus {
    ACTIVE("active"),
    INACTIVE("inactive"),
    PAUSED("paused");

    private final String value;

    AdvertStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static AdvertStatus fromValue(String value) {
        for (AdvertStatus advStatus : values()) {
            if (advStatus.value.equalsIgnoreCase(value)) {
                return advStatus;
            }
        }
        throw new IllegalArgumentException("Invalid fuel type: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
