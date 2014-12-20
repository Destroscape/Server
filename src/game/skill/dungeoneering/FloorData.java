package game.skill.dungeoneering;

import engine.util.Misc;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;

public class FloorData {

	/**
	 * @author Ochroid | Scott
	 */

	protected static void startFloor1(final Player c) {
		if (c.isResting) {
			return;
		}
		c.dungDeaths = 0;
		c.dungKills = 0;
		c.getPA().sendFrame126("@or2@Kills: " + c.dungKills + "", 26257);
		c.getPA().sendFrame126("@or2@Deaths: " + c.dungDeaths + "", 26255);
		c.sendMessage("Starting Floor " + c.floor + " - Complexity "
				+ c.complexity + ", Goodluck!");
		if (c.floor == 1) {
			if (c.complexity == 1) {
				complexity1Floor1(c);
			} else if (c.complexity == 2) {
				complexity2Floor1(c);
			} else if (c.complexity == 3) {
				complexity3Floor1(c);
			} else if (c.complexity == 4) {
				complexity4Floor1(c);
			}
			c.getPA().movePlayer(DungHandler.F1SX, DungHandler.F1SY,
					c.playerId * 4);
			c.absX = DungHandler.F1SX;
			c.absY = DungHandler.F1SY;
		} else if (c.floor == 2) {
			if (c.complexity == 1) {
				complexity1Floor2(c);
			} else if (c.complexity == 2) {
				complexity2Floor2(c);
			} else if (c.complexity == 3) {
				complexity3Floor2(c);
			} else if (c.complexity == 4) {
				complexity4Floor2(c);
			}
			c.getPA().movePlayer(DungHandler.F2SX, DungHandler.F2SY,
					c.playerId * 4);
			c.absX = DungHandler.F2SX;
			c.absY = DungHandler.F2SY;
		} else if (c.floor == 3) {
			if (c.complexity == 1) {
				complexity1Floor3(c);
			} else if (c.complexity == 2) {
				complexity2Floor3(c);
			} else if (c.complexity == 3) {
				complexity3Floor3(c);
			} else if (c.complexity == 4) {
				complexity4Floor3(c);
			}
			c.getPA().movePlayer(DungHandler.F3SX, DungHandler.F3SY,
					c.playerId * 4);
			c.absX = DungHandler.F3SX;
			c.absY = DungHandler.F3SY;
		} else if (c.floor == 4) {
			if (c.complexity == 1) {
				complexity1Floor4(c);
			} else if (c.complexity == 2) {
				complexity2Floor4(c);
			} else if (c.complexity == 3) {
				complexity3Floor4(c);
			} else if (c.complexity == 4) {
				complexity4Floor4(c);
			}
			c.sendMessage("Make sure you really search the floor, to find the creatures.");
			c.getPA().movePlayer(DungHandler.F4SX, DungHandler.F4SY,
					c.playerId * 4);
			c.absX = DungHandler.F4SX;
			c.absY = DungHandler.F4SY;
		} else if (c.floor == 5) {
			if (c.complexity == 1) {
				complexity1Floor5(c);
			} else if (c.complexity == 2) {
				complexity2Floor5(c);
			} else if (c.complexity == 3) {
				complexity3Floor5(c);
			} else if (c.complexity == 4) {
				complexity4Floor5(c);
			}
			c.getPA().movePlayer(DungHandler.F5SX, DungHandler.F5SY,
					c.playerId * 4);
			c.absX = DungHandler.F5SX;
			c.absY = DungHandler.F5SY;
		}
		c.lastSpear1 = System.currentTimeMillis();
	}

	/**
	 * Floor 1 - Configured NPC Spawns
	 * 
	 * @param c
	 *            Client c
	 */

	protected static void complexity1Floor1(final Player c) {
		Dungeoneering.createItems(c);
		NPCHandler.spawnNpc2(10736, 2799, 9203, c.playerId * 4, 0, 30, 2, 40,
				25);
		NPCHandler.spawnNpc2(10737, 2800, 9197, c.playerId * 4, 0, 30, 2, 40,
				25);
		NPCHandler.spawnNpc2(10738, 2791, 9194, c.playerId * 4, 0, 30, 2, 40,
				25);
		NPCHandler.spawnNpc2(10707, 2786, 9203, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10480, 2786, 9190, c.playerId * 4, 0, 10, 2, 15,
				20);
	}

	protected static void complexity2Floor1(final Player c) {
		Dungeoneering.createItems2(c);
		NPCHandler.spawnNpc2(10837, 2799, 9203, c.playerId * 4, 0, 50, 3, 50,
				50);
		NPCHandler.spawnNpc2(10738, 2800, 9197, c.playerId * 4, 0, 30, 2, 40,
				25);
		NPCHandler.spawnNpc2(10838, 2791, 9194, c.playerId * 4, 0, 60, 5, 65,
				60);
		NPCHandler.spawnNpc2(10802, 2786, 9203, c.playerId * 4, 0, 75, 6, 80,
				80);
		NPCHandler.spawnNpc2(10801, 2786, 9190, c.playerId * 4, 0, 65, 4, 70,
				70);
	}

	protected static void complexity3Floor1(final Player c) {
		Dungeoneering.createItems3(c);
		NPCHandler.spawnNpc2(10167, 2799, 9203, c.playerId * 4, 0, 90, 9, 150,
				115);
		NPCHandler.spawnNpc2(10802, 2800, 9197, c.playerId * 4, 0, 75, 6, 115,
				80);
		NPCHandler.spawnNpc2(10462, 2791, 9194, c.playerId * 4, 0, 85, 11, 95,
				90);
		NPCHandler.spawnNpc2(10756, 2786, 9203, c.playerId * 4, 0, 150, 16,
				250, 145);
		NPCHandler.spawnNpc2(10699, 2786, 9190, c.playerId * 4, 0, 100, 8, 119,
				100);
	}

	protected static void complexity4Floor1(final Player c) {
		Dungeoneering.createItems4(c);
		NPCHandler.spawnNpc2(10756, 2799, 9203, c.playerId * 4, 0, 150, 16,
				250, 145);
		NPCHandler.spawnNpc2(10699, 2800, 9197, c.playerId * 4, 0, 100, 8, 125,
				70);
		NPCHandler.spawnNpc2(10465, 2791, 9194, c.playerId * 4, 0, 200, 20,
				275, 200);
		NPCHandler.spawnNpc2(10218, 2786, 9203, c.playerId * 4, 0, 175, 10,
				250, 300);
		NPCHandler.spawnNpc2(10698, 2786, 9190, c.playerId * 4, 0, 50, 7, 125,
				500);
	}

	/**
	 * Floor 2 - Configured NPC Spawns
	 * 
	 * @param c
	 */

	protected static void complexity1Floor2(final Player c) {
		Dungeoneering.createItems(c);
		NPCHandler.spawnNpc2(10707, 2861, 9576, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10801, 2855, 9574, c.playerId * 4, 0, 40, 3, 57,
				47);
	}

	protected static void complexity2Floor2(final Player c) {
		Dungeoneering.createItems2(c);
		NPCHandler.spawnNpc2(10838, 2861, 9576, c.playerId * 4, 0, 80, 3, 60,
				75);
		NPCHandler.spawnNpc2(10262, 2855, 9574, c.playerId * 4, 0, 85, 4, 50,
				55);
	}

	protected static void complexity3Floor2(final Player c) {
		Dungeoneering.createItems3(c);
		NPCHandler.spawnNpc2(10462, 2861, 9576, c.playerId * 4, 0, 125, 5, 158,
				113);
		NPCHandler.spawnNpc2(10707, 2855, 9574, c.playerId * 4, 0, 130, 5, 175,
				116);
	}

	protected static void complexity4Floor2(final Player c) {
		Dungeoneering.createItems4(c);
		NPCHandler.spawnNpc2(10802, 2861, 9576, c.playerId * 4, 0, 160, 7, 265,
				150);
		NPCHandler.spawnNpc2(10756, 2855, 9574, c.playerId * 4, 0, 145, 5, 230,
				157);
	}

	/**
	 * Floor 3 - Configured NPC Spawns
	 * 
	 * @param c
	 */

	protected static void complexity1Floor3(final Player c) {
		Dungeoneering.createItems(c);
		NPCHandler.spawnNpc2(10736, 2916, 9529, c.playerId * 4, 0, 30, 1, 50,
				25);
		NPCHandler.spawnNpc2(10837, 2901, 9522, c.playerId * 4, 0, 40, 3, 50,
				25);
		NPCHandler.spawnNpc2(10262, 2898, 9516, c.playerId * 4, 0, 25, 2, 50,
				25);
		NPCHandler.spawnNpc2(10167, 2908, 9506, c.playerId * 4, 0, 35, 3, 45,
				25);
		NPCHandler.spawnNpc2(10707, 2915, 9503, c.playerId * 4, 0, 46, 1, 44,
				25);
		NPCHandler.spawnNpc2(10419, 2937, 9504, c.playerId * 4, 0, 34, 4, 47,
				25);
		NPCHandler.spawnNpc2(10480, 2930, 9479, c.playerId * 4, 0, 41, 2, 48,
				25);
		NPCHandler.spawnNpc2(10738, 2916, 9491, c.playerId * 4, 0, 25, 3, 42,
				25);
		NPCHandler.spawnNpc2(10414, 2922, 9507, c.playerId * 4, 0, 27, 3, 44,
				25);
		NPCHandler.spawnNpc2(10418, 2916, 9477, c.playerId * 4, 0, 32, 2, 46,
				25);
		NPCHandler.spawnNpc2(10801, 2910, 9475, c.playerId * 4, 0, 35, 1, 48,
				25);
		NPCHandler.spawnNpc2(10419, 2938, 9492, c.playerId * 4, 0, 37, 2, 44,
				25);
		NPCHandler.spawnNpc2(10415, 2906, 9483, c.playerId * 4, 0, 40, 4, 46,
				25);
		NPCHandler.spawnNpc2(10423, 2931, 9508, c.playerId * 4, 0, 29, 2, 42,
				25);
		NPCHandler.spawnNpc2(10422, 2907, 9526, c.playerId * 4, 0, 33, 3, 41,
				25);
	}

	protected static void complexity2Floor3(final Player c) {
		Dungeoneering.createItems2(c);
		NPCHandler.spawnNpc2(10738, 2916, 9529, c.playerId * 4, 0, 80, 4, 90,
				75);
		NPCHandler.spawnNpc2(10837, 2901, 9522, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10445, 2898, 9516, c.playerId * 4, 0, 85, 6, 90,
				75);
		NPCHandler.spawnNpc2(10442, 2908, 9506, c.playerId * 4, 0, 85, 5, 95,
				75);
		NPCHandler.spawnNpc2(10443, 2915, 9503, c.playerId * 4, 0, 86, 4, 94,
				75);
		NPCHandler.spawnNpc2(10419, 2937, 9504, c.playerId * 4, 0, 84, 5, 97,
				75);
		NPCHandler.spawnNpc2(10448, 2930, 9479, c.playerId * 4, 0, 81, 6, 98,
				75);
		NPCHandler.spawnNpc2(10450, 2916, 9491, c.playerId * 4, 0, 85, 5, 92,
				75);
		NPCHandler.spawnNpc2(10442, 2922, 9507, c.playerId * 4, 0, 87, 4, 94,
				75);
		NPCHandler.spawnNpc2(10837, 2916, 9477, c.playerId * 4, 0, 82, 5, 96,
				75);
		NPCHandler.spawnNpc2(10801, 2910, 9475, c.playerId * 4, 0, 85, 6, 98,
				75);
		NPCHandler.spawnNpc2(10419, 2938, 9492, c.playerId * 4, 0, 87, 5, 94,
				75);
		NPCHandler.spawnNpc2(10415, 2906, 9483, c.playerId * 4, 0, 80, 4, 96,
				75);
		NPCHandler.spawnNpc2(10707, 2931, 9508, c.playerId * 4, 0, 89, 5, 92,
				75);
		NPCHandler.spawnNpc2(10448, 2907, 9526, c.playerId * 4, 0, 83, 6, 91,
				75);
	}

	protected static void complexity3Floor3(final Player c) {
		Dungeoneering.createItems3(c);
		NPCHandler.spawnNpc2(10462, 2916, 9529, c.playerId * 4, 0, 120 - 15, 1,
				150, 125);
		NPCHandler.spawnNpc2(10707, 2901, 9522, c.playerId * 4, 0, 120 - 15, 3,
				150, 125);
		NPCHandler.spawnNpc2(10699, 2898, 9516, c.playerId * 4, 0, 125 - 15, 2,
				150, 125);
		NPCHandler.spawnNpc2(10465, 2915, 9503, c.playerId * 4, 0, 126 - 15, 1,
				144, 125);
		NPCHandler.spawnNpc2(10699, 2937, 9504, c.playerId * 4, 0, 124 - 15, 4,
				147, 125);
		NPCHandler.spawnNpc2(10738, 2930, 9479, c.playerId * 4, 0, 121 - 15, 2,
				148, 125);
		NPCHandler.spawnNpc2(10450, 2916, 9491, c.playerId * 4, 0, 125 - 15, 3,
				142, 125);
		NPCHandler.spawnNpc2(10465, 2922, 9507, c.playerId * 4, 0, 127 - 15, 3,
				144, 125);
		NPCHandler.spawnNpc2(10837, 2916, 9477, c.playerId * 4, 0, 122 - 15, 2,
				146, 125);
		NPCHandler.spawnNpc2(10418, 2910, 9475, c.playerId * 4, 0, 135 - 15, 1,
				148, 125);
		NPCHandler.spawnNpc2(10802, 2938, 9492, c.playerId * 4, 0, 137 - 15, 2,
				144, 125);
		NPCHandler.spawnNpc2(10738, 2906, 9483, c.playerId * 4, 0, 140 - 15, 4,
				146, 125);
		NPCHandler.spawnNpc2(10707, 2931, 9508, c.playerId * 4, 0, 129 - 15, 2,
				142, 125);
		NPCHandler.spawnNpc2(10218, 2907, 9526, c.playerId * 4, 0, 133 - 15, 3,
				141, 125);
	}

	// 10431, 10532, 10531, 10534, 10520, 10522
	protected static void complexity4Floor3(final Player c) {
		Dungeoneering.createItems4(c);
		NPCHandler.spawnNpc2(10431, 2916, 9529, c.playerId * 4, 0, 30 + 125, 1,
				50 + 125, 25 + 125);
		NPCHandler.spawnNpc2(10532, 2901, 9522, c.playerId * 4, 0, 40 + 125, 3,
				50 + 125, 25 + 125);
		NPCHandler.spawnNpc2(10445, 2898, 9516, c.playerId * 4, 0, 25 + 125, 2,
				50 + 125, 25 + 125);
		NPCHandler.spawnNpc2(10531, 2908, 9506, c.playerId * 4, 0, 35 + 125, 3,
				45 + 125, 25 + 125);
		NPCHandler.spawnNpc2(10534, 2915, 9503, c.playerId * 4, 0, 46 + 125, 1,
				44 + 125, 25 + 125);
		NPCHandler.spawnNpc2(10520, 2937, 9504, c.playerId * 4, 0, 34 + 125, 4,
				47 + 125, 25 + 125);
		NPCHandler.spawnNpc2(10522, 2930, 9479, c.playerId * 4, 0, 41 + 125, 2,
				48 + 125, 25 + 125);
		NPCHandler.spawnNpc2(10837, 2916, 9491, c.playerId * 4, 0, 25 + 125, 3,
				42 + 125, 25 + 125);
		NPCHandler.spawnNpc2(10534, 2922, 9507, c.playerId * 4, 0, 27 + 125, 3,
				44 + 125, 25 + 125);
		NPCHandler.spawnNpc2(10532, 2916, 9477, c.playerId * 4, 0, 32 + 125, 2,
				46 + 125, 25 + 125);
		NPCHandler.spawnNpc2(10522, 2910, 9475, c.playerId * 4, 0, 35 + 125, 1,
				48 + 125, 25 + 125);
		NPCHandler.spawnNpc2(10419, 2938, 9492, c.playerId * 4, 0, 37 + 125, 2,
				44 + 125, 25 + 125);
		NPCHandler.spawnNpc2(10415, 2906, 9483, c.playerId * 4, 0, 40 + 125, 4,
				46 + 125, 25 + 125);
		NPCHandler.spawnNpc2(10802, 2931, 9508, c.playerId * 4, 0, 29 + 125, 2,
				42 + 125, 25 + 125);
		NPCHandler.spawnNpc2(10448, 2907, 9526, c.playerId * 4, 0, 33 + 125, 3,
				41 + 125, 25 + 125);
	}

	/**
	 * Floor 4 - Configured NPC Spawns
	 * 
	 * @param c
	 */

	protected static void complexity1Floor4(final Player c) {
		Dungeoneering.createItems(c);
		NPCHandler.spawnNpc2(10707, 2614, 9514, c.playerId * 4, 0, 60, 2, 45,
				30);
		NPCHandler.spawnNpc2(10480, 2614, 9506, c.playerId * 4, 0, 61, 2, 48,
				25);
		NPCHandler.spawnNpc2(10738, 2618, 9485, c.playerId * 4, 0, 45, 3, 42,
				25);
		NPCHandler.spawnNpc2(10414, 2611, 9483, c.playerId * 4, 0, 47, 3, 44,
				25);
		NPCHandler.spawnNpc2(10418, 2614, 9487, c.playerId * 4, 0, 72, 2, 46,
				25);
		NPCHandler.spawnNpc2(10801, 2599, 9487, c.playerId * 4, 0, 35, 1, 48,
				25);
		NPCHandler.spawnNpc2(10419, 2594, 9497, c.playerId * 4, 0, 37, 2, 44,
				25);
		NPCHandler.spawnNpc2(10415, 2586, 9499, c.playerId * 4, 0, 40, 4, 46,
				25);
		NPCHandler.spawnNpc2(10738, 2579, 9501, c.playerId * 4, 0, 35, 3, 42,
				25);
		NPCHandler.spawnNpc2(10780, 2579, 9506, c.playerId * 4, 0, 90, 3, 42,
				25);
	}

	protected static void complexity2Floor4(final Player c) {
		Dungeoneering.createItems2(c);
		NPCHandler.spawnNpc2(10707, 2614, 9514, c.playerId * 4, 0, 80, 2, 45,
				70);
		NPCHandler.spawnNpc2(10480, 2614, 9506, c.playerId * 4, 0, 81, 2, 48,
				75);
		NPCHandler.spawnNpc2(10738, 2618, 9485, c.playerId * 4, 0, 85, 3, 42,
				75);
		NPCHandler.spawnNpc2(10414, 2611, 9483, c.playerId * 4, 0, 87, 3, 44,
				75);
		NPCHandler.spawnNpc2(10418, 2614, 9487, c.playerId * 4, 0, 72, 2, 46,
				75);
		NPCHandler.spawnNpc2(10801, 2599, 9487, c.playerId * 4, 0, 85, 1, 48,
				75);
		NPCHandler.spawnNpc2(10419, 2594, 9497, c.playerId * 4, 0, 87, 2, 64,
				75);
		NPCHandler.spawnNpc2(10415, 2586, 9499, c.playerId * 4, 0, 80, 4, 66,
				75);
		NPCHandler.spawnNpc2(10738, 2579, 9501, c.playerId * 4, 0, 85, 3, 62,
				75);
		NPCHandler.spawnNpc2(10780, 2579, 9506, c.playerId * 4, 0, 100, 3, 90,
				75);
	}

	protected static void complexity3Floor4(final Player c) {
		Dungeoneering.createItems3(c);
		NPCHandler.spawnNpc2(10707, 2614, 9514, c.playerId * 4, 0, 130, 2, 45,
				70);
		NPCHandler.spawnNpc2(10480, 2614, 9506, c.playerId * 4, 0, 111, 2, 48,
				75);
		NPCHandler.spawnNpc2(10738, 2618, 9485, c.playerId * 4, 0, 135, 3, 42,
				75);
		NPCHandler.spawnNpc2(10414, 2611, 9483, c.playerId * 4, 0, 137, 3, 44,
				75);
		NPCHandler.spawnNpc2(10418, 2614, 9487, c.playerId * 4, 0, 142, 2, 46,
				75);
		NPCHandler.spawnNpc2(10801, 2599, 9487, c.playerId * 4, 0, 135, 1, 48,
				75);
		NPCHandler.spawnNpc2(10419, 2594, 9497, c.playerId * 4, 0, 147, 2, 64,
				75);
		NPCHandler.spawnNpc2(10415, 2586, 9499, c.playerId * 4, 0, 140, 4, 66,
				75);
		NPCHandler.spawnNpc2(10738, 2579, 9501, c.playerId * 4, 0, 135, 3, 62,
				75);
		NPCHandler.spawnNpc2(10780, 2579, 9506, c.playerId * 4, 0, 150, 3, 120,
				75);
	}

	protected static void complexity4Floor4(final Player c) {
		Dungeoneering.createItems4(c);
		NPCHandler.spawnNpc2(10707, 2614, 9514, c.playerId * 4, 0, 150, 2, 45,
				70);
		NPCHandler.spawnNpc2(10480, 2614, 9506, c.playerId * 4, 0, 131, 2, 48,
				75);
		NPCHandler.spawnNpc2(10738, 2618, 9485, c.playerId * 4, 0, 145, 3, 42,
				75);
		NPCHandler.spawnNpc2(10414, 2611, 9483, c.playerId * 4, 0, 157, 3, 44,
				75);
		NPCHandler.spawnNpc2(10418, 2614, 9487, c.playerId * 4, 0, 152, 2, 46,
				75);
		NPCHandler.spawnNpc2(10801, 2599, 9487, c.playerId * 4, 0, 155, 1, 48,
				75);
		NPCHandler.spawnNpc2(10419, 2594, 9497, c.playerId * 4, 0, 127, 2, 64,
				75);
		NPCHandler.spawnNpc2(10415, 2586, 9499, c.playerId * 4, 0, 160, 4, 66,
				75);
		NPCHandler.spawnNpc2(10738, 2579, 9501, c.playerId * 4, 0, 125, 3, 62,
				75);
		NPCHandler.spawnNpc2(10780, 2579, 9506, c.playerId * 4, 0, 180, 3, 90,
				75);
	}

	/**
	 * Floor 5 - Configured NPC Spawns
	 * 
	 * @param c
	 */

	protected static void complexity1Floor5(final Player c) {
		Dungeoneering.createItems(c);
		NPCHandler.spawnNpc2(10707, 2374, 9677, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10736, 2370, 9683, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10480, 2372, 9689, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10802, 2399, 9712, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10480, 2399, 9701, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10450, 2409, 9706, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10445, 2423, 9709, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10419, 2425, 9718, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10167, 2410, 9722, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10415, 2401, 9722, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10738, 2379, 9719, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10802, 2407, 9725, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10431, 2387, 9701, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10480, 2424, 9722, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10418, 2417, 9723, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10422, 2409, 9712, c.playerId * 4, 0, 40, 2, 45,
				30);
	}

	protected static void complexity2Floor5(final Player c) {
		Dungeoneering.createItems2(c);
		NPCHandler.spawnNpc2(10837, 2374, 9677, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10462, 2370, 9683, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10415, 2372, 9689, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10465, 2379, 9697, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10448, 2399, 9712, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10431, 2399, 9701, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10445, 2423, 9709, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10419, 2425, 9718, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10167, 2410, 9722, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10415, 2401, 9722, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10480, 2379, 9719, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10802, 2407, 9725, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10431, 2387, 9701, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10480, 2424, 9722, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10418, 2417, 9723, c.playerId * 4, 0, 80, 5, 90,
				75);
		NPCHandler.spawnNpc2(10422, 2409, 9712, c.playerId * 4, 0, 80, 5, 90,
				75);
	}

	protected static void complexity3Floor5(final Player c) {
		Dungeoneering.createItems3(c);
		NPCHandler.spawnNpc2(10707, 2374, 9677, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10736, 2370, 9683, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10480, 2372, 9689, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10802, 2399, 9712, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10480, 2399, 9701, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10450, 2409, 9706, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10445, 2423, 9709, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10419, 2425, 9718, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10167, 2410, 9722, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10415, 2401, 9722, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10738, 2379, 9719, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10802, 2407, 9725, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10431, 2387, 9701, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10480, 2424, 9722, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10418, 2417, 9723, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10422, 2409, 9712, c.playerId * 4, 0, 40, 2, 45,
				30);
	}

	protected static void complexity4Floor5(final Player c) {
		Dungeoneering.createItems4(c);
		NPCHandler.spawnNpc2(10707, 2374, 9677, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10736, 2370, 9683, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10480, 2372, 9689, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10802, 2399, 9712, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10480, 2399, 9701, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10450, 2409, 9706, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10445, 2423, 9709, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10419, 2425, 9718, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10167, 2410, 9722, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10415, 2401, 9722, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10738, 2379, 9719, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10802, 2407, 9725, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10431, 2387, 9701, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10480, 2424, 9722, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10418, 2417, 9723, c.playerId * 4, 0, 40, 2, 45,
				30);
		NPCHandler.spawnNpc2(10422, 2409, 9712, c.playerId * 4, 0, 40, 2, 45,
				30);
	}

	/**
	 * Handles other spawns.
	 * 
	 * @param c
	 */

	public static void spawnMoreNPC(final Player c) {
		if (c.complexity == 1) {
			NPCHandler.spawnBoss(c, 10736, 2847, 9582, c.playerId * 4, 0, 30,
					2, 40, 25, true);
			NPCHandler.spawnNpc2(10167, 2833, 9564, c.playerId * 4, 0, 40, 1,
					40, 40);
			NPCHandler.spawnNpc2(10699, 2837, 9583, c.playerId * 4, 0, 20, 3,
					55, 54);
			NPCHandler.spawnNpc2(10212, 2839, 9557, c.playerId * 4, 0, 50, 3,
					55, 54);
			NPCHandler.spawnNpc2(10308, 2836, 9575, c.playerId * 4, 0, 30, 2,
					30, 30);
		} else if (c.complexity == 2) {
			NPCHandler.spawnBoss(c, 10308, 2847, 9582, c.playerId * 4, 0, 75,
					3, 70, 80, true);
			NPCHandler.spawnNpc2(10167, 2833, 9564, c.playerId * 4, 0, 40, 1,
					40, 40);
			NPCHandler.spawnNpc2(10801, 2833, 9564, c.playerId * 4, 0, 90, 4,
					40, 40);
			NPCHandler.spawnNpc2(10837, 2837, 9583, c.playerId * 4, 0, 80, 2,
					55, 54);
			NPCHandler.spawnNpc2(10212, 2839, 9557, c.playerId * 4, 0, 85, 4,
					55, 54);
			NPCHandler.spawnNpc2(10698, 2836, 9575, c.playerId * 4, 0, 75, 3,
					30, 30);
		} else if (c.complexity == 3) {
			NPCHandler.spawnBoss(c, 10736, 2847, 9582, c.playerId * 4, 0, 112,
					5, 134, 80, true);
			NPCHandler.spawnNpc2(10756, 2837, 9583, c.playerId * 4, 0, 123, 7,
					96, 54);
			NPCHandler.spawnNpc2(10212, 2839, 9557, c.playerId * 4, 0, 131, 5,
					93, 54);
			NPCHandler.spawnNpc2(10699, 2836, 9575, c.playerId * 4, 0, 113, 6,
					100, 30);
			NPCHandler.spawnNpc2(10698, 2833, 9563, c.playerId * 4, 0, 75, 3,
					30, 30);
		} else if (c.complexity == 4) {
			NPCHandler.spawnBoss(c, 10218, 2847, 9582, c.playerId * 4, 0, 175,
					10, 300, 200, true);
			NPCHandler.spawnNpc2(10507, 2833, 9564, c.playerId * 4, 0, 140, 9,
					325, 255);
			NPCHandler.spawnNpc2(10756, 2837, 9583, c.playerId * 4, 0, 155, 7,
					310, 186);
			NPCHandler.spawnNpc2(10212, 2839, 9557, c.playerId * 4, 0, 160, 12,
					295, 200);
			NPCHandler.spawnNpc2(10699, 2836, 9575, c.playerId * 4, 0, 156, 9,
					290, 145);
		}
	}

	public static void spawnMoreNPC1(final Player c) {
		if (c.complexity == 1) {
			NPCHandler.spawnNpc2(10801, 2841, 9608, c.playerId * 4, 0, 35, 3,
					57, 47);
			NPCHandler.spawnNpc2(10837, 2841, 9627, c.playerId * 4, 0, 40, 1,
					40, 60);
			NPCHandler.spawnNpc2(10262, 2836, 9653, c.playerId * 4, 0, 25, 2,
					30, 40);
		} else if (c.complexity == 2) {
			NPCHandler.spawnNpc2(10167, 2841, 9608, c.playerId * 4, 0, 35, 3,
					57, 47);
			NPCHandler.spawnNpc2(10837, 2841, 9627, c.playerId * 4, 0, 40, 5,
					40, 60);
			NPCHandler.spawnNpc2(10699, 2836, 9653, c.playerId * 4, 0, 25, 6,
					30, 40);
		} else if (c.complexity == 3) {
			NPCHandler.spawnNpc2(10218, 2841, 9608, c.playerId * 4, 0, 120, 8,
					99, 147);
			NPCHandler.spawnNpc2(10262, 2841, 9627, c.playerId * 4, 0, 96, 6,
					145, 160);
			NPCHandler.spawnNpc2(10462, 2836, 9653, c.playerId * 4, 0, 89, 7,
					157, 140);
		} else if (c.complexity == 4) {
			NPCHandler.spawnNpc2(10462, 2841, 9608, c.playerId * 4, 0, 150, 10,
					256, 220);
			NPCHandler.spawnNpc2(10699, 2841, 9627, c.playerId * 4, 0, 144, 9,
					343, 209);
			NPCHandler.spawnNpc2(10840, 2836, 9653, c.playerId * 4, 0, 174, 7,
					288, 221);
		}
	}

	public static void handleBoss(final Player c) {
		final int random = Misc.random(1);
		final int random1 = Misc.random(2);
		switch (c.floor) {
		case 1:
			switch (c.complexity) {
			case 1:
				NPCHandler.spawnBoss(c, 9948, 2762, 9195, c.playerId * 4, 0,
						100, 6, 122, 15, true);
				break;

			case 2:
				NPCHandler.spawnBoss(c, 9951, 2762, 9195, c.playerId * 4, 0,
						150, 10, 12, 17, true);
				break;

			case 3:
				if (random == 0) {
					NPCHandler.spawnBoss(c, 9951, 2762, 9195, c.playerId * 4,
							0, 250, 17, 450, 20, true);
				} else {
					NPCHandler.spawnBoss(c, 9916, 2762, 9195, c.playerId * 4,
							0, 300, 17, 55, 25, true);
				}
				break;

			case 4:
				if (random == 0) {
					NPCHandler.spawnBoss(c, 9951, 2762, 9195, c.playerId * 4,
							0, 350, 24, 444, 24, true);
				} else if (random == 1) {
					NPCHandler.spawnBoss(c, 9916, 2762, 9195, c.playerId * 4,
							0, 400, 27, 52, 27, true);
				}
				break;
			}
			break;
		case 2:
			switch (c.complexity) {
			case 1:
				if (random == 0) {
					NPCHandler.spawnBoss(c, 9948, 2855, 9638, c.playerId * 4,
							0, 100, 6, 100, 10, true);
				} else if (random == 1) {
					NPCHandler.spawnBoss(c, 9916, 2855, 9638, c.playerId * 4,
							0, 125, 10, 11, 11, true);
				}
				break;
			case 2:
				if (random1 == 0) {
					NPCHandler.spawnBoss(c, 9951, 2855, 9638, c.playerId * 4,
							0, 175, 6, 150, 15, true);
				} else if (random1 == 1) {
					NPCHandler.spawnBoss(c, 9916, 2855, 9638, c.playerId * 4,
							0, 185, 10, 17, 17, true);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2855, 9638, c.playerId * 4,
							0, 215, 10, 189, 17, true);
				}
				break;
			case 3:
				if (random1 == 0) {
					NPCHandler.spawnBoss(c, 9951, 2855, 9638, c.playerId * 4,
							0, 275, 14, 250, 20, true);
				} else if (random1 == 1) {
					NPCHandler.spawnBoss(c, 9916, 2855, 9638, c.playerId * 4,
							0, 300, 19, 17, 17, true);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2855, 9638, c.playerId * 4,
							0, 325, 22, 189, 17, true);
				}
				break;
			case 4:
				if (random1 == 0) {
					NPCHandler.spawnBoss(c, 9951, 2855, 9638, c.playerId * 4,
							0, 350, 24, 400, 30, true);
				} else if (random1 == 1) {
					NPCHandler.spawnBoss(c, 9916, 2855, 9638, c.playerId * 4,
							0, 400, 27, 45, 30, true);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2855, 9638, c.playerId * 4,
							0, 425, 30, 475, 30, true);
				}
				break;
			}
			break;
		case 3:
			switch (c.complexity) {
			case 1:
				if (random == 0) {
					NPCHandler.spawnBoss(c, 9948, 2893, 9486, c.playerId * 4,
							0, 100, 6, 100, 10, true);
				} else if (random == 1) {
					NPCHandler.spawnBoss(c, 9916, 2893, 9486, c.playerId * 4,
							0, 125, 10, 111, 11, true);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2893, 9486, c.playerId * 4,
							0, 215, 10, 189, 17, true);
				}
				break;
			case 2:
				if (random1 == 0) {
					NPCHandler.spawnBoss(c, 9951, 2893, 9486, c.playerId * 4,
							0, 175, 6, 150, 15, true);
				} else if (random1 == 1) {
					NPCHandler.spawnBoss(c, 9916, 2893, 9486, c.playerId * 4,
							0, 185, 10, 175, 17, true);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2893, 9486, c.playerId * 4,
							0, 215, 10, 189, 17, true);
				}
				break;
			case 3:
				if (random1 == 0) {
					NPCHandler.spawnBoss(c, 9951, 2893, 9486, c.playerId * 4,
							0, 275, 14, 250, 20, true);
				} else if (random1 == 1) {
					NPCHandler.spawnBoss(c, 9916, 2893, 9486, c.playerId * 4,
							0, 300, 19, 175, 17, true);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2893, 9486, c.playerId * 4,
							0, 325, 22, 189, 17, true);
				}
				break;
			case 4:
				if (random1 == 0) {
					NPCHandler.spawnBoss(c, 9951, 2893, 9486, c.playerId * 4,
							0, 350, 24, 400, 30, true);
				} else if (random1 == 1) {
					NPCHandler.spawnBoss(c, 9916, 2893, 9486, c.playerId * 4,
							0, 400, 27, 450, 30, true);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2893, 9486, c.playerId * 4,
							0, 425, 30, 475, 30, true);
				}
				break;
			}
			break;

		case 4:
			switch (c.complexity) {
			case 1:
				if (random == 0) {
					NPCHandler.spawnBoss(c, 9948, 2572, 9529, c.playerId * 4,
							0, 100, 6, 100, 10, true);
				} else if (random == 1) {
					NPCHandler.spawnBoss(c, 9916, 2572, 9529, c.playerId * 4,
							0, 125, 10, 111, 11, true);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2572, 9529, c.playerId * 4,
							0, 215, 10, 189, 17, true);
				}
				break;
			case 2:
				if (random1 == 0) {
					NPCHandler.spawnBoss(c, 9951, 2572, 9529, c.playerId * 4,
							0, 175, 6, 150, 15, true);
				} else if (random1 == 1) {
					NPCHandler.spawnBoss(c, 9916, 2572, 9529, c.playerId * 4,
							0, 185, 10, 175, 17, true);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2572, 9529, c.playerId * 4,
							0, 215, 10, 189, 17, true);
				}
				break;
			case 3:
				if (random1 == 0) {
					NPCHandler.spawnBoss(c, 9951, 2572, 9529, c.playerId * 4,
							0, 275, 14, 250, 20, true);
				} else if (random1 == 1) {
					NPCHandler.spawnBoss(c, 9916, 2572, 9529, c.playerId * 4,
							0, 300, 19, 175, 17, true);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2572, 9529, c.playerId * 4,
							0, 325, 22, 189, 19, true);
				}
				break;
			case 4:
				if (random1 == 0) {
					NPCHandler.spawnBoss(c, 9951, 2572, 9529, c.playerId * 4,
							0, 350, 24, 400, 30, true);
				} else if (random1 == 1) {
					NPCHandler.spawnBoss(c, 9916, 2572, 9529, c.playerId * 4,
							0, 400, 27, 450, 30, true);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2572, 9529, c.playerId * 4,
							0, 425, 30, 475, 30, true);
				}
				break;
			}
			break;

		case 5:
			switch (c.complexity) {
			case 1:
				if (random == 0) {
					NPCHandler.spawnBoss(c, 9727, 2394, 9705, c.playerId * 4,
							0, 100, 6, 100, 10, false);
				} else if (random == 1) {
					NPCHandler.spawnBoss(c, 9727, 2394, 9705, c.playerId * 4,
							0, 125, 10, 111, 15, false);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2394, 9705, c.playerId * 4,
							0, 215, 10, 189, 19, false);
				}
				break;
			case 2:
				if (random1 == 0) {
					NPCHandler.spawnBoss(c, 9951, 2394, 9705, c.playerId * 4,
							0, 175, 6, 150, 10, false);
				} else if (random1 == 1) {
					NPCHandler.spawnBoss(c, 9916, 2394, 9705, c.playerId * 4,
							0, 185, 10, 175, 17, false);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2394, 9705, c.playerId * 4,
							0, 215, 10, 189, 19, false);
				}
				break;
			case 3:
				if (random1 == 0) {
					NPCHandler.spawnBoss(c, 9951, 2394, 9705, c.playerId * 4,
							0, 275, 14, 250, 20, false);
				} else if (random1 == 1) {
					NPCHandler.spawnBoss(c, 9916, 2394, 9705, c.playerId * 4,
							0, 300, 19, 175, 17, false);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2394, 9705, c.playerId * 4,
							0, 325, 22, 189, 17, false);
				}
				break;
			case 4:
				if (random1 == 0) {
					NPCHandler.spawnBoss(c, 9951, 2394, 9705, c.playerId * 4,
							0, 350, 24, 400, 30, false);
				} else if (random1 == 1) {
					NPCHandler.spawnBoss(c, 9916, 2394, 9705, c.playerId * 4,
							0, 400, 27, 450, 30, false);
				} else if (random1 == 2) {
					NPCHandler.spawnBoss(c, 9727, 2394, 9705, c.playerId * 4,
							0, 425, 30, 475, 30, false);
				}
				break;
			}
			c.sendMessage("The boss is somewhere in the dungeon. Look for him!");
			break;
		}
	}

}
