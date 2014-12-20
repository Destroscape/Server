package game.object;

import engine.util.Misc;
import game.Server;
import game.clip.region.Region;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * Project Insanity - Evolved v.3
 * ObjectHandler.java
 */
@SuppressWarnings("resource")
public class ObjectHandler {

	public static List<Objects> globalObjects = new ArrayList<Objects>();
	public static List<Objects> globalObjects1 = new ArrayList<Objects>();
	public static final int MAX_DOORS = 30;
	public static int[][] doors = new int[ObjectHandler.MAX_DOORS][5];
	public static int doorFace = 0;
	public final int IN_USE_ID = 14825;
	public int[] obeliskIds = { 14829, 14830, 111235, 14828, 14826, 14831 };
	public int[][] obeliskCoords = { { 3154, 3618 }, { 3225, 3665 },
			{ 3033, 3730 }, { 3104, 3792 }, { 2978, 3864 }, { 3305, 3914 } };
	public boolean[] activated = { false, false, false, false, false, false };

	public ObjectHandler() {
		loadGlobalObjects("./Data/cfg/Global Objects.cfg");
	}

	/**
	 * Adds object to list
	 **/
	public static void addObject(final Objects object) {
		globalObjects.add(object);
	}

	public static void addObject1(final Objects object) {
		globalObjects1.add(object);
	}

	public static void createAnObject(Player c, int id, int x, int y) {
		Objects OBJECT = new Objects(id, x, y, 0, 0, 10, 0);
		if (id == -1) {
			removeObject1(OBJECT);
		} else {
			addObject1(OBJECT);
		}
		Server.objectHandler.placeObject(OBJECT);
	}

	public static void createAnObject(Player c, int id, int x, int y, int face) {
		Objects OBJECT = new Objects(id, x, y, 0, face, 10, 0);
		if (id == -1) {
			removeObject1(OBJECT);
		} else {
			addObject1(OBJECT);
		}
		Server.objectHandler.placeObject(OBJECT);
	}

	public void doorHandling(int doorId, final int doorX, final int doorY,
			final int doorHeight) {
		for (final int[] door : ObjectHandler.doors) {
			if (doorX == door[0] && doorY == door[1] && doorHeight == door[2]) {
				if (door[4] == 0) {
					doorId++;
				} else {
					doorId--;
				}
				for (final Player p : PlayerHandler.players) {
					if (p != null) {
						final Player person = p;
						if (person != null) {
							if (p.heightLevel == doorHeight) {
								if (person.distanceToPoint(doorX, doorY) <= 60) {
									person.getPA().object(-1, door[0], door[1],
											0, 0);
									if (door[3] == 0 && door[4] == 1) {
										person.getPA().object(doorId, door[0],
												door[1] + 1, -1, 0);
									} else if (door[3] == -1 && door[4] == 1) {
										person.getPA().object(doorId,
												door[0] - 1, door[1], -2, 0);
									} else if (door[3] == -2 && door[4] == 1) {
										person.getPA().object(doorId, door[0],
												door[1] - 1, -3, 0);
									} else if (door[3] == -3 && door[4] == 1) {
										person.getPA().object(doorId,
												door[0] + 1, door[1], 0, 0);
									} else if (door[3] == 0 && door[4] == 0) {
										person.getPA().object(doorId,
												door[0] - 1, door[1], -3, 0);
									} else if (door[3] == -1 && door[4] == 0) {
										person.getPA().object(doorId, door[0],
												door[1] - 1, 0, 0);
									} else if (door[3] == -2 && door[4] == 0) {
										person.getPA().object(doorId,
												door[0] + 1, door[1], -1, 0);
									} else if (door[3] == -3 && door[4] == 0) {
										person.getPA().object(doorId, door[0],
												door[1] + 1, -2, 0);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public int getObeliskIndex(final int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id) {
				return j;
			}
		}
		return -1;
	}

	public boolean isObelisk(final int id) {
		for (final int obeliskId : obeliskIds) {
			if (obeliskId == id) {
				return true;
			}
		}
		return false;
	}

	public boolean loadDoorConfig(final String fileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader objectFile = null;
		try {
			objectFile = new BufferedReader(new FileReader("./" + fileName));
		} catch (final FileNotFoundException fileex) {
			Misc.println(fileName + ": file not found.");
			return false;
		}
		try {
			line = objectFile.readLine();
		} catch (final IOException ioexception) {
			Misc.println(fileName + ": error loading file.");
			return false;
		}
		int door = 0;
		while (EndOfFile == false && line != null) {
			line = line.trim();
			final int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("door")) {
					ObjectHandler.doors[door][0] = Integer.parseInt(token3[0]);
					ObjectHandler.doors[door][1] = Integer.parseInt(token3[1]);
					ObjectHandler.doors[door][2] = Integer.parseInt(token3[2]);
					ObjectHandler.doors[door][3] = Integer.parseInt(token3[3]);
					ObjectHandler.doors[door][4] = Integer.parseInt(token3[4]);
					door++;
				}
			} else {
				if (line.equals("[ENDOFDOORLIST]")) {
					try {
						objectFile.close();
					} catch (final IOException ioexception) {
					}
					return true;
				}
			}
			try {
				line = objectFile.readLine();
			} catch (final IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			objectFile.close();
		} catch (final IOException ioexception) {
		}
		return false;
	}

	public boolean loadGlobalObjects(final String fileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader objectFile = null;
		try {
			objectFile = new BufferedReader(new FileReader("./" + fileName));
		} catch (final FileNotFoundException fileex) {
			Misc.println(fileName + ": file not found.");
			return false;
		}
		try {
			line = objectFile.readLine();
		} catch (final IOException ioexception) {
			Misc.println(fileName + ": error loading file.");
			return false;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			final int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("object")) {
					final Objects object = new Objects(
							Integer.parseInt(token3[0]),
							Integer.parseInt(token3[1]),
							Integer.parseInt(token3[2]),
							Integer.parseInt(token3[3]),
							Integer.parseInt(token3[4]),
							Integer.parseInt(token3[5]), 0);
					addObject(object);
				}
			} else {
				if (line.equals("[ENDOFOBJECTLIST]")) {
					try {
						objectFile.close();
					} catch (final IOException ioexception) {
					}
					return true;
				}
			}
			try {
				line = objectFile.readLine();
			} catch (final IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			objectFile.close();
		} catch (final IOException ioexception) {
		}
		return false;
	}

	public Objects objectExists(final int objectX, final int objectY,
			final int objectHeight) {
		for (final Objects o : globalObjects) {
			if (Objects.getObjectX() == objectX
					&& Objects.getObjectY() == objectY
					&& o.getObjectHeight() == objectHeight) {
				return o;
			}
		}
		return null;
	}

	/**
	 * Creates the object for anyone who is within 60 squares of the object
	 **/
	public void placeObject(final Objects o) {
		for (final Player p : PlayerHandler.players) {
			if (p != null) {
				removeAllObjects(o);
				globalObjects.add(o);
				final Player person = p;
				if (person != null) {
					if (p.heightLevel == o.getObjectHeight()
							&& o.objectTicks == 0) {
						if (person.distanceToPoint(Objects.getObjectX(),
								Objects.getObjectY()) <= 60) {
							person.getPA().object(Objects.getObjectId(),
									Objects.getObjectX(), Objects.getObjectY(),
									o.getObjectFace(), o.getObjectType());
						}
					}
				}
			}
		}
	}

	public void process() {
		for (int j = 0; j < globalObjects.size(); j++) {
			if (globalObjects.get(j) != null) {
				final Objects o = globalObjects.get(j);
				if (o.objectTicks > 0) {
					o.objectTicks--;
				}
				if (o.objectTicks == 1) {
					final Objects deleteObject = objectExists(
							Objects.getObjectX(), Objects.getObjectY(),
							o.getObjectHeight());
					removeObject(deleteObject);
					o.objectTicks = 0;
					placeObject(o);
					removeObject(o);
					if (isObelisk(Objects.objectId)) {
						final int index = getObeliskIndex(Objects.objectId);
						if (activated[index]) {
							activated[index] = false;
							teleportObelisk(index);
						}
					}
				}
			}

		}
	}

	/*public static void removeAllObjects(final Objects o) {
		for (final Objects s : globalObjects) {
			if (Objects.getObjectX() == Objects.objectX
					&& Objects.getObjectY() == Objects.objectY
					&& s.getObjectHeight() == o.getObjectHeight()) {
				globalObjects.remove(s);
				break;
			}
		}
	}*/

	public static void removeAllObjects(Objects o) {
		for (Objects s : globalObjects) { 
			if (s.getObjectX() == o.objectX && s.getObjectY() == o.objectY && s.getObjectHeight() == o.getObjectHeight()) {
				globalObjects.remove(s);
				break;
			}
		}
	}

	/**
	 * Removes object from list
	 **/
	public static void removeObject(final Objects object) {
		globalObjects.remove(object);
	}

	public static void removeObject1(final Objects object) {
		globalObjects1.remove(object);
	}

	public void startObelisk(final int obeliskId) {
		final int index = getObeliskIndex(obeliskId);
		if (index >= 0) {
			if (!activated[index]) {
				activated[index] = true;
				final Objects obby1 = new Objects(14825,
						obeliskCoords[index][0], obeliskCoords[index][1], 0,
						-1, 10, 0);
				final Objects obby2 = new Objects(14825,
						obeliskCoords[index][0] + 4, obeliskCoords[index][1],
						0, -1, 10, 0);
				final Objects obby3 = new Objects(14825,
						obeliskCoords[index][0], obeliskCoords[index][1] + 4,
						0, -1, 10, 0);
				final Objects obby4 = new Objects(14825,
						obeliskCoords[index][0] + 4,
						obeliskCoords[index][1] + 4, 0, -1, 10, 0);
				addObject(obby1);
				addObject(obby2);
				addObject(obby3);
				addObject(obby4);
				Server.objectHandler.placeObject(obby1);
				Server.objectHandler.placeObject(obby2);
				Server.objectHandler.placeObject(obby3);
				Server.objectHandler.placeObject(obby4);
				final Objects obby5 = new Objects(obeliskIds[index],
						obeliskCoords[index][0], obeliskCoords[index][1], 0,
						-1, 10, 10);
				final Objects obby6 = new Objects(obeliskIds[index],
						obeliskCoords[index][0] + 4, obeliskCoords[index][1],
						0, -1, 10, 10);
				final Objects obby7 = new Objects(obeliskIds[index],
						obeliskCoords[index][0], obeliskCoords[index][1] + 4,
						0, -1, 10, 10);
				final Objects obby8 = new Objects(obeliskIds[index],
						obeliskCoords[index][0] + 4,
						obeliskCoords[index][1] + 4, 0, -1, 10, 10);
				addObject(obby5);
				addObject(obby6);
				addObject(obby7);
				addObject(obby8);
			}
		}
	}

	public void teleportObelisk(final int port) {
		int random = Misc.random(5);
		while (random == port) {
			random = Misc.random(5);
		}
		for (final Player player : PlayerHandler.players) {
			if (player != null) {
				final Player c = player;
				if (c.goodDistance(c.getX(), c.getY(),
						obeliskCoords[port][0] + 2, obeliskCoords[port][1] + 2,
						1)) {
					c.getPA().startTeleport2(obeliskCoords[random][0] + 2,
							obeliskCoords[random][1] + 2, 0);
				}
			}
		}
	}



	/**
	* Update objects when entering a new region or logging in
	**/
	public void updateObjects(Player c) { 
		for(Objects o : globalObjects) {
			if(c != null) {
				if(c.heightLevel == o.getObjectHeight() && o.objectTicks == 0) {
					if (c.distanceToPoint(o.getObjectX(), o.getObjectY()) <= 60) {
						c.getPA().object(o.getObjectId(), o.getObjectX(), o.getObjectY(), o.getObjectFace(), o.getObjectType());
					}
				}		
			}
		}
		if (c.distanceToPoint(2961, 3389) <= 60) {
			c.getPA().object(6552, 2961, 3389, -1, 10);		
		}
	}

	/**
	 * Creates the object for all minigame players who is within 60 squares of
	 * the object
	 **/
	@SuppressWarnings("rawtypes")
	public static void placeMinigameObject(Objects o,
			Map<Player, Integer> minigamePlayers) {
		removeAllObjects(o);
		globalObjects.add(o);
		int objectId = Objects.getObjectId();
		if (objectId != 4439 && objectId != 2732 && objectId != 4900
				&& objectId != 4901 && objectId != 14825 && objectId != 1343
				&& objectId != 734 && objectId != -1 && objectId != 1531
				&& objectId != 1517 && objectId != 1520)// && objectId != 4466
														// && objectId != 4466
														// && objectId != 4425
			Region.addObject(objectId, Objects.getObjectX(),
					Objects.getObjectY(), o.getObjectHeight(),
					o.getObjectType(), o.getObjectFace());
		if (objectId == -1 || objectId == 734 || objectId == 4425
				|| objectId == 4466 || objectId == 4429 || objectId == 4468
				|| objectId == 1531 || objectId == 1517 || objectId == 1520
				|| objectId == 4439)
			Region.removeClipping(Objects.getObjectX(), Objects.getObjectY(),
					o.getObjectHeight());

		Iterator iterator = minigamePlayers.keySet().iterator();
		while (iterator.hasNext()) {
			Player minigamePlayer = (Player) iterator.next();
			if (minigamePlayer.heightLevel == o.getObjectHeight()) {
				if (minigamePlayer.distanceToPoint(Objects.getObjectX(),
						Objects.getObjectY()) <= 60)
					minigamePlayer.getPA().object(objectId,
							Objects.getObjectX(), Objects.getObjectY(),
							o.getObjectFace(), o.getObjectType());
			}
		}
	}

}
