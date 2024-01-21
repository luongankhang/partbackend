package com.api.module.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.api.module.product.mappers.SupplierMapper;

@SpringBootApplication
@ComponentScan(basePackages = "com.api.module.product")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
