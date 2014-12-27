package game.net.packets;

import game.Server;
import game.entity.player.Player;

/*
 * Project Insanity - Evolved v.3
 * ChangeRegions.java
 */

public class ChangeRegions implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		Server.objectHandler.updateObjects(c);
		Server.itemHandler.reloadItems(c);
		Server.objectManager.loadObjects(c);

		c.saveFile = true;

		if (c.skullTimer > 0) {
			c.isSkulled = true;
			c.headIconPk = 0;
			c.getPA().requestUpdates();
		}
	}

}
