package game.entity.player;

import game.net.packets.PacketType;

/*
 * Project Insanity - Evolved v.3
 * ChallangePlayer.java
 */

public class ChallengePlayer implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		switch (packetType) {
		case 128:
			final int answerPlayer = c.getInStream().readUnsignedWord();
			if (PlayerHandler.players[answerPlayer] == null) {
				return;
			}
			if (c.arenas() || c.duelStatus == 5) {
				c.sendMessage("You can't challenge inside the arena!");
				return;
			}
			Player o = PlayerHandler.players[answerPlayer];
			if (c.duelStatus != 0 || o.duelStatus != 0) {
				c.sendMessage("You are currently unable to challenge this player.");
				return;
			}
			if (o.duelStatus > 0) {
				c.sendMessage("That player is currently dueling someone else.");
				return;
			}
			c.getTradeAndDuel().requestDuel(answerPlayer);
			break;
		}
	}

}
