package com.dnb.sparkdemo.response;

import com.google.gson.JsonElement;

import lombok.Data;

@Data
public class UserResponse {
	private String message;
	private StatusResponse status;
	private JsonElement data;
	
	public UserResponse(String message, StatusResponse status, JsonElement data) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
	}

	public UserResponse(String message, StatusResponse status) {
		super();
		this.message = message;
		this.status = status;
	}

	public UserResponse(StatusResponse status) {
		super();
		this.status = status;
	}
	
	
}
