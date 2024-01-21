package com.api.module.customer.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDto {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
}
