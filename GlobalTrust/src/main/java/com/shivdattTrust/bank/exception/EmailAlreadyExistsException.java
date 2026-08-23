package com.shivdattTrust.bank.exception;

public class EmailAlreadyExistsException extends RuntimeException {
	
	public EmailAlreadyExistsException(String email) {
		super("An account with email : '"+ email + "' allaready exists");
		// TODO Auto-generated constructor stub
	}

}
