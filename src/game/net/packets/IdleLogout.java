package game.net.packets;

import game.entity.player.Player;

/*
 * Project Insanity - Evolved v.3
 * IdleLogout.java
 */

public class IdleLogout implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		if (c.underAttackBy > 0 || c.underAttackBy2 > 0) {
			return;
		} else {
			// c.logout();
		}
	}
}
