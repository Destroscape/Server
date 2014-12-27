package game.net.packets;

import game.Server;
import game.content.PriceChecker;
import game.entity.player.Player;
import game.minigame.bountyhunter.BountyHunter;
import game.skill.crafting.JewelryMaking;

public class RemoveItem implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		final int interfaceId = c.getInStream().readUnsignedWordA();
		final int removeSlot = c.getInStream().readUnsignedWordA();
		final int removeId = c.getInStream().readUnsignedWordA();
		c.getPA().resetAutocast();
		switch (interfaceId) {

		case 1688:
			c.getItems().removeItem(removeId, removeSlot);
			break;

		case 43933:
			PriceChecker.withdrawItem(c, removeId, removeSlot, 1);
			break;

		case 5064:
			if (!c.usingBoB) {
				if (c.inDung() || c.inWild() && !BountyHunter.safeArea(c) || c.inFightCaves()
						|| c.inFightPits() || c.inPcGame() || c.duelStatus > 3) {
					return;
				}
				if (c.inTrade) {
					c.getTradeAndDuel().declineTrade(true);
					return;
				}
				if (c.isChecking) {
					PriceChecker.depositItem(c, removeId, 1);
					return;
				}
				if (c.isBanking) {
					if (c.inDung() || c.inWild() && !BountyHunter.safeArea(c) || c.inFightCaves()
							|| c.inFightPits() || c.inPcGame()
							|| c.duelStatus > 3) {
						return;
					}
					c.getItems().bankItem(removeId, removeSlot, 1);
				}
			} else {
				c.getPA().addToBoB(removeSlot, 1);
			}
			break;
			
		case 2274:
			if (c.inPartyRoom && c.isBanking == false) {
				Server.partyRoom.withdrawItem(c, removeSlot);
				c.isBanking = false;
				return;
			}
			if (removeId == 995 && !c.isBanking) {
				c.sendMessage("You cannt deposit coins");
				return;
			}
			break;

		case 4233:
			JewelryMaking.jewelryMaking(c, "RING", removeId, 1);
			break;
		case 4239:
			JewelryMaking.jewelryMaking(c, "NECKLACE", removeId, 1);
			break;
		case 4245:
			JewelryMaking.jewelryMaking(c, "AMULET", removeId, 1);
			break;

		case 5382:
			if (c.inDung() || c.inWild() && !BountyHunter.safeArea(c) || c.inFightCaves() || c.inFightPits()
					|| c.inPcGame() || c.duelStatus > 3) {
				return;
			}
			c.getItems().fromBank(removeId, removeSlot, 1);
			break;

		case 2700: // Bob Interface
			if (c.inDung() || c.inWild() && !BountyHunter.safeArea(c) || c.inFightCaves() || c.inFightPits()
					|| c.inPcGame() || c.duelStatus > 3) {
				return;
			}
			if (c.usingBoB) {
				c.getPA().takeFromBoB(removeSlot, 1);
			}
			break;

		case 3900:
			c.getShops().buyFromShopPrice(removeId, removeSlot);
			break;

		case 3823:
			c.getShops().sellToShopPrice(removeId, removeSlot);
			break;
		case 3322:
			if (!c.canOffer) {
				return;
			}
			if (c.duelStatus <= 0) {
				c.getTradeAndDuel().tradeItem(removeId, removeSlot, 1);
			} else {
				c.getTradeAndDuel().stakeItem(removeId, removeSlot, 1);
			}
			break;

		case 3415:
			if (!c.canOffer) {
				return;
			}
			if (c.inDung() || c.inWild() && !BountyHunter.safeArea(c) || c.inFightCaves() || c.inFightPits()
					|| c.inPcGame() || c.duelStatus > 3) {
				return;
			}
			if (c.duelStatus <= 0) {
				c.getTradeAndDuel().fromTrade(removeId, 1);
			}
			break;
		case 6669:
			if (c.inDung() || c.inWild() && !BountyHunter.safeArea(c) || c.inFightCaves() || c.inFightPits()
					|| c.inPcGame() || c.duelStatus > 3) {
				return;
			}
			c.getTradeAndDuel().fromDuel(removeId, removeSlot, 1);
			break;

		case 1119:
		case 1120:
		case 1121:
		case 1122:
		case 1123:
			c.getSmithing().readInput(c.playerLevel[c.playerSmithing],
					Integer.toString(removeId), c, 1);
			break;
		}
	}

}
