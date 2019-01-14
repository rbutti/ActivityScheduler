package com.activityscheduler.constant;

public class ErrorMessages {

	public static final String INPUT_VALIDATION_ERROR_MESSAGE = "Failed to include input activites filePath as command line argument \n"
			+ " SYNTAX : java -jar activity-scheduler.jar [FILE PATH] \n"
			+ " EXAMPLE: java -jar activity-scheduler.jar C:\\Users\\activities.txt \n";

	public static final String APPLICATION_FAILED_ERROR_MESSAGE = "Error Occurred while creating schedule for input file";

}
