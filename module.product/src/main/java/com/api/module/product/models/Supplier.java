package com.api.module.product.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tblSupplier")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
//Bảng Nhà Cung Cấp
public class Supplier {
    // Mã nhà cung cấp (Supplier Id)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supplierId")
    private UUID supplierId;

    // Tên nhà cung cấp (Supplier Name)
    @Column(name = "supplierName")
    private String supplierName;

    // Địa chỉ (Address)
    @Column(name = "address", length = 1000)
    private String address;

    // Số điện thoại (Phone)
    @Column(name = "phone")
    private String phone;

    // Email
    @Column(name = "email")
    private String email;

    // Website
    @Column(name = "website")
    private String website;

    public void updateFrom(Supplier other) {
        this.supplierName = other.supplierName;
        this.address = other.address;
        this.phone = other.phone;
        this.email = other.email;
        this.website = other.website;
    }
}
