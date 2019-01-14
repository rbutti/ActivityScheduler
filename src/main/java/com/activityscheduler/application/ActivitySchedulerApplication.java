package com.activityscheduler.application;

import com.activityscheduler.constant.AppConfiguration;
import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.domain.ActivitySchedule;
import com.activityscheduler.exception.ErrorCode;
import com.activityscheduler.exception.SchedulerApplicationException;
import com.activityscheduler.service.ActivitySchedulerService;
import com.activityscheduler.service.impl.ActivitySchedulerServiceImpl;

public class ActivitySchedulerApplication {

	private static final String ERROR_MESSAGE = "Failed to include input activites filePath as command line argument \n"
			+ " SYNTAX : java -jar activity-scheduler.jar [FILE PATH] \n"
			+ " EXAMPLE: java -jar activity-scheduler.jar C:\\Users\\activities.txt \n";

	public static void main(String[] args) throws SchedulerApplicationException {

		ActivitySchedulerService schedulerService = new ActivitySchedulerServiceImpl();
		try {

			String fileName;
			if (args.length > 0) {
				fileName = args[0];
			} else {
				System.out.println(ERROR_MESSAGE);
				throw new SchedulerApplicationException(ERROR_MESSAGE, ErrorCode.APPLICATION_ERROR);
			}
			ActivityCatalog activityCatalog = schedulerService.parseActivityCatalog(fileName);
			ActivitySchedule activitySchedule = schedulerService.generateSchedule(activityCatalog,
					AppConfiguration.getEventInfo());
			schedulerService.printSchedule(activitySchedule);
		} catch (Exception exception) {
			throw new SchedulerApplicationException("Error Occurred while creating schedule for input file", exception,
					ErrorCode.UNEXPECTED_ERROR);
		}
	}

}
