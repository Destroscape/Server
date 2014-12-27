package game.skill.dungeoneering;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.util.Misc;
import game.entity.npc.NPC;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;

public class Dungeoneering extends DungHandler {

	/**
	 * Integer Declarations
	 */

	/**
	 * Before Dungeon
	 */

	public static void kinshipTele(final Player c) {
		int teleSpot = Misc.random2(3);
		c.getPA().startTeleport(2844 - teleSpot, 4317 - teleSpot, 0, "dung");
	}

	public static void getDButtons(final Player c, final int button) {
		switch (button) {
		case 102114:
			if (c.inDung()) {
				c.sendMessage("You cannot change this while you are in a dungeon.");
				return;
			}
			c.getDH().sendOption5("Floor 1 - Very Small", "Floor 2 - Small",
					"Floor 3 - Medium", "Floor 4 - Large",
					"Floor 5 - Very Large");
			c.dialogueAction = 300;
			break;

		case 102134:
			if (c.inDung()) {
				c.sendMessage("You cannot change this while you are in a dungeon.");
				return;
			}
			c.getDH().sendOption4("Complexity 1 - Beginner",
					"Complexity 2 - Intermediate", "Complexity 3 - Advanced",
					"Complexity 4 - Extreme");
			c.dialogueAction = 301;
			break;

		case 105249:
			formPlayer(c);
			break;

		case 102117:
			c.getDH().sendStatement("Are you sure you want to do this?");
			c.nextChat = 200;
			break;

		case 102120:
			if (c.floor == 0 && c.complexity == 0) {
				return;
			}
			if (c.inDung()) {
				c.sendMessage("You cannot do this while you are in a dungeon.");
				return;
			}
			handleReset(c);
			c.getPA().sendFrame126("0", 26240);
			c.getPA().sendFrame126("0", 26241);
			break;
		}
	}

	private static void formPlayer(final Player c) {
		if (c.inLobby()) {
			c.sendMessage("You are prepared to enter the dungeons.");
			c.getPA().sendFrame126("@or2@Kills: N/A", 26257);
			c.getPA().sendFrame126("@or2@Deaths: N/A", 26255);
			c.setSidebarInterface(13, 26224);
			data(c);
			c.getPA().sendFrame126(Misc.optimizeText(c.playerName), 26235);
			c.isDungReady = true;
		} else {
			c.sendMessage("You must be in the Dungeoneering Lobby to do this.");
		}
	}

	private static void data(final Player c) {
		c.getPA().sendFrame126("00:00:0" + c.floorTimeSeconds + "", 26251);
		c.getPA().sendFrame126("0", 26240);
		c.getPA().sendFrame126("0", 26241);
	}

	public static void startDungeon(final Player c) {
		if (c.hasClickedDung) {
			return;
		}
		if (c.floor == 0) {
			c.sendMessage("You do not have a floor currently selected.");
			return;
		}
		if (c.complexity == 0) {
			c.sendMessage("You do not have a complexity selected.");
			return;
		}
		Binding.addBindItems(c);
		FloorData.startFloor1(c);
		c.dungEvent = true;
		c.bossDead = false;
		c.hasRecievedRewards = false;
		dungeonTimer(c);
	}

	/**
	 * While in Dungeon
	 */

	private static void configureTime(final Player c) {
		if (c.floorTimeHours == 0 && c.floorTimeMinutes == 0) {
			if (c.floorTimeSeconds < 10) {
				c.getPA().sendFrame126("00:00:0" + c.floorTimeSeconds + "",
						26251);
			} else {
				c.getPA().sendFrame126("00:00:" + c.floorTimeSeconds + "",
						26251);
			}
		}
		if (c.floorTimeHours == 0) {
			if (c.floorTimeMinutes < 10) {
				if (c.floorTimeSeconds < 10) {
					c.getPA().sendFrame126(
							"00:0" + c.floorTimeMinutes + ":0"
									+ c.floorTimeSeconds + "", 26251);
				} else {
					c.getPA().sendFrame126(
							"00:0" + c.floorTimeMinutes + ":"
									+ c.floorTimeSeconds + "", 26251);
				}
			} else {
				if (c.floorTimeSeconds < 10) {
					c.getPA().sendFrame126(
							"00:" + c.floorTimeMinutes + ":0"
									+ c.floorTimeSeconds + "", 26251);
				} else {
					c.getPA().sendFrame126(
							"00:" + c.floorTimeMinutes + ":"
									+ c.floorTimeSeconds + "", 26251);
				}
			}
		}
		if (c.floorTimeHours < 10) {
			if (c.floorTimeMinutes < 10) {
				if (c.floorTimeSeconds < 10) {
					c.getPA().sendFrame126(
							"0" + c.floorTimeHours + ":0" + c.floorTimeMinutes
							+ ":0" + c.floorTimeSeconds + "", 26251);
				} else {
					c.getPA().sendFrame126(
							"0" + c.floorTimeHours + ":0" + c.floorTimeMinutes
							+ ":" + c.floorTimeSeconds + "", 26251);
				}
			} else {
				if (c.floorTimeSeconds < 10) {
					c.getPA().sendFrame126(
							"0" + c.floorTimeHours + ":" + c.floorTimeMinutes
							+ ":0" + c.floorTimeSeconds + "", 26251);
				} else {
					c.getPA().sendFrame126(
							"0" + c.floorTimeHours + ":" + c.floorTimeMinutes
							+ ":" + c.floorTimeSeconds + "", 26251);
				}
			}
		} else {
			if (c.floorTimeMinutes < 10) {
				if (c.floorTimeSeconds < 10) {
					c.getPA().sendFrame126(
							"" + c.floorTimeHours + ":0" + c.floorTimeMinutes
							+ ":0" + c.floorTimeSeconds + "", 26251);
				} else {
					c.getPA().sendFrame126(
							"" + c.floorTimeHours + ":0" + c.floorTimeMinutes
							+ ":" + c.floorTimeSeconds + "", 26251);
				}
			} else {
				if (c.floorTimeSeconds < 10) {
					c.getPA().sendFrame126(
							"" + c.floorTimeHours + ":" + c.floorTimeMinutes
							+ ":0" + c.floorTimeSeconds + "", 26251);
				} else {
					c.getPA().sendFrame126(
							"" + c.floorTimeHours + ":" + c.floorTimeMinutes
							+ ":" + c.floorTimeSeconds + "", 26251);
				}
			}
		}
	}

	public static void dungeonTimer(final Player c) {
		if (!c.inDung()) {
			return;
		}
		if (!c.dungEvent) {
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.dungEvent == false) {
					container.stop();
				}
				if (!c.inDung()) {
					container.stop();
				}
				if (c.dungEvent == true) {
					c.floorTimeSeconds++;
					if (c.floorTimeSeconds == 60) {
						c.floorTimeSeconds = 0;
						c.floorTimeMinutes++;
					}
					if (c.floorTimeMinutes == 60) {
						c.floorTimeMinutes = 0;
						c.floorTimeHours++;
					}
					configureTime(c);
				}
			}

			@Override
			public void stop() {
				resetTimes(c);
				c.dungEvent = false;
				c.hasClickedDung = false;
				c.hasRecievedRewards = false;
				c.bossDead = false;
				c.hasOpened = false;
			}
		}, 1);

	}

	public static void deathRespawn(final Player c) {
		switch (c.floor) {
		case 1:
			c.getPA().movePlayer(F1SX, F1SY, c.playerId * 4);
			break;
		case 2:
			c.getPA().movePlayer(F2SX, F2SY, c.playerId * 4);
			break;
		case 3:
			c.getPA().movePlayer(F3SX, F3SY, c.playerId * 4);
			break;
		case 4:
			c.getPA().movePlayer(F4SX, F4SY, c.playerId * 4);
			break;
		}
	}

	/**
	 * After Dungeon / Exit Dungeon
	 **/

	private static void handleReset(final Player c) {
		c.floor = 0;
		c.complexity = 0;
		c.dungDeaths = 0;
		c.dungKills = 0;
		c.getPA().sendFrame126("@or2@Kills: N/A", 26257);
		c.getPA().sendFrame126("@or2@Deaths: N/A", 26255);
	}

	public static void leaveParty(final Player c) {
		c.dungEvent = false;
		c.isDungReady = false;
		c.hasSpawnedBoss = false;
		c.setSidebarInterface(13, 27124);
		handleReset(c);
		resetTimes(c);
		c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
		c.getPA().refreshSkill(5);
	}

	private static void resetTimes(final Player c) {
		c.floorTimeSeconds = 0;
		c.floorTimeMinutes = 0;
		c.floorTimeHours = 0;
		c.getPA().sendFrame126(
				"0" + c.floorTimeHours + ":0" + c.floorTimeMinutes + ":0"
						+ c.floorTimeSeconds + "", 26251);
	}

	public static void abandonDung(final Player c) {
		if (!c.inDung()) {
			return;
		}
		for (NPC n : NPCHandler.npcs) {
			if (c.inDung()) {
				if (c.floor == 1
						&& n != null
						&& n.goodDistance(n.getX(), n.getY(), c.getX(),
								c.getY(), 70)) {
					if (n.heightLevel == c.playerId * 4) {
						n.applyDead = true;
						n.isDead = true;
						n.updateRequired = true;
					}
				} else if (c.floor == 2
						&& n != null
						&& n.goodDistance(n.getX(), n.getY(), c.getX(),
								c.getY(), 150)) {
					if (n.heightLevel == c.playerId * 4) {
						n.applyDead = true;
						n.isDead = true;
						n.updateRequired = true;
					}
				} else if (c.floor == 3
						&& n != null
						&& n.goodDistance(n.getX(), n.getY(), c.getX(),
								c.getY(), 300)) {
					if (n.heightLevel == c.playerId * 4) {
						n.applyDead = true;
						n.isDead = true;
						n.updateRequired = true;
					}
				} else if (c.floor == 4
						&& n != null
						&& n.goodDistance(n.getX(), n.getY(), c.getX(),
								c.getY(), 600)) {
					if (n.heightLevel == c.playerId * 4) {
						n.applyDead = true;
						n.isDead = true;
						n.updateRequired = true;
					}
				} else if (c.floor == 5
						&& n != null
						&& n.goodDistance(n.getX(), n.getY(), c.getX(),
								c.getY(), 1000)) {
					if (n.heightLevel == c.playerId * 4) {
						n.applyDead = true;
						n.isDead = true;
						n.updateRequired = true;
					}
				}
			}
		}
		c.getPA().removeAllItems();
		c.getPA().removeEquipment();
		leaveParty(c);
		for (int i = 0; i < 23; i++) {
			c.playerLevel[i] = c.getPA().getLevelForXP(c.playerXP[i]);
			c.getPA().refreshSkill(i);
		}
		c.playerLevel[23] = c.getPA().getLevelForDungXP(c.playerXP[23]);
		c.getPA().sendFrame126("", 26235);
		c.getPA().movePlayer(2844, 4320, 0);
		c.getPA().removeAllWindows();
		c.hasRecievedRewards = false;
		c.bossDead = false;
		c.dungClick1 = false;
		c.dungClick2 = false;
		c.dungClick3 = false;
		c.dungClick4 = false;
		c.dungClick5 = false;
	}

	protected static void createItems(final Player c) { // Complexity 1
		c.getItems().addItem(noviteItem(), 1);
		c.getItems().addItem(noviteItem(), 1);
		c.getItems().addItem(noviteItem(), 1);
		c.getItems().addItem(noviteItem(), 1);
		c.getItems().addItem(bathusItem(), 1);
		c.getItems().addItem(bathusItem(), 1);
		c.getItems().addItem(bathusItem(), 1);
		c.getItems().addItem(bathusItem(), 1);
		c.getItems().addItem(chooseDFF(), Misc.random(1));
		c.getItems().addItem(chooseDFF(), Misc.random(1));
	}

	protected static void createItems2(final Player c) { // Complexity 2
		c.getItems().addItem(marmarosItem(), 1);
		c.getItems().addItem(marmarosItem(), 1);
		c.getItems().addItem(marmarosItem(), 1);
		c.getItems().addItem(marmarosItem(), 1);
		c.getItems().addItem(kratoniteItem(), 1);
		c.getItems().addItem(kratoniteItem(), 1);
		c.getItems().addItem(kratoniteItem(), 1);
		c.getItems().addItem(kratoniteItem(), 1);
		c.getItems().addItem(chooseDFF(), Misc.random(2));
		c.getItems().addItem(chooseDFF(), Misc.random(2));
	}

	protected static void createItems3(final Player c) { // Complexity 3
		c.getItems().addItem(fractiteItem(), 1);
		c.getItems().addItem(fractiteItem(), 1);
		c.getItems().addItem(fractiteItem(), 1);
		c.getItems().addItem(fractiteItem(), 1);
		c.getItems().addItem(zephyriumItem(), 1);
		c.getItems().addItem(zephyriumItem(), 1);
		c.getItems().addItem(zephyriumItem(), 1);
		c.getItems().addItem(zephyriumItem(), 1);
		c.getItems().addItem(chooseDFF(), Misc.random(5) + 1);
	}

	protected static void createItems4(final Player c) { // Complexity 4
		c.getItems().addItem(argoniteItem(), 1);
		c.getItems().addItem(argoniteItem(), 1);
		c.getItems().addItem(argoniteItem(), 1);
		c.getItems().addItem(argoniteItem(), 1);
		c.getItems().addItem(katagonItem(), 1);
		c.getItems().addItem(katagonItem(), 1);
		c.getItems().addItem(katagonItem(), 1);
		c.getItems().addItem(katagonItem(), 1);
		c.getItems().addItem(chooseDFF(), Misc.random(4));
		c.getItems().addItem(chooseDFF(), Misc.random(4) + 1);
	}

}