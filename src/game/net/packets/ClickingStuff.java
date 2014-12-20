package game.net.packets;

import engine.util.Misc;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;

/*
 * Project Insanity - Evolved v.3
 * ClickingStuff.java
 */

public class ClickingStuff implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		if (c.inTrade) {
			if (!c.acceptedTrade) {
				Misc.println("trade reset");
				c.getTradeAndDuel().declineTrade();
			}
		}
		final Player o = PlayerHandler.players[c.duelingWith];
		if (o != null) {
			if (c.duelStatus >= 1 && c.duelStatus <= 4) {
				c.getTradeAndDuel().declineDuel();
				o.getTradeAndDuel().declineDuel();
			}
		}
		if (c.duelStatus == 6) {
			c.getTradeAndDuel().claimStakedItems();
		}
	}

}
