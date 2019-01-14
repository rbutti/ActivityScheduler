package com.activityscheduler.facade;

import com.activityscheduler.domain.ActivitySchedule;
import com.activityscheduler.exception.SchedulerFacadeException;

/**
 * Facade interface for writing activity schedule to a source in a human
 * readable format <p>
 * Concrete implementation of this interface should implement logic to write the
 * activity schedule to a source in an human readable format
 * 
 * @author rbutti
 *
 */
public interface ScheduleWriterFacade {

	/**
	 * Concrete implementation of this method should implement logic to write the
	 * activity schedule to a source in an human readable format
	 * 
	 * @param activitySchedule - Activity schedule to be written to a source
	 * @throws SchedulerFacadeException : Facade Exception
	 */
	public void printSchedule(ActivitySchedule activitySchedule) throws SchedulerFacadeException;
}
