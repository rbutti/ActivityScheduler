package com.activityscheduler.exception;

/**
 * The ActivitySchedulerStrategyException wraps all checked standard Java
 * exception and enriches them with a custom error code. You can use this code
 * to retrieve localized error messages and to link to our online documentation.
 * 
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
}
