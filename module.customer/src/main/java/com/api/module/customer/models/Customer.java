package com.api.module.customer.models;

import java.util.Collection;
import java.util.UUID;

import com.api.module.customer.dtos.UpdateDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tblCustomer")
public class Customer implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID customerId;

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private String email;
	private String fileName;
	private Role role;

	public Customer() {
		super();
	}

	public Customer(String username, String password, String firstName, String lastName, String address, String phone,
			String email, String fileName, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.fileName = fileName;
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public static class CustomerBuilder {
		private UUID customerId;
		private String username;
		private String password;
		private String firstName;
		private String lastName;
		private String address;
		private String phone;
		private String email;
		private String fileName;
		private Role role;

		private PasswordEncoder passwordEncoder;

		public CustomerBuilder() {
			super();
		}

		public CustomerBuilder(PasswordEncoder passwordEncoder) {
			this.passwordEncoder = passwordEncoder;
		}

		public CustomerBuilder withCustomerId(UUID customerId) {
			this.customerId = customerId;
			return this;
		}

		public CustomerBuilder withUsername(String username) {
			this.username = username;
			return this;
		}

		public CustomerBuilder withPassword(String password) {
			this.password = password;
			return this;
		}

		public CustomerBuilder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public CustomerBuilder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public CustomerBuilder withAddress(String address) {
			this.address = address;
			return this;
		}

		public CustomerBuilder withPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public CustomerBuilder withEmail(String email) {
			this.email = email;
			return this;
		}

		public CustomerBuilder withFileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		public CustomerBuilder withRole(Role role) {
			this.role = role;
			return this;
		}

		public Customer build() {
	        if (passwordEncoder != null) {
	            this.password = passwordEncoder.encode(password);
	        }
	        return new Customer(username, password, firstName, lastName, address, phone, email, fileName, role);
	    }
	}
}
