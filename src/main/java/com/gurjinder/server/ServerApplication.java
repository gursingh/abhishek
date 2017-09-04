package com.gurjinder.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

@SpringBootApplication
public class ServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(ServerApplication.class);
	}

	@Bean
	public org.springframework.security.authentication.encoding.PasswordEncoder passwordEncoder() {
		return new MessageDigestPasswordEncoder("MD5");
	}
}
