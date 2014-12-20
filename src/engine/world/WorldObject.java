package engine.world;

public class WorldObject {

	public int x, y, height, id, type, face;

	public WorldObject() {

	}

	public WorldObject(final int x, final int y, final int h, final int id,
			final int type, final int face) {
		this.x = x;
		this.y = y;
		height = h;
		this.id = id;
		this.type = type;
		this.face = face;
	}

}
