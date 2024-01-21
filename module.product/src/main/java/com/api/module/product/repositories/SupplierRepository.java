package com.api.module.product.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.module.product.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

	boolean existsBySupplierName(String supplierName);

	boolean existsByPhone(String phone);

	boolean existsByEmail(String email);

//	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Supplier c WHERE c.supplierName = :supplierName")
//    boolean existsBySupplierName@Param("supplierName") String supplierName);
//	
//	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Supplier c WHERE c.phone = :phone")
//    boolean existsByPhone(@Param("phone") String phone);
//	
//	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Supplier c WHERE c.email = :email")
//    boolean existsByEmail(@Param("email") String email);
}
