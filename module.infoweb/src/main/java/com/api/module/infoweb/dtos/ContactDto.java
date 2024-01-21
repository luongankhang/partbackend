package com.api.module.infoweb.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDto {
    private UUID contactId;
    private String location;
    private String email;
    private String phone;
    private int hide;
}
