package dev.local.restapi.search;

import dev.local.restapi.model.enums.FuelType;
import dev.local.restapi.model.enums.GearboxType;
import lombok.Data;

@Data
public class SearchAdvertRequestDto {

    private Integer minYear;
    private Integer maxYear;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer minEnginePower;
    private Integer maxEnginePower;
    private Integer minEngineCapacity;
    private Integer maxEngineCapacity;
    private Integer minMileage;
    private Integer maxMileage;

    private String brand;
    private String model;
    private Integer doorCount;
    private String generation;
    private String version;
    private String color;
    private GearboxType gearboxType;
    private FuelType fuelType;
}
