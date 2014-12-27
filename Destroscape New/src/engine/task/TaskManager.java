package engine.task;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import engine.task.TaskFactory.Task;

/*
 * Project Insanity - Evolved v.3
 * TaskManager.java
 */

public class TaskManager {

	/*
	 * NOTE: The pool size _must_ remain 1 to keep synchronization proper.
	 */
	private static ScheduledThreadPoolExecutor scheduledExecutor = (ScheduledThreadPoolExecutor) Executors
			.newScheduledThreadPool(1);

	public static Future<?> registerClientTask(final Task task, final int delay) {
		final Future<?> taskFuture = TaskManager.scheduledExecutor.schedule(
				task, delay, TimeUnit.SECONDS);
		if (!task.isGlobal()) {

		}
		return taskFuture;
	}

	public static Future<?> registerDelayedTask(final Runnable task,
			final int delay, final TimeUnit unit) {
		return TaskManager.scheduledExecutor.schedule(task, delay, unit);
	}

	public static Future<?> scheduleTask(final Runnable task, final int delay,
			final int rate, final TimeUnit unit) {
		return TaskManager.scheduledExecutor.scheduleWithFixedDelay(task,
				delay, rate, unit);
	}

}
