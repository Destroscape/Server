package game.combat.prayer;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.Config;
import game.entity.player.Player;

public class Curse {

	private Player c;

	public Curse(Player c) {
		this.c = c;
	}

	public void resetCurse() {
		for (int p = 0; p < c.curseActive.length; p++) {
			c.curseActive[p] = false;
			c.getPA().sendFrame36(c.CURSE_GLOW[p], 0);
		}
		c.getPA().sendFrame36(90, 0); // defmellee
		c.headIcon = -1;
		c.getPA().requestUpdates();
	}

	public void strCurse(int i) {
		for (int j = 0; j < str.length; j++) {
			if (str[j] != i) {
				c.curseActive[str[j]] = false;
				c.getPA().sendFrame36(c.CURSE_GLOW[str[j]], 0);
			}
		}
	}

	public void atkCurse(int i) {
		for (int j = 0; j < atk.length; j++) {
			if (atk[j] != i) {
				c.curseActive[atk[j]] = false;
				c.getPA().sendFrame36(c.CURSE_GLOW[atk[j]], 0);
			}
		}
	}

	public void defCurse(int i) {
		for (int j = 0; j < def.length; j++) {
			if (def[j] != i) {
				c.curseActive[def[j]] = false;
				c.getPA().sendFrame36(c.CURSE_GLOW[def[j]], 0);
			}
		}
	}

	public void rngCurse(int i) {
		for (int j = 0; j < rng.length; j++) {
			if (rng[j] != i) {
				c.curseActive[rng[j]] = false;
				c.getPA().sendFrame36(c.CURSE_GLOW[rng[j]], 0);
			}
		}
	}

	public void mgeCurse(int i) {
		for (int j = 0; j < mge.length; j++) {
			if (mge[j] != i) {
				c.curseActive[mge[j]] = false;
				c.getPA().sendFrame36(c.CURSE_GLOW[mge[j]], 0);
			}
		}
	}

	public void sprtCurse(int i) {
		for (int j = 0; j < sprt.length; j++) {
			if (sprt[j] != i) {
				c.curseActive[sprt[j]] = false;
				c.getPA().sendFrame36(c.CURSE_GLOW[sprt[j]], 0);
			}
		}
	}

	public int[] def = { 13, 19 };
	public int[] str = { 14, 19 };
	public int[] atk = { 1, 10, 19 };
	public int[] rng = { 2, 11, 19 };
	public int[] mge = { 3, 12, 19 };
	public int[] sprt = { 4, 16 };// spirit

	public void activateCurse(int i) {
		if (c.duelRule[7]) {
			resetCurse();
			c.sendMessage("Prayer has been disabled in this duel!");
			return;
		}
		if (c.inRFD()) {
			c.sendMessage("You can't use prayers in this minigame.");
			return;
		}
		if (c.playerLevel[1] < 30) {
			c.getPA().sendFrame36(c.CURSE_GLOW[i], 0);
			c.sendMessage("You need 30 Defence to use this prayer.");
			return;
		}
		if (c.playerLevel[5] > 0 || !Config.PRAYER_POINTS_REQUIRED) {
			if (c.getPA().getLevelForXP(c.playerXP[5]) >= c.CURSE_LEVEL_REQUIRED[i]
					|| !Config.PRAYER_LEVEL_REQUIRED) {
				handlePrayerDrain(c);
				boolean headIcon = false;
				switch (i) {

				case 0:// pItem
					if (c.prayerActive[10] == false) {
						c.prayerActive[10] = true;
						c.lastProtItem = System.currentTimeMillis();
						c.startAnimation(12567);
						c.gfx0(2213);
					} else {
						c.prayerActive[10] = false;
					}
					break;

				case 1:
				case 10:
					if (c.curseActive[i] == false) {
						atkCurse(i); //
					}
					break;

				case 2:
				case 11:
					if (c.curseActive[i] == false) {
						rngCurse(i); //
					}
					break;

				case 3:
				case 12:
					if (c.curseActive[i] == false) {
						mgeCurse(i); //
					}
					break;

				case 4:
				case 16:
					if (c.curseActive[i] == false) {
						sprtCurse(i); //
					}
					break;
				case 5:
					// if(!c.zerkOn) {
					// c.zerkOn = true;
					c.startAnimation(12589);
					c.gfx0(2266);
					c.sendMessage("You somehow feel your boosted stats will last longer.");
					// } else {
					// c.zerkOn = false;
					// }
					break;

				case 13:
					if (c.curseActive[i] == false) {
						defCurse(i); //
					}
					break;
				case 14:
					if (c.curseActive[i] == false) {
						strCurse(i); //
					}
					break;

				case 6:
				case 7:
				case 8:
				case 9:
					if (System.currentTimeMillis() - c.stopPrayerDelay < 5000) {
						c.sendMessage("You have been injured and can't use this prayer!");
						c.getPA().sendFrame36(c.CURSE_GLOW[7], 0);
						c.getPA().sendFrame36(c.CURSE_GLOW[8], 0);
						c.getPA().sendFrame36(c.CURSE_GLOW[9], 0);
						return;
					}
					if (i == 7)
						c.protMageDelay = System.currentTimeMillis();
					else if (i == 8)
						c.protRangeDelay = System.currentTimeMillis();
					else if (i == 9)
						c.protMeleeDelay = System.currentTimeMillis();
				case 17:
				case 18:
					headIcon = true;
					for (int p = 6; p < 19; p++) {
						if (i != p && p != 10 && p != 11 && p != 10 && p != 12
								&& p != 13 && p != 14 && p != 15 && p != 16) {
							c.curseActive[p] = false;
							c.getPA().sendFrame36(c.CURSE_GLOW[p], 0);
						}
					}
					break;
					// no idea plus i gotta eat lunch l0l mehh fml
				case 19:
					if (c.curseActive[i] == false) {
						c.startAnimation(12565);
						c.gfx0(2226);
						strCurse(i);
						atkCurse(i);
						defCurse(i);
						mgeCurse(i);
						rngCurse(i);
					}
					break;
				}
				if (!headIcon) {
					if (c.curseActive[i] == false) {
						c.curseActive[i] = true;
						c.getPA().sendFrame36(c.CURSE_GLOW[i], 1);
					} else {
						c.curseActive[i] = false;
						c.getPA().sendFrame36(c.CURSE_GLOW[i], 0);
					}
				} else {
					if (c.curseActive[i] == false) {
						c.curseActive[i] = true;
						c.getPA().sendFrame36(c.CURSE_GLOW[i], 1);
						c.headIcon = c.CURSE_HEAD_ICONS[i];
						c.getPA().requestUpdates();
					} else {
						c.curseActive[i] = false;
						c.getPA().sendFrame36(c.CURSE_GLOW[i], 0);
						c.headIcon = -1;
						c.getPA().requestUpdates();
					}
				}
			} else {
				c.getPA().sendFrame36(c.CURSE_GLOW[i], 0);
				c.getPA().sendFrame126(
						"You need a @blu@Prayer level of "
								+ c.CURSE_LEVEL_REQUIRED[i] + " to use "
								+ c.CURSE_NAME[i] + ".", 357);
				c.getPA().sendFrame126("Click here to continue", 358);
				c.getPA().sendFrame164(356);
			}
		} else {
			resetCurse();
			strCurse(i);
			atkCurse(i);
			defCurse(i);
			mgeCurse(i);
			rngCurse(i);
			c.sendMessage("You have run out of Prayer points!");
		}
	}

	public static double[] PRAYER_DRAIN = { 		0.6, // Protect Item
		2, // Sap Warrior
		2, // Sap Range
		2, // Sap Mage
		2, // Sap Spirit
		1, // Berserker
		2, // Deflect Summoning
		2, // Deflect Mage
		2, // Deflect Range
		2, // Deflect Melee
		2, // Leech Attack
		2, // Leech Range
		2, // Leech Mage
		2, // Leech Defence
		2, // Leech Strength
		2, // Leech Energy
		2, // Leech Special
		2, // Wrath
		4, // Soul Split
		5, // Turmoil
	};

	public static double[] curseData = { 0.3, // Protect Item
		2.5, // Sap Warrior
		2.5, // Sap Ranger
		2.5, // Sap Mage
		2.5, // Sap Spirit
		0.3, // Berserker
		2, // Deflect Summoning
		2, // Deflect Magic
		2, // Deflect Missiles
		2, // Deflect Melee
		1.6, // Leech Attack
		1.6, // Leech Ranged
		1.6, // Leech Magic
		1.6, // Leech Defence
		1.6, // Leech Energy
		1.6, // Leech Special
		0.5, // Wrath
		3, // SS
		3, // Turmoil.
	};

	static double getDrainRate(Player p) {
		double rate = 0;
		for (double index = 0; index < PRAYER_DRAIN.length; index++) {
			if (p.prayerActive[(int) index])
				rate += PRAYER_DRAIN[(int) index];
		}
		for (int j = 0; j < curseData.length; j++) {
			if (p.curseActive[j]) {
				rate += curseData[j];
			}
		}
		return rate;
	}

	public void handlePrayerDrain(final Player p) { //method is never called lol
		if (!p.prayerDraining) {
			p.prayerDraining = true;
			CycleEventHandler.getSingleton().addEvent(p, new CycleEvent() {
				// Go to CycleEvent class
				@Override
				public void execute(CycleEventContainer container) {
					double decreasePrayer = 0.0;
					for (int j = 0; j < PRAYER_DRAIN.length; j++) {
						if (p.curseActive[j]) {
							decreasePrayer += PRAYER_DRAIN[j] / 20;
						}
					}
					if (decreasePrayer > 0) {
						decreasePrayer /= (1 +(0.035 * p.playerBonus[11]));
					}
					if (p.playerEquipment[p.playerAmulet] == 4081) {
						return;
					}
					if (p.prayer - getDrainRate(p) <= 0)
						p.prayer = 0;
					else
						p.prayer -= decreasePrayer; // try now
					//p.sendMessage(p.prayer + "");
					p.getPA().refreshSkill(5);
					if (p.prayer <= 0 || !hasPrayerActive(p))
						container.stop();
				}

				@Override
				public void stop() {
					if (p.prayer == 0) {
						p.sendMessage("You have run out of Prayer points; you can recharge at an altar.");
					}
					resetCurse();
					p.prayerDraining = false;
				}
			}, 1);
		}
	}

	public static boolean hasPrayerActive(Player p) {
		for (int i = 0; i < 46; i++) {
			if (p.prayerActive[i])
				return true;
		}
		for (int i = 0; i < 20; i++) {
			if (p.curseActive[i])
				return true;
		}
		return false;
	}

}