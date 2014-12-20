package engine.event;

import game.Server;

import java.util.ArrayList;
import java.util.List;

/*
 * Project Insanity - Evolved v.3
 * CycleEventHandler.java
 */

public class CycleEventHandler {

	/**
	 * The instance of this class
	 */
	private static CycleEventHandler instance;

	/**
	 * Returns the instance of this class
	 * 
	 * @return
	 */
	public static CycleEventHandler getSingleton() {
		if (CycleEventHandler.instance == null) {
			CycleEventHandler.instance = new CycleEventHandler();
		}
		return CycleEventHandler.instance;
	}

	/**
	 * Holds all of our events currently being ran
	 */
	private final List<CycleEventContainer> events;

	/**
	 * Creates a new instance of this class
	 */
	public CycleEventHandler() {
		events = new ArrayList<CycleEventContainer>();
	}

	/**
	 * Add an event to the list
	 * 
	 * @param id
	 * @param owner
	 * @param event
	 * @param cycles
	 */
	public void addEvent(final int id, final Object owner,
			final CycleEvent event, final int cycles) {
		events.add(new CycleEventContainer(id, owner, event, cycles));
	}

	/**
	 * Add an event to the list
	 * 
	 * @param owner
	 * @param event
	 * @param cycles
	 */
	public void addEvent(final Object owner, final CycleEvent event,
			final int cycles) {
		events.add(new CycleEventContainer(-1, owner, event, cycles));
	}

	/**
	 * Returns the amount of events currently running
	 * 
	 * @return amount
	 */
	public int getEventsCount() {
		return events.size();
	}

	/**
	 * Execute and remove events
	 */
	public void process() {
		final List<CycleEventContainer> eventsCopy = new ArrayList<CycleEventContainer>(
				events);
		final List<CycleEventContainer> remove = new ArrayList<CycleEventContainer>();
		for (final CycleEventContainer c : eventsCopy) {
			if (c != null) {
				if (c.needsExecution()) {
					c.execute();
				}
				if (!c.isRunning()) {
					remove.add(c);
				}
			}
		}
		for (final CycleEventContainer c : remove) {
			events.remove(c);
		}
	}

	/**
	 * Stops all events for a specific owner and id
	 * 
	 * @param id
	 */
	public void stopEvents(final int id) {
		for (final CycleEventContainer c : events) {
			if (id == c.getID()) {
				c.stop();
			}
		}
	}

	/**
	 * Stops all events for a specific owner and id
	 * 
	 * @param owner
	 */
	public void stopEvents(final Object owner) {
		for (final CycleEventContainer c : events) {
			if (c.getOwner() == owner) {
				c.stop();
			}
		}
	}

	/**
	 * Stops all events for a specific owner and id
	 * 
	 * @param owner
	 * @param id
	 */
	public void stopEvents(final Object owner, final int id) {
		for (final CycleEventContainer c : events) {
			if (c.getOwner() == owner && id == c.getID()) {
				c.stop();
			}
		}
	}

	public void stopEvent(CycleEvent e) {
		for (CycleEventContainer c : events) {
			if (c.getEvent() == e) {
				c.forceStop();
			}
		}
	}

	public void addEvent(CycleEvent event, int cycles) {
		addEvent(Server.class, event, cycles);
	}

}
