package dev.local.restapi.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Contact {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
}
