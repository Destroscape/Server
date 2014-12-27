package game.skill.summoning;

import game.entity.player.Player;

public class FamiliarAbilities {

	public static void abilities(final Player c) {
		switch (c.familiarID) {
		case 6825:
			c.playerLevel[c.playerFarming] = c
					.getLevelForXP(c.playerXP[c.playerFarming]) + 1;
			if (c.playerLevel[c.playerFarming] > c
					.getLevelForXP(c.playerXP[c.playerFarming]) + 1) {
				c.playerLevel[c.playerFarming] = c
						.getLevelForXP(c.playerXP[c.playerFarming]) + 1;
			}
			c.getPA().refreshSkill(c.playerFarming);
			break;

		case 6796:
			c.playerLevel[c.playerFishing] = c
					.getLevelForXP(c.playerXP[c.playerFishing]) + 1;
			if (c.playerLevel[c.playerFishing] > c
					.getLevelForXP(c.playerXP[c.playerFishing]) + 1) {
				c.playerLevel[c.playerFishing] = c
						.getLevelForXP(c.playerXP[c.playerFishing]) + 1;
			}
			c.getPA().refreshSkill(c.playerFishing);
			break;

		case 6831:
			c.playerLevel[c.playerMining] = c
					.getLevelForXP(c.playerXP[c.playerMining]) + 1;
			if (c.playerLevel[c.playerMining] > c
					.getLevelForXP(c.playerXP[c.playerMining]) + 1) {
				c.playerLevel[c.playerMining] = c
						.getLevelForXP(c.playerXP[c.playerMining]) + 1;
			}
			c.getPA().refreshSkill(c.playerMining);
			break;

		case 6871:
		case 6827:
			c.playerLevel[c.playerFarming] = c
					.getLevelForXP(c.playerXP[c.playerFarming]) + 2;
			if (c.playerLevel[c.playerFarming] > c
					.getLevelForXP(c.playerXP[c.playerFarming]) + 2) {
				c.playerLevel[c.playerFarming] = c
						.getLevelForXP(c.playerXP[c.playerFarming]) + 2;
			}
			c.getPA().refreshSkill(c.playerFarming);
			break;

		case 6808:
			c.playerLevel[c.playerWoodcutting] = c
					.getLevelForXP(c.playerXP[c.playerWoodcutting]) + 2;
			if (c.playerLevel[c.playerWoodcutting] > c
					.getLevelForXP(c.playerXP[c.playerWoodcutting]) + 2) {
				c.playerLevel[c.playerWoodcutting] = c
						.getLevelForXP(c.playerXP[c.playerWoodcutting]) + 2;
			}
			c.getPA().refreshSkill(c.playerWoodcutting);
			break;

		case 7377:
			c.playerLevel[c.playerFiremaking] = c
					.getLevelForXP(c.playerXP[c.playerFiremaking]) + 3;
			if (c.playerLevel[c.playerFiremaking] > c
					.getLevelForXP(c.playerXP[c.playerFiremaking]) + 3) {
				c.playerLevel[c.playerFiremaking] = c
						.getLevelForXP(c.playerXP[c.playerFiremaking]) + 3;
			}
			c.getPA().refreshSkill(c.playerFiremaking);
			break;

		case 6824:
			c.playerLevel[c.playerThieving] = c
					.getLevelForXP(c.playerXP[c.playerThieving]) + 2;
			if (c.playerLevel[c.playerThieving] > c
					.getLevelForXP(c.playerXP[c.playerThieving]) + 2) {
				c.playerLevel[c.playerThieving] = c
						.getLevelForXP(c.playerXP[c.playerThieving]) + 2;
			}
			c.getPA().refreshSkill(c.playerThieving);
			break;

		case 6991:
			c.playerLevel[c.playerFishing] = c
					.getLevelForXP(c.playerXP[c.playerFishing]) + 3;
			if (c.playerLevel[c.playerFishing] > c
					.getLevelForXP(c.playerXP[c.playerFishing]) + 3) {
				c.playerLevel[c.playerFishing] = c
						.getLevelForXP(c.playerXP[c.playerFishing]) + 3;
			}
			c.getPA().refreshSkill(c.playerFishing);
			break;

		case 7363:
		case 7365:
		case 7337:
			c.playerLevel[c.playerHunter] = c
					.getLevelForXP(c.playerXP[c.playerHunter]) + 5;
			if (c.playerLevel[c.playerHunter] > c
					.getLevelForXP(c.playerXP[c.playerHunter]) + 5) {
				c.playerLevel[c.playerHunter] = c
						.getLevelForXP(c.playerXP[c.playerHunter]) + 5;
			}
			c.getPA().refreshSkill(c.playerHunter);
			break;

		case 6839:
			c.playerLevel[c.playerHunter] = c
					.getLevelForXP(c.playerXP[c.playerHunter]) + 7;
			if (c.playerLevel[c.playerHunter] > c
					.getLevelForXP(c.playerXP[c.playerHunter]) + 7) {
				c.playerLevel[c.playerHunter] = c
						.getLevelForXP(c.playerXP[c.playerHunter]) + 7;
			}
			c.getPA().refreshSkill(c.playerHunter);
			break;

		case 7345:
			c.playerLevel[c.playerMining] = c
					.getLevelForXP(c.playerXP[c.playerMining]) + 7;
			if (c.playerLevel[c.playerMining] > c
					.getLevelForXP(c.playerXP[c.playerMining]) + 7) {
				c.playerLevel[c.playerMining] = c
						.getLevelForXP(c.playerXP[c.playerMining]) + 7;
			}
			c.getPA().refreshSkill(c.playerMining);
			break;

		case 6849:
			c.playerLevel[c.playerFishing] = c
					.getLevelForXP(c.playerXP[c.playerFishing]) + 4;
			if (c.playerLevel[c.playerFishing] > c
					.getLevelForXP(c.playerXP[c.playerFishing]) + 4) {
				c.playerLevel[c.playerFishing] = c
						.getLevelForXP(c.playerXP[c.playerFishing]) + 4;
			}
			c.getPA().refreshSkill(c.playerFishing);
			break;

		case 7341:
			c.playerLevel[c.playerMining] = c
					.getLevelForXP(c.playerXP[c.playerMining]) + 10;
			if (c.playerLevel[c.playerMining] > c
					.getLevelForXP(c.playerXP[c.playerMining]) + 10) {
				c.playerLevel[c.playerMining] = c
						.getLevelForXP(c.playerXP[c.playerMining]) + 10;
			}
			c.getPA().refreshSkill(c.playerMining);
			c.playerLevel[c.playerFiremaking] = c
					.getLevelForXP(c.playerXP[c.playerFiremaking]) + 10;
			if (c.playerLevel[c.playerFiremaking] > c
					.getLevelForXP(c.playerXP[c.playerFiremaking]) + 10) {
				c.playerLevel[c.playerFiremaking] = c
						.getLevelForXP(c.playerXP[c.playerFiremaking]) + 10;
			}
			c.getPA().refreshSkill(c.playerFiremaking);
			break;

		case 7339:
			c.playerLevel[c.playerMining] = c
					.getLevelForXP(c.playerXP[c.playerMining]) + 10;
			if (c.playerLevel[c.playerMining] > c
					.getLevelForXP(c.playerXP[c.playerMining]) + 10) {
				c.playerLevel[c.playerMining] = c
						.getLevelForXP(c.playerXP[c.playerMining]) + 10;
			}
			c.getPA().refreshSkill(c.playerMining);
			break;

		case 6869:
			c.playerLevel[c.playerHunter] = c
					.getLevelForXP(c.playerXP[c.playerHunter]) + 5;
			if (c.playerLevel[c.playerHunter] > c
					.getLevelForXP(c.playerXP[c.playerHunter]) + 5) {
				c.playerLevel[c.playerHunter] = c
						.getLevelForXP(c.playerXP[c.playerHunter]) + 5;
			}
			c.getPA().refreshSkill(c.playerHunter);
			break;
		}
	}

	public static void stopAbilities(final Player c) {
		c.getPA().refreshAll();
		c.sendMessage("The familiars ability will have an effect for a short time, even after dismissal.");
	}
}