
package com.glofox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GlofoxApplication {

	// This Bare minimum functionality application is created using mvc
	// architechture using springboot and java features having a in memory database
	// and the end goal being to convert into a docker image and deploy
	public static void main(String[] args) {
		SpringApplication.run(GlofoxApplication.class, args);
	}
}
