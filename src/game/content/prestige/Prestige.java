package game.content.prestige;

import engine.util.Misc;
import game.entity.player.Player;

public class Prestige {

	static int exp = 125000;

	public static int[][] dataNov = { 
		{ 2, 0 }, // Attack
		{ 2, 1 }, // Defence
		{ 2, 2 }, // Strength
		{ 2, 3 }, // Hitpoints
		{ 2, 4 }, // Range
		{ 2, 5 }, // Prayer
		{ 2, 6 }, // Magic
		{ 2, 7 }, // Cooking
		{ 2, 8 }, // Woodcutting
		{ 2, 9 }, // Fletching
		{ 2, 10 }, // Fishing
		{ 1, 11 }, // Firemaking
		{ 2, 12 }, // Crafting
		{ 2, 13 }, // Smithing
		{ 2, 14 }, // Mining
		{ 2, 15 }, // Herblore
		{ 2, 16 }, // Agility
		{ 2, 17 }, // Thieving
		{ 10, 18 }, // Slayer
		{ 2, 19 }, // Farming
		{ 2, 20 }, // Runecrafting
		{ 2, 21 }, // Hunting
		{ 2, 22 }, // Summoning
		{ 10, 23 }, // Dungeoneering
	};

	public static int[][] dataExp = { 
		{ 4, 0 }, // Attack
		{ 4, 1 }, // Defence
		{ 4, 2 }, // Strength
		{ 4, 3 }, // Hitpoints
		{ 4, 4 }, // Range
		{ 6, 5 }, // Prayer
		{ 5, 6 }, // Magic
		{ 6, 7 }, // Cooking
		{ 6, 8 }, // Woodcutting
		{ 6, 9 }, // Fletching
		{ 7, 10 }, // Fishing
		{ 6, 11 }, // Firemaking
		{ 6, 12 }, // Crafting
		{ 7, 13 }, // Smithing
		{ 7, 14 }, // Mining
		{ 6, 15 }, // Herblore
		{ 6, 16 }, // Agility
		{ 7, 17 }, // Thieving
		{ 20, 18 }, // Slayer
		{ 8, 19 }, // Farming
		{ 12, 20 }, // Runecrafting
		{ 8, 21 }, // Hunting
		{ 10, 22 }, // Summoning
		{ 20, 23 }, // Dungeoneering
	};

	public static int[][] dataLeg = { 
		{ 5, 0 }, // Attack
		{ 5, 1 }, // Defence
		{ 5, 2 }, // Strength
		{ 5, 3 }, // Hitpoints
		{ 5, 4 }, // Range
		{ 5, 5 }, // Prayer
		{ 5, 6 }, // Magic
		{ 5, 7 }, // Cooking
		{ 6, 8 }, // Woodcutting
		{ 8, 9 }, // Fletching
		{ 8, 10 }, // Fishing
		{ 6, 11 }, // Firemaking
		{ 7, 12 }, // Crafting
		{ 7, 13 }, // Smithing
		{ 7, 14 }, // Mining
		{ 7, 15 }, // Herblore
		{ 7, 16 }, // Agility
		{ 7, 17 }, // Thieving
		{ 10, 18 }, // Slayer
		{ 7, 19 }, // Farming
		{ 7, 20 }, // Runecrafting
		{ 7, 21 }, // Hunting
		{ 7, 22 }, // Summoning
		{ 10, 23 }, // Dungeoneering
	};

	public static boolean wearingItem(Player c) {
		for (int j = 0; j < c.playerEquipment.length; j++) {
			if (c.playerEquipment[j] > 0) {
				c.sendMessage("Please remove ALL worn items to Prestige.");
				return true;
			}
		}
		return false;
	}

	public static void prestigeSingleRank(Player c,int skillId) {
		if(c.prestige)
			return;
		int skillLvl = c.getPA().getLevelForXP(c.playerXP[skillId]);
		if(wearingItem(c))
			return;
		c.prestige = true;
		if(skillLvl == 99) {
			for(int i = 0;i < dataNov.length;i++) {
				if(dataNov[i][1] == skillId){
					int level = 1;
					c.skillsPrestiged++;
					c.playerXP[skillId] = c.getPA().getXPForLevel(level);
					c.playerLevel[skillId] = c.getPA().getLevelForXP(c.playerXP[skillId]);
					c.getPA().addSkillXP(exp, 24);
					c.getPA().refreshSkill(skillId);
					c.getPA().globalMessage(255, ""+Misc.optimizeText(c.playerName)+" has just prestiged "+c.getPA().getSkillName(skillId)+"! They've prestiged "+c.skillsPrestiged+" skills!");
					c.sendMessage("Your prestige rank is now "+c.skillsPrestiged+"");
					c.saveGame();
				} else if (skillLvl <= 99) {
					c.sendMessage("You need to be 99 "+c.getPA().getSkillName(skillId )+" to prestige.");
				}
			}
		}
		if(c.currentSkillInfo >= 0)
			c.drawInterface().drawSkillInfo(c, c.currentSkillInfo);
			//DrawInterface.drawPrestigePanel(c);
			c.prestige = false;
	}
	
	public static void prestigeSinglePoints(Player c,int skillId) {
		if(c.prestige)
			return;
		int skillLvl = c.getPA().getLevelForXP(c.playerXP[skillId]);
		if(wearingItem(c))
			return;
		c.prestige = true;
		if(skillLvl == 99){
			for(int i = 0;i < dataNov.length;i++){
				if(dataNov[i][1] == skillId){
					int level = 1;
		if (c.xpTitle.equalsIgnoreCase("novice")) {
				c.prestigePoints += dataNov[i][0];
				c.playerXP[skillId] = c.getPA().getXPForLevel(level);
				c.playerLevel[skillId] = c.getPA().getLevelForXP(c.playerXP[skillId]);
				c.getPA().addSkillXP(exp, 24);
				c.getPA().refreshSkill(skillId);
				c.getPA().globalMessage(255, "[N]"+Misc.optimizeText(c.playerName)+" has just prestiged "+c.getPA().getSkillName(skillId)+" and obtained "+dataNov[i][0]+" Prestige Points!");
				c.sendMessage("You recieve "+dataNov[i][0]+" Prestige Points!");
				c.saveGame();
		}
		if (c.xpTitle.equalsIgnoreCase("none")) {
				c.prestigePoints += dataExp[i][0];
				c.playerXP[skillId] = c.getPA().getXPForLevel(level);
				c.playerLevel[skillId] = c.getPA().getLevelForXP(c.playerXP[skillId]);
				c.getPA().addSkillXP(exp, 24);
				c.getPA().refreshSkill(skillId);
				c.getPA().globalMessage(255, ""+Misc.optimizeText(c.playerName)+" has just prestiged "+c.getPA().getSkillName(skillId)+" and obtained "+dataExp[i][0]+" Prestige Points!");
				c.sendMessage("You recieve "+dataExp[i][0]+" Prestige Points!");
				c.saveGame();
		}
		if (c.xpTitle.equalsIgnoreCase("legend")) {
				c.prestigePoints += dataLeg[i][0];
				c.playerXP[skillId] = c.getPA().getXPForLevel(level);
				c.playerLevel[skillId] = c.getPA().getLevelForXP(c.playerXP[skillId]);
				c.getPA().addSkillXP(exp, 24);
				c.getPA().refreshSkill(skillId);
				c.getPA().globalMessage(255, "[L]"+Misc.optimizeText(c.playerName)+" has just prestiged "+c.getPA().getSkillName(skillId)+" and obtained "+dataLeg[i][0]+" Prestige Points!");
				c.sendMessage("You recieve "+dataLeg[i][0]+" Prestige Points!");
				c.saveGame();
		}
				} else if (skillLvl <= 99) {
					c.sendMessage("You need to be 99 "+c.getPA().getSkillName(skillId )+" to prestige.");
				}
			}
		}
		if(c.currentSkillInfo >= 0)
			c.drawInterface().drawSkillInfo(c, c.currentSkillInfo);
			//DrawInterface.drawPrestigePanel(c);
			c.prestige = false;
	}
	
	public static void prestigeCombatRank(Player c) {
		if(c.prestige)
			return;
		if(wearingItem(c))
			return;
		c.prestige = true;
		if(hasCombat(c)){
			for(int i = 0;i < 7;i++){
				int skillId = dataNov[i][1];
				int level = 1;
				if(skillId == c.playerHitpoints)
					level = 10;
				if(skillId == c.playerPrayer)
					continue;
				c.playerXP[skillId] = c.getPA().getXPForLevel(level);
				c.playerLevel[skillId] = c.getPA().getLevelForXP(c.playerXP[skillId]);
				c.getPA().refreshSkill(skillId);
			}
			c.skillsPrestiged += 7;
			c.prestigePoints += 25;
			c.getPA().globalMessage(255, ""+Misc.optimizeText(c.playerName)+" has just prestiged All Combat Levels and advanced 7 Prestige Ranks");
			c.sendMessage("You advanced 7 prestige ranks and currently at prestige rank "+ c.skillsPrestiged +"");
			c.saveGame();
		} else {
			c.sendMessage("You need Attack, Strength, Defence, Range, Mage, Prayer, and Hitpoints Mastered.");
		}
		if(c.currentSkillInfo >= 0)
			c.drawInterface().drawSkillInfo(c, c.currentSkillInfo);
			//DrawInterface.drawPrestigePanel(c);
			c.prestige = false;
	}

	public static void prestigeCombatPoints(Player c) {
		if(c.prestige)
			return;
		if(wearingItem(c))
			return;
		c.prestige = true;
		if(hasCombat(c)) {
			for(int i = 0;i < 7;i++){
				int skillId = dataNov[i][1];
				int level = 1;
				if(skillId == c.playerHitpoints)
					level = 10;
				if(skillId == c.playerPrayer)
					continue;
				c.playerXP[skillId] = c.getPA().getXPForLevel(level);
				c.playerLevel[skillId] = c.getPA().getLevelForXP(c.playerXP[skillId]);
				c.getPA().refreshSkill(skillId);
			}
			c.prestigePoints += 25;
			c.getPA().globalMessage(255, ""+Misc.optimizeText(c.playerName)+" has just prestiged All Combat Levels and received 25 Prestige Points");
			c.sendMessage("You recieve 25 Prestige Points!");
			c.saveGame();
		} else {
			c.sendMessage("You need Attack, Strength, Defence, Range, Mage, Prayer, and Hitpoints Mastered.");
		}
		if(c.currentSkillInfo >= 0)
			c.drawInterface().drawSkillInfo(c, c.currentSkillInfo);
			//DrawInterface.drawPrestigePanel(c);
			c.prestige = false;
	}

	public static boolean hasCombat(Player c) {
		int count = 0;
		for(int i = 0;i < 7;i++) {
			if(i == 5)
				continue;
			int skillId = dataExp[i][1];
			int skillLvl = c.getPA().getLevelForXP(c.playerXP[skillId]);
			if(skillLvl == 99)
				count++;
		}
		return (count == 6);
	}

	public static void prestigeMaxPoints(Player c) {
		if(c.prestige)
			return;
		@SuppressWarnings("unused")
		int count = 0;
		if(!c.hasSpace()){
			c.sendMessage("You dont have enough space in your inventory.");
			return;
		}
		if(wearingItem(c))
			return;
		c.prestige = true;
		if(hasMax(c)){
			for(int i = 0;i < 24;i++){
				int skillId = dataNov[i][1];
				int level = 1;
				if(skillId == c.playerHitpoints)
					level = 10;
				c.playerXP[skillId] = c.getPA().getXPForLevel(level);
				c.playerLevel[skillId] = c.getPA().getLevelForXP(c.playerXP[skillId]);
				c.getPA().refreshSkill(skillId);
			}
			c.prestigePoints += 190;
			c.getItems().addItem(995, 10000000);
			c.getPA().globalMessage(255, ""+Misc.optimizeText(c.playerName)+" has just prestiged Every Level! And obtained 190 Prestige Points!");
			c.sendMessage("You recieve 190 Prestige Points and 10 million gp for Prestiging!");
			c.saveGame();
		} else {
			c.sendMessage("You need every skill mastered to max prestige.");
		}
		if(c.currentSkillInfo >= 0)
			c.drawInterface().drawSkillInfo(c, c.currentSkillInfo);
			//DrawInterface.drawPrestigePanel(c);
			c.prestige = false;
	}
	
	public static void prestigeMaxRank(Player c) {
		if(c.prestige)
			return;
		@SuppressWarnings("unused")
		int count = 0;
		if(!c.hasSpace()){
			c.sendMessage("You dont have enough space in your inventory.");
			return;
		}
		if(wearingItem(c))
			return;
		c.prestige = true;
		if(hasMax(c)) {
			for(int i = 0;i < 24;i++){
				int skillId = dataNov[i][1];
				int level = 1;
				if(skillId == c.playerHitpoints)
					level = 10;
				c.playerXP[skillId] = c.getPA().getXPForLevel(level);
				c.playerLevel[skillId] = c.getPA().getLevelForXP(c.playerXP[skillId]);
				c.getPA().refreshSkill(skillId);
			}
			c.skillsPrestiged += 23;
			c.getItems().addItem(995, 10000000);
			c.getPA().globalMessage(255, ""+Misc.optimizeText(c.playerName)+" has just prestiged Every Level! They've Prestiged "+ c.skillsPrestiged +" skills!");
			c.sendMessage("You've advanced 23 Prestige Ranks");
			c.saveGame();
		} else {
			c.sendMessage("You need every skill mastered to max prestige.");
		}
		if(c.currentSkillInfo >= 0)
			c.drawInterface().drawSkillInfo(c, c.currentSkillInfo);
			//DrawInterface.drawPrestigePanel(c);
			c.prestige = false;
	}

	public static boolean hasMax(Player c) {
		for(int i = 0; i < 24; i++) {
			if (c.getPA().getLevelForXP(c.playerXP[i]) != 99) {
				return false;
			}
		}
		return true;
	}

	public static void buyTokens(Player c,int option) {
		int[][] tokenData = {
				//Option,Amount,Price
				{1,5,10},	
				{2,20,35},
				{3,50,85},
				{4,100,150},
		};
		for(int i = 0;i < tokenData.length;i++) {
			if(tokenData[i][0] == option){
				if(c.prestigePoints >= tokenData[i][2]) {
					c.getPA().removeAllWindows();
					c.prestigePoints -= tokenData[i][2];
					c.prestigeTokens += tokenData[i][1];
					c.sendMessage("You have purchased "+tokenData[i][1]+" Prestige Tokens for "+tokenData[i][2]+" Prestige Points!");
					c.saveGame();
				}
			}
		}
		//DrawInterface.drawPrestigePanel(c);
	}
}