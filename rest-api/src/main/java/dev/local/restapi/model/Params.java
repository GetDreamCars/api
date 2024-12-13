package dev.local.restapi.model;

import dev.local.restapi.model.enums.FuelType;
import dev.local.restapi.model.enums.GearboxType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Params {

    private String vin;
    private Integer manufactureYear;
    private String brand;
    private String model;
    private Integer enginePower;
    private Integer engineCapacity;
    private Integer doorCount;
    private String generation;
    private String version;
    private Integer mileage;
    private String bodyType;
    private String color;
    private String video;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Enumerated(EnumType.STRING)
    private GearboxType gearbox;

    @Embedded
    private Price price;
}
