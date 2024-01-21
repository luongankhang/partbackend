package com.api.module.product.services;

import com.api.module.product.dtos.PartTypeDto;
import com.api.module.product.models.PartType;
import com.api.module.product.repositories.PartCategoryRepository;
import com.api.module.product.repositories.PartTypeRepository;
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
public class PartTypeService implements CustomIActions<PartTypeDto> {
    private final PartTypeRepository partTypeRepository;
    private final PartCategoryRepository partCategoryRepository;

    @Override
    public CustomResponse listAll() {
        List<PartType> allObjs = partTypeRepository.findAll();

        if (allObjs.isEmpty()) {
            return CustomResponse.builder().message("Danh sách trống.").build();
        }

        return CustomResponse.builder().data(allObjs).build();
    }

    @Override
    public CustomResponse getById(UUID id) {
        if (ValidationUtils.existsFormById(id)) {
            final var partType = partTypeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Part Type not found."));
        }

        var obj = partTypeRepository.findById(id);

        return CustomResponse.builder().data(obj).build();
    }

    @Override
    public CustomResponse create(PartTypeDto partTypeDto) {
        if (partTypeRepository.existsByPartTypeName(partTypeDto.getPartTypeName())) {
            throw new DataIntegrityViolationException("Tên loại phụ tùng đã tồn tại.");
        }

        final var partCategoryId = partTypeDto.getPartCategory().getCategoryId();
        final var partCategory = partCategoryRepository.findById(partCategoryId)
                .orElseThrow(() -> new EntityNotFoundException("Part Category not found."));

        var obj = PartType.builder()
                .partCategory(partCategory)
                .partTypeName(partTypeDto.getPartTypeName())
                .build();
        var save = partTypeRepository.save(obj);

        return CustomResponse.builder().data(save).build();
    }

    @Override
    public CustomResponse update(UUID id, PartTypeDto partTypeDto) {
        if (ValidationUtils.existsFormById(id)) {
            final var partType = partTypeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Part Type not found."));

            if (partTypeRepository.existsByPartTypeName(partTypeDto.getPartTypeName())) {
                throw new DataIntegrityViolationException("Tên loại danh mục đã tồn tại.");
            }

            final var partCategoryId = partTypeDto.getPartCategory().getCategoryId();
            final var partCategory = partCategoryRepository.findById(partCategoryId)
                    .orElseThrow(() -> new EntityNotFoundException("Part Category not found."));

            final var updated = PartType.builder()
                    .partTypeName(partTypeDto.getPartTypeName())
                    .partCategory(partCategory)
                    .build();
            partType.updateFrom(updated);

            final var saved = partTypeRepository.save(partType);

            return CustomResponse.builder().data(saved).build();
        }
        return CustomResponse.builder().message("Invalid UUID format for id.").build();
    }

    @Override
    public CustomResponse delete(UUID id) {
        if (ValidationUtils.existsFormById(id)) {
            final var partType = partTypeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Part Category not found."));

            partTypeRepository.delete(partType);

            return CustomResponse.builder().message("Xóa thành công.").build();
        }
        return CustomResponse.builder().message("Invalid UUID format for id.").build();
    }
}
