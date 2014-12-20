package engine.event;

/*
 * Project Insanity - Evolved v.3
 * Events.java
 */

public abstract class Events {

	/**
	 * The delay, in milliseconds.
	 */
	private long delay;

	/**
	 * The running flag.
	 */
	private boolean running = true;

	/**
	 * Creates an event with the specified delay.
	 * 
	 * @param delay
	 *            The delay.
	 */
	public Events(final long delay) {
		this.delay = delay;
	}

	/**
	 * The execute method is called when the event is run. The general contract
	 * of the execute method is that it may take any action whatsoever.
	 */
	public abstract void execute();

	/**
	 * Gets the event delay.
	 * 
	 * @return The delay, in milliseconds.
	 */
	public long getDelay() {
		return delay;
	}

	/**
	 * Checks if the event is running.
	 * 
	 * @return <code>true</code> if the event is still running,
	 *         <code>false</code> if not.
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Sets the event delay.
	 * 
	 * @param delay
	 *            The delay to set.
	 * @throws IllegalArgumentException
	 *             if the delay is negative.
	 */
	public void setDelay(final long delay) {
		if (delay < 0) {
			throw new IllegalArgumentException("Delay must be positive.");
		}
		this.delay = delay;
	}

	/**
	 * Stops the event from running in the future.
	 */
	public void stop() {
		running = false;
	}

}
