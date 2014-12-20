package engine.world;

import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Hashtable;

public final class WorldMap {

	public static Hashtable<Integer, GameObject> gameObjects = new Hashtable<Integer, GameObject>();
	public static final HashMap<Integer, String> map = new HashMap<Integer, String>();

	public static boolean canMove(final int baseX, final int baseY,
			final int toX, final int toY) {
		final int diffX = baseX - toX;
		final int diffY = baseY - toY;
		int moveX = 0;
		int moveY = 0;
		if (diffX < 0) {
			moveX = 1;
		} else if (diffX > 0) {
			moveX = -1;
		}
		if (diffY < 0) {
			moveY = 1;
		} else if (diffY > 0) {
			moveY = -1;
		}
		if (moveX > 0 && moveY > 0) {
			if (WorldMap.solidObjectExists(baseX + 1, baseY + 1)
					|| WorldMap.solidObjectExists(baseX + 1, baseY)
					|| WorldMap.solidObjectExists(baseX, baseY + 1)) {
				return false;
			}
		} else if (moveX < 0 && moveY < 0) {
			if (WorldMap.solidObjectExists(baseX - 1, baseY - 1)
					|| WorldMap.solidObjectExists(baseX - 1, baseY)
					|| WorldMap.solidObjectExists(baseX, baseY - 1)) {
				return false;
			}
		} else if (moveX > 0 && moveY < 0) {
			if (WorldMap.solidObjectExists(baseX + 1, baseY - 1)
					|| WorldMap.solidObjectExists(baseX + 1, baseY)
					|| WorldMap.solidObjectExists(baseX, baseY - 1)) {
				return false;
			}
		} else if (moveX < 0 && moveY > 0) {
			if (WorldMap.solidObjectExists(baseX - 1, baseY + 1)
					|| WorldMap.solidObjectExists(baseX - 1, baseY)
					|| WorldMap.solidObjectExists(baseX, baseY + 1)) {
				return false;
			}
		} else if (moveX < 0 && moveY == 0) {
			if (WorldMap.solidObjectExists(baseX - 1, baseY)) {
				return false;
			}
		} else if (moveX > 0 && moveY == 0) {
			if (WorldMap.solidObjectExists(baseX + 1, baseY)) {
				return false;
			}
		} else if (moveX == 0 && moveY < 0) {
			if (WorldMap.solidObjectExists(baseX, baseY - 1)) {
				return false;
			}
		} else if (moveX == 0 && moveY > 0) {
			if (WorldMap.solidObjectExists(baseX, baseY + 1)) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unused")
	public static void loadWorldMap() {
		try {
			RandomAccessFile in = null;
			byte[] cache = null;
			int ptr = 0;
			final long a = System.currentTimeMillis();
			in = new RandomAccessFile("./Data/worldmap.bin", "r");
			cache = new byte[(int) in.length()];
			in.read(cache, 0, (int) in.length());
			in.close();
			for (int i = 0; i < 1280618; i++/* ,j++ */) {
				final int objectId = (cache[ptr++] & 0xFF) << 8 | cache[ptr++]
						& 0xFF;
				final int objectX = (cache[ptr++] & 0xFF) << 8 | cache[ptr++]
						& 0xFF;
				final int objectY = (cache[ptr++] & 0xFF) << 8 | cache[ptr++]
						& 0xFF;
				final int objectHeight = cache[ptr++] & 0xFF;
				final int objectType = cache[ptr++] & 0xFF;
				final int objectFace = cache[ptr++] & 0xFF;
				final GameObject go = new GameObject(objectId, objectType,
						objectX, objectY, objectFace);
				if (go.type() != 0) {
					WorldMap.gameObjects.put(go.y() + (go.x() << 16), go);
				}
			}
			final long took = System.currentTimeMillis() - a;
			System.out.println("Loaded " + WorldMap.gameObjects.size()
					+ " clips.");
			System.out.println("Loaded WorldMap In (" + took + " ms)... ");
		} catch (final Exception e2) {
			e2.printStackTrace();
		}
	}

	public static boolean solidObjectExists(final int x, final int y) {
		final GameObject go = WorldMap.gameObjects.get(y + (x << 16));
		if (go != null && go.type() == 2 && go.x() == x && go.y() == y) {
			return true;
		}
		if (go != null && go.type() == 10 && go.x() == x && go.y() == y) {
			return true;
		}
		if (go != null && go.type() == 9 && go.x() == x && go.y() == y) {
			return true;
		}
		if (go != null && go.type() == 8 && go.x() == x && go.y() == y) {
			return true;
		}
		if (go != null && go.type() == 7 && go.x() == x && go.y() == y) {
			return true;
		}
		if (go != null && go.type() == 4 && go.x() == x && go.y() == y) {
			return true;
		}
		return false;
	}

}