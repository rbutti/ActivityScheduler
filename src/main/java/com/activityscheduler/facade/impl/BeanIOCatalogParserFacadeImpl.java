package com.activityscheduler.facade.impl;

import java.io.File;
import java.io.StringReader;

import org.apache.commons.io.FileUtils;
import org.beanio.BeanReader;
import org.beanio.StreamFactory;

import com.activityscheduler.constant.AppConfiguration;
import com.activityscheduler.domain.Activity;
import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.exception.ErrorCode;
import com.activityscheduler.exception.SchedulerFacadeException;
import com.activityscheduler.facade.CatalogParserFacade;

public class BeanIOCatalogParserFacadeImpl implements CatalogParserFacade {

	@Override
	public ActivityCatalog parseActivityCatalog(String fileName) throws SchedulerFacadeException {

		ActivityCatalog catalog = new ActivityCatalog();
		Object record;
		String str;
		try {
			StreamFactory factory = StreamFactory.newInstance();
			factory.loadResource(AppConfiguration.BEANIO_ACTIVITY_CONF_FILE);

			str = FileUtils.readFileToString(new File(fileName), "UTF-8");
			BeanReader reader = factory.createReader(AppConfiguration.BEANIO_ACTIVITY_READER, new StringReader(str));

			while ((record = reader.read()) != null) {
				Activity activity = (Activity) record;
				catalog.addActivity(activity);
			}
		} catch (Exception ioException) {
			throw new SchedulerFacadeException("Unable to parse the input file", ioException, ErrorCode.FACADE_ERROR);
		}
		return catalog;
	}
}
