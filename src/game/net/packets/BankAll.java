package game.net.packets;

import game.content.PriceChecker;
import game.entity.player.Player;
import game.item.GameItem;
import game.item.Item;
import game.minigame.bountyhunter.BountyHunter;

/*
 * Project Insanity - Evolved v.3
 * BankAll.java
 */

public class BankAll implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		final int removeSlot = c.getInStream().readUnsignedWordA();
		final int interfaceId = c.getInStream().readUnsignedWord();
		final int removeId = c.getInStream().readUnsignedWordA();
		if (c.inDung() || c.inWild() && !BountyHunter.safeArea(c) || c.inFightCaves() || c.inFightPits()
				|| c.inPcGame() || c.duelStatus > 3) {
			return;
		}
		switch (interfaceId) {

		case 43933:
			PriceChecker.withdrawItem(c, removeId, removeSlot,
					c.priceN[removeSlot]);
			break;

		case 3900:
			c.getShops().buyItem(removeId, removeSlot, 10);
			break;

		case 3823:
			c.getShops().sellItem(removeId, removeSlot, 10);
			break;

		case 2700:
			if (c.usingBoB) {
				c.getPA().takeFromBoB(removeSlot, c.bobSlotCount);
			}
			break;

		case 5064:
			if (!c.usingBoB) {
				if (c.inTrade) {
					c.sendMessage("You can't store items while trading!");
					return;
				}
				if (c.isChecking) {
					PriceChecker.depositItem(c, removeId,
							c.playerItemsN[removeSlot]);
					return;
				}
				if (!c.isBanking || c.inTrade) {
					return;
				}
				if (Item.itemStackable[removeId]) {
					c.getItems().bankItem(c.playerItems[removeSlot],
							removeSlot, c.playerItemsN[removeSlot]);
				} else {
					c.getItems().bankItem(c.playerItems[removeSlot],
							removeSlot,
							c.getItems().itemAmount(c.playerItems[removeSlot]));
				}
			} else {
				c.getPA().addToBoB(removeSlot, 28);
			}
			break;

		case 5382:
			c.getItems().fromBank(c.bankItems[removeSlot], removeSlot,
					c.bankItemsN[removeSlot]);
			break;

		case 3322:
			if (c.duelStatus <= 0) {
				if (Item.itemStackable[removeId]) {
					c.getTradeAndDuel().tradeItem(removeId, removeSlot,
							c.playerItemsN[removeSlot]);
				} else {
					c.getTradeAndDuel().tradeItem(removeId, removeSlot, 28);
				}
			} else {
				if (Item.itemStackable[removeId] || Item.itemIsNote[removeId]) {
					c.getTradeAndDuel().stakeItem(removeId, removeSlot,
							c.playerItemsN[removeSlot]);
				} else {
					c.getTradeAndDuel().stakeItem(removeId, removeSlot, 28);
				}
			}
			break;

		case 3415:
			if (c.duelStatus <= 0) {
				if (Item.itemStackable[removeId]) {
					for (GameItem item : c.getTradeAndDuel().offeredItems) {
						if (item.id == removeId) {
							c.getTradeAndDuel().fromTrade(
									removeId,
									c.getTradeAndDuel().offeredItems
											.get(removeSlot).amount);
						}
					}
				} else {
					for (GameItem item : c.getTradeAndDuel().offeredItems) {
						if (item.id == removeId) {
							c.getTradeAndDuel().fromTrade(removeId, 28);
						}
					}
				}
			}
			break;

		case 6669:
			if (Item.itemStackable[removeId] || Item.itemIsNote[removeId]) {
				for (GameItem item : c.getTradeAndDuel().stakedItems) {
					if (item.id == removeId) {
						c.getTradeAndDuel()
								.fromDuel(
										removeId,
										removeSlot,
										c.getTradeAndDuel().stakedItems
												.get(removeSlot).amount);
					}
				}

			} else {
				c.getTradeAndDuel().fromDuel(removeId, removeSlot, 28);
			}
			break;
		}
	}

}
