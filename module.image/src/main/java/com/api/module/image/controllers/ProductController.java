package com.api.module.image.controllers;

import com.api.module.image.responses.CustomResponse;
import com.api.module.image.services.IStorageService;
import com.api.module.image.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api/msshop/mi/product")
@RequiredArgsConstructor
public class ProductController {
    private final IStorageService storageService;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<CustomResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(productService.uploadFile(file));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("Xảy ra lỗi.", e.getMessage()));
        }
    }

    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
}
