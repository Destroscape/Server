package game.content.partyroom;

import engine.event.Event;
import engine.event.EventContainer;
import engine.event.EventManager;
import engine.util.Misc;
import game.Config;
import game.Server;
import game.entity.player.Player;
import game.object.ObjectHandler;
import game.object.Objects;

public class Balloon {
	private int id;
	private int popped;
	private int x, y;
	private int face;
	private int item, amount;
	private boolean empty = false;
	private Objects object;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param popped
	 * @param x
	 * @param y
	 * @param item
	 * @param amount
	 * @param empty
	 */

	public Balloon(int id, int popped, int x, int y, int item, int amount) {
		int face = (-2 - Misc.random(3));
		object = new Objects(id, x, y, 0, face, 10, 0);
		this.id = id;
		this.popped = popped;
		this.face = face;
		this.x = x;
		this.y = y;
		this.item = item;
		this.amount = amount;
		this.empty = item == -1;
	}

	public void destruct() {
		ObjectHandler.removeObject(getObject());
		EventManager.getSingleton().addEvent(new Event() {
			@Override
			public void execute(EventContainer c) {
				ObjectHandler.addObject(getObject());
				Server.partyRoom.placeObject(getPoppedId(), getX(), getY(),
						getFace());
				c.stop();
			}
		}, 1000);
		EventManager.getSingleton().addEvent(new Event() {
			@Override
			public void execute(EventContainer c) {
				Server.partyRoom.placeObject(-1, getX(), getY(), getFace());
				c.stop();
			}
		}, 1400);
	}

	/**
	 * Drops the balloon after a set delay.
	 * 
	 * @param delay
	 */
	public void drop(int delay) {
		ObjectHandler.addObject(getObject());
		if (Config.SERVER_DEBUG) {
			if (!isEmpty())
				System.out.println("Dropping Ballon: " + getItem() + "("
						+ getAmount() + ")  at x" + x + " y" + y + "");
			else
				System.out.println("Dropping Empty Balloon...");
		}
		if (Config.PARTYROOM_DROP_DELAY) {
			final Balloon o = this;
			EventManager.getSingleton().addEvent(new Event() {
				@Override
				public void execute(EventContainer c) {
					Server.partyRoom.placeObject(getId(), o.getX(), o.getY(),
							o.getFace());
					c.stop();
				}
			}, delay);
		} else {
			ObjectHandler.addObject(getObject());
			Server.partyRoom.placeObject(getId(), getX(), getY(), getFace());
		}
	}

	/**
	 * Call for the item amount
	 * 
	 * @return amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Call for Object face
	 * 
	 * @return face
	 */
	public int getFace() {
		return face;
	}

	/**
	 * Call for Object ID
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Call for the item ID.
	 * 
	 * @return item
	 */
	public int getItem() {
		return item;
	}

	/**
	 * Call for the Global Object.
	 * 
	 * @return object
	 */
	public Objects getObject() {
		return object;
	}

	/**
	 * Call for Popped ID
	 * 
	 * @return
	 */
	public int getPoppedId() {
		return popped;
	}

	/**
	 * Call for the X coordinate of the balloon.
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Call for the Y coordinate of the balloon.
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Check to see it the Balloon is empty.
	 * 
	 * @return empty
	 */
	public boolean isEmpty() {
		return empty;
	}

	/**
	 * Pop Balloon Removes popped balloon and creates the item contained inside
	 * in it's place(if not empty)
	 */
	public void pop(final Player p) {
		ObjectHandler.removeObject(getObject());
		Server.partyRoom.remove(this);
		p.startAnimation(794);
		if (Config.PARTYROOM_DROP_DELAY) {
			final Balloon o = this;
			EventManager.getSingleton().addEvent(new Event() {
				@Override
				public void execute(EventContainer c) {
					Server.partyRoom.placeObject(o.getPoppedId(), o.getX(),
							o.getY(), o.getFace());
					c.stop();
				}
			}, 1000);
			EventManager.getSingleton().addEvent(new Event() {
				@Override
				public void execute(EventContainer c) {
					Server.partyRoom.placeObject(-1, o.getX(), o.getY(),
							o.getFace());
					if (!o.isEmpty()) {
						Server.itemHandler.createGroundItem(p,
								o.getItem(), o.getX(), o.getY(), o.getAmount(),
								p.playerId);
					}
					c.stop();
				}
			}, 1400);
		} else {
			ObjectHandler.removeObject(getObject());
			Server.partyRoom.placeObject(-1, getX(), getY(), getFace());
			if (!isEmpty())
				Server.itemHandler.createGroundItem(p, getItem(), getX(),
						getY(), getAmount(), p.playerId);
		}
	}

}
