package com.api.module.product.responses;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomValidation {
    private static Validator validator;

    public static <T> CustomResponse validateObject(T object, BindingResult bindingResult) {
        // Kiểm tra null trước khi gọi validate
        if (validator != null) {
            validator.validate(object, bindingResult);
        }

        if (bindingResult.hasErrors()) {
            List<ErrorDetail> errorDetails = new ArrayList<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                ErrorDetail errorDetail = new ErrorDetail(error.getField(), error.getDefaultMessage());
                errorDetails.add(errorDetail);
            }

            // Tạo một message từ danh sách lỗi
            String errorMessage = errorDetails.stream()
                    .map(errorDetail -> String.format("%s", errorDetail.getMessage()))
                    .collect(Collectors.joining("; "));

            return CustomResponse.builder()
                    .message(errorMessage)
                    .data(errorDetails)
                    .build();
        } else {
            // Nếu không có lỗi, trả về thông báo thành công hoặc message rỗng
            return CustomResponse.builder()
                    .message("Validation successful.")
                    .data(object) // hoặc có thể trả về null nếu không muốn có dữ liệu khi không có lỗi
                    .build();
        }
    }
}
