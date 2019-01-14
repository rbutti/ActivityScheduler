package com.activityscheduler.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.activityscheduler.constant.AppConfiguration;

/**
 * A domain class extending {@link AbstractDomainObject} to hold the activity
 * information like the name, duration etc
 * <p>
 * 
 * This class consist of following domain driven design method
 * <ul>
 * <li>getFormattedStartTime() - To format start time into application specific
 * format
 * </ul>
 * 
 * @author rbutti
 * @see AbstractDomainObject
 *
 */
public class Activity extends AbstractDomainObject {

	private static final long serialVersionUID = -7068428055267646238L;

	private int duration;
	private boolean scheduled;
	private String name;
	private LocalTime startTime;

	public Activity() {
		super();
	}

	public Activity(int duration, String name) {
		super();
		this.duration = duration;
		this.name = name;
	}

	public Activity(int duration, String name, boolean scheduled) {
		super();
		this.duration = duration;
		this.scheduled = scheduled;
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public boolean isScheduled() {
		return scheduled;
	}

	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	public String getName() {
		return name;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	/**
	 * This helper method converts the Start time of the activity to an Application
	 * specific print format
	 * 
	 * @return : StartTime in the format "hh:mm a" or empty string if Start time is
	 *         not set
	 */
	public String getFormattedStartedTime() {

		if (startTime != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(AppConfiguration.DEFAULT_TIME_FORMAT);
			return startTime.format(dateTimeFormatter);
		} else {
			return new String();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + duration;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (scheduled ? 1231 : 1237);
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (duration != other.duration)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (scheduled != other.scheduled)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Activity [duration=" + duration + ", scheduled=" + scheduled + ", name=" + name + ", startTime="
				+ startTime + "]";
	}

}
