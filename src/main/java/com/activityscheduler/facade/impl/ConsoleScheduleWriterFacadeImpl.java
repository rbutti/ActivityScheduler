package com.activityscheduler.facade.impl;

import com.activityscheduler.domain.ActivitySchedule;
import com.activityscheduler.facade.ScheduleWriterFacade;

/**
 * An implementation of the interface {@link ScheduleWriterFacade}. This
 * implementation writes the Activity Schedule to an console
 * 
 * @author rbutti
 *
 */
public class ConsoleScheduleWriterFacadeImpl implements ScheduleWriterFacade {

	/* (non-Javadoc)
	 * @see com.activityscheduler.facade.ScheduleWriterFacade#printSchedule(com.activityscheduler.domain.ActivitySchedule)
	 */
	@Override
	public void printSchedule(ActivitySchedule activitySchedule) {
		System.out.println(activitySchedule.print());
	}
}
