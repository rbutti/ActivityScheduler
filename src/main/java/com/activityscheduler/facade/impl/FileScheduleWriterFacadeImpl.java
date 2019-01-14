package com.activityscheduler.facade.impl;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.activityscheduler.constant.AppConfiguration;
import com.activityscheduler.constant.ErrorMessages;
import com.activityscheduler.domain.ActivitySchedule;
import com.activityscheduler.exception.ErrorCode;
import com.activityscheduler.exception.SchedulerFacadeException;
import com.activityscheduler.facade.ScheduleWriterFacade;

/**
 * An implementation of the interface {@link ScheduleWriterFacade}. This
 * implementation writes the Activity Schedule to an file. The file will be
 * generated in the same location as the application jar
 * 
 * @author rbutti
 *
 */
public class FileScheduleWriterFacadeImpl implements ScheduleWriterFacade {

	private static Logger logger = LoggerFactory.getLogger(BeanIOCatalogParserFacadeImpl.class);

	public FileScheduleWriterFacadeImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.activityscheduler.facade.ScheduleWriterFacade#printSchedule(com.
	 * activityscheduler.domain.ActivitySchedule)
	 */
	@Override
	public void printSchedule(ActivitySchedule activitySchedule) throws SchedulerFacadeException {

		try {

			BufferedReader reader = new BufferedReader(new StringReader(activitySchedule.print()));
			FileWriter fileWriter = new FileWriter(AppConfiguration.SCHEDULE_OUTPUT_FILE);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			reader.lines().forEach(line -> printWriter.println(line));
			printWriter.close();

			logger.debug("Printing Schedule to File complete. fileName : {}", AppConfiguration.SCHEDULE_OUTPUT_FILE);
		} catch (Exception exception) {
			throw new SchedulerFacadeException(ErrorMessages.FAILED_TO_WRITE_TO_FILE, exception,
					ErrorCode.FACADE_ERROR);
		}

	}

}
