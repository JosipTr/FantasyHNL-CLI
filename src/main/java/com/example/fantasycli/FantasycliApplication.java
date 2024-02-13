package com.example.fantasycli;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class FantasycliApplication {

	public static void main(String[] args) {
//		SpringApplication.run(FantasycliApplication.class, args);
		new SpringApplicationBuilder(FantasycliApplication.class)
        .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
        .run(args);
	}

}
