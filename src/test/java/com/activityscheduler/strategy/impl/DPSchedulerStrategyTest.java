package com.activityscheduler.strategy.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.activityscheduler.domain.Activity;
import com.activityscheduler.exception.SchedulerStrategyException;
import com.activityscheduler.strategy.SchedulerStrategy;

public class DPSchedulerStrategyTest {

	private SchedulerStrategy scheduler;
	private Activity[] inputActivities;

	@Before
	public void before() {
		scheduler = new DPSchedulerStrategy();
		
		inputActivities = new Activity[4];
		inputActivities[1] = new Activity(30);
		inputActivities[2] = new Activity(10);
		inputActivities[3] = new Activity(20);
	}

	@Test
	public void testScheduler_ScheduleFor30Mins() throws SchedulerStrategyException {
		
		Activity[] processedActivities = scheduler.schedule(inputActivities, 30);

		Assert.assertNotNull(processedActivities);
		Assert.assertEquals(inputActivities.length, processedActivities.length);
		Assert.assertFalse(processedActivities[1].isScheduled());
		Assert.assertTrue(processedActivities[2].isScheduled());
		Assert.assertTrue(processedActivities[3].isScheduled());
	}
	
	@Test
	public void testScheduler_ScheduleFor70Mins() throws SchedulerStrategyException {
		
		Activity[] processedActivities = scheduler.schedule(inputActivities, 70);

		Assert.assertNotNull(processedActivities);
		Assert.assertEquals(inputActivities.length, processedActivities.length);
		Assert.assertTrue(processedActivities[1].isScheduled());
		Assert.assertTrue(processedActivities[2].isScheduled());
		Assert.assertTrue(processedActivities[3].isScheduled());
	}
	
	@Test
	public void testScheduler_ScheduleFor5Mins() throws SchedulerStrategyException {
		
		Activity[] processedActivities = scheduler.schedule(inputActivities, 5);

		Assert.assertNotNull(processedActivities);
		Assert.assertEquals(inputActivities.length, processedActivities.length);
		Assert.assertFalse(processedActivities[1].isScheduled());
		Assert.assertFalse(processedActivities[2].isScheduled());
		Assert.assertFalse(processedActivities[3].isScheduled());
	}
	
	@Test
	public void testScheduler_ScheduleFor40Mins() throws SchedulerStrategyException {
		
		Activity[] processedActivities = scheduler.schedule(inputActivities, 40);

		Assert.assertNotNull(processedActivities);
		Assert.assertEquals(inputActivities.length, processedActivities.length);
		Assert.assertTrue(processedActivities[1].isScheduled());
		Assert.assertTrue(processedActivities[2].isScheduled());
		Assert.assertFalse(processedActivities[3].isScheduled());
	}
	
	@Test(expected = SchedulerStrategyException.class)
	public void testScheduler_ScheduleForException() throws SchedulerStrategyException {
		
		inputActivities[2] = null;
		Activity[] processedActivities = scheduler.schedule(inputActivities, 40);

		Assert.fail("This method should throw exception and following line should not get executed");
	}
}
