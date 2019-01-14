package com.activityscheduler.strategy.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.activityscheduler.constant.ErrorMessages;
import com.activityscheduler.domain.Activity;
import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.exception.ErrorCode;
import com.activityscheduler.exception.SchedulerStrategyException;
import com.activityscheduler.service.impl.ActivitySchedulerServiceImpl;
import com.activityscheduler.strategy.SchedulerStrategy;

/**
 * A strategy implementation of the {@linkplain SchedulerStrategy} interface.
 * This implementation uses Dynamic
 * Programming https://en.wikipedia.org/wiki/Dynamic_programming to
 * determine the schedule and is variation of the famous knapsack
 * programme.<p>
 * This Strategy is designed to able to fit maximum activities to a given time
 * period/duration
 * 
 * @author rbutti
 */
public class DPSchedulerStrategy implements SchedulerStrategy {

	private static Logger logger = LoggerFactory.getLogger(ActivitySchedulerServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.activityscheduler.strategy.SchedulerStrategy#schedule(com.
	 * activityscheduler.domain.ActivityCatalog, int)
	 */
	public ActivityCatalog schedule(ActivityCatalog activities, int duration) throws SchedulerStrategyException {

		try {
			logger.trace("Scheduling activities for duration {} : {}", duration, activities);

			// if there are no activities to schedule return empty catalog
			if (!activities.hasActivities()) {
				logger.info("No activites found to schedule");
				return activities;
			}
			int numOfActivities = activities.getActivityCount() - 1;

			// store all the options
			int[][] options = new int[numOfActivities + 1][duration + 1];

			// store all the selected options
			boolean[][] selections = new boolean[numOfActivities + 1][duration + 1];

			// iterate over the list of activities to be schedule
			for (int numOfActivity = 1; numOfActivity <= numOfActivities; numOfActivity++) {

				Activity activity = activities.getActivity(numOfActivity);
				logger.trace("Processing activity : {}", activity);

				// For a given time , check if the above activity is better option compared to
				// other activities
				for (int time = 1; time <= duration; time++) {

					// previous options
					int prevOption = options[numOfActivity - 1][time];

					// current option
					int currentOption = Integer.MIN_VALUE;
					if (activity.getDuration() <= time) {
						currentOption = 1 + options[numOfActivity - 1][time - activity.getDuration()];
					}

					// select better of two options
					options[numOfActivity][time] = Math.max(prevOption, currentOption);
					selections[numOfActivity][time] = (currentOption > prevOption);
				}
			}

			// determine which items to schedule for this duration
			for (int numOfActivity = numOfActivities, time = duration; numOfActivity > 0; numOfActivity--) {
				
				if (selections[numOfActivity][time]) {
					activities.getActivity(numOfActivity).setScheduled(true);
					time = time - activities.getActivity(numOfActivity).getDuration();
				}
			}
			logger.trace("Completed scheduling activities for duration {} : {}", duration, activities);
			return activities;
		} catch (Exception exception) {
			throw new SchedulerStrategyException(ErrorMessages.SCHEDULING_STRATEGY_ERROR, exception,
					ErrorCode.UNEXPECTED_ERROR);
		}
	}
}
