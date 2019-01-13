package com.activityscheduler.strategy.impl;

import com.activityscheduler.domain.Activity;
import com.activityscheduler.exception.ErrorCode;
import com.activityscheduler.exception.SchedulerStrategyException;
import com.activityscheduler.strategy.SchedulerStrategy;

public class DPSchedulerStrategy implements SchedulerStrategy {

	// A utility function that returns maximum of two integers
	int max(int a, int b) {
		return (a > b) ? a : b;
	}

// Returns the maximum value that can be put in a knapsack of capacity W 
	public Activity[] schedule(Activity[] activities, int duration) throws SchedulerStrategyException {

		try {
			int numOfActivities = activities.length - 1;

			// opt[n][w] = max profit of packing items 1..n with weight limit w
			// sol[n][w] = does opt solution to pack items 1..n with weight limit w include
			// item n?
			int[][] options = new int[numOfActivities + 1][duration + 1];
			boolean[][] selections = new boolean[numOfActivities + 1][duration + 1];

			for (int n = 1; n <= numOfActivities; n++) {
				for (int w = 1; w <= duration; w++) {

					// don't take item n
					int prevOption = options[n - 1][w];

					// take item n
					int currentOption = Integer.MIN_VALUE;
					if (activities[n].getDuration() <= w) {
						currentOption = 1 + options[n - 1][w - activities[n].getDuration()];
					}

					// select better of two options
					options[n][w] = Math.max(prevOption, currentOption);
					selections[n][w] = (currentOption > prevOption);
				}
			}

			// determine which items to take
			for (int n = numOfActivities, w = duration; n > 0; n--) {
				if (selections[n][w]) {
					activities[n].setScheduled(true);
					w = w - activities[n].getDuration();
				}
			}

			return activities;
		} catch (Exception exception) {
			throw new SchedulerStrategyException("Error occurred while schedulign the activities", exception,
					ErrorCode.UNEXPECTED_ERROR);
		}
	}

}
