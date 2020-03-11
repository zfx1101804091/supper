package com.zfx.supper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SpringBootApplication
public class SupperApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupperApplication.class, args);
	}

}
