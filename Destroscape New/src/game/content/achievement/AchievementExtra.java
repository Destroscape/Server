package game.content.achievement;

import game.Server;
import game.entity.player.Player;

/**
 * 
 * @author 2012 : 18/08/2011
 * 
 */

public class AchievementExtra {
	
	public static void addExtra(Player c, int achievement) {
		switch (achievement) {
		case Achievements.MUNCHER:
			c.getPA().addSkillXP(10000, 3);
			addItems(c, new int[][] { {386, 50}, {392, 20}, {380, 250}});
			c.sendMessage("You're awarded 50 sharks, 20 manta ray and 250 lobsters!");
			c.sendMessage("And also 10,000 hitpoints experience!");
			break;
		case Achievements.POCKETEER:
			c.getPA().addSkillXP(60000, 17);
			c.sendMessage("You're awarded 60K thieving experience");
			break;
		case Achievements.GWD_WARRIOR:
			c.getPA().addSkillXP(100000, 2);
			c.sendMessage("You're awarded 100K strength experience");
			break;
		case Achievements.GWD_RANGER:
			c.getPA().addSkillXP(100000, 4);
			c.sendMessage("You're awarded 100K range experience");
			break;
		case Achievements.GWD_QUEEN:
			c.getPA().addSkillXP(100000, 5);
			c.sendMessage("You're awarded 100K prayer experience");
			break;
		case Achievements.GWD_DARKER:
			c.getPA().addSkillXP(100000, 1);
			c.sendMessage("You're awarded 100K defence experience");
			break;
		case Achievements.ALCHEMIZER:
			c.getPA().addSkillXP(100000, 6);
			addItems(c, new int[][] { {554, 3000}, {561, 1000}});
			c.sendMessage("You're awarded 3000 fire runes, and 1000 nature runes!");
			c.sendMessage("And also 100K magic experience!");
			break;
		case Achievements.DUNGEONEERER:
			c.dTokens += 100000;
			c.sendMessage("You're awarded 100K Dungeoneering Tokens.");
			break;
			default: c.sendMessage("There are no items or experience reward for this achievement."); break;
		}
	}
	
	private static void addItems(Player c, int[][] items) {
		int itemAmount = items.length;
		for (int i = 0; i < itemAmount; i++) {
			if (c.getItems().freeSlots() < itemAmount) {
				Server.itemHandler.createGroundItem(c, items[i][0], c.absX,
						c.absY, items[i][1], c.playerId);
			} else {
				c.getItems().addItem(items[i][0], items[i][1]);
			}
		}
	}
}