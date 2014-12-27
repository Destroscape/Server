package game.skill.herblore;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.entity.player.Player;
import game.minigame.bountyhunter.BountyHunter;

public class Potions {

	private final Player c;

	public Potions(final Player c) {
		this.c = c;
	}

	public void curePoison(final long delay) {
		c.poisonDamage = 0;
		c.poisonImmune = delay;
		c.lastPoisonSip = System.currentTimeMillis();
	}

	public void drinkOverload() {
		if (c.playerLevel[3] < 51) {
			c.sendMessage("You should get more health before drinking this!");
			return;
		}
		if (c.hasOverloadEffect) {
			c.sendMessage("You already have a boost.");
			return;
		}
		c.hasOverloadEffect = true;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			int hits = 5;

			@Override
			public void execute(CycleEventContainer overload) {
				switch (hits) {
				case 5:
				case 4:
				case 3:
				case 2:
				case 1:
					//c.setAnimation(Animation.create(3170));
					c.startAnimation(3170);
					c.handleHitMask(10, 0, 0);
					c.dealDamage(10);
					c.getPA().refreshSkill(3);
					setOverloadSkills(true);
					break;

				case 0:
					overloadEffect();
					overload.stop();
					break;
				}
				if (hits > 0)
					hits--;

			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	private void overloadEffect() {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			int minutes = 4; // since it takes 1 min to start this, start mins
			// at 4

			@Override
			public void execute(CycleEventContainer effects) {
				if (minutes == 0) {
					c.sendMessage("You feel the effects of the overload wear off...");
					setOverloadSkills(false);
					c.hasOverloadEffect = false;
					effects.stop();
				}
				if (minutes > 0)
					minutes--;
					setOverloadSkills(true);

			}

			@Override
			public void stop() {
				c.hasOverloadEffect = false;

			}
		}, 60);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {

			@Override
			public void execute(CycleEventContainer container) {
				if (c.hasOverloadEffect && c.inWild() && !BountyHunter.safeArea(c)) {
					c.sendMessage("You feel the effects of the overload wear off...");
					setOverloadSkills(false);
					c.hasOverloadEffect = false;
					container.stop();
				}
				if (!c.hasOverloadEffect) // stop this event if the players ovl
					// effect already ended with the
					// first event
					container.stop();

			}

			@Override
			public void stop() {
				c.hasOverloadEffect = false;

			}
		}, 1);
	}

	private void setOverloadSkills(boolean overload) { // overload =
		// playerHasOverloadEffect?
		if (overload) {
			c.playerLevel[0] = (int) (c.getLevelForXP(c.playerXP[0]) + (c
					.getLevelForXP(c.playerXP[0]) * 0.27));
			c.playerLevel[1] = (int) (c.getLevelForXP(c.playerXP[1]) + (c
					.getLevelForXP(c.playerXP[1]) * 0.27));
			c.playerLevel[2] = (int) (c.getLevelForXP(c.playerXP[2]) + (c
					.getLevelForXP(c.playerXP[2]) * 0.27));
			c.playerLevel[4] = (int) (c.getLevelForXP(c.playerXP[4]) + (c
					.getLevelForXP(c.playerXP[4]) * 0.235));
			c.playerLevel[6] = (c.getLevelForXP(c.playerXP[6]) + 7);
		} else {
			c.playerLevel[0] = c.getPA().getLevelForXP(c.playerXP[0]);
			c.playerLevel[1] = c.getPA().getLevelForXP(c.playerXP[1]);
			c.playerLevel[2] = c.getPA().getLevelForXP(c.playerXP[2]);
			c.playerLevel[4] = c.getPA().getLevelForXP(c.playerXP[4]);
			c.playerLevel[6] = c.getPA().getLevelForXP(c.playerXP[6]);
		}
		for (int i = 0; i < 7; i++)
			c.getPA().refreshSkill(i);
	}

	@SuppressWarnings("unused")
	public void doTheBrewZam(int itemId, int replaceItem, int slot) { //by hontiris1
		if (c.duelRule[6]) {
			c.sendMessage("You may not eat in this duel.");
			return;
		}
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		int[] toDecrease = {1,3};

		int[] toIncrease = {0,2,5};
		for (int tD : toDecrease) {
			c.playerLevel[tD] -= getBrewStat(tD, .10);
			if (c.playerLevel[tD] < 0)
				c.playerLevel[tD] = 1;
			c.getPA().refreshSkill(tD);
			c.getPA().setSkillLevel(tD, c.playerLevel[tD], c.playerXP[tD]);
		}
		c.playerLevel[0] += getBrewStat(0, .20);		
		if (c.playerLevel[0] > (c.getLevelForXP(c.playerXP[0])*1.2 + 1)) {
			c.playerLevel[0] = (int)(c.getLevelForXP(c.playerXP[0])*1.2);
		}
		c.playerLevel[2] += getBrewStat(2, .12);		
		if (c.playerLevel[2] > (c.getLevelForXP(c.playerXP[2])*1.2 + 1)) {
			c.playerLevel[2] = (int)(c.getLevelForXP(c.playerXP[2])*1.2);
		}
		c.playerLevel[5] += getBrewStat(5, .10);		
		if (c.playerLevel[5] > (c.getLevelForXP(c.playerXP[5])*1.2 + 1)) {
			c.playerLevel[5] = (int)(c.getLevelForXP(c.playerXP[5])*1.2);
		}
		c.getPA().refreshSkill(0);
		c.getPA().refreshSkill(2);
		c.getPA().refreshSkill(5);
	}

	public void doTheBrewSara(final int itemId, final int replaceItem,
			final int slot) {
		if (c.duelRule[6]) {
			c.sendMessage("You may not eat in this duel.");
			return;
		}
		//c.setAnimation(Animation.create(829));
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		final int[] toDecrease = { 0, 2, 4, 6 };

		for (final int tD : toDecrease) {
			c.playerLevel[tD] -= getBrewStat(tD, .10);
			if (c.playerLevel[tD] < 0) {
				c.playerLevel[tD] = 1;
			}
			c.getPA().refreshSkill(tD);
			c.getPA().setSkillLevel(tD, c.playerLevel[tD], c.playerXP[tD]);
		}
		c.playerLevel[1] += getBrewStat(1, .20);
		if (c.playerLevel[1] > c.getLevelForXP(c.playerXP[1]) * 1.2 + 1) {
			c.playerLevel[1] = (int) (c.getLevelForXP(c.playerXP[1]) * 1.2);
		}
		c.getPA().refreshSkill(1);

		c.playerLevel[3] += getBrewStat(3, .15);
		if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]) * 1.17 + 1) {
			c.playerLevel[3] = (int) (c.getLevelForXP(c.playerXP[3]) * 1.17);
		}
		c.getPA().refreshSkill(3);
		if (c.hasOverloadEffect) {
			setOverloadSkills(true);
		}
	}

	public void drinkAntiPoison(final int itemId, final int replaceItem,
			final int slot, final long delay) {
		//c.setAnimation(Animation.create(829));
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		curePoison(delay);
	}

	public void drinkExtremePotion(final int itemId, final int replaceItem,
			final int slot, final int stat, final boolean sup) {
		//c.setAnimation(Animation.create(829));
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		enchanceStat2(stat, sup);
	}

	public void drinkExtremePrayer(final int itemId, final int replaceItem,
			final int slot, final boolean rest) {
		//c.setAnimation(Animation.create(829));
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		c.prayer += c.getMaxPrayer() * .38;
		if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5])) {
			c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
		}
		if (rest) {
			c.prayer += 1;
		}
		if (c.prayer > c.getMaxPrayer() + 150) {
			c.prayer = c.getMaxPrayer() + 150;
		}
		c.getPA().refreshSkill(5);
		if (rest) {
			restoreStats();
		}
	}

	public void drinkPrayerPot(final int itemId, final int replaceItem,
			final int slot, final boolean rest) {
		//c.setAnimation(Animation.create(829));
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		c.playerLevel[5] += c.getLevelForXP(c.playerXP[5]) * .33;
		if (rest) {
			c.playerLevel[5] += 1;
		}
		if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5])) {
			c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
		}
		if (c.prayer + c.getLevelForXP(c.playerXP[5]) * .33 >= c.maxPrayer()) {
			c.prayer = c.maxPrayer();
		} else {
			c.prayer += (int) (c.getLevelForXP(c.playerXP[5]) * .33);
		}
		c.getPA().refreshSkill(5);
		if (rest) {
			restoreStats();
		}
	}

	public void drinkStatPotion(final int itemId, final int replaceItem,
			final int slot, final int stat, final boolean sup) {
		//c.setAnimation(Animation.create(829));
		c.startAnimation(829);
		c.playerItems[slot] = replaceItem + 1;
		c.getItems().resetItems(3214);
		enchanceStat(stat, sup);
	}

	public void enchanceStat(final int skillID, final boolean sup) {
		c.playerLevel[skillID] += getBoostedStat(skillID, sup);
		c.getPA().refreshSkill(skillID);
	}

	public void enchanceStat2(final int skillID, final boolean sup) {
		c.playerLevel[skillID] += getExtremeStat(skillID, sup);
		c.getPA().refreshSkill(skillID);
	}

	public int getBoostedStat(final int skill, final boolean sup) {
		int increaseBy = 0;
		if (sup) {
			increaseBy = (int) (c.getLevelForXP(c.playerXP[skill]) * .20);
		} else {
			increaseBy = (int) (c.getLevelForXP(c.playerXP[skill]) * .13) + 1;
		}
		if (c.playerLevel[skill] + increaseBy > c
				.getLevelForXP(c.playerXP[skill]) + increaseBy + 1) {
			return c.getLevelForXP(c.playerXP[skill]) + increaseBy
					- c.playerLevel[skill];
		}
		return increaseBy;
	}

	public int getBrewStat(final int skill, final double amount) {
		return (int) (c.getLevelForXP(c.playerXP[skill]) * amount);
	}

	public int getExtremeStat(final int skill, final boolean sup) {
		int increaseBy = 0;
		if (sup) {
			increaseBy = (int) (c.getLevelForXP(c.playerXP[skill]) * .25);
		} else {
			increaseBy = (int) (c.getLevelForXP(c.playerXP[skill]) * .25) + 1;
		}
		if (c.playerLevel[skill] + increaseBy > c
				.getLevelForXP(c.playerXP[skill]) + increaseBy + 1) {
			return c.getLevelForXP(c.playerXP[skill]) + increaseBy
					- c.playerLevel[skill];
		}
		return increaseBy;
	}

	public void handlePotion(final int itemId, final int slot) {
		final String name = c.getItems().getItemName(itemId);
		if (c.duelRule[5]) {
			c.sendMessage("You may not drink potions in this duel.");
			return;
		}
		if (System.currentTimeMillis() - c.potDelay >= 750) {
			c.potDelay = System.currentTimeMillis();
			c.getCombat().resetPlayerAttack();
			c.attackTimer++;
			switch (itemId) {
			case 3040:
				drinkStatPotion(itemId,3042,slot,6,false);
				break;
			case 3042:
				drinkStatPotion(itemId,3044,slot,6,false);
				break;
			case 3044:
				drinkStatPotion(itemId,3046,slot,6,false);
				break;
			case 3046:
				drinkStatPotion(itemId,229,slot,6,false);
				break;
			case 2450:	//zamy brew
				doTheBrewZam(itemId, 189, slot);
				break;
			case 189:
				doTheBrewZam(itemId, 191, slot);
				break;
			case 191:
				doTheBrewZam(itemId, 193, slot);
				break;
			case 193:
				doTheBrewZam(itemId, 229, slot);
				break;
			case 6685: // brews
				doTheBrewSara(itemId, 6687, slot);
				break;
			case 6687:
				doTheBrewSara(itemId, 6689, slot);
				break;
			case 6689:
				doTheBrewSara(itemId, 6691, slot);
				break;
			case 6691:
				doTheBrewSara(itemId, 229, slot);
				break;
			case 2436:
				drinkStatPotion(itemId, 145, slot, 0, true); // sup attack
				break;
			case 145:
				drinkStatPotion(itemId, 147, slot, 0, true);
				break;
			case 147:
				drinkStatPotion(itemId, 149, slot, 0, true);
				break;
			case 149:
				drinkStatPotion(itemId, 229, slot, 0, true);
				break;
			case 2440:
				drinkStatPotion(itemId, 157, slot, 2, true); // sup str
				break;
			case 157:
				drinkStatPotion(itemId, 159, slot, 2, true);
				break;
			case 159:
				drinkStatPotion(itemId, 161, slot, 2, true);
				break;
			case 161:
				drinkStatPotion(itemId, 229, slot, 2, true);
				break;
			case 2444:
				drinkStatPotion(itemId, 169, slot, 4, false); // range pot
				break;
			case 169:
				drinkStatPotion(itemId, 171, slot, 4, false);
				break;
			case 171:
				drinkStatPotion(itemId, 173, slot, 4, false);
				break;
			case 173:
				drinkStatPotion(itemId, 229, slot, 4, false);
				break;
			case 2432:
				drinkStatPotion(itemId, 133, slot, 1, false); // def pot
				break;
			case 133:
				drinkStatPotion(itemId, 135, slot, 1, false);
				break;
			case 135:
				drinkStatPotion(itemId, 137, slot, 1, false);
				break;
			case 137:
				drinkStatPotion(itemId, 229, slot, 1, false);
				break;
			case 113:
				drinkStatPotion(itemId, 115, slot, 2, false); // str pot
				break;
			case 115:
				drinkStatPotion(itemId, 117, slot, 2, false);
				break;
			case 117:
				drinkStatPotion(itemId, 119, slot, 2, false);
				break;
			case 119:
				drinkStatPotion(itemId, 229, slot, 2, false);
				break;
			case 2428:
				drinkStatPotion(itemId, 121, slot, 0, false); // attack pot
				break;
			case 121:
				drinkStatPotion(itemId, 123, slot, 0, false);
				break;
			case 123:
				drinkStatPotion(itemId, 125, slot, 0, false);
				break;
			case 125:
				drinkStatPotion(itemId, 229, slot, 0, false);
				break;
			case 2442:
				drinkStatPotion(itemId, 163, slot, 1, true); // super def pot
				break;
			case 163:
				drinkStatPotion(itemId, 165, slot, 1, true);
				break;
			case 165:
				drinkStatPotion(itemId, 167, slot, 1, true);
				break;
			case 167:
				drinkStatPotion(itemId, 229, slot, 1, true);
				break;
			case 3024:
				drinkPrayerPot(itemId, 3026, slot, true); // sup restore
				break;
			case 3026:
				drinkPrayerPot(itemId, 3028, slot, true);
				break;
			case 3028:
				drinkPrayerPot(itemId, 3030, slot, true);
				break;
			case 3030:
				drinkPrayerPot(itemId, 229, slot, true);
				break;
				// Summoning Potion
			case 12140:
				drinkRestoreSummoning(itemId, 12142, slot);
				break;

			case 12142:
				drinkRestoreSummoning(itemId, 12144, slot);
				break;

			case 12144:
				drinkRestoreSummoning(itemId, 12146, slot);
				break;

			case 12146:
				drinkRestoreSummoning(itemId, 229, slot);
				break;
				// End of Summoning Potion
			case 10925:
				drinkPrayerPot(itemId, 10927, slot, true); // sanfew serums
				curePoison(300000);
				break;
			case 10927:
				drinkPrayerPot(itemId, 10929, slot, true);
				curePoison(300000);
				break;
			case 10929:
				drinkPrayerPot(itemId, 10931, slot, true);
				curePoison(300000);
				break;
			case 10931:
				drinkPrayerPot(itemId, 229, slot, true);
				curePoison(300000);
				break;
			case 2434:
				drinkPrayerPot(itemId, 139, slot, false); // pray pot
				break;
			case 139:
				drinkPrayerPot(itemId, 141, slot, false);
				break;
			case 141:
				drinkPrayerPot(itemId, 143, slot, false);
				break;
			case 143:
				drinkPrayerPot(itemId, 229, slot, false);
				break;
			case 2446:
				drinkAntiPoison(itemId, 175, slot, 30000); // anti poisons
				break;
			case 175:
				drinkAntiPoison(itemId, 177, slot, 30000);
				break;
			case 177:
				drinkAntiPoison(itemId, 179, slot, 30000);
				break;
			case 179:
				drinkAntiPoison(itemId, 229, slot, 30000);
				break;
			case 2448:
				drinkAntiPoison(itemId, 181, slot, 300000); // anti poisons
				break;
			case 181:
				drinkAntiPoison(itemId, 183, slot, 300000);
				break;
			case 183:
				drinkAntiPoison(itemId, 185, slot, 300000);
				break;
			case 185:
				drinkAntiPoison(itemId, 229, slot, 300000);
				break;
				/*
				 * Extreme Potions
				 */
			case 15312: // Extreme Strength
				drinkExtremePotion(itemId, 15313, slot, 2, false);
				break;
			case 15313: // Extreme Strength
				drinkExtremePotion(itemId, 15314, slot, 2, false);
				break;
			case 15314: // Extreme Strength
				drinkExtremePotion(itemId, 15315, slot, 2, false);
				break;
			case 15315: // Extreme Strength
				drinkExtremePotion(itemId, 229, slot, 2, false);
				break;
			case 15332:// overload 4
				if (c.hasOverloadEffect) {
					c.sendMessage("You must wait until the effects wear off.");
					return;
				}
				drinkOverload();
				if (c.playerLevel[3] < 51) {
					return;
				}
				c.getItems().deleteItem(15332, slot, 1);
				c.getItems().addItem(15333, 1);
				break;
			case 15333:// overload 3
				if (c.hasOverloadEffect) {
					c.sendMessage("You must wait until the effects wear off.");
					return;
				}
				drinkOverload();
				if (c.playerLevel[3] < 51) {
					return;
				}
				c.getItems().deleteItem(15333, slot, 1);
				c.getItems().addItem(15334, 1);
				break;
			case 15334:// overload 2
				if (c.hasOverloadEffect) {
					c.sendMessage("You must wait until the effects wear off.");
					return;
				}
				drinkOverload();
				if (c.playerLevel[3] < 51) {
					return;
				}
				c.getItems().deleteItem(15334, slot, 1);
				c.getItems().addItem(15335, 1);
				break;
			case 15335:// overload 1
				if (c.hasOverloadEffect) {
					c.sendMessage("You must wait until the effects wear off.");
					return;
				}
				drinkOverload();
				if (c.playerLevel[3] < 51) {
					return;
				}
				c.getItems().deleteItem(15335, slot, 1);
				c.getItems().addItem(229, 1);
				break;
			case 15308: // Extreme Attack
				drinkExtremePotion(itemId, 15309, slot, 0, false);
				break;
			case 15309: // Extreme Attack
				drinkExtremePotion(itemId, 15310, slot, 0, false);
				break;
			case 15310: // Extreme Attack
				drinkExtremePotion(itemId, 15311, slot, 0, false);
				break;
			case 15311: // Extreme Attack
				drinkExtremePotion(itemId, 229, slot, 0, false);
				break;
			case 15316: // Extreme Defence
				drinkExtremePotion(itemId, 15317, slot, 1, false);
				break;
			case 15317: // Extreme Defence
				drinkExtremePotion(itemId, 15318, slot, 1, false);
				break;
			case 15318: // Extreme Defence
				drinkExtremePotion(itemId, 15319, slot, 1, false);
				break;
			case 15319: // Extreme Defence
				drinkExtremePotion(itemId, 229, slot, 1, false);
				break;
			case 15324: // Extreme Ranging
				drinkExtremePotion(itemId, 15325, slot, 4, false);
				break;
			case 15325: // Extreme Ranging
				drinkExtremePotion(itemId, 15326, slot, 4, false);
				break;
			case 15326: // Extreme Ranging
				drinkExtremePotion(itemId, 15327, slot, 4, false);
				break;
			case 15327: // Extreme Ranging
				drinkExtremePotion(itemId, 229, slot, 4, false);
				break;
			case 15320: // Extreme Magic
				drinkExtremePotion(itemId, 15321, slot, 6, false);
				break;
			case 15321: // Extreme Magic
				drinkExtremePotion(itemId, 15322, slot, 6, false);
				break;
			case 15322: // Extreme Magic
				drinkExtremePotion(itemId, 15323, slot, 6, false);
				break;
			case 15323: // Extreme Magic
				drinkExtremePotion(itemId, 229, slot, 6, false);
				break;
			case 15328: // Super Prayer
				drinkExtremePrayer(itemId, 15329, slot, true);
				break;
			case 15329: // Super Prayer
				drinkExtremePrayer(itemId, 15330, slot, true);
				break;
			case 15330: // Super Prayer
				drinkExtremePrayer(itemId, 15331, slot, true);
				break;
			case 15331: // Super Prayer
				drinkExtremePrayer(itemId, 229, slot, true);
				break;
			case 15300: // Recover Special
				recoverSpecial(itemId, 15301, slot);
				break;
			case 15301: // Recover Special
				recoverSpecial(itemId, 15302, slot);
				break;
			case 15302: // Recover Special
				recoverSpecial(itemId, 15303, slot);
				break;
			case 15303: // Recover Special
				recoverSpecial(itemId, 229, slot);
				break;

				/**
				 * Potion Flasks
				 */
			case 23243:
				drinkPrayerPot(itemId,23245,slot,false);//prayer flask
				break;
			case 23245:
				drinkPrayerPot(itemId,23247,slot,false);
				break;
			case 23247:
				drinkPrayerPot(itemId,23249,slot,false);
				break;
			case 23249:
				drinkPrayerPot(itemId,23251,slot,false);
				break;
			case 23251:
				drinkPrayerPot(itemId,23253,slot,false);
				break;
			case 23253:
				drinkPrayerPot(itemId,733,slot,false);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23255: // super attack flask
				drinkStatPotion(itemId,23257,slot,0,true);
				break;
			case 23257:
				drinkStatPotion(itemId,23259,slot,0,true);
				break;
			case 23259:
				drinkStatPotion(itemId,23261,slot,0,true);
				break;
			case 23261:
				drinkStatPotion(itemId,23263,slot,0,true);
				break;
			case 23263:
				drinkStatPotion(itemId,23265,slot,0,true);
				break;
			case 23265:
				drinkStatPotion(itemId,733,slot,0,true);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23279://sup str flask
				drinkStatPotion(itemId,23281,slot,2,true);
				break;
			case 23281:
				drinkStatPotion(itemId,23283,slot,2,true);
				break;
			case 23283:
				drinkStatPotion(itemId,23285,slot,2,true);
				break;
			case 23285:
				drinkStatPotion(itemId,23287,slot,2,true);
				break;
			case 23287:
				drinkStatPotion(itemId,23289,slot,2,true);
				break;
			case 23289:
				drinkStatPotion(itemId,733,slot,2,true);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23291: //super def
				drinkStatPotion(itemId,23293,slot,1,true);
				break;
			case 23293:
				drinkStatPotion(itemId,23295,slot,1,true);
				break;
			case 23295:
				drinkStatPotion(itemId,23297,slot,1,true);
				break;
			case 23297:
				drinkStatPotion(itemId,23299,slot,1,true);
				break;
			case 23299:
				drinkStatPotion(itemId,23301,slot,1,true);
				break;
			case 23301:
				drinkStatPotion(itemId,733,slot,1,true);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23303: //range flask
				drinkStatPotion(itemId,23305,slot,4,false);
				break;
			case 23305:
				drinkStatPotion(itemId,23307,slot,4,false);
				break;
			case 23307:
				drinkStatPotion(itemId,23309,slot,4,false);
				break;
			case 23309:
				drinkStatPotion(itemId,23311,slot,4,false);
				break;
			case 23311:
				drinkStatPotion(itemId,23313,slot,4,false);
				break;
			case 23313:
				drinkStatPotion(itemId,733,slot,4,false);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23327: //super anti
				drinkAntiPoison(itemId,23329,slot,300000);
				break;
			case 23329:
				drinkAntiPoison(itemId,23331,slot,300000);
				break;
			case 23331:
				drinkAntiPoison(itemId,23333,slot,300000);
				break;
			case 23333:
				drinkAntiPoison(itemId,23335,slot,300000);
				break;
			case 23335:
				drinkAntiPoison(itemId,23337,slot,300000);
				break;
			case 23337:
				drinkAntiPoison(itemId,733,slot,300000);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23351: //brew flasks
				doTheBrewSara(itemId, 23353, slot);
				break;
			case 23353:
				doTheBrewSara(itemId, 23355, slot);
				break;
			case 23355:
				doTheBrewSara(itemId, 23357, slot);
				break;
			case 23357:
				doTheBrewSara(itemId, 23359, slot);
				break;
			case 23359:
				doTheBrewSara(itemId, 23361, slot);
				break;
			case 23361:
				doTheBrewSara(itemId, 733, slot);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23399://super res flasks
				drinkPrayerPot(itemId,23401,slot,true);
				break;
			case 23401:
				drinkPrayerPot(itemId,23403,slot,true);
				break;
			case 23403:
				drinkPrayerPot(itemId,23405,slot,true);
				break;
			case 23405:
				drinkPrayerPot(itemId,23407,slot,true);
				break;
			case 23407:
				drinkPrayerPot(itemId,23409,slot,true);
				break;
			case 23409:
				drinkPrayerPot(itemId,733,slot,true);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23423://mage flasks
				drinkStatPotion(itemId,23425,slot,6,false);
				break;
			case 23425:
				drinkStatPotion(itemId,23427,slot,6,false);
				break;
			case 23427:
				drinkStatPotion(itemId,23429,slot,6,false);
				break;
			case 23429:
				drinkStatPotion(itemId,23431,slot,6,false);
				break;
			case 23431:
				drinkStatPotion(itemId,23433,slot,6,false);
				break;
			case 23433:
				drinkStatPotion(itemId,733,slot,6,false);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23483://spec rec flasks
				recoverSpecial(itemId, 23484, slot);
				break;
			case 23484:
				recoverSpecial(itemId, 23485, slot);
				break;
			case 23485:
				recoverSpecial(itemId, 23486, slot);
				break;
			case 23486:
				recoverSpecial(itemId, 23487, slot);
				break;
			case 23487:
				recoverSpecial(itemId, 23488, slot);
				break;
			case 23488:
				recoverSpecial(itemId, 733, slot);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23495://extreme attk flsks
				drinkExtremePotion(itemId, 23496, slot, 0, false);
				break;
			case 23496:
				drinkExtremePotion(itemId, 23497, slot, 0, false);
				break;
			case 23497:
				drinkExtremePotion(itemId, 23498, slot, 0, false);
				break;
			case 23498:
				drinkExtremePotion(itemId, 23499, slot, 0, false);
				break;
			case 23499:
				drinkExtremePotion(itemId, 23500, slot, 0, false);
				break;
			case 23500:
				drinkExtremePotion(itemId, 733, slot, 0, false);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23501://extreme str flsks
				drinkExtremePotion(itemId, 23502, slot, 2, false);
				break;
			case 23502:
				drinkExtremePotion(itemId, 23503, slot, 2, false);
				break;
			case 23503:
				drinkExtremePotion(itemId, 23504, slot, 2, false);
				break;
			case 23504:
				drinkExtremePotion(itemId, 23505, slot, 2, false);
				break;
			case 23505:
				drinkExtremePotion(itemId, 23506, slot, 2, false);
				break;
			case 23506:
				drinkExtremePotion(itemId, 733, slot, 2, false);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23507://extreme def flsks
				drinkExtremePotion(itemId, 23508, slot, 1, false);
				break;
			case 23508:
				drinkExtremePotion(itemId, 23509, slot, 1, false);
				break;
			case 23509:
				drinkExtremePotion(itemId, 23510, slot, 1, false);
				break;
			case 23510:
				drinkExtremePotion(itemId, 23511, slot, 1, false);
				break;
			case 23511:
				drinkExtremePotion(itemId, 23512, slot, 1, false);
				break;
			case 23512:
				drinkExtremePotion(itemId, 733, slot, 1, false);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23513://extreme magic flsks
				drinkExtremePotion(itemId, 23514, slot, 6, false);
				break;
			case 23514:
				drinkExtremePotion(itemId, 23515, slot, 6, false);
				break;
			case 23515:
				drinkExtremePotion(itemId, 23516, slot, 6, false);
				break;
			case 23516:
				drinkExtremePotion(itemId, 23517, slot, 6, false);
				break;
			case 23517:
				drinkExtremePotion(itemId, 23518, slot, 6, false);
				break;
			case 23518:
				drinkExtremePotion(itemId, 733, slot, 6, false);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23519://extreme range flsks
				drinkExtremePotion(itemId, 23520, slot, 4, false);
				break;
			case 23520:
				drinkExtremePotion(itemId, 23521, slot, 4, false);
				break;
			case 23521:
				drinkExtremePotion(itemId, 23522, slot, 4, false);
				break;
			case 23522:
				drinkExtremePotion(itemId, 23523, slot, 4, false);
				break;
			case 23523:
				drinkExtremePotion(itemId, 23524, slot, 4, false);
				break;
			case 23524:
				drinkExtremePotion(itemId, 733, slot, 4, false);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23525: //Super Prayer
				drinkExtremePrayer(itemId, 23526, slot, true);
				break;
			case 23526: 
				drinkExtremePrayer(itemId, 23527, slot, true);
				break;
			case 23527: 
				drinkExtremePrayer(itemId, 23528, slot, true);
				break;
			case 23528: 
				drinkExtremePrayer(itemId, 23529, slot, true);
				break;
			case 23529: 
				drinkExtremePrayer(itemId, 23530, slot, true);
				break;
			case 23530: 
				drinkExtremePrayer(itemId, 733, slot, true);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			case 23531: //ovl flask
				if (c.hasOverloadEffect) {
					c.sendMessage("You must wait until the effects wear off.");
					return;
				}
				drinkOverload();
				if (c.playerLevel[3] < 51) {
					return;
				}
				c.getItems().deleteItem(23531, slot, 1);
				c.getItems().addItem(23532, 1);
				break;
			case 23532: 
				if (c.hasOverloadEffect) {
					c.sendMessage("You must wait until the effects wear off.");
					return;
				}
				drinkOverload();
				if (c.playerLevel[3] < 51) {
					return;
				}
				c.getItems().deleteItem(23532, slot, 1);
				c.getItems().addItem(23533, 1);
				break;
			case 23533: 
				if (c.hasOverloadEffect) {
					c.sendMessage("You must wait until the effects wear off.");
					return;
				}
				drinkOverload();
				if (c.playerLevel[3] < 51) {
					return;
				}
				c.getItems().deleteItem(23533, slot, 1);
				c.getItems().addItem(23534, 1);
				break;
			case 23534: 
				if (c.hasOverloadEffect) {
					c.sendMessage("You must wait until the effects wear off.");
					return;
				}
				drinkOverload();
				if (c.playerLevel[3] < 51) {
					return;
				}
				c.getItems().deleteItem(23534, slot, 1);
				c.getItems().addItem(23535, 1);
				break;
			case 23535: 
				if (c.hasOverloadEffect) {
					c.sendMessage("You must wait until the effects wear off.");
					return;
				}
				drinkOverload();
				if (c.playerLevel[3] < 51) {
					return;
				}
				c.getItems().deleteItem(23535, slot, 1);
				c.getItems().addItem(23536, 1);
				break;
			case 23536: 
				if (c.hasOverloadEffect) {
					c.sendMessage("You must wait until the effects wear off.");
					return;
				}
				drinkOverload();
				if (c.playerLevel[3] < 51) {
					return;
				}
				c.getItems().deleteItem(23536, slot, 1);
				c.getItems().addItem(733, 1);
				c.sendMessage("Your " + name + " breaks after you drink the last dosage.");
				break;
			}
		}
	}

	public boolean isPotion(int itemId) {
		String name = c.getItems().getItemName(itemId);
		return name.contains("(4)") 
				|| name.contains("(3)") 
				|| name.contains("(2)") 
				|| name.contains("(1)") 
				|| name.contains("(5)") 
				|| name.contains("(6)");	
	}

	public void recoverSpecial(final int itemId, final int replaceItem,
			final int slot) {
		if (c.inWild()) {
			c.sendMessage("You are unable to restore special in the wilderness.");
			return;
		} else if (c.specAmount >= 7.5) {
			c.sendMessage("You are unable to drink the potion as your special is above 75%.");
		} else {
			if (System.currentTimeMillis() - c.restoreDelay >= 60000) {
				c.specAmount += 2.5;
				//c.setAnimation(Animation.create(829));
				c.startAnimation(829);
				// c.sendMessage("As you drink drink the potion, you feel your special attack slightly regenerate.");
				c.playerItems[slot] = replaceItem + 1;
				c.getItems().resetItems(3214);
				c.getItems().updateSpecialBar();
				c.restoreDelay = System.currentTimeMillis();
			} else {
				c.sendMessage("You can only restore your special once every minute.");
			}
		}
	}

	public void drinkRestoreStats(final int itemId, final int replaceItem,
			final int slot, final boolean rest) {
		for (int j = 0; j <= 22; j++) {
			if (j == 5 || j == 3 || j == 22) {
				continue;
			}
			if (c.playerLevel[j] < c.getLevelForXP(c.playerXP[j])) {
				c.playerLevel[j] += c.getLevelForXP(c.playerXP[j]) * .33;
				if (c.playerLevel[j] > c.getLevelForXP(c.playerXP[j])) {
					c.playerLevel[j] = c.getLevelForXP(c.playerXP[j]);
				}
				c.getPA().refreshSkill(j);
				c.getPA().setSkillLevel(j, c.playerLevel[j], c.playerXP[j]);
			}
		}
	}

	public void restoreStats() {
		for (int j = 0; j <= 24; j++) {
			if (j == 5 || j == 3 || j == 22) {
				continue;
			}
			if (c.playerLevel[j] < c.getLevelForXP(c.playerXP[j])) {
				c.playerLevel[j] += c.getLevelForXP(c.playerXP[j]) * .33;
				if (c.playerLevel[j] > c.getLevelForXP(c.playerXP[j])) {
					c.playerLevel[j] = c.getLevelForXP(c.playerXP[j]);
				}
				c.getPA().refreshSkill(j);
				c.getPA().setSkillLevel(j, c.playerLevel[j], c.playerXP[j]);
			}
		}
	}

	public void drinkRestoreSummoning(final int itemId, final int replaceItem,
			final int slot) {
		if (c.playerLevel[22] < c.getLevelForXP(c.playerXP[22])) {
			c.playerLevel[22] += c.getLevelForXP(c.playerXP[22]) * .25;
			if (c.playerLevel[22] > c.getLevelForXP(c.playerXP[22])) {
				c.playerLevel[22] = c.getLevelForXP(c.playerXP[22]);
			}
			//c.setAnimation(Animation.create(829));
			c.startAnimation(829);
			c.playerItems[slot] = replaceItem + 1;
			c.getItems().resetItems(3214);
			c.getPA().refreshSkill(22);
			c.getPA().setSkillLevel(22, c.playerLevel[22], c.playerXP[22]);
			c.getPA()
			.sendFrame126(
					Integer.toString(c.playerLevel[c.summoning])
					+ "/"
					+ Integer.toString(c
							.getLevelForXP(c.playerXP[22])),
							18045);
			c.sendMessage("You feel your summoning points restore.");
		} else {
			c.sendMessage("You cannot restore your summoning anymore.");
		}

	}

}