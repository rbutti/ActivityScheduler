package com.activityscheduler.strategy.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.exception.SchedulerStrategyException;
import com.activityscheduler.strategy.SchedulerStrategy;

public class DPSchedulerStrategyTest {

	private SchedulerStrategy scheduler;
	private ActivityCatalog inputActivities;

	@Before
	public void before() {
		scheduler = new DPSchedulerStrategy();
		
		inputActivities = new ActivityCatalog();
		inputActivities.addActivtiy("Test Activity 1", 30);
		inputActivities.addActivtiy("Test Activity 2", 10);
		inputActivities.addActivtiy("Test Activity 3", 20);
	}

	@Test
	public void testScheduler_ScheduleFor30Mins() throws SchedulerStrategyException {
		
		ActivityCatalog processedActivities = scheduler.schedule(inputActivities, 30);

		Assert.assertNotNull(processedActivities);
		Assert.assertEquals(inputActivities.getActivityCount(), processedActivities.getActivityCount());
		Assert.assertFalse(inputActivities.getActivity(1).isScheduled());
		Assert.assertTrue(inputActivities.getActivity(2).isScheduled());
		Assert.assertTrue(inputActivities.getActivity(3).isScheduled());
	}
	
	@Test
	public void testScheduler_ScheduleFor70Mins() throws SchedulerStrategyException {
		
		ActivityCatalog processedActivities = scheduler.schedule(inputActivities, 70);

		Assert.assertNotNull(processedActivities);
		Assert.assertEquals(inputActivities.getActivityCount(), processedActivities.getActivityCount());
		Assert.assertTrue(inputActivities.getActivity(1).isScheduled());
		Assert.assertTrue(inputActivities.getActivity(2).isScheduled());
		Assert.assertTrue(inputActivities.getActivity(3).isScheduled());
	}
	
	@Test
	public void testScheduler_ScheduleFor5Mins() throws SchedulerStrategyException {
		
		ActivityCatalog processedActivities = scheduler.schedule(inputActivities, 5);

		Assert.assertNotNull(processedActivities);
		Assert.assertEquals(inputActivities.getActivityCount(), processedActivities.getActivityCount());
		Assert.assertFalse(inputActivities.getActivity(1).isScheduled());
		Assert.assertFalse(inputActivities.getActivity(2).isScheduled());
		Assert.assertFalse(inputActivities.getActivity(3).isScheduled());
	}
	
	@Test
	public void testScheduler_ScheduleFor40Mins() throws SchedulerStrategyException {
		
		ActivityCatalog processedActivities = scheduler.schedule(inputActivities, 40);

		Assert.assertNotNull(processedActivities);
		Assert.assertEquals(inputActivities.getActivityCount(), processedActivities.getActivityCount());
		Assert.assertTrue(inputActivities.getActivity(1).isScheduled());
		Assert.assertTrue(inputActivities.getActivity(2).isScheduled());
		Assert.assertFalse(inputActivities.getActivity(3).isScheduled());
	}
	
	@Test(expected = SchedulerStrategyException.class)
	public void testScheduler_ScheduleForException() throws SchedulerStrategyException {
		
		inputActivities.setActivities(null);
		scheduler.schedule(inputActivities, 40);

		Assert.fail("This method should throw exception and following line should not get executed");
	}
}
