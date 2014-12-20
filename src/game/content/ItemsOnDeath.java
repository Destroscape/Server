package game.content;

import engine.util.Misc;
import game.entity.player.Player;

public class ItemsOnDeath {

	public static void BestItem1(Player p) {
		int BestValue = 0;
		int NextValue = 0;
		int ItemsContained = 0;
		p.WillKeepItem1 = 0;
		p.WillKeepItem1Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (p.playerItems[ITEM] > 0) {
				ItemsContained += 1;
				NextValue = (int) Math.floor(p.getShops().getItemShopValue(
						p.playerItems[ITEM] - 1));
				if (NextValue > BestValue) {
					BestValue = NextValue;
					p.WillKeepItem1 = p.playerItems[ITEM] - 1;
					p.WillKeepItem1Slot = ITEM;
					if (p.playerItemsN[ITEM] > 2 && !p.prayerActive[10]) {
						p.WillKeepAmt1 = 3;
					} else if (p.playerItemsN[ITEM] > 3 && p.prayerActive[10]) {
						p.WillKeepAmt1 = 4;
					} else {
						p.WillKeepAmt1 = p.playerItemsN[ITEM];
					}
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (p.playerEquipment[EQUIP] > 0) {
				ItemsContained += 1;
				NextValue = (int) Math.floor(p.getShops().getItemShopValue(
						p.playerEquipment[EQUIP]));
				if (NextValue > BestValue) {
					BestValue = NextValue;
					p.WillKeepItem1 = p.playerEquipment[EQUIP];
					p.WillKeepItem1Slot = EQUIP + 28;
					if (p.playerEquipmentN[EQUIP] > 2 && !p.prayerActive[10]) {
						p.WillKeepAmt1 = 3;
					} else if (p.playerEquipmentN[EQUIP] > 3
							&& p.prayerActive[10]) {
						p.WillKeepAmt1 = 4;
					} else {
						p.WillKeepAmt1 = p.playerEquipmentN[EQUIP];
					}
				}
			}
		}
		if (!p.isSkulled
				&& ItemsContained > 1
				&& (p.WillKeepAmt1 < 3 || (p.prayerActive[10] && p.WillKeepAmt1 < 4))) {
			BestItem2(p, ItemsContained);
		}
	}

	public static void BestItem2(Player p, int ItemsContained) {
		int BestValue = 0;
		int NextValue = 0;
		p.WillKeepItem2 = 0;
		p.WillKeepItem2Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (p.playerItems[ITEM] > 0) {
				NextValue = (int) Math.floor(p.getShops().getItemShopValue(
						p.playerItems[ITEM] - 1));
				if (NextValue > BestValue
						&& !(ITEM == p.WillKeepItem1Slot && p.playerItems[ITEM] - 1 == p.WillKeepItem1)) {
					BestValue = NextValue;
					p.WillKeepItem2 = p.playerItems[ITEM] - 1;
					p.WillKeepItem2Slot = ITEM;
					if (p.playerItemsN[ITEM] > 2 - p.WillKeepAmt1
							&& !p.prayerActive[10]) {
						p.WillKeepAmt2 = 3 - p.WillKeepAmt1;
					} else if (p.playerItemsN[ITEM] > 3 - p.WillKeepAmt1
							&& p.prayerActive[10]) {
						p.WillKeepAmt2 = 4 - p.WillKeepAmt1;
					} else {
						p.WillKeepAmt2 = p.playerItemsN[ITEM];
					}
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (p.playerEquipment[EQUIP] > 0) {
				NextValue = (int) Math.floor(p.getShops().getItemShopValue(
						p.playerEquipment[EQUIP]));
				if (NextValue > BestValue
						&& !(EQUIP + 28 == p.WillKeepItem1Slot && p.playerEquipment[EQUIP] == p.WillKeepItem1)) {
					BestValue = NextValue;
					p.WillKeepItem2 = p.playerEquipment[EQUIP];
					p.WillKeepItem2Slot = EQUIP + 28;
					if (p.playerEquipmentN[EQUIP] > 2 - p.WillKeepAmt1
							&& !p.prayerActive[10]) {
						p.WillKeepAmt2 = 3 - p.WillKeepAmt1;
					} else if (p.playerEquipmentN[EQUIP] > 3 - p.WillKeepAmt1
							&& p.prayerActive[10]) {
						p.WillKeepAmt2 = 4 - p.WillKeepAmt1;
					} else {
						p.WillKeepAmt2 = p.playerEquipmentN[EQUIP];
					}
				}
			}
		}
		if (!p.isSkulled
				&& ItemsContained > 2
				&& (p.WillKeepAmt1 + p.WillKeepAmt2 < 3 || (p.prayerActive[10] && p.WillKeepAmt1
						+ p.WillKeepAmt2 < 4))) {
			BestItem3(p, ItemsContained);
		}
	}

	public static void BestItem3(Player p, int ItemsContained) {
		int BestValue = 0;
		int NextValue = 0;
		p.WillKeepItem3 = 0;
		p.WillKeepItem3Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (p.playerItems[ITEM] > 0) {
				NextValue = (int) Math.floor(p.getShops().getItemShopValue(
						p.playerItems[ITEM] - 1));
				if (NextValue > BestValue
						&& !(ITEM == p.WillKeepItem1Slot && p.playerItems[ITEM] - 1 == p.WillKeepItem1)
						&& !(ITEM == p.WillKeepItem2Slot && p.playerItems[ITEM] - 1 == p.WillKeepItem2)) {
					BestValue = NextValue;
					p.WillKeepItem3 = p.playerItems[ITEM] - 1;
					p.WillKeepItem3Slot = ITEM;
					if (p.playerItemsN[ITEM] > 2 - (p.WillKeepAmt1 + p.WillKeepAmt2)
							&& !p.prayerActive[10]) {
						p.WillKeepAmt3 = 3 - (p.WillKeepAmt1 + p.WillKeepAmt2);
					} else if (p.playerItemsN[ITEM] > 3 - (p.WillKeepAmt1 + p.WillKeepAmt2)
							&& p.prayerActive[10]) {
						p.WillKeepAmt3 = 4 - (p.WillKeepAmt1 + p.WillKeepAmt2);
					} else {
						p.WillKeepAmt3 = p.playerItemsN[ITEM];
					}
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (p.playerEquipment[EQUIP] > 0) {
				NextValue = (int) Math.floor(p.getShops().getItemShopValue(
						p.playerEquipment[EQUIP]));
				if (NextValue > BestValue
						&& !(EQUIP + 28 == p.WillKeepItem1Slot && p.playerEquipment[EQUIP] == p.WillKeepItem1)
						&& !(EQUIP + 28 == p.WillKeepItem2Slot && p.playerEquipment[EQUIP] == p.WillKeepItem2)) {
					BestValue = NextValue;
					p.WillKeepItem3 = p.playerEquipment[EQUIP];
					p.WillKeepItem3Slot = EQUIP + 28;
					if (p.playerEquipmentN[EQUIP] > 2 - (p.WillKeepAmt1 + p.WillKeepAmt2)
							&& !p.prayerActive[10]) {
						p.WillKeepAmt3 = 3 - (p.WillKeepAmt1 + p.WillKeepAmt2);
					} else if (p.playerEquipmentN[EQUIP] > 3 - p.WillKeepAmt1
							&& p.prayerActive[10]) {
						p.WillKeepAmt3 = 4 - (p.WillKeepAmt1 + p.WillKeepAmt2);
					} else {
						p.WillKeepAmt3 = p.playerEquipmentN[EQUIP];
					}
				}
			}
		}
		if (!p.isSkulled && ItemsContained > 3 && p.prayerActive[10]
				&& ((p.WillKeepAmt1 + p.WillKeepAmt2 + p.WillKeepAmt3) < 4)) {
			BestItem4(p);
		}
	}

	public static void BestItem4(Player p) {
		int BestValue = 0;
		int NextValue = 0;
		p.WillKeepItem4 = 0;
		p.WillKeepItem4Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (p.playerItems[ITEM] > 0) {
				NextValue = (int) Math.floor(p.getShops().getItemShopValue(
						p.playerItems[ITEM] - 1));
				if (NextValue > BestValue
						&& !(ITEM == p.WillKeepItem1Slot && p.playerItems[ITEM] - 1 == p.WillKeepItem1)
						&& !(ITEM == p.WillKeepItem2Slot && p.playerItems[ITEM] - 1 == p.WillKeepItem2)
						&& !(ITEM == p.WillKeepItem3Slot && p.playerItems[ITEM] - 1 == p.WillKeepItem3)) {
					BestValue = NextValue;
					p.WillKeepItem4 = p.playerItems[ITEM] - 1;
					p.WillKeepItem4Slot = ITEM;
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (p.playerEquipment[EQUIP] > 0) {
				NextValue = (int) Math.floor(p.getShops().getItemShopValue(
						p.playerEquipment[EQUIP]));
				if (NextValue > BestValue
						&& !(EQUIP + 28 == p.WillKeepItem1Slot && p.playerEquipment[EQUIP] == p.WillKeepItem1)
						&& !(EQUIP + 28 == p.WillKeepItem2Slot && p.playerEquipment[EQUIP] == p.WillKeepItem2)
						&& !(EQUIP + 28 == p.WillKeepItem3Slot && p.playerEquipment[EQUIP] == p.WillKeepItem3)) {
					BestValue = NextValue;
					p.WillKeepItem4 = p.playerEquipment[EQUIP];
					p.WillKeepItem4Slot = EQUIP + 28;
				}
			}
		}
	}

	public static void FindItemKeptInfo(Player p) {
		if (p.isSkulled && p.prayerActive[10])
			ItemKeptInfo(p, 1);
		else if (!p.isSkulled && !p.prayerActive[10])
			ItemKeptInfo(p, 3);
		else if (!p.isSkulled && p.prayerActive[10])
			ItemKeptInfo(p, 4);
	}

	public static void ItemKeptInfo(Player p, int Lose) {
		for (int i = 17109; i < 17131; i++) {
			p.getPA().sendFrame126("", i);
		}
		p.getPA().sendFrame126("Items you will keep on death:", 17104);
		p.getPA().sendFrame126("Items you will lose on death:", 17105);
		p.getPA().sendFrame126("Player Information", 17106);
		p.getPA().sendFrame126("Max items kept on death:", 17107);
		p.getPA().sendFrame126("~ " + Lose + " ~", 17108);
		p.getPA().sendFrame126("The normal amount of", 17111);
		p.getPA().sendFrame126("items kept is three.", 17112);
		switch (Lose) {
		case 0:
		default:
			p.getPA().sendFrame126("Items you will keep on death:", 17104);
			p.getPA().sendFrame126("Items you will lose on death:", 17105);
			p.getPA().sendFrame126("You're marked with a", 17111);
			p.getPA().sendFrame126("@red@skull. @lre@This reduces the", 17112);
			p.getPA().sendFrame126("items you keep from", 17113);
			p.getPA().sendFrame126("three to zero!", 17114);
			break;
		case 1:
			p.getPA().sendFrame126("Items you will keep on death:", 17104);
			p.getPA().sendFrame126("Items you will lose on death:", 17105);
			p.getPA().sendFrame126("You're marked with a", 17111);
			p.getPA().sendFrame126("@red@skull. @lre@This reduces the", 17112);
			p.getPA().sendFrame126("items you keep from", 17113);
			p.getPA().sendFrame126("three to zero!", 17114);
			p.getPA().sendFrame126("However, you also have", 17115);
			p.getPA().sendFrame126("the @red@Protect @lre@Items prayer", 17116);
			p.getPA().sendFrame126("active, which saves you", 17117);
			p.getPA().sendFrame126("one extra item!", 17118);
			break;
		case 3:
			p.getPA().sendFrame126(
					"Items you will keep on death(if not skulled):", 17104);
			p.getPA().sendFrame126(
					"Items you will lose on death(if not skulled):", 17105);
			p.getPA().sendFrame126("You have no factors", 17111);
			p.getPA().sendFrame126("affecting the items you", 17112);
			p.getPA().sendFrame126("keep.", 17113);
			break;
		case 4:
			p.getPA().sendFrame126(
					"Items you will keep on death(if not skulled):", 17104);
			p.getPA().sendFrame126(
					"Items you will lose on death(if not skulled):", 17105);
			p.getPA().sendFrame126("You have the @red@Protect", 17111);
			p.getPA().sendFrame126("@red@Item @lre@prayer active,", 17112);
			p.getPA().sendFrame126("which saves you one", 17113);
			p.getPA().sendFrame126("extra item!", 17114);
			break;
		}
	}

	public static void open(Player p) {
		if (System.currentTimeMillis() - p.logoutDelay > 10000) {
			p.getPA().sendFrame126("Items kept on death", 17103);
			StartBestItemScan(p);
			p.EquipStatus = 0;
			for (int k = 0; k < 4; k++)
				p.getPA().sendFrame34a(10494, -1, k, 1);
			for (int k = 0; k < 39; k++)
				p.getPA().sendFrame34a(10600, -1, k, 1);
			if (p.WillKeepItem1 > 0)
				p.getPA().sendFrame34a(10494, p.WillKeepItem1, 0,
						p.WillKeepAmt1);
			if (p.WillKeepItem2 > 0)
				p.getPA().sendFrame34a(10494, p.WillKeepItem2, 1,
						p.WillKeepAmt2);
			if (p.WillKeepItem3 > 0)
				p.getPA().sendFrame34a(10494, p.WillKeepItem3, 2,
						p.WillKeepAmt3);
			if (p.WillKeepItem4 > 0 && p.prayerActive[10])
				p.getPA().sendFrame34a(10494, p.WillKeepItem4, 3, 1);
			for (int ITEM = 0; ITEM < 28; ITEM++) {
				if (p.playerItems[ITEM] - 1 > 0
						&& !(p.playerItems[ITEM] - 1 == p.WillKeepItem1 && ITEM == p.WillKeepItem1Slot)
						&& !(p.playerItems[ITEM] - 1 == p.WillKeepItem2 && ITEM == p.WillKeepItem2Slot)
						&& !(p.playerItems[ITEM] - 1 == p.WillKeepItem3 && ITEM == p.WillKeepItem3Slot)
						&& !(p.playerItems[ITEM] - 1 == p.WillKeepItem4 && ITEM == p.WillKeepItem4Slot)) {
					p.getPA().sendFrame34a(10600, p.playerItems[ITEM] - 1,
							p.EquipStatus, p.playerItemsN[ITEM]);
					p.EquipStatus += 1;
				} else if (p.playerItems[ITEM] - 1 > 0
						&& (p.playerItems[ITEM] - 1 == p.WillKeepItem1 && ITEM == p.WillKeepItem1Slot)
						&& p.playerItemsN[ITEM] > p.WillKeepAmt1) {
					p.getPA().sendFrame34a(10600, p.playerItems[ITEM] - 1,
							p.EquipStatus,
							p.playerItemsN[ITEM] - p.WillKeepAmt1);
					p.EquipStatus += 1;
				} else if (p.playerItems[ITEM] - 1 > 0
						&& (p.playerItems[ITEM] - 1 == p.WillKeepItem2 && ITEM == p.WillKeepItem2Slot)
						&& p.playerItemsN[ITEM] > p.WillKeepAmt2) {
					p.getPA().sendFrame34a(10600, p.playerItems[ITEM] - 1,
							p.EquipStatus,
							p.playerItemsN[ITEM] - p.WillKeepAmt2);
					p.EquipStatus += 1;
				} else if (p.playerItems[ITEM] - 1 > 0
						&& (p.playerItems[ITEM] - 1 == p.WillKeepItem3 && ITEM == p.WillKeepItem3Slot)
						&& p.playerItemsN[ITEM] > p.WillKeepAmt3) {
					p.getPA().sendFrame34a(10600, p.playerItems[ITEM] - 1,
							p.EquipStatus,
							p.playerItemsN[ITEM] - p.WillKeepAmt3);
					p.EquipStatus += 1;
				} else if (p.playerItems[ITEM] - 1 > 0
						&& (p.playerItems[ITEM] - 1 == p.WillKeepItem4 && ITEM == p.WillKeepItem4Slot)
						&& p.playerItemsN[ITEM] > 1) {
					p.getPA().sendFrame34a(10600, p.playerItems[ITEM] - 1,
							p.EquipStatus, p.playerItemsN[ITEM] - 1);
					p.EquipStatus += 1;
				}
			}
			for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
				if (p.playerEquipment[EQUIP] > 0
						&& !(p.playerEquipment[EQUIP] == p.WillKeepItem1 && EQUIP + 28 == p.WillKeepItem1Slot)
						&& !(p.playerEquipment[EQUIP] == p.WillKeepItem2 && EQUIP + 28 == p.WillKeepItem2Slot)
						&& !(p.playerEquipment[EQUIP] == p.WillKeepItem3 && EQUIP + 28 == p.WillKeepItem3Slot)
						&& !(p.playerEquipment[EQUIP] == p.WillKeepItem4 && EQUIP + 28 == p.WillKeepItem4Slot)) {
					p.getPA().sendFrame34a(10600, p.playerEquipment[EQUIP],
							p.EquipStatus, p.playerEquipmentN[EQUIP]);
					p.EquipStatus += 1;
				} else if (p.playerEquipment[EQUIP] > 0
						&& (p.playerEquipment[EQUIP] == p.WillKeepItem1 && EQUIP + 28 == p.WillKeepItem1Slot)
						&& p.playerEquipmentN[EQUIP] > 1
						&& p.playerEquipmentN[EQUIP] - p.WillKeepAmt1 > 0) {
					p.getPA().sendFrame34a(10600, p.playerEquipment[EQUIP],
							p.EquipStatus,
							p.playerEquipmentN[EQUIP] - p.WillKeepAmt1);
					p.EquipStatus += 1;
				} else if (p.playerEquipment[EQUIP] > 0
						&& (p.playerEquipment[EQUIP] == p.WillKeepItem2 && EQUIP + 28 == p.WillKeepItem2Slot)
						&& p.playerEquipmentN[EQUIP] > 1
						&& p.playerEquipmentN[EQUIP] - p.WillKeepAmt2 > 0) {
					p.getPA().sendFrame34a(10600, p.playerEquipment[EQUIP],
							p.EquipStatus,
							p.playerEquipmentN[EQUIP] - p.WillKeepAmt2);
					p.EquipStatus += 1;
				} else if (p.playerEquipment[EQUIP] > 0
						&& (p.playerEquipment[EQUIP] == p.WillKeepItem3 && EQUIP + 28 == p.WillKeepItem3Slot)
						&& p.playerEquipmentN[EQUIP] > 1
						&& p.playerEquipmentN[EQUIP] - p.WillKeepAmt3 > 0) {
					p.getPA().sendFrame34a(10600, p.playerEquipment[EQUIP],
							p.EquipStatus,
							p.playerEquipmentN[EQUIP] - p.WillKeepAmt3);
					p.EquipStatus += 1;
				} else if (p.playerEquipment[EQUIP] > 0
						&& (p.playerEquipment[EQUIP] == p.WillKeepItem4 && EQUIP + 28 == p.WillKeepItem4Slot)
						&& p.playerEquipmentN[EQUIP] > 1
						&& p.playerEquipmentN[EQUIP] - 1 > 0) {
					p.getPA().sendFrame34a(10600, p.playerEquipment[EQUIP],
							p.EquipStatus, p.playerEquipmentN[EQUIP] - 1);
					p.EquipStatus += 1;
				}
			}
			p.getPA().sendFrame126("Carried wealth:", 17117);
			p.getPA().sendFrame126(
					Misc.insertCommasToNumber(Integer.toString(p.getItems()
							.getTotalNet(p))) + "", 17118);
			p.getPA().sendFrame126("Risking wealth:", 17119);
			p.getPA().sendFrame126(
					Misc.insertCommasToNumber(Integer.toString(p.getItems()
							.getTotalNet(p) - p.getItems().getKeepNet()))
							+ "", 17120);
			p.getPA().showInterface(17100);
			ResetKeepItems(p);
		} else {
			p.sendMessage("I can't open this in combat..");
		}
	}

	public static void ResetKeepItems(Player p) {
		p.WillKeepAmt1 = -1;
		p.WillKeepItem1 = -1;
		p.WillKeepAmt2 = -1;
		p.WillKeepItem2 = -1;
		p.WillKeepAmt3 = -1;
		p.WillKeepItem3 = -1;
		p.WillKeepAmt4 = -1;
		p.WillKeepItem4 = -1;
	}

	public static void StartBestItemScan(Player p) {
		if (p.isSkulled && !p.prayerActive[10]) {
			ItemKeptInfo(p, 0);
			return;
		}
		FindItemKeptInfo(p);
		ResetKeepItems(p);
		BestItem1(p);
	}

}