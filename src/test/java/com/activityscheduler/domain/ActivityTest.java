package com.activityscheduler.domain;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

public class ActivityTest {

	private Activity activity;

	@Test
	public void testActivity_TwoArgConstructor() {

		String testName = "Test Acivity";
		int testDuration = 10;

		activity = new Activity(testDuration, testName);
		Assert.assertNotNull(activity);
		Assert.assertNotNull(activity.getId());
		Assert.assertEquals(testDuration, activity.getDuration());
		Assert.assertEquals(testName, activity.getName());
		Assert.assertFalse(activity.isScheduled());
	}
	
	@Test
	public void testActivity_ThreeArgConstructor() {

		String testName = "Test Acivity";
		int testDuration = 10;

		activity = new Activity(testDuration, testName, true);
		Assert.assertNotNull(activity);
		Assert.assertNotNull(activity.getId());
		Assert.assertEquals(testDuration, activity.getDuration());
		Assert.assertEquals(testName, activity.getName());
		Assert.assertTrue(activity.isScheduled());
	}

	@Test
	public void testSetSchedule_DefaultValue() {

		String testName = "Test Acivity";
		int testDuration = 10;

		Activity activity = new Activity(testDuration, testName);
		activity.setScheduled(true);
		Assert.assertTrue(activity.isScheduled());
	}
	
	@Test
	public void testGetStartTimeString_DefaultValue() {

		String testName = "Test Acivity";
		int testDuration = 10;

		Activity activity = new Activity(testDuration, testName);
		activity.setStartTime(LocalTime.of(9,0,0));
		Assert.assertEquals("09:00 AM", activity.getStartTimeString());
	}
}
