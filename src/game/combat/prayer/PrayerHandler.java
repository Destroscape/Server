package game.combat.prayer;

import java.util.HashMap;
import java.util.Map;

public enum PrayerHandler {
	// actionButtonId, playerLevel required, name, glowIndex, headIcon, prayer
	// Index
	THICK_SKIN(21233, 1, "Thick skin", 83, -1, 0), BURST_OF_STRENGTH(21234, 4,
			"Burst of strength", 84, -1, 1), CLARITY_OF_THOUGHT(21235, 7,
			"Clarity of thought", 85, -1, 2), SHARP_EYE(70080, 8, "Sharp eye",
			601, -1, 3), MYSTIC_WILL(70082, 9, "Mystic will", 602, -1, 4), ROCK_SKIN(
			21236, 10, "Rock skin", 86, -1, 5), SUPERHUMAN_STRENGTH(21237, 13,
			"Superhuman strength", 87, -1, 6), IMPROVED_REFLEXES(21238, 16,
			"Improved reflexes", 88, -1, 7), RAPID_RESTORE(21239, 19,
			"Rapid restore", 89, -1, 8), RAPID_HEAL(21240, 22, "Rapid heal",
			90, -1, 9), PROTECT_ITEM(21241, 25, "Protect item", 91, -1, 10), HAWK_EYE(
			70084, 26, "Hawk eye", 603, -1, 11), MYSTIC_LORE(70086, 27,
			"Mystic lore", 604, -1, 12), STEEL_SKIN(21242, 28, "Steel skin",
			92, -1, 13), ULTIMATE_STRENGTH(21243, 31, "Ultimate strength", 93,
			-1, 14), INCREDIBLE_REFLEXES(21244, 34, "Incredible reflexes", 94,
			-1, 15), PROTECT_FROM_MAGIC(21245, 37, "Protect from magic", 95, 2,
			16), PROTECT_FROM_MISSLES(21246, 40, "Protect from missles", 96, 1,
			17), PROTECT_FROM_MELEE(21247, 43, "Protect from melee", 97, 0, 18), EAGLE_EYE(
			70088, 44, "Eagly eye", 605, -1, 19), MYSTIC_MIGHT(70090, 45,
			"Mystic might", 606, -1, 20), RETRIBUTION(2171, 46, "Retribution",
			98, 3, 21), REDEMPTION(2172, 49, "Redemption", 99, 5, 22), SMITE(
			2173, 52, "Smite", 100, 4, 23), CHIVARLY(70092, 60, "Chivarly",
			607, -1, 24), PIETY(70094, 70, "Piety", 608, -1, 25), PROTECT_ITEM2(
			87231, 50, "Protect item", 610, -1, 26), SAP_WARRIOR(87233, 50,
			"Sap warrior", 611, -1, 27), SAP_RANGER(87235, 52, "Sap ranger",
			612, -1, 28), SAP_MAGE(87237, 54, "Sap mage", 613, -1, 29), SAP_SPIRIT(
			87239, 56, "Sap spirit", 614, -1, 30), BERSERKER(87241, 59,
			"Berserker", 615, -1, 31), DEFLECT_SUMMONING(87243, 62,
			"Deflect summoning", 616, 12, 32), DEFLECT_MAGIC(87245, 65,
			"Deflect magic", 617, 10, 33), DEFLECT_MISSILES(87247, 68,
			"Deflect missiles", 618, 11, 34), DEFLECT_MELEE(87249, 71,
			"Deflect melee", 619, 9, 35), LEECH_ATTACK(87251, 74,
			"Leech attack", 620, -1, 36), LEECH_RANGED(87253, 76,
			"Leech ranged", 621, -1, 37), LEECH_MAGIC(87255, 78, "Leech magic",
			622, -1, 38), LEECH_DEFENCE(88001, 80, "Leech defence", 623, -1, 39), LEECH_STRENGTH(
			88003, 82, "Leech strength", 624, -1, 40), LEECH_ENERGY(88005, 84,
			"Leech energy", 625, -1, 41), LEECH_SPECIAL_ATTACK(88007, 86,
			"Leech special attack", 626, -1, 42), WRATH(88009, 89, "Wrath",
			627, 16, 43), SOULSPLIT(88011, 92, "Soul split", 628, 17, 44), TURMOIL(
			88013, 95, "Turmoil", 629, -1, 45);

	private int buttonId, levelRequired, glowIndex, headIcon, prayerIndex;
	private String prayerName;

	private static Map<Integer, PrayerHandler> prayer = new HashMap<Integer, PrayerHandler>();

	static {
		for (final PrayerHandler index : PrayerHandler.values()) {
			prayer.put(index.buttonId, index);
		}
	}

	public static PrayerHandler forId(int object) {
		return prayer.get(object);
	}

	PrayerHandler(int buttonId, int levelRequired, String prayerName,
			int glowIndex, int headIcon, int prayerIndex) {
		this.buttonId = buttonId;
		this.levelRequired = levelRequired;
		this.prayerName = prayerName;
		this.glowIndex = glowIndex;
		this.headIcon = headIcon;
		this.prayerIndex = prayerIndex;
	}

	public int getButtonId() {
		return buttonId;
	}

	public int getGlowIndex() {
		return glowIndex;
	}

	public int getHeadIcon() {
		return headIcon;
	}

	public int getLevelRequired() {
		return levelRequired;
	}

	public int getPrayerIndex() {
		return prayerIndex;
	}

	public String getPrayerName() {
		return prayerName;
	}
}
