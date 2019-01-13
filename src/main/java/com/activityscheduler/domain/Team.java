package com.activityscheduler.domain;

public class Team extends AbstractDomainObject {

	private static final long serialVersionUID = -8400309572306576701L;

	private String name;
	private ActivityCatalog activityCatalog;

	public Team(String name) {
		super();
		this.name = name;
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
		activityCatalog.addActivtiy(activity);
		return activity;
	}

}
