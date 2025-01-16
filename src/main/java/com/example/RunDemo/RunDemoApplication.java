package com.example.RunDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class RunDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunDemoApplication.class, args);
	}

}
