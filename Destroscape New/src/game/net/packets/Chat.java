package game.net.packets;

import engine.util.Misc;
import game.content.ReportHandler;
import game.entity.player.Player;

public class Chat implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		c.setChatTextEffects(c.getInStream().readUnsignedByteS());
		c.setChatTextColor(c.getInStream().readUnsignedByteS());
		c.setChatTextSize((byte) (c.packetSize - 2));
		c.inStream.readBytes_reverseA(c.getChatText(), c.getChatTextSize(), 0);
		c.getPA().writeChatLog(Misc.textUnpack(c.getChatText(), packetSize-2));
		ReportHandler.addText(c.playerName, c.getChatText(), packetSize - 2);
		if (System.currentTimeMillis() < c.muteEnd) {
			c.sendMessage("You are muted, send an appeal on the forums.");
			return;
		} else {
			c.setChatTextUpdateRequired(true);
			c.muteEnd = 0;
		}
	}
}
