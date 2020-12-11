package com.uni7.college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollegeApplication {

	public static void main(String[] args) {
		// modificando context-path para "/api" e startando aplicação
		System.setProperty("server.servlet.context-path", "/api");
		SpringApplication.run(CollegeApplication.class, args);
	}

}