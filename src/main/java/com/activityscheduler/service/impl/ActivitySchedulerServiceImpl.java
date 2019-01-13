package com.activityscheduler.service.impl;

import java.time.Duration;
import java.util.List;

import com.activityscheduler.domain.Activity;
import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.domain.ActivitySchedule;
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
import com.activityscheduler.service.ActivitySchedulerService;
import com.activityscheduler.strategy.SchedulerStrategy;
import com.activityscheduler.strategy.impl.DPSchedulerStrategy;

public class ActivitySchedulerServiceImpl implements ActivitySchedulerService {

	private SchedulerStrategy scheduler;
	private CatalogParserFacade parserFacade;
	private ScheduleWriterFacade writerFacade;

	public ActivitySchedulerServiceImpl() {
		super();
		this.scheduler = new DPSchedulerStrategy();
		this.parserFacade = new BeanIOCatalogParserFacadeImpl();
		this.writerFacade = new ConsoleScheduleWriterFacadeImpl();
	}

	@Override
	public ActivitySchedule generateSchedule(ActivityCatalog activityCatalog, EventInfo eventInfo)
			throws SchedulerServiceException {

		try {
			ActivitySchedule schedule = new ActivitySchedule();

			while (activityCatalog.hasActivities()) {

				Team team = new Team(eventInfo);
				scheduler.schedule(activityCatalog, Math.toIntExact(
						Duration.between(eventInfo.getEventStartTime(), eventInfo.getLunchStartTime()).toMinutes()));
				List<Activity> scheduledActivities = activityCatalog.extractScheduledActivities();
				team.addScheduledActivities(scheduledActivities);

				Activity lunch = new Activity(60, "Lunch Break", true);
				team.addActivity(lunch);

				scheduler.schedule(activityCatalog, Math.toIntExact(
						Duration.between(team.getNextActivityStartTime(), eventInfo.getEventEndTime()).toMinutes()));

				scheduledActivities = activityCatalog.extractScheduledActivities();
				team.addScheduledActivities(scheduledActivities);
				schedule.addTeam(team);

			}
			return schedule;
		} catch (SchedulerStrategyException strategyException) {
			throw new SchedulerServiceException("Failed to create a schedule with given input", strategyException,
					ErrorCode.STRATEGY_ENGINE_ERROR);
		} catch (Exception exception) {
			throw new SchedulerServiceException("Unexpected error occured while generating schedule with given input",
					exception, ErrorCode.UNEXPECTED_ERROR);
		}
	}

	@Override
	public ActivityCatalog parseActivityCatalog(String filePath) throws SchedulerServiceException {
		
		try {
			return parserFacade.parseActivityCatalog(filePath);
		} catch (SchedulerFacadeException facadeException) {
			throw new SchedulerServiceException("Failed to read activities from input file", facadeException,
					ErrorCode.FACADE_ERROR);
		} catch (Exception exception) {
			throw new SchedulerServiceException("Unexpected Error occured while reading form the input file", exception,
					ErrorCode.UNEXPECTED_ERROR);
		}
	}

	@Override
	public void printSchedule(ActivitySchedule activitySchedule) throws SchedulerServiceException {
		
		try {
			writerFacade.printSchedule(activitySchedule);
		} catch (SchedulerFacadeException facadeException) {
			throw new SchedulerServiceException("Failed to read activities from input file", facadeException,
					ErrorCode.FACADE_ERROR);
		} catch (Exception exception) {
			throw new SchedulerServiceException("Unexpected Error occured while reading form the input file", exception,
					ErrorCode.UNEXPECTED_ERROR);
		}
		
	}

}
