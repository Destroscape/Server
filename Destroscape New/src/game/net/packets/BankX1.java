package game.net.packets;

import game.entity.player.Player;
import game.minigame.bountyhunter.BountyHunter;

public class BankX1 implements PacketType {

	public static final int PART1 = 135;
	public static final int PART2 = 208;
	public int XremoveSlot, XinterfaceID, XremoveID, Xamount;

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		if (c.inDung() || c.inWild() && !BountyHunter.safeArea(c) || c.inFightCaves() || c.inFightPits()
				|| c.inPcGame()) {
			return;
		}
		if (packetType == 135) {
			c.xRemoveSlot = c.getInStream().readSignedWordBigEndian();
			c.xInterfaceId = c.getInStream().readUnsignedWordA();
			c.xRemoveId = c.getInStream().readSignedWordBigEndian();
		}
		/*if (c.xInterfaceId == 3900) {
			c.buyingX = true;
			c.outStream.createFrame(27);
			return;
		}*/
		if (c.xInterfaceId == 3823) {
			c.getShops().sellItem(c.xRemoveId, c.xRemoveSlot, 100);
			c.xRemoveSlot = 0;
			c.xInterfaceId = 0;
			c.xRemoveId = 0;
			return;
		}
		if (c.xInterfaceId == 3900) {
			c.getShops().buyItem(c.xRemoveId, c.xRemoveSlot, 100);//buy 100
			c.xRemoveSlot = 0;
			c.xInterfaceId = 0;
			c.xRemoveId = 0;
			return;
		}
		if (packetType == BankX1.PART1) {
			c.getOutStream().createFrame(27);
		}
	}
}