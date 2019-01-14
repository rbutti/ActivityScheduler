package com.activityscheduler.exception;

/**
 * The SchedulerApplicationException wraps all checked standard Java exception
 * and enriches them with a custom error code.
 */
public class SchedulerApplicationException extends Exception {

	private static final long serialVersionUID = 7718828512143293558L;

	private final ErrorCode code;

	public SchedulerApplicationException(ErrorCode code) {
		super();
		this.code = code;
	}

	public SchedulerApplicationException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public SchedulerApplicationException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public SchedulerApplicationException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}

	public ErrorCode getErrorCode() {
		return this.code;
	}

	@Override
	public String toString() {
		return "[code=" + code + "] | " + super.toString();
	}	
}
