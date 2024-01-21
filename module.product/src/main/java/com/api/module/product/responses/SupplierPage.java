package com.api.module.product.responses;

import java.util.List;

import com.api.module.product.models.Supplier;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
public class SupplierPage {
	private List<Supplier> suppliers;
	private int totalPages;
	private long totalElements;

	public SupplierPage() {
		super();
	}

	public SupplierPage(List<Supplier> suppliers, int totalPages, long totalElements) {
		super();
		this.suppliers = suppliers;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
}
