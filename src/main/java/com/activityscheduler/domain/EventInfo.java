package com.activityscheduler.domain;

import java.time.LocalTime;

public class EventInfo {
	
	private LocalTime eventStartTime;
	private LocalTime eventEndTime;
	private LocalTime lunchStartTime;

		
	public EventInfo(LocalTime eventStartTime, LocalTime eventEndTime, LocalTime lunchStartTime) {
		super();
		this.eventStartTime = eventStartTime;
		this.eventEndTime = eventEndTime;
		this.lunchStartTime = lunchStartTime;
	}
	public LocalTime getEventStartTime() {
		return eventStartTime;
	}
	public void setEventStartTime(LocalTime eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	public LocalTime getEventEndTime() {
		return eventEndTime;
	}
	public void setEventEndTime(LocalTime eventEndTime) {
		this.eventEndTime = eventEndTime;
	}
	public LocalTime getLunchStartTime() {
		return lunchStartTime;
	}
	public void setLunchStartTime(LocalTime lunchStartTime) {
		this.lunchStartTime = lunchStartTime;
	}
	
}
