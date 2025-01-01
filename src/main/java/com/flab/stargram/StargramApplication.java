package com.flab.stargram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StargramApplication {

	public static void main(String[] args) {
		SpringApplication.run(StargramApplication.class, args);
	}

}
