package com.activityscheduler.domain;

import org.junit.Assert;
import org.junit.Test;

public class ActivityCatalogTest {

	private ActivityCatalog activityCatalog;

	@Test
	public void testActivity_Constructor() {

		activityCatalog = new ActivityCatalog();
		Assert.assertNotNull(activityCatalog);
		Assert.assertNotNull(activityCatalog.getActivitiesList());
		Assert.assertEquals(1, activityCatalog.getActivityCount());
	}

	@Test
	public void testAddActivity_PassActivityValues() {

		String testName = "Test Acivity";
		int testDuration = 10;

		activityCatalog = new ActivityCatalog();
		activityCatalog.addActivtiy(testName, testDuration);
		Assert.assertNotNull(activityCatalog);
		Assert.assertNotNull(activityCatalog.getActivitiesList());
		Assert.assertEquals(2, activityCatalog.getActivityCount());
		
		Assert.assertEquals(testName, activityCatalog.getActivity(1).getName());
		Assert.assertEquals(testDuration, activityCatalog.getActivity(1).getDuration());
	}
	
	@Test
	public void testAddActivity_PassActivityObj() {

		String testName = "Test Acivity";
		int testDuration = 10;

		Activity activity = new Activity(testDuration, testName);
		activityCatalog = new ActivityCatalog();
		activityCatalog.addActivtiy(activity);
		Assert.assertNotNull(activityCatalog);
		Assert.assertNotNull(activityCatalog.getActivitiesList());
		Assert.assertEquals(2, activityCatalog.getActivityCount());
		
		Assert.assertEquals(testName, activityCatalog.getActivity(1).getName());
		Assert.assertEquals(testDuration, activityCatalog.getActivity(1).getDuration());
	}
}
