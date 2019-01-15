package com.activityscheduler.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.activityscheduler.constant.AppConfiguration;
import com.activityscheduler.constant.ErrorMessages;
import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.domain.ActivitySchedule;
import com.activityscheduler.domain.DeloitteAwayDayEventInfo;
import com.activityscheduler.exception.ErrorCode;
import com.activityscheduler.exception.SchedulerApplicationException;
import com.activityscheduler.service.ActivitySchedulerService;
import com.activityscheduler.service.impl.ActivitySchedulerServiceImpl;

/**
 * An Activity Scheduler Application for planning events.
 * <p>
 * This application has the following features:
 * <ul>
 * <li>The catalog of activities for the event will be read from an input file.
 * Input file format :
 * https://github.com/rbutti/ActivityScheduler/blob/master/src/test/resources/activities.txt
 * <li>The employees will be divided into various teams and each team will be
 * performing various activities in a day
 * <li>Output will be printed on console and to a file generate in the same
 * directory as the application
 * <li>Application schedules activities using Greedy Algorithm to fit in maximum
 * activities in a given day (A flavor of Dynamic Programming Knapsack problem)
 * </ul>
 * 
 * <p>
 * Current implementation supports scheduling of activities for the following
 * event
 * <ul>
 * <li>Deloitte Digital Away Day
 * </ul>
 * 
 * <p>
 * USAGE: java -jar activity-scheduler.jar [FILE PATH] <p>
 * EXAMPLE: java -jar activity-scheduler.jar C:\\Users\\activities.txt
 * 
 * @author ravikiran763 (ravikiran763@gmail.com)
 */
public class ActivitySchedulerApplication {

	private static Logger logger = LoggerFactory.getLogger(ActivitySchedulerApplication.class);

	/**
	 * Entrypoint to the application. This method performs the following activities
	 * <ul>
	 * <li>Step 1 : Read activities from input file
	 * <li>Step 2 : Generate the schedule of activities
	 * <li>Step 3 : Print schedule to a file and console
	 * </ul>
	 * 
	 * @param args - Command line argument. Should contain a path for the input file
	 *             containing a list of activities.
	 */
	public static void main(String[] args) {

		logger.info("Activity Scheduler Application Started");
		ActivitySchedulerService schedulerService = new ActivitySchedulerServiceImpl();
		try {
			// validate input
			if (args == null || args.length <= 0) {
				System.err.println(ErrorMessages.INPUT_VALIDATION_ERROR_MESSAGE);
				throw new SchedulerApplicationException(ErrorMessages.INPUT_VALIDATION_ERROR_MESSAGE,
						ErrorCode.APPLICATION_ERROR);
			}
			logger.trace("Input file path provided by the user : {}", args[0]);

			// Read activities from input file
			ActivityCatalog activityCatalog = schedulerService.parseActivityCatalog(args[0]);
			logger.trace("Read Activities : {}", activityCatalog);

			// Generate schedule for the event
			ActivitySchedule activitySchedule = schedulerService.generateSchedule(activityCatalog,
					AppConfiguration.getEventInfo(DeloitteAwayDayEventInfo.DELOITTE_DIGITAL_AWAY_DAY_EVENT));
			logger.trace("Schedule of activities : {} for event {}", activityCatalog,
					AppConfiguration.getEventInfo(DeloitteAwayDayEventInfo.DELOITTE_DIGITAL_AWAY_DAY_EVENT));

			// print the schedule on console and to a file
			schedulerService.printSchedule(activitySchedule);
			logger.trace("Printing of activities completed");

		} catch (Exception exception) {
			// Action : log error messages to console.
			logger.error(ErrorMessages.APPLICATION_FAILED_ERROR_MESSAGE, exception);
		}

		logger.info("Activity Scheduler Application Ended");
	}
}
