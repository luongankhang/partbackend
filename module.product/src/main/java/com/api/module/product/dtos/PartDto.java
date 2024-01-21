package com.api.module.product.dtos;

import com.api.module.product.models.PartType;
import com.api.module.product.models.Supplier;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartDto {
    @NotBlank(message = "Tên không được trống.")
    @Length(min = 1, max = 1000, message = "Tên phải có độ dài từ 1 đến 1000 ký tự.")
    @Pattern(regexp = "^[a-zA-Z0-9\\p{L} ]+$", message = "Tên chỉ được chứa ký tự chữ cái, số và khoảng trắng.")
    private String partName;

    @NotNull(message = "price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "price must be greater than 0")
    private BigDecimal price;

    private BigDecimal priceLast;

    private PartType partType;

    @NotBlank(message = "Mô tả không được trống.")
    @Length(min = 1, max = 1000, message = "Mô tả phải có độ dài từ 1 đến 1000 ký tự.")
    @Pattern(regexp = "^[a-zA-Z0-9\\p{L} ]+$", message = "Tên chỉ được chứa ký tự chữ cái, số và khoảng trắng.")
    private String description;

    @Size(max = 100, message = "location length must be less than or equal to 100")
    private String location;

    @Min(value = 0, message = "inventoryQuantity must be greater than or equal to 0")
    private int inventoryQuantity;

    private Supplier supplier;

    @Size(max = 255, message = "fileName length must be less than or equal to 255")
    private String fileName;

    private String status;
}
