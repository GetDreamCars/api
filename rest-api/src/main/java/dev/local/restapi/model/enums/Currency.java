package dev.local.restapi.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Currency {
    PLN("PLN"),
    EUR("EUR");

    private final String value;

    Currency(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Currency fromValue(String value) {
        for (Currency currency : values()) {
            if (currency.value.equalsIgnoreCase(value)) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Invalid advertiser type: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
