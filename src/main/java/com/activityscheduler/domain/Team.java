package com.activityscheduler.domain;

import java.time.LocalTime;
import java.util.List;

/**
 * A domain object extending {@link AbstractDomainObject} to hold the event
 * specific information
 * 
 * This class consist of following domain driven design method
 * <ul>
 * <li>addScheduledActivities() - Merge all the scheduled input activities to
 * the team catalog
 * <li>addActivity() - Add the input activity to the team catalog
 * <li>getNextActivityStartTime() - Get the Start time of the next activity of
 * the team considering the end time of the existing activity
 * </ul>
 * 
 * @author rbutti
 *
 */
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

	/**
	 * This helper method adds the input activity to the team's catalog
	 * 
	 * @return : true if the addition is successful or else false
	 */
	public boolean addActivity(Activity activity) {

		activity.setStartTime(getNextActivityStartTime());
		return activityCatalog.addActivity(activity);
	}

	/**
	 * This helper method adds all the input activities to the team's catalog and
	 * sets the start time of the activity based on scheduled end time of the last
	 * activity
	 * 
	 * @return : true if the addition is successful or else false
	 */
	public boolean addScheduledActivities(List<Activity> activities) {

		LocalTime activityStartTime = getNextActivityStartTime();

		for (Activity activity : activities) {
			activity.setStartTime(activityStartTime);
			activityStartTime = activityStartTime.plusMinutes(activity.getDuration());
		}

		return activityCatalog.addAllActivities(activities);
	}

	/**
	 * This helper method gets start time of next activity based on the end time of
	 * the last activity assigned to the team.
	 * 
	 * @return : true if the addition is successful or else false
	 */
	public LocalTime getNextActivityStartTime() {
		LocalTime activityStartTime = eventInfo.getEventStartTime();

		if (activityCatalog.hasActivities()) {

			// Generating the start time of the next activity based on the last activity in
			// the team's catalog
			Activity lastScheduledActivity = activityCatalog.getLastActivity();
			activityStartTime = lastScheduledActivity.getStartTime().plusMinutes(lastScheduledActivity.getDuration());
		}
		return activityStartTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((activityCatalog == null) ? 0 : activityCatalog.hashCode());
		result = prime * result + ((eventInfo == null) ? 0 : eventInfo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Team other = (Team) obj;
		if (activityCatalog == null) {
			if (other.activityCatalog != null)
				return false;
		} else if (!activityCatalog.equals(other.activityCatalog))
			return false;
		if (eventInfo == null) {
			if (other.eventInfo != null)
				return false;
		} else if (!eventInfo.equals(other.eventInfo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Team [name=" + name + ", eventInfo=" + eventInfo + ", activityCatalog=" + activityCatalog + "]";
	}
}
