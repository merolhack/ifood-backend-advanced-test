package com.sindelantal.ifood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.sindelantal.ifood")
@SpringBootApplication
public class IfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfoodApplication.class, args);
	}

}
