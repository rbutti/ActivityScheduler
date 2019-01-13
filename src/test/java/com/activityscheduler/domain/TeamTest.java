package com.activityscheduler.domain;

import org.junit.Assert;
import org.junit.Test;

public class TeamTest {

	private Team team;

	@Test
	public void testTeam_Constructor() {
		
		String testTeamName = "Team 1";
		
		team = new Team(testTeamName);
		Assert.assertNotNull(team);
		Assert.assertNotNull(team.getId());
		Assert.assertEquals(testTeamName, team.getName());
		Assert.assertNotNull(team.getActivityCatalog());
		Assert.assertEquals(1, team.getActivityCatalog().getActivityCount());
	}

	@Test
	public void testAddActivity_DefaultValue() {

		String testName = "Test Acivity";
		int testDuration = 10;

		Activity activity = new Activity(testDuration, testName);
		team = new Team("Team 1");
		team.addActivity(activity);
		
		Assert.assertNotNull(team);
		Assert.assertNotNull(team.getActivityCatalog());
		Assert.assertEquals(2, team.getActivityCatalog().getActivityCount());
		Assert.assertEquals(testName, team.getActivityCatalog().getActivity(1).getName());
		Assert.assertEquals(testDuration, team.getActivityCatalog().getActivity(1).getDuration());
	}
}
