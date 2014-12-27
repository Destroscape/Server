package game.net.packets.dialoguebuttons;

import game.Config;
import game.content.travel.GnomeGlider;
import game.entity.player.Player;
import game.minigame.skillingtasks.SkillingGame;
import game.skill.slayer.Slayer;

public class FourOptions {

	public static void handleOption1(Player c) {
		if (c.usingGlory) {
			c.getPA().useCharge();
			c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0,
					"modern");
		}
		if(c.dialogueAction == 202) {
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.getPA().removeAllWindows();
						c.sendMessage("You cannot wear any items when changing XP Mode.");
						return;
					}
				}
				for (int i = 0; i < 25; i++) {
					c.playerLevel[i] = 1;
					c.playerXP[i] = c.getPA().getXPForLevel(1);
					c.getPA().refreshSkill(i);	
				}
			c.xpTitle = Config.XP_NOVICE;
			c.loyaltyTitle = 1;
			c.sendMessage("You chose <col=255>Novice</col> Your xp is now set at <col=255>5000</col>.");
			c.getPA().requestUpdates();
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 157) {
			c.setSidebarInterface(6, 12855);
			c.dialogueAction = -1;
			c.playerMagicBook = 2;
			//c.setAnimation(Animation.create(6299));
			c.startAnimation(6299);
			c.gfx0(1062);
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 2) {
			c.getPA().startTeleport(3428, 3538, 0, "modern");
		}
		if (c.dialogueAction == 3) {
			c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0,
					"modern");
		}
		if (c.dialogueAction == 4) {
			c.getPA().startTeleport(3565, 3314, 0, "modern");
		}
		if (c.dialogueAction == 5) {
			if (c.slayerTask <= 0) {
				Slayer.giveTask(c);
				c.getPA().removeAllWindows();
			} else {
				c.getDH().sendDialogues(16, 1597);
			}
		}
		if (c.dialogueAction == 20) {
			c.getPA().startTeleport(2897, 3618, 4, "modern");
			c.killCount = 0;
		}
		if (c.dialogueAction == 116) {
			c.getDH().sendDialogues(117, 6537);
		}
		if (c.dialogueAction == 150) { // Teletab Creation
			c.getTabCreation().createTab(8013);
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 151) {
			c.getTabCreation().createTab(8008);
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 3853) {
			c.getPA().startTeleport(2880, 3546, 0, "modern"); // Games Necklace
		}
		if (c.dialogueAction == 128241) {
			c.getPA().spellTeleport(2910, 4832, 0); // Essence mine
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 3938) {
			c.getPA().spellTeleport(2474, 3437, 0);
			c.dialogueAction = -1;
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 5001) {
		if (c.setTask > 0) {
			c.sendMessage("You seem to have a task already.");
			c.getPA().removeAllWindows();
			return;
		}
			c.getSkillingGame().chooseTask();
		}
	/*
	 * Title manager (4 Options - option 1)
	 */
		if (c.dialogueAction == 5116) {
			c.getDH().sendDialogues(5117, 884);
		}
		if (c.dialogueAction == 5118) {
			if (c.kills >= 500) {
				c.sendMessage("You have unlocked <col=255>[Nov. PK]</col> Congratulations!");
				c.getPA().removeAllWindows();
			} else {
				c.sendMessage("You need atleast 500 kills to unlock this title");
				c.getPA().removeAllWindows();
			}
		}
		if (c.dialogueAction == 5119) {
			if (c.kills >= 500) {
				c.sendMessage("You have unlocked <col=255>[Exp. PK]</col> Congratulations!");
				c.getPA().removeAllWindows();
			} else {
				c.sendMessage("You need atleast 500 kills to unlock this title");
				c.getPA().removeAllWindows();
			}
		}
		if (c.dialogueAction == 5120) {
			if (c.kills >= 500) {
				c.sendMessage("You have unlocked <col=255>[Leg. PK]</col> Congratulations!");
				c.getPA().removeAllWindows();
			} else {
				c.sendMessage("You need atleast 500 kills to unlock this title");
				c.getPA().removeAllWindows();
			}
		}
		if (c.dialogueAction == 5121) {
			if (c.kills >= 500) {
				c.sendMessage("You changed your title to <col=255>[Nov. PK]</col>");
				c.getPA().removeAllWindows();
				c.loyaltyTitle = 4;
			} else {
				c.sendMessage("You need atleast 500 kills to unlock this title");
				c.getPA().removeAllWindows();
			}
		}
		if (c.dialogueAction == 5122) {
			if (c.kills >= 500) {
				c.sendMessage("You changed your title to <col=255>[Exp. PK]</col>");
				c.getPA().removeAllWindows();
				c.loyaltyTitle = 6;
			} else {
				c.sendMessage("You need atleast 500 kills to unlock this title");
				c.getPA().removeAllWindows();
			}
		}
		if (c.dialogueAction == 5123) {
			if (c.kills >= 500) {
				c.sendMessage("You changed your title to <col=255>[Leg. PK]</col>");
				c.getPA().removeAllWindows();
				c.loyaltyTitle = 8;
			} else {
				c.sendMessage("You need atleast 500 kills to unlock this title");
				c.getPA().removeAllWindows();
			}
		}
	/*
	 * End of Title manager
	 */

		if (c.dialogueAction == 10000) {
			GnomeGlider.flightButtons(c, 100012);
			c.sendMessage("You have landed in the Godwars Dungeon.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 50034630) {
			GnomeGlider.flightButtons(c, 100000);
			c.sendMessage("You have landed in the Training Cavern.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 10001) {
			c.getDH().sendOption4("Rock Crabs", "Knights Hall",
					"Desert Training", "Secret Dungeon");
			c.dialogueAction = 50034630;
			c.dialogueId = -1;
		}
		if (c.dialogueAction == 10002) {
			GnomeGlider.flightButtons(c, 100004);
			c.sendMessage("You have landed at Pest Control Island.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 10003) {
			GnomeGlider.flightButtons(c, 100008);
			c.sendMessage("You have landed in Edgeville.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 1000) {
			c.getPA().startTeleport(2845, 4831, 0, "modern");
		}
		if (c.dialogueAction == 9) {
			c.setSidebarInterface(6, 1151);
			c.getPA().resetAutocast();
			c.playerMagicBook = 1;
			c.getPA().removeAllWindows();
			c.sendMessage("You Switch To Normal Magic..");
		}
		if (c.dialogueAction == 301) {
			c.complexity = 1;
			c.getPA().sendFrame126("" + c.complexity + "", 26241);
			c.sendMessage("You set the complexity of the floor to "
					+ c.complexity + ".");
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 500001) {
			c.getDH().sendOption2("Gnome Course", "Barbarian Course");
			c.dialogueAction = 500002;
		} else if (c.dialogueAction == 128237) {
			c.getPA().spellTeleport(2595, 3415, 0); // fishing guild
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 2035) {
			c.xpTitle = Config.XP_NOVICE;
			c.loyaltyTitle = 1;
			c.getDH().sendDialogues(2037, 949);
			c.sendMessage("You chose <col=255>Novice</col> Your xp is now set at <col=255>5000</col>.");
		}
		if (c.dialogueAction == 700) {
			if (c.getLevelForXP(c.playerXP[22]) >= 99) {
				if (c.getItems().playerHasItem(995, 99000)) {
					if (c.getItems().freeSlots() <= 2) {
						c.sendMessage("You must have 2 free inventory spaces to buy this cape.");
						c.dialogueAction = -1;
						return;
					}
					c.getItems().deleteItem2(995, 99000);
					c.getItems().addItem(12169, 1);
					c.getItems().addItem(12171, 1);
					c.sendMessage("You earned this cape.");
					c.getPA().removeAllWindows();
					c.dialogueAction = -1;
				} else {
					c.sendMessage("You must have 99K coins to buy this cape.");
					c.getPA().removeAllWindows();
					c.dialogueAction = -1;
				}
			} else {
				c.sendMessage("You need a Summoning level of 99 to buy this cape.");
				c.getPA().removeAllWindows();
				c.dialogueAction = -1;
			}
		}
		// God Books
		if (c.usingbook && c.sarabook) {
			c.forcedText = "In the name of Saradomin, Protector of us all, I now join you in the eyes of saradomin";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			//c.setAnimation(Animation.create(1670));
			c.startAnimation(1670);
		}
		if (c.usingbook && c.zambook) {
			c.forcedText = "Two great warriors, joined by hand, to spread destruction across the land, In Zamorak's name, now two are one.";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			//c.setAnimation(Animation.create(1670));
			c.startAnimation(1670);
		}
		if (c.usingbook && c.guthbook) {
			c.forcedText = "Light and dark, day and night, balance arises from contrast, I unify thee in the name of guthix";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			//c.setAnimation(Animation.create(1670));
			c.startAnimation(1670);
		}
	}

	public static void handleOption2(Player c) {
		if (c.usingGlory) {
			c.getPA().useCharge();
			c.getPA().startTeleport(Config.AL_KHARID_X, Config.AL_KHARID_Y, 0,
					"modern");
		}
		if (c.dialogueAction == 9) {
			c.setSidebarInterface(6, 12855);
			c.getPA().resetAutocast();
			c.playerMagicBook = 2;
			c.getPA().removeAllWindows();
			c.sendMessage("You Switch To Ancient Magic..");

		}
		if (c.dialogueAction == 5) {
		if (c.easierTask == 0) {
			Slayer.getEasyTask(c);
			c.getPA().removeAllWindows();
			c.easierTask = 1;
		} else {
			c.getPA().removeAllWindows();
			c.sendMessage("<col=255>You already have an easy task..");
			}
		}
		if(c.dialogueAction == 202) {
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.getPA().removeAllWindows();
						c.sendMessage("You cannot wear any items when changing XP Mode.");
						return;
					}
				}
				for (int i = 0; i < 25; i++) {
					c.playerLevel[i] = 1;
					c.playerXP[i] = c.getPA().getXPForLevel(1);
					c.getPA().refreshSkill(i);	
				}
			c.xpTitle = Config.XP_EXPERT;
			c.loyaltyTitle = 2;
			c.sendMessage("You chose <col=255>Expert</col> Your xp is now set at <col=255>500</col>.");
			c.getPA().requestUpdates();
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 157) {
			/*c.setSidebarInterface(6, 29999);
			c.dialogueAction = -1;
			c.playerMagicBook = 3;
			//c.setAnimation(Animation.create(6299));
			c.startAnimation(6299);
			c.gfx0(1062);
			c.getPA().removeAllWindows();*/
		}
		if (c.dialogueAction == 128241) {
			c.getPA().spellTeleport(3082, 9507, 0); // tutorial mining area
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 2) {
			c.getPA().startTeleport(2884, 3395, 0, "modern");
		}
		if (c.dialogueAction == 3) {
			c.getPA().startTeleport(3243, 3513, 0, "modern");
		}
		if (c.dialogueAction == 4) {
			c.getPA().startTeleport(2444, 5170, 0, "modern");
		}
		if (c.dialogueAction == 20) {
			c.getPA().startTeleport(2897, 3618, 12, "modern");
			c.killCount = 0;
		}
		if (c.dialogueAction == 2035) {
			c.xpTitle = Config.XP_EXPERT;
			c.loyaltyTitle = 2;
			c.getDH().sendDialogues(2037, 949);
			c.sendMessage("You chose <col=255>Expert</col> Your xp is now set at <col=255>500</col>.");
		}
		if (c.dialogueAction == 3853) {
			c.getPA().startTeleport(1706, 5599, 0, "modern"); // Games Necklace
		}
		if (c.dialogueAction == 3938) {
			c.getPA().spellTeleport(2551, 3555, 0);
			c.dialogueAction = -1;
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 5001) {
		if (c.setTask == 0) {
			c.sendMessage("You currently have no task.");
			c.getPA().removeAllWindows();
			return;
		}
			c.getSkillingGame().sayTask();
		}
	/*
	 * Title manager (4 Options - option 2)
	 */
		if (c.dialogueAction == 5116) {
		if (c.xpTitle.equalsIgnoreCase("novice")) {
			c.getDH().sendDialogues(5118, 884);
		}
		if (c.xpTitle.equalsIgnoreCase("expert")) {
			c.getDH().sendDialogues(5119, 884);
		}
		if (c.xpTitle.equalsIgnoreCase("legend")) {
			c.getDH().sendDialogues(5120, 884);
		}
	}
		if (c.dialogueAction == 5118) {
			if (c.totalNPCKO >= 1500) {
				c.sendMessage("You have unlocked <col=255>[Nov. PVM]</col> Congratulations!");
				c.getPA().removeAllWindows();
			} else {
				c.sendMessage("You need atleast 1500 kills to unlock this title");
				c.getPA().removeAllWindows();
			}
		}
		if (c.dialogueAction == 5119) {
			if (c.totalNPCKO >= 1500) {
				c.sendMessage("You have unlocked <col=255>[Exp. PVM]</col> Congratulations!");
				c.getPA().removeAllWindows();
			} else {
				c.sendMessage("You need atleast 1500 kills to unlock this title");
				c.getPA().removeAllWindows();
			}
		}
		if (c.dialogueAction == 5120) {
			if (c.totalNPCKO >= 1500) {
				c.sendMessage("You have unlocked <col=255>[Leg. PVM]</col> Congratulations!");
				c.getPA().removeAllWindows();
			} else {
				c.sendMessage("You need atleast 1500 kills to unlock this title");
				c.getPA().removeAllWindows();
			}
		}
		if (c.dialogueAction == 5121) {
			if (c.totalNPCKO >= 1500) {
				c.sendMessage("You changed your title to <col=255>[Nov. PVM]</col>");
				c.getPA().removeAllWindows();
				c.loyaltyTitle = 5;
			} else {
				c.sendMessage("You need atleast 1500 kills to unlock this title");
				c.getPA().removeAllWindows();
			}
		}
		if (c.dialogueAction == 5122) {
			if (c.totalNPCKO >= 1500) {
				c.sendMessage("You changed your title to <col=255>[Exp. PVM]</col>");
				c.getPA().removeAllWindows();
				c.loyaltyTitle = 7;
			} else {
				c.sendMessage("You need atleast 1500 kills to unlock this title");
				c.getPA().removeAllWindows();
			}
		}
		if (c.dialogueAction == 5123) {
			if (c.totalNPCKO >= 1500) {
				c.sendMessage("You changed your title to <col=255>[Leg. PVM]</col>");
				c.getPA().removeAllWindows();
				c.loyaltyTitle = 9;
			} else {
				c.sendMessage("You need atleast 1500 kills to unlock this title");
				c.getPA().removeAllWindows();
			}
		}
	/*
	 * End of Title manager
	 */
		if (c.dialogueAction == 10000) {
			GnomeGlider.flightButtons(c, 100013);
			c.sendMessage("You have landed in the Corporeal Beasts Lair.");
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 500001) {
			c.getPA().spellTeleport(2999, 3408, 0);
			c.dialogueAction = -1;
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 50034630) {
			GnomeGlider.flightButtons(c, 100016);
			c.sendMessage("You have landed in the Knights Hall.");
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 128237) {
			c.getPA().spellTeleport(2842, 3434, 0); // catherby close to the
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 700) {
			c.getShops().openShop(4);
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 301) {
			c.complexity = 2;
			c.getPA().sendFrame126("" + c.complexity + "", 26241);
			c.sendMessage("You set the complexity of the floor to "
					+ c.complexity + ".");
			c.getPA().removeAllWindows();
		}
		/*if (c.dialogueAction == 150) { // Teletab Creation
			c.getTabCreation().createTab(8007);
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 151) {
			c.getTabCreation().createTab(8010);
			c.getPA().removeAllWindows();
		}*/
		if (c.dialogueAction == 10001) {
			GnomeGlider.flightButtons(c, 100001);
			c.sendMessage("You have landed at the infamous Slayer Tower.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 10002) {
			GnomeGlider.flightButtons(c, 100005);
			c.sendMessage("You have landed at the Barrows entrance.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 10003) {
			GnomeGlider.flightButtons(c, 100009);
			c.sendMessage("You have landed at the West Dragons.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 1000) {
			c.getPA().startTeleport(2713, 4836, 0, "modern");
			c.dialogueAction = -1;
		}
		// God Books
		if (c.usingbook && c.sarabook) {
			c.forcedText = "Thy cause was false, thy skills did lack. see you in lumbridge when you get back";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			//c.setAnimation(Animation.create(1670));
			c.startAnimation(1670);
		}
		if (c.usingbook && c.zambook) {
			c.forcedText = "The weak deserve to die, so that the strong may florish, This is the creed of zamorak";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			//c.setAnimation(Animation.create(1670));
			c.startAnimation(1670);
		}
		if (c.usingbook && c.guthbook) {
			c.forcedText = "Thy death was not in vain, for it brought some balance to the world, may guthix bring you rest";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			//c.setAnimation(Animation.create(1670));
			c.startAnimation(1670);
		}
	}

	public static void handleOption3(Player c) {
		if (c.usingGlory) {
			c.getPA().useCharge();
			c.getPA().startTeleport(Config.KARAMJA_X, Config.KARAMJA_Y, 0,
					"modern");
		}
		if (c.dialogueAction == 9) {
			/*c.setSidebarInterface(6, 29999);
			c.getPA().resetAutocast();
			c.playerMagicBook = 3;
			c.getPA().removeAllWindows();
			c.sendMessage("You Switch To Lunar Magic..");*/
		}
		if (c.dialogueAction == 5) {
			c.getDH().sendDialogues(28, 884);
		}
		if(c.dialogueAction == 202) {
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.getPA().removeAllWindows();
						c.sendMessage("You cannot wear any items when changing XP Mode.");
						return;
					}
				}
				for (int i = 0; i < 25; i++) {
					c.playerLevel[i] = 1;
					c.playerXP[i] = c.getPA().getXPForLevel(1);
					c.getPA().refreshSkill(i);	
				}
			c.xpTitle = Config.XP_LEGEND;
			c.loyaltyTitle = 3;
			c.sendMessage("You chose <col=255>Legend</col> Your xp is now set at <col=255>50</col>.");
			c.getPA().requestUpdates();
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 157) {
			c.playerMagicBook = 1;
			c.setSidebarInterface(6, 1151);
			c.dialogueAction = -1;
			//c.setAnimation(Animation.create(6299));
			c.startAnimation(6299);
			c.gfx0(1062);
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 2035) {
			c.xpTitle = Config.XP_LEGEND;
			c.loyaltyTitle = 3;
			c.getDH().sendDialogues(2037, 949);
			c.sendMessage("You chose <col=255>legend</col> Your xp is now set at <col=255>50</col>.");
		}
		if (c.dialogueAction == 128241) {
			c.getPA().spellTeleport(3022, 9739, 0); // mining guild
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 2) {
			c.getPA().startTeleport(2471, 10137, 0, "modern");
		}
		if (c.dialogueAction == 3) {
			c.getPA().startTeleport(3363, 3676, 0, "modern");
		}
		if (c.dialogueAction == 4) {
			c.getPA().startTeleport(2659, 2676, 0, "modern");
		}
		if (c.dialogueAction == 3853) {
			c.sendMessage("<col=255>Being worked on..");
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 5001) {
		if (c.setTask == 0) {
			c.sendMessage("You currently have no task to complete.");
			c.getPA().removeAllWindows();
			return;
		}
			c.getSkillingGame().finishTask();
		}
	/*
	 * Title manager (4 Options - option 3)
	 */
		if (c.dialogueAction == 5116) {
		if (c.xpTitle.equalsIgnoreCase("novice")) {
			c.getDH().sendDialogues(5121, 884);
		}
		if (c.xpTitle.equalsIgnoreCase("expert")) {
			c.getDH().sendDialogues(5122, 884);
		}
		if (c.xpTitle.equalsIgnoreCase("legend")) {
			c.getDH().sendDialogues(5123, 884);
		}
	}
		if (c.dialogueAction == 301) {
			c.complexity = 3;
			c.getPA().sendFrame126("" + c.complexity + "", 26241);
			c.sendMessage("You set the complexity of the floor to "
					+ c.complexity + ".");
			c.getPA().removeAllWindows();
		}
		/*if (c.dialogueAction == 150) { // Teletab Creation
			c.getTabCreation().createTab(8009);
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 151) {
			c.getTabCreation().createTab(8011);
			c.getPA().removeAllWindows();
		}*/
		if (c.dialogueAction == 700) {
			c.getShops().openShop(5);
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 20) {
			c.getPA().startTeleport(2897, 3618, 8, "modern");
			c.killCount = 0;
		}
		if (c.dialogueAction == 3938) {
			c.getPA().spellTeleport(3003, 3934, 0);
			c.dialogueAction = -1;
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 10000) {
			GnomeGlider.flightButtons(c, 100014);
			c.sendMessage("You have landed in the Dagonnoth Cavern.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 50034630) {
			GnomeGlider.flightButtons(c, 100017);
			c.sendMessage("You have landed in the Desert Training Area.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 10001) {
			GnomeGlider.flightButtons(c, 100002);
			c.sendMessage("You have landed in the BrimHaven Dungeon.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 10002) {
			GnomeGlider.flightButtons(c, 100006);
			c.sendMessage("You have landed at the Duel Arena.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 10003) {
			GnomeGlider.flightButtons(c, 100010);
			c.sendMessage("You have landed at the East Dragons.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 1000) {
			c.getPA().startTeleport(2660, 4839, 0, "modern");
			c.dialogueAction = -1;
		}
		// God Books
		if (c.usingbook && c.sarabook) {
			c.forcedText = "go in peace in the name of saradomin, may his glory shine upon you like the sun.";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			//c.setAnimation(Animation.create(1670));
			c.startAnimation(1670);
		}
		if (c.usingbook && c.zambook) {
			c.forcedText = "May your bloodthirst never be sated, and may all your battles be glorious, Zamorak bring you strength";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			//c.setAnimation(Animation.create(1670));
			c.startAnimation(1670);
		}
		if (c.usingbook && c.guthbook) {
			c.forcedText = "May you walk the path and never fall, for guthix walks beside thee on thy journey, may guthix bring you peace";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			//c.setAnimation(Animation.create(1670));
			c.startAnimation(1670);
		} else if (c.dialogueAction == 500001) {
			c.getPA().spellTeleport(2662, 3309, 0);
			c.dialogueAction = -1;
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 128237) {
			c.getPA().spellTeleport(2336, 3695, 0); // fishing colony
			c.getPA().removeAllWindows();
		}
	}

	public static void handleOption4(Player c) {
		if (c.usingGlory) {
			c.getPA().useCharge();
			c.getPA().startTeleport(Config.MAGEBANK_X, Config.MAGEBANK_Y, 0,
					"modern");
		}
		if (c.dialogueAction == 9) {
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 2035) {
			c.xpTitle = Config.XP_NONE;
			return;
		}
		if (c.dialogueAction == 5) {
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 5001) {
		if (c.skillPoints > 0) {
			c.skillPoints -= 1;
			c.skillAmount = 0;
			c.tid = 0;
			c.ia = 0;
			c.skillTask = 0;
			c.skillAmount1 = 0;
			c.tid1 = 0;
			c.ia1 = 0;
			c.skillTask1 = 0;
			c.skillAmount2 = 0;
			c.tid2 = 0;
			c.ia2 = 0;
			c.skillTask2 = 0;
			c.setTask = 0;
			c.getPA().removeAllWindows();
			c.sendMessage("<col=255>You successfully reset your skilltask for 1 skilling point.");
		} else {
			c.getPA().removeAllWindows();
			c.sendMessage("<col=255>You need atleast 1 skilling point to reset your task.");
			}
		}
		if(c.dialogueAction == 202) { //allstats
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 2) {
			c.getPA().startTeleport(2669, 3714, 0, "modern");
		}
		if (c.dialogueAction == 3) {
			c.getPA().startTeleport(2540, 4716, 0, "modern");
		}
		if (c.dialogueAction == 4) {
			c.getPA().startTeleport(3366, 3266, 0, "modern");
			c.sendMessage("Dueling is at your own risk. Refunds will not be given for items lost due to glitches.");
		}
		if (c.dialogueAction == 3853) {
			c.getPA().startTeleport(3045, 3370, 0, "modern");
		}
		if (c.dialogueAction == 301) {
			c.complexity = 4;
			c.getPA().sendFrame126("" + c.complexity + "", 26241);
			c.sendMessage("You set the complexity of the floor to "
					+ c.complexity + ".");
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 700) {
			c.dialogueAction = -1;
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 150) { // Teletab Creation
			c.getDH().sendDialogues(151, 0);
		}
		if (c.dialogueAction == 151) { // Teletab Creation
			c.getDH().sendDialogues(150, 0);
		}
		if (c.dialogueAction == 3938) {
			c.dialogueAction = -1;
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 157) {
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 20) {
			// c.getPA().startTeleport(3366, 3266, 0, "modern");
			// c.killCount = 0;
			c.sendMessage("This will be added shortly");
		}
		if (c.dialogueAction == 128241) {
			//c.getPA().spellTeleport(2910, 4832, 0); // Essence mine //TODO
			c.sendMessage("Coming Soon.");
			c.getPA().removeAllWindows();
		}
		if (c.dialogueAction == 10000) {
			GnomeGlider.flightButtons(c, 100015);
			c.sendMessage("You have landed in the Tormented Demons Lair.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 50034630) {
			GnomeGlider.flightButtons(c, 100018);
			c.sendMessage("You have landed in the Secret Dungeon.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 10001) {
			GnomeGlider.flightButtons(c, 100003);
			c.sendMessage("You have landed outside of the Taverly Dungeon.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 10002) {
			GnomeGlider.flightButtons(c, 100007);
			c.sendMessage("You have landed at the Tzhaar Caves.");
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 500001) {
			c.getDH().sendOption5("Mining", "Woodcutting", "Fishing",
					"Farming", "More");
			c.dialogueAction = 500000;
		}
		if (c.dialogueAction == 10003) {
			GnomeGlider.flightButtons(c, 100011);
			c.sendMessage("You have landed in the Magic Arena Bank.");
			c.dialogueAction = -1;
		}
		if (c.dialogueAction == 1000) {
			c.getPA().startTeleport(2786, 4833, 0, "modern");
		}
		// God Books
		if (c.usingbook && c.sarabook) {
			c.forcedText = "Walk proud, and show mercy, for you cary my name in your heart, this is saradomin's wisdom";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			//c.setAnimation(Animation.create(1670));
			c.startAnimation(1670);
		}
		if (c.usingbook && c.zambook) {
			c.forcedText = "There is no opinion that cannot be proven true. By crushing those who choose to disagree with it, Zamorak Bring me Strength!";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			//c.setAnimation(Animation.create(1670));
			c.startAnimation(1670);
		}
		if (c.usingbook && c.guthbook) {
			c.forcedText = "A Journey of a single step, may take thee over a thousand miles, may guthix bring you balance";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			//c.setAnimation(Animation.create(1670));
			c.startAnimation(1670);
		}
	}

}
