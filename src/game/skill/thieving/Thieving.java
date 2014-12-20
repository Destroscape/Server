package game.skill.thieving;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.util.Misc;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.object.ObjectHandler;
import game.skill.SkillHandler;
import game.content.achievement.*;

public class Thieving extends SkillHandler {

	public static void stealFromStall(final Player c, final int id, int amount,
			int xp, int level, final int i, final int x, final int y,
			final int face) {
	if ((System.currentTimeMillis() - c.lastRandom) > 600000) {
		if (c.playerEquipment[c.playerCape] == 19754 && Misc.random(600) == 1) {
			c.executeRandom();
			return;
		}
		if (c.playerEquipment[c.playerCape] != 19754 && Misc.random(300) == 0) {
			c.executeRandom();
			return;
		}
	}
		if (System.currentTimeMillis() - c.lastThieve < 2500)
			return;
		if (c.underAttackBy > 0 || c.underAttackBy2 > 0) {
			c.sendMessage("You can't steal from a stall while in combat!");
			return;
		}
		if (c.playerLevel[c.playerThieving] >= level) {
			if (c.getItems().addItem(id, amount)) {
				//c.setAnimation(Animation.create(832));
				c.startAnimation(832);
				
				if (c.playerEquipment[c.playerHands] == 13854) {
					c.getPA().addSkillXP(xp * THIEVING_XP * 2, c.playerThieving);
				} else {
					c.getPA().addSkillXP(xp * THIEVING_XP, c.playerThieving);
				}
				c.lastThieve = System.currentTimeMillis();
				c.sendMessage("You steal some coins.");

				ObjectHandler.createAnObject(c, 634, x, y, face);

				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						ObjectHandler.createAnObject(c, i, x, y, face);
						container.stop();
					}

					@Override
					public void stop() {

					}
				}, getRespawnTime(c, i));
			}
		} else {
			c.sendMessage("You must have a thieving level of " + level
					+ " to thieve from this stall.");
		}
	}

	private static int getRespawnTime(Player c, int i) {
		switch (i) {
		case 2561:
			return 4; // BAKER
		case 2560:
			return 12; // SILK
		case 7053:
			return 12; // SEED
		case 2563:
			return 15; // FUR
		case 2565:
			return 20; // SILVER
		case 2564:
			return 25; // SPICE
		case 2562:
			return 30; // GEM
		case 14011:
			return 5; // JUG
		default:
			return 5;
		}
	}

	private static int[][] pickpocket = { 
		{ 1, 1, 8, 1532, 3, 10, 20, 30 },
		{ 2, 1, 8, 1532, 3, 10, 20, 30 }, 
		{ 3, 1, 8, 1532, 3, 10, 20, 30 },
		{ 4, 1, 8, 1532, 3, 10, 20, 30 }, 
		{ 5, 1, 8, 1532, 3, 10, 20, 30 },
		{ 6, 1, 8, 1532, 3, 10, 20, 30 },
		{ 3223, 1, 8, 1532, 3, 10, 20, 30 },
		{ 3224, 1, 8, 1532, 3, 10, 20, 30 },
		{ 3226, 1, 8, 1532, 3, 10, 20, 30 },
		{ 3227, 1, 8, 1532, 3, 10, 20, 30 },
		{ 3915, 1, 8, 1532, 3, 10, 20, 30 },
		{ 7, 10, 15, 2000, 3, 20, 30, 40 },
		{ 9, 40, 47, 2355, 3, 50, 60, 70 },
		{ 15, 25, 26, 2164, 3, 35, 45, 55 },
		{ 20, 70, 151, 4241, 8, 80, 90, 99 },
		{ 2256, 70, 151, 4241, 8, 80, 90, 99 },
		{ 21, 80, 275, 6643, 8, 90, 95, 99 },
		{ 23, 55, 84, 3174, 7, 65, 75, 85 },
		{ 26, 55, 84, 3174, 7, 65, 75, 85 },
		{ 18, 25, 26, 2164, 3, 35, 45, 55 },
		{ 3225, 10, 15, 2000, 3, 20, 30, 40 },
		{ 2234, 38, 43, 2532, 7, 48, 58, 68 },
		{ 2235, 38, 43, 2532, 7, 48, 58, 68 },
		{ 32, 40, 47, 2753, 3, 50, 60, 70 },
		{ 296, 40, 47, 2753, 3, 50, 60, 70 },
		{ 297, 40, 47, 2753, 3, 50, 60, 70 },
		{ 298, 40, 47, 2753, 3, 50, 60, 70 },
		{ 299, 40, 47, 2753, 3, 50, 60, 70 },
		{ 2699, 40, 47, 2753, 3, 50, 60, 70 },
		{ 2701, 40, 47, 2753, 3, 50, 60, 70 },
		{ 2702, 40, 47, 2753, 3, 50, 60, 70 },
		{ 2703, 40, 47, 2753, 3, 50, 60, 70 },
		{ 3228, 40, 47, 2753, 3, 50, 60, 70 },
		{ 3229, 40, 47, 2753, 3, 50, 60, 70 },
		{ 3230, 40, 47, 2753, 3, 50, 60, 70 },
		{ 3231, 40, 47, 2753, 3, 50, 60, 70 },
		{ 3232, 40, 47, 2753, 3, 50, 60, 70 },
		{ 3233, 40, 47, 2753, 3, 50, 60, 70 },
		{ 3241, 40, 47, 2753, 3, 50, 60, 70 },
		{ 4307, 40, 47, 2753, 3, 50, 60, 70 },
		{ 4308, 40, 47, 2753, 3, 50, 60, 70 },
		{ 4309, 40, 47, 2753, 3, 50, 60, 70 },
		{ 4310, 40, 47, 2753, 3, 50, 60, 70 },
		{ 4311, 40, 47, 2753, 3, 50, 60, 70 },
		{ 5919, 40, 47, 2753, 3, 50, 60, 70 },
		{ 5920, 40, 47, 2753, 3, 50, 60, 70 }, };

	public static boolean pickpocketNPC(Player c, int npc) {
		for (int i = 0; i < pickpocket.length; i++) {
			if (npc == pickpocket[i][0]) {
				return true;
			}
		}
		return false;
	}

	private static void failThieve(final Player c, final int i, final int npc) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.setHitDiff(pickpocket[i][4]);
				c.setHitUpdateRequired(true);
				c.playerLevel[3] -= pickpocket[i][4];
				c.getPA().refreshSkill(3);

				c.gfx100(80);
				//c.setAnimation(Animation.create(404));
				c.startAnimation(404);
				c.lastThieve = System.currentTimeMillis() + 7500;
				c.sendMessage("...you fail to pickpocket the "
						+ NPCHandler.getNpcListName(pickpocket[i][0]).replace(
								"_", " ") + "!");
				c.playerStun = true;
				AchievementManager.increase(c, Achievements.NOT_A_POCKETEER);
				container.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.playerStun = false;
				container.stop();
			}

			@Override
			public void stop() {

			}
		}, 13);
	}

	private static void completeThieve(final Player c, final int i) {
		int loot = pickpocket[i][3];
		int chance = Misc.random(20);
		String message = "...You successfully pickpocket the "
				+ NPCHandler.getNpcListName(pickpocket[i][0]) + ".";
		if (chance == 1) {
			if (c.playerLevel[c.playerAgility] >= pickpocket[i][5]) {
				loot = pickpocket[i][3] * 2;
				message = "You steal a double loot from the the "
						+ NPCHandler.getNpcListName(pickpocket[i][0]) + "!";
			}
		} else if (chance == 5) {
			if (c.playerLevel[c.playerAgility] >= pickpocket[i][6]) {
				loot = pickpocket[i][3] * 3;
				message = "You steal a triple loot from the "
						+ NPCHandler.getNpcListName(pickpocket[i][0]) + "!";
			}
		} else if (chance == 10) {
			if (c.playerLevel[c.playerAgility] >= pickpocket[i][7]) {
				loot = pickpocket[i][3] * 4;
				message = "You steal a quadruple loot from the "
						+ NPCHandler.getNpcListName(pickpocket[i][0]) + "!";
			}
		}
		final int loot2 = loot;
		final String message2 = message;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.sendMessage(message2);
				if (c.playerEquipment[c.playerHands] == 13854) {
					c.getPA().addSkillXP(pickpocket[i][2] * THIEVING_XP * 2, 17);
				} else {
					c.getPA().addSkillXP(pickpocket[i][2] * THIEVING_XP, 17);
				}
				c.getItems().addItem(995, loot2);
				AchievementManager.increase(c, Achievements.POCKETEER);
				container.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public static void stealFromStall(Player c, int id, int amount, int xp, int level) {
	if ((System.currentTimeMillis() - c.lastRandom) > 600000) {
		if (c.playerEquipment[c.playerCape] == 19754 && Misc.random(800) == 1) {
			c.executeRandom();
			return;
		}
		if (c.playerEquipment[c.playerCape] != 19754 && Misc.random(400) == 0) {
			c.executeRandom();
			return;
		}
	}
		if (System.currentTimeMillis() - c.lastThieve < 2500)
			return;
		if (c.playerLevel[c.playerThieving] >= level) {

			if (c.playerEquipment[c.playerCape] == 19754 && Misc.random(24) == 1) {
				c.sendMessage("You get caught trying to thieve the stall..");
				//c.setAnimation(Animation.create(3679));
				c.startAnimation(3679);
				if(c.playerLevel[3] <= 20) {
					appendHit(Misc.random(2), c);
				} else {
					appendHit(Misc.random(5), c);
					return;
				}
			}

			if (c.playerEquipment[c.playerCape] != 19754 && Misc.random(12) == 1) {
				c.sendMessage("You get caught trying to thieve the stall..");
				//c.setAnimation(Animation.create(3679));
				c.startAnimation(3679);
				if(c.playerLevel[3] <= 20) {
					appendHit(Misc.random(2), c);
				} else {
					appendHit(Misc.random(5), c);
					return;
				}
			}
			if (c.getItems().addItem(id, amount)) {
				c.sendMessage("You attempt to steal something from the stall...");
				c.sendMessage("You managed to steal some coins.");
				//c.setAnimation(Animation.create(832));
				c.startAnimation(832);
				c.getItems().addItem(995, c.playerLevel[c.playerThieving] * 65);
			}
			if (c.playerEquipment[c.playerHands] == 13854) {
				c.getPA().addSkillXP(xp * THIEVING_XP * 2, c.playerThieving);
			} else {
				c.getPA().addSkillXP(xp * THIEVING_XP, c.playerThieving);
			}
			c.lastThieve = System.currentTimeMillis();
		}		
		else if(c.playerLevel[17] < level) {
			c.sendMessage("You need a theiving level of "+level+" to theif from this stall.");
		}
	}

	public static void appendHit(int damage, Player c) {
		PlayerHandler.players[c.playerId].setHitDiff(damage);
		PlayerHandler.players[c.playerId].playerLevel[3] -= damage;
		c.getPA().refreshSkill(3);
		PlayerHandler.players[c.playerId].setHitUpdateRequired(true);	
		PlayerHandler.players[c.playerId].updateRequired = true;		
	}	

	public static void attemptToPickpocket(final Player c, int npcId) {
		if (System.currentTimeMillis() - c.lastThieve < 2000 || c.playerStun) {
			return;
		}
		if (c.underAttackBy > 0 || c.underAttackBy2 > 0) {
			c.sendMessage("You can't pickpocket while in combat!");
			return;
		}
		for (int i = 0; i < pickpocket.length; i++) {
			if (npcId == pickpocket[i][0]) {
				if (!hasRequiredLevel(c, 17, pickpocket[i][1], "thieving",
						"pickpocket this")) {
					return;
				}
				c.sendMessage("You attempt to pickpocket the "
						+ NPCHandler.getNpcListName(pickpocket[i][0]) + "...");
				//c.setAnimation(Animation.create(881));
				c.startAnimation(881);
				if (c.playerEquipment[c.playerCape] == 19754 && Misc.random(24) == 1) {
					failThieve(c, i, npcId);
				} else {
					completeThieve(c, i);
				}
				if (c.playerEquipment[c.playerCape] != 19754 && Misc.random(c.playerLevel[17] + 5) < Misc
						.random(pickpocket[i][1])) {
					failThieve(c, i, npcId);
				} else {
					completeThieve(c, i);
				}
				c.lastThieve = System.currentTimeMillis();
			}
		}
	}
}