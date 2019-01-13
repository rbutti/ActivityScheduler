package com.activityscheduler.exception;

public enum ErrorCode {
	UNEXPECTED_ERROR(001);

	// declaring private variable for getting values
	private int code;

	// getter method
	public int getCode() {
		return this.code;
	}

	// enum constructor - cannot be public or protected
	private ErrorCode(int code) {
		this.code = code;
	}
}
