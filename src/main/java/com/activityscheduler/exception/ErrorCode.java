package com.activityscheduler.exception;

public enum ErrorCode {
	UNEXPECTED_ERROR("ERR001"), STRATEGY_ENGINE_ERROR("ERR002"), FACADE_ERROR("ERR003"), SERVICE_ERROR("ERR004"),
	APPLICATION_ERROR("ERR005");

	// declaring private variable for getting values
	private String code;

	// getter method
	public String getCode() {
		return this.code;
	}

	// enum constructor - cannot be public or protected
	private ErrorCode(String code) {
		this.code = code;
	}
}
