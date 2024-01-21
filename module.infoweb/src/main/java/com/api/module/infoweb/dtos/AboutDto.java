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
public class AboutDto {
    private UUID aboutId;
    private String content;
    private String fileName;
    private int hide;
}
