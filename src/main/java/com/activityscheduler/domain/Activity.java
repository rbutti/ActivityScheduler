package com.activityscheduler.domain;

public class Activity {

	private int duration;

	private boolean scheduled;

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

}
