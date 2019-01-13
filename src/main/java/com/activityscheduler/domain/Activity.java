package com.activityscheduler.domain;

public class Activity extends AbstractDomainObject {

	private static final long serialVersionUID = -7068428055267646238L;

	private final int duration;
	private boolean scheduled;
	private final String name;

	public Activity(int duration, String name) {
		super();
		this.duration = duration;
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

}
