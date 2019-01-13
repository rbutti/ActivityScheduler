package com.activityscheduler.exception;

import org.junit.Assert;
import org.junit.Test;

public class SchedulerServiceExceptionTest {

	@Test(expected = SchedulerServiceException.class)
	public void testSchedulerServiceException_ThreeArgErroCodeConstructor() throws SchedulerServiceException {
		throw new SchedulerServiceException("Test Error", new RuntimeException(), ErrorCode.UNEXPECTED_ERROR);
	}

	@Test(expected = SchedulerServiceException.class)
	public void testSchedulerServiceException_OneArgConstructor() throws SchedulerServiceException {
		throw new SchedulerServiceException(ErrorCode.UNEXPECTED_ERROR);
	}

	@Test(expected = SchedulerServiceException.class)
	public void testSchedulerServiceException_TwoArgErroCodeConstructor() throws SchedulerServiceException {
		throw new SchedulerServiceException("Test Error", ErrorCode.UNEXPECTED_ERROR);
	}

	@Test(expected = SchedulerServiceException.class)
	public void testSchedulerServiceException_TwoArgErroCodeConstructor2() throws SchedulerServiceException {
		throw new SchedulerServiceException(new RuntimeException(), ErrorCode.UNEXPECTED_ERROR);
	}

	@Test
	public void testGetErrorCode_UnexpectedError() throws SchedulerServiceException {
		SchedulerServiceException serviceExcpetion = new SchedulerServiceException(new RuntimeException(),
				ErrorCode.UNEXPECTED_ERROR);
		Assert.assertEquals(ErrorCode.UNEXPECTED_ERROR, serviceExcpetion.getErrorCode());
	}
}
