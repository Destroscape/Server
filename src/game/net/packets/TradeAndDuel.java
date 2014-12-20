package game.net.packets;

import engine.util.Misc;
import game.Config;
import game.Server;
import game.combat.prayer.Prayer;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.entity.player.PlayerSave;
import game.item.GameItem;
import game.item.Item;

import java.util.concurrent.CopyOnWriteArrayList;

public class TradeAndDuel {

	private final Player c;

	public TradeAndDuel(Player Client) {
		this.c = Client;
	}

	/**
	 * Trading
	 */

	public CopyOnWriteArrayList<GameItem> offeredItems = new CopyOnWriteArrayList<GameItem>();

	public void selectEquipmentRule(int i) {
		Player o = PlayerHandler.players[c.duelingWith];
		if (!c.duelRule[i]) {
			c.getPA().sendFrame87(286, 600000 + i);
			o.getPA().sendFrame87(286, 600000 + i);
		} else {
			c.getPA().sendFrame87(286, 500000 + i);
			o.getPA().sendFrame87(286, 500000 + i);
		}
	}

	private String formatPlayerName(String str) {
		String str1 = Misc.ucFirst(str);
		str1.replace("_", " ");
		return str1;
	}

	public void requestTrade(int id) {

		Player o = PlayerHandler.players[id];
		if (o == null || c.playerId < 0 || id == c.playerId
				|| c.tradeWith == c.playerId) {
			return;
		}

		c.tradeWith = id;
		if (c.connectedFrom.equals(o.connectedFrom)
				|| o.connectedFrom.equals(c.connectedFrom)) {
			c.sendMessage("You cannot trade yourself from the same IP.");
			// return;
		}
		if (c.anticheattimer > 0) {
			c.sendMessage("you must wait " + c.anticheattimer / 2
					+ " more seconds to trade");
			return;
		}
		if (o.duelStatus > 0 || o.inTrade || o.isBanking || o.playerIsNPC) {
			// c.sendMessage(""+o.duelStatus+" "+o.inTrade+" "+o.isBanking+" "+o.playerIsNPC+"");
			c.sendMessage("This player is currently busy.");
			return;
		}
		if (!c.inTrade && o.tradeRequested && o.tradeWith == c.playerId) {
			c.getTradeAndDuel().openTrade();
			o.getTradeAndDuel().openTrade();
		} else if (!c.inTrade) {
			c.tradeRequested = true;
			c.sendMessage("Sending trade request...");
			o.sendMessage("" + Misc.ucFirst(c.playerName) + ":tradereq:");
		}
	}

	public void openTrade() {
		Player o = PlayerHandler.players[c.tradeWith];
		if (o == null || c.tradeWith == c.playerId || c.duelStatus == 6
				|| c.isBanking || c.isPartyRoom || c.inDuelScreen) {
			return;
		}
		c.inTrade = true;
		c.canOffer = true;
		c.tradeStatus = 1;
		o.getPA().sendFrame126("" + formatPlayerName(c.playerName) + "", 3451);
		c.getPA().sendFrame126("" + formatPlayerName(o.playerName) + "", 3451);
		o.getPA().sendFrame126(
				"Trading with: " + formatPlayerName(c.playerName) + "", 3417);
		c.getPA().sendFrame126(
				"Trading with: " + formatPlayerName(o.playerName) + "", 3417);
		c.tradeRequested = false;
		c.getItems().resetItems(3322);
		resetTItems(3415);
		resetOTItems(3416);
		String out = o.playerName;

		if (o.playerRights == 1) {
			out = "@cr1@" + out;
		} else if (o.playerRights == 2) {
			out = "@cr2@" + out;
		}
		displayWAndI(c);
		c.getPA().sendFrame126("", 3431);
		c.getPA().sendFrame126("Are you sure you want to make this trade?",
				3535);
		c.getPA().sendFrame248(3323, 3321);
	}

	public void displayWAndI(Player c) {
		Player o = PlayerHandler.players[c.tradeWith];
		c.playerTradeWealth = 0;
		o.playerTradeWealth = 0;
		for (GameItem item : c.getTradeAndDuel().offeredItems) {
			c.playerTradeWealth += (c.getShops().getItemShopValue(item.id) * item.amount);
		}

		for (GameItem item : o.getTradeAndDuel().offeredItems) {
			o.playerTradeWealth += (o.getShops().getItemShopValue(item.id) * item.amount);
		}

		int playerDifference1 = (c.playerTradeWealth - o.playerTradeWealth);
		int playerDifference2 = (o.playerTradeWealth - c.playerTradeWealth);

		boolean player1HasMore = (playerDifference1 > playerDifference2);
		boolean equalsSame = (c.playerTradeWealth == o.playerTradeWealth);

		if (c.playerTradeWealth < -1) {
			c.playerTradeWealth = 2147483647;
		}
		if (o.playerTradeWealth < -1) {
			o.playerTradeWealth = 2147483647;
		}

		String playerValue1 = ""
				+ c.getPA().getTotalAmount(c, c.playerTradeWealth) + " ("
				+ Misc.format(c.playerTradeWealth) + ")";
		String playerValue2 = ""
				+ c.getPA().getTotalAmount(o, o.playerTradeWealth) + " ("
				+ Misc.format(o.playerTradeWealth) + ")";

		if (c.playerTradeWealth < -1) {
			playerValue1 = "+" + playerValue1;
		}
		if (o.playerTradeWealth < -1) {
			playerValue2 = "+" + playerValue2;
		}
		if (equalsSame) {
			c.getPA().sendFrame126("@yel@Equal Trade", 23504);
			o.getPA().sendFrame126("@yel@Equal Trade", 23504);
		} else if (player1HasMore) {
			c.getPA().sendFrame126(
					"-@red@" + c.getPA().getTotalAmount(c, playerDifference1)
							+ " (" + Misc.format(playerDifference1) + ")",
					23504);
			o.getPA().sendFrame126(
					"+@yel@" + o.getPA().getTotalAmount(o, playerDifference1)
							+ " (" + Misc.format(playerDifference1) + ")",
					23504);
		} else if (!player1HasMore) {
			c.getPA().sendFrame126(
					"+@yel@" + c.getPA().getTotalAmount(c, playerDifference2)
							+ " (" + Misc.format(playerDifference2) + ")",
					23504);
			o.getPA().sendFrame126(
					"-@red@" + o.getPA().getTotalAmount(o, playerDifference2)
							+ " (" + Misc.format(playerDifference2) + ")",
					23504);
		}
		c.getPA().sendFrame126(playerValue1, 23506);
		c.getPA().sendFrame126(playerValue2, 23507);
		o.getPA().sendFrame126(playerValue2, 23506);
		o.getPA().sendFrame126(playerValue1, 23507);
		c.getPA().sendFrame126(
				Misc.formatPlayerName(o.playerName) + " has\\n "
						+ o.getItems().freeSlots()
						+ " free\\n inventory slots.", 23505);
		o.getPA().sendFrame126(
				Misc.formatPlayerName(c.playerName) + " has\\n "
						+ c.getItems().freeSlots()
						+ " free\\n inventory slots.", 23505);
		o.getPA().sendFrame126(
				"Trading with: " + Misc.formatPlayerName(c.playerName) + "",
				3417);
		c.getPA().sendFrame126(
				"Trading with: " + Misc.formatPlayerName(o.playerName) + "",
				3417);
	}

	/*
	 * public void displayWAndI(Client c) { Client o = (Client)
	 * PlayerHandler.players[c.tradeWith]; c.getPA().sendFrame126(
	 * formatPlayerName(o.playerName) + " has\\n " + o.getItems().freeSlots() +
	 * " free\\n inventory slots.", 23505); o.getPA().sendFrame126(
	 * formatPlayerName(c.playerName) + " has\\n " + c.getItems().freeSlots() +
	 * " free\\n inventory slots.", 23505); }
	 */

	public void resetTItems(int WriteFrame) {
		synchronized (c) {
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(WriteFrame);
			int len = offeredItems.toArray().length;
			int current = 0;
			c.getOutStream().writeWord(len);
			for (GameItem item : offeredItems) {
				if (item.amount > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord_v2(item.amount);
				} else {
					c.getOutStream().writeByte(item.amount);
				}
				c.getOutStream().writeWordBigEndianA(item.id + 1);
				current++;
			}
			if (current < 27) {
				for (int i = current; i < 28; i++) {
					c.getOutStream().writeByte(1);
					c.getOutStream().writeWordBigEndianA(-1);
				}
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}

	public boolean fromTrade(int itemID, int amount) {
		Player o = PlayerHandler.players[c.tradeWith];
		if (o == null || !c.inTrade || !c.canOffer) {
			declineTrade();
			return false;
		}
		falseTradeConfirm();
		if (!Item.itemStackable[itemID]) {
			if (amount > 28) {
				amount = 28;
			}
			for (int a = 0; a < amount; a++) {
				for (GameItem item : offeredItems) {
					if (item.id == itemID) {
						if (!item.stackable) {
							offeredItems.remove(item);
							c.getItems().addItem(itemID, 1);
						} else {
							if (item.amount > amount) {
								item.amount -= amount;
								c.getItems().addItem(itemID, amount);
							} else {
								amount = item.amount;
								offeredItems.remove(item);
								c.getItems().addItem(itemID, amount);
							}
						}
						break;
					}
				}
			}
		}
		for (GameItem item : offeredItems) {
			if (item.id == itemID) {
				if (!item.stackable) {
				} else {
					if (item.amount > amount) {
						item.amount -= amount;
						c.getItems().addItem(itemID, amount);
					} else {
						amount = item.amount;
						offeredItems.remove(item);
						c.getItems().addItem(itemID, amount);
					}
				}
				break;
			}
		}
		falseTradeConfirm();
		c.getItems().resetItems(3322);
		resetTItems(3415);
		o.getTradeAndDuel().resetOTItems(3416);
		displayWAndI(c);
		c.getPA().sendFrame126("", 3431);
		o.getPA().sendFrame126("", 3431);
		return true;
	}

	private boolean falseTradeConfirm() {
		Player o = PlayerHandler.players[c.tradeWith];
		return c.tradeConfirmed = o.tradeConfirmed = false;
	}

	public boolean tradeItem(int itemID, int fromSlot, int amount) {
		Player o = PlayerHandler.players[c.tradeWith];
		if (o == null) {
			return false;
		}
		for (int i : Config.ITEM_TRADEABLE) {
			if (i == itemID) {
				c.sendMessage("You can't trade this item.");
				return false;
			}
		}
		if (itemID != 995 & c.playerItems[fromSlot] == 996) {
			return false;
		}

		if (amount > c.playerItemsN[fromSlot])
			return false;

		c.tradeConfirmed = false;
		o.tradeConfirmed = false;
		if (!Item.itemStackable[itemID] && !Item.itemIsNote[itemID]) {
			for (int a = 0; a < amount; a++) {
				if (c.getItems().playerHasItem(itemID, 1)) {
					offeredItems.add(new GameItem(itemID, 1));
					c.getItems().deleteItem(itemID,
							c.getItems().getItemSlot(itemID), 1);
				}
			}
			c.getItems().resetItems(3322);
			resetTItems(3415);
			o.getTradeAndDuel().resetOTItems(3416);
			displayWAndI(c);
			c.getPA().sendFrame126("", 3431);
			o.getPA().sendFrame126("", 3431);
		}
		if (c.getItems().getItemCount(itemID) < amount) {
			amount = c.getItems().getItemCount(itemID);
			if (amount == 0)
				return false;
		}

		if (!c.inTrade || !c.canOffer) {
			declineTrade();
			return false;
		}

		if (Item.itemStackable[itemID] || Item.itemIsNote[itemID]) {
			boolean inTrade = false;
			for (GameItem item : offeredItems) {
				if (item.id == itemID) {
					inTrade = true;
					item.amount += amount;
					c.getItems().deleteItem(itemID,
							c.getItems().getItemSlot(itemID), amount);
					break;
				}
			}

			if (!inTrade) {
				offeredItems.add(new GameItem(itemID, amount));
				c.getItems().deleteItem(itemID, fromSlot, amount);
			}
		}
		c.getItems().resetItems(3322);
		resetTItems(3415);
		o.getTradeAndDuel().resetOTItems(3416);
		displayWAndI(c);
		c.getPA().sendFrame126("", 3431);
		o.getPA().sendFrame126("", 3431);
		return true;
	}

	public void resetTrade() {
		offeredItems.clear();
		c.inTrade = false;
		c.tradeWith = 0;
		c.canOffer = true;
		c.tradeConfirmed = false;
		c.tradeConfirmed2 = false;
		c.acceptedTrade = false;
		c.getPA().removeAllWindows();
		c.tradeResetNeeded = false;
		c.getPA().sendFrame126("Are you sure you want to make this trade?",
				3535);
	}

	public void declineTrade() {
		c.tradeStatus = 0;
		declineTrade(true);
	}

	public void declineTrade(boolean tellOther) {
		c.getPA().removeAllWindows();
		Player o = PlayerHandler.players[c.tradeWith];
		if (o == null) {
			return;
		}
		if (tellOther) {
			o.getTradeAndDuel().declineTrade(false);
			o.getTradeAndDuel().c.getPA().removeAllWindows();
		}
		if (c.isPartyRoom) {
			c.isPartyRoom = false;
		}
		for (GameItem item : offeredItems) {
			if (item.amount < 1) {
				continue;
			}
			if (item.stackable) {
				c.getItems().addItem(item.id, item.amount);
			} else {
				for (int i = 0; i < item.amount; i++) {
					c.getItems().addItem(item.id, 1);
				}
			}
		}
		c.canOffer = true;
		c.tradeConfirmed = false;
		c.tradeConfirmed2 = false;
		offeredItems.clear();
		c.inTrade = false;
		c.tradeWith = 0;
	}

	public void resetOTItems(int WriteFrame) {
		synchronized (c) {
			Player o = PlayerHandler.players[c.tradeWith];
			if (o == null) {
				return;
			}
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(WriteFrame);
			int len = o.getTradeAndDuel().offeredItems.toArray().length;
			int current = 0;
			c.getOutStream().writeWord(len);
			for (GameItem item : o.getTradeAndDuel().offeredItems) {
				if (item.amount > 254) {
					c.getOutStream().writeByte(255); // item's stack count. if
														// over 254, write byte
														// 255
					c.getOutStream().writeDWord_v2(item.amount);
				} else {
					c.getOutStream().writeByte(item.amount);
				}
				c.getOutStream().writeWordBigEndianA(item.id + 1); // item id
				current++;
			}
			if (current < 27) {
				for (int i = current; i < 28; i++) {
					c.getOutStream().writeByte(1);
					c.getOutStream().writeWordBigEndianA(-1);
				}
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}

	public void confirmScreen() {
		Player o = PlayerHandler.players[c.tradeWith];
		if (o == null) {
			return;
		}
		c.canOffer = false;
		c.getItems().resetItems(3214);
		String SendTrade = "Absolutely nothing!";
		String SendAmount;
		int Count = 0;
		for (GameItem item : offeredItems) {
			if (item.id > 0) {
				if (item.amount >= 1000 && item.amount < 1000000) {
					SendAmount = "@cya@" + (item.amount / 1000) + "K @whi@("
							+ Misc.format(item.amount) + ")";
				} else if (item.amount >= 1000000) {
					SendAmount = "@gre@" + (item.amount / 1000000)
							+ " million @whi@(" + Misc.format(item.amount)
							+ ")";
				} else {
					SendAmount = "" + Misc.format(item.amount);
				}
				if (Count == 0) {
					SendTrade = c.getItems().getItemName(item.id);
				} else {
					SendTrade = SendTrade + "\\n"
							+ c.getItems().getItemName(item.id);
				}

				if (item.stackable) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}

		c.getPA().sendFrame126(SendTrade, 3557);
		SendTrade = "Absolutely nothing!";
		SendAmount = "";
		Count = 0;

		for (GameItem item : o.getTradeAndDuel().offeredItems) {
			if (item.id > 0) {
				if (item.amount >= 1000 && item.amount < 1000000) {
					SendAmount = "@cya@" + (item.amount / 1000) + "K @whi@("
							+ Misc.format(item.amount) + ")";
				} else if (item.amount >= 1000000) {
					SendAmount = "@gre@" + (item.amount / 1000000)
							+ " million @whi@(" + Misc.format(item.amount)
							+ ")";
				} else {
					SendAmount = "" + Misc.format(item.amount);
				}
				// SendAmount = SendAmount;
				if (Count == 0) {
					SendTrade = c.getItems().getItemName(item.id);
				} else {
					SendTrade = SendTrade + "\\n"
							+ c.getItems().getItemName(item.id);
				}
				if (item.stackable) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		c.getPA().sendFrame126(SendTrade, 3558);
		c.getPA().sendFrame248(3443, 197);
	}

	public void giveItems() {
		Player o = PlayerHandler.players[c.tradeWith];
		if (o == null) {
			return;
		}
		try {
			for (GameItem item : o.getTradeAndDuel().offeredItems) {
				if (item.id > 0) {
					c.getItems().addItem(item.id, item.amount);
					c.getTradeLog().tradeReceived(c.getItems().getItemName(item.id), item.amount);
				}
				o.getTradeLog().tradeGive(c.getItems().getItemName(item.id), item.amount);
			}

			c.getPA().removeAllWindows();
			c.tradeResetNeeded = true;
			PlayerSave.saveGame(c);
		} catch (Exception ignored) {
		}
	}

	/**
	 * Dueling
	 */

	public CopyOnWriteArrayList<GameItem> otherStakedItems = new CopyOnWriteArrayList<GameItem>();
	public CopyOnWriteArrayList<GameItem> stakedItems = new CopyOnWriteArrayList<GameItem>();

	public void requestDuel(int id) {
		if (id == c.playerId) {
			return;
		}
		resetDuel();
		resetDuelItems();
		c.duelingWith = id;
		Player o = PlayerHandler.players[id];
		if (o == null || c.connectedFrom.equals(o.connectedFrom)
				|| o.connectedFrom.equals(c.connectedFrom)) {
			c.sendMessage("Flurp");
			// return;
		}
		if (o.inDuelScreen || o.inTrade || o.isBanking || o.playerIsNPC
				|| o.isPartyRoom || o.duelStatus == 6 || o.duelStatus > 0) {
			c.sendMessage("This player is currently busy.");
			return;
		}
		if (c.duelStatus > 0) {
			c.sendMessage("You must decline this duel before accepting another one!");
			return;
		}
		c.duelRequested = true;
		if (c.duelStatus == 0 && o.duelStatus == 0 && c.duelRequested
				&& o.duelRequested && c.duelingWith == o.getId()
				&& o.duelingWith == c.getId()) {
			if (c.goodDistance(c.getX(), c.getY(), o.getX(), o.getY(), 1)) {
				if (c.duelStatus == 0) {
					c.getTradeAndDuel().openDuel();
					o.getTradeAndDuel().openDuel();
				} else {
					c.sendMessage("You must decline this duel before accepting another one!");
				}
			} else {
				c.sendMessage("You need to get closer to your opponent to start the duel.");
			}

		} else {
			c.sendMessage("Sending duel request...");
			o.sendMessage(c.playerName + ":duelreq:");
		}
	}

	public void openDuel() {
		Player o = PlayerHandler.players[c.duelingWith];
		if (o == null || c.inTrade || c.duelStatus == 6 || c.isBanking
				|| c.isPartyRoom) {
			return;
		}
		c.inDuelScreen = true;
		c.duelStatus = 1;
		refreshduelRules();
		refreshDuelScreen();
		c.canOffer = true;
		for (int i = 0; i < c.playerEquipment.length; i++) {
			sendDuelEquipment(c.playerEquipment[i], c.playerEquipmentN[i], i);
		}
		c.getPA().sendFrame126(
				"Dueling with: " + o.playerName + " (level-" + o.combatLevel
						+ ")", 6671);
		c.getPA().sendFrame126("", 6684);
		c.getPA().sendFrame248(6575, 3321);
		c.getItems().resetItems(3322);

	}

	public void sendDuelEquipment(int itemId, int amount, int slot) {
		synchronized (c) {
			if (itemId != 0) {
				c.getOutStream().createFrameVarSizeWord(34);
				c.getOutStream().writeWord(13824);
				c.getOutStream().writeByte(slot);
				c.getOutStream().writeWord(itemId + 1);

				if (amount > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord(amount);
				} else {
					c.getOutStream().writeByte(amount);
				}
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
			}
		}
	}

	public void refreshduelRules() {
		for (int i = 0; i < c.duelRule.length; i++) {
			c.duelRule[i] = false;
		}
		c.getPA().sendFrame87(286, 0);
		c.getPA().sendFrame87(286, 505000);
		c.duelOption = 0;
	}

	public void refreshDuelScreen() {
		synchronized (c) {
			Player o = PlayerHandler.players[c.duelingWith];
			if (o == null) {
				return;
			}
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(6669);
			c.getOutStream().writeWord(stakedItems.toArray().length);
			int current = 0;
			for (GameItem item : stakedItems) {
				if (item.amount > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord_v2(item.amount);
				} else {
					c.getOutStream().writeByte(item.amount);
				}
				if (item.id > Config.ITEM_LIMIT || item.id < 0) {
					item.id = Config.ITEM_LIMIT;
				}
				c.getOutStream().writeWordBigEndianA(item.id + 1);

				current++;
			}

			if (current < 27) {
				for (int i = current; i < 28; i++) {
					c.getOutStream().writeByte(1);
					c.getOutStream().writeWordBigEndianA(-1);
				}
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();

			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(6670);
			c.getOutStream().writeWord(
					o.getTradeAndDuel().stakedItems.toArray().length);
			current = 0;
			for (GameItem item : o.getTradeAndDuel().stakedItems) {
				if (item.amount > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord_v2(item.amount);
				} else {
					c.getOutStream().writeByte(item.amount);
				}
				if (item.id > Config.ITEM_LIMIT || item.id < 0) {
					item.id = Config.ITEM_LIMIT;
				}
				c.getOutStream().writeWordBigEndianA(item.id + 1);
				current++;
			}

			if (current < 27) {
				for (int i = current; i < 28; i++) {
					c.getOutStream().writeByte(1);
					c.getOutStream().writeWordBigEndianA(-1);
				}
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}

	public boolean stakeItem(int itemID, int fromSlot, int amount) {
		for (int i : Config.ITEM_TRADEABLE) {
			if (i == itemID) {
				c.sendMessage("You can't stake this item.");
				return false;
			}
		}
		if (c.inTrade || !c.inDuelScreen || !c.canOffer) {
			return false;
		}
		if (c.playerItems[fromSlot] - 1 != itemID) {
			return false;
		}
		if (!c.getItems().playerHasItem(itemID, amount)) {
			return false;
		}
		if (amount <= 0)
			return false;
		Player o = PlayerHandler.players[c.duelingWith];
		if (o == null) {
			declineDuel();
			return false;
		}
		if (o.duelStatus <= 0 || c.duelStatus <= 0) {
			declineDuel();
			o.getTradeAndDuel().declineDuel();
			return false;
		}
		changeDuelStuff();
		if (!Item.itemStackable[itemID]) {
			for (int a = 0; a < amount; a++) {
				if (c.getItems().playerHasItem(itemID, 1)) {
					stakedItems.add(new GameItem(itemID, 1));
					c.getItems().deleteItem(itemID,
							c.getItems().getItemSlot(itemID), 1);
				}
			}
			c.getItems().resetItems(3214);
			c.getItems().resetItems(3322);
			o.getItems().resetItems(3214);
			o.getItems().resetItems(3322);
			refreshDuelScreen();
			o.getTradeAndDuel().refreshDuelScreen();
			c.getPA().sendFrame126("", 6684);
			o.getPA().sendFrame126("", 6684);
		}

		if (!c.getItems().playerHasItem(itemID, amount)) {
			return false;
		}
		if (Item.itemStackable[itemID] || Item.itemIsNote[itemID]) {
			boolean found = false;
			for (GameItem item : stakedItems) {
				if (item.id == itemID) {
					found = true;
					item.amount += amount;
					c.getItems().deleteItem(itemID, fromSlot, amount);
					break;
				}
			}
			if (!found) {
				c.getItems().deleteItem(itemID, fromSlot, amount);
				stakedItems.add(new GameItem(itemID, amount));
			}
		}

		c.getItems().resetItems(3214);
		c.getItems().resetItems(3322);
		o.getItems().resetItems(3214);
		o.getItems().resetItems(3322);
		refreshDuelScreen();
		o.getTradeAndDuel().refreshDuelScreen();
		c.getPA().sendFrame126("", 6684);
		o.getPA().sendFrame126("", 6684);
		return true;
	}

	public boolean fromDuel(int itemID, int fromSlot, int amount) {
		Player o = PlayerHandler.players[c.duelingWith];
		if (o == null) {
			declineDuel();
			return false;
		}
		if (!c.getItems().playerHasItem(itemID, amount)) {
			return false;
		}
		if (c.playerItems[fromSlot] - 1 != itemID) {
			return false;
		}
		if (c.duelStatus == 0 || c.duelStatus == 5) {
			c.getTradeAndDuel().stakedItems.clear();
			System.out.println("" + c.playerName + " Tried to dupe!");
			return false;
		}
		if (o.duelStatus == 6 || c.duelStatus > 2 && c.duelStatus < 7) {
			return false;
		}
		if (o.duelStatus <= 0 || c.duelStatus <= 0) {
			declineDuel();
			o.getTradeAndDuel().declineDuel();
			return false;
		}
		if (Item.itemStackable[itemID]) {
			if (c.getItems().freeSlots() - 1 < (c.duelSpaceReq)) {
				c.sendMessage("You have too many rules set to remove that item.");
				return false;
			}
		}

		if (!c.canOffer && !c.inDuelScreen) {
			return false;
		}

		changeDuelStuff();
		boolean goodSpace = true;
		if (!Item.itemStackable[itemID]) {
			for (int a = 0; a < amount; a++) {
				for (GameItem item : stakedItems) {
					if (item.id == itemID) {
						if (!item.stackable) {
							if (c.getItems().freeSlots() - 1 < (c.duelSpaceReq)) {
								goodSpace = false;
								break;
							}
							if (c.duelStatus == 0 || c.duelStatus == 5) {
								System.out.println("" + c.playerName
										+ " Tried to dupe!");
								c.getTradeAndDuel().stakedItems.clear();
								return false;
							}
							stakedItems.remove(item);
							c.getItems().addItem(itemID, 1);
						} else {
							if (c.getItems().freeSlots() - 1 < (c.duelSpaceReq)) {
								goodSpace = false;
								break;
							}
							if (item.amount > amount) {
								item.amount -= amount;
								c.getItems().addItem(itemID, amount);
							} else {
								if (c.getItems().freeSlots() - 1 < (c.duelSpaceReq)) {
									goodSpace = false;
									break;
								}
								amount = item.amount;
								stakedItems.remove(item);
								c.getItems().addItem(itemID, amount);
							}
						}
						break;
					}
					o.duelStatus = 1;
					c.duelStatus = 1;
					c.getItems().resetItems(3214);
					c.getItems().resetItems(3322);
					o.getItems().resetItems(3214);
					o.getItems().resetItems(3322);
					c.getTradeAndDuel().refreshDuelScreen();
					o.getTradeAndDuel().refreshDuelScreen();
					o.getPA().sendFrame126("", 6684);
				}
			}
		}

		for (GameItem item : stakedItems) {
			if (item.id == itemID) {
				if (!item.stackable) {
				} else {
					if (item.amount > amount) {
						item.amount -= amount;
						c.getItems().addItem(itemID, amount);
					} else {
						amount = item.amount;
						stakedItems.remove(item);
						c.getItems().addItem(itemID, amount);
					}
				}
				break;
			}
		}
		o.duelStatus = 1;
		c.duelStatus = 1;
		c.getItems().resetItems(3214);
		c.getItems().resetItems(3322);
		o.getItems().resetItems(3214);
		o.getItems().resetItems(3322);
		c.getTradeAndDuel().refreshDuelScreen();
		o.getTradeAndDuel().refreshDuelScreen();
		o.getPA().sendFrame126("", 6684);
		if (!goodSpace) {
			c.sendMessage("You have too many rules set to remove that item.");
			return true;
		}
		return true;
	}

	public void confirmDuel() {
		Player o = PlayerHandler.players[c.duelingWith];
		if (o == null) {
			declineDuel();
			return;
		}
		String itemId = "";
		for (GameItem item : stakedItems) {
			if (Item.itemStackable[item.id] || Item.itemIsNote[item.id]) {
				itemId += c.getItems().getItemName(item.id) + " x "
						+ Misc.format(item.amount) + "\\n";
			} else {
				itemId += c.getItems().getItemName(item.id) + "\\n";
			}
		}
		c.getPA().sendFrame126(itemId, 6516);
		itemId = "";
		for (GameItem item : o.getTradeAndDuel().stakedItems) {
			if (Item.itemStackable[item.id] || Item.itemIsNote[item.id]) {
				itemId += c.getItems().getItemName(item.id) + " x "
						+ Misc.format(item.amount) + "\\n";
			} else {
				itemId += c.getItems().getItemName(item.id) + "\\n";
			}
		}
		c.getPA().sendFrame126(itemId, 6517);
		c.getPA().sendFrame126("", 8242);
		for (int i = 8238; i <= 8253; i++) {
			c.getPA().sendFrame126("", i);
		}
		c.getPA().sendFrame126("Hitpoints will be restored.", 8250);
		c.getPA().sendFrame126("Boosted stats will be restored.", 8238);
		if (c.duelRule[8]) {
			c.getPA().sendFrame126("There will be obstacles in the arena.",
					8239);
		}
		c.getPA().sendFrame126("", 8240);
		c.getPA().sendFrame126("", 8241);

		String[] rulesOption = { "Players cannot forfeit!",
				"Players cannot move.", "Players cannot use range.",
				"Players cannot use melee.", "Players cannot use magic.",
				"Players cannot drink pots.", "Players cannot eat food.",
				"Players cannot use prayer." };

		int lineNumber = 8242;
		for (int i = 0; i < 8; i++) {
			if (c.duelRule[i]) {
				c.getPA().sendFrame126("" + rulesOption[i], lineNumber);
				lineNumber++;
			}
		}
		c.getPA().sendFrame126("", 6571);
		c.getPA().sendFrame248(6412, 197);
	}

	public void startDuel() {
		c.inDuelScreen = false;
		Player o = PlayerHandler.players[c.duelingWith];
		if (o == null || o.disconnected) {
			duelVictory();
		}
		if (o != null) {
			c.duelName = o.playerName;
			c.duelLevel = o.combatLevel;
		} else {
			c.duelName = "???";
			c.duelLevel = 3;
		}
		c.headIconHints = 2;
		if (c.duelRule[7]) {
			for (int p = 0; p < c.PRAYER.length; p++) { // reset prayer glows
				c.prayerActive[p] = false;
				c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);
			}
			c.headIcon = -1;
			c.getPA().requestUpdates();
		}
		if (c.duelRule[11]) {
			c.getItems().removeItem(0);
		}
		if (c.duelRule[12]) {
			c.getItems().removeItem(1);
		}
		if (c.duelRule[13]) {
			c.getItems().removeItem(2);
		}
		if (c.duelRule[14]) {
			c.getItems().removeItem(3);
		}
		if (c.duelRule[15]) {
			c.getItems().removeItem(4);
		}
		if (c.duelRule[16]) {
			c.getItems().removeItem(5);
		}
		if (c.duelRule[17]) {
			c.getItems().removeItem(7);
		}
		if (c.duelRule[18]) {
			c.getItems().removeItem(9);
		}
		if (c.duelRule[19]) {
			c.getItems().removeItem(10);
		}
		if (c.duelRule[20]) {
			c.getItems().removeItem(12);
		}
		if (c.duelRule[21]) {
			c.getItems().removeItem(13);
		}
		if ((c.duelRule[16] && c.getItems().is2handed(
				c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
						.toLowerCase(), c.playerEquipment[c.playerWeapon]))) {
			c.getItems().removeItem(3);
		}
		c.duelStatus = 5;
		c.getPA().removeAllWindows();
		c.specAmount = 10;
		c.getItems().addSpecialBar(c.playerEquipment[c.playerWeapon]);
		c.lastVeng = 0;
		c.vengOn = false;

		if (c.duelRule[8]) {
			if (c.duelRule[1]) {
				c.getPA().movePlayer(c.duelTeleX, c.duelTeleY, 0);
			} else {
				c.getPA().movePlayer(3366 + Misc.random(12),
						3246 + Misc.random(6), 0);
			}
		} else {
			if (c.duelRule[1]) {
				c.getPA().movePlayer(c.duelTeleX, c.duelTeleY, 0);
			} else {
				c.getPA().movePlayer(3335 + Misc.random(12),
						3246 + Misc.random(6), 0);
			}
		}
		if (o != null) {
			o.getPA().createPlayerHints(10, o.playerId);
			c.getPA().createPlayerHints(10, c.playerId);
		}
		c.getPA().showOption(3, 0, "Attack");
		for (int i = 0; i < 20; i++) {
			c.playerLevel[i] = c.getPA().getLevelForXP(c.playerXP[i]);
			c.getPA().refreshSkill(i);
		}
		if (o != null) {
			for (GameItem item : o.getTradeAndDuel().stakedItems) {
				otherStakedItems.add(new GameItem(item.id, item.amount));
			}
		}
		PlayerSave.saveGame(c);
		c.getPA().requestUpdates();
	}

	public void duelVictory() {
		Player o = PlayerHandler.players[c.duelingWith];
		c.duelStatus = 6;
		Prayer.closeAllPrayers(c);
		for (int i = 0; i < 20; i++) {
			c.playerLevel[i] = c.getPA().getLevelForXP(c.playerXP[i]);
			c.getPA().refreshSkill(i);
		}
		if (c.isSkulled) {
			c.isSkulled = false;
			c.skullTimer = 0;
			c.headIconPk = -1;
			c.getPA().requestUpdates();
		}
		c.getPA().refreshSkill(3);
		duelRewardInterface();
		c.getPA().sendFrame126(c.duelName, 6840);
		c.getPA().sendFrame126("" + c.duelLevel, 6839);
		c.getPA().showInterface(6733);
		claimStakedItems();
		c.getPA().movePlayer(
				Config.DUELING_RESPAWN_X
						+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
				Config.DUELING_RESPAWN_Y
						+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)), 0);
		c.getPA().requestUpdates();
		c.getPA().showOption(3, 0, "Challenge");
		c.getPA().createPlayerHints(10, -1);
		c.canOffer = true;
		c.duelSpaceReq = 0;
		c.duelingWith = 0;
		c.getCombat().resetPlayerAttack();
		c.duelRequested = false;
		PlayerSave.saveGame(c);
		if (o != null)
			PlayerSave.saveGame(o);
	}

	public void duelRewardInterface() {
		synchronized (c) {
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(6822);
			c.getOutStream().writeWord(otherStakedItems.toArray().length);
			for (GameItem item : otherStakedItems) {
				if (item.amount > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord_v2(item.amount);
				} else {
					c.getOutStream().writeByte(item.amount);
				}
				if (item.id > Config.ITEM_LIMIT || item.id < 0) {
					item.id = Config.ITEM_LIMIT;
				}
				c.getOutStream().writeWordBigEndianA(item.id + 1);
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}

	public void claimStakedItems() {
		for (GameItem item : otherStakedItems) {
			if (item.id > 0 && item.amount > 0) {
				if (Item.itemStackable[item.id]) {
					if (!c.getItems().addItem(item.id, item.amount)) {
						Server.itemHandler.createGroundItem(c, item.id, 3363,
								3269, c.heightLevel, item.amount, c.getId());
					}
				} else {
					int amount = item.amount;
					for (int a = 1; a <= amount; a++) {
						if (!c.getItems().addItem(item.id, 1)) {
							Server.itemHandler.createGroundItem(c, item.id,
									3363, 3269, c.heightLevel, 1, c.getId());
						}
					}
				}
			}
		}
		for (GameItem item : stakedItems) {
			if (item.id > 0 && item.amount > 0) {
				if (Item.itemStackable[item.id]) {
					if (!c.getItems().addItem(item.id, item.amount)) {
						Server.itemHandler.createGroundItem(c, item.id, 3363,
								3269, c.heightLevel, item.amount, c.getId());
					}
				} else {
					int amount = item.amount;
					for (int a = 1; a <= amount; a++) {
						if (!c.getItems().addItem(item.id, 1)) {
							Server.itemHandler.createGroundItem(c, item.id,
									3363, 3269, c.heightLevel, 1, c.getId());
						}
					}
				}
			}
		}
		resetDuel();
		resetDuelItems();
		PlayerSave.saveGame(c);
		c.duelStatus = 0;
	}

	public void declineDuel() {
		declineDuel(true);
	}

	public void declineDuel(boolean tellOther) {
		c.getPA().removeAllWindows();
		Player o = PlayerHandler.players[c.duelingWith];
		if (o == null || o.duelStatus == 6) {
			return;
		}
		o.getPA().removeAllWindows();
		// o.sendMessage("Other player declined trade!");
		if (tellOther) {
			o.getTradeAndDuel().declineDuel(false);
			o.getTradeAndDuel().c.getPA().removeAllWindows();
		}
		c.canOffer = true;
		c.duelStatus = 0;
		c.duelingWith = 0;
		c.duelSpaceReq = 0;
		c.inDuelScreen = false;
		c.duelRequested = false;
		for (GameItem item : stakedItems) {
			if (item.amount < 1) {
				continue;
			}
			if (Item.itemStackable[item.id] || Item.itemIsNote[item.id]) {
				c.getItems().addItem(item.id, item.amount);
			} else {
				c.getItems().addItem(item.id, 1);
			}
		}
		stakedItems.clear();
		for (int i = 0; i < c.duelRule.length; i++) {
			c.duelRule[i] = false;
		}
	}

	public void resetDuel() {
		if (c.isDead) {
			c.lostDuel = true;
		}
		c.getPA().showOption(3, 0, "Challenge");
		c.headIconHints = 0;
		for (int i = 0; i < c.duelRule.length; i++) {
			c.duelRule[i] = false;
		}
		c.inDuelScreen = false;
		c.getPA().createPlayerHints(10, -1);
		c.canOffer = true;
		c.duelStatus = 0;
		c.duelSpaceReq = 0;
		c.duelingWith = 0;
		c.getPA().requestUpdates();
		c.getCombat().resetPlayerAttack();
		c.duelRequested = false;
	}

	public void resetDuelItems() {
		stakedItems.clear();
		otherStakedItems.clear();
	}

	public void changeDuelStuff() {
		Player o = PlayerHandler.players[c.duelingWith];
		if (o == null) {
			return;
		}
		o.duelStatus = 1;
		c.duelStatus = 1;
		o.getPA().sendFrame126("", 6684);
		c.getPA().sendFrame126("", 6684);
	}

	public void selectRule(int i) { // rules

		Player o = PlayerHandler.players[c.duelingWith];
		if (o == null) {
			return;
		}
		if (!c.canOffer)
			return;
		c.getPA().sendString("", 6684);
		o.getPA().sendString("", 6684);
		changeDuelStuff();
		o.duelSlot = c.duelSlot;
		if (i >= 11 && c.duelSlot > -1) {
			if (c.playerEquipment[c.duelSlot] > 0) {
				if (!c.duelRule[i]) {
					c.duelSpaceReq++;
				} else {
					c.duelSpaceReq--;
				}
			}
			if (o.playerEquipment[o.duelSlot] > 0) {
				if (!o.duelRule[i]) {
					o.duelSpaceReq++;
				} else {
					o.duelSpaceReq--;
				}
			}
		}

		if (i >= 11) {
			if (c.getItems().freeSlots() < (c.duelSpaceReq)
					|| o.getItems().freeSlots() < (o.duelSpaceReq)) {
				c.sendMessage("You or your opponent don't have the required space to set this rule.");
				if (c.playerEquipment[c.duelSlot] > 0) {
					c.duelSpaceReq--;
				}
				if (o.playerEquipment[o.duelSlot] > 0) {
					o.duelSpaceReq--;
				}
				return;
			}
		}
		if (i >= 11 && i <= 21) {
			if (!c.duelRule[i]) {
				c.getPA().sendFrame87(286, 500000 + (i * 2));
			} else {
				c.getPA().sendFrame87(286, 500000 + i);
			}
		}
		if (!c.duelRule[i]) {
			c.duelRule[i] = true;
			c.duelOption += c.DUEL_RULE_ID[i];
		} else {
			c.duelRule[i] = false;
			c.duelOption -= c.DUEL_RULE_ID[i];
		}

		c.getPA().sendFrame87(286, c.duelOption);
		o.duelOption = c.duelOption;
		o.duelRule[i] = c.duelRule[i];
		o.getPA().sendFrame87(286, o.duelOption);

		if (c.duelRule[8]) {
			if (c.duelRule[1]) {
				c.duelTeleX = 3366 + Misc.random(12);
				o.duelTeleX = c.duelTeleX - 1;
				c.duelTeleY = 3246 + Misc.random(6);
				o.duelTeleY = c.duelTeleY;
			}
		} else {
			if (c.duelRule[1]) {
				c.duelTeleX = 3335 + Misc.random(12);
				o.duelTeleX = c.duelTeleX - 1;
				c.duelTeleY = 3246 + Misc.random(6);
				o.duelTeleY = c.duelTeleY;
			}
		}

	}

	/*
	 * 
	 * public void selectRule(int i) { // rules Client o = (Client)
	 * PlayerHandler.players[c.duelingWith]; if(o == null) { return; } if
	 * (!c.canOffer) return; changeDuelStuff(); o.duelSlot = c.duelSlot; if(i >=
	 * 11 && c.duelSlot > -1) { if(c.playerEquipment[c.duelSlot] > 0) {
	 * if(!c.duelRule[i]) { c.duelSpaceReq++; } else { c.duelSpaceReq--; } }
	 * if(o.playerEquipment[o.duelSlot] > 0) { if(!o.duelRule[i]) {
	 * o.duelSpaceReq++; } else { o.duelSpaceReq--; } } }
	 * 
	 * if(i >= 11) { if(c.getItems().freeSlots() < (c.duelSpaceReq ) ||
	 * o.getItems().freeSlots() < (o.duelSpaceReq)) { c.sendMessage(
	 * "You or your opponent don't have the required space to set this rule.");
	 * if(c.playerEquipment[c.duelSlot] > 0) { c.duelSpaceReq--; }
	 * if(o.playerEquipment[o.duelSlot] > 0) { o.duelSpaceReq--; } return; } }
	 * 
	 * if(!c.duelRule[i]) { c.duelRule[i] = true; c.duelOption +=
	 * c.DUEL_RULE_ID[i]; } else { c.duelRule[i] = false; c.duelOption -=
	 * c.DUEL_RULE_ID[i]; }
	 * 
	 * o.duelOption = c.duelOption; o.duelRule[i] = c.duelRule[i];
	 * c.getPA().sendFrame87(286, c.duelOption); o.getPA().sendFrame87(286,
	 * o.duelOption);
	 * 
	 * if(c.duelRule[8]){ if(c.duelRule[1]) { c.duelTeleX = 3366 +
	 * Misc.random(12); o.duelTeleX = c.duelTeleX-1; c.duelTeleY = 3246 +
	 * Misc.random(6); o.duelTeleY = c.duelTeleY; } } else { if(c.duelRule[1]) {
	 * c.duelTeleX = 3335 + Misc.random(12); o.duelTeleX = c.duelTeleX-1;
	 * c.duelTeleY = 3246 + Misc.random(6); o.duelTeleY = c.duelTeleY; } }
	 * 
	 * }
	 */

	public static final int[] RULE_IDS = { 1, 2, 16, 32, 64, 128, 256, 512,
			1024, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288,
			2097152, 8388608, 16777216, 67108864, 134217728 };
}
