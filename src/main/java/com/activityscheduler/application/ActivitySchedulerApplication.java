package com.activityscheduler.application;

import com.activityscheduler.constant.AppConfiguration;
import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.domain.ActivitySchedule;
import com.activityscheduler.exception.ErrorCode;
import com.activityscheduler.exception.SchedulerApplicationException;
import com.activityscheduler.service.ActivitySchedulerService;
import com.activityscheduler.service.impl.ActivitySchedulerServiceImpl;

public class ActivitySchedulerApplication {

	public static void main(String[] args) throws SchedulerApplicationException {

		ActivitySchedulerService schedulerService = new ActivitySchedulerServiceImpl();
		try {

			String fileName;
			if (args.length > 0) {
				fileName = args[0];
			} else {
				System.out.println(
						"NOTE : File containing list of activities is not provided as command line argument. "
						+ "Will process a default file and provide output");
				fileName = "src/test/resources/activities.txt";
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
