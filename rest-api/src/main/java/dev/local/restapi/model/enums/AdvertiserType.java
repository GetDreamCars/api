package dev.local.restapi.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AdvertiserType {
    PRIVATE("private"),
    DEALER("dealer");

    private final String value;

    AdvertiserType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static AdvertiserType fromValue(String value) {
        for (AdvertiserType advertiserType : values()) {
            if (advertiserType.value.equalsIgnoreCase(value)) {
                return advertiserType;
            }
        }
        throw new IllegalArgumentException("Invalid advertiser type: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
