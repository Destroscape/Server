package engine.event;

/*
 * Project Insanity - Evolved v.3
 * EventContainer.java
 */

public class EventContainer {

	/**
	 * The actual event.
	 */
	private final Event event;

	/**
	 * A flag which specifies if the event is running;
	 */
	private boolean isRunning;

	/**
	 * When this event was last run.
	 */
	private long lastRun;

	/**
	 * The tick time in milliseconds.
	 */
	private final int tick;

	/**
	 * The event container.
	 * 
	 * @param evt
	 * @param tick
	 */
	protected EventContainer(final Event evt, final int tick) {
		this.tick = tick;
		event = evt;
		isRunning = true;
		lastRun = System.currentTimeMillis();
		// can be changed to 0 if you want events to run straight away
	}

	/**
	 * Executes the event!
	 */
	public void execute() {
		lastRun = System.currentTimeMillis();
		event.execute(this);
	}

	/**
	 * Gets the last run time.
	 * 
	 * @return
	 */
	public long getLastRun() {
		return lastRun;
	}

	/**
	 * Returns the tick time.
	 * 
	 * @return
	 */
	public int getTick() {
		return tick;
	}

	/**
	 * Returns the is running flag.
	 * 
	 * @return
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * Stops this event.
	 */
	public void stop() {
		isRunning = false;
	}

}
