package com.activityscheduler.exception;

import org.junit.Assert;
import org.junit.Test;

public class SchedulerStrategyExceptionTest {

	@Test(expected = SchedulerStrategyException.class)
	public void testSchedulerStrategyException_ThreeArgErroCodeConstructor() throws SchedulerStrategyException {
		throw new SchedulerStrategyException("Test Error", new RuntimeException(), ErrorCode.UNEXPECTED_ERROR);
	}

	@Test(expected = SchedulerStrategyException.class)
	public void testSchedulerStrategyException_OneArgConstructor() throws SchedulerStrategyException {
		throw new SchedulerStrategyException(ErrorCode.UNEXPECTED_ERROR);
	}

	@Test(expected = SchedulerStrategyException.class)
	public void testSchedulerStrategyException_TwoArgErroCodeConstructor() throws SchedulerStrategyException {
		throw new SchedulerStrategyException("Test Error", ErrorCode.UNEXPECTED_ERROR);
	}

	@Test(expected = SchedulerStrategyException.class)
	public void testSchedulerServiceException_TwoArgErroCodeConstructor2() throws SchedulerStrategyException {
		throw new SchedulerStrategyException(new RuntimeException(), ErrorCode.UNEXPECTED_ERROR);
	}

	@Test
	public void testGetErrorCode_UnexpectedError() throws SchedulerServiceException {
		SchedulerStrategyException serviceExcpetion = new SchedulerStrategyException(new RuntimeException(),
				ErrorCode.UNEXPECTED_ERROR);
		Assert.assertEquals(ErrorCode.UNEXPECTED_ERROR, serviceExcpetion.getErrorCode());
	}
}
