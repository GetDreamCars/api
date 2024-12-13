package dev.local.restapi.search;

import dev.local.restapi.model.Advert;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

public class AdvertSpecification {

    public static Specification<Advert> search(SearchAdvertRequestDto searchDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // manufactureYear
            if (!isEmpty(searchDto.getMinYear())) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("params").get("manufactureYear"), searchDto.getMinYear()));
            }
            if (!isEmpty(searchDto.getMaxYear())) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("params").get("manufactureYear"), searchDto.getMaxYear()));
            }
            // price
            if (!isEmpty(searchDto.getMinPrice())) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("params").get("price").get("amount"), searchDto.getMinPrice()));
            }
            if (!isEmpty(searchDto.getMaxPrice())) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("params").get("price").get("amount"), searchDto.getMaxPrice()));
            }
            // engine power
            if (!isEmpty(searchDto.getMinEnginePower())) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("params").get("enginePower"), searchDto.getMinEnginePower()));
            }
            if (!isEmpty(searchDto.getMaxEnginePower())) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("params").get("enginePower"), searchDto.getMaxEnginePower()));
            }
            // engine capacity
            if (!isEmpty(searchDto.getMinEngineCapacity())) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("params").get("engineCapacity"), searchDto.getMinEngineCapacity()));
            }
            if (!isEmpty(searchDto.getMaxEngineCapacity())) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("params").get("engineCapacity"), searchDto.getMaxEngineCapacity()));
            }
            // mileage
            if (!isEmpty(searchDto.getMinMileage())) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("params").get("mileage"), searchDto.getMinMileage()));
            }
            if (!isEmpty(searchDto.getMaxMileage())) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("params").get("mileage"), searchDto.getMaxMileage()));
            }
            // brand
            if (!isEmpty(searchDto.getBrand())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("params").get("brand")), "%" + searchDto.getBrand().toLowerCase() + "%"));
            }
            // model
            if (!isEmpty(searchDto.getModel())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("params").get("model")), "%" + searchDto.getModel().toLowerCase() + "%"));
            }
            // door count
            if (!isEmpty(searchDto.getDoorCount())) {
                predicates.add(criteriaBuilder.equal(root.get("params").get("doorCount"), searchDto.getDoorCount()));
            }
            // generation
            if (!isEmpty(searchDto.getGeneration())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("params").get("generation")), "%" + searchDto.getGeneration().toLowerCase() + "%"));
            }
            // version
            if (!isEmpty(searchDto.getVersion())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("params").get("version")), "%" + searchDto.getVersion().toLowerCase() + "%"));
            }
            // color
            if (!isEmpty(searchDto.getColor())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("params").get("color")), "%" + searchDto.getColor().toLowerCase() + "%"));
            }
            // gearbox
            if (!isEmpty(searchDto.getGearboxType())) {
                predicates.add(criteriaBuilder.equal(root.get("params").get("gearboxType"), searchDto.getGearboxType()));
            }
            // fuel
            if (!isEmpty(searchDto.getFuelType())) {
                predicates.add(criteriaBuilder.equal(root.get("params").get("fuelType"), searchDto.getFuelType()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
