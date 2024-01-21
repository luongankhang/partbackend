package com.api.module.product.controllers;

import com.api.module.product.dtos.PartDto;
import com.api.module.product.repositories.PartRepository;
import com.api.module.product.responses.CustomResponse;
import com.api.module.product.responses.CustomValidation;
import com.api.module.product.services.PartService;
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
@RequestMapping(path = "/api/msshop/mp/part")
@RequiredArgsConstructor
public class PartController {
    private final PartService partService;
    private final PartRepository partRepository;

    private static final Logger logger = LoggerFactory.getLogger(PartController.class);

    @GetMapping
    public ResponseEntity listAlls() {
//        try {
//            logger.info("Part");
//            return ResponseEntity.ok(partService.listAll());
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new CustomResponse("Xảy ra lỗi.", e.getMessage()));
//        }
        return ResponseEntity.ok(partRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable UUID id) {
//        try {
//            logger.info("Part id");
//            return ResponseEntity.ok(partService.getById(id));
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new CustomResponse("Xảy ra lỗi.", e.getMessage()));
//        }
        logger.info("Part id");
        var obj = partRepository.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody PartDto partDto, BindingResult bindingResult) {
        try {
            var validationResponse = CustomValidation.validateObject(partDto, bindingResult);
            if (!validationResponse.getMessage().equals("Validation successful.")) {
                logger.error(validationResponse.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(validationResponse);
            }

            logger.info("Part");
            return ResponseEntity.ok(partService.create(partDto));
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
    public ResponseEntity<CustomResponse> update(@Valid @RequestBody PartDto partDto, @PathVariable UUID id, BindingResult bindingResult) {
        try {
            var validationResponse = CustomValidation.validateObject(partDto, bindingResult);
            if (!validationResponse.getMessage().equals("Validation successful.")) {
                logger.error(validationResponse.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(validationResponse);
            }

            logger.info("Part");
            return ResponseEntity.ok(partService.update(id, partDto));
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
            logger.info("Part");
            return ResponseEntity.ok(partService.delete(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("Xảy ra lỗi.", e.getMessage()));
        }
    }
}
