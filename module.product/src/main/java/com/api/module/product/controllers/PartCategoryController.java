package com.api.module.product.controllers;

import com.api.module.product.dtos.PartCategoryDto;
import com.api.module.product.repositories.PartCategoryRepository;
import com.api.module.product.responses.CustomResponse;
import com.api.module.product.responses.CustomValidation;
import com.api.module.product.services.PartCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/msshop/mp/partcategory")
@RequiredArgsConstructor
public class PartCategoryController {
    private final PartCategoryService partCategoryService;
    private final PartCategoryRepository partCategoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(PartCategoryController.class);

    @GetMapping
    public ResponseEntity listAll() {
//        try {
//            logger.info("PartCategory");
//            return ResponseEntity.ok(partCategoryService.listAll());
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new CustomResponse("Xảy ra lỗi.", e.getMessage()));
//        }
        return ResponseEntity.ok(partCategoryRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(partCategoryService.getById(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("Xảy ra lỗi.", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody PartCategoryDto partCategoryDto, BindingResult bindingResult) {
        try {
            var validationResponse = CustomValidation.validateObject(partCategoryDto, bindingResult);
            if (!validationResponse.getMessage().equals("Validation successful.")) {
                logger.error(validationResponse.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(validationResponse);
            }

            logger.info("PartCategory");
            return ResponseEntity.ok(partCategoryService.create(partCategoryDto));
        } catch (DataIntegrityViolationException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponse(ex.getMessage(), ex.getMessage()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("Xảy ra lỗi.", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> update(@Valid @RequestBody PartCategoryDto partCategoryDto, @PathVariable UUID id, BindingResult bindingResult) {
        try {
            var validationResponse = CustomValidation.validateObject(partCategoryDto, bindingResult);
            if (!validationResponse.getMessage().equals("Validation successful.")) {
                logger.error(validationResponse.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(validationResponse);
            }
            logger.info("PartCategory");
            return ResponseEntity.ok(partCategoryService.update(id, partCategoryDto));
        } catch (DataIntegrityViolationException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponse(ex.getMessage(), ex.getMessage()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("Xảy ra lỗi.", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> delete(@PathVariable UUID id) {
        try {
            logger.info("PartCategory");
            return ResponseEntity.ok(partCategoryService.delete(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("Xảy ra lỗi.", e.getMessage()));
        }
    }
}
