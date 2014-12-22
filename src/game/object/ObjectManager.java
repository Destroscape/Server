package game.object;

import engine.util.Misc;
import game.Server;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ObjectManager {

	public ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<Object> toRemove = new ArrayList<Object>();

	public void process() {
		for (final Object o : objects) {
			if (o.tick > 0) {
				o.tick--;
			} else {
				updateObject(o);
				toRemove.add(o);
			}
		}
		for (final Object o : toRemove) {
			if (o.objectId == 2732) {
				for (final Player player : PlayerHandler.players) {
					if (player != null) {
						final Player c = player;
						Server.itemHandler.createGroundItem(c, 592, o.objectX,
								o.objectY, 1, c.playerId);
					}
				}
			}
			if (isObelisk(o.newId)) {
				final int index = getObeliskIndex(o.newId);
				if (activated[index]) {
					activated[index] = false;
					teleportObelisk(index);
				}
			}
			objects.remove(o);
		}
		toRemove.clear();
	}

	public void removeObject(int x, int y) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Player c = PlayerHandler.players[j];
				c.getPA().object(-1, x, y, 0, 10);
			}
		}
	}

	public void updateObject(Object o) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Player c = PlayerHandler.players[j];
				c.getPA().object(o.newId, o.objectX, o.objectY, o.face, o.type);
			}
		}
	}

	public void placeObject(Object o) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Player c = PlayerHandler.players[j];
				if (c.distanceToPoint(o.objectX, o.objectY) <= 60)
					c.getPA().object(o.objectId, o.objectX, o.objectY, o.face,
							o.type);
			}
		}
	}

	public Object getObject(int x, int y, int height) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y && o.height == height)
				return o;
		}
		return null;
	}

	public boolean objectExists(final int x, final int y) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y) {
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings({ "unused", "resource" })
	public void loadDeletedObjects(Player c,String s)
	{
		int count = 0;
		try
		{
			Scanner scanner = new Scanner(new File((new StringBuilder()).append("./data/cfg/").append(s).toString()));
			do
			{
				if(!scanner.hasNextLine())
					break;
				String as[] = scanner.nextLine().split(" ");
				int posX = Integer.parseInt(as[0]);
				int posY = Integer.parseInt(as[1]);
				int posH = Integer.parseInt(as[2]);
				c.getPA().checkObjectSpawn(-1, posX, posY, posH, 10);
				count++;
			} while(true);
		}
		catch(IOException ioexception)
		{
			ioexception.printStackTrace();
		}
	}

	public void loadObjects(Player c) {
		if (c == null)
			return;
		for (Object o : objects) {
			if (loadForPlayer(o, c))
				c.getPA().object(o.objectId, o.objectX, o.objectY, o.face,
						o.type);
		}
		loadCustomSpawns(c);
		loadDeletedObjects(c,"deletedObjects.txt");
	}

	@SuppressWarnings("unused")
	private int[][] customObjects = { {} };

	public static void loadCustomSpawns(Player c) {

		//Penguin crates
		c.getPA().checkObjectSpawn(7347, 2655, 10389, 0, 10);
		c.getPA().checkObjectSpawn(7348, 2657, 10389, 0, 10);
		c.getPA().checkObjectSpawn(7349, 2659, 10389, 0, 10);
		c.getPA().checkObjectSpawn(6911, 2661, 10389, 0, 10);

		c.getPA().checkObjectSpawn(19038, 3099, 3496, 0, 10);

		/**
		 * Removing
		 */
		
		c.getPA().checkObjectSpawn(-1, 2559, 4959, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2560, 4959, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2560, 4960, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2559, 4960, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3095, 3957, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3092, 3957, 0, 0);

		c.getPA().checkObjectSpawn(-1, 2669, 3316, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2799, 9200, 0, 10);

		// bank room
		c.getPA().checkObjectSpawn(-1, 2703, 5347, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2703, 5346, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2703, 5345, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2703, 5351, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2703, 5352, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2703, 5353, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2704, 5352, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2704, 5351, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2704, 5347, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2704, 5346, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2701, 5345, 0, 10);

		// glacors
		/*
		 * for (int j = 5467; j < 5485; j++) { c.getPA().checkObjectSpawn(-1,
		 * 2901, j, 0, 10); c.getPA().checkObjectSpawn(-1, 2888, j, 0, 10);
		 * 
		 * } for (int j = 5466; j < 5486; j++) { c.getPA().checkObjectSpawn(-1,
		 * 2900, j, 0, 10); c.getPA().checkObjectSpawn(-1, 2899, j, 0, 10);
		 * c.getPA().checkObjectSpawn(-1, 2898, j, 0, 10);
		 * c.getPA().checkObjectSpawn(-1, 2897, j, 0, 10);
		 * c.getPA().checkObjectSpawn(-1, 2896, j, 0, 10);
		 * c.getPA().checkObjectSpawn(-1, 2895, j, 0, 10);
		 * c.getPA().checkObjectSpawn(-1, 2894, j, 0, 10);
		 * c.getPA().checkObjectSpawn(-1, 2893, j, 0, 10);
		 * c.getPA().checkObjectSpawn(-1, 2892, j, 0, 10);
		 * c.getPA().checkObjectSpawn(-1, 2891, j, 0, 10);
		 * c.getPA().checkObjectSpawn(-1, 2890, j, 0, 10);
		 * c.getPA().checkObjectSpawn(-1, 2889, j, 0, 10); }
		 */

		//Fairy Rings, Object ID = 12003

		
		/**
		 * Lunar Isle
		 */
		
		c.getPA().checkObjectSpawn(12003, 2107, 3919, 0, 10);

		/**
		 * Dungeoneering
		 */

		c.getPA().checkObjectSpawn(6553, 2777, 9195, 0, 10); // floor 1
		
		/**
		 * Crafting Guild
		 */
		
		c.getPA().checkObjectSpawn(13642, 2928, 3288, 1, 10); // Teletab creation

		/**
		 * Thieving
		 */
		
		/*c.getPA().checkObjectSpawn(-1, 2659, 3314, 0, 10); // silver stall
		c.getPA().checkObjectSpawn(-1, 2663, 3297, 0, 10); // fur stall
		c.getPA().checkObjectSpawn(-1, 2659, 3297, 0, 10); // spice stall
		c.getPA().checkObjectSpawn(-1, 2667, 3302, 0, 10); // gem stall
		c.getPA().checkObjectSpawn(-1, 2656, 3310, 0, 10); // baker stall
		c.getPA().checkObjectSpawn(-1, 2663, 3314, 0, 10); // silk stall
		c.getPA().checkObjectSpawn(-1, 2667, 3310, 0, 10); // baker stall
		c.getPA().checkObjectSpawn(-1, 2656, 3302, 0, 10); // silk stall*/

		/*c.getPA().checkObjectSpawn(2565, 2326, 3685, 0, 10); // silver stall
		c.getPA().checkObjectSpawn(2563, 2327, 3685, 0, 10); // fur stall
		c.getPA().checkObjectSpawn(2564, 2328, 3685, 0, 10); // spice stall
		c.getPA().checkObjectSpawn(2562, 2329, 3685, 1, 10); // gem stall
		c.getPA().checkObjectSpawn(2561, 2330, 3685, 1, 10); // baker stall
		c.getPA().checkObjectSpawn(2560, 2331, 3685, 0, 10); // silk stall
		c.getPA().checkObjectSpawn(2561, 2332, 3685, 3, 10); // baker stall
		c.getPA().checkObjectSpawn(2560, 2333, 3685, 1, 10); // silk stall*/

		/**
		 * Mining
		 */

		c.getPA().checkObjectSpawn(11933, 3303, 3313, 0, 10); // tin
		c.getPA().checkObjectSpawn(11933, 3303, 3312, 0, 10); // tin
		c.getPA().checkObjectSpawn(11933, 3295, 3303, 0, 10); // tin
		c.getPA().checkObjectSpawn(11933, 3294, 3301, 0, 10); // tin
		c.getPA().checkObjectSpawn(11933, 3293, 3300, 0, 10); // tin

		c.getPA().checkObjectSpawn(31073, 3296, 3312, 0, 10); // iron
		c.getPA().checkObjectSpawn(31073, 3297, 3311, 0, 10); // iron
		c.getPA().checkObjectSpawn(31073, 3297, 3310, 0, 10); // iron
		c.getPA().checkObjectSpawn(31073, 3303, 3310, 0, 10); // iron
		c.getPA().checkObjectSpawn(31073, 3302, 3309, 0, 10); // iron
		c.getPA().checkObjectSpawn(31073, 3302, 3302, 0, 10); // iron
		c.getPA().checkObjectSpawn(31073, 3301, 3301, 0, 10); // iron
		c.getPA().checkObjectSpawn(31073, 3300, 3287, 0, 10); // iron
		c.getPA().checkObjectSpawn(31073, 3300, 3286, 0, 10); // iron

		c.getPA().checkObjectSpawn(31065, 3297, 3288, 0, 10); // gold
		c.getPA().checkObjectSpawn(31065, 3296, 3287, 0, 10); // gold

		/**
		 * Skills
		 */

		c.getPA().checkObjectSpawn(2783, 2974, 3372, 0, 10); // Anvil

		/**
		 * Dungeoneering
		 */

		c.getPA().checkObjectSpawn(3193, 2717, 4905, 3, 10); // Bank Chest Lobby
		c.getPA().checkObjectSpawn(1995, 2851, 9577, 2, 10); // Dungeoneering Chest
		c.getPA().checkObjectSpawn(11, 2758, 9200, 1, 10); // Ladder - Floor 1
		c.getPA().checkObjectSpawn(11, 2858, 9637, 1, 10); // Ladder - Floor 2
		c.getPA().checkObjectSpawn(11, 2893, 9491, 2, 10); // Ladder - Floor 3
		c.getPA().checkObjectSpawn(11, 3233, 9313, 1, 10); // Ladder - Floor 4
		c.getPA().checkObjectSpawn(-1, 2892, 9487, 1, 10);
		c.getPA().checkObjectSpawn(409, 2640, 3281, 1, 10);
		c.getPA().checkObjectSpawn(409, 2130, 4914, 2, 10);
		c.getPA().checkObjectSpawn(6552, 2644, 3286, 3, 10);
		
		/**
		 * Mining Area
		 */
		
		c.getPA().checkObjectSpawn(22819, 3017, 9741, 3, 10); // Bank Booth
		c.getPA().checkObjectSpawn(22819, 3017, 9738, 3, 10); // Bank Booth
		c.getPA().checkObjectSpawn(22819, 3017, 9739, 3, 10); // Bank Booth
		c.getPA().checkObjectSpawn(22819, 3017, 9740, 3, 10); // Bank Booth
		//Iron Ore
		c.getPA().checkObjectSpawn(31073, 3041, 9743, 3, 10); // Iron Ore
		c.getPA().checkObjectSpawn(31073, 3037, 9745, 3, 10); // Iron Ore
		c.getPA().checkObjectSpawn(31073, 3046, 9745, 3, 10); // Iron Ore
		c.getPA().checkObjectSpawn(31073, 3048, 9748, 3, 10); // Iron Ore
		c.getPA().checkObjectSpawn(31073, 3048, 9745, 3, 10); // Iron Ore
		c.getPA().checkObjectSpawn(31073, 3051, 9744, 3, 10); // Iron Ore
		c.getPA().checkObjectSpawn(31073, 3052, 9748, 3, 10); // Iron Ore
		//Coal Ore
		c.getPA().checkObjectSpawn(11932, 3035, 9734, 3, 10); // Coal Ore
		//Gold Ore
		c.getPA().checkObjectSpawn(11183, 3026, 9735, 3, 10); // Gold Ore
		c.getPA().checkObjectSpawn(11183, 3030, 9737, 3, 10); // Gold Ore
		c.getPA().checkObjectSpawn(11183, 3026, 9739, 3, 10); // Gold Ore
		c.getPA().checkObjectSpawn(11183, 3037, 9739, 3, 10); // Gold Ore
		c.getPA().checkObjectSpawn(11183, 3035, 9734, 3, 10); // Gold Ore
		//Mith Ore
		c.getPA().checkObjectSpawn(11944, 3046, 9740, 3, 10); // Mithril Ore
		c.getPA().checkObjectSpawn(11944, 3046, 9740, 3, 10); // Mithril Ore
		c.getPA().checkObjectSpawn(11944, 3049, 9744, 3, 10); // Mithril Ore
		c.getPA().checkObjectSpawn(11944, 3045, 9747, 3, 10); // Mithril Ore
		c.getPA().checkObjectSpawn(11944, 3037, 9734, 3, 10); // Mithril Ore
		c.getPA().checkObjectSpawn(11944, 3041, 9735, 3, 10); // Mithril Ore
		c.getPA().checkObjectSpawn(11944, 3041, 9738, 3, 10); // Mithril Ore
		//Adamant Ore
		c.getPA().checkObjectSpawn(11939, 3036, 9737, 3, 10); // Adamant Ore
		c.getPA().checkObjectSpawn(11939, 3037, 9741, 3, 10); // Adamant Ore
		c.getPA().checkObjectSpawn(11939, 3038, 9743, 3, 10); // Adamant Ore
		c.getPA().checkObjectSpawn(11939, 3047, 9743, 3, 10); // Adamant Ore
		c.getPA().checkObjectSpawn(11939, 3045, 9737, 3, 10); // Adamant Ore
		c.getPA().checkObjectSpawn(11939, 3045, 9740, 3, 10); // Adamant Ore
		c.getPA().checkObjectSpawn(11939, 3039, 9734, 3, 10); // Adamant Ore
		//Rune Ore
		c.getPA().checkObjectSpawn(14859, 3049, 9741, 3, 10); // Runite Ore
		c.getPA().checkObjectSpawn(14859, 3048, 9737, 3, 10); // Runite Ore
		c.getPA().checkObjectSpawn(14859, 3049, 9741, 3, 10); // Runite Ore
		c.getPA().checkObjectSpawn(14859, 3042, 9745, 3, 10); // Runite Ore
		c.getPA().checkObjectSpawn(14859, 3043, 9740, 3, 10); // Runite Ore
		c.getPA().checkObjectSpawn(14859, 3040, 9741, 3, 10); // Runite Ore
		c.getPA().checkObjectSpawn(14859, 3039, 9737, 3, 10); // Runite Ore
		c.getPA().checkObjectSpawn(14859, 3044, 9735, 3, 10); // Runite Ore
		c.getPA().checkObjectSpawn(14859, 3035, 9733, 3, 10); // Runite Ore
		c.getPA().checkObjectSpawn(14859, 3042, 9733, 3, 10); // Runite Ore
		
		/**
		 * Nex
		 */
		
		c.getPA().checkObjectSpawn(3193, 2905, 5205, 0, 10); // Bank Chest
		
		/**
		 * RFD Room
		 */
		
		c.getPA().checkObjectSpawn(4483, 3219, 9623, 1, 10);
		c.getPA().checkObjectSpawn(12356, 3219, 9620, 0, 10);

		if (c.heightLevel == 0) {

		} else {

		}
	}

	public final int IN_USE_ID = 14825;

	public boolean isObelisk(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return true;
		}
		return false;
	}

	public int[] obeliskIds = { 14829, 14830, 14827, 14828, 14826, 14831 };
	public int[][] obeliskCoords = { { 3154, 3618 }, { 3225, 3665 },
			{ 3033, 3730 }, { 3104, 3792 }, { 2978, 3864 }, { 3305, 3914 } };
	public boolean[] activated = { false, false, false, false, false, false };

	public void startObelisk(int obeliskId) {
		int index = getObeliskIndex(obeliskId);
		if (index >= 0) {
			if (!activated[index]) {
				activated[index] = true;
				addObject(new Object(14825, obeliskCoords[index][0],
						obeliskCoords[index][1], 0, -1, 10, obeliskId, 16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4,
						obeliskCoords[index][1], 0, -1, 10, obeliskId, 16));
				addObject(new Object(14825, obeliskCoords[index][0],
						obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId, 16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4,
						obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId, 16));
			}
		}
	}

	public int getObeliskIndex(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return j;
		}
		return -1;
	}

	public void teleportObelisk(int port) {
		int random = Misc.random(5);
		while (random == port) {
			random = Misc.random(5);
		}
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Player c = PlayerHandler.players[j];
				int xOffset = c.absX - obeliskCoords[port][0];
				int yOffset = c.absY - obeliskCoords[port][1];
				if (c.goodDistance(c.getX(), c.getY(),
						obeliskCoords[port][0] + 2, obeliskCoords[port][1] + 2,
						1)) {
					c.getPA().startTeleport2(
							obeliskCoords[random][0] + xOffset,
							obeliskCoords[random][1] + yOffset, 0);
				}
			}
		}
	}

	public boolean loadForPlayer(Object o, Player c) {
		if (o == null || c == null)
			return false;
		return c.distanceToPoint(o.objectX, o.objectY) <= 60
				&& c.heightLevel == o.height;
	}

	public void addObject(Object o) {
		if (getObject(o.objectX, o.objectY, o.height) == null) {
			objects.add(o);
			placeObject(o);
		}
	}

}
