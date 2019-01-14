package com.activityscheduler.domain;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ActivityScheduleTest {

	private ActivitySchedule activitySchedule;
	private EventInfo eventInfo;
	
	@Before
	public void before() {
		eventInfo = new DeloitteAwayDayEventInfo(LocalTime.of(8, 0, 0), LocalTime.of(16, 0, 0), LocalTime.of(11, 0, 0));
	}

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

		Team team = new Team(eventInfo);
		activitySchedule = new ActivitySchedule();
		activitySchedule.addTeam(team);

		Assert.assertNotNull(activitySchedule);
		Assert.assertNotNull(activitySchedule.getTeams());
		Assert.assertEquals(1, activitySchedule.getTeamCount());
		Assert.assertEquals(testTeamName, activitySchedule.getTeams().get(0).getName());
	}
}
