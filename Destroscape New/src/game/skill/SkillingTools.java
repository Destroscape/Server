package game.skill;

import engine.util.Misc;
import game.Config;
import game.entity.player.Player;
import game.skill.firemaking.LogData.logData;

public class SkillingTools extends SkillHandler {

	public final static int infernoAdze = 13661;

	public final boolean getAdzeRequirements(Player p) {
		if (!(p.playerLevel[p.playerWoodcutting] >= 61 && p.playerLevel[p.playerFiremaking] >= 92 && p.playerLevel[p.playerMining] >= 41)) {
			p.sendMessage("You need at least 92 Firemaking, 61 woodcutting and 41 mining to use this axe.");
			return false;
		}	
		return true;
	}

	public void cutAndFire(Player p, int logID)  {
		int burnChance = Misc.random(2);
		if(burnChance == 2) {
			p.gfx(1776,150);

			for (logData l : logData.values()) {
				if (logID == l.getLogId()) {
					p.getPA().addSkillXP(l.getXp()*FIREMAKING_XP, p.playerFiremaking);
					p.sendMessage("Your inferno adze burns the logs.");
					break;
				}
			}
		} else 
			p.getItems().addItem(logID, 1);
	}

	/*private static final int[][] BRAWLING_GLOVES = {
		{13855, 13}, //Smith {13848, 5}, //Pray
		{13857, 7}, //Cooking {13856, 10}, //Fishing
		{13854, 17}, //Thieving {13853, 21}, //Hunter
		{13852, 14}, //Mining {13851, 11}, //FM
		{13850, 8} //WC
	};*/

	/*public static void handleExperienceGain(Player c, int skill, int amount) {
		for (int i = 0; i < BRAWLING_GLOVES.length; i++) {
			if ((c.playerEquipment[c.playerHands] == BRAWLING_GLOVES[i][0]) && (skill == BRAWLING_GLOVES[i][1])) {
				if (c.playerEquipment[c.playerGloves] == 13850) {
					c.getPA().addSkillXP(amount = (amount * 0), i);
				} else {
					c.getPA().addSkillXP(amount = (amount * 0), i);
			}
		}
		}
	}*/
}
