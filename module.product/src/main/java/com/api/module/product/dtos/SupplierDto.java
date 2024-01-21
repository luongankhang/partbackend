package com.api.module.product.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
public class SupplierDto {
    @NotBlank(message = "Tên không được trống.")
    @Length(min = 1, max = 1000, message = "Tên phải có độ dài từ 1 đến 1000 ký tự.")
    @Pattern(regexp = "^[a-zA-Z0-9\\p{L} ]+$", message = "Tên chỉ được chứa ký tự chữ cái, số và khoảng trắng.")
    private String supplierName;

    @Size(max = 255, message = "Địa chỉ không quá 255 kí tự.")
    private String address;

    @NotBlank(message = "Số điện thoại không được để trống.")
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại phải có định dạng 10 số.")
    private String phone;

    @NotBlank(message = "Email không được để trống.")
    @Email(message = "Định dạng email không hợp lệ.")
    private String email;

    //	@NotBlank(message = "Website không được để trống.")
    @Size(max = 255, message = "Website không quá 255 kí tự.")
    private String website;

    public SupplierDto() {
        super();
    }

    public SupplierDto(String supplierName, String address, String phone, String email, String website) {
        super();
        this.supplierName = supplierName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.website = website;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
