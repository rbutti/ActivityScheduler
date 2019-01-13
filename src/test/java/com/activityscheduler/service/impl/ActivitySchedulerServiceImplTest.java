package com.activityscheduler.service.impl;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.domain.ActivitySchedule;
import com.activityscheduler.domain.EventInfo;
import com.activityscheduler.exception.SchedulerServiceException;
import com.activityscheduler.service.ActivitySchedulerService;

public class ActivitySchedulerServiceImplTest {

	private ActivitySchedulerService schedulerService;
	private ActivityCatalog inputActivities;
	private EventInfo eventInfo;

	@Before
	public void before() {

		schedulerService = new ActivitySchedulerServiceImpl();

		inputActivities = new ActivityCatalog();
		inputActivities.addActivtiy("Duck Herding", 60);
		inputActivities.addActivtiy("Archery", 45);
		inputActivities.addActivtiy("Learning Magic Tricks", 40);
		inputActivities.addActivtiy("Laser Clay Shooting", 60);
		inputActivities.addActivtiy("Human Table Football", 30);
		inputActivities.addActivtiy("Buggy Driving", 30);
		inputActivities.addActivtiy("Salsa & Pickles sprint", 15);
		inputActivities.addActivtiy("2-wheeled Segways", 45);
		inputActivities.addActivtiy("Viking Axe Throwing", 60);
		inputActivities.addActivtiy("Giant Puzzle Dinosaurs", 30);
		inputActivities.addActivtiy("Giant Digital Graffiti", 60);
		inputActivities.addActivtiy("Cricket 2020", 60);
		inputActivities.addActivtiy("Wine Tasting sprint", 15);
		inputActivities.addActivtiy("Arduino Bonanza", 30);
		inputActivities.addActivtiy("Digital Tresure Hunt", 60);
		inputActivities.addActivtiy("Enigma Challenge", 45);
		inputActivities.addActivtiy("Monti Carlo or Bust", 55);
		inputActivities.addActivtiy("New Zealand Haka", 30);
		inputActivities.addActivtiy("Time Tracker sprint", 15);
		inputActivities.addActivtiy("Indiano Drizzle", 45);

		eventInfo = new EventInfo(LocalTime.of(9, 0, 0), LocalTime.of(17, 0, 0), LocalTime.of(11, 0, 0));
	}
	
	@Test
	public void testGenerateSchedule_ScheduleForTwoTeams() throws SchedulerServiceException {
		
		ActivitySchedule activitySchedule = schedulerService.generateSchedule(inputActivities, eventInfo);

		Assert.assertNotNull(activitySchedule);
		Assert.assertEquals(2, activitySchedule.getTeamCount());
		Assert.assertEquals(15, activitySchedule.getTeams().get(0).getActivityCatalog().getActivityCount());
		Assert.assertEquals(15, activitySchedule.getTeams().get(0).getActivityCatalog().getActivityCount());
		Assert.assertEquals("Staff Motivation Presentation", activitySchedule.getTeams().get(0).getActivityCatalog().getActivity(0).getName());
		activitySchedule.print();
	}
	
	@Test(expected=SchedulerServiceException.class)
	public void testGenerateSchedule_ScheduleStrategyException() throws SchedulerServiceException {
		
		eventInfo = new EventInfo(LocalTime.of(9, 0, 0), LocalTime.of(4, 0, 0), LocalTime.of(11, 0, 0));
		schedulerService.generateSchedule(inputActivities, eventInfo);
	}
	
	@Test(expected=SchedulerServiceException.class)
	public void testGenerateSchedule_Exception() throws SchedulerServiceException {

		schedulerService.generateSchedule(inputActivities, null);
	}

}
