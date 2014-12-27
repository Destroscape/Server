package game.skill.fishing;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.util.Misc;
import game.entity.player.Player;
import game.skill.SkillHandler;
import game.content.achievement.*;

public class Fishing extends SkillHandler {

	private final static int[][] data = {
		{ 1, 1, 303, -1, 317, 10, 621, 321, 15, 30, Misc.random(0) + 4 }, // SHRIMP / ANCHOVIES
		{ 2, 5, 307, 313, 327, 20, 622, 345, 10, 30, Misc.random(1) + 4 }, // SARDINE / HERRING
		{ 3, 62, 303, -1, 7944, 300, 621, -1, -1, -1, Misc.random(2) + 3 }, // MONKFISH
		{ 4, 20, 309, 314, 335, 50, 622, 331, 30, 70, Misc.random(2) + 4 }, // TROUT
		{ 5, 23, 303, -1, 341, 45, 619, 363, 46, 100, Misc.random(2) + 4 }, // BASS / COD
		{ 6, 25, 307, 313, 349, 60, 622, -1, -1, -1, Misc.random(2) + 4 }, // PIKE
		{ 7, 35, 311, -1, 359, 80, 618, 371, 50, 100, Misc.random(3) + 4 }, // TUNA / SWORDIE
		{ 8, 40, 301, -1, 377, 90, 619, -1, -1, -1, Misc.random(3) + 4 }, // LOBSTER
		{ 9, 16, 303, -1, 353, 20, 620, -1, -1, -1, Misc.random(2) + 4 }, // MACKEREL
		{ 10, 76, 311, -1, 383, 500, 618, -1, -1, -1, Misc.random(4) + 4 }, // SHARK
		{ 11, 79, 303, -1, 395, 500, 619, -1, -1, -1, Misc.random(5) + 4 }, // SEA TURTLE
		{ 12, 81, 303, -1, 389, 900, 621, 389, 90, -1, Misc.random(3) + 2 }, // MANTA RAY
		{ 13, 99, 307, 15263, 15270, 1300, 622, 15270, 90, -1, Misc.random(3) + 4 }, // Rocktails
	};

	private static void attemptdata(final Player c, int npcId) {
		if (c.fishingProp[4] > 0) {
			c.playerIsFishing = false;
			c.fishingProp[4] = -1;
			return;
		}
		if (!noInventorySpace(c, "fishing")) {
			return;
		}
		c.events++;
		for (int[] aData : data) {
			if (npcId == aData[0]) {
				if (c.playerLevel[c.playerFishing] < aData[1]) {
					c.sendMessage("You don't meet the fishing requirement to use this spot.");
					c.getPA().sendStatement(
							"You need a fishing level of " + aData[1]
									+ " to fish here.");
					return;
				}
				if (aData[3] > 0) {
					if (!c.getItems().playerHasItem(aData[3])) {
						c.sendMessage("You don't have any "
								+ c.getItems().getItemName(aData[3]) + "!");
						return;
					}
				}
				c.fishingProp[0] = aData[6]; // ANIM
				c.fishingProp[1] = aData[4]; // FISH
				c.fishingProp[2] = aData[5]; // XP
				c.fishingProp[3] = aData[3]; // BAIT
				c.fishingProp[4] = aData[2]; // EQUIP
				c.fishingProp[5] = aData[7]; // sFish
				c.fishingProp[6] = aData[8]; // sLvl
				c.fishingProp[7] = aData[4]; // FISH
				c.fishingProp[8] = aData[9]; // sXP
				c.fishingProp[9] = Misc.random(1) == 0 ? 7 : 5;
				c.fishingProp[10] = aData[0]; // INDEX
				if (!hasFishingEquipment(c, c.fishingProp[4])) {
					return;
				}

				// c.sendMessage("You begin fishing");
				//c.setAnimation(Animation.create(c.fishingProp[0]));
				c.startAnimation(c.fishingProp[0]);
				c.stopPlayerSkill = true;

				if (c.playerIsFishing) {
					return;
				}

				c.playerIsFishing = true;
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						if (c.fishingProp[3] > 0) {
							c.getItems().deleteItem(c.fishingProp[3],
									c.getItems().getItemSlot(c.fishingProp[3]),
									1);
							if (!c.getItems().playerHasItem(c.fishingProp[3])) {
								c.sendMessage("You don't have any "
										+ c.getItems().getItemName(
												c.fishingProp[3]) + " left!");
								c.sendMessage("You need "
										+ c.getItems().getItemName(
												c.fishingProp[3])
												+ " to fish here.");
								container.stop();
								return;
							}
						}
						if (c.events > 1) {
							container.stop();
							return;
						}
						if (!hasFishingEquipment(c, c.fishingProp[4])) {
							container.stop();
							return;
						}
						if (!noInventorySpace(c, "fishing")) {
							container.stop();
							return;
						}
						if (Misc.random(300) == 0) {
							c.sendMessage("The fish spat in your face! you got shocked and stopped fishing.");
							container.stop();
							return;
						}
						if (!c.stopPlayerSkill) {
							container.stop();
							return;
						}
						if (!c.playerIsFishing) {
							container.stop();
							return;
						}
						if (c.fishingProp[5] > 0) {
							if (c.playerLevel[c.playerFishing] >= c.fishingProp[6]) {
								c.fishingProp[1] = c.fishingProp[Misc.random(1) == 0 ? 7
										: 5];
							}
						}

						if (c.fishingProp[1] > 0) {
						if (c.fishingProp[1] == 15270) {
							AchievementManager.increase(c, Achievements.ROCKTAIL_TAMER);
						}
						if (c.fishingProp[1] == 383) {
							AchievementManager.increase(c, Achievements.SHARK_TAMER);
						}
							c.sendMessage("You catch a "
									+ c.getItems()
									.getItemName(c.fishingProp[1])
									+ ".");
						}
						if (c.fishingProp[1] > 0) {
							c.getItems().addItem(c.fishingProp[1], 1);
							//c.setAnimation(Animation.create(c.fishingProp[0]));
							c.startAnimation(c.fishingProp[0]);
						}
						if (c.fishingProp[2] > 0) {
							if (c.playerEquipment[c.playerHands] == 13856) {
								c.getPA().addSkillXP(c.fishingProp[2] * 20 * 2,
										c.playerFishing);
							} else {
								c.getPA().addSkillXP(c.fishingProp[2] * 20,
										c.playerFishing);
							}
						}
					}

					@Override
					public void stop() {
						resetFishing(c);
						c.events = 0;
					}
				}, 7);
			}
		}
	}

	private static boolean hasFishingEquipment(Player c, int equipment) {
		if (!c.getItems().playerHasItem(equipment)) {
			if (equipment == 311) {
				if (!c.getItems().playerHasItem(311)
						&& !c.getItems().playerHasItem(10129)
						&& c.playerEquipment[3] != 10129) {
					c.sendMessage("You need a "
							+ c.getItems().getItemName(equipment)
							+ " to fish here.");
					return false;
				}
			} else {
				c.sendMessage("You need a "
						+ c.getItems().getItemName(equipment)
						+ " to fish here.");
				return false;
			}
		}
		return true;
	}

	public static void resetFishing(Player c) {
		c.fishTimer = 0;
		//c.setAnimation(Animation.create(65535));
		c.startAnimation(65535);
		c.getPA().removeAllWindows();
		c.playerIsFishing = false;
		c.fishingProp[4] = -1;
	}

	public static void fishingNPC(Player c, int i, int l) {
		switch (i) {
		case 1:
			switch (l) {
			case 329:
			case 323:
			case 325:
			case 327:
			case 330:
			case 332:
			case 316: // NET + BAIT
				Fishing.attemptdata(c, 1);
				break;
			case 319:
			case 334:
			case 313: // NET + HARPOON
				Fishing.attemptdata(c, 3);
				break;
			case 322: // NET + HARPOON
				Fishing.attemptdata(c, 5);
				break;

			case 309: // LURE
			case 310:
			case 311:
			case 314:
			case 315:
			case 317:
			case 318:
			case 328:
			case 331:
				Fishing.attemptdata(c, 4);
				break;

			case 312:
			case 321:
			case 324: // CAGE + HARPOON
				Fishing.attemptdata(c, 8);
				break;
			case 326: // Manta Rays
				Fishing.attemptdata(c, 12);
				break;
			}
			break;
		case 2:
			switch (l) {
			case 327:
			case 330:
			case 332:
			case 316: // BAIT + NET
				Fishing.attemptdata(c, 2);
				break;

			case 319: //Rocktails
				Fishing.attemptdata(c, 13);
				break;
			case 323:
			case 325: // BAIT + NET
				Fishing.attemptdata(c, 9);
				break;
			case 310:
			case 311:
			case 314:
			case 315:
			case 317:
			case 318:
			case 328:
			case 329:
			case 331:
			case 309: // BAIT + LURE
				Fishing.attemptdata(c, 6);
				break;
			case 312:
			case 321:
			case 324:// SWORDIES+TUNA-CAGE+HARPOON
				Fishing.attemptdata(c, 7);
				break;
			case 313:
			case 322:
			case 334: // NET+HARPOON
				Fishing.attemptdata(c, 10);
				break;
			case 326: // SEA TURTLE
				Fishing.attemptdata(c, 11);
				break;
			}
			break;
		}
	}

	public static boolean fishingNPC(int npc) {
		for (int i = 308; i < 335; i++) {
			if (npc == i) {
				return true;
			}
		}
		return false;
	}
}