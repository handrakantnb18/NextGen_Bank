package com.shivdattTrust.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email must be required")
	private String email;
	
	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]$", message = "Phone number is must be 10 digits")
	private String phone;
	
	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 charactors")
	private String password;
}
