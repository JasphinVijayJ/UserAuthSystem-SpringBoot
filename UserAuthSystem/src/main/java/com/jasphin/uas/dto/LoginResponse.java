package com.jasphin.uas.dto;

public class LoginResponse {

	private String token;
	private String message;

	public LoginResponse(String token, String message) {
		this.token = token;
		this.message = message;
	}
	
	// getters and setters
	public String getToken() {
	    return token;
	}

	public String getMessage() {
	    return message;
	}

}


//	Spring Boot uses Jackson to convert Java objects to JSON.
//	Jackson needs public getters to access private fields.
//	Without getters, your JSON response will be empty: