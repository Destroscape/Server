package game.net.packets;

import game.entity.player.Player;
import game.entity.player.PlayerHandler;

public class ChallengePlayer implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		switch (packetType) {
		case 128:
			int answerPlayer = c.getInStream().readUnsignedWord();
			if (PlayerHandler.players[answerPlayer] == null
					|| c.duelStatus == 5) {
				return;
			}
			c.getTradeAndDuel().requestDuel(answerPlayer);
			break;
		}
	}
}
