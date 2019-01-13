package com.activityscheduler.facade;

import com.activityscheduler.domain.ActivitySchedule;
import com.activityscheduler.exception.SchedulerFacadeException;

public interface ScheduleWriterFacade {
	public void printSchedule(ActivitySchedule activitySchedule) throws SchedulerFacadeException;
}
