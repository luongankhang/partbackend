package com.api.module.customer.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.module.customer.dtos.CustomerDto;
import com.api.module.customer.models.Customer;
import com.api.module.customer.repositories.CustomerRepository;

@Service
public class CustomerService {
	private final CustomerRepository customerRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
		this.customerRepository = customerRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Customer add(CustomerDto dto) {
		if (customerRepository.existsByUsername(dto.getUsername())) {
			throw new DataIntegrityViolationException("Tài khoản đã tồn tại.");
		}

		if (customerRepository.existsByPhone(dto.getPhone())) {
			throw new DataIntegrityViolationException("Số điện thoại đã tồn tại.");
		}

		if (customerRepository.existsByEmail(dto.getEmail())) {
			throw new DataIntegrityViolationException("Email đã tồn tại.");
		}

		ModelMapper mapper = new ModelMapper();
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		Customer customer = mapper.map(dto, Customer.class);

		return customerRepository.save(customer);
	}
}
