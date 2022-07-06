package com.springbootexpert.vendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.springbootexpert.vendas"})
public class VendasApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
