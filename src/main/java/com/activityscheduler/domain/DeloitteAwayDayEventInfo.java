package com.activityscheduler.domain;

import java.time.LocalTime;

/**
 * A domain object extending {@link EventInfo} to hold the event specific
 * information for Deloitte Digital Away day
 * 
 * @author rbutti
 *
 */
public class DeloitteAwayDayEventInfo extends EventInfo {

	private static final long serialVersionUID = -4200801522467421368L;

	// Default name of the event
	public static final String DELOITTE_DIGITAL_AWAY_DAY_EVENT = "Deloitte Digital Away Day";

	// Staff Motivation Presentation
	public static final String MANDATORY_STAFF_MOTIVATION_PRESENTATION = "Staff Motivation Presentation";

	// Lunch break
	public static final String MANDATORY_LUNCH_BREAK = "Lunch Break";

	public DeloitteAwayDayEventInfo(LocalTime eventStartTime, LocalTime eventEndTime, LocalTime lunchStartTime) {
		super(DELOITTE_DIGITAL_AWAY_DAY_EVENT, eventStartTime, eventEndTime);
	}

	/**
	 * An helper method to run the Lunch Activity for Deloitte Digital Away Day
	 * event
	 * 
	 * @return
	 */
	public Activity getLunchActivity() {
		Activity activity = new Activity(60, DeloitteAwayDayEventInfo.MANDATORY_LUNCH_BREAK, true);
		activity.setStartTime(LocalTime.of(12, 0, 0));
		return activity;
	}

}
