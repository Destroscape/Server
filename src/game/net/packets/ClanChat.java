package game.net.packets;

import engine.util.Misc;
import game.entity.player.Player;

/*
 * Project Insanity - Evolved v.3
 * ClanChat.java
 */

public class ClanChat implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		String textSent = Misc.longToPlayerName2(c.getInStream().readQWord());
		textSent = textSent.replaceAll("_", " ");
		// Server.clanChat.handleClanChat(c, textSent);
	}

}
