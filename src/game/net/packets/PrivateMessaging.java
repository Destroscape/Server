package game.net.packets;

import engine.network.Connection;
import engine.util.Misc;
import game.Config;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;

public class PrivateMessaging implements PacketType {

	public final int ADD_FRIEND = 188, SEND_PM = 126, REMOVE_FRIEND = 215,
			CHANGE_PM_STATUS = 95, REMOVE_IGNORE = 59, ADD_IGNORE = 133;

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		switch (packetType) {

		case ADD_FRIEND:
			c.friendUpdate = true;
			final long friendToAdd = c.getInStream().readQWord();
			boolean canAdd = true;

			for (final long friend : c.friends) {
				if (friend != 0 && friend == friendToAdd) {
					canAdd = false;
					c.sendMessage(friendToAdd
							+ " is already on your friends list.");
				}
			}
			if (canAdd == true) {
				for (int i1 = 0; i1 < c.friends.length; i1++) {
					if (c.friends[i1] == 0) {
						c.friends[i1] = friendToAdd;
						for (int i2 = 1; i2 < Config.MAX_PLAYERS; i2++) {
							if (PlayerHandler.players[i2] != null
									&& PlayerHandler.players[i2].isActive
									&& Misc.playerNameToInt64(PlayerHandler.players[i2].playerName) == friendToAdd) {
								final Player o = PlayerHandler.players[i2];
								if (o != null) {
									if (PlayerHandler.players[i2].privateChat == 0
											|| PlayerHandler.players[i2].privateChat == 1
											&& o.getPA()
													.isInPM(Misc
															.playerNameToInt64(c.playerName))) {
										c.getPA().loadPM(friendToAdd, 1);
										break;
									}
								}
							}
						}
						break;
					}
				}
			}
			break;
		case SEND_PM:
			final long sendMessageToFriendId = c.getInStream().readQWord();
			final byte pmchatText[] = new byte[100];
			final int pmchatTextSize = (byte) (packetSize - 8);
			c.getInStream().readBytes(pmchatText, pmchatTextSize, 0);
			c.getPA().writePMLog(Misc.textUnpack(pmchatText, packetSize-8));
			if (Connection.isMuted(c)) {
				return;
			}
			final long myName = Misc.playerNameToInt64(c.playerName);
			for (final long element : c.friends) {
				if (element == sendMessageToFriendId) {
					boolean pmSent = false;

					for (int i2 = 1; i2 < Config.MAX_PLAYERS; i2++) {
						if (PlayerHandler.players[i2] != null
								&& PlayerHandler.players[i2].isActive
								&& Misc.playerNameToInt64(PlayerHandler.players[i2].playerName) == sendMessageToFriendId) {
							final Player o = PlayerHandler.players[i2];
							if (o != null) {
								if (c.playerRights >= 2
										|| PlayerHandler.players[i2].privateChat == 0
										|| PlayerHandler.players[i2].privateChat == 1
										&& o.getPA().isInPM(myName)) {
									o.getPA().sendPM(myName, c.playerRights,
											pmchatText, pmchatTextSize);
									pmSent = true;
								}
							}
							break;
						}
					}
					if (!pmSent) {
						c.sendMessage("That player is currently offline.");
						break;
					}
				}
			}
			break;

		case REMOVE_FRIEND:
			c.friendUpdate = true;
			final long friendToRemove = c.getInStream().readQWord();

			for (int i1 = 0; i1 < c.friends.length; i1++) {
				if (c.friends[i1] == friendToRemove) {
					for (int i2 = 1; i2 < Config.MAX_PLAYERS; i2++) {
						final Player o = PlayerHandler.players[i2];
						if (o != null) {
							if (c.friends[i1] == Misc
									.playerNameToInt64(PlayerHandler.players[i2].playerName)) {
								o.getPA().updatePM(c.playerId, 0);
								break;
							}
						}
					}
					c.friends[i1] = 0;
					break;
				}
			}
			break;

		case REMOVE_IGNORE:
			final int ii = c.getInStream().readDWord();
			final int i2i = c.getInStream().readDWord();
			final int i3i = c.getInStream().readDWord();
			// for other status changing
			c.getPA().handleStatus(ii, i2i, i3i);
			c.friendUpdate = true;
			final long ignore = c.getInStream().readQWord();
			for (int i = 0; i < c.ignores.length; i++) {
				if (c.ignores[i] == ignore) {
					c.ignores[i] = 0;
					break;
				}
			}
			break;

		case CHANGE_PM_STATUS:
			c.getInStream().readUnsignedByte();
			c.privateChat = c.getInStream().readUnsignedByte();
			c.getInStream().readUnsignedByte();
			for (int i1 = 1; i1 < Config.MAX_PLAYERS; i1++) {
				if (PlayerHandler.players[i1] != null
						&& PlayerHandler.players[i1].isActive == true) {
					final Player o = PlayerHandler.players[i1];
					if (o != null) {
						o.getPA().updatePM(c.playerId, 1);
					}
				}
			}
			break;

		case ADD_IGNORE:
			final int a = c.getInStream().readDWord();
			final int a2 = c.getInStream().readDWord();
			final int j3 = 18;
			// for other status changing
			c.getPA().handleStatus(a, a2, j3);
			c.friendUpdate = true;
			final long ignoreAdd = c.getInStream().readQWord();

			for (int i = 0; i < c.ignores.length; i++) {
				if (c.ignores[i] == 0) {
					c.ignores[i] = ignoreAdd;
					break;
				}
			}
			break;
		}
	}

}
