package com.activityscheduler.domain;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TeamTest {

	private Team team;
	
private EventInfo eventInfo;
	
	@Before
	public void before() {
		eventInfo = new EventInfo(LocalTime.of(8, 0, 0), LocalTime.of(16, 0, 0), LocalTime.of(11, 0, 0));
	}

	@Test
	public void testTeam_Constructor() {

		team = new Team(eventInfo);
		Assert.assertNotNull(team);
		Assert.assertNotNull(team.getId());
		Assert.assertNotNull(team.getActivityCatalog());
		Assert.assertEquals(1, team.getActivityCatalog().getActivityCount());
	}

	@Test
	public void testAddActivity_DefaultValue() {

		String testName = "Test Acivity";
		int testDuration = 10;

		Activity activity = new Activity(testDuration, testName);
		team = new Team(eventInfo);
		team.addActivity(activity);
		
		Assert.assertNotNull(team);
		Assert.assertNotNull(team.getActivityCatalog());
		Assert.assertEquals(2, team.getActivityCatalog().getActivityCount());
		Assert.assertEquals(testName, team.getActivityCatalog().getActivity(1).getName());
		Assert.assertEquals(testDuration, team.getActivityCatalog().getActivity(1).getDuration());
	}
}
