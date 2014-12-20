package game.shop;

import game.Config;
import game.Server;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.item.Item;

public class ShopAssistant {

	private final Player c;

	public void openSkillCape() {
		int capes = get99Count();
		if (capes > 1) {
			capes = 1;
		} else {
			capes = 0;
		}
		c.myShopId = 14;
		setupSkillCapes(get99Count());
	}

	public int[] skillCapes = {9747, 9753, 9750, 9768, 9756, 9759, 9762, 9801,
			9807, 9783, 9798, 9804, 9780, 9795, 9792, 9774, 9771, 9777, 9786,
			9810, 9765, 9948, 12169};

	public int get99Count() {
		int count = 0;
		for (int j = 0; j < c.playerLevel.length; j++) {
			if (c.getLevelForXP(c.playerXP[j]) >= 99) {
				count++;
			}
		}
		return count;
	}

	public void setupSkillCapes(int capes2) {
		c.getItems().resetItems(3823);
		c.isShopping = true;
		c.myShopId = 14;
		c.getPA().sendFrame248(3824, 3822);
		c.getPA().sendFrame126("Skillcape Shop", 3901);

		int TotalItems;
		TotalItems = capes2;
		if (TotalItems > ShopHandler.MaxShopItems) {
			TotalItems = ShopHandler.MaxShopItems;
		}
		c.getOutStream().createFrameVarSizeWord(53);
		c.getOutStream().writeWord(3900);
		c.getOutStream().writeWord(TotalItems);
		for (int i = 0; i <= 22; i++) {
			if (c.getLevelForXP(c.playerXP[i]) < 99) {
				continue;
			}
			c.getOutStream().writeByte(1);
			c.getOutStream().writeWordBigEndianA(skillCapes[i] + 2);
		}
		c.getOutStream().endFrameVarSizeWord();
		c.flushOutStream();
	}

	public void skillBuy(int item) {
		int nn = get99Count();
		if (nn > 1) {
			nn = 1;
		} else {
			nn = 0;
		}
		for (int j = 0; j < skillCapes.length; j++) {
			if (skillCapes[j] == item || skillCapes[j] + 1 == item) {
				if (c.getItems().freeSlots() > 1) {
					if (c.getItems().playerHasItem(995, 99000)) {
						if (c.getLevelForXP(c.playerXP[j]) >= 99) {
							c.getItems().deleteItem(995,
									c.getItems().getItemSlot(995), 99000);
							c.getItems().addItem(skillCapes[j] + nn, 1);
							c.getItems().addItem(skillCapes[j] + 2, 1);
						} else {
							c.sendMessage("You must have 99 in the skill of the cape you're trying to buy.");
						}
					} else {
						c.sendMessage("You need 99k to buy this item.");
					}
				} else {
					c.sendMessage("You must have at least 1 inventory spaces to buy this item.");
				}
			}
			/*
			 * if (skillCapes[j][1 + nn] == item) { if (c.getItems().freeSlots()
			 * >= 1) { if (c.getItems().playerHasItem(995,99000)) { if
			 * (c.getLevelForXP(c.playerXP[j]) >= 99) {
			 * c.getItems().deleteItem(995, c.getItems().getItemSlot(995),
			 * 99000); c.getItems().addItem(skillCapes[j] + nn,1);
			 * c.getItems().addItem(skillCapes[j] + 2,1); } else {
			 * c.sendMessage(
			 * "You must have 99 in the skill of the cape you're trying to buy."
			 * ); } } else { c.sendMessage("You need 99k to buy this item."); }
			 * } else { c.sendMessage(
			 * "You must have at least 1 inventory spaces to buy this item."); }
			 * break; }
			 */
		}
		c.getItems().resetItems(3823);
	}

	public ShopAssistant(final Player client) {
		c = client;
	}

	public boolean addShopItem(int itemID, final int amount) {
		boolean Added = false;
		if (amount <= 0) {
			return false;
		}
		if (Item.itemIsNote[itemID] == true) {
			itemID = c.getItems().getUnnotedItem(itemID);
		}
		for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
			if (ShopHandler.ShopItems[c.myShopId][i] - 1 == itemID) {
				ShopHandler.ShopItemsN[c.myShopId][i] += amount;
				Added = true;
			}
		}
		if (Added == false) {
			for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
				if (ShopHandler.ShopItems[c.myShopId][i] == 0) {
					ShopHandler.ShopItems[c.myShopId][i] = itemID + 1;
					ShopHandler.ShopItemsN[c.myShopId][i] = amount;
					ShopHandler.ShopItemsDelay[c.myShopId][i] = 0;
					break;
				}
			}
		}
		return true;
	}

	/**
	 * buy item from shop (Shop Price)
	 **/
	public void buyFromShopPrice(final int removeId, final int removeSlot) {
		int ShopValue = (int) Math.floor(getItemShopValue(removeId, 0,
				removeSlot));
		ShopValue *= 1;
		String ShopAdd = "";
		if (c.myShopId == 17) {
			c.sendMessage(c.getItems().getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " slayer points.");
			return;
		}
		if (c.myShopId == 3) {
			c.sendMessage(c.getItems().getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " donator points.");
			return;
		}
		if (c.myShopId == 19) {
			c.sendMessage(c.getItems().getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " Vote Points.");
			return;
		}
		if (c.myShopId == 22) {
			c.sendMessage(c.getItems().getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " loyalty points.");
			return;
		}
		if (c.myShopId == 27) {
			c.sendMessage(c.getItems().getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " Mage Points.");
			return;
		}
		if (c.myShopId == 28) {
			c.sendMessage(c.getItems().getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " Skilling Points.");
			return;
		}
		if (c.myShopId == 26) {
			c.sendMessage(c.getItems().getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " prestige points.");
			return;
		}
		if (c.myShopId == 23) {
			c.sendMessage(c.getItems().getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " pk points.");
			return;
		}
		if (c.myShopId == 25) {
			c.sendMessage("This item current costs " + getSpecialItemValue(removeId)
					+ "  Tokkul.");
			return;
		}
		if (c.myShopId == 30) {
			c.sendMessage(c.getItems().getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " achievement points.");
			return;
		}
		if (c.myShopId == 6) {
			c.sendMessage(c.getItems().getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " tokens.");
			return;
		}
		if (c.myShopId == 18) {
			c.sendMessage(c.getItems().getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " points.");
			return;
		}
		if (c.myShopId == 15) {
			c.sendMessage("This item current costs "
					+ c.getItems().getUntradePrice(removeId) + " coins.");
			return;
		}
		if (ShopValue >= 1000 && ShopValue < 1000000) {
			ShopAdd = " (" + ShopValue / 1000 + "K)";
		} else if (ShopValue >= 1000000) {
			ShopAdd = " (" + ShopValue / 1000000 + " million)";
		}
		c.sendMessage(c.getItems().getItemName(removeId) + ": currently costs "
				+ ShopValue + " coins" + ShopAdd);
	}

	public boolean buyItem(final int itemID, final int fromSlot, int amount) {
		if (!shopSellsItem(itemID) && c.myShopId != 50 && c.myShopId != 60
				&& c.myShopId != 14) {
			return false;
		}
		if (c.myShopId == 14) {
			skillBuy(itemID);
			return false;
		} else if (c.myShopId == 15) {
			buyVoid(itemID);
			return false;
		} else if(c.myShopId == 1) {
			c.sendMessage("You can only sell items to the general store!");
			return false;
		}
		if (amount > 0) {
			if (amount > ShopHandler.ShopItemsN[c.myShopId][fromSlot]) {
				amount = ShopHandler.ShopItemsN[c.myShopId][fromSlot];
			}
			// double ShopValue;
			// double TotPrice;
			int TotPrice2 = 0;
			// int Overstock;
			int Slot = 0;
			int Slot1 = 0;// Tokkul
			if (c.myShopId == 17 || c.myShopId == 18 || c.myShopId == 19 || c.myShopId == 50
					|| c.myShopId == 60 || c.myShopId == 6 || c.myShopId == 3 
					|| c.myShopId == 23 || c.myShopId == 26 || c.myShopId == 27
					|| c.myShopId == 22 || c.myShopId == 25 || c.myShopId == 28 || c.myShopId == 30) {
				handleOtherShop(itemID);
				return false;
			}
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int) Math.floor(getItemShopValue(itemID, 0,
						fromSlot));
				Slot = c.getItems().getItemSlot(995);
				Slot1 = c.getItems().getItemSlot(6529);
				if (Slot == -1 && c.myShopId != 30 && c.myShopId != 25) {
					c.sendMessage("You don't have enough coins.");
					break;
				}
				if (Slot1 == -1 && c.myShopId == 25) {
					c.sendMessage("You don't have enough tokkul.");
					break;
				}

				if (Slot1 == -1 && c.myShopId == 18 || c.myShopId == 17 || c.myShopId == 3 || c.myShopId == 23
						|| c.myShopId == 26 || c.myShopId == 27 || c.myShopId == 22 || c.myShopId == 30) {
					c.sendMessage("You don't have enough points.");
					break;
				}
				if (TotPrice2 <= 1) {
					TotPrice2 = (int) Math.floor(getItemShopValue(itemID, 0,
							fromSlot));
					TotPrice2 *= 1.66;
				}
				if (c.myShopId != 29 || c.myShopId != 17 || c.myShopId != 25 || c.myShopId != 19 || c.myShopId != 28
						|| c.myShopId != 3 || c.myShopId != 18
						|| c.myShopId != 30 || c.myShopId != 31 || c.myShopId != 23) {
					if (c.playerItemsN[Slot] >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							if (Item.itemStackable[itemID] && c.getItems().playerHasItem(995, TotPrice2*amount)) {
								c.getItems().deleteItem(995, c.getItems().getItemSlot(995), TotPrice2*amount);
								c.getItems().addItem(itemID, amount);
								ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= amount;
								ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
								c.getItems().resetItems(3823);
								resetShop(c.myShopId);
								updatePlayerShop();
								return false;
							}
							c.getItems().deleteItem(995,
									c.getItems().getItemSlot(995), TotPrice2);
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot]--;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if (ShopHandler.ShopItemsN[c.myShopId][fromSlot] < 1) {
								if (c.myShopId == 1) // General store id
									for (int p = 0; p < 99; p++) {
										ShopHandler.ShopItems[c.myShopId][fromSlot
										                                  + p] = ShopHandler.ShopItems[c.myShopId][fromSlot
										                                                                           + 1 + p];
										ShopHandler.ShopItemsN[c.myShopId][fromSlot
										                                   + p] = ShopHandler.ShopItemsN[c.myShopId][fromSlot
										                                                                             + 1 + p];
									}
								else
									ShopHandler.ShopItemsN[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough coins.");
						break;
					}
				}
				if (c.myShopId == 25) {
					if (c.playerItemsN[Slot1] >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							c.getItems().deleteItem(6529,
									c.getItems().getItemSlot(6529), TotPrice2);
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if (fromSlot + 1 > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough tokkul.");
						break;
					}
				}
			}
			c.getItems().resetItems(3823);
			resetShop(c.myShopId);
			updatePlayerShop();
			return true;
		}
		return false;
	}

	public boolean playerHasExactOrLessThan(int itemID, int amt) {
		itemID++;
		for (int i = 0; i < c.playerItems.length; i++)
			if (c.playerItems[i] == itemID)
				if (c.playerItemsN[i] <= amt)
					return true;
		return false;
	}

	public void buyVoid(final int item) {
		/*
		 * if (item > 2527 || item < 2518) return; //c.sendMessage("" + item);
		 * if (c.voidStatus[(item-2518)/2] > 0) { if (c.getItems().freeSlots()
		 * >= 1) { if
		 * (c.getItems().playerHasItem(995,c.getItems().getUntradePrice(item)))
		 * { c.voidStatus[(item-2518)/2]--;
		 * c.getItems().deleteItem(995,c.getItems().getItemSlot(995),
		 * c.getItems().getUntradePrice(item)); c.getItems().addItem(item,1);
		 * openVoid(); } else { c.sendMessage("This item costs " +
		 * c.getItems().getUntradePrice(item) + " coins to rebuy."); } } else {
		 * c.sendMessage("I should have a free inventory space."); } } else {
		 * c.sendMessage
		 * ("I don't need to recover this item from the void knights."); }
		 */
	}

	public int getItemShopValue(int itemId) {
		if (itemId < 0) {
			return 0;
		}
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null)
				if (Item.itemIsNote[itemId] == true && itemId != 0) {
					itemId = itemId - 1;
				}
			if (Server.itemHandler.ItemList[i].itemId == itemId)
				return (int) Server.itemHandler.ItemList[i].ShopValue;
		}
		return 250;
	}

	public double getItemShopValue(int ItemID, int Type, int fromSlot) {
		double ShopValue = 1;
		double TotPrice = 0;
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Item.itemIsNote[ItemID] == true) {
					ItemID = ItemID - 1;
				}
				if (Server.itemHandler.ItemList[i].itemId == ItemID) {
					ShopValue = Server.itemHandler.ItemList[i].ShopValue;
				}
			}
		}

		TotPrice = ShopValue;

		if (ShopHandler.ShopBModifier[c.myShopId] == 1) {
			TotPrice *= 1;
			TotPrice *= 1;
			if (Type == 1) {
				TotPrice *= 1;
			}
		} else if (Type == 1) {
			TotPrice *= 1;
		}
		return TotPrice;
	}

	public int getSpecialItemValue(final int id) {
		switch (id) {
		
		/**
		 * Vote Points Shop
		 */
		case 20929:
			return 50;
		case 20553:
			return 5;
		case 20554:
			return 10;
		case 20555:
			return 15;
		case 20556:
			return 20;
		case 20557:
			return 25;
		case 4566:
			return 60;
		case 15426:
			return 30;
		case 14728:
			return 50;

		/**
		 * Skillpoint Shop
		 */
		case 13667:
		case 13668:
		case 13669:
		case 13670:
			return 16;
		case 15069:
		case 15071:
			return 10;
		case 20949:
		case 20950:
		case 20951:
		case 20952:
			return 25;
		case 12595:
		case 21390:
		case 21391:
			return 15;
		case 21508:
		case 21510:
		case 775:
			return 20;

		/**
		 * Donator Shop
		 */

		case 15039:
		case 15041:
		case 15042:
		case 15043:
		case 15044:
			return 10;

		case 6583:
			return 35;

		case 9470:
		case 10394:
		case 10398:
			return 10;

		case 15674:
			return 45;

			/**
			 * Others
			 */

		case 4155:
			return 150;

		case 7461:
		case 21736:
			return 75;
		case 7460:
			return 70;
		case 7459:
			return 65;
		case 7458:
			return 60;
		case 7457:
			return 55;
		case 7456:
			return 50;
		case 7455:
			return 25;
		case 7454:
			return 10;
		case 7453:
			return 5;

		case 6889:
		case 6914:
			return 200;
		case 4151:
			return 20;
		case 6916:
		case 6918:
		case 6920:
		case 6922:
		case 6924:
			return 50;
		case 11663:
		case 11664:
		case 11665:
		case 8842:
			return 100;
		case 8839:
		case 8840:
		case 21744:
			return 150;
		case 8844: //Bronze Defender
			return 15;
		case 9477:
			return 5;
		case 8845: //Iron Defender
			return 25;
		case 8847: //Black Defender
			return 60;
		case 8848: //Mithril Defender
			return 80;
		case 8849: //Adamant Defender
			return 85;
		case 8850: //Rune Defender
			return 90;
		case 20072: //Dragon Defender
			return 100;
		case 6199:
			return 16;
		case 7462: //Barrows Gloves
		case 11716:
			return 100;
		case 3093:
			if (c.myShopId == 22)
				return 5;
			else
				return 3;
			
		// Achievement Shop //
		case 19757:
			return 60;
		case 19748:
			return 80;
		case 10551: //Fighter Torso
			return 20;
		case 10548: //Fighter Hat
			return 10;
		case 4712:
		case 23531: //Overload Flask
		case 6099:
			return 5;


		case 4714:
			return 10;
		case 11694:
			return 200;
		case 11696:
		case 11698:
		case 11700:
			return 100;
		case 11235:
			return 40;
		case 18349:
		case 18351:
		case 18353:
		case 18355:
		case 18357:
		case 18359:
		case 18361:
		case 18363:
			return 200000;
		case 9952:
			return 80000;
		case 10499:
			return 40;
		case 19669:
			return 50000;
		case 18333:
			return 6500;
		case 18334:
			return 15500;
		case 18335:
			return 30500;
		case 18346:
			return 43000;
		case 18347:
			return 48500;
		case 19893:
			return 45000;
		case 13263:
			return 500;
		case 15486:
			return 550;
		case 15488:
			return 450;
		case 15490:
			return 450;
		
		case 21472: //Vanguard
		case 21473:
		case 21474:
		case 21475:
		case 21476:
			return 50;

		case 19712: //Void Deflector
		case 21752:
		case 21760:
			return 125;
		case 15441:
		case 15442:
		case 15443:
		case 15444:
		case 15701:
		case 15702:
		case 15703:
		case 15704:
			return 375;
		case 18336:
			if (c.myShopId == 26)
				return 130;
			else
				return 500;
		case 3062:
		case 19785: //Elite Void
		case 19786:
		case 19787:
		case 19788:
		case 19789:
		case 19790:
			return 250;
		case 19333:
		case 19354:
		case 19356:
		case 19358:
		case 19360:
		case 19346:
		case 19348:
		case 19350:
		case 19352:
		case 2528:
			return 50;

		//Brawler gloves
		case 13855:
		case 13848:
		case 13857:
		case 13856:
		case 13854:
		case 13853:
		case 13852:
		case 13851:
		case 13850:
			return 15;

		//Tokkul Shop
		case 23639: //Tokhaar-kal
			return 80000;

		case 6571: //Uncut Onyx
			return 150000;

		case 6522:
		case 6523:
		case 6524:
		case 6525:
		case 6526:
		case 6527:
		case 6528:
			return 10000;

		case 11128:
			return 70000;
		}
		return 0;
	}

	public void handleOtherShop(final int itemID) {
		if (c.myShopId == 17) {
			if (c.slayerPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.slayerPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
					c.getPA().sendFrame126(
							"@or2@Slayer Points: " + c.slayerPoints + "", 16029);
				}
			} else {
				c.sendMessage("You do not have enough Slayer Points to buy this item.");
			}
		} else if (c.myShopId == 18) {
			if (c.pcPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.pcPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough PC Points to buy this item.");
			}
		} else if (c.myShopId == 3) {
			if (c.donatorPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.donatorPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Donator Points to buy this item.");
			}
		} else if (c.myShopId == 19) {
			if (c.votePoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.votePoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Vote Points to buy this item.");
			}
		} else if (c.myShopId == 22) {
			if (c.loyaltyPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.loyaltyPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Loyalty Points to buy this item.");
			}
		} else if (c.myShopId == 26) {
			if (c.prestigePoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.prestigePoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Prestige Points to buy this item.");
			}
		} else if (c.myShopId == 25) {
			if (c.getItems().playerHasItem(6529, getSpecialItemValue(itemID))) {
				if (c.getItems().freeSlots() > 0){
					c.getItems().deleteItem(6529, getSpecialItemValue(itemID));
					c.getItems().addItem(itemID,1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Tokkul to buy this item.");			
			}
		} else if (c.myShopId == 27) {
			if (c.magePoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.magePoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Mage Points to buy this item.");
			}
		} else if (c.myShopId == 28) {
			if (c.skillPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.skillPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Skill Points to buy this item.");
			}
		} else if (c.myShopId == 23) {
			if (c.pkPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.pkPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough points to buy this item.");
			}
		} else if (c.myShopId == 30) {
			if (c.achievementPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.achievementPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough points to buy this item.");
			}
		} else if (c.myShopId == 6) {
			if (c.dTokens >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.dTokens -= getSpecialItemValue(itemID);
					c.getPA().sendFrame126(
							"@or2@Dungeoneering Tokens: " + c.dTokens + "",
							16032);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Dungeoneering Points to buy this item.");
			}
		}
	}

	/**
	 * Shops
	 **/
	public void openShop(final int ShopID) {
		c.getItems().resetItems(3823);
		resetShop(ShopID);
		c.isShopping = true;
		c.myShopId = ShopID;
		c.getPA().sendFrame248(3824, 3822);
		c.getPA().sendFrame126(ShopHandler.ShopName[ShopID], 3901);
	}

	public void openVoid() {

	}

	public void resetShop(final int ShopID) {
		int TotalItems = 0;
		for (int i = 0; i < ShopHandler.MaxShopItems; i++) {
			if (ShopHandler.ShopItems[ShopID][i] > 0) {
				TotalItems++;
			}
		}
		if (TotalItems > ShopHandler.MaxShopItems) {
			TotalItems = ShopHandler.MaxShopItems;
		}
		c.getOutStream().createFrameVarSizeWord(53);
		c.getOutStream().writeWord(3900);
		c.getOutStream().writeWord(TotalItems);
		int TotalCount = 0;
		for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
			if (ShopHandler.ShopItems[ShopID][i] > 0
					|| i <= ShopHandler.ShopItemsStandard[ShopID]) {
				if (ShopHandler.ShopItemsN[ShopID][i] > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord_v2(
							ShopHandler.ShopItemsN[ShopID][i]);
				} else {
					c.getOutStream().writeByte(
							ShopHandler.ShopItemsN[ShopID][i]);
				}
				if (ShopHandler.ShopItems[ShopID][i] > Config.ITEM_LIMIT
						|| ShopHandler.ShopItems[ShopID][i] < 0) {
					ShopHandler.ShopItems[ShopID][i] = Config.ITEM_LIMIT;
				}
				c.getOutStream().writeWordBigEndianA(
						ShopHandler.ShopItems[ShopID][i]);
				TotalCount++;
			}
			if (TotalCount > TotalItems) {
				break;
			}
		}
		c.getOutStream().endFrameVarSizeWord();
		c.flushOutStream();
	}

	public boolean sellItem(final int itemID, final int fromSlot, int amount) {
		if (c.myShopId == 14) {
			return false;
		}
		if (c.myShopId == 25) {
			c.sendMessage("You cannot sell items to the tokkul shop.");
			return false;
		}
		for (final int i : Config.ITEM_SELLABLE) {
			if (i == itemID) {
				c.sendMessage("You can't sell "
						+ c.getItems().getItemName(itemID).toLowerCase() + ".");
				return false;
			}
		}
		/** Player Selling Options **/
		if (amount > 0 && itemID == c.playerItems[fromSlot] - 1) {
			if (ShopHandler.ShopSModifier[c.myShopId] > 1) {
				boolean IsIn = false;
				for (int i = 0; i <= ShopHandler.ShopItemsStandard[c.myShopId]; i++) {
					if (itemID == ShopHandler.ShopItems[c.myShopId][i] - 1) {
						IsIn = true;
						break;
					}
				}
				if (IsIn == false && c.myShopId > 1) {
					c.sendMessage("You can't sell "
							+ c.getItems().getItemName(itemID).toLowerCase()
							+ " to this store.");
					return false;
				}
			}
			if (amount > c.playerItemsN[fromSlot]
					&& (Item.itemIsNote[c.playerItems[fromSlot] - 1] == true || Item.itemStackable[c.playerItems[fromSlot] - 1] == true)) {
				amount = c.playerItemsN[fromSlot];
			} else if (amount > c.getItems().getItemAmount(itemID)
					&& Item.itemIsNote[c.playerItems[fromSlot] - 1] == false
					&& Item.itemStackable[c.playerItems[fromSlot] - 1] == false) {
				amount = c.getItems().getItemAmount(itemID);
			}
			// double ShopValue;
			// double TotPrice;
			int TotPrice2 = 0;
			// int Overstock;
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int) Math.floor(getItemShopValue(itemID, 1,
						fromSlot) * 0.90);
				if (c.getItems().freeSlots() > 0
						|| c.getItems().playerHasItem(995)) {
					if (Item.itemIsNote[itemID] == false) {
						c.getItems().deleteItem(itemID,
								c.getItems().getItemSlot(itemID), 1);
					} else {
						c.getItems().deleteItem(itemID, fromSlot, 1);
					}
					c.getItems().addItem(995, TotPrice2);
					if(c.myShopId != 1) {
						addShopItem(itemID, 1);
					}
				} else {
					c.sendMessage("You don't have enough space in your inventory.");
					break;
				}
			}
			c.getItems().resetItems(3823);
			resetShop(c.myShopId);
			updatePlayerShop();
			return true;
		}
		return true;
	}

	/**
	 * Sell item to shop (Shop Price)
	 **/
	public void sellToShopPrice(final int removeId, final int removeSlot) {
		for (final int i : Config.ITEM_SELLABLE) {
			if (i == removeId) {
				c.sendMessage("You can't sell "
						+ c.getItems().getItemName(removeId).toLowerCase()
						+ ".");
				return;
			}
		}
		boolean IsIn = false;
		if (ShopHandler.ShopSModifier[c.myShopId] > 1) {
			for (int j = 0; j <= ShopHandler.ShopItemsStandard[c.myShopId]; j++) {
				if (removeId == ShopHandler.ShopItems[c.myShopId][j] - 1) {
					IsIn = true;
					break;
				}
			}
		} else {
			IsIn = true;
		}
		if (IsIn == false && c.myShopId != 1) {
			c.sendMessage("You can't sell "
					+ c.getItems().getItemName(removeId).toLowerCase()
					+ " to this store.");
		} else {
			int ShopValue = (int) Math.floor(getItemShopValue(removeId, 1,
					removeSlot) * 0.90);
			String ShopAdd = "";
			if (ShopValue >= 1000 && ShopValue < 1000000) {
				ShopAdd = " (" + ShopValue / 1000 + "K)";
			} else if (ShopValue >= 1000000) {
				ShopAdd = " (" + ShopValue / 1000000 + " million)";
			}
			c.sendMessage(c.getItems().getItemName(removeId)
					+ ": shop will buy for " + ShopValue + " coins" + ShopAdd);
		}
	}

	public boolean shopSellsItem(final int itemID) {
		for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
			if (itemID == ShopHandler.ShopItems[c.myShopId][i] - 1) {
				return true;
			}
		}
		return false;
	}

	public void updatePlayerShop() {
		for (int i = 1; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] != null) {
				if (PlayerHandler.players[i].isShopping == true
						&& PlayerHandler.players[i].myShopId == c.myShopId
						&& i != c.playerId) {
					PlayerHandler.players[i].updateShop = true;
				}
			}
		}
	}

	public void updateshop(final int i) {
		resetShop(i);
	}

}
