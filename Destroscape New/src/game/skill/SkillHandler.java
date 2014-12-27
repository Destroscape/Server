package game.skill;

import java.util.Random;

import game.entity.player.Player;

public class SkillHandler {

	public static final int WOODCUTTING_XP = 40;
	public static final int FIREMAKING_XP = 40;
	public static final int COOKING_XP = 40;
	public static final int MINING_XP = 40;
	public static final int FLETCHING_XP = 40;
	public static final int HERBLORE_XP = 40;
	public static final int THIEVING_XP = 37;
	public static final int RUNECRAFTING_XP = 40;
	public static final int AGILITY_XP = 45;
	public static final int CRAFTING_XP = 50;
	public static final int PRAYER_XP = 50;
	public static final int SUMMONING_XP = 70;
	public static final int DUNGEONEERING_XP = 50;
	public static final int HUNTER_XP = 65;
	public static final int SMITHING_XP = 55;
	public static final int FARMING_XP = 45;
	public static final int SLAYER_XP = 40;
	public static final int FISHING_XP = 60;

	public static final boolean view190 = true;

	public static void resetPlayerSkillVariables(Player c) {
		if (c.playerIsMining) {
			for (int i = 0; i < 2; i++) {
				c.miningProp[i] = -1;
			}
		} else if (c.playerIsCooking) {
			for (int i2 = 0; i2 < 6; i2++) {
				c.cookingProp[i2] = -1;

			}
		}
	}

	public static boolean[] isSkilling = new boolean[25];

	public static long lastSkillingAction;

	public static void resetSkillingVariables() {
		for (int skill = 0; skill < isSkilling.length; skill++) {
			isSkilling[skill] = false;
		}
	}

	public static String getLine(Player c) {
		return c.below459 ? ("\\n\\n\\n\\n") : ("\\n\\n\\n\\n\\n");
	}

	public static boolean noInventorySpace(Player c, String skill) {
		if (c.getItems().freeSlots() == 0) {
			c.sendMessage("You haven't got enough inventory space to continue "
					+ skill + "!");
			c.getPA().sendStatement(
					"You haven't got enough inventory space to continue "
							+ skill + "!");
			return false;
		}
		return true;
	}

	public static boolean hasRequiredLevel(final Player c, int id, int lvlReq,
			String skill, String event) {
		if (c.playerLevel[id] < lvlReq) {
			c.sendMessage("You dont't have a high enough " + skill
					+ " level to " + event + "");
			c.sendMessage("You at least need the " + skill + " level of "
					+ lvlReq + ".");
			c.getPA().sendStatement(
					"You haven't got high enough " + skill + " level to "
							+ event + "!");
			return false;
		}
		return true;
	}

	public static void deleteTime(Player c) {
		c.doAmount--;
	}

	public static boolean skillCheck(int level, int levelRequired, int itemBonus) {
		double chance = 0.0;
		double baseChance = levelRequired < 11 ? 15 : levelRequired < 51 ? 10 : 5;//Math.pow(10d-levelRequired/10d, 2d)/2d;
		chance = baseChance + ((level - levelRequired) / 2d) + (itemBonus / 10d);
		return chance >= (new Random().nextDouble() * 100.0);
	}
}