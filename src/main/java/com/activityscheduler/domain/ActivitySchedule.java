package com.activityscheduler.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ActivitySchedule extends AbstractDomainObject {

	private static final long serialVersionUID = 3928110535193750469L;

	List<Team> teams;
	private LocalTime staffMotivationStartTime;

	public ActivitySchedule() {
		super();
		this.teams = new ArrayList<>();
		staffMotivationStartTime = LocalTime.of(16, 0,0);
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Team addTeam(Team team) {
		team.setName("Team " + (teams.size() + 1));
		teams.add(team);
		
		if(team.getNextActivityStartTime().isAfter(staffMotivationStartTime)) {
			staffMotivationStartTime = team.getNextActivityStartTime();
			teams.forEach(existingTeam -> {
				existingTeam.getActivityCatalog().getActivity(0).setStartTime(staffMotivationStartTime);
			});
		}
		team.getActivityCatalog().getActivity(0).setStartTime(staffMotivationStartTime);
		
		return team;
	}

	public int getTeamCount() {
		return teams.size();
	}

	public void print() {
		

		teams.forEach(team -> {
			System.out.print(team.getName());
			System.out.println(":");
		 team.getActivityCatalog().getActivities().sort(Comparator.comparing(Activity::getStartTime));
		 team.getActivityCatalog().getActivities().forEach(activity -> {
				System.out.println(activity.getStartTimeString() + " : " + activity.getName());
			});
			System.out.println();
		});
	}
}
