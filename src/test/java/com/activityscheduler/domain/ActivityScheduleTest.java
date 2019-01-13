package com.activityscheduler.domain;

import org.junit.Assert;
import org.junit.Test;

public class ActivityScheduleTest {

	private ActivitySchedule activitySchedule;

	@Test
	public void testActivitySchedule_Constructor() {

		activitySchedule = new ActivitySchedule();
		Assert.assertNotNull(activitySchedule);
		Assert.assertNotNull(activitySchedule.getId());
		Assert.assertEquals(0, activitySchedule.getTeamCount());
	}

	@Test
	public void testAddTeam_DefaultValue() {

		String testTeamName = "Team 1";

		Team team = new Team(testTeamName);
		activitySchedule = new ActivitySchedule();
		activitySchedule.addTeam(team);

		Assert.assertNotNull(activitySchedule);
		Assert.assertNotNull(activitySchedule.getTeams());
		Assert.assertEquals(1, activitySchedule.getTeamCount());
		Assert.assertEquals(testTeamName, activitySchedule.getTeams().get(0).getName());
	}
}
