package game.net.packets;

import game.entity.npc.NPCHandler;
import game.entity.player.Player;

/*
 * Project Insanity - Evolved v.3
 * ItemOnNpc.java
 */

public class ItemOnNpc implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		final int itemId = c.getInStream().readSignedWordA();
		final int i = c.getInStream().readSignedWordA();
		final int slot = c.getInStream().readSignedWordBigEndian();
		final int npcId = NPCHandler.npcs[i].npcType;
		if (!c.getItems().playerHasItem(itemId, 1, slot)) {
			return;
		}
		UseItem.ItemonNpc(c, itemId, npcId, slot);
	}

}
