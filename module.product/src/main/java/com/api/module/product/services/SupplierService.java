package com.api.module.product.services;

import com.api.module.product.dtos.SupplierDto;
import com.api.module.product.models.Supplier;
import com.api.module.product.repositories.SupplierRepository;
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
public class SupplierService implements CustomIActions<SupplierDto> {
    private final SupplierRepository supplierRepository;

    @Override
    public CustomResponse listAll() {
        final List<Supplier> allObjs = supplierRepository.findAll();

        if (allObjs.isEmpty()) {
            return CustomResponse.builder().message("Danh sách trống.").build();
        }

        return CustomResponse.builder().data(allObjs).build();
    }

    @Override
    public CustomResponse getById(UUID id) {
        if (ValidationUtils.existsFormById(id)) {
            final var supplier = supplierRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Part Type not found."));
        }

        var obj = supplierRepository.findById(id);

        return CustomResponse.builder().data(obj).build();
    }

    @Override
    public CustomResponse create(SupplierDto supplierDto) {
        if (supplierRepository.existsBySupplierName(supplierDto.getSupplierName())) {
            throw new DataIntegrityViolationException("Tên nhà cung cấp đã tồn tại.");
        }

        if (supplierRepository.existsByPhone(supplierDto.getPhone())) {
            throw new DataIntegrityViolationException("Số điện thoại đã tồn tại.");
        }

        if (supplierRepository.existsByEmail(supplierDto.getEmail())) {
            throw new DataIntegrityViolationException("Email đã tồn tại.");
        }

        final var obj = Supplier.builder()
                .supplierName(supplierDto.getSupplierName())
                .address(supplierDto.getAddress())
                .phone(supplierDto.getPhone())
                .email(supplierDto.getEmail())
                .website(supplierDto.getWebsite())
                .build();

        final var saved = supplierRepository.save(obj);

        return CustomResponse.builder().data(saved).build();
    }

    @Override
    public CustomResponse update(UUID id, SupplierDto supplierDto) {
        if (ValidationUtils.existsFormById(id)) {
            final var supplier = supplierRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Part Category not found."));

            if (supplierRepository.existsBySupplierName(supplierDto.getSupplierName())) {
                throw new DataIntegrityViolationException("Tên nhà cung cấp đã tồn tại.");
            }

            if (supplierRepository.existsByPhone(supplierDto.getPhone())) {
                throw new DataIntegrityViolationException("Số điện thoại đã tồn tại.");
            }

            if (supplierRepository.existsByEmail(supplierDto.getEmail())) {
                throw new DataIntegrityViolationException("Email đã tồn tại.");
            }

            final var updated = Supplier.builder()
                    .supplierName(supplierDto.getSupplierName())
                    .address(supplierDto.getAddress())
                    .phone(supplierDto.getPhone())
                    .email(supplierDto.getEmail())
                    .website(supplierDto.getWebsite())
                    .build();
            supplier.updateFrom(updated);

            final var saved = supplierRepository.save(supplier);

            return CustomResponse.builder().data(saved).build();
        }
        return CustomResponse.builder().message("Invalid UUID format for id.").build();
    }

    @Override
    public CustomResponse delete(UUID id) {
        if (ValidationUtils.existsFormById(id)) {
            final var supplier = supplierRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Part Category not found."));

            supplierRepository.delete(supplier);

            return CustomResponse.builder().message("Xóa thành công.").build();
        }
        return CustomResponse.builder().message("Invalid UUID format for id.").build();
    }
}
