package dev.local.restapi.model;

import dev.local.restapi.model.enums.Currency;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Price {

    private int amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private String grossNet;
}
