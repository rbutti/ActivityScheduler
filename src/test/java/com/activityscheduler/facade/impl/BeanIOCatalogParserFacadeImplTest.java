package com.activityscheduler.facade.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.activityscheduler.domain.ActivityCatalog;
import com.activityscheduler.exception.SchedulerFacadeException;
import com.activityscheduler.facade.CatalogParserFacade;



public class BeanIOCatalogParserFacadeImplTest {
	
	private CatalogParserFacade facade;
	
	@Before
	public void before() {
		
		facade = new BeanIOCatalogParserFacadeImpl();
	}
	
	@Test
	public void testParseActivityCatalog_ValidInput() throws SchedulerFacadeException {
	
		ActivityCatalog catalog  = facade.parseActivityCatalog("src/test/resources/activities.txt");
		Assert.assertNotNull(catalog);
		Assert.assertEquals(21, catalog.getActivityCount());
		Assert.assertEquals("Duck Herding", catalog.getActivity(1).getName());
		Assert.assertEquals(60, catalog.getActivity(1).getDuration());
		Assert.assertFalse(catalog.getActivity(1).isScheduled());
		
		Assert.assertEquals("Salsa & Pickles sprint", catalog.getActivity(7).getName());
		Assert.assertEquals(15, catalog.getActivity(7).getDuration());
		Assert.assertFalse(catalog.getActivity(7).isScheduled());
	}
	
	@Test(expected=SchedulerFacadeException.class)
	public void testParseActivityCatalog_Exception() throws  SchedulerFacadeException {
	
		 facade.parseActivityCatalog("src/test/resources/nonExisting.txt");
	}
	
	@Test(expected=SchedulerFacadeException.class)
	public void testParseActivityCatalog_Exception2() throws  SchedulerFacadeException {
	
		 facade.parseActivityCatalog("src/test/resources/invalidActivities.txt");
	}


}
