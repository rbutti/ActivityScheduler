package com.activityscheduler.service.impl;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.activityscheduler.domain.Activity;
import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.domain.ActivitySchedule;
import com.activityscheduler.domain.DeloitteAwayDayEventInfo;
import com.activityscheduler.domain.EventInfo;
import com.activityscheduler.domain.Team;
import com.activityscheduler.exception.ErrorCode;
import com.activityscheduler.exception.SchedulerFacadeException;
import com.activityscheduler.exception.SchedulerServiceException;
import com.activityscheduler.exception.SchedulerStrategyException;
import com.activityscheduler.facade.CatalogParserFacade;
import com.activityscheduler.facade.ScheduleWriterFacade;
import com.activityscheduler.facade.impl.BeanIOCatalogParserFacadeImpl;
import com.activityscheduler.facade.impl.ConsoleScheduleWriterFacadeImpl;
import com.activityscheduler.facade.impl.FileScheduleWriterFacadeImpl;
import com.activityscheduler.service.ActivitySchedulerService;
import com.activityscheduler.strategy.SchedulerStrategy;
import com.activityscheduler.strategy.impl.DPSchedulerStrategy;

/**
 * An implementation of {@linkplain ActivitySchedulerService} interface. This
 * class implements logic to read activities from a file, generate a schedule
 * and print the schedule on an console and to a file
 * 
 * @author rbutti
 *
 */
public class ActivitySchedulerServiceImpl implements ActivitySchedulerService {

	private static Logger logger = LoggerFactory.getLogger(ActivitySchedulerServiceImpl.class);

	private SchedulerStrategy scheduler;
	private CatalogParserFacade parserFacade;
	private ScheduleWriterFacade consoleWriterFacade;
	private ScheduleWriterFacade fileWriterFacade;

	public ActivitySchedulerServiceImpl() {

		super();
		this.scheduler = new DPSchedulerStrategy();
		this.parserFacade = new BeanIOCatalogParserFacadeImpl();
		this.consoleWriterFacade = new ConsoleScheduleWriterFacadeImpl();
		this.fileWriterFacade = new FileScheduleWriterFacadeImpl();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.activityscheduler.service.ActivitySchedulerService#parseActivityCatalog(
	 * java.lang.String)
	 */
	@Override
	public ActivityCatalog parseActivityCatalog(String filePath) throws SchedulerServiceException {

		try {
			
			logger.trace("Reading activities from the file : {}", filePath);
			return parserFacade.parseActivityCatalog(filePath);
		} catch (SchedulerFacadeException facadeException) {
			throw new SchedulerServiceException("Failed to read activities from input file", facadeException,
					ErrorCode.FACADE_ERROR);
		} catch (Exception exception) {
			throw new SchedulerServiceException("Unexpected Error occured while reading form the input file", exception,
					ErrorCode.UNEXPECTED_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.activityscheduler.service.ActivitySchedulerService#generateSchedule(com.
	 * activityscheduler.domain.ActivityCatalog,
	 * com.activityscheduler.domain.EventInfo)
	 */
	@Override
	public ActivitySchedule generateSchedule(ActivityCatalog activityCatalog, EventInfo eventInfo)
			throws SchedulerServiceException {

		logger.debug("Started Generating the schedule for Catalog : {} and eventInfo : {}", activityCatalog, eventInfo);
		try {

			ActivitySchedule schedule = new ActivitySchedule();
			Activity lunchActivity = getLunchActivity(eventInfo);

			// Iterate over the list of activities that needs to be scheduled
			while (activityCatalog.hasActivities()) {

				// create a team
				Team team = new Team(eventInfo);
				logger.trace("Created the team : {}", team);

				// assign morning activities to the team
				scheduler.schedule(activityCatalog, Math.toIntExact(
						Duration.between(eventInfo.getEventStartTime(), lunchActivity.getStartTime()).toMinutes()));
				List<Activity> scheduledActivities = activityCatalog.extractScheduledActivities();
				team.addScheduledActivities(scheduledActivities);
				logger.trace("Morning activities scheduled for the team : {}", scheduledActivities);

				// add lunch break for the team
				team.addActivity(lunchActivity);

				// assign evening activities for the team
				scheduler.schedule(activityCatalog, Math.toIntExact(
						Duration.between(team.getNextActivityStartTime(), eventInfo.getEventEndTime()).toMinutes()));

				scheduledActivities = activityCatalog.extractScheduledActivities();
				team.addScheduledActivities(scheduledActivities);
				logger.trace("Evening activities scheduled for the team : {}", scheduledActivities);

				//add team to the schedule
				schedule.addTeam(team);
				logger.debug("Added the team to schedule : {} ", team);
			}

			logger.debug("Completed Generating the schedule  : {} ", schedule);
			return schedule;
		} catch (SchedulerStrategyException strategyException) {
			throw new SchedulerServiceException("Failed to create a schedule with given input", strategyException,
					ErrorCode.STRATEGY_ENGINE_ERROR);
		} catch (Exception exception) {
			throw new SchedulerServiceException("Unexpected error occured while generating schedule with given input",
					exception, ErrorCode.UNEXPECTED_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.activityscheduler.service.ActivitySchedulerService#printSchedule(com.
	 * activityscheduler.domain.ActivitySchedule)
	 */
	@Override
	public void printSchedule(ActivitySchedule activitySchedule) throws SchedulerServiceException {

		try {

			consoleWriterFacade.printSchedule(activitySchedule);
			logger.trace("Writing  activities to console completed");
			
			fileWriterFacade.printSchedule(activitySchedule);
			logger.trace("Reading activities to file completed");
			
		} catch (SchedulerFacadeException facadeException) {
			throw new SchedulerServiceException("Failed to read activities from input file", facadeException,
					ErrorCode.FACADE_ERROR);
		} catch (Exception exception) {
			throw new SchedulerServiceException("Unexpected Error occured while reading form the input file", exception,
					ErrorCode.UNEXPECTED_ERROR);
		}

	}

	/**
	 * This method adds a lunch brean to the event.
	 * 
	 * @param eventInfo - Event Information
	 * @return - An activity for lunch break
	 */
	private Activity getLunchActivity(EventInfo eventInfo) {

		Activity lunchActivity = new Activity();

		if (eventInfo instanceof DeloitteAwayDayEventInfo) {
			lunchActivity = ((DeloitteAwayDayEventInfo) eventInfo).getLunchActivity();
		}
		return lunchActivity;
	}

}
