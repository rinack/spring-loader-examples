package org.springtest.examples.test;

import java.util.Date;

public class Task {

	private long id;
	private String triggerName;
	private String cron;
	private State state;
	private Date nextExecute;

	public Task(long id, String triggerName, String cron, State state, Date nextExecute) {
		this.id = id;
		this.triggerName = triggerName;
		this.cron = cron;
		this.state = state;
		this.nextExecute = nextExecute;
	}

	public enum State {
		RUN, STOP, WRITTING_NEXT
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Date getNextExecute() {
		return nextExecute;
	}

	public void setNextExecute(Date nextExecute) {
		this.nextExecute = nextExecute;
	}

}
