package com.activityscheduler.facade.impl;

import com.activityscheduler.domain.ActivitySchedule;
import com.activityscheduler.facade.ScheduleWriterFacade;

public class ConsoleScheduleWriterFacadeImpl implements ScheduleWriterFacade {

	@Override
	public void printSchedule(ActivitySchedule activitySchedule) {
		System.out.println(activitySchedule.print());
	}
}
