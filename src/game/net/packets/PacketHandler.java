package game.net.packets;

import game.entity.player.AttackPlayer;
import game.entity.player.ChallengePlayer;
import game.entity.player.Player;
import game.entity.player.commands.CommandPacket;

/*
 * Project Insanity - Evolved v.3
 * PacketHandler.java
 */

public class PacketHandler {

	private static PacketType packetId[] = new PacketType[256];

	static {
		final SilentPacket u = new SilentPacket();
		PacketHandler.packetId[3] = u;
		PacketHandler.packetId[202] = u;
		PacketHandler.packetId[77] = u;
		PacketHandler.packetId[86] = u;
		PacketHandler.packetId[78] = u;
		PacketHandler.packetId[36] = u;
		PacketHandler.packetId[226] = u;
		PacketHandler.packetId[246] = u;
		PacketHandler.packetId[148] = u;
		PacketHandler.packetId[183] = u;
		PacketHandler.packetId[230] = u;
		PacketHandler.packetId[136] = u;
		PacketHandler.packetId[189] = u;
		PacketHandler.packetId[152] = u;
		PacketHandler.packetId[200] = u;
		PacketHandler.packetId[85] = u;
		PacketHandler.packetId[165] = u;
		PacketHandler.packetId[238] = u;
		PacketHandler.packetId[150] = u;
		PacketHandler.packetId[40] = new Dialogue();
		final ClickObject co = new ClickObject();
		PacketHandler.packetId[132] = co;
		PacketHandler.packetId[252] = co;
		PacketHandler.packetId[70] = co;
		PacketHandler.packetId[57] = new ItemOnNpc();
		final ClickNPC cn = new ClickNPC();
		PacketHandler.packetId[72] = cn;
		packetId[128] = new ChallengePlayer();
		PacketHandler.packetId[131] = cn;
		PacketHandler.packetId[155] = cn;
		PacketHandler.packetId[17] = cn;
		PacketHandler.packetId[21] = cn;
		PacketHandler.packetId[16] = new ItemClick2();
		PacketHandler.packetId[75] = new ItemClick3();
		PacketHandler.packetId[122] = new ClickItem();
		PacketHandler.packetId[241] = new ClickingInGame();
		PacketHandler.packetId[4] = new Chat();
		PacketHandler.packetId[236] = new PickupItem();
		PacketHandler.packetId[87] = new DropItem();
		PacketHandler.packetId[185] = new ClickingButtons();
		PacketHandler.packetId[130] = new ClickingStuff();
		//PacketHandler.packetId[103] = new Commands();
		PacketHandler.packetId[103] = new CommandPacket();
		PacketHandler.packetId[214] = new MoveItems();
		PacketHandler.packetId[237] = new MagicOnItems();
		PacketHandler.packetId[181] = new MagicOnFloorItems();
		PacketHandler.packetId[202] = new IdleLogout();
		final AttackPlayer ap = new AttackPlayer();
		PacketHandler.packetId[73] = ap;
		PacketHandler.packetId[249] = ap;
		PacketHandler.packetId[128] = new ChallengePlayer();
		packetId[39] = new Trade();
		packetId[139] = new FollowPlayer();
		PacketHandler.packetId[41] = new WearItem();
		PacketHandler.packetId[145] = new RemoveItem();
		PacketHandler.packetId[117] = new Bank5();
		PacketHandler.packetId[43] = new Bank10();
		PacketHandler.packetId[129] = new BankAll();
		PacketHandler.packetId[101] = new ChangeAppearance();
		final PrivateMessaging pm = new PrivateMessaging();
		PacketHandler.packetId[188] = pm;
		PacketHandler.packetId[126] = pm;
		PacketHandler.packetId[215] = pm;
		PacketHandler.packetId[59] = pm;
		PacketHandler.packetId[95] = pm;
		PacketHandler.packetId[133] = pm;
		PacketHandler.packetId[135] = new BankX1();
		PacketHandler.packetId[208] = new BankX2();
		final Walking w = new Walking();
		PacketHandler.packetId[98] = w;
		PacketHandler.packetId[164] = w;
		PacketHandler.packetId[248] = w;
		PacketHandler.packetId[53] = new ItemOnItem();
		PacketHandler.packetId[192] = new ItemOnObject();
		final ChangeRegions cr = new ChangeRegions();
		PacketHandler.packetId[121] = cr;
		PacketHandler.packetId[210] = cr;
		PacketHandler.packetId[60] = new JoinChat();
		PacketHandler.packetId[127] = new ReceiveString();
		PacketHandler.packetId[213] = new InterfaceAction();
		packetId[14] = new ItemOnPlayer();
		packetId[218] = new Reports();
	}

	public static void processPacket(final Player c, final int packetType,
			final int packetSize) {
		if (packetType == -1) {
			return;
		}
		final PacketType p = PacketHandler.packetId[packetType];
		if (p != null) {
			try {
				// System.out.println("packet: " + packetType);
				p.processPacket(c, packetType, packetSize);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Unhandled packet type: " + packetType
					+ " - size: " + packetSize);
		}
	}

}
