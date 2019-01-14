package com.activityscheduler.facade.impl;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;

import com.activityscheduler.constant.AppConfiguration;
import com.activityscheduler.domain.ActivitySchedule;
import com.activityscheduler.exception.ErrorCode;
import com.activityscheduler.exception.SchedulerFacadeException;
import com.activityscheduler.facade.ScheduleWriterFacade;

public class FileScheduleWriterFacadeImpl implements ScheduleWriterFacade {

	public FileScheduleWriterFacadeImpl() {
		super();
	}

	@Override
	public void printSchedule(ActivitySchedule activitySchedule) throws SchedulerFacadeException {

		try {
			BufferedReader reader = new BufferedReader(new StringReader(activitySchedule.print()));
			FileWriter fileWriter = new FileWriter(AppConfiguration.SCHEDULE_OUTPUT_FILE);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			reader.lines().forEach(line -> printWriter.println(line));
			printWriter.close();
		} catch (Exception exception) {
			throw new SchedulerFacadeException("Error occured while righting output to a file", exception,
					ErrorCode.FACADE_ERROR);
		}

	}

}
