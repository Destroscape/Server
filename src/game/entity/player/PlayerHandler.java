package game.entity.player;

import engine.util.Misc;
import engine.util.Stream;
import game.Config;
import game.Server;
import game.entity.npc.NPCHandler;

import java.net.InetSocketAddress;

/*
 * Project Insanity - Evolved v.3
 * PlayerHandler.java
 */

public class PlayerHandler {

	public static Object lock = new Object();
	public static Player players[] = new Player[Config.MAX_PLAYERS];
	public static String messageToAll = "";
	public static int playerCount = 0;
	public static String playersCurrentlyOn[] = new String[Config.MAX_PLAYERS];
	public static boolean updateAnnounced;
	public static boolean updateRunning;
	public static int updateSeconds;
	public static long updateStartTime;

	public static Player getPlayer(String name) {
		for (int d = 0; d < Config.MAX_PLAYERS; d++) {
			if (PlayerHandler.players[d] != null) {
				Player p = PlayerHandler.players[d];
				if (p.playerName.equalsIgnoreCase(name)) {
					return p;
				}
			}
		}
		return null;
	}

	private static void removePlayer(final Player plr) {
		if (plr.privateChat != 2) {
			for (int i = 1; i < Config.MAX_PLAYERS; i++) {
				if (PlayerHandler.players[i] == null
						|| PlayerHandler.players[i].isActive == false) {
					continue;
				}
				final Player o = PlayerHandler.players[i];
				if (o != null) {
					o.getPA().updatePM(plr.playerId, 0);
				}
			}
		}
		plr.destruct();
	}

	public static boolean kickAllPlayers = false;

	static {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			PlayerHandler.players[i] = null;
		}
	}

	public static int getPlayerCount() {
		return PlayerHandler.playerCount;
	}

	public static boolean isPlayerOn(final String playerName) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.playersCurrentlyOn[i] != null) {
				if (PlayerHandler.playersCurrentlyOn[i]
						.equalsIgnoreCase(playerName)) {
					return true;
				}
			}
		}
		return false;
	}

	private final Stream updateBlock = new Stream(new byte[Config.BUFFER_SIZE]);

	public void destruct() {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] == null) {
				continue;
			}
			PlayerHandler.players[i].destruct();
			PlayerHandler.players[i] = null;
		}
	}

	public boolean newPlayerClient(final Player client1) {
		int slot = -1;
		for (int i = 1; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] == null
					|| PlayerHandler.players[i].disconnected) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			return false;
		}
		client1.handler = this;
		client1.playerId = slot;
		PlayerHandler.players[slot] = client1;
		PlayerHandler.players[slot].isActive = true;
		PlayerHandler.players[slot].connectedFrom = ((InetSocketAddress) client1
				.getSession().getRemoteAddress()).getAddress().getHostAddress();
		if (Config.SERVER_DEBUG) {
			Misc.println("Player Slot " + slot + " slot 0 "
					+ PlayerHandler.players[0] + " Player Hit "
					+ PlayerHandler.players[slot]);
		}
		return true;
	}

	public void process() {
		updatePlayerNames();
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] == null
					|| !PlayerHandler.players[i].isActive) {
				continue;
			}
			try {
				if (PlayerHandler.players[i].disconnected
						&& (System.currentTimeMillis()
								- PlayerHandler.players[i].logoutDelay > 10000
								|| PlayerHandler.players[i].properLogout || kickAllPlayers)) {

					if (PlayerHandler.players[i].inTrade) {
						final Player o = PlayerHandler.players[PlayerHandler.players[i].tradeWith];
						if (o != null) {
							o.getTradeAndDuel().declineTrade();
						}
					}
					if (PlayerHandler.players[i].duelStatus == 5) {
						final Player o = PlayerHandler.players[PlayerHandler.players[i].duelingWith];
						if (o != null) {
							o.getTradeAndDuel().duelVictory();
						}
					} else if (PlayerHandler.players[i].duelStatus <= 4
							&& PlayerHandler.players[i].duelStatus >= 1) {
						final Player o = PlayerHandler.players[PlayerHandler.players[i].duelingWith];
						if (o != null) {
							o.getTradeAndDuel().declineDuel();
						}
					}
					final Player o = PlayerHandler.players[i];
					if (PlayerSave.saveGame(o)) {
						System.out.println("Game saved for player "
								+ PlayerHandler.players[i].playerName);
					} else {
						System.out.println("Could not save for "
								+ PlayerHandler.players[i].playerName);
					}
					PlayerHandler.removePlayer(PlayerHandler.players[i]);
					PlayerHandler.players[i] = null;
					continue;
				}
				PlayerHandler.players[i].preProcessing();
				while (PlayerHandler.players[i].processQueuedPackets()) {
					;
				}
				PlayerHandler.players[i].process();
				PlayerHandler.players[i].postProcessing();
				PlayerHandler.players[i].getNextPlayerMovement();

			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] == null
					|| !PlayerHandler.players[i].isActive) {
				continue;
			}
			try {
				if (PlayerHandler.players[i].disconnected
						&& (System.currentTimeMillis()
								- PlayerHandler.players[i].logoutDelay > 10000
								|| PlayerHandler.players[i].properLogout || kickAllPlayers)) {
					if (PlayerHandler.players[i].inTrade) {
						final Player o = PlayerHandler.players[PlayerHandler.players[i].tradeWith];
						if (o != null) {
							o.getTradeAndDuel().declineTrade();
						}
					}
					if (PlayerHandler.players[i].duelStatus == 5) {
						final Player o1 = PlayerHandler.players[PlayerHandler.players[i].duelingWith];
						if (o1 != null) {
							o1.getTradeAndDuel().duelVictory();
						}
					} else if (PlayerHandler.players[i].duelStatus <= 4
							&& PlayerHandler.players[i].duelStatus >= 1) {
						final Player o1 = PlayerHandler.players[PlayerHandler.players[i].duelingWith];
						if (o1 != null) {
							o1.getTradeAndDuel().declineDuel();
						}
					}
					final Player o1 = PlayerHandler.players[i];
					if (PlayerSave.saveGame(o1)) {
						System.out.println("Game saved for player "
								+ PlayerHandler.players[i].playerName);
					} else {
						System.out.println("Could not save for "
								+ PlayerHandler.players[i].playerName);
					}
					PlayerHandler.removePlayer(PlayerHandler.players[i]);
					PlayerHandler.players[i] = null;
				} else {
					// if(o.g) {
					if (!PlayerHandler.players[i].initialized) {
						PlayerHandler.players[i].initialize();
						PlayerHandler.players[i].initialized = true;
					} else {
						PlayerHandler.players[i].update();
					}
					// }
				}
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		if (PlayerHandler.updateRunning && !PlayerHandler.updateAnnounced) {
			PlayerHandler.updateAnnounced = true;
			Server.UpdateServer = true;
		}
		/*if (PlayerHandler.updateRunning
				&& System.currentTimeMillis() - PlayerHandler.updateStartTime > PlayerHandler.updateSeconds * 1000) {
			kickAllPlayers = true;
		}*/
      if(updateRunning && System.currentTimeMillis() - updateStartTime > (long)(updateSeconds * 1000)) {
         this.kickAllPlayers = true;
      }
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] == null
					|| !PlayerHandler.players[i].isActive) {
				continue;
			}
			try {
				PlayerHandler.players[i].clearUpdateFlags();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		// }
	}

	public void updateNPC(final Player plr, final Stream str) {
		updateBlock.currentOffset = 0;

		str.createFrameVarSizeWord(65);
		str.initBitAccess();

		str.writeBits(8, plr.npcListSize);
		final int size = plr.npcListSize;
		plr.npcListSize = 0;
		for (int i = 0; i < size; i++) {
			if (plr.RebuildNPCList == false
					&& plr.withinDistance(plr.npcList[i]) == true) {
				plr.npcList[i].updateNPCMovement(str);
				plr.npcList[i].appendNPCUpdateBlock(updateBlock);
				plr.npcList[plr.npcListSize++] = plr.npcList[i];
			} else {
				final int id = plr.npcList[i].npcId;
				plr.npcInListBitmap[id >> 3] &= ~(1 << (id & 7));
				str.writeBits(1, 1);
				str.writeBits(2, 3);
			}
		}
		for (int i = 0; i < NPCHandler.maxNPCs; i++) {
			if (NPCHandler.npcs[i] != null) {
				final int id = NPCHandler.npcs[i].npcId;
				if (plr.RebuildNPCList == false
						&& (plr.npcInListBitmap[id >> 3] & 1 << (id & 7)) != 0) {

				} else if (plr.withinDistance(NPCHandler.npcs[i]) == false) {

				} else {
					plr.addNewNPC(NPCHandler.npcs[i], str, updateBlock);
				}
			}
		}
		plr.RebuildNPCList = false;
		if (updateBlock.currentOffset > 0) {
			str.writeBits(14, 16383);
			str.finishBitAccess();
			str.writeBytes(updateBlock.buffer, updateBlock.currentOffset, 0);
		} else {
			str.finishBitAccess();
		}
		str.endFrameVarSizeWord();
		// }
	}

	public void updatePlayer(final Player plr, final Stream str) {
		updateBlock.currentOffset = 0;
		/*
		 * if (updateRunning) { str.createFrame(114);
		 * str.writeWordBigEndian(PlayerHandler.updateSeconds * 50 / 30); }
		 */
		if (updateRunning && !updateAnnounced) {
			str.createFrame(114);
			str.writeWordBigEndian(PlayerHandler.updateSeconds*50/30);
		}
		plr.updateThisPlayerMovement(str);
		final boolean saveChatTextUpdate = plr.isChatTextUpdateRequired();
		plr.setChatTextUpdateRequired(false);
		plr.appendPlayerUpdateBlock(updateBlock);
		plr.setChatTextUpdateRequired(saveChatTextUpdate);
		str.writeBits(8, plr.playerListSize);
		final int size = plr.playerListSize;
		plr.playerListSize = 0;
		for (int i = 0; i < size; i++) {
			if (!plr.didTeleport && !plr.playerList[i].didTeleport
					&& plr.withinDistance(plr.playerList[i])) {
				plr.playerList[i].updatePlayerMovement(str);
				plr.playerList[i].appendPlayerUpdateBlock(updateBlock);
				plr.playerList[plr.playerListSize++] = plr.playerList[i];
			} else {
				final int id = plr.playerList[i].playerId;
				plr.playerInListBitmap[id >> 3] &= ~(1 << (id & 7));
				str.writeBits(1, 1);
				str.writeBits(2, 3);
			}
		}
		int j = 0;
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (plr.playerListSize >= 254) {
				break;
			}
			if (updateBlock.currentOffset + str.currentOffset >= 4900) {
				break;
			}
			if (PlayerHandler.players[i] == null
					|| !PlayerHandler.players[i].isActive
					|| PlayerHandler.players[i] == plr) {
				continue;
			}
			final int id = PlayerHandler.players[i].playerId;
			if ((plr.playerInListBitmap[id >> 3] & 1 << (id & 7)) != 0) {
				continue;
			}
			if (j >= 10) {
				break;
			}
			if (!plr.withinDistance(PlayerHandler.players[i])) {
				continue;
			}
			plr.addNewPlayer(PlayerHandler.players[i], str, updateBlock);
			j++;
		}

		if (updateBlock.currentOffset > 0) {
			str.writeBits(11, 2047);
			str.finishBitAccess();

			str.writeBytes(updateBlock.buffer, updateBlock.currentOffset, 0);
		} else {
			str.finishBitAccess();
		}

		str.endFrameVarSizeWord();

	}

	public void updatePlayerNames() {
		PlayerHandler.playerCount = 0;
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] != null) {
				PlayerHandler.playersCurrentlyOn[i] = PlayerHandler.players[i].playerName;
				PlayerHandler.playerCount++;
			} else {
				PlayerHandler.playersCurrentlyOn[i] = "";
			}
		}
	}

	public Player[] getPlayers() {
		return players;
	}

}
