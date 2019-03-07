package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TestAppApplication {

	private static ApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(TestAppApplication.class, args);
	}
	
	public static ApplicationContext getContext() {
		return context;
	}

}
