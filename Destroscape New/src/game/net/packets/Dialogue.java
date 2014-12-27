package game.net.packets;

import game.entity.player.Player;

public class Dialogue implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		if (c.nextChat > 0) {
			c.getDH().sendDialogues(c.nextChat, c.talkingNpc);
		} else {
			c.getDH().sendDialogues(0, -1);
		}
	}

}
