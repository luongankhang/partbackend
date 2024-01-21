package com.api.module.image.services;

import com.api.module.image.models.ProductSub;
import com.api.module.image.repositories.ProductSubRepository;
import com.api.module.image.responses.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductSubService {
    private final IStorageService storageService;
    private final ProductSubRepository productSubRepository;

    public CustomResponse uploadFiles(List<MultipartFile> files) {
        if (files != null && !files.isEmpty()) {
            try {
                List<String> fileNames = storageService.storeMFiles(files);
                final var productSub = new ProductSub(fileNames);
                return CustomResponse.builder().data(productSubRepository.save(productSub)).build();
            } catch (Exception e) {
                return CustomResponse.builder().build();
            }
        }
        return CustomResponse.builder().build();
    }

    public List<String> getFileNamesByImageProductSubId(UUID imageProductSubId) {
        final var productSub = productSubRepository.findById(imageProductSubId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ProductSub với id: " + imageProductSubId));

        List<String> fileNames = new ArrayList<>();
        fileNames.add(productSub.getFileName1());
        fileNames.add(productSub.getFileName2());
        fileNames.add(productSub.getFileName3());
        fileNames.add(productSub.getFileName4());
        fileNames.add(productSub.getFileName5());

        return fileNames;
    }
}
