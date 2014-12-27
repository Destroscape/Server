package game.combat.prayer;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.entity.player.Player;

public class Prayer {

	public static int THICK_SKIN = 21233;
	public static int BURST_OF_STRENGTH = 21234;
	public static int CLARITY_OF_THOUGHT = 21235;
	public static int SHARP_EYE = 70080;
	public static int MYSTIC_WILL = 70082;
	public static int ROCK_SKIN = 21236;
	public static int SUPERHUMAN_STRENGTH = 21237;
	public static int IMPROVED_REFLEXES = 21238;
	public static int RAPID_RESTORE = 21239;
	public static int RAPID_HEAL = 21240;
	public static int PROTECT_ITEM = 21241;

	public static int[][] formatPrayer = { { 0, 21233 }, { 1, 21234 },
		{ 2, 21235 }, { 3, 70080 }, { 4, 70082 }, { 5, 21236 },
		{ 6, 21237 }, { 7, 21238 }, { 8, 21239 }, { 9, 21240 },
		{ 10, 21241 }, { 11, 70084 }, { 12, 70086 }, { 13, 21242 },
		{ 14, 21243 }, { 15, 21244 }, { 16, 21245 }, { 17, 21246 },
		{ 18, 21247 }, { 19, 70088 }, { 20, 70090 }, { 21, 2171 },
		{ 22, 2172 }, { 23, 2173 }, { 24, 70092 }, { 25, 70094 } };

	public static int HAWK_EYE = 70084;
	public static int MYSTIC_LORE = 70086;
	public static int STEEL_SKIN = 21242;
	public static int ULTIMATE_STRENGTH = 21243;
	public static int INCREDIBLE_REFLEXES = 21244;
	public static int PROTECT_FROM_MAGIC = 21245;
	public static int PROTECT_FROM_MISSLES = 21246;
	public static int PROTECT_FROM_MELEE = 21247;
	public static int EAGLE_EYE = 70088;
	public static int MYSTIC_MIGHT = 70090;
	public static int RETRIBUTION = 2171;
	public static int REDEMPTION = 2172;
	public static int SMITE = 2173;
	public static int CHIVARLY = 70092;
	public static int PIETY = 70094;

	public static int PROTECT_ITEM2 = 87231;
	public static int SAP_WARRIOR = 87233;
	public static int SAP_RANGER = 87235;
	public static int SAP_MAGE = 87237;
	public static int SAP_SPIRIT = 87239;
	public static int BERSERKER = 87241;
	public static int DEFLECT_SUMMONING = 87243;
	public static int DEFLECT_MAGIC = 87245;
	public static int DEFLECT_MISSILES = 87247;
	public static int DEFLECT_MELEE = 87249;
	public static int LEECH_ATTACK = 87251;
	public static int LEECH_RANGED = 87253;
	public static int LEECH_MAGIC = 87255;
	public static int LEECH_DEFENCE = 88001;
	public static int LEECH_STRENGTH = 88003;
	public static int LEECH_ENERGY = 88005;
	public static int LEECH_SPECIAL_ATTACK = 88007;
	public static int WRATH = 88009;
	public static int SOULSPLIT = 88011;
	public static int TURMOIL = 88013;

	static String message = "You have run out of Prayer points; you can recharge at an altar.";

	public static int[][] formatCurses = { { 0, 87231 }, { 1, 87233 },
		{ 2, 87235 }, { 3, 87237 }, { 4, 87239 }, { 5, 87241 },
		{ 6, 87243 }, { 7, 87245 }, { 8, 87247 }, { 9, 87249 },
		{ 10, 87251 }, { 11, 87253 }, { 12, 87255 }, { 13, 88001 },
		{ 14, 88003 }, { 15, 88005 }, { 16, 88007 }, { 17, 88009 },
		{ 18, 88011 }, { 19, 88013 } };

	public static int[] defencePrayers = { THICK_SKIN, ROCK_SKIN, STEEL_SKIN,
		CHIVARLY, PIETY, LEECH_DEFENCE };

	public static int[] strengthPrayers = { BURST_OF_STRENGTH,
		SUPERHUMAN_STRENGTH, ULTIMATE_STRENGTH, CHIVARLY, PIETY,
		LEECH_STRENGTH };

	public static int[] attackPrayers = { CLARITY_OF_THOUGHT,
		IMPROVED_REFLEXES, INCREDIBLE_REFLEXES, CHIVARLY, PIETY,
		SAP_WARRIOR, LEECH_ATTACK };

	public static int[] otherPrayers = { SHARP_EYE, HAWK_EYE, EAGLE_EYE,
		MYSTIC_WILL, MYSTIC_LORE, MYSTIC_MIGHT };

	public static int[] curseRanged = { SAP_RANGER, LEECH_RANGED };

	public static int[] curseMagic = { SAP_MAGE, LEECH_MAGIC };

	public static int[] curseSap = { SAP_SPIRIT, LEECH_SPECIAL_ATTACK };

	public static int[] curseEnergy = { LEECH_ENERGY };
	public static int[] noCombatPrayers = { BERSERKER, PROTECT_ITEM,
		PROTECT_ITEM2 };
	public static int[] headIconPrayers = { PROTECT_FROM_MAGIC,
		PROTECT_FROM_MISSLES, PROTECT_FROM_MELEE, RETRIBUTION, REDEMPTION,
		SMITE, DEFLECT_SUMMONING, DEFLECT_MAGIC, DEFLECT_MELEE,
		DEFLECT_MISSILES, WRATH, SOULSPLIT };

	public static void activatePrayer(Player p, int index, boolean quickPrayers) {
		final PrayerHandler prayer = PrayerHandler.forId(index);
		if (prayer == null) {
			return;
		}
		if (hasRequiredLevel(p, index)
				&& !p.prayerActive[prayer.getPrayerIndex()]) {
			if (p.prayer == 0) {
				p.sendMessage(message);
				deActivatePrayer(p, index);
				return;
			}
			if (quickPrayers)
				p.getPA().sendFrame36(prayer.getGlowIndex(), 1);
			handlePrayerDrain(p); //method is called once here when you active prayer
			handleCurseEffects(p, index);
			checkPrayers(p, prayer.getPrayerIndex(), index);
			for (int length = 0; length < headIconPrayers.length; length++)
				if (index == headIconPrayers[length])
					p.headIcon = prayer.getHeadIcon();
			p.getPA().requestUpdates();
			p.prayerActive[prayer.getPrayerIndex()] = true;
		} else {
			deActivatePrayer(p, index);
		}
	}

	public static void checkPrayers(Player p, int index, int config) {
		switch (index) {
		case 0:
		case 5:
		case 13:
			clearCheckedPrayers(p, config, otherPrayers);
			clearCheckedPrayers(p, config, defencePrayers);
			break;
		case 1:
		case 6:
		case 14:
			clearCheckedPrayers(p, config, otherPrayers);
			clearCheckedPrayers(p, config, strengthPrayers);
			break;
		case 2:
		case 7:
		case 15:
			clearCheckedPrayers(p, config, otherPrayers);
			clearCheckedPrayers(p, config, attackPrayers);
			break;
		case 3:
		case 11:
		case 19:
		case 4:
		case 12:
		case 20:
			clearCheckedPrayers(p, config, strengthPrayers);
			clearCheckedPrayers(p, config, attackPrayers);
			clearCheckedPrayers(p, config, otherPrayers);
			break;
		case 16:
		case 17:
		case 18:
		case 21:
		case 22:
		case 23:
			clearCheckedPrayers(p, config, headIconPrayers);
			break;
		case 24:
		case 25:
		case 45:
			clearCheckedPrayers(p, config, defencePrayers);
			clearCheckedPrayers(p, config, strengthPrayers);
			clearCheckedPrayers(p, config, attackPrayers);
			clearCheckedPrayers(p, config, otherPrayers);
			clearCheckedPrayers(p, config, curseSap);
			clearCheckedPrayers(p, config, curseEnergy);
			clearCheckedPrayers(p, config, curseMagic);
			clearCheckedPrayers(p, config, curseRanged);
			break;
		case 32:
		case 33:
		case 34:
		case 35:
		case 43:
		case 44:
			clearCheckedPrayers(p, config, headIconPrayers);
			break;
		case 27:
		case 36:
			clearCheckedPrayers(p, config, attackPrayers);
			deActivatePrayer(p, 88013);
			break;
		case 28:
		case 37:
			clearCheckedPrayers(p, config, curseRanged);
			deActivatePrayer(p, 88013);
			break;
		case 29:
		case 38:
			clearCheckedPrayers(p, config, curseMagic);
			deActivatePrayer(p, 88013);
			break;

		}
	}

	public static void clearCheckedPrayers(Player p, int index,
			int[] prayerIndex) {
		for (int length = 0; length < prayerIndex.length; length++)
			if (index != prayerIndex[length])
				deActivatePrayer(p, prayerIndex[length]);
	}

	public static void closeAllPrayers(Player p) {
		clearCheckedPrayers(p, 0, defencePrayers);
		clearCheckedPrayers(p, 0, strengthPrayers);
		clearCheckedPrayers(p, 0, attackPrayers);
		clearCheckedPrayers(p, 0, otherPrayers);
		clearCheckedPrayers(p, 0, curseRanged);
		clearCheckedPrayers(p, 0, curseMagic);
		clearCheckedPrayers(p, 0, noCombatPrayers);
		clearCheckedPrayers(p, 0, headIconPrayers);
		clearCheckedPrayers(p, 0, curseSap);
		clearCheckedPrayers(p, 0, curseEnergy);
		deActivatePrayer(p, 88013);
	}

	public static void deActivatePrayer(Player p, int index) {
		PrayerHandler prayer = PrayerHandler.forId(index);
		if (prayer == null) {
			return;
		}
		p.getPA().sendFrame36(prayer.getGlowIndex(), 0);
		for (int length = 0; length < headIconPrayers.length; length++)
			if (index == headIconPrayers[length])
				p.headIcon = -1;
		p.getPA().requestUpdates();
		p.prayerActive[prayer.getPrayerIndex()] = false;
	}

	public static void handleCurseEffects(Player p, int index) {
		switch (index) {
		case 87231:// protect item
			p.startAnimation(12567);
			p.gfx0(2213);
			break;
		case 87241:// berserker
			p.startAnimation(12589);
			p.gfx0(2266);
			break;
		case 88013:// turmoil
			p.startAnimation(12565);
			p.gfx0(2226);
			break;
		}
	}

	public static double[] PRAYER_DRAIN = { 0.5, // Thick Skin.
		0.5, // Burst of Strength.
		0.5, // Clarity of Thought.
		0.5, // Sharp Eye.
		0.5, // Mystic Will.
		1, // Rock Skin.
		1, // SuperHuman Strength.
		1, // Improved Reflexes.
		0.15, // Rapid restore
		0.3, // Rapid Heal.
		0.3, // Protect Items
		1, // Hawk eye.
		1, // Mystic Lore.
		2, // Steel Skin.
		2, // Ultimate Strength.
		2, // Incredible Reflexes.
		2, // Protect from Magic.
		2, // Protect from Missiles.
		2, // Protect from Melee.
		2, // Eagle Eye.
		2, // Mystic Might.
		0.5, // Retribution.
		1, // Redemption.
		2, // Smite
		2, // Chivalry.
		4, // Piety
		0.3, // Protect Item
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
		3, };

	public static double[] curseData = { 0.6, // Protect Item
		2, // Sap Warrior
		2, // Sap Range
		2, // Sap Mage
		2, // Sap Spirit
		1, // Berserker
		4, // Deflect Summoning
		4, // Deflect Mage
		4, // Deflect Range
		4, // Deflect Melee
		4, // Leech Attack
		4, // Leech Range
		4, // Leech Mage
		4, // Leech Defence
		4, // Leech Strength
		4, // Leech Energy
		4, // Leech Special
		4, // Wrath
		8, // Soul Split
		10, // Turmoil
	};

	static double getDrainRate(Player p) {
		double rate = 0;
		for (double index = 0; index < PRAYER_DRAIN.length; index++) {
			if (p.prayerActive[(int) index])
				rate += PRAYER_DRAIN[(int) index];
		}
		for (int j = 0; j < curseData.length; j++) {
			if (p.curseActive[j]) {
				rate += curseData[j] / 20;
			}
		}
		return rate;
	}

	/*public static void sendPrayerDrain(Player p) {
		c.usingPrayer = false;
		double toRemove = 0.0D;
		for (int j = 0; j < PRAYER_DRAIN.length; j++) {
			if (p.prayerActive[j]) {
				toRemove += PRAYER_DRAIN[j] / 20;
				p.usingPrayer = true;
			}
		}
		if (toRemove > 0) {
			toRemove /= (1 +(0.035D * p.playerBonus[11]));
		}
		p.prayer -= toRemove;
		if (p.prayer <= 0) {
			p.prayer = 1.0D + p.prayer;
		}

		//Your p.prayer isn't a double?
	}*/

	public static void handlePrayerDrain(final Player p) {
		if (!p.prayerDraining) {
			p.prayerDraining = true;
			CycleEventHandler.getSingleton().addEvent(p, new CycleEvent() {
				// Go to CycleEvent class
				@Override
				public void execute(CycleEventContainer container) {
					double decreasePrayer = 0.0;
					for (int j = 0; j < PRAYER_DRAIN.length; j++) {
						if (p.prayerActive[j]) {
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
						p.sendMessage(message);
					}
					closeAllPrayers(p);
					p.prayerDraining = false;
				}
			}, 1);
		}
	}

	/**
	 * Prayer draining
	 * 
	 * @author Jeffrey
	 * 
	 */
	// 60 = 100
	// 1.2 = 72

	public static boolean hasPrayerActive(Player p) {
		for (int i = 0; i < 46; i++) {
			if (p.prayerActive[i])
				return true;
		}
		return false;
	}

	static boolean hasPrayerActive(Player p, int index) {
		return p.prayerActive[index];
	}

	public static boolean hasRequiredLevel(Player p, int index) {
		final PrayerHandler prayer = PrayerHandler.forId(index);
		if (prayer == null) {

		} else {
			if (p.getMaxPrayer() >= prayer.getLevelRequired()) {
				return true;
			}
			deActivatePrayer(p, index);
			p.getDH().sendStatement(
					"You need @blu@Prayer@bla@ level of "
							+ prayer.getLevelRequired() / 10 + " to use @blu@"
							+ prayer.getPrayerName() + "");
		}
		return false;
	}

	public static void reFormattedIndex(Player p, int deIndex, boolean curses) {
		if (curses) {
			for (int index = 0; index < formatCurses.length; index++)
				if (deIndex == formatCurses[index][0])
					activatePrayer(p, formatCurses[index][1], true);
		} else {
			for (int index = 0; index < formatPrayer.length; index++)
				if (deIndex == formatPrayer[index][0])
					activatePrayer(p, formatPrayer[index][1], true);
		}
	}
}
