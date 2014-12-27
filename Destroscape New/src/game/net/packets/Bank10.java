package game.net.packets;

import game.content.PriceChecker;
import game.entity.player.Player;
import game.item.operate.OperateManager;
import game.minigame.bountyhunter.BountyHunter;
import game.skill.crafting.JewelryMaking;

public class Bank10 implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		final int interfaceId = c.getInStream().readUnsignedWordBigEndian();
		final int removeId = c.getInStream().readUnsignedWordA();
		final int removeSlot = c.getInStream().readUnsignedWordA();
		if (c.inDung() || c.inWild() && !BountyHunter.safeArea(c) || c.inFightCaves() || c.inFightPits()
				|| c.inPcGame()) {
			return;
		}
		switch (interfaceId) {

		case 1688:
			if (c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
			}
			OperateManager.execute(c, removeId);
			break;
		case 3900:
			if (c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
			}
			c.getShops().buyItem(removeId, removeSlot, 5);
			break;

		case 3823:
			if (c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
			}
			c.getShops().sellItem(removeId, removeSlot, 5);
			break;

		case 4233:
			JewelryMaking.jewelryMaking(c, "RING", removeId, 10);
			break;
		case 4239:
			JewelryMaking.jewelryMaking(c, "NECKLACE", removeId, 10);
			break;
		case 4245:
			JewelryMaking.jewelryMaking(c, "AMULET", removeId, 10);
			break;

		case 2700:
			if (c.usingBoB) {
				c.getPA().takeFromBoB(removeSlot, 10);
			}
			break;
		case 43933:
			PriceChecker.withdrawItem(c, removeId, removeSlot, 10);
			break;

		case 5064:
			if (!c.usingBoB) {
				if (c.inTrade) {
					c.getTradeAndDuel().declineTrade(true);
					if (c.isChecking)
						PriceChecker.depositItem(c, removeId, 10);
				}
				c.getItems().bankItem(removeId, removeSlot, 10);
			} else {
				c.getPA().addToBoB(removeSlot, 10);
			}
			break;

		case 5382:
			if (c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
			}
			c.getItems().fromBank(removeId, removeSlot, 10);
			break;

		case 3322:
			if (c.duelStatus <= 0) {
				c.getTradeAndDuel().tradeItem(removeId, removeSlot, 10);
			} else {
				c.getTradeAndDuel().stakeItem(removeId, removeSlot, 10);
			}
			break;

		case 3415:
			if (c.duelStatus <= 0) {
				c.getTradeAndDuel().fromTrade(removeId, 10);
			}
			break;

		case 6669:
			c.getTradeAndDuel().fromDuel(removeId, removeSlot, 10);
			break;

		case 1119:
		case 1120:
		case 1121:
		case 1122:
		case 1123:
			c.getSmithing().readInput(c.playerLevel[c.playerSmithing],
					Integer.toString(removeId), c, 10);
			break;
		}
	}

}
