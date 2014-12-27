package game.net.packets;

import engine.util.Misc;
import game.Config;
import game.content.ArmorSets;
import game.content.CrystalChest;
import game.entity.player.Player;
import game.entity.player.PlayerAssistant;
import game.minigame.warriorsguild.WarriorsGuild;
import game.skill.SkillHandler;
import game.skill.cooking.Cooking;
import game.skill.crafting.GemCutting;
import game.skill.herblore.Herblore;
import game.skill.summoning.SummoningSpecials;

public class UseItem {

	public static void ItemonItem(final Player c, final int itemUsed,
			final int useWith) {
		// Crystal key Creation
		c.isSkilling[9] = false;
		c.logID = -1;
		c.woodCutting = false;
		c.isArrowing = false;
		Cooking.resetCooking(c);
		c.isStringing = false;
		if (itemUsed == CrystalChest.toothHalf()
				&& useWith == CrystalChest.loopHalf()
				|| itemUsed == CrystalChest.loopHalf()
				&& useWith == CrystalChest.toothHalf()) {
			CrystalChest.makeKey(c);
		}
		if (c.getItems().getItemName(itemUsed).contains("(")
				&& c.getItems().getItemName(useWith).contains("(")) {
			c.getPotMixing().mixPotion2(itemUsed, useWith);
		}
		if (itemUsed == 269 && useWith == 15308 
				|| itemUsed == 269 && useWith == 15312 
				|| itemUsed == 269 && useWith == 15316 
				|| itemUsed == 269 && useWith == 15320 
				|| itemUsed == 269 && useWith == 15324) {
			if (c.getItems().playerHasItem(15308, 1) 
					&& c.getItems().playerHasItem(15312, 1) 
					&& c.getItems().playerHasItem(15316, 1) 
					&& c.getItems().playerHasItem(15320, 1) 
					&& c.getItems().playerHasItem(15324, 1)) {
				Herblore.finishOverload(c);
			} else {
				c.sendMessage("You need all extreme potions with a torstol to make an Overload.");
			}
		}
if (itemUsed == 145 && useWith == 261 || itemUsed == 261 && useWith == 145) {
        if (c.getItems().playerHasItem(145, 1) && c.getItems().playerHasItem(261, 1)){
            if (c.playerLevel[c.playerHerblore] >= 88) {
            c.getItems().deleteItem(145, c.getItems().getItemSlot(145),1);
            c.getItems().deleteItem(261, c.getItems().getItemSlot(261),1);
            c.getItems().addItem(15309,1);
                c.sendMessage("You make a Extreme Attack (3).");
                c.getPA().addSkillXP(220 * 40, c.playerHerblore);
            } else {
                c.sendMessage("You need a herblore level of 88 to make that potion.");
            }
            } else {
                c.sendMessage("You need super attack (3) and a Clean avantoe to make that.");
            }
        }

if (itemUsed == 267 && useWith == 157 || itemUsed == 157 && useWith == 267) {
        if (c.getItems().playerHasItem(157, 1) && c.getItems().playerHasItem(267, 1)){
            if (c.playerLevel[c.playerHerblore] >= 89) {
            c.getItems().deleteItem(267, c.getItems().getItemSlot(267),1);
            c.getItems().deleteItem(157, c.getItems().getItemSlot(157),1);
            c.getItems().addItem(15313,1);
                c.sendMessage("You make a Extreme Strength (3).");
                c.getPA().addSkillXP(230 * 40, c.playerHerblore);
            } else {
                c.sendMessage("You need a herblore level of 89 to make that potion.");
            }
            } else {
                c.sendMessage("You need super strength (3) and a Clean Dwarf Weed.");
            }
        }

if (itemUsed == 3026 && useWith == 3000 || itemUsed == 3000 && useWith == 3026) {
        if (c.getItems().playerHasItem(3026, 1) && c.getItems().playerHasItem(3000, 1)){
            if (c.playerLevel[c.playerHerblore] >= 85) {
            c.getItems().deleteItem(3026, c.getItems().getItemSlot(3026),1);
            c.getItems().deleteItem(3000, c.getItems().getItemSlot(3000),1);
            c.getItems().addItem(15301,1);
                c.sendMessage("You make a Recover Special (3).");
                c.getPA().addSkillXP(200 * 40, c.playerHerblore);
            } else {
                c.sendMessage("You need a herblore level of 85 to make that potion.");
            }
            } else {
                c.sendMessage("You need super restore (3) and a Clean Snap dragon.");
            }
        }

if (itemUsed == 3042 && useWith == 3000 || itemUsed == 3000 && useWith == 3042) {
        if (c.getItems().playerHasItem(3042, 1) && c.getItems().playerHasItem(3000, 1)){
            if (c.playerLevel[c.playerHerblore] >= 93) {
            c.getItems().deleteItem(3042, c.getItems().getItemSlot(3042),1);
            c.getItems().deleteItem(3000, c.getItems().getItemSlot(3000),1);
            c.getItems().addItem(15321,1);
                c.sendMessage("You make a Extreme Mage (3).");
                c.getPA().addSkillXP(320 * 35, c.playerHerblore);
            } else {
                c.sendMessage("You need a herblore level of 93 to make that potion.");
            }
            } else {
                c.sendMessage("You need a Magic Potion (3) and a Clean Snap dragon.");
            }
        }

if (itemUsed == 139 && useWith == 3000 || itemUsed == 3000 && useWith == 139) {
        if (c.getItems().playerHasItem(139, 1) && c.getItems().playerHasItem(3000, 1)){
            if (c.playerLevel[c.playerHerblore] >= 92) {
            c.getItems().deleteItem(139, c.getItems().getItemSlot(139),1);
            c.getItems().deleteItem(3000, c.getItems().getItemSlot(3000),1);
            c.getItems().addItem(15329,1);
                c.sendMessage("You make a Super Prayer (3).");
                c.getPA().addSkillXP(330 * 35, c.playerHerblore);
            } else {
                c.sendMessage("You need a herblore level of 92 to make that potion.");
            }
            } else {
                c.sendMessage("You need a Prayer Potion (3) and a Clean Snap dragon.");
            }
        }

if (itemUsed == 169 && useWith == 12539 || itemUsed == 12539 && useWith == 169) {
        if (c.getItems().playerHasItem(169, 1) && c.getItems().playerHasItem(12539, 5)){
            if (c.playerLevel[c.playerHerblore] >= 91) {
            c.getItems().deleteItem(169, c.getItems().getItemSlot(169),1);
            c.getItems().deleteItem(12539, c.getItems().getItemSlot(12539),5);
            c.getItems().addItem(15325,1);
                c.sendMessage("You make a Extreme Range (3).");
                c.getPA().addSkillXP(310 * 35, c.playerHerblore);
            } else {
                c.sendMessage("You need a herblore level of 91 to make that potion.");
            }
            } else {
                c.sendMessage("You need a ranging potion (3) and x5 Grenwall Spikes.");
            }
        }

if (itemUsed == 2481 && useWith == 163 || itemUsed == 163 && useWith == 2481) {
        if (c.getItems().playerHasItem(2481, 1) && c.getItems().playerHasItem(163, 1)){
            if (c.playerLevel[c.playerHerblore] >= 90) {
            c.getItems().deleteItem(2481, c.getItems().getItemSlot(2481),1);
            c.getItems().deleteItem(163, c.getItems().getItemSlot(163),1);
            c.getItems().addItem(15317,1);
                c.sendMessage("You make a Extreme Defence (3).");
                c.getPA().addSkillXP(240 * 40, c.playerHerblore);
            } else {
                c.sendMessage("You need a herblore level of 90 to make that potion.");
            }
            } else {
                c.sendMessage("You need super Defence (3) and a Clean lantadyme.");
            }
        }
		if (itemUsed == 1755 || useWith == 1755) {
			GemCutting.cutGem(c, itemUsed, useWith);
		}
		if (itemUsed == 13305 || useWith == 13305){
			if (ArmorSets.isSet(itemUsed)){
				ArmorSets.handleSet(c, itemUsed);
			} else if (ArmorSets.isSet(useWith)){
				ArmorSets.handleSet(c, useWith);
			}
		}
		/*if (itemUsed == 269 && useWith == 15308 || itemUsed == 269
				&& useWith == 15312 || itemUsed == 269 && useWith == 15316
				|| itemUsed == 269 && useWith == 15320 || itemUsed == 269
				&& useWith == 15324) {
			if (c.getItems().playerHasItem(15308, 1)
					&& c.getItems().playerHasItem(15312, 1)
					&& c.getItems().playerHasItem(15316, 1)
					&& c.getItems().playerHasItem(15320, 1)
					&& c.getItems().playerHasItem(15324, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 96) {
					c.getItems().deleteItem(269, c.getItems().getItemSlot(269),
							1);
					c.getItems().deleteItem(15308,
							c.getItems().getItemSlot(15308), 1);
					c.getItems().deleteItem(15312,
							c.getItems().getItemSlot(15312), 1);
					c.getItems().deleteItem(15316,
							c.getItems().getItemSlot(15316), 1);
					c.getItems().deleteItem(15320,
							c.getItems().getItemSlot(15320), 1);
					c.getItems().deleteItem(15324,
							c.getItems().getItemSlot(15324), 1);
					c.getItems().addItem(15332, 1);
					c.sendMessage("You make a Overload Potion (4).");
					c.getPA().addSkillXP(500 * SkillHandler.HERBLORE_XP,
							c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 96 to make that potion.");
				}
			} else {
				c.sendMessage("You need all extreme potions to make a Overload.");
			}
		}*/
		if (itemUsed == 12435) {
			if (c.timeBetweenSpecials > 0 || c.familiarID != 6873) {
				return;
			} else {
				if (c.summoningSpecialPoints >= 12) {
					if (c.getItems().playerHasItem(12435, 1)) {
						c.getItems().bankItem(useWith,
								c.getItems().getItemSlot(useWith), 1);
						c.getItems().deleteItem2(itemUsed, 1);
						c.summoningSpecialPoints -= 12;
						SummoningSpecials.typicalStuff(c);
						c.getPA().addSkillXP(328, c.summoning);
						c.getPA().refreshSkill(c.summoning);
						if (c.playerEquipment[c.playerCape] == 19893) {
							c.summoningSpecialPoints += 2;
						}
						if (c.summoningSpecialPoints >= 10) {
							c.getPA().sendFrame126(
									"" + c.summoningSpecialPoints + "/60",
									18024);
						} else {
							c.getPA().sendFrame126(
									" " + c.summoningSpecialPoints + "/60",
									18024);
						}
						c.sendMessage("The yak sends your requested item to the bank!");
					} else {
						c.sendMessage("You do not have the correct scroll for this familiar.");
					}
				} else {
					c.sendMessage("You do not have enough special points to cast this special.");
				}
			}
		}
		if (itemUsed >= 11710 && itemUsed <= 11714 && useWith >= 11710
				&& useWith <= 11714) {
			if (c.getItems().hasAllShards()) {
				c.getItems().makeBlade();
			}
		}
		if (itemUsed == 2368 && useWith == 2366 || itemUsed == 2366
				&& useWith == 2368) {
			c.getItems().deleteItem(2368, c.getItems().getItemSlot(2368), 1);
			c.getItems().deleteItem(2366, c.getItems().getItemSlot(2366), 1);
			c.getItems().addItem(1187, 1);
		}
		if (itemUsed == 6573 || useWith == 1595) { //Make onyx amulet
		if (!c.getItems().playerHasItem(2357, 1)) {
			c.sendMessage("You need a gold bar to create an onyx amulet.");
			return;
		}
		if (!c.getItems().playerHasItem(6573, 1)) {
			c.sendMessage("You need an Onyx to create an Onyx Amulet.");
			return;
		}
		if (c.playerLevel[c.playerCrafting] < 90) {
			c.sendMessage("You need a Crafting Level of atleast 90 to do this.");
			return;
		}
			c.getItems().deleteItem(6573, c.getItems().getItemSlot(6573), 1);
			c.getItems().deleteItem(2357, c.getItems().getItemSlot(2357), 1);
			c.getItems().addItem(6581, 1);
		}

		if (itemUsed == 21369 || useWith == 4151) {
			if (c.getItems().playerHasItem(21369, 1) && c.getItems().playerHasItem(4151, 1)) {
				c.getItems().deleteItem(21369,1);
				c.getItems().deleteItem(4151,1);
				c.getItems().addItem(21371,1);
			}
		}

		if (itemUsed == 14472 && useWith == 14474 || itemUsed == 14474
				&& useWith == 14472 || itemUsed == 14472 && useWith == 14476
				|| itemUsed == 14476 && useWith == 14472 || itemUsed == 14474
				&& useWith == 14476 || itemUsed == 14476 && useWith == 14474) {
			if (c.getItems().playerHasItem(14472)
					&& c.getItems().playerHasItem(14474)
					&& c.getItems().playerHasItem(14476)) {
				c.getItems().deleteItem(14472, c.getItems().getItemSlot(14472),
						1);
				c.getItems().deleteItem(14474, c.getItems().getItemSlot(14474),
						1);
				c.getItems().deleteItem(14476, c.getItems().getItemSlot(14476),
						1);
				c.getItems().addItem(14479, 1);
			}
		}

		if (itemUsed == 13263 && useWith == 15488 || itemUsed == 15488
				&& useWith == 13263 || itemUsed == 13263 && useWith == 15490
				|| itemUsed == 15490 && useWith == 13263 || itemUsed == 15488
				&& useWith == 15490 || itemUsed == 15490 && useWith == 15488) {
			if (c.getItems().playerHasItem(13263)
					&& c.getItems().playerHasItem(15488)
					&& c.getItems().playerHasItem(15490)) {
				c.getItems().deleteItem(13263, c.getItems().getItemSlot(14463),
						1);
				c.getItems().deleteItem(15488, c.getItems().getItemSlot(14488),
						1);
				c.getItems().deleteItem(15490, c.getItems().getItemSlot(14490),
						1);
				c.getItems().addItem(15492, 1);
			}
		}

		if (c.getItems().isHilt(itemUsed) || c.getItems().isHilt(useWith)) {
			final int hilt = c.getItems().isHilt(itemUsed) ? itemUsed : useWith;
			final int blade = c.getItems().isHilt(itemUsed) ? useWith
					: itemUsed;
			if (blade == 11690) {
				c.getItems().makeGodsword(hilt);
			}
		}
		switch (itemUsed) {
		default:
			if (Config.SERVER_DEBUG = true) {
				if (c.playerRights == 3) {
					Misc.println("Player used Item id: " + itemUsed
							+ " with Item id: " + useWith);
				}
			}
			break;
		}
	}

	public static void ItemonNpc(final Player c, final int itemId,
			final int npcId, final int slot) {
		switch (itemId) {

		default:
			if (Config.SERVER_DEBUG = true) {
				if (c.playerRights >= 4) {
					Misc.println("Player used Item id: " + itemId
							+ " with Npc id: " + npcId + " With Slot : " + slot);
				}
			}
			break;
		}
	}

	public static void ItemonObject(final Player c, final int objectID,
			final int objectX, final int objectY, final int itemId) {
		if (!c.getItems().playerHasItem(itemId, 1)) {
			return;
		}
		c.isSkilling[7] = false;
		switch (objectID) {

		case 15621:
			WarriorsGuild.getInstance().armorOnAnimator(c, itemId);
			break;

			// Cow Milking
		case 8689:
			if (c.getItems().playerHasItem(1925, 1) && c.objectId == 8689) {
				c.turnPlayerTo(c.objectX, c.objectY);
				//c.setAnimation(Animation.create(2292));
				c.startAnimation(2292);
				c.getItems().addItem(1927, 1);
				c.getItems().deleteItem(1925, 1);

			} else {
				c.sendMessage("You need a bucket to milk a cow!");
			}
			break;
			// Crystal Chest
		case 172:
			if (itemId == CrystalChest.KEY) {
				CrystalChest.searchChest(c, objectID, objectX, objectY);
			}
			break;
		case 2783:
			if (itemId == 11286) {
				PlayerAssistant.dfsCreate(c, itemId);
			} else {
				c.getSmithingInt().showSmithInterface(itemId);
			}
			break;

		case 9684:
			if (itemId == 434) {
				if (c.getItems().playerHasItem(434, 1)) {
					c.getItems().deleteItem(434, 1);
					c.getItems().addItem(1761, 1);
					c.sendMessage("You soaked the clay and made soft clay.");
				} else {
					c.sendMessage("You must have at least 1 clay to do this.");
				}
			}
			break;

		default:
			if (Config.SERVER_DEBUG = true) {
				if (c.playerRights == 3) {
					Misc.println("Player At Object id: " + objectID
							+ " with Item id: " + itemId);
				}
			}
			break;
		}
	}

}
