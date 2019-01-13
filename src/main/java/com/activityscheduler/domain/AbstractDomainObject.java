package com.activityscheduler.domain;

import java.io.Serializable;
import java.util.UUID;

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

}
