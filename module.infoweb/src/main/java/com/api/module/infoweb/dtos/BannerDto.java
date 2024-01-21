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
public class BannerDto {
    private UUID bannerId;
    private String fileName1;
    private String fileName2;
    private int hide;
}
