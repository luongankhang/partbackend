package com.api.module.product.services;

import com.api.module.product.dtos.PartCategoryDto;
import com.api.module.product.models.PartCategory;
import com.api.module.product.repositories.PartCategoryRepository;
import com.api.module.product.responses.CustomResponse;
import com.api.module.product.responses.ValidationUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PartCategoryService implements CustomIActions<PartCategoryDto> {
    private final PartCategoryRepository partCategoryRepository;

    @Override
    public CustomResponse listAll() {
        final List<PartCategory> allObjs = partCategoryRepository.findAll();

        if (allObjs.isEmpty()) {
            return CustomResponse.builder().message("Danh sách trống.").build();
        }

        return CustomResponse.builder().data(allObjs).build();
    }

    @Override
    public CustomResponse getById(UUID id) {
        if (ValidationUtils.existsFormById(id)) {
            final var partCategory = partCategoryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Part Category not found."));
        }

        var obj = partCategoryRepository.findById(id);

        return CustomResponse.builder().data(obj).build();
    }

    @Override
    public CustomResponse create(PartCategoryDto partCategoryDto) {
        if (partCategoryRepository.existsByCategoryName(partCategoryDto.getCategoryName())) {
            throw new DataIntegrityViolationException("Tên danh mục đã tồn tại.");
        }
        var obj = PartCategory.builder().categoryName(partCategoryDto.getCategoryName()).build();
        var save = partCategoryRepository.save(obj);
        return CustomResponse.builder().data(save).build();
    }

    @Override
    public CustomResponse update(UUID id, PartCategoryDto partCategoryDto) {
        if (ValidationUtils.existsFormById(id)) {
            final var partCategory = partCategoryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Part Category not found."));

            if (partCategoryRepository.existsByCategoryName(partCategoryDto.getCategoryName())) {
                throw new DataIntegrityViolationException("Tên danh mục đã tồn tại.");
            }

            final var updated = PartCategory.builder()
                    .categoryName(partCategoryDto.getCategoryName()).build();
            partCategory.updateFrom(updated);

            final var saved = partCategoryRepository.save(partCategory);

            return CustomResponse.builder().data(saved).build();
        }
        return CustomResponse.builder().message("Invalid UUID format for id.").build();
    }

    @Override
    public CustomResponse delete(UUID id) {
        if (ValidationUtils.existsFormById(id)) {
            final var partCategory = partCategoryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Part Category not found."));

            partCategoryRepository.delete(partCategory);

            return CustomResponse.builder().message("Xóa thành công").build();
        }
        return CustomResponse.builder().message("Invalid UUID format for id.").build();
    }
}
