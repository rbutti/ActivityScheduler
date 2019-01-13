package com.activityscheduler.strategy;

import com.activityscheduler.domain.Activity;
import com.activityscheduler.exception.SchedulerStrategyException;

public interface SchedulerStrategy {

	public Activity[] schedule(Activity[] activities, int W) throws SchedulerStrategyException;
}
