package com.api.module.image.controllers;

import com.api.module.image.responses.CustomResponse;
import com.api.module.image.services.IStorageService;
import com.api.module.image.services.ProductSubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/msshop/mi/productsub")
@RequiredArgsConstructor
public class ProductSubController {
    private final IStorageService storageService;
    private final ProductSubService productSubService;

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @PostMapping
    public ResponseEntity<CustomResponse> uploadFile(@RequestParam("file") List<MultipartFile> files) {
        try {
            return ResponseEntity.ok(productSubService.uploadFiles(files));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("Xảy ra lỗi.", e.getMessage()));
        }
    }

    @GetMapping("/files/{imageProductSubId}")
    public ResponseEntity<byte[]> getImagesByImageProductSubId(@PathVariable UUID imageProductSubId) {
        List<String> fileNames = productSubService.getFileNamesByImageProductSubId(imageProductSubId);

        if (fileNames.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<byte[]> images = new ArrayList<>();
        for (String fileName : fileNames) {
            if (fileName != null) {
                byte[] bytes = storageService.readFileContent(fileName);
                images.add(bytes);
            }
        }

        // Combine all images into a single byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (byte[] image : images) {
            try {
                outputStream.write(image);
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }

        byte[] combinedImages = outputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(combinedImages, headers, HttpStatus.OK);
    }
}
