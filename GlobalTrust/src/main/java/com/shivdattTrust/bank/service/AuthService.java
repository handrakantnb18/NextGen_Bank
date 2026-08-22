package com.shivdattTrust.bank.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shivdattTrust.bank.dto.AuthResponse;
import com.shivdattTrust.bank.dto.LoginRequest;
import com.shivdattTrust.bank.dto.RegisterRequest;
import com.shivdattTrust.bank.entity.Role;
import com.shivdattTrust.bank.exception.EmailAlreadyExistsException;
import com.shivdattTrust.bank.exception.InvalidCredentialsException;
import com.shivdattTrust.bank.repository.UserRepository;
import com.shivdattTrust.bank.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	private JwtUtil jwtUtil;
	
	
	public AuthResponse register(RegisterRequest request)
	{
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new EmailAlreadyExistsException(request.getEmail());
		}
		
		User user = User.builder()
				.name(request.getName())
				.email(request.getEmail())
				.phone(request.getPhone())
				.passwordHash(passwordEncoder.encode(request.getPassword()))
				.role(Role.CUSTOMER)
				.active(true)
				.build();
		
		User saved = userRepository.save(user);
		String token = jwtUtil.generateToken(saved);
		
		return AuthResponse.of(token, saved.getId(), saved.getName(), saved.getEmail(), saved.getRole().name());
		
		public AuthResponse login(LoginRequest request)
		{
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			}
			catch(BadCredentialsException ex) {
				throw new InvalidCredentialsException();
			}
			
			User user = 
		}
		
	}
}
