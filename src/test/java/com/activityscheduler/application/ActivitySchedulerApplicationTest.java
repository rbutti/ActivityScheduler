package com.activityscheduler.application;

import org.junit.Assert;
import org.junit.Test;

import com.activityscheduler.exception.SchedulerApplicationException;
import com.activityscheduler.exception.SchedulerServiceException;

public class ActivitySchedulerApplicationTest {

	@Test
	public void testMain_WithCommandLineArgs() throws SchedulerServiceException {

		String[] args = { "src/test/resources/activities.txt" };
		try {
			ActivitySchedulerApplication.main(args);
		} catch (SchedulerApplicationException e) {
			Assert.fail();
		}
	}

	@Test
	public void testMain_WithoutCommandLineArgs() throws SchedulerServiceException {

		String[] args = {};
		try {
			ActivitySchedulerApplication.main(args);
		} catch (SchedulerApplicationException e) {
			Assert.fail();
		}
	}

	@Test(expected = SchedulerApplicationException.class)
	public void testMain_Exception() throws SchedulerApplicationException {

		ActivitySchedulerApplication.main(null);

	}
}
