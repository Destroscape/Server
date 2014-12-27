package game.net.packets;

import game.entity.player.Player;

/*
 * Project Insanity - Evolved v.3
 * PacketType.java
 */

public interface PacketType {
	public void processPacket(Player c, int packetType, int packetSize);
}
