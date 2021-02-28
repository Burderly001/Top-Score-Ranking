package com.burderly.topranking.entity;



public class ResponseJson {
	 
	private String code;
	private String error;
	
	private Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setMessage(Object data) {
		this.data = data;
	}
	
	
	
	

}
