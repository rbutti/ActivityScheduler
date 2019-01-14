package com.activityscheduler.domain;

import java.io.Serializable;
import java.util.UUID;

/**
 * An Abstract class for Domain objects. This class assigns an unique Id to all
 * the domain objects and makes them serializable.<br/>
 * The unique Id is generated using java's UUID generator.
 * 
 * @author rbutti
 *
 */
public abstract class AbstractDomainObject implements Serializable {

	private static final long serialVersionUID = 8610314777055616602L;

	private UUID id;

	public AbstractDomainObject() {
		super();
		this.id = UUID.randomUUID();
	}

	public AbstractDomainObject(UUID id) {
		super();
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractDomainObject other = (AbstractDomainObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractDomainObject [id=" + id + "]";
	}

}
