package com.activityscheduler.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Activity extends AbstractDomainObject {

	private static final long serialVersionUID = -7068428055267646238L;

	private  int duration;
	private boolean scheduled;
	private  String name;
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

	public String getStartTimeString() {

		if (startTime != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
			return startTime.format(dateTimeFormatter);
		} else {
			return new String();
		}
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "Activity [duration=" + duration + ", scheduled=" + scheduled + ", name=" + name + ", startTime="
				+ startTime + "]";
	}

}
