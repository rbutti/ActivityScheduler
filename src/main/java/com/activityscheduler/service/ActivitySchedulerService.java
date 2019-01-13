package com.activityscheduler.service;

import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.domain.ActivitySchedule;
import com.activityscheduler.domain.EventInfo;
import com.activityscheduler.exception.SchedulerServiceException;

public interface ActivitySchedulerService {
	
	public ActivitySchedule generateSchedule(ActivityCatalog activityCatalog, EventInfo eventInfo) throws SchedulerServiceException;

}
