package engine.event;

import java.util.ArrayList;
import java.util.List;

/*
 * Project Insanity - Evolved v.3
 * EventManager.java
 */

public class EventManager implements Runnable {

	/**
	 * A reference to the singleton;
	 */
	private static EventManager singleton = null;

	/**
	 * The waitFor variable is multiplied by this before the call to wait() is
	 * made. We do this because other events may be executed after waitFor is
	 * set (and take time). We may need to modify this depending on event count?
	 * Some proper tests need to be done.
	 */
	private static final double WAIT_FOR_FACTOR = 0.5;

	/**
	 * Gets the event manager singleton. If there is no singleton, the singleton
	 * is created.
	 * 
	 * @return The event manager singleton.
	 */
	public static EventManager getSingleton() {
		if (EventManager.singleton == null) {
			EventManager.singleton = new EventManager();
			EventManager.singleton.thread = new Thread(EventManager.singleton);
			EventManager.singleton.thread.start();
		}
		return EventManager.singleton;
	}

	/**
	 * Initialises the event manager (if it needs to be).
	 */
	public static void initialize() {
		EventManager.getSingleton();
	}

	/**
	 * A list of events that are being executed.
	 */
	private final List<EventContainer> events;

	/**
	 * The event manager thread. So we can interrupt it and end it nicely on
	 * shutdown.
	 */
	private Thread thread;

	/**
	 * Initialise the event manager.
	 */
	private EventManager() {
		events = new ArrayList<EventContainer>();
	}

	/**
	 * Adds an event.
	 * 
	 * @param event
	 *            The event to add.
	 * @param tick
	 *            The tick time.
	 */
	public synchronized void addEvent(final Event event, final int tick) {
		events.add(new EventContainer(event, tick));
		notify();
	}

	@Override
	/*
	 * Processes events. Works kinda like newer versions of cron.
	 */
	public synchronized void run() {
		long waitFor = -1;
		final List<EventContainer> remove = new ArrayList<EventContainer>();
		while (true) {
			// reset wait time
			waitFor = -1;
			// process all events
			for (final EventContainer container : events) {
				if (container.isRunning()) {
					if (System.currentTimeMillis() - container.getLastRun() >= container
							.getTick()) {
						try {
							container.execute();
						} catch (final Exception e) {
							e.printStackTrace();
						}
					}
					if (container.getTick() < waitFor || waitFor == -1) {
						waitFor = container.getTick();
					}
				} else {
					// add to remove list
					remove.add(container);
				}
			}
			// remove events that have completed
			for (final EventContainer container : remove) {
				events.remove(container);
			}
			remove.clear();
			// no events running
			try {
				if (waitFor == -1) {
					wait(); // wait with no timeout
				} else {
					// an event is running, wait for that time or until a new
					// event is added
					final int decimalWaitFor = (int) Math.ceil(waitFor
							* EventManager.WAIT_FOR_FACTOR);
					wait(decimalWaitFor);
				}
			} catch (final InterruptedException e) {
				break; // stop running
			}
		}
	}

	/**
	 * Shuts the event manager down.
	 */
	public void shutdown() {
		thread.interrupt();
	}

}
