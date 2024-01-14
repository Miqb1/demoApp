package com.klaudiusz.demonstration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemonstrationApplication {

	private final static Logger LOGGER = LoggerFactory.getLogger(DemonstrationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemonstrationApplication.class, args);
		LOGGER.warn("Let's go!");
	}
}