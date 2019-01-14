package com.activityscheduler.constant;

/**
 * This class consist of all the error messages used in the application.
 * 
 * @author rbutti
 *
 */
public final class ErrorMessages {

	// Error message when input validation failed
	public static final String INPUT_VALIDATION_ERROR_MESSAGE = "Failed to include input activites filePath as command line argument \n"
			+ " SYNTAX : java -jar activity-scheduler.jar [FILE PATH] \n"
			+ " EXAMPLE: java -jar activity-scheduler.jar C:\\Users\\activities.txt \n";

	// Error message when application fails
	public static final String APPLICATION_FAILED_ERROR_MESSAGE = "Application failed to generate schedule witht he given inputs";

	// Error message when input file has activity without duration or mentioned as
	// sprint
	public static final String INVALID_RECORD_TYPE = "Invalid record found in the input at line : %d "
			+ "Input should either end with min or sprint keyword";

	// Error message when failing to read from the file
	public static final String FILE_READ_FAILED = "Unable to parse the input file";

	// Error message when failing to write to a file
	public static final String FAILED_TO_WRITE_TO_FILE = "Error occured while writing output to a file";

	// Error message for failures during scheduling
	public static final String SCHEDULING_STRATEGY_ERROR = "Error occurred while schedulign the activities";

}
