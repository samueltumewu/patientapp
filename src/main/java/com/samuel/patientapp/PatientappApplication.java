package com.samuel.patientapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@Configuration
@EnableAutoConfiguration
public class PatientappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientappApplication.class, args);
	}

}
