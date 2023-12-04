package com.dnb.sparkdemo.response;

public enum StatusResponse {
	SUCCESS("success"), ERROR("error");
	
	final private String status;
	
	StatusResponse(String status){
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
}
