package game.content;

import game.combat.prayer.Curse;
import game.combat.prayer.Prayer;
import game.entity.player.Player;

/**
 *
 * @author relex lawl / relex
 *
 * Old class I decided to upgrade
 */

public class QuickCurses {

	public static final int MAX_CURSES = 20;
	private static final int[][] data = { 	{67050, 630, 0}, {67051, 631, 1}, {67052, 632, 2}, {67053, 633, 3},
											{67054, 634, 4}, {67055, 635, 5}, {67056, 636, 6}, {67057, 637, 7},
											{67058, 638, 8}, {67059, 639, 9}, {67060, 640, 10}, {67061, 641, 11},
											{67062, 642, 12}, {67063, 643, 13}, {67064, 644, 14}, {67065, 645, 15},
											{67066, 646, 16}, {67067, 647, 17}, {67068, 648, 18}, {67069, 649, 19}
										};//actionID, frameID, quickCurse = true

	public static void clickCurse(Player p, int actionId) {
		canBeSelected(p, actionId);
		for (int j = 0; j < data.length; j++) {
			if (data[j][0] == actionId) {
				if (p.quickCurses[data[j][2]]) {
					p.quickCurses[data[j][2]] = false;
					p.getPA().sendFrame36(data[j][1], 0);
				} else {
					p.quickCurses[data[j][2]] = true;
					p.getPA().sendFrame36(data[j][1], 1);
				}
			}
		}
	}
	
	public static void loadCheckMarks(Player player) {
		for (int j = 0; j < data.length; j++)
			player.getPA().sendFrame36(data[j][1], player.quickCurses[data[j][2]] ? 1 : 0);
	}

	public static void canBeSelected(Player p, int actionId) {
		boolean[] curse = new boolean[MAX_CURSES];
		for (int j = 0; j < curse.length; j++)
			curse[j] = true;
		switch (actionId) {
			case 67051:
				curse[10] = false;
				curse[19] = false;
			break;

			case 67052:
				curse[11] = false;
				curse[19] = false;
			break;

			case 67053:
				curse[12] = false;
				curse[19] = false;
			break;

			case 67054:
				curse[16] = false;
			break;

			case 67057:
				curse[8] = false;
				curse[9] = false;
				curse[17] = false;
				curse[18] = false;
			break;

			case 67058:
				curse[7] = false;
				curse[9] = false;
				curse[17] = false;
				curse[18] = false;
			break;

			case 67059:
				curse[7] = false;
				curse[8] = false;
				curse[17] = false;
				curse[18] = false;
			break;

			case 67060:
				curse[1] = false;
				curse[19] = false;
			break;

			case 67061:
				curse[2] = false;
				curse[19] = false;
			break;

			case 67062:
				curse[3] = false;
				curse[19] = false;
			break;

			case 67063:
			case 67064:
			case 67065: //Leech run-energy
				curse[19] = false;
			break;

			case 67066: //Leech special
				curse[4] = false;
				curse[19] = false;
			break;

			case 67067:
				for (int i = 7; i < 10; i++)
					curse[i] = false;
				curse[18] = false;
			break;

			case 67068:
				for (int i = 7; i < 10; i++)
					curse[i] = false;
				curse[17] = false;
			break;

			case 67069:
				for (int i = 1; i < 5; i++) {
					for (int j = 10; j < 17; j++) {
						curse[i] = false;
						curse[j] = false;
					}
				}
			break;
		}
		for (int i = 0; i < MAX_CURSES; i++) {
			if (!curse[i]) {
				p.quickCurses[i] = false;
				for (int j = 0; j < data.length; j++) {
					if (i == data[j][2])
						p.getPA().sendFrame36(data[j][1], 0);
				}
			}
		}
	}

	public static void turnOnQuicks(Player p) {
		if (p.altarPrayed == 0) {
			for (int i = 0; i < p.quickPrayers.length; i++) {
				if (p.quickPrayers[i] && !p.prayerActive[i]){
					p.quickPray = true;
					Prayer.activatePrayer(p, i, true);
					p.getPA().sendFrame126(":quicks:on", -1);
					p.quickPrayersOn = true;
					p.updateRequired = true;
					p.appearanceUpdateRequired = true;
				}
				if (!p.quickPrayers[i]) {
					p.prayerActive[i] = false;
					p.getPA().sendFrame36(p.PRAYER_GLOW[i], 0);
					p.getPA().requestUpdates();
				}
			}			
		} else {
			for (int i = 0; i < p.quickCurses.length; i++) {
				if (p.quickCurses[i] && !p.curseActive[i]){
					p.quickCurse = true;
					p.getCurse().activateCurse(i);
					p.getPA().sendFrame126(":quicks:on", -1);
					p.quickPrayersOn = true;
					p.updateRequired = true;
					p.appearanceUpdateRequired = true;
				}
				if (!p.quickCurses[i]) {
					p.curseActive[i] = false;
					p.getPA().sendFrame36(p.CURSE_GLOW[i], 0);
					p.getPA().requestUpdates();
				}
			}
		}
	}

	public static void turnOffQuicks(Player p) {	
		Prayer.closeAllPrayers(p);
		p.getCurse().resetCurse();
		p.getPA().sendFrame126(":quicks:off", -1);
		p.quickPray = false;
		p.quickCurse = false;
		p.headIcon = -1;
		p.getPA().requestUpdates();
		p.quickPrayersOn = false;
		p.updateRequired = true;
		p.appearanceUpdateRequired = true;
	}

	public static void selectQuickInterface(Player p) {
		if (p.altarPrayed == 0)
			QuickPrayers.loadCheckMarks(p);
		else
			loadCheckMarks(p);
		p.setSidebarInterface(5, p.altarPrayed == 0 ? 20000 : 22000);
		p.getPA().sendFrame106(5);
	}

	public static void clickConfirm(Player p) {
		p.setSidebarInterface(5, p.altarPrayed == 1 ? 22500 : 5608);
	}
}