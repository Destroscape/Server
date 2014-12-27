package engine.task;

import game.entity.player.Player;

/*
 * Project Insanity - Evolved v.3
 * taskFactory.java
 */

public class TaskFactory {

	protected static class Task implements Runnable {

		private final Player client;
		private final boolean global;

		public Task(final Player client, final boolean global) {
			this.client = client;
			this.global = global;
		}

		public Player getClient() {
			return client;
		}

		public boolean isGlobal() {
			return global;
		}

		@Override
		public void run() {
		}
	}

	public static Task getDelayedGlobalTask(final String callbackFunction,
			final int actionID, final int actionX, final int actionY) {
		final Task task = new Task(null, true) {
			@Override
			public void run() {
			}
		};
		return task;
	}

	public static Task getDelayedTask(final String callbackFunction,
			final Player client, final int actionX, final int actionY) {
		final Task task = new Task(client, false) {
			@Override
			public void run() {
			}
		};
		return task;
	}

	public static Task getDelayedTask(final String callbackFunction,
			final Player client, final int actionID, final int actionX,
			final int actionY) {
		final Task task = new Task(client, false) {
			@Override
			public void run() {
			}
		};
		return task;
	}

}
