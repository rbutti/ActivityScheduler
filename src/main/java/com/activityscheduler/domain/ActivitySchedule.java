package com.activityscheduler.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A domain class extending {@link AbstractDomainObject} to hold the activity
 * schedule information for an event
 * <p>
 * 
 * This class consist of following domain driven design method
 * <ul>
 * <li>addTeam() - Add a team to the schedule.
 * <li>getTeamCount() - Get the count of teams in the schedule
 * <li>printSchedule() - Print the schedule in human readable format
 * </ul>
 * 
 * @author rbutti
 * @see AbstractDomainObject
 *
 */
public class ActivitySchedule extends AbstractDomainObject {

	private static final long serialVersionUID = 3928110535193750469L;

	private static Logger logger = LoggerFactory.getLogger(ActivitySchedule.class);

	List<Team> teams;
	private LocalTime staffMotivationStartTime;

	public ActivitySchedule() {
		super();
		this.teams = new ArrayList<>();
		// start time of the staffMotivation presentation can be only after 4 PM
		staffMotivationStartTime = LocalTime.of(16, 0, 0);
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	/**
	 * This helper method adds a team to the activity schedule.In addition it also
	 * adjusts the start time of the Mandatory Staff Motivation Activity.
	 * 
	 * @param team - The input team with the list of activities planned
	 * @return The team newly added to the schedule
	 */
	public Team addTeam(Team team) {

		team.setName("Team " + (teams.size() + 1));
		teams.add(team);

		if (team.getNextActivityStartTime().isAfter(staffMotivationStartTime)) {

			logger.trace("Updated Staff Motivation Start Activity Start time: {}", team.getNextActivityStartTime());

			staffMotivationStartTime = team.getNextActivityStartTime();
			teams.forEach(existingTeam -> {
				existingTeam.getActivityCatalog().getActivity(0).setStartTime(staffMotivationStartTime);
			});
		}
		team.getActivityCatalog().getActivity(0).setStartTime(staffMotivationStartTime);

		logger.debug("Successfully added a team with the following information : {}", team);
		return team;
	}

	/**
	 * This helper method returns the count of the teams currently in the schedule
	 * 
	 * @return Count of teams in the schedule
	 */
	public int getTeamCount() {
		return teams.size();
	}

	/**
	 * This helper method generates the schedule in a human readable format
	 * 
	 * @return : String containing the schedule in human readable format
	 */
	public String print() {

		StringBuilder scheduleOutput = new StringBuilder();
		teams.forEach(team -> {
			scheduleOutput.append(team.getName());
			scheduleOutput.append(": \n");
			team.getActivityCatalog().getActivities().sort(Comparator.comparing(Activity::getStartTime));
			team.getActivityCatalog().getActivities().forEach(activity -> {
				scheduleOutput.append(activity.getFormattedStartedTime() + " : " + activity.getName());
				scheduleOutput.append(" \n");
			});
			scheduleOutput.append(" \n");
		});
		
		logger.trace("Schedule to be printed : {}", scheduleOutput.toString());
		return scheduleOutput.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((staffMotivationStartTime == null) ? 0 : staffMotivationStartTime.hashCode());
		result = prime * result + ((teams == null) ? 0 : teams.hashCode());
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
		ActivitySchedule other = (ActivitySchedule) obj;
		if (staffMotivationStartTime == null) {
			if (other.staffMotivationStartTime != null)
				return false;
		} else if (!staffMotivationStartTime.equals(other.staffMotivationStartTime))
			return false;
		if (teams == null) {
			if (other.teams != null)
				return false;
		} else if (!teams.equals(other.teams))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ActivitySchedule [teams=" + teams + ", staffMotivationStartTime=" + staffMotivationStartTime + "]";
	}
}
