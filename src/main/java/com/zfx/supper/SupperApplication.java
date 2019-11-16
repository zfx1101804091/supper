package com.zfx.supper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.zfx.supper.mapper")
public class SupperApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupperApplication.class, args);
	}

}
