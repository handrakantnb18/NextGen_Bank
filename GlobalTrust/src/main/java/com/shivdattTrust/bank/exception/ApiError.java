package com.shivdattTrust.bank.exception;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiError {

	private int status;
	private String message;
	private LocalDateTime timestamp;
	private Map<String, String> fieldErrors;
}
