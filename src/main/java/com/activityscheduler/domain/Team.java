package com.activityscheduler.domain;

import java.time.LocalTime;
import java.util.List;

public class Team extends AbstractDomainObject {

	private static final long serialVersionUID = -8400309572306576701L;

	private String name;
	private EventInfo eventInfo;
	private ActivityCatalog activityCatalog;


	public Team(EventInfo eventInfo) {
		super();
		this.eventInfo = eventInfo;
		activityCatalog = new ActivityCatalog();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ActivityCatalog getActivityCatalog() {
		return activityCatalog;
	}

	public void setActivityCatalog(ActivityCatalog activityCatalog) {
		this.activityCatalog = activityCatalog;
	}

	public Activity addActivity(Activity activity) {

		activity.setStartTime(getNextActivityStartTime());
		activityCatalog.addActivity(activity);
		return activity;
	}
	

	public List<Activity> addScheduledActivities(List<Activity> activities) {
		
		 LocalTime activityStartTime = getNextActivityStartTime();
		
		for(Activity activity:activities) {
			activity.setStartTime(activityStartTime);
			activityStartTime = activityStartTime.plusMinutes(activity.getDuration());
		}
		
		activityCatalog.addAllActivities(activities);
		return activities;
	}

	public LocalTime getNextActivityStartTime() {
		LocalTime activityStartTime = eventInfo.getEventStartTime();
		
		if(activityCatalog.hasActivities()) {
			Activity lastScheduledActivity = activityCatalog.getLastScheduledActivity();
			activityStartTime = lastScheduledActivity.getStartTime().plusMinutes(lastScheduledActivity.getDuration());
		}
		return activityStartTime;
	}

}
