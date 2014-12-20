package game.content;

import engine.util.Misc;
import game.entity.player.Player;
import game.content.achievement.*;

public class AncientEffigies {

	public static final int 
	STARVED_ANCIENT_EFFIGY = 18778,
	NOURISHED_ANCIENT_EFFIGY = 18779, 
	SATED_ANCIENT_EFFIGY = 18780,
	GORGED_ANCIENT_EFFIGY = 18781, 
	DRAGONKIN_LAMP = 18782;

	public static final int[][] EFFIGIES = {
		{ STARVED_ANCIENT_EFFIGY, 80000, NOURISHED_ANCIENT_EFFIGY },
		{ NOURISHED_ANCIENT_EFFIGY, 80000, SATED_ANCIENT_EFFIGY } ,
		{ SATED_ANCIENT_EFFIGY, 80000, GORGED_ANCIENT_EFFIGY } ,
		{ GORGED_ANCIENT_EFFIGY, 80000, DRAGONKIN_LAMP } ,
	};

	public static final String levelName[] = {"Attack", "Defence", "Strength", "Hitpoints", "Ranged", "Prayer", "Magic", "Cooking", "Woodcutting", "Fletching", "Fishing", "Firemaking", "Crafting", "Smithing", "Mining","Herblore", "Agility", "Thieving", "Slayer", "Farming", "Runecrafting","Hunter", "Summoning", "Construction", "Dungeoneering"};

	public static boolean finishedEffigy(Player p) {
		int skill = p.effigySkill;
		int lvl = p.effigySkillLvl;
		if (p.playerLevel[skill] >= lvl) {
			return true;
		}
		p.getPA().sendStatement("You haven't got a high enough "+levelName[p.effigySkill]+" level to do this.");
		p.sendMessage("You need a "+levelName[p.effigySkill]+" level of "+p.effigySkillLvl+" to active this effigy.");
		return false;
	}

	public static void resetPlayerEffigy(Player p) {
		p.effigySkill = 0;
		p.effigySkillLvl = 0;
	}


	public static void handleNewEffigySkill(Player p) {
		p.effigySkillLvl = 60 + Misc.random(39);
		p.effigySkill = Misc.random(11);

	}

	public static void investigateEffigy(Player p) {
		if (!finishedEffigy(p))
			return;
		for (int i = 0; i < EFFIGIES.length; i++) {
			if (p.getItems().playerHasItem(EFFIGIES[i][0])) {
				p.getItems().deleteItem(EFFIGIES[i][0], 1);
				p.getItems().addItem(EFFIGIES[i][2], 1);
				p.startAnimation(14177);
				p.gfx0(2692);
				p.getPA().addSkillXP(EFFIGIES[i][1], p.effigySkill);
				resetPlayerEffigy(p);
				handleNewEffigySkill(p);
				AchievementManager.increase(p, Achievements.EFFIGY_INSPECTOR);
				return;
			}
		}
	}
	
	private static final int[][] DRAGONKIN_INTERFACE = {
		{ 203038, 0, 1 }, { 203039, 2, 2 },
		{ 203040, 4, 3 }, { 203041, 6, 4 },
	};
	
	public static int getButton(final int i) {
		return AncientEffigies.DRAGONKIN_INTERFACE[i][0];
	}
	
	public static int getSkillID(final int i) {
		return AncientEffigies.DRAGONKIN_INTERFACE[i][1];
	}
	
	public static int getDragonkinSkill(final int i) {
		return AncientEffigies.DRAGONKIN_INTERFACE[i][2];
	}

}
