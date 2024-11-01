package dev.local.restapi.model;

import dev.local.restapi.model.enums.AdvertStatus;
import dev.local.restapi.model.enums.AdvertiserType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime validTo;

    @Enumerated(EnumType.STRING)
    private AdvertiserType advertiserType;
    @Enumerated(EnumType.STRING)
    private AdvertStatus status;

    @Embedded
    private Contact contact;
    @Embedded
    private Params params;
}
