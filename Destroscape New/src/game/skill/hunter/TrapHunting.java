package game.skill.hunter;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.Server;
import game.entity.npc.NPC;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.object.ObjectHandler;
import game.object.Objects;

public class TrapHunting {

	/**
	 * Trap Data
	 */

	private static final int[][] trapData = {
			{ 5117, 19651, 19675, 5262, -1, -1, 700, 10149, 21, 1939, 19652,
					19662 },
			{ 5087, 19206, 19216, 5272, -1, -1, 500, 9953, 20, 1739, 19205 },
			{ 5073, 19175, 19180, 6779, 10006, 5208, 800, 10088, 1 },
			{ 5072, 19175, 19178, 6779, 10006, 5208, 790, 10087, 1 },
			{ 5074, 19175, 19182, 6779, 10006, 5208, 900, 10089, 1 },
			{ 5079, 19187, 19192, 5184, 10008, 5208, 10000, 10033, 53, 1982 },
			{ 5080, 19187, 19192, 5184, 10008, 5208, 15000, 10034, 64, 1982 } };

	private static int getData(final int[][] from, final int data,
			final int inputData, final int returningData) {
		for (int[] aFrom : from) {
			if (aFrom[inputData] == data) {
				return aFrom[returningData];
			}
		}
		return -1;
	}

	/**
	 * Checks if can trap
	 * 
	 * @param c
	 *            Client
	 */

	public static boolean canTrap(final Player c) {
		int amt = 0;
		Objects objects[] = new Objects[6];
		for (Objects o : ObjectHandler.globalObjects) {
			if (o.owner == c.playerId) {
				boolean same = false;
				for (Objects o2 : objects) {
					if (o2 != null && Objects.objectX == Objects.objectX + 0
							&& Objects.objectY == Objects.objectY - 0) {
						same = true;
					}
				}
				if (!same)
					objects[amt++] = o;

			}
			if (Objects.objectId == c.objectId
					&& Objects.getObjectX() == c.getAbsX()
					&& Objects.getObjectY() == c.getAbsY()) {
				c.sendMessage("You cannot lay a trap on another trap.");
				return false;
			}
		}
		if (amt > maxTraps(c.getPA()
				.getLevelForXP(c.playerXP[Constants.HUNTER]))) {
			c.sendMessage("You cannot lay that many traps at your level.");
			return false;
		}
		return true;
	}

	/**
	 * Most traps
	 */

	private static int maxTraps(int levelForXP) {
		return levelForXP / 20 + 1;
	}

	/**
	 * Handles Laying the trap
	 * 
	 * @param c
	 *            Client
	 * @param id
	 *            Id of Trap
	 * @return Returns if can lay
	 */

	public static boolean layTrap(final Player c, final int id) {
		if (getData(trapData, id, 4, 1) <= -1) {
			return false;
		}
		if (!canTrap(c)) {
			return false;
		}
		//c.setAnimation(Animation.create(5208));
		c.startAnimation(5208);
		c.getItems().deleteItem(id, 1);
		final game.object.Objects o = new game.object.Objects(getData(trapData,
				id, 4, 1), c.getAbsX(), c.getAbsY(), 0, 0, 10, 0);
		o.owner = c.playerId;
		ObjectHandler.addObject(o);
		Server.objectHandler.placeObject(o);
		CycleEventHandler.getSingleton().addEvent(id, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (o.owner == -1) {
					return;
				}
				if (c.getOutStream() != null) {
					Server.itemHandler.createGroundItem(
							c,
							getData(trapData, Objects.objectId,
									Constants.OBJECTID, Constants.ITEMID),
							Objects.objectX, Objects.objectY, 1, c.getId());
				}
				game.object.Objects empty = new game.object.Objects(100,
						Objects.objectX, Objects.objectY, 0, 0, 10, 0);
				Server.objectHandler.placeObject(empty);
				ObjectHandler.removeObject(o);
				o.owner = -1;
				Objects.objectX = 0;
				Objects.objectY = 0;
				c.sendMessage("Your trap has dismantled");
				container.stop();
			}

			@Override
			public void stop() {

			}
		}, 40 * 1);
		return true;
	}

	public static void checkTrap(final NPC n, final Player c) {
		if (getData(trapData, n.npcType, 0, 1) > -1) {
			final Objects o = checkClosestObjects(n,
					getData(trapData, n.npcType, 0, 1), n.npcId);
			if ((o != null && !n.isDead && distSquare(n.getAbsX(), n.getAbsY(),
					Objects.objectX, Objects.objectY) > 0)
					&& (((int) (Math.random() * (getData(trapData, n.npcType,
							Constants.NPCID, Constants.REQ)
							* (o.bait ? 10 : 20) + 50)) < (PlayerHandler.players[o.owner] == null ? 0
								: PlayerHandler.players[o.owner].playerLevel[22])
							&& o.delay < System.currentTimeMillis() && n.delay < System
							.currentTimeMillis()) || n.npcId == o.target)) {
				if (PlayerHandler.players[o.owner] != null
						&& PlayerHandler.players[o.owner].playerLevel[22] < getData(
								trapData, n.npcType, Constants.NPCID,
								Constants.REQ)) {
					return;
				}
				if (o.target != n.npcId)
					o.target = n.npcId;
				n.moveX = NPCHandler.GetMove(n.getAbsX(), Objects.getObjectX());
				n.moveY = NPCHandler.GetMove(n.getAbsY(), Objects.getObjectY());
				n.getNextNPCMovement(n.npcId);
				o.delay = System.currentTimeMillis() + 4000;
				n.delay = System.currentTimeMillis() + 2000;
			} else if (o != null
					&& distSquare(n.getAbsX(), n.getAbsY(), Objects.objectX,
							Objects.objectY) <= 0
					&& o.oDelay < System.currentTimeMillis() && !n.isDead) {
				if (PlayerHandler.players[o.owner] != null
						&& PlayerHandler.players[o.owner].playerLevel[22] < getData(
								trapData, n.npcType, Constants.NPCID,
								Constants.REQ)) {
					return;
				}
				n.noDeathEmote = true;
				n.forceAnim(getData(trapData, n.npcType, Constants.NPCID,
						Constants.ANIM));
				o.oDelay = System.currentTimeMillis() + 2000;
				final Objects catchObject = new Objects(getData(trapData,
						n.npcType, Constants.NPCID, Constants.CATCHID),
						Objects.objectX, Objects.objectY, 0, 0, 10, 0);
				catchObject.owner = o.owner;
				if (getSize(trapData, Objects.objectId, Constants.OBJECTID) < 12) {
					ObjectHandler.addObject(catchObject);
					Server.objectHandler.placeObject(catchObject);
					ObjectHandler.removeObject(o);
					o.owner = -1;
				} else {
					Objects.objectY -= 1;
					Objects empty = new Objects(100, Objects.objectX,
							Objects.objectY, 0, 0, 10, 0);
					ObjectHandler.addObject(empty);
					Server.objectHandler.placeObject(empty);
					ObjectHandler.removeObject(empty);
					empty.owner = -1;
					o.owner = -1;
				}
				ObjectHandler.addObject(catchObject);
				Server.objectHandler.placeObject(catchObject);
				ObjectHandler.removeObject(o);
				o.owner = -1;
				Objects.objectX = 0;
				Objects.objectY = 0;
				catchObject.item = getData(trapData, n.npcType,
						Constants.NPCID, Constants.LOOT);
				catchObject.xp = getData(trapData, n.npcType, Constants.NPCID,
						Constants.XP);
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						if (catchObject.owner == -1) {
							return;
						}
						if (PlayerHandler.players[catchObject.owner] != null) {
							if (PlayerHandler.players[catchObject.owner]
									.getOutStream() != null) {
								Server.itemHandler
										.createGroundItem(
												(PlayerHandler.players[catchObject.owner]),
												getData(trapData, n.npcType,
														Constants.NPCID,
														Constants.ITEMID),
												Objects.objectX,
												Objects.objectY,
												1,
												PlayerHandler.players[catchObject.owner]
														.getId());
							}
						}
						ObjectHandler.removeObject(catchObject);
						Objects empty = new Objects(100, Objects.objectX,
								Objects.objectY, 0, 0, 10, 0);
						Server.objectHandler.placeObject(empty);
						PlayerHandler.players[catchObject.owner]
								.sendMessage("Your trap has dismantled.");
						catchObject.owner = -1;
						Objects.objectX = 0;
						Objects.objectY = 0;
						container.stop();
					}

					@Override
					public void stop() {

					}
				}, 20 * 1);
				n.animUpdateRequired = true;
				n.isDead = true;
				n.updateRequired = true;
				n.delay = System.currentTimeMillis() + 2000;
			} else if (o != null && n.npcId == o.target) {

			}
		}
	}

	private static int getSize(int[][] from, int data, int inputData) {
		for (int[] aFrom : from) {
			if (aFrom[inputData] == data) {
				return aFrom.length;
			}
		}
		return -1;
	}

	private static int distSquare(int x, int y, int tx, int ty) {
		return (int) Math.sqrt((Math.abs(x - tx) + Math.abs(y - ty)));
	}

	private static Objects checkClosestObjects(NPC n, int objectId, int npcId) {
		Objects closest = null;
		for (Objects o : ObjectHandler.globalObjects) {
			if (distSquare(n.getAbsX(), n.getAbsY(), Objects.objectX,
					Objects.objectY) < 5 && Objects.objectId == objectId) {
				if (closest == null) {
					closest = o;
				} else if (npcId == o.target) {
					return o;
				} else if (distSquare(n.getAbsX(), n.getAbsY(),
						Objects.objectX, Objects.objectY) > distSquare(
						n.getAbsX(), n.getAbsY(), Objects.objectX,
						Objects.objectY)) {
					closest = o;
				}
			}
		}
		return closest;
	}

}
