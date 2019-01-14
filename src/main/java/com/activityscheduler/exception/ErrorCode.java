package com.activityscheduler.exception;

/**
 * An enumerator to hold the applicaiton error codes
 * 
 * @author rbutti
 *
 */
public enum ErrorCode {

	UNEXPECTED_ERROR("ERR001"), // Unexpected errors in the application
	STRATEGY_ENGINE_ERROR("ERR002"), // errors occuring in the strategy layer
	FACADE_ERROR("ERR003"), // errors occuring in the facade layer
	SERVICE_ERROR("ERR004"), // errors occuring in the service layer
	APPLICATION_ERROR("ERR005"); // errors occuring in the application layer

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
