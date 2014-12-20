package game.item;

import engine.util.Misc;
import game.Config;
import game.Server;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;

public class ItemAssistant {

	private final Player c;

	public boolean updateInventory = false;

	public int getKeepNet() {
		return c.getShops().getItemShopValue(c.WillKeepItem1) * c.WillKeepAmt1
				+ c.getShops().getItemShopValue(c.WillKeepItem2)
				* c.WillKeepAmt2
				+ c.getShops().getItemShopValue(c.WillKeepItem3)
				* c.WillKeepAmt3
				+ c.getShops().getItemShopValue(c.WillKeepItem4)
				* c.WillKeepAmt4;
	}

	public int getTotalNet(Player c) {
		int totalAmount = 0;
		for (int j = 0; j < c.playerEquipment.length; j++) {
			totalAmount += c.getShops().getItemShopValue(c.playerEquipment[j])
					* c.playerEquipmentN[j];
		}
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] - 1 > 0) {
				int inventoryItemValue = c.getShops().getItemShopValue(
						c.playerItems[i] - 1);
				if (inventoryItemValue > 0) {
					totalAmount += inventoryItemValue;
				}
			}
		}
		return totalAmount;
	}
	public void dropAllItemsPVP() {
		//Player pl = (Player) Server.playerHandler.players[c.killerId];
		final Player o = PlayerHandler.players[c.killerId];
		int random = Misc.random(100);
		c.sendMessage("k " +random + " ");
		
		if (o != null && c.killerId != c.playerId) {
			
			if (random > 1 && random < 4) {
				Server.itemHandler.createGroundItem(o, PvPDropsRR(), c.getX(), c.getY(), 1, c.killerId);
				
			} else if (random > 5 && random < 14) {
				Server.itemHandler.createGroundItem(o, PvPDropsCR(), c.getX(), c.getY(), 1, c.killerId);
				
			} else if (random > 2 && random < 25) {
				Server.itemHandler.createGroundItem(o, PVPItems(), c.getX(), c.getY(), Misc.random(2), c.killerId);
				
			} else if (random > 14 && random < 31) {
				Server.itemHandler.createGroundItem(o, PVPItems(), c.getX(), c.getY(), Misc.random(2), c.killerId);
			
			}
		
		}
	}
		int[] PvpItems = { 14876, 14877, 14878, 14879, 14880, 14881, 14882, 14883, 14884, 14885, 14886, 14888, 14889, 14890, 14891, 14892, };
		int[] PvPDropsR = {13887,13893,13899,13905,13884,13890,13896,13902,13858,13861,13864,13867,13870,13873,13876,};
		int[] PvPDropsC = {13911,13917,13923,13929,13908,13914,13920,13926,13932,13935,13938,13941,13944,13947,13950,
				13958,13961,13964,13967,13970,13973,13976,13979,13982,13985,13988,13855,13848,13857,13856,13854,13853,13852,
				13851,13850};
		//int[] PvPDrops = {11335,11732, 11284,10352,10344,10346, 10348, 10350,10330,10332,10334,10336,10338,10340,10342};
		//int[] GoodDrops = {/*2653,2655,2657,2659,2661,2663,2665,2667,2669,2671,2673,2675*/};
		//int[] FoodDrops = {2550,391,385,379,361,139};
		//int[] MedDrops = {6737,6735,6733,6731,11128,658511732,4151,4716,4718,4720,4722,4724,4726,4728,4730,4732,4734,4736,4708,4710,4712,4714,4740,4741,4743,4745,4747,4749,4751,5753,4755,4757,4759};
		//int[] LowDrops = {3751,10828,4091,4093,4101,4103,4111,4113,4131,1079,1127,1713,385,391,361,139};

		public int PvPDropsCR() {
			return PvPDropsC[(int) (Math.random() * PvPDropsC.length)];
		}
		public int PVPItems() {
			return PvpItems[(int) (Math.random() * PvpItems.length)];
		}
		public int PvPDropsRR() {
			return PvPDropsR[(int) (Math.random() * PvPDropsR.length)];
		}

	public void removeItem(int slot) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			if (c.playerEquipment[slot] > -1){
				if (addItem(c.playerEquipment[slot], c.playerEquipmentN[slot])) {
					c.playerEquipment[slot] = -1;
					c.playerEquipmentN[slot] = 0;
					sendWeapon(c.playerEquipment[c.playerWeapon],
							getItemName(c.playerEquipment[c.playerWeapon]));
					resetBonus();
					getBonus();
					writeBonus();
					c.getCombat().getPlayerAnimIndex(
							c.getItems()
							.getItemName(
									c.playerEquipment[c.playerWeapon])
									.toLowerCase());
					c.getOutStream().createFrame(34);
					c.getOutStream().writeWord(6);
					c.getOutStream().writeWord(1688);
					c.getOutStream().writeByte(slot);
					c.getOutStream().writeWord(0);
					c.getOutStream().writeByte(0);
					c.flushOutStream();
					c.updateRequired = true;
					c.setAppearanceUpdateRequired(true);
				}
			}
		}
	}

	/*public void removeItem(final int wearID, final int slot) {
		boolean torvaChanged = false;
		if (c.getOutStream() != null && c != null) {
			if (c.playerEquipment[slot] > -1) {
				if (addItem(c.playerEquipment[slot], c.playerEquipmentN[slot])) {
					if (c.playerEquipment[c.wearSlot] == 20135 || c.playerEquipment[c.wearSlot] == 20139 || c.playerEquipment[c.wearSlot] == 20143 || c.playerEquipment[c.wearSlot] == 20159 || c.playerEquipment[c.wearSlot] == 20163 || c.playerEquipment[c.wearSlot] == 20167 || c.playerEquipment[c.wearSlot] == 20147 || c.playerEquipment[c.wearSlot] == 20151 || c.playerEquipment[c.wearSlot] == 20155)
						torvaChanged = true;
					c.playerEquipment[slot] = -1;
					c.playerEquipmentN[slot] = 0;
					sendWeapon(c.playerEquipment[c.playerWeapon], getItemName(c.playerEquipment[c.playerWeapon]));
					resetBonus();
					getBonus();
					writeBonus();
					c.getCombat().getPlayerAnimIndex(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
					c.getOutStream().createFrame(34);
					c.getOutStream().writeWord(6);
					c.getOutStream().writeWord(1688);
					c.getOutStream().writeByte(slot);
					c.getOutStream().writeWord(0);
					c.getOutStream().writeByte(0);
					c.flushOutStream();
					c.updateRequired = true;
					c.setAppearanceUpdateRequired(true);
					if (torvaChanged && c.playerLevel[3] > c.calculateMaxLifePoints()) {
						c.playerLevel[3] = c.calculateMaxLifePoints();
						c.getPA().refreshSkill(3);
					}
				}
			}
		}
	}*/

	public void replaceItem(Player c, int i, int l) {
		for (int k = 0; k < c.playerItems.length; k++) {
			if (playerHasItem(i, 1)) {
				deleteItem(i, getItemSlot(i), 1);
				addItem(l, 1);
			}
		}
	}

	/**
	 * Items
	 */
	public int[][] skillcapes = { { 9747, 9748 }, // attack
			{ 9753, 9754 }, // defence
			{ 9750, 9751 }, // strength
			{ 9768, 9769 }, // hitpoints
			{ 9756, 9757 }, // range
			{ 9759, 9760 }, // prayer
			{ 9762, 9763 }, // magic
			{ 9801, 9802 }, // cooking
			{ 9807, 9808 }, // woodcutting
			{ 9783, 9784 }, // fletching
			{ 9798, 9799 }, // fishing
			{ 9804, 9805 }, // firemaking
			{ 9780, 9781 }, // crafting
			{ 9795, 9796 }, // smithing
			{ 9792, 9793 }, // mining
			{ 9774, 9775 }, // herblore
			{ 9771, 9772 }, // agility
			{ 9777, 9778 }, // thieving
			{ 9786, 9787 }, // slayer
			{ 9810, 9811 }, // farming
			{ 9765, 9766 } // runecraft
	};

	public int[][] brokenBarrows = { { 4708, 4860 }, { 4710, 4866 },
			{ 4712, 4872 }, { 4714, 4878 }, { 4716, 4884 }, { 4720, 4896 },
			{ 4718, 4890 }, { 4720, 4896 }, { 4722, 4902 }, { 4732, 4932 },
			{ 4734, 4938 }, { 4736, 4944 }, { 4738, 4950 }, { 4724, 4908 },
			{ 4726, 4914 }, { 4728, 4920 }, { 4730, 4926 }, { 4745, 4956 },
			{ 4747, 4926 }, { 4749, 4968 }, { 4751, 4794 }, { 4753, 4980 },
			{ 4755, 4986 }, { 4757, 4992 }, { 4759, 4998 } };

	/**
	 * Bonuses
	 **/
	public final String[] BONUS_NAMES = { "Stab", "Slash", "Crush", "Magic",
			"Range", "Stab", "Slash", "Crush", "Magic", "Range", "Strength",
	"Prayer" };

	public ItemAssistant(final Player client) {
		c = client;
	}

	/**
	 * Add Item
	 **/
	public boolean addItem(final int item, int amount) {
		if (amount < 1) {
			amount = 1;
		}
		if (item <= 0) {
			return false;
		}
		final int playerAmount1 = c.getItems().getItemAmount(item);
		if (playerAmount1 + amount < -1) {
			return false;
		}
		if ((freeSlots() >= 1 || playerHasItem(item, 1))
				&& Item.itemStackable[item] || freeSlots() > 0
				&& !Item.itemStackable[item]) {
			for (int i = 0; i < c.playerItems.length; i++) {
				if (c.playerItems[i] == item + 1 && Item.itemStackable[item]
						&& c.playerItems[i] > 0) {
					c.playerItems[i] = item + 1;
					if (c.playerItemsN[i] + amount < Config.MAXITEM_AMOUNT
							&& c.playerItemsN[i] + amount > -1) {
						c.playerItemsN[i] += amount;
					} else {
						c.playerItemsN[i] = Config.MAXITEM_AMOUNT;
					}
					if (c.getOutStream() != null && c != null) {
						c.getOutStream().createFrameVarSizeWord(34);
						c.getOutStream().writeWord(3214);
						c.getOutStream().writeByte(i);
						c.getOutStream().writeWord(c.playerItems[i]);
						if (c.playerItemsN[i] > 254) {
							c.getOutStream().writeByte(255);
							c.getOutStream().writeDWord(c.playerItemsN[i]);
						} else {
							c.getOutStream().writeByte(c.playerItemsN[i]);
						}
						c.getOutStream().endFrameVarSizeWord();
						c.flushOutStream();
					}
					i = 30;
					return true;
				}
			}
			for (int i = 0; i < c.playerItems.length; i++) {
				if (c.playerItems[i] <= 0) {
					c.playerItems[i] = item + 1;
					if (amount < Config.MAXITEM_AMOUNT && amount > -1) {
						c.playerItemsN[i] = 1;
						if (amount > 1) {
							c.getItems().addItem(item, amount - 1);
							return true;
						}
					} else {
						c.playerItemsN[i] = Config.MAXITEM_AMOUNT;
					}
					/*
					 * if(c.getOutStream() != null && c != null ) {
					 * c.getOutStream().createFrameVarSizeWord(34);
					 * c.getOutStream().writeWord(3214);
					 * c.getOutStream().writeByte(i);
					 * c.getOutStream().writeWord(c.playerItems[i]); if
					 * (c.playerItemsN[i] > 254) {
					 * c.getOutStream().writeByte(255);
					 * c.getOutStream().writeDWord(c.playerItemsN[i]); } else {
					 * c.getOutStream().writeByte(c.playerItemsN[i]); }
					 * c.getOutStream().endFrameVarSizeWord();
					 * c.flushOutStream(); }
					 */
					resetItems(3214);
					i = 30;
					return true;
				}
			}
			return false;
		} else {
			resetItems(3214);
			c.sendMessage("Not enough space in your inventory.");
			return false;
		}
	}

	/**
	 * Weapons special bar, adds the spec bars to weapons that require them and
	 * removes the spec bars from weapons which don't require them
	 **/
	public void addSpecialBar(int weapon) {
		switch (weapon) {
		case 4151: // whip
			c.getPA().sendFrame171(0, 12323);
			specialAmount(weapon, c.specAmount, 12335);
			break;

		case 15441: // whip
			c.getPA().sendFrame171(0, 12323);
			specialAmount(weapon, c.specAmount, 12335);
			break;

		case 15442: // whip
			c.getPA().sendFrame171(0, 12323);
			specialAmount(weapon, c.specAmount, 12335);
			break;

		case 15443: // whip
			c.getPA().sendFrame171(0, 12323);
			specialAmount(weapon, c.specAmount, 12335);
			break;

		case 15444: // whip
			c.getPA().sendFrame171(0, 12323);
			specialAmount(weapon, c.specAmount, 12335);
			break;

		case 21371: //vine whip
			c.getPA().sendFrame171(0, 12323);
			specialAmount(weapon, c.specAmount, 12335);
			break;

		case 859: // magic bows
		case 861:
		case 15241:
		case 15701:
		case 15702:
		case 15703:
		case 15704:
		case 11235:
		case 15015:
		case 15016:
		case 13022:
		case 13021:
		case 13023:
		case 20171:

			c.getPA().sendFrame171(0, 7549);
			specialAmount(weapon, c.specAmount, 7561);
			break;

		case 4587: // dscimmy
			c.getPA().sendFrame171(0, 7599);
			specialAmount(weapon, c.specAmount, 7611);
			break;

			/*
			 * case 19780: c.getPA().sendFrame171(0, 7599); specialAmount(weapon,
			 * c.specAmount, 7611); break;
			 */

		case 3204: // d hally

			c.getPA().sendFrame171(0, 8493);
			specialAmount(weapon, c.specAmount, 8505);
			break;

		case 1377: // d battleaxe

			c.getPA().sendFrame171(0, 7499);
			specialAmount(weapon, c.specAmount, 7511);
			break;

		case 4153: // gmaul
			c.getPA().sendFrame171(0, 7474);
			specialAmount(weapon, c.specAmount, 7486);
			break;

		case 13902:
			c.getPA().sendFrame171(0, 7474);
			specialAmount(weapon, c.specAmount, 7486);
			break;

		case 13905:
		case 1249: // dspear
			c.getPA().sendFrame171(0, 7674);
			specialAmount(weapon, c.specAmount, 7686);
			break;

		case 14484: // Dragon claws
			c.getPA().sendFrame171(0, 7800);
			specialAmount(weapon, c.specAmount, 7812);
			break;

		case 1215:// dragon dagger
		case 1231:
		case 13899:
		case 10887:
		case 5680:
		//case 13905:
		case 5698:
		case 1305: // dragon long
		case 11694:
		case 11698:
		case 11700:
		case 15486: // SOL
		case 11730:
		case 11696:
		case 19780:// korasi
			c.getPA().sendFrame171(0, 7574);
			specialAmount(weapon, c.specAmount, 7586);
			break;

		case 1434: // dragon mace
			c.getPA().sendFrame171(0, 7624);
			specialAmount(weapon, c.specAmount, 7636);
			break;

		default:
			c.getPA().sendFrame171(1, 7624); // mace interface
			c.getPA().sendFrame171(1, 7474); // hammer, gmaul
			c.getPA().sendFrame171(1, 7499); // axe
			c.getPA().sendFrame171(1, 7549); // bow interface
			c.getPA().sendFrame171(1, 7574); // sword interface
			c.getPA().sendFrame171(1, 7599); // scimmy sword interface, for most
			// swords
			c.getPA().sendFrame171(1, 8493);
			c.getPA().sendFrame171(1, 12323); // whip interface
			c.getPA().sendFrame171(1, 7812); // Claws
			break;
		}
	}

	public void addToVoidList(final int itemId) {
		switch (itemId) {

		case 2518:
			c.voidStatus[0]++;
			break;
		case 2520:
			c.voidStatus[1]++;
			break;
		case 2522:
			c.voidStatus[2]++;
			break;
		case 2524:
			c.voidStatus[3]++;
			break;
		case 2526:
			c.voidStatus[4]++;
			break;
		}
	}

	

	/**
	 * Drop Item
	 **/
	public void createGroundItem(final int itemID, final int itemX,
			final int itemY, final int itemAmount) {
		c.getOutStream().createFrame(85);
		c.getOutStream().writeByteC(itemY - 8 * c.mapRegionY);
		c.getOutStream().writeByteC(itemX - 8 * c.mapRegionX);
		c.getOutStream().createFrame(44);
		c.getOutStream().writeWordBigEndianA(itemID);
		c.getOutStream().writeWord(itemAmount);
		c.getOutStream().writeByte(0);
		c.flushOutStream();
	}

	/**
	 * delete all items
	 **/
	public void deleteAllItems() {
		for (int i1 = 0; i1 < c.playerEquipment.length; i1++) {
			deleteEquipment(c.playerEquipment[i1], i1);
		}
		for (int i = 0; i < c.playerItems.length; i++) {
			deleteItem(c.playerItems[i] - 1, getItemSlot(c.playerItems[i] - 1),
					c.playerItemsN[i]);
		}
	}

	/**
	 * Delete Arrows
	 **/
	public void deleteArrow() {
		if (c.playerEquipment[c.playerCape] == 10499 || c.playerEquipment[c.playerCape] == 14001 
		 || c.playerEquipment[c.playerCape] == 14002  || c.playerEquipment[c.playerCape] == 14003 
		 || c.playerEquipment[c.playerCape] == 14003  || c.playerEquipment[c.playerCape] == 14004 
		 || c.playerEquipment[c.playerCape] == 14005  || c.playerEquipment[c.playerCape] == 14006 
		 || c.playerEquipment[c.playerCape] == 14007 || c.playerEquipment[c.playerCape] == 14008 
		 || c.playerEquipment[c.playerCape] == 14009  || c.playerEquipment[c.playerCape] == 14010 
		 || c.playerEquipment[c.playerCape] == 20769  || c.playerEquipment[c.playerCape] == 20771) {
		if (Misc.random(2) == 1) {
			return;
		} else {
		if (c.playerEquipmentN[c.playerArrows] == 1) {
			c.getItems().deleteEquipment(c.playerEquipment[c.playerArrows],
					c.playerArrows);
				}
			}
		}
		if (c.playerEquipmentN[c.playerArrows] != 0) {
			c.getOutStream().createFrameVarSizeWord(34);
			c.getOutStream().writeWord(1688);
			c.getOutStream().writeByte(c.playerArrows);
			c.getOutStream().writeWord(c.playerEquipment[c.playerArrows] + 1);
			if (c.playerEquipmentN[c.playerArrows] - 1 > 254) {
				c.getOutStream().writeByte(255);
				c.getOutStream().writeDWord(
						c.playerEquipmentN[c.playerArrows] - 1);
			} else {
				c.getOutStream().writeByte(
						c.playerEquipmentN[c.playerArrows] - 1);
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
			c.playerEquipmentN[c.playerArrows] -= 1;
		}
		c.updateRequired = true;
		c.setAppearanceUpdateRequired(true);
	}

	public void deleteEquipment() {
		if (c.playerEquipmentN[c.playerWeapon] == 1) {
			c.getItems().deleteEquipment(c.playerEquipment[c.playerWeapon],
					c.playerWeapon);
		}
		if (c.playerEquipmentN[c.playerWeapon] != 0) {
			c.getOutStream().createFrameVarSizeWord(34);
			c.getOutStream().writeWord(1688);
			c.getOutStream().writeByte(c.playerWeapon);
			c.getOutStream().writeWord(c.playerEquipment[c.playerWeapon] + 1);
			if (c.playerEquipmentN[c.playerWeapon] - 1 > 254) {
				c.getOutStream().writeByte(255);
				c.getOutStream().writeDWord(
						c.playerEquipmentN[c.playerWeapon] - 1);
			} else {
				c.getOutStream().writeByte(
						c.playerEquipmentN[c.playerWeapon] - 1);
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
			c.playerEquipmentN[c.playerWeapon] -= 1;
		}
		c.updateRequired = true;
		c.setAppearanceUpdateRequired(true);
	}

	/**
	 * delete Item
	 **/
	public void deleteEquipment(final int i, final int j) {
		if (PlayerHandler.players[c.playerId] == null) {
			return;
		}
		if (i < 0) {
			return;
		}

		c.playerEquipment[j] = -1;
		c.playerEquipmentN[j] = c.playerEquipmentN[j] - 1;
		c.getOutStream().createFrame(34);
		c.getOutStream().writeWord(6);
		c.getOutStream().writeWord(1688);
		c.getOutStream().writeByte(j);
		c.getOutStream().writeWord(0);
		c.getOutStream().writeByte(0);
		getBonus();
		if (j == c.playerWeapon) {
			sendWeapon(-1, "Unarmed");
		}
		resetBonus();
		getBonus();
		writeBonus();
		c.updateRequired = true;
		c.setAppearanceUpdateRequired(true);
	}

	public void deleteItem(final int id, int amount) {
		deleteItem(id, getItemSlot(id), amount);
	}

	public void deleteItem(final int id, final int slot, final int amount) {
		if (id <= 0 || slot < 0) {
			return;
		}
		if (c.playerItems[slot] == id + 1) {
			if (c.playerItemsN[slot] > amount) {
				c.playerItemsN[slot] -= amount;
			} else {
				c.playerItemsN[slot] = 0;
				c.playerItems[slot] = 0;
			}
			resetItems(3214);
		}
	}

	public void deleteItem2(final int id, final int amount) {
		int am = amount;
		for (int i = 0; i < c.playerItems.length; i++) {
			if (am == 0) {
				break;
			}
			if (c.playerItems[i] == id + 1) {
				if (c.playerItemsN[i] > amount) {
					c.playerItemsN[i] -= amount;
					break;
				} else {
					c.playerItems[i] = 0;
					c.playerItemsN[i] = 0;
					am--;
				}
			}
		}
		resetItems(3214);
	}

	/**
	 * Drop all items for your killer
	 **/
	public void dropAllItems() {
		final Player o = PlayerHandler.players[c.killerId];
		@SuppressWarnings("unused")
		int random = Misc.random(32);
		for (int i = 0; i < c.playerItems.length; i++) {
			if (o != null) {
				if (tradeable(c.playerItems[i] - 1)) {
					Server.itemHandler.createGroundItem(o,
							c.playerItems[i] - 1, c.getX(), c.getY(),
							c.playerItemsN[i], c.killerId);
				} else {
					if (specialCase(c.playerItems[i] - 1)) {
						Server.itemHandler.createGroundItem(o, 995, c.getX(),
								c.getY(),
								getUntradePrice(c.playerItems[i] - 1),
								c.killerId);
					}
					Server.itemHandler.createGroundItem(c,
							c.playerItems[i] - 1, c.getX(), c.getY(),
							c.playerItemsN[i], c.playerId);
				}
			} else {
				Server.itemHandler.createGroundItem(c, c.playerItems[i] - 1,
						c.getX(), c.getY(), c.playerItemsN[i], c.playerId);
			}
		}
		for (int e = 0; e < c.playerEquipment.length; e++) {
			if (o != null) {
				
				if (tradeable(c.playerEquipment[e])) {
					Server.itemHandler.createGroundItem(o,
							c.playerEquipment[e], c.getX(), c.getY(),
							c.playerEquipmentN[e], c.killerId);
				} else {
					if (specialCase(c.playerEquipment[e])) {
						Server.itemHandler.createGroundItem(o, 995, c.getX(),
								c.getY(),
								getUntradePrice(c.playerEquipment[e]),
								c.killerId);
					}
					Server.itemHandler.createGroundItem(c,
							c.playerEquipment[e], c.getX(), c.getY(),
							c.playerEquipmentN[e], c.playerId);
				}
			} else {
				Server.itemHandler.createGroundItem(c, c.playerEquipment[e],
						c.getX(), c.getY(), c.playerEquipmentN[e], c.playerId);
			}
		}
		if (o != null) {
			Server.itemHandler.createGroundItem(o, 526, c.getX(), c.getY(), 1,
					c.killerId);
			//dropAllItemsPVP();
		}
		
	}

	/**
	 * Dropping Arrows
	 **/
	public void dropArrowNpc() {
		if (c.playerEquipment[c.playerCape] == 10499 || c.playerEquipment[c.playerCape] == 14001 
		 || c.playerEquipment[c.playerCape] == 14002  || c.playerEquipment[c.playerCape] == 14003 
		 || c.playerEquipment[c.playerCape] == 14003  || c.playerEquipment[c.playerCape] == 14004 
		 || c.playerEquipment[c.playerCape] == 14005  || c.playerEquipment[c.playerCape] == 14006 
		 || c.playerEquipment[c.playerCape] == 14007 || c.playerEquipment[c.playerCape] == 14008 
		 || c.playerEquipment[c.playerCape] == 14009  || c.playerEquipment[c.playerCape] == 14010 
		 || c.playerEquipment[c.playerCape] == 20769  || c.playerEquipment[c.playerCape] == 20771) {
		if (Misc.random(2) == 1) {
			return;
		} else {
		final int enemyX = NPCHandler.npcs[c.oldNpcIndex].getX();
		final int enemyY = NPCHandler.npcs[c.oldNpcIndex].getY();
		if (Misc.random(10) >= 4) {
			if (Server.itemHandler.itemAmount(c.rangeItemUsed, enemyX, enemyY) == 0) {
				Server.itemHandler.createGroundItem(c, c.rangeItemUsed, enemyX,
						enemyY, 1, c.getId());
			} else if (Server.itemHandler.itemAmount(c.rangeItemUsed, enemyX,
					enemyY) != 0) {
				final int amount = Server.itemHandler.itemAmount(
						c.rangeItemUsed, enemyX, enemyY);
				Server.itemHandler.removeGroundItem(c, c.rangeItemUsed, enemyX,
						enemyY, false);
				Server.itemHandler.createGroundItem(c, c.rangeItemUsed, enemyX,
						enemyY, amount + 1, c.getId());
					}
				}
			}
		}
	}

	public void dropArrowPlayer() {
		final int enemyX = PlayerHandler.players[c.oldPlayerIndex].getX();
		final int enemyY = PlayerHandler.players[c.oldPlayerIndex].getY();
		if (c.playerEquipment[c.playerCape] == 10499 || c.playerEquipment[c.playerCape] == 14001 
		 || c.playerEquipment[c.playerCape] == 14002  || c.playerEquipment[c.playerCape] == 14003 
		 || c.playerEquipment[c.playerCape] == 14003  || c.playerEquipment[c.playerCape] == 14004 
		 || c.playerEquipment[c.playerCape] == 14005  || c.playerEquipment[c.playerCape] == 14006 
		 || c.playerEquipment[c.playerCape] == 14007 || c.playerEquipment[c.playerCape] == 14008 
		 || c.playerEquipment[c.playerCape] == 14009  || c.playerEquipment[c.playerCape] == 14010 
		 || c.playerEquipment[c.playerCape] == 20769  || c.playerEquipment[c.playerCape] == 20771) {
			return;
		}
		if (Misc.random(10) >= 4) {
			if (Server.itemHandler.itemAmount(c.rangeItemUsed, enemyX, enemyY) == 0) {
				Server.itemHandler.createGroundItem(c, c.rangeItemUsed, enemyX,
						enemyY, 1, c.getId());
			} else if (Server.itemHandler.itemAmount(c.rangeItemUsed, enemyX,
					enemyY) != 0) {
				final int amount = Server.itemHandler.itemAmount(
						c.rangeItemUsed, enemyX, enemyY);
				Server.itemHandler.removeGroundItem(c, c.rangeItemUsed, enemyX,
						enemyY, false);
				Server.itemHandler.createGroundItem(c, c.rangeItemUsed, enemyX,
						enemyY, amount + 1, c.getId());
			}
		}
	}

	public int findItem(final int id, final int[] items, final int[] amounts) {
		for (int i = 0; i < c.playerItems.length; i++) {
			if (items[i] - 1 == id && amounts[i] > 0) {
				return i;
			}
		}
		return -1;
	}

	

	public int freeSlots() {
		int freeS = 0;
		for (final int playerItem : c.playerItems) {
			if (playerItem <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	

	public void getBonus() {
		for (final int element : c.playerEquipment) {
			if (element > -1) {
				for (int j = 0; j < Config.ITEM_LIMIT; j++) {
					if (Server.itemHandler.ItemList[j] != null) {
						if (Server.itemHandler.ItemList[j].itemId == element) {
							for (int k = 0; k < c.playerBonus.length; k++) {
								c.playerBonus[k] += Server.itemHandler.ItemList[j].Bonuses[k];
							}
							break;
						}
					}
				}
			}
		}
	}

	public int getItemAmount(final int ItemID) {
		int itemCount = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] - 1 == ItemID) {
				itemCount += c.playerItemsN[i];
			}
		}
		return itemCount;
	}

	public int getItemCount(final int itemID) {
		int count = 0;
		for (int j = 0; j < c.playerItems.length; j++) {
			if (c.playerItems[j] == itemID + 1) {
				count += c.playerItemsN[j];
			}
		}
		return count;
	}

	public int getItemId(final String itemName) {
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemName
						.equalsIgnoreCase(itemName)) {
					return Server.itemHandler.ItemList[i].itemId;
				}
			}
		}
		return -1;
	}

	public String getItemName(final int ItemID) {
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == ItemID) {
					return Server.itemHandler.ItemList[i].itemName;
				}
			}
		}
		return "Unarmed";
	}

	public int getItemSlot(final int ItemID) {
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] - 1 == ItemID) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Weapon Requirements
	 **/
	public void getRequirements(String itemName, int itemId) {
		c.attackLevelReq = c.defenceLevelReq = c.strengthLevelReq = c.rangeLevelReq = c.magicLevelReq = 0;
		if (itemId == 14799) {
			c.rangeLevelReq = 75;
			c.defenceLevelReq = 85;
		}
		if (itemId == 14793) {
			c.magicLevelReq = 75;
			c.defenceLevelReq = 85;
		}
		if (itemId == 14787) {
			c.attackLevelReq = 75;
			c.defenceLevelReq = 85;
		}
		if (itemId == 1065) { // green
			c.rangeLevelReq = 40;
			return;
		}
		if (itemId == 2489) { // red
			c.rangeLevelReq = 60;
			return;
		}
		if (itemId == 1065) { // blue
			c.rangeLevelReq = 50;
			return;
		}
		if (itemId == 21371) {
			c.attackLevelReq = 85;
		}
		if (itemName.contains("mystic") || itemName.contains("nchanted")) {
			if (itemName.contains("Staff of light")) {
				c.magicLevelReq = 75;
				c.attackLevelReq = 75;
			}
			if (itemName.contains("staff")) {
				c.magicLevelReq = 20;
				c.attackLevelReq = 40;
			} else {
				c.magicLevelReq = 20;
				c.defenceLevelReq = 20;
			}
		}

		if (itemName.contains("infinity")) {
			c.magicLevelReq = 50;
			c.defenceLevelReq = 25;
		}
		if (itemName.contains("splitbark")) {
			c.magicLevelReq = 40;
			c.defenceLevelReq = 40;
		}
		if (itemName.contains("rune crossbow")) {
			c.rangeLevelReq = 61;
		}
		if (itemName.contains("Saradomin bow")) {
			c.rangeLevelReq = 65;
		}
		if (itemName.contains("Zamorak bow")) {
			c.rangeLevelReq = 65;
		}
		if (itemName.contains("Guthix bow")) {
			c.rangeLevelReq = 65;
		}
		if (itemName.contains("blue d'hide")) {
			c.rangeLevelReq = 50;
		}
		if (itemName.contains("red d'hide")) {
			c.rangeLevelReq = 60;
		}
		if (itemName.contains("black d'hide")) {
			c.rangeLevelReq = 70;
		}
		if (itemName.contains("virtus")) {
			c.magicLevelReq = 80;
			c.defenceLevelReq = 80;
		}
		if (itemName.contains("vanguard")) {
			c.strengthLevelReq = 85;
			c.defenceLevelReq = 85;
		}
		if (itemName.contains("torva")) {
			c.attackLevelReq = 80;
			c.defenceLevelReq = 80;
		}
		if (itemName.contains("pernix")) {
			c.rangeLevelReq = 80;
			c.defenceLevelReq = 80;
		}
		if (itemName.contains("tzhaar-ket-om")) {
			c.strengthLevelReq = 60;
		}
		if (itemName.contains("red d'hide")) {
			c.rangeLevelReq = 60;
		}
		if (itemName.contains("blue d'hide")) {
			c.rangeLevelReq = 50;
		}
		if (itemName.contains("green d'hide")) {
			c.rangeLevelReq = 40;
		}
		if (itemName.contains("snakeskin")) {
			c.rangeLevelReq = 40;
			c.defenceLevelReq = 30;
		}
		if (itemName.contains("initiate")) {
			c.defenceLevelReq = 20;
		}
		if (itemName.contains("bronze")) {
			if (!itemName.contains("knife") && !itemName.contains("dart")
					&& !itemName.contains("javelin")
					&& !itemName.contains("thrownaxe")) {
				c.attackLevelReq = c.defenceLevelReq = 1;
			}
			return;
		}
		if (itemName.contains("iron")) {
			if (!itemName.contains("knife") && !itemName.contains("dart")
					&& !itemName.contains("javelin")
					&& !itemName.contains("thrownaxe")) {
				c.attackLevelReq = c.defenceLevelReq = 1;
			}
			return;
		}
		if (itemName.contains("steel")) {
			if (!itemName.contains("knife") && !itemName.contains("dart")
					&& !itemName.contains("javelin")
					&& !itemName.contains("thrownaxe")) {
				c.attackLevelReq = c.defenceLevelReq = 5;
			}
			return;
		}
		if (itemName.contains("black")) {
			if (!itemName.contains("knife") && !itemName.contains("dart")
					&& !itemName.contains("javelin")
					&& !itemName.contains("thrownaxe")
					&& !itemName.contains("vamb") && !itemName.contains("chap")) {
				c.attackLevelReq = c.defenceLevelReq = 10;
			}
			return;
		}
		if (itemName.contains("mithril")) {
			if (!itemName.contains("knife") && !itemName.contains("dart")
					&& !itemName.contains("javelin")
					&& !itemName.contains("thrownaxe")) {
				c.attackLevelReq = c.defenceLevelReq = 20;
			}
			return;
		}
		if (itemName.contains("adamant")) {
			if (!itemName.contains("knife") && !itemName.contains("dart")
					&& !itemName.contains("javelin")
					&& !itemName.contains("thrownaxe")) {
				c.attackLevelReq = c.defenceLevelReq = 30;
			}
			return;
		}
		if (itemName.contains("rune")) {
			if (!itemName.contains("knife") && !itemName.contains("dart")
					&& !itemName.contains("javelin")
					&& !itemName.contains("thrownaxe")
					&& !itemName.contains("crossbow")) {
				c.attackLevelReq = c.defenceLevelReq = 40;
			}
			return;
		}
		if (itemName.contains("rc hood")) {
			c.defenceLevelReq = 1;
		}
		if (itemName.contains("granite shield")) {
			if (!itemName.contains("maul")) {
				c.defenceLevelReq = 50;
			}
			return;
		}
		if (itemName.contains("granite maul")) {
			if (!itemName.contains("shield")) {
				c.attackLevelReq = 50;
			}
			return;
		}
		if (itemName.contains("warrior")) {
			if (!itemName.contains("ring")) {
				c.defenceLevelReq = 40;
			}
			return;
		}
		if (itemName.contains("dragonfire")) {

			c.defenceLevelReq = 75;

		}
		if (itemName.contains("runecrafting hood")) {

			c.defenceLevelReq = 1;

		}
		if (itemName.contains("enchanted")) {

			c.defenceLevelReq = 40;

		}
		if (itemName.contains("d'hide")) {
			if (!itemName.contains("chaps")) {
				c.defenceLevelReq = c.rangeLevelReq = 40;
			}
			return;
		}
		if (itemName.contains("Bandos platebody") || itemName.contains("Bandos platelegs") || itemName.contains("Bandos plateskirt") || itemName.contains("Bandos full helm") || itemName.contains("Bandos kiteshield")) {

			c.defenceLevelReq = 40;
		}
		if (itemName.contains("Armadyl platebody") || itemName.contains("Armadyl platelegs") || itemName.contains("Armadyl plateskirt") || itemName.contains("Armadyl full helm") || itemName.contains("Armadyl kiteshield")) {

			c.defenceLevelReq = 40;
		}
		if (itemName.contains("Ancient platebody") || itemName.contains("Ancient platelegs") || itemName.contains("Ancient plateskirt") || itemName.contains("Ancient full helm") || itemName.contains("Ancient kiteshield")) {

			c.defenceLevelReq = 40;
		}
		if (itemName.contains("dragon dagger")) {

			c.attackLevelReq = 60;
		}
		if (itemName.contains("drag dagger")) {

			c.attackLevelReq = 60;
		}
		if (itemName.contains("ancient")) {

			c.attackLevelReq = 50;
		}
		if (itemName.contains("hardleather")) {

			c.defenceLevelReq = 10;
		}
		if (itemName.contains("studded")) {

			c.defenceLevelReq = 20;
		}
		if (itemName.contains("bandos")) {
			if (!itemName.contains("godsword")) {
				c.strengthLevelReq = c.defenceLevelReq = 65;
				return;
			}
		}
		if (itemName.contains("dragon")) {
			if (!itemName.contains("nti-") && !itemName.contains("fire")) {
				c.attackLevelReq = c.defenceLevelReq = 60;
				return;
			}
		}
		if (itemName.contains("crystal")) {
			if (itemName.contains("shield")) {
				c.defenceLevelReq = 70;
			} else {
				c.rangeLevelReq = 70;
			}
			return;
		}
		if (itemName.contains("ahrim")) {
			if (itemName.contains("staff")) {
				c.magicLevelReq = 70;
				c.attackLevelReq = 70;
			} else {
				c.magicLevelReq = 70;
				c.defenceLevelReq = 70;
			}
		}
		if (itemName.contains("karil")) {
			if (itemName.contains("crossbow")) {
				c.rangeLevelReq = 70;
			} else {
				c.rangeLevelReq = 70;
				c.defenceLevelReq = 70;
			}
		}
		if (itemName.contains("armadyl")) {
			if (itemName.contains("godsword")) {
				c.attackLevelReq = 75;
			} else {
				c.rangeLevelReq = c.defenceLevelReq = 65;
			}
		}
		if (itemName.contains("saradomin")) {
			if (itemName.contains("sword")) {
				c.attackLevelReq = 70;
			}
			if (itemName.contains("crozier")) {
				c.attackLevelReq = 1;
				if (itemName.contains("robe")) {
					c.attackLevelReq = 1;

				} else {
					c.defenceLevelReq = 40;

				}
			}
		}
		if (itemName.contains("godsword")) {
			c.attackLevelReq = 75;
		}
		if (itemName.contains("3rd age") && !itemName.contains("amulet")) {
			c.defenceLevelReq = 60;
		}

		/**
		 * Dungeoneering Requirements
		 */
		if (itemName.contains("Primal")) {
			if (itemName.contains("sword") || itemName.contains("axe")
					|| itemName.contains("warhammer")
					|| itemName.contains("spear")
					|| itemName.contains("rapier")
					|| itemName.contains("dagger")) {
				c.attackLevelReq = 99;
			} else if (itemName.contains("maul")) {
				c.strengthLevelReq = 99;
			} else {
				c.defenceLevelReq = 99;
			}
		}
		if (itemName.contains("promethium")) {
			if (itemName.contains("sword") || itemName.contains("axe")
					|| itemName.contains("warhammer")
					|| itemName.contains("spear")
					|| itemName.contains("rapier")
					|| itemName.contains("dagger")) {
				c.attackLevelReq = 90;
			} else if (itemName.contains("maul")) {
				c.strengthLevelReq = 90;
			} else {
				c.defenceLevelReq = 90;
			}
		}
		if (itemName.contains("gorgonite")) {
			if (itemName.contains("sword") || itemName.contains("axe")
					|| itemName.contains("warhammer")
					|| itemName.contains("spear")
					|| itemName.contains("rapier")
					|| itemName.contains("dagger")) {
				c.attackLevelReq = 80;
			} else if (itemName.contains("maul")) {
				c.strengthLevelReq = 80;
			} else {
				c.defenceLevelReq = 80;
			}
		}
		if (itemName.contains("katagon")) {
			if (itemName.contains("sword") || itemName.contains("axe")
					|| itemName.contains("warhammer")
					|| itemName.contains("spear")
					|| itemName.contains("rapier")
					|| itemName.contains("dagger")) {
				c.attackLevelReq = 70;
			} else if (itemName.contains("maul")) {
				c.strengthLevelReq = 70;
			} else {
				c.defenceLevelReq = 70;
			}
		}
		if (itemName.contains("argonite")) {
			if (itemName.contains("sword") || itemName.contains("axe")
					|| itemName.contains("warhammer")
					|| itemName.contains("spear")
					|| itemName.contains("rapier")
					|| itemName.contains("dagger")) {
				c.attackLevelReq = 60;
			} else if (itemName.contains("maul")) {
				c.strengthLevelReq = 60;
			} else {
				c.defenceLevelReq = 60;
			}
		}
		if (itemName.contains("zephyrium")) {
			if (itemName.contains("sword") || itemName.contains("axe")
					|| itemName.contains("warhammer")
					|| itemName.contains("spear")
					|| itemName.contains("rapier")
					|| itemName.contains("dagger")) {
				c.attackLevelReq = 50;
			} else if (itemName.contains("maul")) {
				c.strengthLevelReq = 50;
			} else {
				c.defenceLevelReq = 50;
			}
		}
		if (itemName.contains("fractite")) {
			if (itemName.contains("sword") || itemName.contains("axe")
					|| itemName.contains("warhammer")
					|| itemName.contains("spear")
					|| itemName.contains("rapier")
					|| itemName.contains("dagger")) {
				c.attackLevelReq = 40;
			} else if (itemName.contains("maul")) {
				c.strengthLevelReq = 40;
			} else {
				c.defenceLevelReq = 40;
			}
		}
		if (itemName.contains("kratonite")) {
			if (itemName.contains("sword") || itemName.contains("axe")
					|| itemName.contains("warhammer")
					|| itemName.contains("spear")
					|| itemName.contains("rapier")
					|| itemName.contains("dagger")) {
				c.attackLevelReq = 30;
			} else if (itemName.contains("maul")) {
				c.strengthLevelReq = 30;
			} else {
				c.defenceLevelReq = 30;
			}
		}
		if (itemName.contains("marmaros")) {
			if (itemName.contains("sword") || itemName.contains("axe")
					|| itemName.contains("warhammer")
					|| itemName.contains("spear")
					|| itemName.contains("rapier")
					|| itemName.contains("dagger")) {
				c.attackLevelReq = 20;
			} else if (itemName.contains("maul")) {
				c.strengthLevelReq = 20;
			} else {
				c.defenceLevelReq = 20;
			}
		}
		if (itemName.contains("bathus")) {
			if (itemName.contains("sword") || itemName.contains("axe")
					|| itemName.contains("warhammer")
					|| itemName.contains("rapier")
					|| itemName.contains("spear")
					|| itemName.contains("dagger")) {
				c.attackLevelReq = 10;
			} else if (itemName.contains("maul")) {
				c.strengthLevelReq = 10;
			} else {
				c.defenceLevelReq = 10;
			}
		}

		if (itemName.contains("verac") || itemName.contains("guthan")
				|| itemName.contains("dharok") || itemName.contains("torag")) {

			if (itemName.contains("hammers")) {
				c.attackLevelReq = 70;
				c.strengthLevelReq = 70;
			} else if (itemName.contains("axe")) {
				c.attackLevelReq = 70;
				c.strengthLevelReq = 70;
			} else if (itemName.contains("warspear")) {
				c.attackLevelReq = 70;
				c.strengthLevelReq = 70;
			} else if (itemName.contains("flail")) {
				c.attackLevelReq = 70;
				c.strengthLevelReq = 70;
			} else {
				c.defenceLevelReq = 70;
			}
		}

		switch (itemId) {
		case 8839:
		case 8840:
		case 8842:
		case 11663:
		case 11664:
		case 11665:
		case 19785:
		case 19786:
		case 19787:
		case 19788:
		case 19789:
		case 19790:
		case 19803:
		case 19804:
			c.attackLevelReq = 42;
			c.rangeLevelReq = 42;
			c.strengthLevelReq = 42;
			c.magicLevelReq = 42;
			c.defenceLevelReq = 42;
			return;
		case 6528:
			c.strengthLevelReq = 60;
			return;
		case 18357: //Chaotic crossbow
			c.rangeLevelReq = 80;
			return;
		case 10551:
		case 2501:
		case 2499:
		case 1135:
			c.defenceLevelReq = 40;
			return;
		case 9767:
			c.defenceLevelReq = 1;
			return;
		case 11235:
		case 15701:
		case 15702:
		case 15703:
		case 15704:
		case 6522:
			c.rangeLevelReq = 60;
			break;
		case 10555:
		case 10449:
		case 10448:
		case 10447:
			c.defenceLevelReq = 42;
			break;
		case 10378: // god armour
		case 10382:
		case 10386:
		case 10390:
		case 10370:
		case 10374:
			c.defenceLevelReq = 40;
			break;
		case 6524:
			c.defenceLevelReq = 60;
			break;
		case 18333:
			c.magicLevelReq = 30;
			break;
		case 18334:
			c.magicLevelReq = 55;
			break;
		case 18335:
			c.magicLevelReq = 70;
			break;
		case 18359:
			c.defenceLevelReq = 80;
			break;
		case 18361:
			c.defenceLevelReq = 80;
			c.rangeLevelReq = 80;
			break;
		case 18363:
			c.defenceLevelReq = 80;
			c.magicLevelReq = 80;
			break;
		case 18349: // Chaotic
		case 18351: // Chaotic
		case 18353: // Chaotic
			c.attackLevelReq = 80;
			break;
		case 2503:
			c.defenceLevelReq = 40;
			c.rangeLevelReq = 70;
			break;
		case 19780:
			c.attackLevelReq = 70;
			c.strengthLevelReq = 70;
			break;
		case 13739:
		case 13741:
		case 13740:
		case 13738:
		case 13742:
		case 13743:
		case 13744:
		case 13745:
			c.defenceLevelReq = 75;
			c.magicLevelReq = 65;
			break;
		case 15024:
		case 15025:
			c.defenceLevelReq = 40;
			break;
		case 11284:
		case 11283:
			c.defenceLevelReq = 75;
			return;
		case 15241: // hand cannon
			c.rangeLevelReq = 61;
			break;
		case 6889:
		case 6914:
			c.magicLevelReq = 60;
			break;
		case 13858: // zurial robes
		case 13861:
		case 13864:
			c.magicLevelReq = 78;
			c.defenceLevelReq = 78;
			break;
		case 13867: // zurial staff
			c.attackLevelReq = 78;
			c.magicLevelReq = 78;
			break;
		case 13870: // morrigans
		case 13873:
		case 13876:
			c.defenceLevelReq = 78;
			c.rangeLevelReq = 78;
			break;
		case 13879: // morrigan weapons
		case 13883:
			c.rangeLevelReq = 78;
			break;
		case 13884: // stat and vesta arm
		case 13887:
		case 13890:
		case 13893:
		case 13896:
			c.defenceLevelReq = 78;
			break;
		case 13899: // vesta and stat weps
		case 13902:
		case 13905:
			c.attackLevelReq = 78;
			break;
		case 15486:
			c.magicLevelReq = 40;
			c.attackLevelReq = 40;
			break;
		case 861:
			c.rangeLevelReq = 50;
			break;
		case 15015:
		case 15016:
			c.rangeLevelReq = 78;
			break;
		case 10828:
			c.defenceLevelReq = 55;
			break;
		case 11724:
		case 11726:
		case 11728:
			c.defenceLevelReq = 65;
			break;
		case 3751:
		case 3749:
		case 3755:
			c.defenceLevelReq = 40;
			break;

		case 7462:
		case 7461:
			c.defenceLevelReq = 40;
			break;
		case 8846:
			c.defenceLevelReq = 5;
			break;
		case 8847:
			c.defenceLevelReq = 10;
			break;
		case 8848:
			c.defenceLevelReq = 20;
			break;
		case 8849:
			c.defenceLevelReq = 30;
			break;

		case 8850:
			c.defenceLevelReq = 40;
			break;
		case 10887:
			c.strengthLevelReq = 60;
			break;

		case 7460:
			c.defenceLevelReq = 20;
			break;

		case 837:
			c.rangeLevelReq = 61;
			break;

		case 4151: // if you don't want to use names
			c.attackLevelReq = 70;
			return;

		case 15441: // if you don't want to use names
			c.attackLevelReq = 70;
			return;
		case 15442: // if you don't want to use names
			c.attackLevelReq = 70;
			return;
		case 14936:
		case 14938:
			c.defenceLevelReq = 20;
			return;
		case 15443: // if you don't want to use names
			c.attackLevelReq = 70;
			return;

		case 15444: // if you don't want to use names
			c.attackLevelReq = 70;
			return;

		case 6724: // seercull
			c.rangeLevelReq = 60; // idk if that is correct
			return;
		case 14484: // dclaw
			c.attackLevelReq = 60;
			return;
		case 4153:
			c.attackLevelReq = 50;
			c.strengthLevelReq = 50;
			return;
		}
	}

	public int getTotalCount(int itemID) {
		int count = 0;	
		for (int j = 0; j < c.playerItems.length; j++) {
			if (Item.itemIsNote[itemID+1]) {
				if (itemID+2 == c.playerItems[j])
					count += c.playerItemsN[j];
			} 
			if (!Item.itemIsNote[itemID+1]) {
				if (itemID+1 == c.playerItems[j]) {
					count += c.playerItemsN[j];
				}
			}
		}
		for (int j = 0; j < c.bankItems.length; j++) {
			if (c.bankItems[j] == itemID + 1) {
				count += c.bankItemsN[j];
			}		
		}
		return count;
	}
	
	public void itemOnInterface(int id, int amount) {
		// synchronized(c) {
		c.getOutStream().createFrameVarSizeWord(53);
		c.getOutStream().writeWord(2274);
		c.getOutStream().writeWord(1);
		if (amount > 254) {
			c.getOutStream().writeByte(255);
			c.getOutStream().writeDWord_v2(amount);
		} else {
			c.getOutStream().writeByte(amount);
		}
		c.getOutStream().writeWordBigEndianA(id);
		c.getOutStream().endFrameVarSizeWord();
		c.flushOutStream();
		// }
	}

	public void resetBank() {
		// synchronized(c) {
		if (c.searchTerm.equals("FLAG")) {
			return;
		}
		c.getOutStream().createFrameVarSizeWord(53);
		c.getOutStream().writeWord(5382); // bank
		c.getOutStream().writeWord(Config.BANK_SIZE);
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankingItemsN[i] > 254) {
				c.getOutStream().writeByte(255);
				c.getOutStream().writeDWord_v2(c.bankingItemsN[i]);
			} else {
				c.getOutStream().writeByte(c.bankingItemsN[i]);
			}
			if (c.bankingItemsN[i] < 1) {
				c.bankingItems[i] = 0;
			}
			if (c.bankingItems[i] > Config.ITEM_LIMIT || c.bankingItems[i] < 0) {
				c.bankingItems[i] = Config.ITEM_LIMIT;
			}
			c.getOutStream().writeWordBigEndianA(c.bankingItems[i]);
		}
		c.getOutStream().endFrameVarSizeWord();
		c.flushOutStream();
		// }
	}

	public void resetBank(int[] itemData, int[] amountData) {
		// synchronized(c) {
		c.getOutStream().createFrameVarSizeWord(53);
		c.getOutStream().writeWord(5382); // bank
		c.getOutStream().writeWord(Config.BANK_SIZE);
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (amountData[i] > 254) {
				c.getOutStream().writeByte(255);
				c.getOutStream().writeDWord_v2(amountData[i]);
			} else {
				c.getOutStream().writeByte(amountData[i]);
			}
			if (amountData[i] < 1) {
				itemData[i] = 0;
			}
			if (itemData[i] > Config.ITEM_LIMIT || itemData[i] < 0) {
				itemData[i] = Config.ITEM_LIMIT;
			}
			c.getOutStream().writeWordBigEndianA(itemData[i]);
		}
		c.getOutStream().endFrameVarSizeWord();
		c.flushOutStream();
		// }
	}

	
	public void toTab(int tab, int fromSlot) {
		if (tab == c.bankingTab)
			return;
		if (tab > c.getPA().getTabCount() + 1)
			return;
		if (c.searchTerm != "N/A")
			return;
		if(c.getPA().getBankItems(tab) >= 350) {
			c.sendMessage("You can't store any more items in this tab!");
			return;
		}
		int id = c.bankingItems[fromSlot];
		/*if(getTotalCount(id) == 0)
			return;//player doesn't have item!*/
		int amount = c.bankingItemsN[fromSlot];
		int[] invItems = new int[28];
		int[] invItemsN = new int[28];
		for (int i = 0; i < c.playerItems.length; i++) {
			invItems[i] = c.playerItems[i];
			invItemsN[i] = c.playerItemsN[i];
			c.playerItems[i] = 0;
			c.playerItemsN[i] = 0;
		}
		c.playerItems[0] = id;
		c.playerItemsN[0] = amount;
		c.bankingItems[fromSlot] = -1;
		c.bankingItemsN[fromSlot] = 0;
		if (tab == 0)
			bankItem(id, 0, amount, c.bankItems, c.bankItemsN);
		else if (tab == 1)
			bankItem(id, 0, amount, c.bankItems1, c.bankItems1N);
		else if (tab == 2)
			bankItem(id, 0, amount, c.bankItems2, c.bankItems2N);
		else if (tab == 3)
			bankItem(id, 0, amount, c.bankItems3, c.bankItems3N);
		else if (tab == 4)
			bankItem(id, 0, amount, c.bankItems4, c.bankItems4N);
		else if (tab == 5)
			bankItem(id, 0, amount, c.bankItems5, c.bankItems5N);
		else if (tab == 6)
			bankItem(id, 0, amount, c.bankItems6, c.bankItems6N);
		else if (tab == 7)
			bankItem(id, 0, amount, c.bankItems7, c.bankItems7N);
		else if (tab == 8)
			bankItem(id, 0, amount, c.bankItems8, c.bankItems8N);
		for (int i = 0; i < invItems.length; i++) {
			c.playerItems[i] = invItems[i];
			c.playerItemsN[i] = invItemsN[i];
		}
		c.getPA().openUpBank(c.bankingTab);// refresh
		c.getPA().openUpBank(c.bankingTab);// refresh twice to ensure update
	}

	public boolean bankItem(int itemID, int fromSlot, int amount, int[] array,
			int[] arrayN) {
		if (c.inPartyRoom == true) {
			Server.partyRoom.offerItem(c, itemID, amount);
			return false;
		}
		if (c.inTrade) {
			c.sendMessage("You can't store items while trading!");
			return false;
		}
		if (c.playerItems[fromSlot] <= 0 || c.playerItemsN[fromSlot] <= 0) {
			return false;
		}
		if (!Item.itemIsNote[c.playerItems[fromSlot] - 1]) {
			if (c.playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[c.playerItems[fromSlot] - 1]
					|| c.playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (array[i] == c.playerItems[fromSlot]) {
						if (c.playerItemsN[fromSlot] < amount)
							amount = c.playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (array[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					array[toBankSlot] = c.playerItems[fromSlot];
					if (c.playerItemsN[fromSlot] < amount) {
						amount = c.playerItemsN[fromSlot];
					}
					if ((arrayN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (arrayN[toBankSlot] + amount) > -1) {
						arrayN[toBankSlot] += amount;
					} else {
						c.sendMessage("Bank full!");
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if ((arrayN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (arrayN[toBankSlot] + amount) > -1) {
						arrayN[toBankSlot] += amount;
					} else {
						c.sendMessage("Bank full!");
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			} else {
				itemID = c.playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (array[i] == c.playerItems[fromSlot]) {
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (array[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							array[toBankSlot] = c.playerItems[firstPossibleSlot];
							arrayN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							arrayN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			}
		} else if (Item.itemIsNote[c.playerItems[fromSlot] - 1]
				&& !Item.itemIsNote[c.playerItems[fromSlot] - 2]) {
			if (c.playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[c.playerItems[fromSlot] - 1]
					|| c.playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (array[i] == (c.playerItems[fromSlot] - 1)) {
						if (c.playerItemsN[fromSlot] < amount)
							amount = c.playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (array[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					array[toBankSlot] = (c.playerItems[fromSlot] - 1);
					if (c.playerItemsN[fromSlot] < amount) {
						amount = c.playerItemsN[fromSlot];
					}
					if ((arrayN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (arrayN[toBankSlot] + amount) > -1) {
						arrayN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if ((arrayN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (arrayN[toBankSlot] + amount) > -1) {
						arrayN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			} else {
				itemID = c.playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (array[i] == (c.playerItems[fromSlot] - 1)) {
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (array[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							array[toBankSlot] = (c.playerItems[firstPossibleSlot] - 1);
							arrayN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetTempItems();
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							arrayN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetTempItems();
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			}
		} else {
			c.sendMessage("Item not supported " + (c.playerItems[fromSlot] - 1));
			return false;
		}
	}
	public int getTabforItem(int itemID)
	{
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankItems[i] == itemID || c.bankItems[i] == itemID+1 || c.bankItems[i] == itemID-1)
				return 0;
			else if (c.bankItems1[i] == itemID || c.bankItems1[i] == itemID+1 || c.bankItems1[i] == itemID-1)
				return 1;
			else if (c.bankItems2[i] == itemID || c.bankItems2[i] == itemID+1 || c.bankItems2[i] == itemID-1)
				return 2;
			else if (c.bankItems3[i] == itemID || c.bankItems3[i] == itemID+1 || c.bankItems3[i] == itemID-1)
				return 3;
			else if (c.bankItems4[i] == itemID || c.bankItems4[i] == itemID+1 || c.bankItems4[i] == itemID-1)
				return 4;
			else if (c.bankItems5[i] == itemID || c.bankItems5[i] == itemID+1 || c.bankItems5[i] == itemID-1)
				return 5;
			else if (c.bankItems6[i] == itemID || c.bankItems6[i] == itemID+1 || c.bankItems6[i] == itemID-1)
				return 6;
			else if (c.bankItems7[i] == itemID || c.bankItems7[i] == itemID+1 || c.bankItems7[i] == itemID-1)
				return 7;
			else if (c.bankItems8[i] == itemID || c.bankItems8[i] == itemID+1 || c.bankItems8[i] == itemID-1)
				return 8;
		}
		return c.bankingTab;//if not in bank add to current tab
	}

	public boolean bankItem(int itemID, int fromSlot, int amount) {
		c.getPA().openUpBank(getTabforItem(itemID));//Move to tab item is in before adding
		if (c.inTrade) {
			c.sendMessage("You can't store items while trading!");
			return false;
		}
		if(c.getPA().getBankItems(c.bankingTab) >= 350) {
			c.sendMessage("You can't store any more items in this tab!");
			return false;
		}
		if (c.playerItems[fromSlot] <= 0 || c.playerItemsN[fromSlot] <= 0) {
			return false;
		}
		if (!Item.itemIsNote[c.playerItems[fromSlot] - 1]) {
			if (c.playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[c.playerItems[fromSlot] - 1]
					|| c.playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (c.bankingItems[i] == c.playerItems[fromSlot]) {
						if (c.playerItemsN[fromSlot] < amount)
							amount = c.playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (c.bankingItems[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					c.bankingItems[toBankSlot] = c.playerItems[fromSlot];
					if (c.playerItemsN[fromSlot] < amount) {
						amount = c.playerItemsN[fromSlot];
					}
					if ((c.bankingItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (c.bankingItemsN[toBankSlot] + amount) > -1) {
						c.bankingItemsN[toBankSlot] += amount;
					} else {
						c.sendMessage("Bank full!");
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetTempItems();
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if ((c.bankingItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (c.bankingItemsN[toBankSlot] + amount) > -1) {
						c.bankingItemsN[toBankSlot] += amount;
					} else {
						c.sendMessage("Bank full!");
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetTempItems();
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			} else {
				itemID = c.playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (c.bankingItems[i] == c.playerItems[fromSlot]) {
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (c.bankingItems[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							c.bankingItems[toBankSlot] = c.playerItems[firstPossibleSlot];
							c.bankingItemsN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetTempItems();
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							c.bankingItemsN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetTempItems();
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			}
		} else if (Item.itemIsNote[c.playerItems[fromSlot] - 1]
				&& !Item.itemIsNote[c.playerItems[fromSlot] - 2]) {
			if (c.playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[c.playerItems[fromSlot] - 1]
					|| c.playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (c.bankingItems[i] == (c.playerItems[fromSlot] - 1)) {
						if (c.playerItemsN[fromSlot] < amount)
							amount = c.playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (c.bankingItems[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					c.bankingItems[toBankSlot] = (c.playerItems[fromSlot] - 1);
					if (c.playerItemsN[fromSlot] < amount) {
						amount = c.playerItemsN[fromSlot];
					}
					if ((c.bankingItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (c.bankingItemsN[toBankSlot] + amount) > -1) {
						c.bankingItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetTempItems();
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if ((c.bankingItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (c.bankingItemsN[toBankSlot] + amount) > -1) {
						c.bankingItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetTempItems();
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			} else {
				itemID = c.playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (c.bankingItems[i] == (c.playerItems[fromSlot] - 1)) {
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (c.bankingItems[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							c.bankingItems[toBankSlot] = (c.playerItems[firstPossibleSlot] - 1);
							c.bankingItemsN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetTempItems();
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							c.bankingItemsN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetTempItems();
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			}
		} else {
			c.sendMessage("Item not supported " + (c.playerItems[fromSlot] - 1));
			return false;
		}
	}

	public int freeBankSlots() {
		return Config.BANK_SIZE - c.getPA().getBankItems(-1);
	}

	public void fromBank(int itemID, int fromSlot, int amount) {
	if (!c.isBanking) {
               c.getPA().closeAllWindows();
              return;
          }
		if (c.searchTerm != null && !c.searchTerm.equals("FLAG")
				&& !c.searchTerm.equals("N/A")) {
			// c.sendMessage("search shit " + c.searchTerm);
			// c.sendMessage("lookin' for " + itemID + " / " + c.searchTerm);
			String temp = c.searchTerm;
			c.searchTerm = "FLAG";
			int tempT = c.bankingTab;
			int collect = amount;
			// c.sendMessage("@" + c.searchTerm + " " + amount + " " + itemID);
			// String item = "";
			for (int i = 0; i < c.getPA().tempItems.length; i++) {
				// item += (c.bankingItems[i] + " ");
				if (c.getPA().tempItems[i] == itemID + 1
						|| c.getPA().tempItems[i] == itemID) {
					int count = Math.min(c.getPA().tempItemsN[i], collect);
					if (collect == -1)
						count = c.getPA().tempItemsN[i];
					// c.sendMessage("taking " + (itemID));
					c.bankingTab = (c.getPA().tempItemsT[i]);
					if(c.bankingTab == 0) {
						c.bankingItems = c.bankItems;
						c.bankingItemsN = c.bankItemsN;
					}
					if(c.bankingTab == 1) {
						c.bankingItems = c.bankItems1;
						c.bankingItemsN = c.bankItems1N;
					}
					if(c.bankingTab == 2) {
						c.bankingItems = c.bankItems2;
						c.bankingItemsN = c.bankItems2N;
					}
					if(c.bankingTab == 3) {
						c.bankingItems = c.bankItems3;
						c.bankingItemsN = c.bankItems3N;
					}
					if(c.bankingTab == 4) {
						c.bankingItems = c.bankItems4;
						c.bankingItemsN = c.bankItems4N;
					}
					if(c.bankingTab == 5) {
						c.bankingItems = c.bankItems5;
						c.bankingItemsN = c.bankItems5N;
					}
					if(c.bankingTab == 6) {
						c.bankingItems = c.bankItems6;
						c.bankingItemsN = c.bankItems6N;
					}
					if(c.bankingTab == 7) {
						c.bankingItems = c.bankItems7;
						c.bankingItemsN = c.bankItems7N;
					}
					if(c.bankingTab == 8) {
						c.bankingItems = c.bankItems8;
						c.bankingItemsN = c.bankItems8N;
					}
					//c.getPA().openUpBank(c.bankingTab);
					c.getItems().fromBank(itemID + 1, c.getPA().tempItemsS[i], count);
					// c.sendMessage("Collecting " + itemID);
					collect -= count;
				}/*
				 * else { c.sendMessage(c.bankingItems[i] + " " + itemID); }
				 */
			}
			// for(int i = 0; i < item.length(); i+= 30) {
			// c.sendMessage(item.substring(i, i+30));
			// }
			// System.out.println("Done with collecting " + itemID);
			c.bankingTab = tempT;
			c.searchTerm = temp;
			// c.sendMessage("Ur search term was " + temp +
			// " so backed it up :3");
		} else {
			if (amount > 0) {
				if (c.bankingItems[fromSlot] > 0) {
					if (!c.takeAsNote) {
						if (Item.itemStackable[c.bankingItems[fromSlot] - 1]) {
							if (c.bankingItemsN[fromSlot] > amount) {
								if (addItem((c.bankingItems[fromSlot] - 1),
										amount)) {
									c.bankingItemsN[fromSlot] -= amount;
									resetBank();
									c.getItems().resetItems(5064);
								}
							} else {
								if (addItem((c.bankingItems[fromSlot] - 1),
										c.bankingItemsN[fromSlot])) {
									c.bankingItems[fromSlot] = 0;
									c.bankingItemsN[fromSlot] = 0;
									resetBank();
									c.getItems().resetItems(5064);
								}
							}
						} else {
							while (amount > 0) {
								if (c.bankingItemsN[fromSlot] > 0) {
									if (addItem((c.bankingItems[fromSlot] - 1),
											1)) {
										c.bankingItemsN[fromSlot] += -1;
										amount--;
									} else {
										amount = 0;
									}
								} else {
									amount = 0;
								}
							}
							resetBank();
							c.getItems().resetItems(5064);
						}
					} else if (c.takeAsNote
							&& Item.itemIsNote[c.bankingItems[fromSlot]]) {
						if (c.bankingItemsN[fromSlot] > amount) {
							if (addItem(c.bankingItems[fromSlot], amount)) {
								c.bankingItemsN[fromSlot] -= amount;
								resetBank();
								c.getItems().resetItems(5064);
							}
						} else {
							if (addItem(c.bankingItems[fromSlot],
									c.bankingItemsN[fromSlot])) {
								c.bankingItems[fromSlot] = 0;
								c.bankingItemsN[fromSlot] = 0;
								resetBank();
								c.getItems().resetItems(5064);
							}
						}
					} else {
						c.sendMessage("This item can't be withdrawn as a note.");
						if (Item.itemStackable[c.bankingItems[fromSlot] - 1]) {
							if (c.bankingItemsN[fromSlot] > amount) {
								if (addItem((c.bankingItems[fromSlot] - 1),
										amount)) {
									c.bankingItemsN[fromSlot] -= amount;
									resetBank();
									c.getItems().resetItems(5064);
								}
							} else {
								if (addItem((c.bankingItems[fromSlot] - 1),
										c.bankingItemsN[fromSlot])) {
									c.bankingItems[fromSlot] = 0;
									c.bankingItemsN[fromSlot] = 0;
									resetBank();
									c.getItems().resetItems(5064);
								}
							}
						} else {
							while (amount > 0) {
								if (c.bankingItemsN[fromSlot] > 0) {
									if (addItem((c.bankingItems[fromSlot] - 1),
											1)) {
										c.bankingItemsN[fromSlot] += -1;
										amount--;
									} else {
										amount = 0;
									}
								} else {
									amount = 0;
								}
							}
							resetBank();
							c.getItems().resetItems(5064);
						}
					}
				}
			}
		}
		if(c.searchTerm.equals("N/A"))
			c.getPA().openUpBank(c.bankingTab);
	}

	public int itemAmount(int itemID) {
		int tempAmount = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] == itemID) {
				tempAmount += c.playerItemsN[i];
			}
		}
		return tempAmount;
	}
	

	public int getUnnotedItem(final int ItemID) {
		int NewID = ItemID - 1;
		String NotedName = "";
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == ItemID) {
					NotedName = Server.itemHandler.ItemList[i].itemName;
				}
			}
		}
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemName == NotedName) {
					if (Server.itemHandler.ItemList[i].itemDescription
							.startsWith("Swap this note at any bank for a") == false) {
						NewID = Server.itemHandler.ItemList[i].itemId;
						break;
					}
				}
			}
		}
		return NewID;
	}

	public int getUntradePrice(final int item) {
		switch (item) {
		case 2518:
		case 2524:
		case 2526:
			return 100000;
		case 2520:
		case 2522:
			return 150000;
		}
		return 0;
	}

	public void handleSpecialPickup(final int itemId) {
		// c.sendMessage("My " + getItemName(itemId) +
		// " has been recovered. I should talk to the void knights to get it back.");
		// c.getItems().addToVoidList(itemId);
	}

	public boolean hasAllShards() {
		return playerHasItem(11712, 1) && playerHasItem(11712, 1)
				&& playerHasItem(11714, 1);
	}

	/**
	 * two handed weapon check
	 **/
	public boolean is2handed(final String itemName, final int itemId) {
		if (itemName.contains("ahrim") || itemName.contains("karil")
				|| itemName.contains("verac") || itemName.contains("guthan")
				|| itemName.contains("dharok") || itemName.contains("torag")) {
			return true;
		}
		if (itemName.contains("longbow") || itemName.contains("shortbow")
				|| itemName.contains("ark bow") || itemName.contains("hand cannon")) {
			return true;
		}
		if (itemName.contains("crystal")) {
			return true;
		}
		if (itemName.contains("maul") || itemName.contains("godsword")
				|| itemName.contains("aradomin sword")
				|| itemName.contains("2h") || itemName.contains("spear") || itemName.contains("katana")) {
			return true;
		}
		switch (itemId) {
		case 6724: // seercull
		case 11730:
		case 4153:
		case 6528:
		case 14484:
			return true;
		}

		return false;
	}

	public boolean isHilt(final int i) {
		return i >= 11702 && i <= 11708 && i % 2 == 0;
	}

	public static boolean isStackable(final int itemID) {
		return Item.itemStackable[itemID];
	}

	

	public String itemType(final int item) {
		if (Item.playerCape(item)) {
			return "cape";
		}
		if (Item.playerBoots(item)) {
			return "boots";
		}
		if (Item.playerGloves(item)) {
			return "gloves";
		}
		if (Item.playerShield(item)) {
			return "shield";
		}
		if (Item.playerAmulet(item)) {
			return "amulet";
		}
		if (Item.playerArrows(item)) {
			return "arrows";
		}
		if (Item.playerRings(item)) {
			return "ring";
		}
		if (Item.playerHats(item)) {
			return "hat";
		}
		if (Item.playerLegs(item)) {
			return "legs";
		}
		if (Item.playerBody(item)) {
			return "body";
		}
		return "weapon";
	}

	/**
	 * Item kept on death
	 **/
	public void keepItem(final int keepItem, final boolean deleteItem) {
		int value = 0;
		int item = 0;
		int slotId = 0;
		boolean itemInInventory = false;
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] - 1 > 0) {
				final int inventoryItemValue = c.getShops().getItemShopValue(
						c.playerItems[i] - 1);
				if (inventoryItemValue > value && !c.invSlot[i]) {
					value = inventoryItemValue;
					item = c.playerItems[i] - 1;
					slotId = i;
					itemInInventory = true;
				}
			}
		}
		for (int i1 = 0; i1 < c.playerEquipment.length; i1++) {
			if (c.playerEquipment[i1] > 0) {
				final int equipmentItemValue = c.getShops().getItemShopValue(
						c.playerEquipment[i1]);
				if (equipmentItemValue > value && !c.equipSlot[i1]) {
					value = equipmentItemValue;
					item = c.playerEquipment[i1];
					slotId = i1;
					itemInInventory = false;
				}
			}
		}
		if (itemInInventory) {
			c.invSlot[slotId] = true;
			if (deleteItem) {
				deleteItem(c.playerItems[slotId] - 1,
						getItemSlot(c.playerItems[slotId] - 1), 1);
			}
		} else {
			c.equipSlot[slotId] = true;
			if (deleteItem) {
				deleteEquipment(item, slotId);
			}
		}
		c.itemKeptId[keepItem] = item;
	}

	public void makeBlade() {
		deleteItem(11710, 1);
		deleteItem(11712, 1);
		deleteItem(11714, 1);
		addItem(11690, 1);
		c.sendMessage("You combine the shards to make a blade.");
	}

	public void makeGodsword(final int i) {
		if (playerHasItem(11690) && playerHasItem(i)) {
			deleteItem(11690, 1);
			deleteItem(i, 1);
			addItem(i - 8, 1);
			c.sendMessage("You combine the hilt and the blade to make a godsword.");
		}
	}

	/**
	 * Banking
	 */

	public void resetAnims() {
		c.playerStandIndex = 0x328;
		c.playerTurnIndex = 0x337;
		c.playerWalkIndex = 0x333;
		c.playerTurn180Index = 0x334;
		c.playerTurn90CWIndex = 0x335;
		c.playerTurn90CCWIndex = 0x336;
		c.playerRunIndex = 0x338;
	}

	/**
	 * Banks our equipment for us
	 */
	public void bankEquipment() {
		for (int i = 0; i < c.playerEquipment.length; i++) {
			if (c.playerEquipment[i] > 0 && c.playerEquipmentN[i] > 0)
				c.getItems().addItemToBank(c.playerEquipment[i],
						c.playerEquipmentN[i]);
			c.getItems().replaceEquipment(i, -1);
		}
		resetAnims();
	}

	/**
	 * Adds an item to the bank without checking if the player has it in there
	 * inventory
	 * 
	 * @param itemId
	 *            the id of the item were banking
	 * @param amount
	 *            amount of items to bank
	 */
	public void addItemToBank(int itemId, int amount) {
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankItems[i] <= 0 || c.bankItems[i] == itemId + 1
					&& c.bankItemsN[i] + amount < Integer.MAX_VALUE) {
				c.bankItems[i] = itemId + 1;
				c.bankItemsN[i] += amount;
				resetBank();
				return;
			}
		}
	}

	/**
	 * Replaces an equipment item with the specified replaceItem and updates
	 * player
	 * 
	 * @param slot
	 *            equipment id slot
	 * @param replaceItem
	 *            the new item
	 */
	public void replaceEquipment(int slot, int replaceItem) {
		if (c.playerEquipment[slot] > 0) {
			c.playerEquipment[slot] = replaceItem;
			if (replaceItem <= 0) {
				c.playerEquipmentN[slot] = 0;
				c.updateRequired = true;
				c.getPA().requestUpdates();
				c.setAppearanceUpdateRequired(true);
			}
			c.getItems().updateSlot(slot);
			resetBonus();
			getBonus();
			writeBonus();
		}
	}

	/**
	 * Move Items
	 **/
	public void moveItems(final int from, final int to, final int moveWindow) {
		if (moveWindow == 3724) {
			int tempI;
			int tempN;
			tempI = c.playerItems[from];
			tempN = c.playerItemsN[from];

			c.playerItems[from] = c.playerItems[to];
			c.playerItemsN[from] = c.playerItemsN[to];
			c.playerItems[to] = tempI;
			c.playerItemsN[to] = tempN;
		}
		if (moveWindow == 34453 && from >= 0 && to >= 0
				&& from < Config.BANK_SIZE && to < Config.BANK_SIZE
				&& to < Config.BANK_SIZE) {
			int tempI;
			int tempN;
			tempI = c.bankItems[from];
			tempN = c.bankItemsN[from];

			c.bankItems[from] = c.bankItems[to];
			c.bankItemsN[from] = c.bankItemsN[to];
			c.bankItems[to] = tempI;
			c.bankItemsN[to] = tempN;
		}
		if (moveWindow == 34453) {
			resetBank();
		}
		if (moveWindow == 18579) {
			int tempI;
			int tempN;
			tempI = c.playerItems[from];
			tempN = c.playerItemsN[from];

			c.playerItems[from] = c.playerItems[to];
			c.playerItemsN[from] = c.playerItemsN[to];
			c.playerItems[to] = tempI;
			c.playerItemsN[to] = tempN;
			resetItems(3214);
		}
		resetTempItems();
		if (moveWindow == 3724) {
			resetItems(3214);
		}
	}

	public boolean ownsCape() {
		if (c.getItems().playerHasItem(2412, 1)
				|| c.getItems().playerHasItem(2413, 1)
				|| c.getItems().playerHasItem(2414, 1)
				|| c.getItems().playerHasItem(2417, 1)
				|| c.getItems().playerHasItem(2416, 1)
				|| c.getItems().playerHasItem(2415, 1)) {
			return true;
		}
		for (int j = 0; j < Config.BANK_SIZE; j++) {
			if (c.bankItems[j] == 2412 || c.bankItems[j] == 2413
					|| c.bankItems[j] == 2414) {
				return true;
			}
		}
		if (c.playerEquipment[c.playerCape] == 2413
				|| c.playerEquipment[c.playerCape] == 2414
				|| c.playerEquipment[c.playerCape] == 2415) {
			return true;
		}
		return false;
	}
	
	public boolean hasBankItem(int itemID) {
		for (int i1 = 0; i1 < c.bankItems.length; i1++) {
			if(c.bankItems[i1]-1 == itemID && c.bankItemsN[i1] >= 1) {
				return true;
			}
		}
		return false;
	}

	public boolean playerHasItem(int itemID) {
		itemID++;
		for (final int playerItem : c.playerItems) {
			if (playerItem == itemID) {
				return true;
			}
		}
		return false;
	}

	public boolean playerHasItem(int itemID, final int amt) {
		itemID++;
		int found = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] == itemID) {
				if (c.playerItemsN[i] >= amt) {
					return true;
				} else {
					found++;
				}
			}
		}
		if (found >= amt) {
			return true;
		}
		return false;
	}

	public boolean playerHasItem(int itemID, final int amt, final int slot) {
		itemID++;
		int found = 0;
		if (c.playerItems[slot] == itemID) {
			for (int i = 0; i < c.playerItems.length; i++) {
				if (c.playerItems[i] == itemID) {
					if (c.playerItemsN[i] >= amt) {
						return true;
					} else {
						found++;
					}
				}
			}
			if (found >= amt) {
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * BANK
	 */
	public void rearrangeBank() {
		int totalItems = 0;
		int highestSlot = 0;
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankItems[i] != 0) {
				totalItems++;
				if (highestSlot <= i) {
					highestSlot = i;
				}
			}
		}
		for (int i = 0; i <= highestSlot; i++) {
			if (c.bankItems[i] == 0) {
				boolean stop = false;

				for (int k = i; k <= highestSlot; k++) {
					if (c.bankItems[k] != 0 && !stop) {
						final int spots = k - i;
						for (int j = k; j <= highestSlot; j++) {
							c.bankItems[j - spots] = c.bankItems[j];
							c.bankItemsN[j - spots] = c.bankItemsN[j];
							stop = true;
							c.bankItems[j] = 0;
							c.bankItemsN[j] = 0;
						}
					}
				}
			}
		}
		int totalItemsAfter = 0;
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankItems[i] != 0) {
				totalItemsAfter++;
			}
		}
		if (totalItems != totalItemsAfter) {
			c.disconnected = true;
		}
	}

	public void removeAllItems() {
		for (int i = 0; i < c.playerItems.length; i++) {
			c.playerItems[i] = 0;
		}
		for (int i = 0; i < c.playerItemsN.length; i++) {
			c.playerItemsN[i] = 0;
		}
		resetItems(3214);
	}

	/**
	 * Pickup Item
	 **/
	public void removeGroundItem(final int itemID, final int itemX,
			final int itemY, final int Amount) {
		c.getOutStream().createFrame(85);
		c.getOutStream().writeByteC(itemY - 8 * c.mapRegionY);
		c.getOutStream().writeByteC(itemX - 8 * c.mapRegionX);
		c.getOutStream().createFrame(156);
		c.getOutStream().writeByteS(0);
		c.getOutStream().writeWord(itemID);
		c.flushOutStream();
	}

	/**
	 * Remove Item
	 **/
	public void removeItem(final int wearID, final int slot) {
		if (c.getOutStream() != null && c != null) {
			if (c.playerEquipment[slot] > -1 && c.playerEquipment[slot] == wearID) {
				if (addItem(c.playerEquipment[slot], c.playerEquipmentN[slot])) {
					c.playerEquipment[slot] = -1;
					c.playerEquipmentN[slot] = 0;
					sendWeapon(c.playerEquipment[c.playerWeapon],
							getItemName(c.playerEquipment[c.playerWeapon]));
					resetBonus();
					getBonus();
					writeBonus();
					c.getCombat().getPlayerAnimIndex(
							c.getItems()
							.getItemName(
									c.playerEquipment[c.playerWeapon])
									.toLowerCase());
					c.getOutStream().createFrame(34);
					c.getOutStream().writeWord(6);
					c.getOutStream().writeWord(1688);
					c.getOutStream().writeByte(slot);
					c.getOutStream().writeWord(0);
					c.getOutStream().writeByte(0);
					c.flushOutStream();
					c.updateRequired = true;
					c.setAppearanceUpdateRequired(true);
				}
			}
		}
	}

	

	public void resetBonus() {
		for (int i = 0; i < c.playerBonus.length; i++) {
			c.playerBonus[i] = 0;
		}
	}

	public void resetItems(final int WriteFrame) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(WriteFrame);
			c.getOutStream().writeWord(c.playerItems.length);
			for (int i = 0; i < c.playerItems.length; i++) {
				if (c.playerItemsN[i] > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord_v2(c.playerItemsN[i]);
				} else {
					c.getOutStream().writeByte(c.playerItemsN[i]);
				}
				c.getOutStream().writeWordBigEndianA(c.playerItems[i]);
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}

	/**
	 * Reset items kept on death
	 **/
	public void resetKeepItems() {
		for (int i = 0; i < c.itemKeptId.length; i++) {
			c.itemKeptId[i] = -1;
		}
		for (int i1 = 0; i1 < c.invSlot.length; i1++) {
			c.invSlot[i1] = false;
		}
		for (int i2 = 0; i2 < c.equipSlot.length; i2++) {
			c.equipSlot[i2] = false;
		}
	}

	public void ResetKeepItems() {
		for (int i = 0; i < c.itemKeptId.length; i++) {
			c.itemKeptId[i] = -1;
		}
		for (int i1 = 0; i1 < c.invSlot.length; i1++) {
			c.invSlot[i1] = false;
		}
		for (int i2 = 0; i2 < c.equipSlot.length; i2++) {
			c.equipSlot[i2] = false;
		}
	}

	public void resetTempItems() {
		int itemCount = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] > -1) {
				itemCount = i;
			}
		}
		c.getOutStream().createFrameVarSizeWord(53);
		c.getOutStream().writeWord(5064);
		c.getOutStream().writeWord(itemCount + 1);
		for (int i = 0; i < itemCount + 1; i++) {
			if (c.playerItemsN[i] > 254) {
				c.getOutStream().writeByte(255);
				c.getOutStream().writeDWord_v2(c.playerItemsN[i]);
			} else {
				c.getOutStream().writeByte(c.playerItemsN[i]);
			}
			if (c.playerItems[i] > Config.ITEM_LIMIT || c.playerItems[i] < 0) {
				c.playerItems[i] = Config.ITEM_LIMIT;
			}
			c.getOutStream().writeWordBigEndianA(c.playerItems[i]);
		}
		c.getOutStream().endFrameVarSizeWord();
		c.flushOutStream();
	}

	public void sendItemsKept() {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(6963);
			c.getOutStream().writeWord(c.itemKeptId.length);
			for (int i = 0; i < c.itemKeptId.length; i++) {
				if (c.playerItemsN[i] > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord_v2(1);
				} else {
					c.getOutStream().writeByte(1);
				}
				if (c.itemKeptId[i] > 0) {
					c.getOutStream().writeWordBigEndianA(c.itemKeptId[i] + 1);
				} else {
					c.getOutStream().writeWordBigEndianA(0);
				}
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}

	/**
	 * Wear Item
	 **/
	public void sendWeapon(final int Weapon, final String WeaponName) {
		String WeaponName2 = WeaponName.replaceAll("Bronze", "");
		WeaponName2 = WeaponName2.replaceAll("Iron", "");
		WeaponName2 = WeaponName2.replaceAll("Steel", "");
		WeaponName2 = WeaponName2.replaceAll("Black", "");
		WeaponName2 = WeaponName2.replaceAll("Mithril", "");
		WeaponName2 = WeaponName2.replaceAll("Adamant", "");
		WeaponName2 = WeaponName2.replaceAll("Rune", "");
		WeaponName2 = WeaponName2.replaceAll("Granite", "");
		WeaponName2 = WeaponName2.replaceAll("Dragon", "");
		WeaponName2 = WeaponName2.replaceAll("Drag", "");
		WeaponName2 = WeaponName2.replaceAll("Crystal", "");
		WeaponName2 = WeaponName2.trim();
		if (WeaponName.equals("Unarmed")) {
			c.setSidebarInterface(0, 5855); // punch, kick, block
			c.getPA().sendFrame126(WeaponName, 5857);
		} else if (WeaponName.endsWith("whip")) {
			c.setSidebarInterface(0, 12290); // flick, lash, deflect
			c.getPA().sendFrame246(12291, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 12293);
		} else if (WeaponName.endsWith("bow") || WeaponName.endsWith("10")
				|| WeaponName.endsWith("full")
				|| WeaponName.startsWith("seercull")
				|| c.playerEquipment[c.playerWeapon] == 15241) {
			c.setSidebarInterface(0, 1764); // accurate, rapid, longrange
			c.getPA().sendFrame246(1765, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 1767);
		} else if (WeaponName.endsWith("staff") || WeaponName.endsWith("wand")) {
			c.setSidebarInterface(0, 328); // spike, impale, smash, block
			c.getPA().sendFrame246(329, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 331);
		} else if (WeaponName2.startsWith("dart")
				|| WeaponName2.startsWith("knife")
				|| WeaponName2.startsWith("javelin")
				|| WeaponName.equalsIgnoreCase("toktz-xil-ul")) {
			c.setSidebarInterface(0, 4446); // accurate, rapid, longrange
			c.getPA().sendFrame246(4447, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 4449);
		} else if (WeaponName.startsWith("Staff")
				|| WeaponName2.startsWith("dagger")
				|| WeaponName2.contains("sword")) {
			c.setSidebarInterface(0, 2276); // stab, lunge, slash, block
			c.getPA().sendFrame246(2277, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 2279);
		} else if (WeaponName2.contains("claws")) {
			c.setSidebarInterface(0, 7762);
			c.getPA().sendFrame246(7763, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 7765);
		} else if (WeaponName2.startsWith("pickaxe")) {
			c.setSidebarInterface(0, 5570); // spike, impale, smash, block
			c.getPA().sendFrame246(5571, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 5573);
		} else if (WeaponName2.startsWith("axe")
				|| WeaponName2.startsWith("battleaxe")) {
			c.setSidebarInterface(0, 1698); // chop, hack, smash, block
			c.getPA().sendFrame246(1699, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 1701);
		} else if (WeaponName2.startsWith("halberd")) {
			c.setSidebarInterface(0, 8460); // jab, swipe, fend
			c.getPA().sendFrame246(8461, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 8463);
		} else if (WeaponName2.startsWith("Scythe")) {
			c.setSidebarInterface(0, 8460); // jab, swipe, fend
			c.getPA().sendFrame246(8461, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 8463);
		} else if (WeaponName2.startsWith("spear")) {
			c.setSidebarInterface(0, 4679); // lunge, swipe, pound, block
			c.getPA().sendFrame246(4680, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 4682);
		} else if (WeaponName2.toLowerCase().contains("mace")) {
			c.setSidebarInterface(0, 3796);
			c.getPA().sendFrame246(3797, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 3799);

		} else if (c.playerEquipment[c.playerWeapon] == 13905) {
			c.setSidebarInterface(0, 4679); // lunge, swipe, pound, block
			c.getPA().sendFrame246(4680, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 4682);
		} else if (c.playerEquipment[c.playerWeapon] == 13902) {
			c.setSidebarInterface(0, 425); // war hamer equip.
			c.getPA().sendFrame246(426, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 428);
		} else if (c.playerEquipment[c.playerWeapon] == 4153) {
			c.setSidebarInterface(0, 425); // war hamer equip.
			c.getPA().sendFrame246(426, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 428);
		} else if (c.playerEquipment[c.playerWeapon] == 18353) {
			c.setSidebarInterface(0, 425); // war hamer equip.
			c.getPA().sendFrame246(426, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 428);
		} else if (c.playerEquipment[c.playerWeapon] == 6527) {
			c.setSidebarInterface(0, 425); // war hamer equip.
			c.getPA().sendFrame246(426, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 428);
		} else {
			c.setSidebarInterface(0, 2423); // chop, slash, lunge, block
			c.getPA().sendFrame246(2424, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 2426);
		}
	}

	/**
	 * Update Equip tab
	 **/
	public void setEquipment(final int wearID, final int amount,
			final int targetSlot) {
		c.getOutStream().createFrameVarSizeWord(34);
		c.getOutStream().writeWord(1688);
		c.getOutStream().writeByte(targetSlot);
		c.getOutStream().writeWord(wearID + 1);
		if (amount > 254) {
			c.getOutStream().writeByte(255);
			c.getOutStream().writeDWord(amount);
		} else {
			c.getOutStream().writeByte(amount);
		}
		c.getOutStream().endFrameVarSizeWord();
		c.flushOutStream();
		c.playerEquipment[targetSlot] = wearID;
		c.playerEquipmentN[targetSlot] = amount;
		c.updateRequired = true;
		c.setAppearanceUpdateRequired(true);
	}

	/**
	 * Specials bar filling amount
	 **/
	public void specialAmount(final int weapon, final double specAmount,
			int barId) {
		c.specBarId = barId;
		c.getPA().sendFrame70(specAmount >= 10 ? 500 : 0, 0, --barId);
		c.getPA().sendFrame70(specAmount >= 9 ? 500 : 0, 0, --barId);
		c.getPA().sendFrame70(specAmount >= 8 ? 500 : 0, 0, --barId);
		c.getPA().sendFrame70(specAmount >= 7 ? 500 : 0, 0, --barId);
		c.getPA().sendFrame70(specAmount >= 6 ? 500 : 0, 0, --barId);
		c.getPA().sendFrame70(specAmount >= 5 ? 500 : 0, 0, --barId);
		c.getPA().sendFrame70(specAmount >= 4 ? 500 : 0, 0, --barId);
		c.getPA().sendFrame70(specAmount >= 3 ? 500 : 0, 0, --barId);
		c.getPA().sendFrame70(specAmount >= 2 ? 500 : 0, 0, --barId);
		c.getPA().sendFrame70(specAmount >= 1 ? 500 : 0, 0, --barId);
		updateSpecialBar();
		sendWeapon(weapon, getItemName(weapon));
	}

	public boolean specialCase(final int itemId) {
		switch (itemId) {
		case 2518:
		case 2520:
		case 2522:
		case 2524:
		case 2526:
			return true;
		}
		return false;
	}

	public static boolean tradeable(final int itemId) {
		for (final int element : Config.ITEM_TRADEABLE) {
			if (itemId == element) {
				return false;
			}
		}
		return true;
	}

	public void updateSlot(final int slot) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrameVarSizeWord(34);
			c.getOutStream().writeWord(1688);
			c.getOutStream().writeByte(slot);
			c.getOutStream().writeWord(c.playerEquipment[slot] + 1);
			if (c.playerEquipmentN[slot] > 254) {
				c.getOutStream().writeByte(255);
				c.getOutStream().writeDWord(c.playerEquipmentN[slot]);
			} else {
				c.getOutStream().writeByte(c.playerEquipmentN[slot]);
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}

	/**
	 * Special attack text and what to highlight or blackout
	 **/
	public void updateSpecialBar() {
		if (c.usingSpecial && c.playerEquipment[c.playerWeapon] != 15050) {
			c.getPA().sendFrame126(
					"@yel@ Special Attack (" + (int) c.specAmount * 10 + "%)",
					c.specBarId);
		} else {
			c.getPA().sendFrame126(
					"@bla@ Special Attack (" + (int) c.specAmount * 10 + "%)",
					c.specBarId);
		}
	}

	/** Old Special Bar **/
	/*
	 * public void updateSpecialBar() { if(c.usingSpecial) {
	 * c.getPA().sendFrame126( ""+(c.specAmount >= 2 ? "@yel@S P" : "@bla@S P")
	 * +""+(c.specAmount >= 3 ? "@yel@ E" : "@bla@ E") +""+(c.specAmount >= 4 ?
	 * "@yel@ C I" : "@bla@ C I") +""+(c.specAmount >= 5 ? "@yel@ A L" :
	 * "@bla@ A L") +""+(c.specAmount >= 6 ? "@yel@  A" : "@bla@  A")
	 * +""+(c.specAmount >= 7 ? "@yel@ T T" : "@bla@ T T") +""+(c.specAmount >=
	 * 8 ? "@yel@ A" : "@bla@ A") +""+(c.specAmount >= 9 ? "@yel@ C" :
	 * "@bla@ C") +""+(c.specAmount >= 10 ? "@yel@ K" : "@bla@ K") ,
	 * c.specBarId); } else {
	 * c.getPA().sendFrame126("@bla@S P E C I A L  A T T A C K", c.specBarId); }
	 * }
	 */

	/**
	 * Wear Item
	 **/
	public boolean wearItem(final int wearID, final int slot) {
		int targetSlot = 0;
		boolean canWearItem = true;
		if (c.playerItems[slot] == wearID + 1) {
			getRequirements(getItemName(wearID).toLowerCase(), wearID);
			targetSlot = Item.targetSlots[wearID];

			if (itemType(wearID).equalsIgnoreCase("cape")) {
				targetSlot = 1;
			} else if (itemType(wearID).equalsIgnoreCase("hat")) {
				targetSlot = 0;
			} else if (itemType(wearID).equalsIgnoreCase("amulet")) {
				targetSlot = 2;
			} else if (itemType(wearID).equalsIgnoreCase("arrows")) {
				targetSlot = 13;
			} else if (itemType(wearID).equalsIgnoreCase("body")) {
				targetSlot = 4;
			} else if (itemType(wearID).equalsIgnoreCase("shield")) {
				targetSlot = 5;
			} else if (itemType(wearID).equalsIgnoreCase("legs")) {
				targetSlot = 7;
			} else if (itemType(wearID).equalsIgnoreCase("gloves")) {
				targetSlot = 9;
			} else if (itemType(wearID).equalsIgnoreCase("boots")) {
				targetSlot = 10;
			} else if (itemType(wearID).equalsIgnoreCase("ring")) {
				targetSlot = 12;
			} else {
				targetSlot = 3;
			}
			switch (wearID) {
			case 5509:
			case 5510:
			case 5511:
			case 5512:
			case 5513:
			case 5514:
			case 15086:
			case 15088:
			case 15090:
			case 15092:
			case 15094:
			case 15096:
			case 15098:
			if (c.xpTitle.equalsIgnoreCase("none")) {
				return false;
			}
				break;
			case 19771:
				targetSlot = 12;
			break;
			
			case 13348:// virtus robe top
			case 13354:// pernix body
			case 13358:// torva plate
			case 13302:// gandormeic top
			case 13673:
				targetSlot = 4;
				break;
			case 13350:// virtus mask
			case 13362:// torva fullhelm
			case 13355:// pernix cowl
			case 13300:
			case 13672:
				targetSlot = 0;
				break;
			case 13346:// virtus legs
			case 13360:// torva legs
			case 13352:// pernix chaps
			case 13304:// ganodermic legs
			case 13674:
				targetSlot = 7;
				break;
			case 9952:
				targetSlot = 1;
				break;
			case 1335:
			case 1339:
			case 1341:
			case 1343:
			case 1345:
			case 1347:
			case 1337: //Bronze warhammer
			case 1351:// bronze hatchet
			case 1349:
			case 1353:
			case 1361:
			case 1355:
			case 1357:
			case 6739:
			case 13661:
			case 1359:// rune hatchet
			case 20671:
			case 21744:
			case 13902://stat hammer
			case 13926://c stat hammer
			case 4726:
			case 4910:
			case 4911:
			case 4912:
			case 4913:
			case 17039:
			case 17037:
			case 17035:
			case 17033:
			case 17031:
			case 17029:
			case 17027:
			case 17025:
			case 17023:
			case 17021:
			case 17019:
			case 16126:
			case 16125:
			case 16124:
			case 16123:
			case 16122:
			case 16121:
			case 16120:
			case 16119:
			case 16118:
			case 16117:
			case 16116:
			case 6613:
				targetSlot = 3;
				break;
			case 14001:
			case 14002:
			case 14003:
			case 14004:
			case 14005:
			case 14006:
			case 14007:
			case 14008:
			case 14009:
			case 14010:
			case 12745:// veteran cape
				targetSlot = 1;
				break;
			case 14793:// boots
			case 14787:
			case 14799:
				targetSlot = 10;
				break;
			case 18346:
				targetSlot = 5;
				break;
			case 4675:
			if (c.DT < 6) {
				c.sendMessage("You must complete <col=255>Desert Treasure</col> to wield this.");
				return false;
			}
				break;
			}
			if (c.duelRule[11] && targetSlot == 0) {
				c.sendMessage("Wearing hats has been disabled in this duel!");
				return false;
			}
			if (c.duelRule[12] && targetSlot == 1) {
				c.sendMessage("Wearing capes has been disabled in this duel!");
				return false;
			}
			if (c.duelRule[13] && targetSlot == 2) {
				c.sendMessage("Wearing amulets has been disabled in this duel!");
				return false;
			}
			if (c.duelRule[14] && targetSlot == 3) {
				c.sendMessage("Wielding weapons has been disabled in this duel!");
				return false;
			}
			if (c.duelRule[15] && targetSlot == 4) {
				c.sendMessage("Wearing bodies has been disabled in this duel!");
				return false;
			}
			if (c.duelRule[16] && targetSlot == 5 || c.duelRule[16]
					&& is2handed(getItemName(wearID).toLowerCase(), wearID)) {
				c.sendMessage("Wearing shield has been disabled in this duel!");
				return false;
			}
			if (c.duelRule[17] && targetSlot == 7) {
				c.sendMessage("Wearing legs has been disabled in this duel!");
				return false;
			}
			if (c.duelRule[18] && targetSlot == 9) {
				c.sendMessage("Wearing gloves has been disabled in this duel!");
				return false;
			}
			if (c.duelRule[19] && targetSlot == 10) {
				c.sendMessage("Wearing boots has been disabled in this duel!");
				return false;
			}
			if (c.duelRule[20] && targetSlot == 12) {
				c.sendMessage("Wearing rings has been disabled in this duel!");
				return false;
			}
			if (c.duelRule[21] && targetSlot == 13) {
				c.sendMessage("Wearing arrows has been disabled in this duel!");
				return false;
			}
			if (Config.itemRequirements) {
				if (targetSlot == 10 || targetSlot == 7 || targetSlot == 5
						|| targetSlot == 4 || targetSlot == 0
						|| targetSlot == 9 || targetSlot == 10) {
					if (c.defenceLevelReq > 0) {
						if (c.getPA().getLevelForXP(c.playerXP[1]) < c.defenceLevelReq) {
							c.sendMessage("You need a defence level of "
									+ c.defenceLevelReq + " to wear this item.");
							canWearItem = false;
						}
					}
					if (c.rangeLevelReq > 0) {
						if (c.getPA().getLevelForXP(c.playerXP[4]) < c.rangeLevelReq) {
							c.sendMessage("You need a range level of "
									+ c.rangeLevelReq + " to wear this item.");
							canWearItem = false;
						}
					}
					if (c.magicLevelReq > 0) {
						if (c.getPA().getLevelForXP(c.playerXP[6]) < c.magicLevelReq) {
							c.sendMessage("You need a magic level of "
									+ c.magicLevelReq + " to wear this item.");
							canWearItem = false;
						}
					}
				}
				if (targetSlot == 3) {
					if (c.attackLevelReq > 0) {
						if (c.getPA().getLevelForXP(c.playerXP[0]) < c.attackLevelReq) {
							c.sendMessage("You need an attack level of "
									+ c.attackLevelReq
									+ " to wield this weapon.");
							canWearItem = false;
						}
					}
					if (c.strengthLevelReq > 0) {
						if (c.getPA().getLevelForXP(c.playerXP[2]) < c.strengthLevelReq) {
							c.sendMessage("You need an strength level of "
									+ c.strengthLevelReq
									+ " to wield this weapon.");
							canWearItem = false;
						}
					}
					if (c.rangeLevelReq > 0) {
						if (c.getPA().getLevelForXP(c.playerXP[4]) < c.rangeLevelReq) {
							c.sendMessage("You need a range level of "
									+ c.rangeLevelReq
									+ " to wield this weapon.");
							canWearItem = false;
						}
					}
					if (c.magicLevelReq > 0) {
						if (c.getPA().getLevelForXP(c.playerXP[6]) < c.magicLevelReq) {
							c.sendMessage("You need a magic level of "
									+ c.magicLevelReq
									+ " to wield this weapon.");
							canWearItem = false;
						}
					}
				}
			}
			if (!canWearItem) {
				return false;
			}

			final int wearAmount = c.playerItemsN[slot];
			if (wearAmount < 1) {
				return false;
			}

			if (targetSlot == c.playerWeapon) {
				c.autocasting = false;
				c.autocastId = 0;
				c.getPA().sendFrame36(108, 0);
			}

			if (slot >= 0 && wearID >= 0) {
				final int toEquip = c.playerItems[slot];
				final int toEquipN = c.playerItemsN[slot];
				int toRemove = c.playerEquipment[targetSlot];
				int toRemoveN = c.playerEquipmentN[targetSlot];
				if (toEquip == toRemove + 1 && Item.itemStackable[toRemove]) {
					deleteItem(toRemove, getItemSlot(toRemove), toEquipN);
					c.playerEquipmentN[targetSlot] += toEquipN;
				} else if (toRemove != -1 && Item.itemStackable[toRemove] && c.getItems().playerHasItem(toRemove)) {
					c.playerItems[slot] = 0;
					c.playerItemsN[slot] = 0;
					c.playerItems[c.getItems().getItemSlot(toRemove)] = toRemove + 1;
					c.playerItemsN[c.getItems().getItemSlot(toRemove)] += toRemoveN;
					c.playerEquipment[targetSlot] = toEquip - 1;
					c.playerEquipmentN[targetSlot] = toEquipN;
				} else if (targetSlot != 5 && targetSlot != 3) {
					c.playerItems[slot] = toRemove + 1;
					c.playerItemsN[slot] = toRemoveN;
					c.playerEquipment[targetSlot] = toEquip - 1;
					c.playerEquipmentN[targetSlot] = toEquipN;
				} else if (targetSlot == 5) {
					final boolean wearing2h = is2handed(
							getItemName(c.playerEquipment[c.playerWeapon])
							.toLowerCase(),
							c.playerEquipment[c.playerWeapon]);
					if (wearing2h) {
						toRemove = c.playerEquipment[c.playerWeapon];
						toRemoveN = c.playerEquipmentN[c.playerWeapon];
						c.playerEquipment[c.playerWeapon] = -1;
						c.playerEquipmentN[c.playerWeapon] = 0;
						updateSlot(c.playerWeapon);
					}
					c.playerItems[slot] = toRemove + 1;
					c.playerItemsN[slot] = toRemoveN;
					c.playerEquipment[targetSlot] = toEquip - 1;
					c.playerEquipmentN[targetSlot] = toEquipN;
				} else if (targetSlot == 3) {
					final boolean is2h = is2handed(getItemName(wearID)
							.toLowerCase(), wearID);
					final boolean wearingShield = c.playerEquipment[c.playerShield] > 0;
					final boolean wearingWeapon = c.playerEquipment[c.playerWeapon] > 0;
					if (is2h) {
						if (wearingShield && wearingWeapon) {
							if (freeSlots() > 0) {
								c.playerItems[slot] = toRemove + 1;
								c.playerItemsN[slot] = toRemoveN;
								c.playerEquipment[targetSlot] = toEquip - 1;
								c.playerEquipmentN[targetSlot] = toEquipN;
								removeItem(c.playerEquipment[c.playerShield],
										c.playerShield);
							} else {
								c.sendMessage("You do not have enough inventory space to do this.");
								return false;
							}
						} else if (wearingShield && !wearingWeapon) {
							c.playerItems[slot] = c.playerEquipment[c.playerShield] + 1;
							c.playerItemsN[slot] = c.playerEquipmentN[c.playerShield];
							c.playerEquipment[targetSlot] = toEquip - 1;
							c.playerEquipmentN[targetSlot] = toEquipN;
							c.playerEquipment[c.playerShield] = -1;
							c.playerEquipmentN[c.playerShield] = 0;
							updateSlot(c.playerShield);
						} else {
							c.playerItems[slot] = toRemove + 1;
							c.playerItemsN[slot] = toRemoveN;
							c.playerEquipment[targetSlot] = toEquip - 1;
							c.playerEquipmentN[targetSlot] = toEquipN;
						}
					} else {
						c.playerItems[slot] = toRemove + 1;
						c.playerItemsN[slot] = toRemoveN;
						c.playerEquipment[targetSlot] = toEquip - 1;
						c.playerEquipmentN[targetSlot] = toEquipN;
					}
				}
				resetItems(3214);
			}
			if (targetSlot == 3) {
				c.usingSpecial = false;
				addSpecialBar(wearID);
			}
			if (c.getOutStream() != null && c != null) {
				c.getOutStream().createFrameVarSizeWord(34);
				c.getOutStream().writeWord(1688);
				c.getOutStream().writeByte(targetSlot);
				c.getOutStream().writeWord(wearID + 1);

				if (c.playerEquipmentN[targetSlot] > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord(c.playerEquipmentN[targetSlot]);
				} else {
					c.getOutStream().writeByte(c.playerEquipmentN[targetSlot]);
				}

				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
			}
			sendWeapon(c.playerEquipment[c.playerWeapon],
					getItemName(c.playerEquipment[c.playerWeapon]));
			resetBonus();
			getBonus();
			writeBonus();
			c.getCombat().getPlayerAnimIndex(
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			c.getPA().requestUpdates();
			return true;
		} else {
			return false;
		}
	}

	public void wearItem(final int wearID, final int wearAmount,
			final int targetSlot) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrameVarSizeWord(34);
			c.getOutStream().writeWord(1688);
			c.getOutStream().writeByte(targetSlot);
			c.getOutStream().writeWord(wearID + 1);

			if (wearAmount > 254) {
				c.getOutStream().writeByte(255);
				c.getOutStream().writeDWord(wearAmount);
			} else {
				c.getOutStream().writeByte(wearAmount);
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
			c.playerEquipment[targetSlot] = wearID;
			c.playerEquipmentN[targetSlot] = wearAmount;
			c.getItems()
			.sendWeapon(
					c.playerEquipment[c.playerWeapon],
					c.getItems().getItemName(
							c.playerEquipment[c.playerWeapon]));
			c.getItems().resetBonus();
			c.getItems().getBonus();
			c.getItems().writeBonus();
			c.getCombat().getPlayerAnimIndex(
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			c.updateRequired = true;
			c.setAppearanceUpdateRequired(true);
			if (targetSlot == 3) {
				c.usingSpecial = false;
				addSpecialBar(wearID);
			}
		}
	}

	public void writeBonus() {
		int offset = 0;
		String send = "";
		for (int i = 0; i < c.playerBonus.length; i++) {
			if (c.playerBonus[i] >= 0) {
				send = BONUS_NAMES[i] + ": +" + c.playerBonus[i];
			} else {
				send = BONUS_NAMES[i] + ": -"
						+ java.lang.Math.abs(c.playerBonus[i]);
			}

			if (i == 10) {
				offset = 1;
			}
			c.getPA().sendFrame126(send, 1675 + i + offset);
		}
	}

	public boolean playerHasEquipped(int itemID) {
		itemID++;
		for (int i = 0; i < c.playerEquipment.length; i++) {
			if (c.playerEquipment[i] == itemID) {
				return true;
			}
		}
		return false;
	}

	public void updateInventory() {
		updateInventory = false;
		resetItems(3214);
	}

	public boolean playerHasItem(int[] items) {
		for (int i : items) {
			if(!playerHasItem(i)) {
				return false;
			}
		}
		return true;
	}

	public int getEquipmentNet(Player c) {
		int toReturn = 0;
		for (int i = 0; i < c.playerEquipment.length; i++) {
			toReturn += (c.getShops().getItemShopValue(c.playerEquipment[i]) * c.playerEquipmentN[i]);
		}
		return toReturn;
	}

	public int getInventoryNet(Player c) {
		int toReturn = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			toReturn += (c.getShops().getItemShopValue(c.playerItems[i]+1) * c.playerItemsN[i]);
		}
		return toReturn;
	}
	
	public boolean isWearingItem(int itemID) {
		for(int i = 0; i < 12; i++) {
			if(c.playerEquipment[i] == itemID) {
				return true;
			}
		}
		return false;
	}

	public int getWornItemAmount(int itemID) {
		for(int i = 0; i < 12; i++) {
			if(c.playerEquipment[i] == itemID) {
				return c.playerEquipmentN[i];
			}
		}
		return 0;
	}

}
