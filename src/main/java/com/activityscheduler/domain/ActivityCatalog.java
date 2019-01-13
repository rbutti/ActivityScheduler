package com.activityscheduler.domain;

import java.util.ArrayList;
import java.util.List;

public class ActivityCatalog extends AbstractDomainObject {

	private static final long serialVersionUID = 3471695252714607381L;

	 List<Activity> activitiesList;

	public ActivityCatalog() {
		super();
		activitiesList = new ArrayList<>();
		addActivtiy("Staff Motivation Presentation", 15);
	}

	public List<Activity> getActivitiesList() {
		return activitiesList;
	}

	public void setActivitiesList(List<Activity> activitiesList) {
		this.activitiesList = activitiesList;
	}

	public Activity addActivtiy(String name, int duration) {

		Activity activity = new Activity(duration, name);
		activitiesList.add(activity);

		return activity;
	}

	public int getActivityCount() {
		return activitiesList.size();
	}

	public Activity getActivity(int index) {
		return activitiesList.get(index);
	}

}
