package com.example.resttemplatesandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ResttemplatesandboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResttemplatesandboxApplication.class, args);
	}

}
