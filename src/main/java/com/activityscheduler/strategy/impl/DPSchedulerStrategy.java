package com.activityscheduler.strategy.impl;

import com.activityscheduler.domain.Activity;
import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.exception.ErrorCode;
import com.activityscheduler.exception.SchedulerStrategyException;
import com.activityscheduler.strategy.SchedulerStrategy;

public class DPSchedulerStrategy implements SchedulerStrategy {

// Returns the maximum value that can be put in a knapsack of capacity W 
	public ActivityCatalog schedule(ActivityCatalog activities, int duration) throws SchedulerStrategyException {

		try {
			 
			if(!activities.hasActivities()) {
				return activities;
			}
			int numOfActivities = activities.getActivityCount() - 1;

			// opt[n][w] = max profit of packing items 1..n with weight limit w
			// sol[n][w] = does opt solution to pack items 1..n with weight limit w include
			// item n?
			int[][] options = new int[numOfActivities + 1][duration + 1];
			boolean[][] selections = new boolean[numOfActivities + 1][duration + 1];

			for (int activityCursor = 1; activityCursor <= numOfActivities; activityCursor++) {

				Activity activity = activities.getActivity(activityCursor);
				for (int minutes = 1; minutes <= duration; minutes++) {

					// don't take item n
					int prevOption = options[activityCursor - 1][minutes];

					// take item n
					int currentOption = Integer.MIN_VALUE;

					if (activity.getDuration() <= minutes) {
						currentOption = 1 + options[activityCursor - 1][minutes - activity.getDuration()];
					}

					// select better of two options
					options[activityCursor][minutes] = Math.max(prevOption, currentOption);
					selections[activityCursor][minutes] = (currentOption > prevOption);
				}
			}

			// determine which items to take
			for (int n = numOfActivities, w = duration; n > 0; n--) {
				if (selections[n][w]) {
					activities.getActivity(n).setScheduled(true);
					w = w - activities.getActivity(n).getDuration();
				}
			}

			return activities;
		} catch (Exception exception) {
			throw new SchedulerStrategyException("Error occurred while schedulign the activities", exception,
					ErrorCode.UNEXPECTED_ERROR);
		}
	}

}
