package com.activityscheduler.domain;

import java.time.LocalTime;

/**
 * A domain object extending {@link AbstractDomainObject} to hold the event
 * specific information
 * 
 * @author rbutti
 *
 */
public class EventInfo extends AbstractDomainObject {

	public static String DEFAULT_EVENT_NAME = "General Event";

	private static final long serialVersionUID = 5306096748756621498L;
	private LocalTime eventStartTime;
	private LocalTime eventEndTime;
	private String name;

	public EventInfo(String name, LocalTime eventStartTime, LocalTime eventEndTime) {
		super();
		this.eventStartTime = eventStartTime;
		this.eventEndTime = eventEndTime;
		this.name = name;
	}


	public LocalTime getEventStartTime() {
		return eventStartTime;
	}

	public void setEventStartTime(LocalTime eventStartTime) {
		this.eventStartTime = eventStartTime;
	}

	public LocalTime getEventEndTime() {
		return eventEndTime;
	}

	public void setEventEndTime(LocalTime eventEndTime) {
		this.eventEndTime = eventEndTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((eventEndTime == null) ? 0 : eventEndTime.hashCode());
		result = prime * result + ((eventStartTime == null) ? 0 : eventStartTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventInfo other = (EventInfo) obj;
		if (eventEndTime == null) {
			if (other.eventEndTime != null)
				return false;
		} else if (!eventEndTime.equals(other.eventEndTime))
			return false;
		if (eventStartTime == null) {
			if (other.eventStartTime != null)
				return false;
		} else if (!eventStartTime.equals(other.eventStartTime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventInfo [eventStartTime=" + eventStartTime + ", eventEndTime=" + eventEndTime + ", name=" + name
				+ "]";
	}

}
