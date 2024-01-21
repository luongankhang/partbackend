package com.api.module.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.api.module.order")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
