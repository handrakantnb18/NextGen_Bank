package com.shivdattTrust.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GlobalTrustApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalTrustApplication.class, args);
		
		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	        String password = "admin123";

	        String encodedPassword = encoder.encode(password);

	        System.out.println("Password: " + password);
	        System.out.println("Encoded Password: " + encodedPassword);
	        
	}

}
