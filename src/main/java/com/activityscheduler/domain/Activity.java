package com.activityscheduler.domain;

public class Activity extends AbstractDomainObject {

	private static final long serialVersionUID = -7068428055267646238L;
	
	private int duration;
	private boolean scheduled;
	private String name;

	public Activity(int duration, String name) {
		super();
		this.duration = duration;
		this.name = name;
	}

	public Activity(int duration) {
		super();
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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

	public void setName(String name) {
		this.name = name;
	}

}
