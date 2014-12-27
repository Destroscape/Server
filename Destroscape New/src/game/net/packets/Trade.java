package game.net.packets;

import game.Config;
import game.entity.player.Player;

/*
 * Project Insanity - Evolved v.3
 * Trade.java
 */

public class Trade implements PacketType {

	@SuppressWarnings("unused")
	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		final int tradeId = c.getInStream().readSignedWordBigEndian();
		c.getPA().resetFollow();
		if (c.disconnected) {
			c.tradeStatus = 0;
		}
		if (c.arenas()) {
			c.sendMessage("You can't trade inside the arena!");
			return;
		}
		/** Player Selling Options **/
		if (c.playerRights == 0 && !Config.REGULAR_CAN_TRADE_ITEMS) {
			c.sendMessage("@red@Trading items as a Regular Player has been disabled.");
			return;
		} else if (c.playerRights == 5 && !Config.DONATOR_CAN_TRADE_ITEMS) {
			c.sendMessage("@red@Trading items as a Donator has been disabled.");
			return;
		} else if (c.playerRights == 6 && !Config.SUPDONATOR_CAN_TRADE_ITEMS) {
			c.sendMessage("@red@Trading items as a Super Donator has been disabled.");
			return;
		} else if (c.playerRights == 1 && !Config.MODERATOR_CAN_TRADE_ITEMS) {
			c.sendMessage("@red@Trading items as a Moderator has been disabled.");
			return;
		} else if (c.playerRights == 6 && !Config.OWNER_CAN_TRADE_ITEMS) {
			c.sendMessage("@red@Trading items as a Owner has been disabled.");
			return;
		}
		if (tradeId != c.playerId) {
			c.getTradeAndDuel().requestTrade(tradeId);
		}
	}

}
