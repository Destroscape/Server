package game.net.packets;

import game.entity.player.Player;

/*
 * Project Insanity - Evolved v.3
 * ChangeApperance.java
 */

public class ChangeAppearance implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		final int gender = c.getInStream().readSignedByte();
		final int head = c.getInStream().readSignedByte();
		final int jaw = c.getInStream().readSignedByte();
		final int torso = c.getInStream().readSignedByte();
		final int arms = c.getInStream().readSignedByte();
		final int hands = c.getInStream().readSignedByte();
		final int legs = c.getInStream().readSignedByte();
		final int feet = c.getInStream().readSignedByte();
		final int hairColour = c.getInStream().readSignedByte();
		final int torsoColour = c.getInStream().readSignedByte();
		final int legsColour = c.getInStream().readSignedByte();
		final int feetColour = c.getInStream().readSignedByte();
		final int skinColour = c.getInStream().readSignedByte();
		if (c.canChangeAppearance) {
			c.playerAppearance[0] = gender; // gender
			c.playerAppearance[1] = head; // head
			c.playerAppearance[2] = torso;// Torso
			c.playerAppearance[3] = arms; // arms
			c.playerAppearance[4] = hands; // hands
			c.playerAppearance[5] = legs; // legs
			c.playerAppearance[6] = feet; // feet
			c.playerAppearance[7] = jaw; // beard
			c.playerAppearance[8] = hairColour; // hair colour
			c.playerAppearance[9] = torsoColour; // torso colour
			c.playerAppearance[10] = legsColour; // legs colour
			c.playerAppearance[11] = feetColour; // feet colour
			c.playerAppearance[12] = skinColour; // skin colour
			c.getPA().removeAllWindows();
			c.getPA().requestUpdates();
			c.canChangeAppearance = false;
		}
	}

}
