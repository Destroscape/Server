package game.net.packets;

import game.Server;
import game.content.PriceChecker;
import game.entity.player.Player;
import game.minigame.bountyhunter.BountyHunter;

/*
 * Project Insanity - Evolved v.3
 * BankX2.java
 */

public class BankX2 implements PacketType {
	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		if (c.inDung() || c.inWild() && !BountyHunter.safeArea(c) || c.inFightCaves() || c.inFightPits()
				|| c.inPcGame()) {
			return;
		}
		int Xamount = c.getInStream().readDWord();
		if (Xamount == 0)
			Xamount = 1;

		if (c.buyingX) {
			if (Xamount <= 500) {
				c.getShops().buyItem(c.xRemoveId, c.xRemoveSlot, Xamount);
			} else {
				c.sendMessage("You cannot buy more than 500 at a time.");
			}
			c.xRemoveSlot = 0;
			c.xInterfaceId = 0;
			c.xRemoveId = 0;
			c.buyingX = false;
		}
		switch (c.xInterfaceId) {
		
		case 2274:
			if (c.inPartyRoom) {
				Server.partyRoom.withdrawItem(c, c.xRemoveSlot);
			}
			break;

		case 43933:
			PriceChecker.withdrawItem(c, c.price[c.xRemoveSlot], c.xRemoveSlot,
					Xamount);
			break;

		case 5064:
			if (c.inPartyRoom) {
				Server.partyRoom.offerItem(c, c.xRemoveId, c.xRemoveSlot);
				return;
			}
			if (!c.usingBoB) {
				if (c.inTrade) {
					c.sendMessage("You can't store items while trading!");
					return;
				}
				;
				c.getItems().bankItem(c.playerItems[c.xRemoveSlot],
						c.xRemoveSlot, Xamount);
			} else {
				c.getPA().addToBoB(c.xRemoveSlot, Xamount);
			}
			break;

		case 2700:
			if (c.usingBoB) {
				c.getPA().takeFromBoB(c.xRemoveSlot, Xamount);
			}
			break;

		case 5382:
			c.getItems().fromBank(c.bankItems[c.xRemoveSlot], c.xRemoveSlot,
					Xamount);
			break;

		case 3322:
			if (!c.getItems().playerHasItem(c.xRemoveId, Xamount))
				return;
			if (c.duelStatus <= 0) {
				if (Xamount > c.getItems().getItemAmount(c.xRemoveId))
					c.getTradeAndDuel().tradeItem(c.xRemoveId, c.xRemoveSlot,
							c.getItems().getItemAmount(c.xRemoveId));
				else
					c.getTradeAndDuel().tradeItem(c.xRemoveId, c.xRemoveSlot,
							Xamount);
			} else {
				if (Xamount > c.getItems().getItemAmount(c.xRemoveId))
					c.getTradeAndDuel().stakeItem(c.xRemoveId, c.xRemoveSlot,
							c.getItems().getItemAmount(c.xRemoveId));
				else
					c.getTradeAndDuel().stakeItem(c.xRemoveId, c.xRemoveSlot,
							Xamount);
			}
			break;

		case 3415:
			if (!c.getItems().playerHasItem(c.xRemoveId, Xamount))
				return;
			if (c.duelStatus <= 0) {
				c.getTradeAndDuel().fromTrade(c.xRemoveId, Xamount);
			}
			break;

		case 6669:
			if (!c.getItems().playerHasItem(c.xRemoveId, Xamount))
				return;
			c.getTradeAndDuel().fromDuel(c.xRemoveId, c.xRemoveSlot, Xamount);
			break;
		}
	}

}
