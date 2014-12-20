package engine.event;

/*
 * Project Insanity - Evolved v.3
 * Event.java
 */

public interface Event {

	/**
	 * Called when the event is executed.
	 * 
	 * @param container
	 *            The event container, so the event can dynamically change the
	 *            tick time etc.
	 */
	public void execute(EventContainer container);

}
