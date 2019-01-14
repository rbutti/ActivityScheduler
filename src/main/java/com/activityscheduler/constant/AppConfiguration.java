package com.activityscheduler.constant;

import java.time.LocalTime;

import org.apache.commons.lang3.StringUtils;

import com.activityscheduler.domain.DeloitteAwayDayEventInfo;
import com.activityscheduler.domain.EventInfo;

/**
 * An Configuration file for Activity Scheduler Application containing important
 * information required for the application
 * 
 * @author rbutti
 *
 */
public final class AppConfiguration {

	// Name of the BeanIO configuration file containing information regarding
	// Activity object
	public static final String BEANIO_ACTIVITY_CONF_FILE = "beanio-activity-configuration.xml";

	// Name of the BeanIO reader to parse data from a file to Activity object
	public static final String BEANIO_ACTIVITY_READER = "ActivityReader";

	// Name of the output next file
	public static final String SCHEDULE_OUTPUT_FILE = "./activity-schedule.txt";
	
	// Application's Default Time format
	public static final String DEFAULT_TIME_FORMAT = "hh:mm a";
	
	// Default sprint activity duration
	public static final String DEFAULT_SPRINT_DURATION = "15";

	/**
	 * A static method that return the event information based on the input event
	 * name
	 * 
	 * @param eventName - Event name
	 * @return - Event information for given input name
	 */
	public static EventInfo getEventInfo(String eventName) {

		if (!StringUtils.isEmpty(eventName)
				&& eventName.equalsIgnoreCase(DeloitteAwayDayEventInfo.DELOITTE_DIGITAL_AWAY_DAY_EVENT)) {
			return new DeloitteAwayDayEventInfo(LocalTime.of(9, 0, 0), LocalTime.of(17, 0, 0), LocalTime.of(12, 0, 0));
		} else { 
			return new EventInfo(EventInfo.DEFAULT_EVENT_NAME, LocalTime.of(9, 0, 0), LocalTime.of(17, 0, 0));

		}
	}
}
