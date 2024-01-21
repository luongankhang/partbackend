package com.api.module.product.controllers;

import com.api.module.product.dtos.PartTypeDto;
import com.api.module.product.repositories.PartTypeRepository;
import com.api.module.product.responses.CustomResponse;
import com.api.module.product.responses.CustomValidation;
import com.api.module.product.services.PartTypeService;
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
@RequestMapping(path = "/api/msshop/mp/parttype")
@RequiredArgsConstructor
public class PartTypeController {
    private final PartTypeService partTypeService;
    private final PartTypeRepository partTypeRepository;

    private static final Logger logger = LoggerFactory.getLogger(PartTypeController.class);

    @GetMapping
    public ResponseEntity listAlls() {
//        try {
//            logger.info("PartType");
//            return ResponseEntity.ok(partTypeService.listAll());
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new CustomResponse("Xảy ra lỗi.", e.getMessage()));
//        }
        return ResponseEntity.ok(partTypeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getById(@PathVariable UUID id) {
        try {
            logger.info("PartType");
            return ResponseEntity.ok(partTypeService.getById(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("Xảy ra lỗi.", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody PartTypeDto partTypeDto, BindingResult bindingResult) {
        try {
            var validationResponse = CustomValidation.validateObject(partTypeDto, bindingResult);
            if (!validationResponse.getMessage().equals("Validation successful.")) {
                logger.error(validationResponse.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(validationResponse);
            }

            logger.info("PartType");
            return ResponseEntity.ok(partTypeService.create(partTypeDto));
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
    public ResponseEntity<CustomResponse> update(@Valid @RequestBody PartTypeDto partTypeDto, @PathVariable UUID id, BindingResult bindingResult) {
        try {
            var validationResponse = CustomValidation.validateObject(partTypeDto, bindingResult);
            if (!validationResponse.getMessage().equals("Validation successful.")) {
                logger.error(validationResponse.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(validationResponse);
            }

            logger.info("PartType");
            return ResponseEntity.ok(partTypeService.update(id, partTypeDto));
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
            return ResponseEntity.ok(partTypeService.delete(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("Xảy ra lỗi.", e.getMessage()));
        }
    }
}
