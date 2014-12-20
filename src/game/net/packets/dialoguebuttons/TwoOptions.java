package game.net.packets.dialoguebuttons;

import game.Config;
import game.content.prestige.Prestige;
import game.entity.player.Player;
import game.minigame.barrows.Dungeon;
import game.minigame.bountyhunter.*;
import game.skill.dungeoneering.Dungeoneering;
import game.skill.summoning.Summoning;
import game.skill.slayer.Slayer;
import game.entity.npc.NPCHandler;

public class TwoOptions {


		private static final int[][] SLAYER_LOCATIONS = {
			{117, 2907, 9724, 0}, //Hill giants
			{1265, 2683, 3725, 0}, //Rock crabs
			{103, 2903, 9849, 0}, //Ghosts *
			{78, 2913, 9832, 0}, //Giant bats *
			{119, 2895, 9769, 0}, //Chaos dwarf
			{18, 3293, 3171, 0}, //Al kharid warrior
			{181, 2932, 9847, 0}, //Chaos druid *
			{1612, 3436, 3560, 0}, //Banshees
			{1618, 3418, 3563, 1}, //Bloodweld
			{941, 2988, 3597, 0}, //Green dragons
			{82, 2932, 9800, 0}, //Lesser demons
			{52, 2916, 9801, 0}, //Baby blue dragons
			{112, 3244, 5557, 0}, //Moss giants
			{1341, 2452, 10149, 0}, //Dagannoths (small) *
			{1610, 3442, 3554, 2}, //Gargoyles
			{1613, 3440, 3566, 2}, //Nechryaels
			{1615, 3417, 3566, 2}, //Abyssal demons
			{55, 2897, 9800, 0}, //Blue dragons
			{84, 2864, 9775, 0}, //Black demons
			{49, 2857, 9840, 0}, //Hell hounds
			{2783, 2907, 9692, 0}, //Dark beasts
			{53, 3205, 3839, 0}, //Red dragons
			{110, 3256, 5540, 0}, //Fire giants
			{5529, 2325, 3799, 0}, //Yaks
			{1648, 3411, 3541, 0}, //Crawling hands 
			{125, 3216, 5442, 0}, //Ice warriors
			{1961, 3161, 5482, 0}, //Mummys
			{1832, 3273, 5557, 0}, //Cave bugs 
			{1624, 3179, 5527, 0}, //Dust devils 
			{6363, 3229, 5543, 0}, //Zamorak warrior 
			{6365, 3239, 5534, 0}, //Zamorak ranger 
			{6367, 3230, 5511, 0}, //Zamorak mager 
			{8349, 2539, 5776, 0}, //Tormented demons 
			{3376, 3313, 5447, 0}, //Mini black dragons 
			{5750, 3271, 5549, 0}, //Giant cave bug 
			{53, 2699, 9510, 0}, //Red dragon
			{54, 2821, 9825, 0}, //Black dragon
			{1590, 2744, 9487, 0}, //Bronze dragon
			{1591, 2737, 9418, 0}, //Iron dragon
			{1592, 2714, 9433, 0}, //Steel dragon
			{1374, 2781, 10062, 0}, //Ganodermic runt
			{13822, 2784, 10100, 0}, //Jadinko male
			{14301, 2772, 10081, 0}, //Glacor
			{9463, 3037, 9543, 0}, //Ice SWyrm
			{9465, 3383, 3015, 0}, //Desert SWyrm
			{9467, 2502, 2893, 0} //Jungle SWyrm
		};

	public static void handleOption1(Player c) {
		if (c.dialogueAction == 1) {
			Dungeon.enterDungeon(c);
		} else if (c.dialogueAction == 2) {
			c.getPA().movePlayer(2507, 4717, 0);
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 5) {
			Slayer.giveTask(c);
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 6) {
		if (c.easierTask == 0) {
			Slayer.getEasyTask(c);
			c.getPA().removeAllWindows();
			c.easierTask = 1;
		} else {
			c.getPA().removeAllWindows();
			c.sendMessage("<col=255>You already have an easy task..");
		}
		} else if (c.dialogueAction == 28) {
		    if(c.slayerPoints >= 20) {
			c.slayerPoints -= 20;
			c.slayerTask = -1;
			c.taskAmount = -1;
			c.taskType = -1;
			c.easierTask = 0;
			c.sendMessage("<col=255>You successfully reset your slayer task.");
			c.getPA().removeAllWindows();
		} else {
			c.sendMessage("<col=255>You need atleast 20 slayer points to reset your slayer task.");
			}
		} else if (c.dialogueAction == 114) {
			Prestige.prestigeSingleRank(c, c.currentSkillInfo);
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 131) {
			if (c.getItems().playerHasItem(995, 10000)) {
				c.getItems().addItem(15707, 1);
				c.getItems().deleteItem(995, 10000);
			} else {
				c.sendMessage("You don't have enough money.");
			}
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 165) {
			c.loyaltyTitle = 0;
			c.sendMessage("Your title has been cleared!");
			c.logout();		
		} else if (c.dialogueAction == 111) { // Saved Teleports Saving
			if (c.playerIndex > 0 || c.npcIndex > 0) {
				c.sendMessage("You can't do this when you're being attacked!");
				return;
			}
			if (c.inMinigame() || c.inMembersArea() || c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL || BountyHunter.inBH(c)) {
				c.sendMessage("You can't save this position.");
				return;
			}
			
			switch (c.questButton) {
			case 1:
				c.teleOneX = c.absX;
				c.teleOneY = c.absY;
				c.teleOneH = c.heightLevel;
				c.savedTele1 = true;
				c.sendMessage("You saved Teleport 1 to <col=255>X:"+ c.absX +", Y:"+ c.absY +"");
				c.getPA().sendFrame126("@whi@~@gre@Teleport 1@whi@~", 16027);
				c.getPA().sendFrame126("@whi@X: @gre@"+ c.teleOneX +" @whi@Y: @gre@"+ c.teleOneY +"", 16028);
				break;

			case 2:
				c.teleTwoX = c.absX;
				c.teleTwoY = c.absY;
				c.teleTwoH = c.heightLevel;
				c.savedTele2 = true;
				c.sendMessage("You saved Teleport 2 to <col=255>X:"+ c.absX +", Y:"+ c.absY +"");
				c.getPA().sendFrame126("@whi@~@gre@Teleport 2@whi@~", 16029);
				c.getPA().sendFrame126("@whi@X: @gre@"+ c.teleTwoX +" @whi@Y: @gre@"+ c.teleTwoY +"", 16030);
				break;

			case 3:
				c.teleThreeX = c.absX;
				c.teleThreeY = c.absY;
				c.teleThreeH = c.heightLevel;
				c.savedTele3 = true;
				c.sendMessage("You saved Teleport 3 to <col=255>X:"+ c.absX +", Y:"+ c.absY +"");
				c.getPA().sendFrame126("@whi@~@gre@Teleport 3@whi@~", 16031);
				c.getPA().sendFrame126("@whi@X: @gre@"+ c.teleThreeX +" @whi@Y: @gre@"+ c.teleThreeY +"", 16032);
				break;

			case 4:
				c.teleFourX = c.absX;
				c.teleFourY = c.absY;
				c.teleFourH = c.heightLevel;
				c.savedTele4 = true;
				c.sendMessage("You saved Teleport 4 to <col=255>X:"+ c.absX +", Y:"+ c.absY +"");
				c.getPA().sendFrame126("@whi@~@gre@Teleport 4@whi@~", 16033);
				c.getPA().sendFrame126("@whi@X: @gre@"+ c.teleFourX +" @whi@Y: @gre@"+ c.teleFourY +"", 16034);
				break;

			case 5:
				c.teleFiveX = c.absX;
				c.teleFiveY = c.absY;
				c.teleFiveH = c.heightLevel;
				c.savedTele5 = true;
				c.sendMessage("You saved Teleport 5 to <col=255>X:"+ c.absX +", Y:"+ c.absY +"");
				c.getPA().sendFrame126("@whi@~@gre@Teleport 5@whi@~", 16035);
				c.getPA().sendFrame126("@whi@X: @gre@"+ c.teleFiveX +" @whi@Y: @gre@"+ c.teleFiveY +"", 16036);
				break;
			}
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 112) { // Saved Teleports Teleporting
			switch (c.questButton) {
			case 1:
				c.getPA().spellTeleport(c.teleOneX, c.teleOneY, c.teleOneH);
				c.sendMessage("You teleported to <col=255>X:"+ c.teleOneX +", Y:"+ c.teleOneY +"");
				break;

			case 2:
				c.getPA().spellTeleport(c.teleTwoX, c.teleTwoY, c.teleTwoH);
				c.sendMessage("You teleported to <col=255>X:"+ c.teleTwoX +", Y:"+ c.teleTwoY +"");
				break;

			case 3:
				c.getPA().spellTeleport(c.teleThreeX, c.teleThreeY, c.teleThreeH);
				c.sendMessage("You teleported to <col=255>X:"+ c.teleThreeX +", Y:"+ c.teleThreeY +"");
				break;

			case 4:
				c.getPA().spellTeleport(c.teleFourX, c.teleFourY, c.teleFourH);
				c.sendMessage("You teleported to <col=255>X:"+ c.teleFourX +", Y:"+ c.teleFourY +"");
				break;

			case 5:
				c.getPA().spellTeleport(c.teleFiveX, c.teleFiveY, c.teleFiveH);
				c.sendMessage("You teleported to <col=255>X:"+ c.teleFiveX +", Y:"+ c.teleFiveY +"");
				break;
			}
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 7) {
			c.getPA().startTeleport(3088, 3933, 0, "modern");
			c.sendMessage("NOTE: You are now in the wilderness...");
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 159) {
				c.getPA().fixAllBarrows();
				c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 13) {
			c.getShops().openShop(2);
		} else if (c.dialogueAction == 3) {
			c.drawInterface().drawToolBelt(c);
		} else if (c.dialogueAction == 9) {
			c.getPA().showInterface(3559);
			c.canChangeAppearance = true;
		} else if (c.dialogueAction == 26) {
			c.sendMessage("My task is: <col=255>" + c.taskAmount + " " + NPCHandler.getNpcListName(c.slayerTask) + ".");
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 27) {
			c.getShops().openShop(3);
		} else if (c.dialogueAction == 144) {
			c.getPA().movePlayer(2962, 9635, 0);
			NPCHandler.spawnNpc(c, 3373, 2962, 9632, 0, 0, 120, 10, 70, 70, true, true);
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 2034) {
			c.getDH().sendDialogues(2018, 949);
		} else if (c.dialogueAction == 3333) {
				c.getPA().sellArtifacts(c);
				c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 3332) {
			c.getShops().openShop(29);
		} else if (c.dialogueAction == 88) {
			/*
			 * if (c.familiarID != -1) { c.getPA().startTeleport(2613, 3347, 0,
			 * "modern"); c.sendMessage(
			 * "Since you have a familiar summoned, you have been teleported outside of home."
			 * ); return; }
			 */
			c.getPA().startTeleport(Config.HOME_X, Config.HOME_Y, 0, "modern");
		} else if (c.dialogueAction == 155) {
			if (c.getItems().playerHasItem(995, 500000)) {
				c.getItems().deleteItem(995, 500000);
				c.getItems().addItem(20767, 1);
				c.getItems().addItem(20768, 1);
			} else {
				c.sendMessage("You don't have enough money to do this.");
			}
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 67) {
			c.getPA().startTeleport(3215,9622,0,"modern");
		} else if (c.dialogueAction == 14) {
			c.getShops().openShop(1);
		} else if (c.dialogueAction == 200) {
			c.getDH().sendDialogues(509, 518);
		} else if (c.dialogueAction == 2000) {
			c.getDH().sendDialogues(2008, 2253);
		} else if (c.dialogueAction == 2001) {
			c.getShops().openShop(6);
		} else if (c.dialogueAction == 3429) {
			c.getDH().sendDialogues(3430, 4515);
		} else if (c.dialogueAction == 3457) {
			c.getDH().sendDialogues(3460, 798);
		} else if (c.dialogueAction == 3472) {
			c.getPA().movePlayer(2851, 3809, 2);
			NPCHandler.spawnNpc(c, 1913, 2842, 3809, 2, 0, 120, 7, 70, 70, true, true);
		} else if (c.dialogueAction == 3475) {
			c.getPA().movePlayer(3554, 3353, 0);
			NPCHandler.spawnNpc(c, 1914, 3555, 3360, 0, 0, 120, 7, 70, 70, true, true);
		} else if (c.dialogueAction == 3478) {
			c.getPA().movePlayer(2698, 9911, 0);
			NPCHandler.spawnNpc(c, 1977, 2697, 9905, 0, 0, 120, 7, 70, 70, true, true);
		} else if (c.dialogueAction == 3481) {
			c.getPA().movePlayer(2739, 5088, 0);
			NPCHandler.spawnNpc(c, 1974, 2741, 5083, 0, 0, 120, 7, 70, 70, true, true);
		} else if (c.dialogueAction == 3499)  {
			c.getPA().removeAllItems();
			c.getPA().removeAllWindows();
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 3501) {
			c.getDH().sendDialogues(3502, 9400);
		} else if (c.dialogueAction == 3504) {
			c.getDH().sendDialogues(3505, 9400);
		} else if (c.dialogueAction == 3515) {
			c.getDH().sendDialogues(3516, 9400);
		} else if (c.dialogueAction == 3524) {
			c.getDH().sendDialogues(3525, 9400);
		} else if (c.dialogueAction == 5401) {
			c.getPA().movePlayer(2720, 2764, 0);
		} else if (c.dialogueAction == 4051) {
			c.wantsPIN = 1;
			c.getPA().removeAllWindows();
			c.sendMessage("@red@Open your bank to set your bank PIN");
			c.setPin = false;
			c.bankPin = "";
		} else if (c.dialogueAction == 4053) {
			c.wantsPIN = 0;
			c.getPA().removeAllWindows();
			c.sendMessage("@red@You have deleted your bank PIN!");
		} else if (c.dialogueAction == 4054) {
			if (c.familiarID > 0) {
			c.getPA().removeAllWindows();
				Summoning.dismissFamiliar(c, true, true);
			} else {
				c.sendMessage("You must have a familiar to use this.");
			c.getPA().removeAllWindows();
			}
		} else if (c.dialogueAction == 5301) {
			c.getDH().sendDialogues(5302, 708);
			c.charmingImpCollect = 1;
			c.charmingImpXP = 0;
		} else if (c.dialogueAction == 3533) {
			if (c.santasHelp == 9) {
			if (c.getItems().freeSlots() <= 2) {
				c.sendMessage("You do not have enough inventory space.");
				return;
			} else {
				c.santasHelp = 10;
				c.getItems().addItem(15422, 1);
				c.getItems().addItem(15423, 1);
				c.getItems().addItem(15425, 1);
				c.getPA().removeAllWindows();
		}
	}
		} else if (c.dialogueAction == 2002) {

			if (c.getPA().getLevelForDungXP(c.playerXP[23]) >= 99) {
				if (c.getItems().playerHasItem(995, 99000)) {
					if (c.getItems().freeSlots() >= 2) {
						c.getItems().deleteItem2(995, 99000);
						c.getItems().addItem(18508, 1);
						c.getItems().addItem(18510, 1);
						c.getPA().removeAllWindows();
					} else {
						c.sendMessage("You must have at least 2 free inventory spaces to buy this.");
						c.getPA().removeAllWindows();
					}
				} else {
					c.getPA().removeAllWindows();
					c.sendMessage("You must have 99K coins to buy this cape.");
				}
			} else {
				c.sendMessage("You must have a dungeoneering level of at least 99 to buy this.");
				c.getPA().removeAllWindows();
			}
		} else if (c.dialogueAction == 302) {
			if (!c.inDung()) {
				Dungeoneering.leaveParty(c);
				c.getPA().removeAllWindows();
			} else {
				Dungeoneering.abandonDung(c);
				c.getPA().removeAllWindows();
			}
		} else if (c.dialogueAction == 135) {
			c.getDH().sendDialogues(136, 0);
		} else if (c.dialogueAction == 137) {
			Prestige.prestigeCombatRank(c);
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 147) {
			c.getDH().sendDialogues(148, 0);
		} else if (c.dialogueAction == 149) {
			Prestige.prestigeMaxRank(c);
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 133) {
			if (c.getItems().playerHasItem(995, 50000)) {
				c.getItems().deleteItem(995, 50000);
				c.getPA().movePlayer(3114, 5528, 0);
			} else {
				c.sendMessage("You don't have the required amount of coins.");
			}
		} else if (c.dialogueAction == 2003) {
			Dungeoneering.abandonDung(c);
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 3001) {
			c.getPA().removeAllWindows();
			c.getShops().openShop(7);
		} else if (c.dialogueAction == 3003) {
			c.getPA().removeAllWindows();
			c.getShops().openShop(8);
		} else if (c.dialogueAction == 3005) {
			c.getPA().removeAllWindows();
			c.getShops().openShop(9);
		} else if (c.dialogueAction == 3007) {
			c.getPA().removeAllWindows();
			c.getShops().openShop(10);
		} else if (c.dialogueAction == 3411) {
			c.getDH().sendDialogues(3412, 945);
		} else if (c.dialogueAction == 140) {
			c.getPA().showInterface(3559);
			c.canChangeAppearance = true;
		}
		//c.getPA().removeAllWindows();
		c.dialogueAction = 0;
	}

	public static void handleOption2(Player c) {
		if (c.dialogueAction == 3499)  {
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 159) {
			c.getPA().resetBarrows();
			c.sendMessage("Your barrows have been reset.");
		} else if (c.dialogueAction == 6) {
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 26) {
		for (int i = 0; i < SLAYER_LOCATIONS.length; i++) {
			if (SLAYER_LOCATIONS[i][0] == c.slayerTask) {
				c.getPA().startTeleport(SLAYER_LOCATIONS[i][1], SLAYER_LOCATIONS[i][2], SLAYER_LOCATIONS[i][3], "modern");
				c.getPA().removeAllWindows();
				}
		}
		} else if (c.dialogueAction == 28) {
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 27) {
			c.getShops().openShop(66);
		} else if (c.dialogueAction == 3429) {
			c.sendMessage("<col=255>Meteora</col> Gives you a bitch slap and walks away..");
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 3457) {
			c.sendMessage("The adventurer agrees with you..");
		} else if (c.dialogueAction == 200) {
			c.getDH().sendDialogues(508, 518);
		} else if (c.dialogueAction == 2004) {
			Dungeoneering.abandonDung(c);
		} else if (c.dialogueAction == 2002) {
			if (c.getPA().getLevelForDungXP(c.playerXP[23]) == 120) {
				if (c.getItems().playerHasItem(995, 500000)) {
					if (c.getItems().freeSlots() >= 1) {
						c.getItems().deleteItem2(995, 500000);
						c.getItems().addItem(19710, 1);
						c.getPA().removeAllWindows();
					} else {
						c.sendMessage("You must have at least 1 free inventory space to buy this.");
						c.getPA().removeAllWindows();
					}
				} else {
					c.getPA().removeAllWindows();
					c.sendMessage("You must have 500K coins to buy this cape.");
				}
			} else {
				c.sendMessage("You must have a dungeoneering level of 120 to buy this.");
				c.getPA().removeAllWindows();
			}
		} else if (c.dialogueAction == 3) {
			c.getDH().sendDialogues(4, 0);
		} else if (c.dialogueAction == 114) {
			Prestige.prestigeSinglePoints(c, c.currentSkillInfo);
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 88) {
			c.getPA().startTeleport(2392, 9907, 0, "modern");
		} else if (c.dialogueAction == 3533) {
			if (c.santasHelp == 9) {
			if (c.getItems().freeSlots() < 1) {
				c.sendMessage("You do not have enough inventory space.");
				return;
			} else {
				c.santasHelp = 10;
				c.getItems().addItem(11949, 1);
			c.getPA().removeAllWindows();
		}
	}
		} else if (c.dialogueAction == 112) { // Saved Teleports Deleting
			if (c.playerIndex > 0 || c.npcIndex > 0) {
				c.sendMessage("You can't do this when you're being attacked!");
				return;
			}
			switch (c.questButton) {
			case 1:
				c.teleOneX = Config.HOME_X;
				c.teleOneY = Config.HOME_Y;
				c.savedTele1 = false;
				c.getPA().sendFrame126("@whi@~@red@Teleport 1@whi@~", 16027);
				c.getPA().sendFrame126("@whi@X: @red@Not Set @whi@Y: @red@Not Set", 16028);
				break;

			case 2:
				c.teleTwoX = Config.HOME_X;
				c.teleTwoY = Config.HOME_Y;
				c.savedTele2 = false;
				c.getPA().sendFrame126("@whi@~@red@Teleport 2@whi@~", 16029);
				c.getPA().sendFrame126("@whi@X: @red@Not Set @whi@Y: @red@Not Set", 16030);
				break;

			case 3:
				c.teleThreeX = Config.HOME_X;
				c.teleThreeY = Config.HOME_Y;
				c.savedTele3 = false;
				c.getPA().sendFrame126("@whi@~@red@Teleport 3@whi@~", 16031);
				c.getPA().sendFrame126("@whi@X: @red@Not Set @whi@Y: @red@Not Set", 16032);
				break;

			case 4:
				c.teleFourX = Config.HOME_X;
				c.teleFourY = Config.HOME_Y;
				c.savedTele4 = false;
				c.getPA().sendFrame126("@whi@~@red@Teleport 4@whi@~", 16033);
				c.getPA().sendFrame126("@whi@X: @red@Not Set @whi@Y: @red@Not Set", 16034);
				break;

			case 5:
				c.teleFiveX = Config.HOME_X;
				c.teleFiveY = Config.HOME_Y;
				c.savedTele5 = false;
				c.getPA().sendFrame126("@whi@~@red@Teleport 5@whi@~", 16035);
				c.getPA().sendFrame126("@whi@X: @red@Not Set @whi@Y: @red@Not Set", 16036);
				break;
			}
			c.sendMessage("You successfully deleted your saved teleport.");
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 140) {
			for (int i = 0; i < 24; i++) {
				if(c.playerXP[i] <= c.getPA().getXPForLevel(10)) {
					c.sendMessage("You need level 10 in ALL LEVELS to view the lowest shop");
					return;
				}
			}
			for (int i = 0; i < 24; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(10) && c.playerXP[i] <= c.getPA().getXPForLevel(20)) {
					c.getShops().openShop(91);
					return;
				}
			}
			for (int i = 0; i < 24; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(20) && c.playerXP[i] <= c.getPA().getXPForLevel(30)) {
					c.getShops().openShop(92);
					return;
				}
			}
			for (int i = 0; i < 24; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(30) && c.playerXP[i] <= c.getPA().getXPForLevel(40)) {
					c.getShops().openShop(93);
					return;
				}
			}
			for (int i = 0; i < 24; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(40) && c.playerXP[i] <= c.getPA().getXPForLevel(50)) {
					c.getShops().openShop(94);
					return;
				}
			}
			for (int i = 0; i < 24; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(50) && c.playerXP[i] <= c.getPA().getXPForLevel(60)) {
					c.getShops().openShop(95);
					return;
				}
			}
			for (int i = 0; i < 24; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(60) && c.playerXP[i] <= c.getPA().getXPForLevel(70)) {
					c.getShops().openShop(96);
					return;
				}
			}
			for (int i = 0; i < 24; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(70) && c.playerXP[i] <= c.getPA().getXPForLevel(80)) {
					c.getShops().openShop(97);
					return;
				}
			}
			for (int i = 0; i < 24; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(80) && c.playerXP[i] <= c.getPA().getXPForLevel(90)) {
					c.getShops().openShop(98);
					return;
				}
			}
			for (int i = 0; i < 24; i++) {
				if(c.playerXP[i] >= c.getPA().getXPForLevel(90) && c.playerXP[i] >= c.getPA().getXPForLevel(99)) {
					c.getShops().openShop(89);
					return;
				}
			}
		} else if (c.dialogueAction == 137) {
			Prestige.prestigeCombatPoints(c);
		} else if (c.dialogueAction == 149) {
			Prestige.prestigeMaxPoints(c);
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 3332) {
			c.getDH().sendDialogues(3333, 6537);
		} else if (c.dialogueAction == 4051) {
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 4053) {
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 4054) {
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 5301) {
			c.getDH().sendDialogues(5304, 708); //5303
		} else if (c.dialogueAction == 5401) {
			c.getPA().removeAllWindows();
		} else {
			c.dialogueAction = 0;
			c.getPA().removeAllWindows();
		}
	}
}
