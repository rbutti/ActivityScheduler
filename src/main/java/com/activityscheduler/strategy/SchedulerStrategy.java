package com.activityscheduler.strategy;

import com.activityscheduler.domain.Activity;

public interface SchedulerStrategy {

	public Activity[] schedule(Activity[] activities, int W);
}
