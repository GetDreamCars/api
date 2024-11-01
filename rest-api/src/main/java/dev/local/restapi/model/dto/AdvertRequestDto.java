package dev.local.restapi.model.dto;

import dev.local.restapi.model.Contact;
import dev.local.restapi.model.Params;
import dev.local.restapi.model.enums.AdvertiserType;
import lombok.Data;

@Data
public class AdvertRequestDto {

    private String title;
    private String description;
    private AdvertiserType advertiserType;
    private Contact contact;
    private Params params;
}