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
public class InformationAboutDto {
    private UUID iAId;
    private String content;
    private int hide;
}
