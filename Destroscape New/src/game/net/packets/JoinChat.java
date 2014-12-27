package game.net.packets;

import engine.util.Misc;
import game.Server;
import game.content.clan.Clan;
import game.entity.player.Player;

public class JoinChat implements PacketType {
	@Override
	public void processPacket(Player paramClient, int paramInt1, int paramInt2) {
		String str = Misc.longToPlayerName2(
				paramClient.getInStream().readQWord()).replaceAll("_", " ");
		if ((str != null) && (str.length() > 0) && (paramClient.clan == null)) {
			Clan localClan = Server.clanManager.getClan(str);
			if (localClan != null)
				localClan.addMember(paramClient);
			else if (str.equalsIgnoreCase(paramClient.playerName))
				Server.clanManager.create(paramClient);
			else {
				paramClient.sendMessage(Misc.formatPlayerName(str)
						+ " has not created a clan yet.");
			}
			paramClient.getPA().refreshSkill(21);
			paramClient.getPA().refreshSkill(22);
			paramClient.getPA().refreshSkill(23);
		}
	}
}