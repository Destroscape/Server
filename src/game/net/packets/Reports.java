package game.net.packets;

import game.content.ReportHandler;
import game.entity.player.Player;

public class Reports implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		try {
			ReportHandler.handleReport(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}