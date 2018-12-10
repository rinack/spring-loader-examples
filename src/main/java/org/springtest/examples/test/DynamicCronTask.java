package org.springtest.examples.test;

import java.util.Arrays;
import java.util.Date;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ContextLifecycleScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;

public class DynamicCronTask {

	private static ContextLifecycleScheduledTaskRegistrar taskRegistrar = null;

	private static String cron;

	public void configureTask() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(10);
		scheduler.initialize();

		if (taskRegistrar != null) {
			taskRegistrar.destroy();
		}

		taskRegistrar = new ContextLifecycleScheduledTaskRegistrar();
		taskRegistrar.setScheduler(scheduler);

		Runnable runnable = new Runnable() {
			@Override
			public void run() {

			}
		};

		Trigger trigger = new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				CronTrigger trigger = new CronTrigger(cron);
				Date nextExec = trigger.nextExecutionTime(triggerContext);
				return nextExec;
			}
		};

		TriggerTask triggerTask = new TriggerTask(runnable, trigger);
		taskRegistrar.setTriggerTasksList(Arrays.asList(triggerTask));
		taskRegistrar.afterSingletonsInstantiated();
	}


}
