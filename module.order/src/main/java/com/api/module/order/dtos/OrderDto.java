package com.api.module.order.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private UUID id;
    private String user;
    private List<ProductInfoDto> products;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
    private String note;
}
