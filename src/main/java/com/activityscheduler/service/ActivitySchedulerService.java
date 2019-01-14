package com.activityscheduler.service;

import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.domain.ActivitySchedule;
import com.activityscheduler.domain.EventInfo;
import com.activityscheduler.exception.SchedulerServiceException;

/**
 * Service interface for Activity Scheduler Application.<br/>
 * Concrete implementation of this interface should implement logic to read
 * activities from a file, generate a schedule and print the schedule
 * 
 * @author rbutti
 *
 */
public interface ActivitySchedulerService {

	/**
	 * Concrete implementation of this method should generate the event schedule
	 * taking activities and event information as method inputs
	 * 
	 * @param activityCatalog - Catalog of Activities that needs to be schedule for
	 *                        the event
	 * @param eventInfo       - Event information containing start and end time of
	 *                        the event and any event specific information
	 * @return Schedule of Activities for the event
	 * @throws SchedulerServiceException
	 */
	public ActivitySchedule generateSchedule(ActivityCatalog activityCatalog, EventInfo eventInfo)
			throws SchedulerServiceException;

	/**
	 * Concrete implementation of this method should validate and read the input
	 * file and parse list of activities from the file
	 * 
	 * @param filePath - Path to the file containing list of activities
	 * @return A catalog of all the activities that needs to be scheduled
	 * @throws SchedulerServiceException
	 */
	public ActivityCatalog parseActivityCatalog(String filePath) throws SchedulerServiceException;

	/**
	 * Concrete implementation of this method should print/output the Schedule in a
	 * human understandable format
	 * 
	 * @param activitySchedule - Schedule of activities to be printed
	 * @throws SchedulerServiceException
	 */
	public void printSchedule(ActivitySchedule activitySchedule) throws SchedulerServiceException;
}
