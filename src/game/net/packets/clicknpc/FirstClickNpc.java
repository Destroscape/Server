package game.net.packets.clicknpc;

import game.content.MoneyBank;
import game.entity.npc.NPC;
import game.entity.player.Player;
import game.skill.crafting.Tanning;
import game.skill.fishing.Fishing;
import game.skill.summoning.FamiliarInteraction;
import engine.util.Misc;

public class FirstClickNpc {
	
	static NPC n;

	public static void handleClick(Player c, int npcId) {
		c.clickNpcType = 0;
		c.npcClickIndex = 0;
		c.playerIsFishing = false;
			if (c.playerRights == 3) {
				Misc.println("Npc click 1: " + npcId);
			}
		if (c.fishTimer == 0) {
			if (Fishing.fishingNPC(npcId)) {
				c.fishTimer = 3;
				Fishing.fishingNPC(c, 1, npcId);
				return;
			}
		}
		if (c.playerRights == 3) {
			c.sendMessage("NpcId: " + npcId);
		}
		switch (npcId) {

		case 1469:
			c.getDH().sendDialogues(5400, npcId);
			break;
		
		case 884:
			c.getDH().sendDialogues(5115, npcId);
			break;

		case 6537:
			c.getDH().sendDialogues(3332, npcId);
			c.nextChat = 3333;
			break;
			
		case 3334:
			c.getDH().sendDialogues(3334, npcId);
			c.dialogueAction = 3334;
			break;

		case 580: //Skilltasks
			c.getDH().sendDialogues(5100, npcId);
			break;
//START OF SANTAS HELPER

		case 9400: //Santas little helper
		if (c.santasHelp == 0) {
			c.getDH().sendDialogues(3494, npcId);
		}
		if (c.santasHelp == 1) {
			c.getDH().sendDialogues(3513, npcId);
		}
		if (c.santasHelp == 2) {
			c.getDH().sendDialogues(3515, npcId);
		}
		if (c.santasHelp == 3) {
			c.getDH().sendDialogues(3522, npcId);
		}
		if (c.santasHelp == 4) {
			c.getDH().sendDialogues(3524, npcId);
		}
		if (c.santasHelp == 8) {
			c.getDH().sendDialogues(3531, npcId);
		}
		if (c.santasHelp == 9) {
			c.getDH().sendDialogues(3533, npcId);
		}
			break;
		case 7955: //Santas little helper
		if (c.santasHelp == 0) {
			c.getDH().sendDialogues(3507, npcId);
		} else {
			c.sendMessage("He has nothing more to say to you.");
		}
			break;
		case 13656: //Santas little helper
		if (c.santasHelp == 2) {
			c.getDH().sendDialogues(3518, npcId);
		} else {
			c.sendMessage("He has nothing more to say to you.");
		}
			break;
		case 8668:
		if (c.santasHelp <= 7) {
			c.getDH().sendDialogues(3528, npcId);
		} else {
			c.sendMessage("He has nothing more to say to you.");
		}
			break;

//END OF SANTAS HELPER

		case 5520:
			c.getShops().openShop(19);
			break;

		case 1289:
			c.getShops().openShop(26);
			break;			
		
		case 212:
			c.getShops().openShop(22);
			break;

		case 8452:
			c.getDH().sendDialogues(27, npcId);
			break;
		
		case 660:
			//c.getShops().openShop(26); //TODO
			c.sendMessage("Coming Soon.");
			break;
		
		case 1152: 
			c.getDH().sendDialogues(159, npcId);
			break;
		
		case 251: //Achievement Shop
			c.getShops().openShop(30);
			break;
		
		case 872: //Mage of Mastery
			c.getDH().sendDialogues(156, npcId);
			break;

		case 3705:
			if (c.playerLevel[0] >= 99 && c.playerLevel[1] >= 99
			&& c.playerLevel[2] >= 99 && c.playerLevel[3] >= 99
			&& c.playerLevel[4] >= 99 && c.playerLevel[5] >= 99
			&& c.playerLevel[6] >= 99 && c.hasKilledMax == false) {
				c.getDH().sendDialogues(142, npcId);
			} else if (c.playerLevel[0] >= 99 && c.playerLevel[1] >= 99
					&& c.playerLevel[2] >= 99 && c.playerLevel[3] >= 99
					&& c.playerLevel[4] >= 99 && c.playerLevel[5] >= 99
					&& c.playerLevel[6] >= 99 && c.hasKilledMax == true) {
				c.getDH().sendDialogues(154, npcId);
			} else {
				c.getDH().sendDialogues(145, npcId);
				c.sendMessage("Come back when you've reached 99 in the following skills:");
				c.sendMessage("@red@Attack - Strength - Defence - Range - Prayer - Magic - Hitpoints");
			}
			break;

		case 6138:
			c.getDH().sendDialogues(201, npcId);
			break;

		case 548:
			c.getDH().sendDialogues(138, npcId);
			break;

		case 798:
		if (c.lunarDiplomacy == 6) {
			c.getDH().sendDialogues(3455, npcId);
		}
			break;
			
		case 4515: //Meteora
		if (c.lunarDiplomacy == 1) {
			c.getDH().sendDialogues(3426, npcId);
		}
		if (c.lunarDiplomacy == 4 && c.getItems().playerHasItem(21403, 1)) {	
			c.getDH().sendDialogues(3448, npcId);
		}	
		if (c.lunarDiplomacy == 5) {
			c.getDH().sendDialogues(3451, npcId);
		}
		if (c.lunarDiplomacy == 8) {
			c.getDH().sendDialogues(3463, npcId);
		}
		break;
		case 367: //Chemist
		if (c.lunarDiplomacy == 2) { 
			c.getDH().sendDialogues(3439, npcId);
		}	
		if (c.lunarDiplomacy == 3 && c.getItems().playerHasItem(592, 2) && c.getItems().playerHasItem(3024, 1) && c.getItems().playerHasItem(12155, 1)) {	
			c.getDH().sendDialogues(3445, npcId);
		}	
		break;	

		case 1918:
		if (c.DT == 1) { 
			c.getDH().sendDialogues(3465, npcId);
		}
		if (c.DT == 2) { 
			c.getDH().sendDialogues(3473, npcId);
		}
		if (c.DT == 3) { 
			c.getDH().sendDialogues(3476, npcId);
		}
		if (c.DT == 4) { 
			c.getDH().sendDialogues(3479, npcId);
		}
		if (c.DT == 5) { 
			c.getDH().sendDialogues(3492, npcId);
		}
		break;
		case 1862:
			c.getPA().showInterface(26400);
			break;

			/**
			 * NPC First Click Options
			 */

		case 882:
			c.getDH().sendDialogues(66, npcId);
			break;

		case 4085:
			if (c.playerRights > 0) {
				c.getPA().startTeleport(2122, 4913, 0, "ancient");
			} else {
				c.sendMessage("You must be a donator to access this area.");
			}
			break;

		case 9462:
		case 9466:
		case 9464:
			if (c.theStryke == 1) {
				c.theStryke = 2;
				c.sendMessage("@blu@You see a glimt of the Strykewyrm.");
				c.sendMessage("@blu@Your questbook has updated.");	
				c.getPA().sendStatement("You have identified the Strykewyrm.");
			} else if (c.theStryke > 1 && c.theStryke < 3) { 
				c.sendMessage("You feel scared and don't dare to touch that.");	
				return;
			}
				NPC.appendSmound(c);
			break;

		case 945:
			c.getDH().sendDialogues(3410, 945);
			break;
		case 519:
			c.getDH().sendDialogues(3400, 519);
			break;

		case 11583:
		case 2566:
			c.getShops().openSkillCape();
			break;

		case 568:
			c.getDH().sendDialogues(500000001, 568);
			break;

		case 563:
			c.getDH().sendDialogues(500000000, 563);
			break;

		case 2258:
			c.getPA().startTeleport(3040, 4842, 0, "modern");
			break;

		case 5778:// bartak
			c.getDH().sendDialogues(41340, 5778);
			break;

		case 804:
			Tanning.sendTanningInterface(c);
			break;

			/**
			 * A Good Start Quest
			 */

		case 518:
			if (c.aGoodStartStatus == 0) {
				c.getDH().sendDialogues(513, 518);
			} else if (c.aGoodStartStatus > 0 && c.aGoodStartStatus < 20) {
				c.getDH().sendDialogues(529, 518);
			} else if (c.aGoodStartStatus == 20) {
				c.getDH().sendDialogues(530, 518);
			}

			break;

		case 949:
			c.getDH().sendDialogues(2018, 949);
			break;

		case 556:
			c.getDH().sendDialogues(3000, 556);
			break;

		case 541:
			c.getDH().sendDialogues(3002, 541);
			break;

		case 550:
			c.getDH().sendDialogues(3004, 550);
			break;

		case 545:
			c.getDH().sendDialogues(3006, 545);
			break;

		case 9711:
			c.getDH().sendDialogues(2012, 9711);
			break;

		case 9713:
			if (!c.getItems().playerHasItem(15707) !=c.getItems().hasBankItem(15707)) {
				c.getDH().sendDialogues(2014, npcId);
			} else {
				c.getDH().sendDialogues(130, npcId);
			}
			break;

		case 2253:

			break;

		case 6971:
			c.getDH().sendDialogues(70, 6971);
			break;

		case 0:
			if (c.aGoodStartStatus == 1) {
				c.getDH().sendDialogues(515, 0);
			} else if (c.aGoodStartStatus == 5) {
				if (c.getItems().playerHasItem(2859, 3)) {
					c.getDH().sendDialogues(523, 0);
				} else {
					c.getDH().sendDialogues(527, 0);
				}
			} else if (c.aGoodStartStatus == 20) {
				c.getDH().sendDialogues(528, 0);
			}
			break;

			// Gnome Glider - Operators
		case 170:
			c.getPA().showInterface(802);
			break;

		case 494: 
		case 495:
		case 496:
		case 5912:
		case 5258:
		case 5260:
		case 7605:
		case 1360:
		case 2619:
			//MoneyBank.openMoneyBank(c);
			break;

			/**
			 * Airport NPC's
			 */

		case 537:
			c.getDH().sendDialogues(50, c.npcType);
			break;

		case 539:
			c.getDH().sendDialogues(53, c.npcType);
			break;

		case 544:
			c.getDH().sendDialogues(56, c.npcType);
			break;

		case 5477:
			c.getDH().sendDialogues(59, c.npcType);
			break;

		case 6829:
		case 6825:
		case 6841:
		case 6806:
		case 6796:
		case 7331:
		case 6831:
		case 6837:
		case 7361:
		case 6847:
		case 6994:
		case 6871:
		case 7353:
		case 6835:
		case 6845:
		case 6808:
		case 7370:
		case 7367:
		case 7351:
		case 7333:
		case 6853:
		case 6867:
		case 6851:
		case 6833:
		case 6855:
		case 7377:
		case 6824:
		case 6843:
		case 6794:
		case 6818:
		case 6992:
		case 6857:
		case 6991:
		case 7363:
		case 7365:
		case 7337:
		case 6809:
		case 6865:
		case 6820:
		case 6802:
		case 6827:
		case 6859:
		case 6889:
		case 6815:
		case 6813:
		case 6817:
		case 7372:
		case 6839:
		case 8575:
		case 7345:
		case 6849:
		case 6798:
		case 6861:
		case 7335:
		case 7347:
		case 6800:
		case 7355:
		case 7357:
		case 7359:
		case 6811:
		case 6804:
		case 7341:
		case 7329:
		case 6863:
		case 6822:
		case 7339:
		case 6869:
		case 7349:
		case 7375:
		case 6873:
		case 7343:
			FamiliarInteraction.interactWithFamiliar(c);
			break;

		case 706:
			c.getDH().sendDialogues(9, npcId);
			break;

			// Slayer Master - Dialogue
		case 1597:
				c.getDH().sendDialogues(11, npcId);
			break;

			// Sailor - Teleporter
		case 1304:
			c.getDH().sendDialogues(4000, npcId);
		break;

			// Void Knight Shop
		case 3788:
			c.getShops().openVoid();
			break;

			// Bank

			// Pest Control - Point
		case 3789:
			c.sendMessage(new StringBuilder().append("You currently have ")
					.append(c.pcPoints).append(" pest control points.")
					.toString());
			break;

		case 557:
			c.getDH().sendDialogues(20, npcId);
			break;

		case 555:
			c.getDH().sendDialogues(23, npcId);
			break;

		case 905:
			c.getDH().sendDialogues(5, npcId);
			break;

		case 460:
			c.getDH().sendDialogues(3, npcId);
			break;

		case 462:
			c.getDH().sendDialogues(7, npcId);
			break;

			// Make Over mage
		case 599:
			c.getDH().sendDialogues(7, npcId);
			break;

			// Mage Arena Points
		case 904:
			c.sendMessage(new StringBuilder().append("You have ")
					.append(c.magePoints).append(" points.").toString());
			break;
		}
	}

}
