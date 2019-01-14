package com.activityscheduler.exception;

/**
 * The ActivitySchedulerStrategyException wraps all checked standard Java
 * exception and enriches them with a custom error code.
 * <p>
 * This exception will be throw from application strategy layer
 * 
 */
public class SchedulerStrategyException extends Exception {
	private static final long serialVersionUID = 7718828512143293558L;

	private final ErrorCode code;

	public SchedulerStrategyException(ErrorCode code) {
		super();
		this.code = code;
	}

	public SchedulerStrategyException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public SchedulerStrategyException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public SchedulerStrategyException(Throwable cause, ErrorCode code) {
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
