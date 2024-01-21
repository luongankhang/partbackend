package com.api.module.product.controllers;

import com.api.module.product.dtos.SupplierDto;
import com.api.module.product.repositories.SupplierRepository;
import com.api.module.product.responses.CustomResponse;
import com.api.module.product.responses.ErrorDetail;
import com.api.module.product.services.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/msshop/mp/supplier")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;
    private final SupplierRepository supplierRepository;

    private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @GetMapping
    public ResponseEntity sdjs() {
        return ResponseEntity.ok(supplierRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CustomResponse> add(@Valid @RequestBody SupplierDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorDetail> errorDetails = new ArrayList<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                ErrorDetail errorDetail = new ErrorDetail(error.getField(), error.getDefaultMessage());
                errorDetails.add(errorDetail);
            }

            String errorMessage = "Xảy ra lỗi: ";
            for (ErrorDetail errorDetail : errorDetails) {
                errorMessage += errorDetail.getField() + ": " + errorDetail.getMessage() + ", ";
            }

            logger.error(errorMessage);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse(errorMessage, errorDetails));
        }

        try {

//			logger.info("Supplier added successfully. Supplier ID: {}", supplier.getSupplierId());
//			return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse("Thành công.", supplier));
            logger.info("kk");
            return ResponseEntity.ok(supplierService.create(dto));
        } catch (DataIntegrityViolationException e) {

            logger.error("Supplier more failure ", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponse(e.getMessage(), e.getMessage()));
        } catch (Exception e) {

            logger.error("Supplier more failure ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse(e.getMessage(), e.getMessage()));
        }
    }
}
