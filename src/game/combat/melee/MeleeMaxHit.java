package game.combat.melee;

import game.entity.player.Player;

public class MeleeMaxHit {

	@SuppressWarnings("unused")
	public static double calculateBaseDamage(Player c, boolean special) {
		double boost = 2.0;
		double maxHit = 0;
		int attBonus = c.playerBonus[10]; //attack
		int attack = c.playerLevel[0]; //attack
		int strBonus = c.playerBonus[10]; //strength
		int strength = c.playerLevel[2]; //strength
		int defBonus = c.playerBonus[10]; //defense
		int defense = c.playerLevel[1]; //defense
		int ranBonus = c.playerBonus[10]; //range
		int range = c.playerLevel[4]; //range
		int magBonus = c.playerBonus[10]; //mage
		int magic = c.playerLevel[6]; //mage
		int attlvlForXP = c.getLevelForXP(c.playerXP[0]); //attack
		int strlvlForXP = c.getLevelForXP(c.playerXP[2]); //strength
		int deflvlForXP = c.getLevelForXP(c.playerXP[1]); //defense
		int ranlvlForXP = c.getLevelForXP(c.playerXP[4]); //range
		int maglvlForXP = c.getLevelForXP(c.playerXP[6]); //mage
		int lvlForXP = c.getLevelForXP(c.playerXP[2]);
		/*if (c.prestigeMaxHit == true && c.prestigeAccuracy == false && c.prestigeRangeAccuracy == false 
				&& c.prestigeMagicAccuracy == false && c.prestigeDefence == false) {
			if (c.playerLevel[24] <= 10 && c.playerLevel[24] >= 1) {
				strength += (int)(strlvlForXP * .02 + c.getstr);
			} else if (c.playerLevel[24] <= 20 && c.playerLevel[24] >= 10) {
				strength += (int)(strlvlForXP * .04 + c.getstr);
			} else if (c.playerLevel[24] <= 30 && c.playerLevel[24] >= 20) {
				strength += (int)(strlvlForXP * .06 + c.getstr);
			} else if (c.playerLevel [24] <= 40 && c.playerLevel[24] >= 30) {
				strength += (int)(strlvlForXP * .08 + c.getstr);
			} else if (c.playerLevel [24] <= 50 && c.playerLevel[24] >= 40) {
				strength += (int)(strlvlForXP * .1 + c.getstr);
			} else if (c.playerLevel [24] <= 60 && c.playerLevel[24] >= 50) {
				strength += (int)(strlvlForXP * .12 + c.getstr);
			} else if (c.playerLevel[24] <= 70 && c.playerLevel[24] >= 60) {
				strength += (int)(strlvlForXP * .14 + c.getstr);
			} else if (c.playerLevel[24] <= 80 && c.playerLevel[24] >= 70) {
				strength += (int)(strlvlForXP * .16 + c.getstr);
			} else if (c.playerLevel[24] <= 90 && c.playerLevel[24] >= 80) {
				strength += (int)(strlvlForXP * .18 + c.getstr);
			} else if (c.playerLevel[24] <= 100 && c.playerLevel[24] >= 90) {
				strength += (int)(strlvlForXP * .20 + c.getstr);
			}
		}*/
		if(c.curseActive[10]) { // Leech Attack
			attack += (int)(attlvlForXP * .10 + c.getatt);
		}	
		if(c.curseActive[13]) { // Leech Defense
			defense += (int)(deflvlForXP * .10 + c.getdef);
		}	
		if(c.curseActive[14]) { // Leech Strength
			strength += (int)(strlvlForXP * .10 + c.getstr);
		}	
		if(c.curseActive[11]) { // Leech Ranged
			range += (int)(ranlvlForXP * .10 + c.getran);
		}	
		if(c.curseActive[12]) { // Leech Magic
			magic += (int)(maglvlForXP * .10 + c.getmag);
		}	
		if(c.prayerActive[1]) {
			strength += (int)(lvlForXP * .05);
		} else
			if(c.prayerActive[6]) {
				strength += (int)(lvlForXP * .1);
			} else
				if(c.prayerActive[14]) {
					strength += (int)(lvlForXP * .15);
				} else
					if(c.prayerActive[24]) {
						strength += (int)(lvlForXP * .18);
					} else
						if(c.prayerActive[25]) {
							strength += (int)(lvlForXP * .23);
						}
		if(c.curseActive[19]) { // turmoil
			strength += (int)(strlvlForXP * .23 + c.getstr);
			attack += (int)(attlvlForXP * .15 + c.getatt);
			defense += (int)(deflvlForXP * .23 + c.getdef);
		}
		if(c.playerEquipment[c.playerHat] == 2526 && c.playerEquipment[c.playerChest] == 2520 && c.playerEquipment[c.playerLegs] == 2522) {	
			maxHit += (maxHit * 10 / 100);
		}
		maxHit += 1.05D + (double)(strBonus * strength) * 0.00175D;
		maxHit += (double)strength * 0.11D;
		if(isWearingDh(c)) {
			double dh = strBonus /= 100; 
			boost *= 0.45 + (dh - 1.05);
			return (int) (((c.getPA().getLevelForXP(c.playerXP[3]) + strength) / 2 - c.playerLevel[3] / 2 ) * boost);
		}
		if (c.specDamage > 1)
			maxHit = (int)(maxHit * c.specDamage);
		if (maxHit < 0)
			maxHit = 1;
		if (c.fullVoidMelee())
			maxHit = (int)(maxHit * 1.22);
		if (c.playerEquipment[c.playerAmulet] == 11128 && c.playerEquipment[c.playerWeapon] == 6528) {
			maxHit *= 1.30;
		}
		return (int)Math.floor(maxHit);
	}
	
	public static boolean isWearingDh(Player c) {
		return(c.playerEquipment[c.playerWeapon] == 4718 && c.playerEquipment[c.playerHat] == 4716 && c.playerEquipment[c.playerChest] == 4720 && c.playerEquipment[c.playerLegs] == 4722);
	}

	public static double getEffectiveStr(Player c) {
		return ((c.playerLevel[2]) * getPrayerStr(c)) + getStyleBonus(c);
	}

	public static int getStyleBonus(Player c) {
		return c.fightMode == 2 ? 3 : c.fightMode == 3 ? 1
				: c.fightMode == 4 ? 3 : 0;
	}

	public static double getPrayerStr(Player c) {
		if (c.prayerActive[1])
			return 1.05;
		else if (c.prayerActive[6])
			return 1.1;
		else if (c.prayerActive[14])
			return 1.15;
		else if (c.prayerActive[24])
			return 1.18;
		else if (c.prayerActive[25])
			return 1.23;
		else if (c.curseActive[19])
			return 1.23;
		return 1;
	}

	public static final double[][] special = { { 5698, 1.1 }, { 5680, 1.1 },
		{ 1231, 1.15 }, { 1215, 1.15 }, { 5680, 1.15 }, { 5698, 1.15 },
		{ 3204, 1.10 }, { 1305, 1.15 }, { 1434, 1.45 }, { 11694, 1.375 },
		{ 11696, 1.21 }, { 11698, 1.10 }, { 11700, 1.10 }, { 861, 1.1 },
		{ 4151, 1.1 }, { 10887, 1.2933 }, };

	public static double getSpecialStr(Player c) {
		for (double[] slot : special) {
			if (c.playerEquipment[3] == slot[0])
				return slot[1];
		}
		return 1;
	}

	public static final int[] obsidianWeapons = { 746, 747, 6523, 6525, 6526,
		6527, 6528 };

	public static boolean hasObsidianEffect(Player c) {
		if (c.playerEquipment[2] != 11128)
			return false;

		for (int weapon : obsidianWeapons) {
			if (c.playerEquipment[3] == weapon)
				return true;
		}
		return false;
	}

	public static boolean hasVoid(Player c) {
		return c.playerEquipment[c.playerHat] == 11665
				&& c.playerEquipment[c.playerLegs] == 8840
				&& c.playerEquipment[c.playerChest] == 8839
				&& c.playerEquipment[c.playerHands] == 8842;
	}

	public static int bestMeleeDef(Player c)
	{
		if(c.playerBonus[5] > c.playerBonus[6] && c.playerBonus[5] > c.playerBonus[7])
			return 5;
		if(c.playerBonus[6] > c.playerBonus[5] && c.playerBonus[6] > c.playerBonus[7])
			return 6;
		return c.playerBonus[7] <= c.playerBonus[5] || c.playerBonus[7] <= c.playerBonus[6] ? 5 : 7;
	}

	public static int calculateMeleeDefence(Player c) {
		int defenceLevel = c.playerLevel[1];
		int i = c.playerBonus[bestMeleeDef(c)];
		if (c.prayerActive[0]) {
			defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.05;
		} else if (c.prayerActive[5]) {
			defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.1;
		} else if (c.prayerActive[13]) {
			defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.15;
		} else if (c.prayerActive[24]) {
			defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.2;
		} else if (c.prayerActive[25]) {
			defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.25;
		} else if (c.curseActive[19]) {
			defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.15 + c.getdef;
		}
		return (int)(defenceLevel + (defenceLevel * 0.15) + (i + i * 0.05));
	}

	public static int calculateMeleeAttack(Player c) {
		int attackLevel = c.playerLevel[0];
		//2, 5, 11, 18, 19
		if (c.prayerActive[2]) {
			attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.05;
		} else if (c.prayerActive[7]) {
			attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.1;
		} else if (c.prayerActive[15]) {
			attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.15;
		} else if (c.prayerActive[24]) {
			attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.18;
		} else if (c.prayerActive[25]) {
			attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.22;
		} else if (c.curseActive[19]) { // turmoil
			attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.15 + c.getatt;
		}
		if (c.fullVoidMelee())
			attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.1;
		attackLevel *= c.specAccuracy;
		//c.sendMessage("Attack: " + (attackLevel + (c.playerBonus[bestMeleeAtk()] * 2)));
		int i = c.playerBonus[bestMeleeAtk(c)];
		i += c.bonusAttack;
		if (c.playerEquipment[c.playerAmulet] == 11128 && c.playerEquipment[c.playerWeapon] == 6528) {
			i *= 1.30;
		}
		return (int)(attackLevel + (attackLevel * 0.15) + (i + i * 0.05));
	}

	public static int bestMeleeAtk(Player c)
	{
		if(c.playerBonus[0] > c.playerBonus[1] && c.playerBonus[0] > c.playerBonus[2])
			return 0;
		if(c.playerBonus[1] > c.playerBonus[0] && c.playerBonus[1] > c.playerBonus[2])
			return 1;
		return c.playerBonus[2] <= c.playerBonus[1] || c.playerBonus[2] <= c.playerBonus[0] ? 0 : 2;
	}
}