package com.e_commerce_low_level.low_level_e_commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class LowLevelECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LowLevelECommerceApplication.class, args);
	}

}