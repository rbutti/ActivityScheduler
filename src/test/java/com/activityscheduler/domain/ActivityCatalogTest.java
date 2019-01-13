package com.activityscheduler.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ActivityCatalogTest {

	private ActivityCatalog activityCatalog;

	@Test
	public void testActivity_Constructor() {

		activityCatalog = new ActivityCatalog();
		Assert.assertNotNull(activityCatalog);
		Assert.assertNotNull(activityCatalog.getActivities());
		Assert.assertEquals(1, activityCatalog.getActivityCount());
	}

	@Test
	public void testAddActivity_PassActivityValues() {

		String testName = "Test Acivity";
		int testDuration = 10;

		activityCatalog = new ActivityCatalog();

		activityCatalog.addActivity(new Activity(testDuration, testName));
		Assert.assertNotNull(activityCatalog);
		Assert.assertNotNull(activityCatalog.getActivities());
		Assert.assertEquals(2, activityCatalog.getActivityCount());
		
		Assert.assertEquals(testName, activityCatalog.getActivity(1).getName());
		Assert.assertEquals(testDuration, activityCatalog.getActivity(1).getDuration());
	}
	
	@Test
	public void testAddActivity_PassActivityValues2() {

		String testName = "Test Acivity";
		int testDuration = 10;

		activityCatalog = new ActivityCatalog();
		activityCatalog.addActivity(new Activity(testDuration, testName, true));
		Assert.assertNotNull(activityCatalog);
		Assert.assertNotNull(activityCatalog.getActivities());
		Assert.assertEquals(2, activityCatalog.getActivityCount());
		
		Assert.assertEquals(testName, activityCatalog.getActivity(1).getName());
		Assert.assertEquals(testDuration, activityCatalog.getActivity(1).getDuration());
		Assert.assertTrue(activityCatalog.getActivity(1).isScheduled());
	}
	
	@Test
	public void testAddActivity_PassActivityObj() {

		String testName = "Test Acivity";
		int testDuration = 10;

		Activity activity = new Activity(testDuration, testName);
		activityCatalog = new ActivityCatalog();
		activityCatalog.addActivity(activity);
		Assert.assertNotNull(activityCatalog);
		Assert.assertNotNull(activityCatalog.getActivities());
		Assert.assertEquals(2, activityCatalog.getActivityCount());
		
		Assert.assertEquals(testName, activityCatalog.getActivity(1).getName());
		Assert.assertEquals(testDuration, activityCatalog.getActivity(1).getDuration());
	}
	
	@Test
	public void testAddAllActivities_WithTwoActivities() {

		String testName = "Test Acivity";
		int testDuration = 10;

		Activity activity1 = new Activity(testDuration, testName);
		Activity activity2 = new Activity(testDuration, testName);
		
		List<Activity> activities = new ArrayList<>();
		activities.add(activity1);
		activities.add(activity2);
		
		activityCatalog = new ActivityCatalog();
		activityCatalog.addAllActivities(activities);
		
		Assert.assertNotNull(activityCatalog);
		Assert.assertNotNull(activityCatalog.getActivities());
		Assert.assertEquals(3, activityCatalog.getActivityCount());
		
		Assert.assertEquals(testName, activityCatalog.getActivity(1).getName());
		Assert.assertEquals(testDuration, activityCatalog.getActivity(1).getDuration());
	}
	
	
	@Test
	public void testExtractScheduledActivities_WithTwoActivities() {

		String testName = "Test Acivity";
		int testDuration = 10;

		Activity activity1 = new Activity(testDuration, testName, true);
		Activity activity2 = new Activity(20, "Sample Activity", false);
		
		List<Activity> activities = new ArrayList<>();
		activities.add(activity1);
		activities.add(activity2);
		
		activityCatalog = new ActivityCatalog();
		activityCatalog.addAllActivities(activities);
		
		
		List<Activity> scheduledActivities = activityCatalog.extractScheduledActivities();
		Assert.assertNotNull(scheduledActivities);

		Assert.assertEquals(1, scheduledActivities.size());
		Assert.assertEquals(2, activityCatalog.getActivityCount());
		
		Assert.assertEquals(testName, scheduledActivities.get(0).getName());
		Assert.assertEquals(testDuration, scheduledActivities.get(0).getDuration());
	}
}
