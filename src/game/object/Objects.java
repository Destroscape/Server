package game.object;

/*
 * Project Insanity - Evolved v.3
 * Objects.java
 */

public class Objects {

	public static int objectId;
	public static int objectX;
	public static int objectY;
	public boolean bait;
	public String belongsTo;
	public long delay, oDelay;
	public int objectHeight, objectFace, xp, item, owner, target, objectType,
			objectTicks;

	public Objects(final int id, final int x, final int y, final int height,
			final int face, final int type, final int ticks) {
		objectId = id;
		objectX = x;
		objectY = y;
		objectHeight = height;
		objectFace = face;
		objectType = type;
		objectTicks = ticks;
	}

	public Objects(int objectId, int x, int y, int height, int face,
			int type) {
		Objects.objectId = objectId;
		Objects.objectX = x;
		Objects.objectY = y;
		this.objectHeight = height;
		this.objectFace = face;
		this.objectType = type;
		this.objectTicks = 0;
	}

	public int getObjectFace() {
		return objectFace;
	}

	public int getObjectHeight() {
		return objectHeight;
	}

	public static int getObjectId() {
		return objectId;
	}

	public int getObjectType() {
		return objectType;
	}

	public static int getObjectX() {
		return objectX;
	}

	public static int getObjectY() {
		return objectY;
	}

}
