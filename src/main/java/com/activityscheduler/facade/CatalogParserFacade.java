package com.activityscheduler.facade;

import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.exception.SchedulerFacadeException;

/**
 * Facade interface for reading activities from the source and parsing them to a
 * Activity Catalog.<br/>
 * Concrete implementation of this interface should implement logic to read
 * activities from a source and construct a Catalog of all the activities
 * 
 * @author rbutti
 *
 */
public interface CatalogParserFacade {

	/**
	 * Concrete implementation of this method should implement logic to read
	 * activities from a file and construct a Catalog of all the activities
	 * 
	 * @param fileName - File containing a list of activities to be read
	 * @return - An Activity Catalog containing list of activities
	 * @throws SchedulerFacadeException
	 */
	public ActivityCatalog parseActivityCatalog(String fileName) throws SchedulerFacadeException;

}
