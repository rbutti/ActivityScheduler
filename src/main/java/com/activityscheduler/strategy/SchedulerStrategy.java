package com.activityscheduler.strategy;

import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.exception.SchedulerStrategyException;

/**
 * An Strategy interface for scheduling a list of activities for a event
 * <p>
 * Concrete implementation of this interface should schedule a list of
 * activities for a scheduledDuration and return the schedule
 * 
 * @author rbutti
 *
 */
public interface SchedulerStrategy {

	/**
	 * Concrete implementation of this method should schedule a list of activities
	 * for a scheduledDuration and return the schedule
	 * 
	 * @param activities       - Catalog of activities to be scheduled
	 * @param scheduleDuration - Duration of schedule
	 * @return - Schedule for activities for input duration
	 * @throws SchedulerStrategyException : Strategy exception
	 */
	public ActivityCatalog schedule(ActivityCatalog activities, int scheduleDuration) throws SchedulerStrategyException;
}
