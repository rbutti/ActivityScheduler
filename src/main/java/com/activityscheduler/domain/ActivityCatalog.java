package com.activityscheduler.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityCatalog extends AbstractDomainObject {

	private static final long serialVersionUID = 3471695252714607381L;

	List<Activity> activities;

	public ActivityCatalog() {
		
		super();
		activities = new ArrayList<>();
		Activity mandatoryActivity = new Activity(15, "Staff Motivation Presentation");
		mandatoryActivity.setStartTime(LocalTime.of(17, 0, 0));
		addActivity(mandatoryActivity);
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activitiesList) {
		this.activities = activitiesList;
	}

	public boolean addAllActivities(List<Activity> activities) {
		return this.activities.addAll(activities);
	}

	public boolean addActivtiy(String name, int duration) {
		return addActivity(name, duration, false);
	}

	public boolean addActivity(String name, int duration, boolean isScheduled) {

		Activity activity = new Activity(duration, name, isScheduled);
		return addActivity(activity);
	}

	public boolean addActivity(Activity activity) {
		return activities.add(activity);
	}

	public int getActivityCount() {
		return activities.size();
	}

	public Activity getActivity(int index) {
		return activities.get(index);
	}
	
	public Activity getLastScheduledActivity() {
		
		return activities.get(activities.size() -1);
	}

	public List<Activity> extractScheduledActivities() {

		List<Activity> scheduledActivities = activities.stream().filter(Activity::isScheduled).collect(Collectors.toList());
		activities.removeAll(scheduledActivities);
		
		return scheduledActivities;
	}

	public boolean hasActivities() {
		return (activities.size() > 1);
	}
	
}


