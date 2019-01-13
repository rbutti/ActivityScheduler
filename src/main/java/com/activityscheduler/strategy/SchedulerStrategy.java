package com.activityscheduler.strategy;

import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.exception.SchedulerStrategyException;

public interface SchedulerStrategy {

	public ActivityCatalog schedule(ActivityCatalog activities, int W) throws SchedulerStrategyException;
}
