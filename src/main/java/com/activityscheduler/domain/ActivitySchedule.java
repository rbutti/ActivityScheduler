package com.activityscheduler.domain;

import java.util.ArrayList;
import java.util.List;

public class ActivitySchedule extends AbstractDomainObject {

	private static final long serialVersionUID = 3928110535193750469L;

	List<Team> teams;

	public ActivitySchedule() {
		super();
		this.teams = new ArrayList<>();
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Team addTeam(Team team) {

		teams.add(team);
		return team;
	}

	public int getTeamCount() {
		return teams.size();
	}
}
