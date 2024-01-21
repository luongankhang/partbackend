package com.api.module.product.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.api.module.product.dtos.SupplierDto;
import com.api.module.product.models.Supplier;

@Mapper
public interface SupplierMapper {

//	@Mapping(source = "supplierName", target = "supplierName")
//	@Mapping(source = "address", target = "address")
//	@Mapping(source = "phone", target = "phone")
//	@Mapping(source = "email", target = "email")
//	@Mapping(source = "website", target = "website")
//	Supplier toSupplier(SupplierDto dto);
}
