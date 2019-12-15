package com.zfx.supper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SupperApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupperApplication.class, args);
	}

}
