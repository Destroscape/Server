package game.net.packets;

import game.Server;
import game.content.PriceChecker;
import game.entity.player.Player;
import game.minigame.bountyhunter.BountyHunter;
import game.skill.crafting.JewelryMaking;

/*
 * Project Insanity - Evolved v.3
 * Bank5.java
 */

public class Bank5 implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		final int interfaceId = c.getInStream().readSignedWordBigEndianA();
		final int removeId = c.getInStream().readSignedWordBigEndianA();
		final int removeSlot = c.getInStream().readSignedWordBigEndian();
		if (c.inDung() || c.inWild() && !BountyHunter.safeArea(c) || c.inFightCaves() || c.inFightPits()
				|| c.inPcGame()) {
			return;
		}
		switch (interfaceId) {
		case 43933:
			PriceChecker.withdrawItem(c, removeId, removeSlot, 5);
			break;

		case 3900:
			if (c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
			}
			c.getShops().buyItem(removeId, removeSlot, 1);
			break;

		case 3823:
			if (c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
			}
			c.getShops().sellItem(removeId, removeSlot, 1);
			break;
			
		case 2274:
			if (c.inPartyRoom == true) {
				Server.partyRoom.withdrawItem(c, removeSlot);
				return;
			}
			break;

		case 4233:
			JewelryMaking.jewelryMaking(c, "RING", removeId, 5);
			break;
			
		case 4239:
			JewelryMaking.jewelryMaking(c, "NECKLACE", removeId, 5);
			break;
			
		case 4245:
			JewelryMaking.jewelryMaking(c, "AMULET", removeId, 5);
			break;
			
		case 5064:
			if (!c.usingBoB) {
				if (c.isChecking)
					PriceChecker.depositItem(c, removeId, 5);
				if (c.inTrade) {
					c.getTradeAndDuel().declineTrade(true);
				}
				c.getItems().bankItem(removeId, removeSlot, 5);
			} else {
				c.getPA().addToBoB(removeSlot, 5);
			}
			break;

		case 2700:
			if (c.usingBoB) {
				c.getPA().takeFromBoB(removeSlot, 5);
			}
			break;

		case 5382:
			if (c.inPartyRoom) {
				Server.partyRoom.withdrawItem(c, removeSlot);
				return;
			}
			if (c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
			}
			c.getItems().fromBank(removeId, removeSlot, 5);
			break;

		case 3322:
			if (c.duelStatus <= 0) {
				c.getTradeAndDuel().tradeItem(removeId, removeSlot, 5);
			} else {
				c.getTradeAndDuel().stakeItem(removeId, removeSlot, 5);
			}
			break;

		case 3415:
			if (c.duelStatus <= 0) {
				c.getTradeAndDuel().fromTrade(removeId, 5);
			}
			break;

		case 6669:
			c.getTradeAndDuel().fromDuel(removeId, removeSlot, 5);
			break;

		case 1119:
		case 1120:
		case 1121:
		case 1122:
		case 1123:
			c.getSmithing().readInput(c.playerLevel[c.playerSmithing],
					Integer.toString(removeId), c, 5);
			break;
		}
	}

}
