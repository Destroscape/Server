package game.net.packets.dialoguebuttons;

import engine.util.Misc;
import game.Config;
import game.content.gambling.DiceHandler;
import game.entity.player.Player;

public class FiveOptions {

	public static void handleOption1(Player c) {
		if (c.teleAction == 1) {
			// rock crabs
			c.getPA().spellTeleport(2589, 9408, 0);
		} else if (c.teleAction == 2) {
			// barrows
			c.getPA().spellTeleport(3565, 3314, 0);
		} else if (c.teleAction == 3) {
			// godwars
			c.getPA().spellTeleport(2916, 3612, 0);
		} else if (c.teleAction == 4) {
			// varrock wildy
			c.getPA().spellTeleport(3243, 3513, 0);
		} else if (c.teleAction == 5) {
			c.getPA().spellTeleport(3046, 9779, 0);
		}
		if (c.dialogueAction == 10) {
			c.getPA().spellTeleport(2845, 4832, 0);
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 11) {
			c.getPA().spellTeleport(2786, 4839, 0);
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 12) {
			c.getPA().spellTeleport(2398, 4841, 0);
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 300) {
			c.floor = 1;
			c.getPA().sendFrame126("" + c.floor + "", 26240);
			c.sendMessage("You have selected floor 1 to explore.");
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 3401) {
			c.getDH().sendDialogues(3403, 0);
		} else if (c.dialogueAction == 3402) {
			c.getPA().spellTeleport(2932, 3285, 0);
		} else if (c.dialogueAction == 3403) {
			c.getPA().spellTeleport(2910, 4832, 0);
		} else if (c.dialogueAction == 3404) {
			c.getPA().spellTeleport(2474, 3437, 0);
		} else if (c.dialogueAction == 3405) {
			c.getPA().spellTeleport(2846, 4834, 0);
		} else if (c.dialogueAction == 3406) {
			c.getPA().spellTeleport(2584, 4836, 0);
		} else if (c.dialogueAction == 3407) {
			c.getPA().spellTeleport(2397, 4841, 0);
		//* Monster teleports
		} else if (c.dialogueAction == 4001) {
			c.getDH().sendDialogues(4002, 0);
		} else if (c.dialogueAction == 4002) {
			c.getDH().sendDialogues(4003, 0);
		} else if (c.dialogueAction == 4003) {
			c.getPA().spellTeleport(2673, 3710, 0);
		} else if (c.dialogueAction == 4004) {
			c.getPA().spellTeleport(2713, 9564, 0);
		} else if (c.dialogueAction == 4005) {
			c.getDH().sendDialogues(4007, 0);
		} else if (c.dialogueAction == 4006) {
			c.getPA().spellTeleport(2913, 4385, 0);
		} else if (c.dialogueAction == 4007) {
			c.getPA().spellTeleport(3039, 9543, 0);
		} else if (c.dialogueAction == 4008) {
			c.getPA().spellTeleport(2660, 2660, 0);
		} else if (c.dialogueAction == 3415) {
		if (c.theStryke == 0) {
			c.getDH().sendDialogues(3416, 945);
		}
		if (c.theStryke == 2) {
			c.getDH().sendDialogues(3423, 945);
		}
		} else if (c.dialogueAction == 117112) {
			c.getPA().spellTeleport(3032, 9549, 0);
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 500000) {
			c.getPA().spellTeleport(3043, 9816, 0);
			c.dialogueAction = -1;
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 129001) {
			c.getPA().spellTeleport(2846, 4834, 0);
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 160) {
			c.getPA().spellTeleport(2584, 4836, 0);
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 161) {
			c.getPA().spellTeleport(2397, 4841, 0);
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 10003) {
			c.getPA().spellTeleport(3086, 3491, 0);
		} else if (c.dialogueAction == 62) {
			int chaosTunnels = Misc.random(4);
			switch (chaosTunnels) {
			case 0:
				c.getPA().spellTeleport(3120, 3571, 0);
				break;

			case 1:
				c.getPA().spellTeleport(3166, 3561, 0);
				break;

			case 2:
				c.getPA().spellTeleport(3164, 3616, 0);
				break;
				
			case 3:
				c.getPA().spellTeleport(3108, 3639, 0);
				break;
			}
		} else {
			DiceHandler.handleClick(c, 9190);
		}
	}

	public static void handleOption2(Player c) {
		if (c.teleAction == 1) {
			// tav dungeon
			c.getPA().spellTeleport(2884, 9798, 0);
		} else if (c.teleAction == 2) {
			// pest control
			c.getPA().spellTeleport(2662, 2650, 0);
		} else if (c.teleAction == 3) {
			// kbd
			c.getPA().spellTeleport(3007, 3849, 0);
		} else if (c.teleAction == 4) {
			// graveyard
			c.getPA().spellTeleport(3164, 3685, 0);
		} else if (c.teleAction == 5) {
			c.getPA().spellTeleport(3079, 9502, 0);
		}
		if (c.dialogueAction == 10) {
			c.getPA().spellTeleport(2796, 4818, 0);
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 11) {
			c.getPA().spellTeleport(2527, 4833, 0);
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 12) {
			c.getPA().spellTeleport(2464, 4834, 0);
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 117112) {
			c.getPA().spellTeleport(2502, 2893, 0);
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 300) {
			if (c.getPA().getLevelForDungXP(c.playerXP[23]) >= 30) {
				c.floor = 2;
				c.getPA().sendFrame126("" + c.floor + "", 26240);
				c.sendMessage("You have selected floor 2 to explore.");
				c.getPA().removeAllWindows();
			} else {
				c.sendMessage("You must have a dungeoneering level of 30 to access this floor.");
				c.getPA().removeAllWindows();
			}
		} else if (c.dialogueAction == 3401) {
			c.getDH().sendDialogues(3405, 0);
		} else if (c.dialogueAction == 3402) {
			c.getPA().spellTeleport(2333, 10015, 0);
		} else if (c.dialogueAction == 3403) {
			c.getPA().spellTeleport(3082, 9507, 0);
		} else if (c.dialogueAction == 3404) {
			c.getPA().spellTeleport(2551, 3555, 0);
		} else if (c.dialogueAction == 3405) {
			c.getPA().spellTeleport(2786, 4838, 0);
		} else if (c.dialogueAction == 3406) {
			c.getPA().spellTeleport(2162, 4833, 0);
		} else if (c.dialogueAction == 3407) {
			c.getPA().spellTeleport(2464, 4834, 0);
		} else if (c.dialogueAction == 3415) {
		if (c.lunarDiplomacy == 0) {
			c.getDH().sendDialogues(3480, 945);
		}
		//* Monster teleports
		} else if (c.dialogueAction == 4001) {
			c.getDH().sendDialogues(4008, 0);
		} else if (c.dialogueAction == 4002) {
			c.getDH().sendDialogues(4004, 0);
		} else if (c.dialogueAction == 4003) {
			c.getPA().spellTeleport(2326, 3804, 0);
		} else if (c.dialogueAction == 4004) {
			c.getPA().spellTeleport(2884, 9799, 0);
		} else if (c.dialogueAction == 4005) {
			c.getPA().spellTeleport(2540, 5777, 0);
		} else if (c.dialogueAction == 4006) {
			c.getPA().spellTeleport(2902, 5204, 0);
		} else if (c.dialogueAction == 4007) {
			c.getPA().spellTeleport(2502, 2893, 0);
		} else if (c.dialogueAction == 4008) {
			c.getPA().spellTeleport(3565, 3316, 0);
		} else if (c.dialogueAction == 500000) {
			c.getPA().spellTeleport(2721, 3459, 0);
			c.dialogueAction = -1;
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 10003) {
			c.getPA().spellTeleport(3339, 3659, 0);
		} else if (c.dialogueAction == 129001) {
			c.getPA().spellTeleport(2786, 4838, 0);
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 160) {
			c.getPA().spellTeleport(2162, 4833, 0);
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 161) {
			c.getPA().spellTeleport(2464, 4834, 0);
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 62) {
			c.getPA().spellTeleport(3288, 3886, 0);
		} else {
			DiceHandler.handleClick(c, 9191);
		}
	}

	public static void handleOption3(Player c) {
		if (c.teleAction == 1) {
			// brimhaven dungeon
			c.getPA().spellTeleport(2710, 9466, 0);
		} else if (c.teleAction == 2) {
			// tzhaar
			c.getPA().spellTeleport(2444, 5170, 0);
		} else if (c.teleAction == 3) {
			// dag kings
			c.getPA().spellTeleport(2479, 10147, 0);
		} else if (c.teleAction == 4) {
			// 44 portals
			c.getPA().spellTeleport(2975, 3873, 0);
		} else if (c.teleAction == 5) {
			c.getPA().spellTeleport(2813, 3436, 0);
		}
		if (c.dialogueAction == 10) {
			c.getPA().spellTeleport(2713, 4836, 0);
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 11) {
			c.getPA().spellTeleport(2162, 4833, 0);
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 12) {
			c.getPA().spellTeleport(2207, 4836, 0);
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 300) {
			if (c.getPA().getLevelForDungXP(c.playerXP[23]) >= 55) {
				c.floor = 3;
				c.getPA().sendFrame126("" + c.floor + "", 26240);
				c.sendMessage("You have selected floor 3 to explore.");
				//c.sendMessage("This floor is currently disabled.");
				c.getPA().removeAllWindows();
			} else {
				c.sendMessage("You must have a dungeoneering level of 55 to access this floor.");
				//c.sendMessage("This floor is currently disabled.");
				c.getPA().removeAllWindows();
			}
		} else if (c.dialogueAction == 3401) {
			c.getDH().sendDialogues(3404, 0);
		} else if (c.dialogueAction == 3403) {
			c.getPA().spellTeleport(3022, 9739, 0);
		} else if (c.dialogueAction == 3404) {
			c.getPA().spellTeleport(3004, 3934, 0);
		} else if (c.dialogueAction == 3405) {
			c.getPA().spellTeleport(3482, 4838, 0);
		} else if (c.dialogueAction == 3406) {
			c.getPA().spellTeleport(2268, 4842, 0);
		} else if (c.dialogueAction == 3407) {
			c.getPA().spellTeleport(2205, 4834, 0);
		} else if (c.dialogueAction == 3415) {
		if (c.DT == 0) {
			c.getDH().sendDialogues(3483, 945);
		}
		//* Monster teleports
		} else if (c.dialogueAction == 4001) {
			c.getDH().sendDialogues(58, 0);
		} else if (c.dialogueAction == 4002) {
			c.getDH().sendDialogues(4005, 0);
		} else if (c.dialogueAction == 4003) {
			c.getPA().spellTeleport(2907, 9713, 0);
		} else if (c.dialogueAction == 4004) {
			c.getPA().spellTeleport(3429, 3538, 0);
		} else if (c.dialogueAction == 4005) {
			c.getPA().spellTeleport(2654, 3991, 1);
		} else if (c.dialogueAction == 4006) {
			c.getPA().spellTeleport(2883, 5309, 2);
		} else if (c.dialogueAction == 4007) {
			c.getPA().spellTeleport(3383, 3015, 0);
		} else if (c.dialogueAction == 4008) {
			c.getPA().spellTeleport(3350, 3266, 0);
		} else if (c.dialogueAction == 129001) {
			c.getPA().spellTeleport(3482, 4838, 0);
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 117112) {
			c.getPA().spellTeleport(3383, 3015, 0);
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 160) {
			c.getPA().spellTeleport(2268, 4842, 0);
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 161) {
			c.getPA().spellTeleport(2205, 4834, 0);
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 500000) {
			c.getPA().spellTeleport(2854, 3429, 0);
			c.dialogueAction = -1;
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 10003) {
			c.getPA().spellTeleport(2967, 3607, 0);
		} else if (c.dialogueAction == 62) {
			c.getPA().spellTeleport(3006, 3849, 0);
		} else {
			DiceHandler.handleClick(c, 9192);
		}
	}

	public static void handleOption4(Player c) {
		if (c.teleAction == 2) {
			// duel arena
			c.getPA().spellTeleport(3366, 3266, 0);
		} else if (c.teleAction == 3) {
			// chaos elemental
			c.getPA().spellTeleport(3295, 3921, 0);
		} else if (c.teleAction == 4) {
			// gdz
			c.getPA().spellTeleport(3288, 3886, 0);
		} else if (c.teleAction == 5) {
			c.getPA().spellTeleport(2724, 3484, 0);
			c.sendMessage("For magic logs, try north of the duel arena.");
		}
		if (c.dialogueAction == 10) {
			c.getPA().spellTeleport(2660, 4839, 0);
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 11) {
			// c.getPA().spellTeleport(2527, 4833, 0); astrals here
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 12) {
			// c.getPA().spellTeleport(2464, 4834, 0); bloods here
			c.dialogueAction = -1;
		} else if (c.dialogueAction == 300) { // dung floor 4
			 if (c.getPA().getLevelForDungXP(c.playerXP[23]) >= 80) { 
				c.floor = 4; 
				c.getPA().sendFrame126("" + c.floor + "", 26240);
			 	c.sendMessage("You have selected floor 4 to explore.");
			 	c.getPA().removeAllWindows();
			 } else { 
				c.sendMessage("You must have a dungeoneering level of 80 to access this floor."); 
				c.getPA().removeAllWindows();
			 }
		} else if (c.dialogueAction == 3401) {
			c.getPA().spellTeleport(2552, 2913, 0);
		} else if (c.dialogueAction == 3405) {
			c.getPA().spellTeleport(2660, 4839, 0);
		} else if (c.dialogueAction == 3406) {
			c.getPA().spellTeleport(2160, 3862, 0);
		} else if (c.dialogueAction == 3407) {
			c.getPA().spellTeleport(2463, 4890, 1);
		//* Monster teleports
		} else if (c.dialogueAction == 4001) {
			c.getPA().spellTeleport(2335, 3689, 0);
		} else if (c.dialogueAction == 4002) {
			c.getDH().sendDialogues(4006, 0);
		} else if (c.dialogueAction == 4003) {
			c.getPA().spellTeleport(3253, 2785, 0);
		} else if (c.dialogueAction == 4004) {
			c.getPA().spellTeleport(2442, 10147, 0);
		} else if (c.dialogueAction == 4005) {
			c.getPA().spellTeleport(2759, 10064, 0);
		} else if (c.dialogueAction == 4006) {
			c.getPA().spellTeleport(3552, 4977, 0);
		} else if (c.dialogueAction == 4007) {
			c.getPA().spellTeleport(3055, 9579, 0);
		} else if (c.dialogueAction == 4008) {
			c.getPA().spellTeleport(2445, 5173, 0);
		} else if (c.dialogueAction == 129001) {
			c.getPA().spellTeleport(2660, 4839, 0);
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 117112) {
			c.getPA().spellTeleport(3055, 9579, 0);
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 160) {
			c.getPA().spellTeleport(2160, 3862, 0);
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 161) {
			c.getPA().spellTeleport(2463, 4890, 1);
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 500000) {
			c.getPA().spellTeleport(2815, 3463, 0);
			c.dialogueAction = -1;
			c.sendMessage("The herb patch is the one you farm on.");
			c.getPA().removeAllWindows();
		} else if (c.dialogueAction == 10003) {
			c.getPA().spellTeleport(Config.MAGEBANK_X, Config.MAGEBANK_Y, 0);
		} else if (c.dialogueAction == 62) {
			c.getPA().spellTeleport(2610, 3148, 0);
			c.getPA().closeAllWindows();
		} else {
			DiceHandler.handleClick(c, 9193);
		}
	}

	public static void handleOption5(Player c) {
		if (c.teleAction == 2) {
			// last minigame spot
			c.sendMessage("Suggest something for this spot on the forums!");
			c.getPA().closeAllWindows();
		} else if (c.teleAction == 3) {
			// last monster spot
			c.sendMessage("Suggest something for this spot on the forums!");
			c.getPA().closeAllWindows();
		} else if (c.teleAction == 4) {
			// ardy lever
			c.getPA().spellTeleport(2561, 3311, 0);
		} else if (c.teleAction == 5) {
			c.getPA().spellTeleport(2812, 3463, 0);
		}
		if (c.dialogueAction == 10 || c.dialogueAction == 11) {
			c.dialogueId++;
			c.getDH().sendDialogues(c.dialogueId, 0);
		} else if (c.dialogueAction == 12) {
			c.dialogueId = 17;
			c.getDH().sendDialogues(c.dialogueId, 0);
		} else if (c.dialogueAction == 300) {
			if (c.getPA().getLevelForDungXP(c.playerXP[23]) >= 110) {
				c.floor = 5;
				c.getPA().sendFrame126("" + c.floor + "", 26240);
				c.sendMessage("You have selected floor 5 to explore.");
				//c.sendMessage("This floor is currently disabled.");
				c.getPA().removeAllWindows();
			} else {
				c.sendMessage("You must have a dungeoneering level of 110 to access this floor.");
				//c.sendMessage("This floor is currently disabled.");
				c.getPA().removeAllWindows();
			}
		} else if (c.dialogueAction == 62) {
			c.getDH().sendDialogues(4001, 0);
		} else if (c.dialogueAction == 3401) {
			c.getDH().sendDialogues(3402, 0);
		} else if (c.dialogueAction == 3402) {
			c.getDH().sendDialogues(3401, 0);
		} else if (c.dialogueAction == 3403) {
			c.getDH().sendDialogues(3401, 0);
		} else if (c.dialogueAction == 3404) {
			c.getDH().sendDialogues(3401, 0);
		} else if (c.dialogueAction == 3405) {
			c.getDH().sendDialogues(3406, 0);
		} else if (c.dialogueAction == 3406) {
			c.getDH().sendDialogues(3407, 0);
		} else if (c.dialogueAction == 3407) {
			c.getDH().sendDialogues(3401, 0);
		//* Teleportation -sailor
		} else if (c.dialogueAction == 4001) {
			c.getDH().sendDialogues(4001, 0);
		} else if (c.dialogueAction == 4002) {
			c.getDH().sendDialogues(4001, 0);
		} else if (c.dialogueAction == 4003) {
			c.getDH().sendDialogues(4002, 0);
		} else if (c.dialogueAction == 4004) {
			c.getDH().sendDialogues(4002, 0);
		} else if (c.dialogueAction == 4005) {
			c.getPA().spellTeleport(3171, 2982, 0);
		} else if (c.dialogueAction == 4006) {
			c.getPA().spellTeleport(2657, 10073, 2);
		} else if (c.dialogueAction == 4007) {
			c.getDH().sendDialogues(4005, 0);
		} else if (c.dialogueAction == 4008) {
			c.getDH().sendDialogues(4001, 0);
		} else if (c.dialogueAction == 10003) {
			c.getDH().sendDialogues(62, 0);
		} else if (c.dialogueAction == 129001) {
			c.getDH().sendDialogues(160, 0);
		} else if (c.dialogueAction == 160) {
			c.getDH().sendDialogues(161, 0);
		} else if (c.dialogueAction == 161) {
			c.getDH().sendDialogues(162, 0);
		} else if (c.dialogueAction == 62) {
			c.getDH().sendDialogues(58, 0);
		} else if (c.dialogueAction == 500000) {
			c.getDH().sendOption4("Agility", "Hunter", "Thieving", "Back");
			c.dialogueAction = 500001;
		} else {
			DiceHandler.handleClick(c, 9194);
		}
	}

}
