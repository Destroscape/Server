package game.object;

import game.Server;

/*
 * Project Insanity - Evolved v.3
 * Object.java
 */

public class Object {

	public int objectId;
	public int objectX;
	public int objectY;
	public int height;
	public int face;
	public int type;
	public int newId;
	public int tick;

	public Object(final int id, final int x, final int y, final int height,
			final int face, final int type, final int newId, final int ticks) {
		objectId = id;
		objectX = x;
		objectY = y;
		this.height = height;
		this.face = face;
		this.type = type;
		this.newId = newId;
		tick = ticks;
		Server.objectManager.addObject(this);
	}

}
