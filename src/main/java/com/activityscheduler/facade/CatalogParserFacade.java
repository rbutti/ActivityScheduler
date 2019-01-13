package com.activityscheduler.facade;

import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.exception.SchedulerFacadeException;

public interface CatalogParserFacade {
	
	public ActivityCatalog parseActivityCatalog(String fileName) throws SchedulerFacadeException;

}
