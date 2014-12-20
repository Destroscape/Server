package game.net.packets.clicknpc;

import game.entity.player.Player;
import game.skill.fishing.Fishing;
import game.skill.slayer.Slayer;
import game.skill.thieving.Thieving;
import game.Config;
import engine.util.Misc;

public class SecondClickNpc {

	public static void handleClick(Player c, int npcId) {
		c.clickNpcType = 0;
		c.npcClickIndex = 0;
		c.playerIsFishing = false;
			if (c.playerRights == 3) {
				Misc.println("Npc click 2: " + npcId);
			}
		if (c.fishTimer == 0) {
			if (Fishing.fishingNPC(npcId)) {
				c.fishTimer = 3;
				Fishing.fishingNPC(c, 2, npcId);
				return;
			}
		}
		if (Thieving.pickpocketNPC(c, npcId)) {
			Thieving.attemptToPickpocket(c, npcId);
			return;
		}
		if (c.familiarID > 0) {
			switch (c.npcType) {
			case 6806: // thorny snail
			case 6807:
			case 6994: // spirit kalphite
			case 6995:
			case 6867: // bull ant
			case 6868:
			case 6794: // spirit terrorbird
			case 6795:
			case 6815: // war tortoise
			case 6816:
			case 6873: // pack yak
			case 6874:
			case 6818: // abyssal thing
			case 6819:
			case 6820:
			case 6821:
				if (!c.inDuelArena()) {
					if (c.familiarID == 6820 || c.familiarID == 6818
					 || c.familiarID == 6806 || c.familiarID == 6994
					 || c.familiarID == 6867 || c.familiarID == 6794
					 || c.familiarID == 6815 || c.familiarID == 6873) {
						c.sendMessage("You are now storing items inside your BoB.");
						c.getBOB().store();
					} else {
						c.sendMessage("This is not your npc");
					}
				} else {
					c.sendMessage("You cannot store here");
				}
				break;
			}
		}
		switch (npcId) {
		
		case 2620:
			c.getShops().openShop(25);
			break;

		case 519:
			c.getShops().openShop(11);
			break;
		case 660:
			c.getShops().openShop(26);
			break;
			
		case 905:
			c.getShops().openShop(27);
			break;	
		
		case 580:
			c.getShops().openShop(28);
			break;

		case 5520:
			c.getPA().sendFrame126(""+ Config.VOTE_URL +"", 12000);
			break;

		case 1304:
			c.getDH().sendDialogues(4001, npcId);
		break;
			
		case 212:
			c.getPA().showInterface(24000);
			//c.drawInterface().drawLoyaltyTitles();//TODO
			break;
			
		case 1610:
		case 1611:
			if (c.getItems().playerHasItem(2347, 1)) {
				c.smashedGargoyle = true;
				//c.setAnimation(Animation.create(8989));
				c.startAnimation(8989);
				c.sendMessage("You used your hammer and smashed the gargoyle");
			} else {
				c.sendMessage("You need a hammer to preform this action.");
			}
			break;
		
		case 872:
			c.getDH().sendDialogues(157, npcId);
			break;

		case 3789:
			c.getShops().openShop(18);
			break;
			/**
			 * NPC Second Click Options
			 */
		case 556:
			c.getShops().openShop(7);
			break;

		case 461:
			c.getShops().openShop(8);
			break;

		case 550:
			c.getShops().openShop(9);
			break;

		case 545:
			c.getShops().openShop(10);
			break;

		case 563:
			c.getShops().openShop(19);
			break;

		case 568:
			c.getShops().openShop(20);
			break;

			// Make Over mage
		case 599:
			c.getPA().showInterface(3559);
			c.canChangeAppearance = true;
			break;

		case 548:
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
			break;

		case 494:
		case 495:
		case 7605:
		case 1360:
		case 2619:
			c.getPA().openUpBank(0);
			break;

		case 4084:
			if (c.absY < 5338) {
				if (c.playerLevel[3] >= 70) {
					c.getPA().movePlayer(2885, 5346, 2);
					c.sendMessage("You enter the Zamorak realm of the godwars dungeon.");
				} else {
					c.sendMessage("You must have 70 hitpoints in order to go into this area.");
				}
			} else {
				c.getPA().movePlayer(2885, 5331, 2);
			}
			break;

		case 1597:
			Slayer.giveTask(c);
			break;

		case 3788:
			c.getShops().openVoid();
			break;

		case 557:
			c.getShops().openShop(2);
			break;

		case 6971:
			c.getShops().openShop(4);
			break;

		case 555:
			c.getShops().openShop(1);
			break;

		case 9711:
			c.getShops().openShop(6);
			break;

			/**
			 * Airport NPC's
			 **/

		case 537:
			c.getDH().sendDialogues(52, c.npcType);
			break;

		case 539:
			c.getDH().sendDialogues(55, c.npcType);
			break;

		case 544:
			c.getDH().sendDialogues(58, c.npcType);
			break;

		case 5477:
			c.getDH().sendDialogues(61, c.npcType);
			break;

		}

	}

}
