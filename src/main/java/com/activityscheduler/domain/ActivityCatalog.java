package com.activityscheduler.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A domain class extending {@link AbstractDomainObject} to hold the activity
 * catalog information consisting of list of activites
 * 
 * This class consist of following domain driven design method
 * <ul>
 * <li>addAllActivities() - Merge all the input activities to the catalog *
 * <li>addActivity() - Add the input activity to the catalog
 * <li>getActivityCount() - get the count of all the activities in the catalog
 * <li>getActivity(index) - get the activity in the catalog at the given index
 * <li>getLastActivity() - Get the last activity in the catalog that has the
 * status as scheduled *
 * <li>extractScheduledActivities() - Extract all the activities in the catalog
 * that has the status as scheduled
 * <li>hasActivities() - Return true if the catalog as atleast one activity .
 * Doesnt consider the mandatory activities in the catalog
 * </ul>
 * 
 * @author rbutti
 * @see AbstractDomainObject
 *
 */
public class ActivityCatalog extends AbstractDomainObject {

	private static final long serialVersionUID = 3471695252714607381L;

	List<Activity> activities;

	public ActivityCatalog() {

		super();
		activities = new ArrayList<>();
		Activity mandatoryActivity = new Activity(15, DeloitteAwayDayEventInfo.MANDATORY_STAFF_MOTIVATION_PRESENTATION);
		mandatoryActivity.setStartTime(LocalTime.of(17, 0, 0));
		addActivity(mandatoryActivity);
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activitiesList) {
		this.activities = activitiesList;
	}

	/**
	 * This helper method adds all the input activities to the catalog. It doesnt
	 * override any of the existing information
	 * 
	 * @param activities - Activities to be added
	 * @return : true if the addition is successful or else false
	 */
	public boolean addAllActivities(List<Activity> activities) {
		return this.activities.addAll(activities);
	}

	/**
	 * This helper method adds the input activity to the catalog
	 * 
	 * @param activity - Activity to be added
	 * @return : true if the addition is successful or else false
	 */
	public boolean addActivity(Activity activity) {
		return activities.add(activity);
	}

	/**
	 * This helper method returns the count of activities in the catalog
	 * 
	 * @return : Count of all the activities in the catalog
	 */
	public int getActivityCount() {
		return activities.size();
	}

	/**
	 * This helper method returns the activity at a given index in the catalog
	 * @param index - Activity index
	 * @return : Count of all the activities in the catalog
	 */
	public Activity getActivity(int index) {
		return activities.get(index);
	}

	/**
	 * This helper method last activity in the catalog
	 * 
	 * @return : Last Activity in the catalog
	 */
	public Activity getLastActivity() {

		return activities.get(activities.size() - 1);
	}

	/**
	 * This helper method extracts all the activities in the catalog that has the
	 * status as scheduled
	 * 
	 * @return : List of activities with the status as Scheduled
	 */
	public List<Activity> extractScheduledActivities() {

		List<Activity> scheduledActivities = activities.stream().filter(Activity::isScheduled)
				.collect(Collectors.toList());
		activities.removeAll(scheduledActivities);

		return scheduledActivities;
	}

	/**
	 * This helper method checks if there are any activities in the catalog that is
	 * not mandatory
	 * 
	 * @return : true if the catalog has activities.
	 */
	public boolean hasActivities() {
		return (activities.size() > 1);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((activities == null) ? 0 : activities.hashCode());
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
		ActivityCatalog other = (ActivityCatalog) obj;
		if (activities == null) {
			if (other.activities != null)
				return false;
		} else if (!activities.equals(other.activities))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ActivityCatalog [activities=" + activities + "]";
	}

}
