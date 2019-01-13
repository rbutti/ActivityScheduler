package com.activityscheduler.strategy.impl;

import com.activityscheduler.domain.Activity;
import com.activityscheduler.strategy.SchedulerStrategy;

public class DPSchedulerStrategy implements SchedulerStrategy {

	// A utility function that returns maximum of two integers
	int max(int a, int b) {
		return (a > b) ? a : b;
	}

// Returns the maximum value that can be put in a knapsack of capacity W 
	public Activity[] schedule(Activity[] activities, int W) {

		int N = activities.length - 1;

		// opt[n][w] = max profit of packing items 1..n with weight limit w
		// sol[n][w] = does opt solution to pack items 1..n with weight limit w include
		// item n?
		int[][] opt = new int[N + 1][W + 1];
		boolean[][] sol = new boolean[N + 1][W + 1];

		for (int n = 1; n <= N; n++) {
			for (int w = 1; w <= W; w++) {

				// don't take item n
				int option1 = opt[n - 1][w];

				// take item n
				int option2 = Integer.MIN_VALUE;
				if (activities[n].getDuration() <= w) {
					option2 = 1 + opt[n - 1][w - activities[n].getDuration()];
				}

				// select better of two options
				opt[n][w] = Math.max(option1, option2);
				sol[n][w] = (option2 > option1);
			}
		}

		// determine which items to take
		for (int n = N, w = W; n > 0; n--) {
			if (sol[n][w]) {
				activities[n].setScheduled(true);
				w = w - activities[n].getDuration();
			} 
		}

		return activities;

	}

}
