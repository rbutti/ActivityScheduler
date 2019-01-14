package com.activityscheduler.application;

import org.junit.Test;

import com.activityscheduler.exception.SchedulerServiceException;

/**
 * Unit test for {@link ActivitySchedulerApplication}
 * @author rbutti
 *
 */
public class ActivitySchedulerApplicationTest {

	/**
	 * Test the method by passing valid input command line arguemet
	 * @throws SchedulerServiceException
	 */
	@Test
	public void testMain_WithCommandLineArgs() throws SchedulerServiceException {

		String[] args = { "src/test/resources/activities.txt" };
		ActivitySchedulerApplication.main(args);
	}

	/**
	 * Test the method by not passing valid input command line argument
	 * @throws SchedulerServiceException
	 */
	@Test
	public void testMain_NoCommandLineArgs() throws SchedulerServiceException {

		String[] args = {};
		ActivitySchedulerApplication.main(args);
	}
}
