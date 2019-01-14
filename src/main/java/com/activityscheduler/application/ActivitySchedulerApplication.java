package com.activityscheduler.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.activityscheduler.constant.AppConfiguration;
import com.activityscheduler.constant.ErrorMessages;
import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.domain.ActivitySchedule;
import com.activityscheduler.exception.ErrorCode;
import com.activityscheduler.exception.SchedulerApplicationException;
import com.activityscheduler.service.ActivitySchedulerService;
import com.activityscheduler.service.impl.ActivitySchedulerServiceImpl;

public class ActivitySchedulerApplication {

	private static Logger logger = LoggerFactory.getLogger(ActivitySchedulerApplication.class);

	public static void main(String[] args) throws SchedulerApplicationException {

		ActivitySchedulerService schedulerService = new ActivitySchedulerServiceImpl();
		try {
			if (args == null || args.length <= 0) {
				System.err.println(ErrorMessages.INPUT_VALIDATION_ERROR_MESSAGE);
				throw new SchedulerApplicationException(ErrorMessages.INPUT_VALIDATION_ERROR_MESSAGE,
						ErrorCode.APPLICATION_ERROR);
			}

			ActivityCatalog activityCatalog = schedulerService.parseActivityCatalog(args[0]);
			ActivitySchedule activitySchedule = schedulerService.generateSchedule(activityCatalog,
					AppConfiguration.getEventInfo());
			schedulerService.printSchedule(activitySchedule);
		} catch (Exception exception) {
			logger.error(ErrorMessages.APPLICATION_FAILED_ERROR_MESSAGE, exception);
		}
	}
}
