package com.activityscheduler.service.impl;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.activityscheduler.domain.Activity;
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
		inputActivities.addActivity(new Activity(60, "Duck Herding"));
		inputActivities.addActivity(new Activity(45, "Archery"));
		inputActivities.addActivity(new Activity(40, "Learning Magic Tricks"));
		inputActivities.addActivity(new Activity(60, "Laser Clay Shooting"));
		inputActivities.addActivity(new Activity(30, "Human Table Football"));
		inputActivities.addActivity(new Activity(30, "Buggy Driving"));
		inputActivities.addActivity(new Activity(15, "Salsa & Pickles sprint"));
		inputActivities.addActivity(new Activity(45, "2-wheeled Segways"));
		inputActivities.addActivity(new Activity(60, "Viking Axe Throwing"));
		inputActivities.addActivity(new Activity(30, "Giant Puzzle Dinosaurs"));
		inputActivities.addActivity(new Activity(60, "Giant Digital Graffiti"));
		inputActivities.addActivity(new Activity(60, "Cricket 2020"));
		inputActivities.addActivity(new Activity(15, "Wine Tasting sprint"));
		inputActivities.addActivity(new Activity(30, "Arduino Bonanza"));
		inputActivities.addActivity(new Activity(60, "Digital Tresure Hunt"));
		inputActivities.addActivity(new Activity(45, "Enigma Challenge"));
		inputActivities.addActivity(new Activity(55, "Monti Carlo or Bust"));
		inputActivities.addActivity(new Activity(30, "New Zealand Haka"));
		inputActivities.addActivity(new Activity(15, "Time Tracker sprint"));
		inputActivities.addActivity(new Activity(45, "Indiano Drizzle"));

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
		System.out.println(activitySchedule.print());
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
	

	@Test
	public void testParseActivityCatalog_ValidInput() throws SchedulerServiceException {
	
		ActivityCatalog catalog  = schedulerService.parseActivityCatalog("src/test/resources/activities.txt");
		Assert.assertNotNull(catalog);
		Assert.assertEquals(21, catalog.getActivityCount());
		Assert.assertEquals("Duck Herding", catalog.getActivity(1).getName());
		Assert.assertEquals(60, catalog.getActivity(1).getDuration());
		Assert.assertFalse(catalog.getActivity(1).isScheduled());
		
		Assert.assertEquals("Salsa & Pickles sprint", catalog.getActivity(7).getName());
		Assert.assertEquals(15, catalog.getActivity(7).getDuration());
		Assert.assertFalse(catalog.getActivity(7).isScheduled());
	}

}
