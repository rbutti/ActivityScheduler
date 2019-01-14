package com.activityscheduler.facade.impl;

import java.io.File;
import java.io.StringReader;

import org.apache.commons.io.FileUtils;
import org.beanio.BeanReader;
import org.beanio.StreamFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.activityscheduler.constant.AppConfiguration;
import com.activityscheduler.constant.ErrorMessages;
import com.activityscheduler.domain.Activity;
import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.exception.ErrorCode;
import com.activityscheduler.exception.SchedulerFacadeException;
import com.activityscheduler.facade.CatalogParserFacade;

/**
 * An implementation of {@linkplain CatalogParserFacade} interface that contains
 * necessary logic to read an input activities file, unmarshals it into an
 * Activity object and returns a catalog of all the activities found in the file.
 * 
 * @author rbutti
 *
 */
public class BeanIOCatalogParserFacadeImpl implements CatalogParserFacade {
	
	private static Logger logger = LoggerFactory.getLogger(BeanIOCatalogParserFacadeImpl.class);	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.activityscheduler.facade.CatalogParserFacade#parseActivityCatalog(java.
	 * lang.String)
	 */
	@Override
	public ActivityCatalog parseActivityCatalog(String fileName) throws SchedulerFacadeException {

		Object record;
		ActivityCatalog catalog = new ActivityCatalog();
		logger.debug("Started reading the input file {}", fileName);
		
		try {
			StreamFactory factory = StreamFactory.newInstance();
			factory.loadResource(AppConfiguration.BEANIO_ACTIVITY_CONF_FILE);
			logger.trace("Successfully loaded the bean IO configuration file : {}", AppConfiguration.BEANIO_ACTIVITY_CONF_FILE);

			String str = FileUtils.readFileToString(new File(fileName), "UTF-8");
			BeanReader reader = factory.createReader(AppConfiguration.BEANIO_ACTIVITY_READER, new StringReader(str));
			logger.trace("Successfully created the reader for file: {}", fileName);


			while ((record = reader.read()) != null) {
				Activity activity = (Activity) record;
				catalog.addActivity(activity);
			}
		} catch (Exception ioException) {
			throw new SchedulerFacadeException(ErrorMessages.FILE_READ_FAILED, ioException, ErrorCode.FACADE_ERROR);
		}
		logger.debug("Activity Catalog read from the input file {} : {}", fileName, catalog);
		return catalog;
	}
}
