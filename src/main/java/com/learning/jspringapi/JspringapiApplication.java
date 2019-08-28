package com.learning.jspringapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JspringapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JspringapiApplication.class, args);
	}

}
