package com.shivdattTrust.bank.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordGenerator {

	CommandLineRunner generatePassword(PasswordEncoder encoder) {
		return args -> {
			
			String hash = encoder.encode("admin123");
			
			System.out.println("Password : admin123");
			System.out.println("Bcrypt Hash: "+hash);
		};
	}
}
