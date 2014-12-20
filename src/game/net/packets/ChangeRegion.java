package game.net.packets;

import game.Server;
import game.entity.player.Player;

/*
 * Project Insanity - Evolved v.3
 * ChangeRegion.java
 */

public class ChangeRegion implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		//Server.objectHandler.updateObjects(c);
		c.getPA().removeObjects();
	}

}
