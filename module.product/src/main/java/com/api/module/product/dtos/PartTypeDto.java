package com.api.module.product.dtos;

import com.api.module.product.models.PartCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartTypeDto {
    private UUID partTypeId;

    private PartCategory partCategory;

    @NotBlank(message = "Tên không được trống.")
    @Length(min = 1, max = 1000, message = "Tên phải có độ dài từ 1 đến 1000 ký tự.")
    @Pattern(regexp = "^[a-zA-Z0-9\\p{L} ]+$", message = "Tên chỉ được chứa ký tự chữ cái, số và khoảng trắng.")
    private String partTypeName;
}
