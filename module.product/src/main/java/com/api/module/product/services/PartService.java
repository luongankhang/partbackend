package com.api.module.product.services;

import com.api.module.product.dtos.PartDto;
import com.api.module.product.models.Part;
import com.api.module.product.repositories.PartRepository;
import com.api.module.product.repositories.PartTypeRepository;
import com.api.module.product.repositories.SupplierRepository;
import com.api.module.product.responses.CustomResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PartService implements CustomIActions<PartDto> {
    private final PartRepository partRepository;
    private final PartTypeRepository partTypeRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public CustomResponse listAll() {
        final List<Part> allObjs = partRepository.findAll();

        if (allObjs.isEmpty()) {
            return CustomResponse.builder().message("No parts found.").build();
        }

        return CustomResponse.builder().message("Parts retrieved successfully.").data(allObjs).build();
    }

    public CustomResponse getById(UUID id) {
        try {
            final UUID uuid = UUID.fromString(String.valueOf(id));
        } catch (IllegalArgumentException e) {
            return CustomResponse.builder().message("Invalid UUID format for id.").build();
        }

        final var part = partRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part Type not found."));

        return CustomResponse.builder().message("Part retrieved successfully.").data(part).build();
    }

    public CustomResponse searchByName(String partName) {
        final List<Part> matchingParts = partRepository.findByPartNameContainingIgnoreCase(partName);

        if (matchingParts.isEmpty()) {
            return CustomResponse.builder().message("No parts found with the given name.").build();
        }

        return CustomResponse.builder().message("Parts retrieved successfully based on name search.").data(matchingParts).build();
    }

    public CustomResponse getAllSortedByPriceAsc() {
        final List<Part> sortedParts = partRepository.findAllByOrderByPriceAsc();

        if (sortedParts.isEmpty()) {
            return CustomResponse.builder().message("No parts found.").build();
        }

        return CustomResponse.builder().message("Parts retrieved and sorted by price ascending successfully.").data(sortedParts).build();
    }

    public CustomResponse getAllSortedByPriceDesc() {
        final List<Part> sortedParts = partRepository.findAllByOrderByPriceDesc();

        if (sortedParts.isEmpty()) {
            return CustomResponse.builder().message("No parts found.").build();
        }

        return CustomResponse.builder().message("Parts retrieved and sorted by price descending successfully.").data(sortedParts).build();
    }


    public CustomResponse create(PartDto partDto) {
        final var partTypeId = partDto.getPartType().getPartTypeId();
        final var partType = partTypeRepository.findById(partTypeId)
                .orElseThrow(() -> new EntityNotFoundException("Part Type not found."));

        final var supplierId = partDto.getSupplier().getSupplierId();
        final var supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new EntityNotFoundException("Part Type not found."));

        final var obj = Part.builder()
                .partName(partDto.getPartName())
                .price(partDto.getPrice())
                .priceLast(partDto.getPriceLast())
                .partType(partType)
                .description(partDto.getDescription())
                .location(partDto.getLocation())
                .inventoryQuantity(partDto.getInventoryQuantity())
                .supplier(supplier)
                .fileName(partDto.getFileName())
                .status(partDto.getStatus())
                .build();
        final var saved = partRepository.save(obj);

        return CustomResponse.builder().data(saved).build();
    }

    public CustomResponse update(UUID id, PartDto partDto) {
        try {
            final UUID uuid = UUID.fromString(String.valueOf(id));
        } catch (IllegalArgumentException e) {
            return CustomResponse.builder().message("Invalid UUID format for id.").build();
        }

        final var part = partRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part Type not found."));

        final var partTypeId = partDto.getPartType().getPartTypeId();
        final var partType = partTypeRepository.findById(partTypeId)
                .orElseThrow(() -> new EntityNotFoundException("Part Type not found."));

        final var supplierId = partDto.getSupplier().getSupplierId();
        final var supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new EntityNotFoundException("Part Type not found."));

        final var updated = Part.builder()
                .partName(partDto.getPartName())
                .price(partDto.getPrice())
                .priceLast(partDto.getPriceLast())
                .partType(partType)
                .description(partDto.getDescription())
                .location(partDto.getLocation())
                .inventoryQuantity(partDto.getInventoryQuantity())
                .supplier(supplier)
                .fileName(partDto.getFileName())
                .status(partDto.getStatus())
                .build();
        part.updateFrom(updated);

        final var saved = partRepository.save(part);

        return CustomResponse.builder().message("Cập nhật thông tin thành công.").data(saved).build();
    }

    public CustomResponse delete(UUID id) {
        try {
            final UUID uuid = UUID.fromString(String.valueOf(id));
        } catch (IllegalArgumentException e) {
            return CustomResponse.builder().message("Invalid UUID format for id.").build();
        }

        final var part = partRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part Type not found."));

        partRepository.delete(part);

        return CustomResponse.builder().message("Xóa thành công.").build();
    }
}
