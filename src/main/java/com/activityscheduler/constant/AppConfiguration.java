package com.activityscheduler.constant;

import java.time.LocalTime;

import com.activityscheduler.domain.EventInfo;

public class AppConfiguration {

	public static final String BEANIO_ACTIVITY_CONF_FILE = "beanio-activity-configuration.xml";
	public static final String BEANIO_ACTIVITY_READER = "ActivityReader";
	public static final String SCHEDULE_OUTPUT_FILE = "./activity-schedule.txt";
	
	public static EventInfo getEventInfo() {
		return new EventInfo(LocalTime.of(9, 0, 0), LocalTime.of(17, 0, 0), LocalTime.of(11, 0, 0));
	}
}
