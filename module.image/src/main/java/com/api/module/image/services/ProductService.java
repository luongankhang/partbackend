package com.api.module.image.services;

import com.api.module.image.models.Product;
import com.api.module.image.repositories.ProductRepository;
import com.api.module.image.responses.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final IStorageService storageService;
    private final ProductRepository productRepository;

    public CustomResponse uploadFile(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                final var generatedFileName = storageService.storeFile(file);
                final var product = new Product(generatedFileName);
                return CustomResponse.builder().data(productRepository.save(product)).build();
            } catch (Exception e) {
                return CustomResponse.builder().build();
            }
        }
        return CustomResponse.builder().build();
    }
}
