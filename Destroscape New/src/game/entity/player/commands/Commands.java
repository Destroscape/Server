package game.entity.player.commands;

import engine.network.Connection;
import engine.util.Misc;
import game.Config;
import game.Server;
import game.entity.npc.DropDumper;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.item.ItemList;
import game.minigame.weapongame.WeaponGame;
import game.net.packets.PacketType;
import game.sql.DonationManager;
//import game.sql.VoteManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Command Handler
 */

public class Commands implements PacketType {

	/**
	 * Handles who can do which commands
	 */

	boolean canNormal(final Player c) {
		if (c.playerRights >= 0) {
			return true;
		}
		return false;
	}

	boolean canMod(final Player c) {
		if (c.playerRights == 1) {
			return true;
		}
		return false;
	}

	boolean canAdmin(final Player c) {
		if (c.playerRights == 2) {
			return true;
		}
		return false;
	}

	boolean canOwner(final Player c) {
		if (c.playerName.equalsIgnoreCase("jlyons") || c.playerName.equalsIgnoreCase("lawless") || c.playerName.equalsIgnoreCase("luis")
				|| c.playerName.equalsIgnoreCase("jake")) {
			return true;
		}
		return false;
	}

	boolean canDonator(final Player c) {
		if (c.playerRights == 2 || c.playerRights == 5 || c.playerRights == 6) {
			return true;
		}
		return false;
	}

	boolean canExtremeDonator(final Player c) {
		if (c.playerRights == 2 || c.playerRights == 6) {
			return true;
		}
		return false;
	}

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		String realPlayerCommand = c.getInStream().readString();
		String playerCommand = realPlayerCommand.toLowerCase();
		c.getPA().writeCommandLog(playerCommand);
		Misc.println(c.playerName + " playerCommand: " + playerCommand);

		/**
		 * Player command splitter
		 */

		String[] command = playerCommand.split(" ");

		/**
		 * Load player commands here
		 */

		if (canNormal(c)) {
			switch (command[0]) {
			
			case "dump":
				DropDumper.dump();
			return;
			
			case "commands":
				c.sendMessage("::website ::forums ::vote ::donate");
				c.sendMessage("::players ::changepassword <newpassword> ::yell <message>");
				c.sendMessage("::canceltask (costs 10 Slayer Points) ::empty");
				if (canMod(c)) {
					c.sendMessage("::xteleto ::xteletome ::mute ::unmute");
					c.sendMessage("::daymute ::threedaymute ::weekmute ::ipmute");
					c.sendMessage("::unipmute ::dayban ::checkinv ::checkbank");
				}
				if (canAdmin(c)) {
					c.sendMessage("::ban ::unban ::threedayban ::weekban");
				}
				if (canDonator(c)) {
					c.sendMessage("::donatorzone");
				}
				return;
				
			case "empty":
				c.getDH().sendDialogues(3499, -1);
				break;

			case "clan":
				if (c.clan != null) {
					String message = playerCommand.substring(6);
					c.clan.sendChat(c, message);
				}
				return;

			case "website":
				c.getPA().sendFrame126(Config.HOMEPAGE_URL, 12000);
				return;

			case "forums":
				c.getPA().sendFrame126(Config.FORUM_URL, 12000);
				return;

			case "vote":
				c.getPA().sendFrame126(Config.VOTE_URL, 12000);
				return;

			case "donate":
				c.getPA().sendFrame126(Config.DONATE_URL, 12000);
				return;

			case "highscores":
				c.getPA().sendFrame126(Config.HIGHSCORES_URL, 12000);
				return;

			case "checkdonation":
				if (System.currentTimeMillis() - c.lastDonate > 300000) {
					c.lastDonate = System.currentTimeMillis();
					DonationManager.createConnection();
					DonationManager.checkStatus(c);
					DonationManager.destroyConnection();
				} else {
					c.sendMessage("You can only do this once every 5 minutes.");
				}
				return;

			case "auth":
				if (System.currentTimeMillis() - c.lastVote > 300000) {
					c.lastVote = System.currentTimeMillis();
					String[] args = playerCommand.split(" ");
					String auth = args[1];
					//VoteManager.createConnection();
					//VoteManager.checkStatus(c, auth);
					//VoteManager.destroyConnection();
				} else {
					c.sendMessage("You can only do this once every 5 minutes.");
				}
				return;

			case "canceltask":
				if (c.magePoints >= 10) {
					c.taskAmount = 0;
					c.slayerTask = 0;
					c.sendMessage("You have canceled your slayer task for 10 Slayer Points.");
					c.sendMessage("You now have " + c.magePoints
							+ " Slayer Points.");
				} else {
					c.sendMessage("You need 10 Slayer Points to do this.");
				}
				return;

			case "players":
				c.clearPlayersInterface();
				c.sendMessage("There are currently "
						+ PlayerHandler.getPlayerCount() + " players online.");
				c.getPA().sendFrame126(
						Config.SERVER_NAME + " - Online Players", 8144);
				c.getPA().sendFrame126(
						"Online players(" + PlayerHandler.getPlayerCount()
								+ "):", 8145);
				int line = 8147;
				for (int i = 1; i < Config.MAX_PLAYERS; i++) {
					Player p = c.getClient(i);
					if (!c.validClient(i))
						continue;
					if (p.playerName != null) {
						String title = "";
						if (p.playerRights == 1) {
							title = "Mod, ";
						} else if (p.playerRights == 2) {
							title = "Admin, ";
						}
						title += "level-" + p.combatLevel;
						String extra = "";
						if (c.playerRights > 0) {
							extra = "(" + p.playerId + ") ";
						}
						c.getPA().sendFrame126(
								"@dre@" + extra + p.playerName + " (" + title
										+ ")", line);
						line++;
					}
				}
				c.getPA().showInterface(8134);
				c.flushOutStream();
				return;

			case "changepassword":
				try {
					String password = playerCommand.substring(15);
					if (password.length() < 4) {
						c.sendMessage("Your password needs to be bigger than 3 characters.");
						return;
					}
					c.playerPass = password;
					c.sendMessage("Your password is now: " + password);
				} catch (Exception e) {
					c.sendMessage("You must enter a password.");
				}
				return;

			case "yell":
				if (c.isJailed) {
					c.sendMessage("You are jailed please appeal at the forums.");
					c.sendMessage(""+Config.FORUM_URL);
					return;
				}
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Player all = PlayerHandler.players[j];
						String userTitle = "";
						switch (c.playerRights) {
						case 0:
							userTitle = "Player";
							break;
						case 1:
							userTitle = "@yel@Moderator@bla@";
							break;
						case 2:
							userTitle = "@yel@Administrator@bla@";
							break;
						case 5:
							userTitle = "@or2@Donator@bla@";
							break;
						case 6:
							userTitle = "@or2@Extreme Donator@bla@";
							break;

						}
						if (c.playerRights == 1) {
							all.sendMessage("["
									+ userTitle
									+ "]"
									+ " @cr1@ "
									+ (Misc.optimizeText(c.playerName))
									+ ": "
									+ Misc.optimizeText(playerCommand
											.substring(5)) + "");

						} else if (c.playerRights == 2) {
							all.sendMessage("["
									+ userTitle
									+ "]"
									+ " @cr2@ "
									+ (Misc.optimizeText(c.playerName))
									+ ": "
									+ Misc.optimizeText(playerCommand
											.substring(5)) + "");

						} else if (c.playerRights == 5) {
							all.sendMessage("["
									+ userTitle
									+ "]"
									+ " @cr3@ "
									+ (Misc.optimizeText(c.playerName))
									+ ": "
									+ Misc.optimizeText(playerCommand
											.substring(5)) + "");

						} else if (c.playerRights == 6) {
							all.sendMessage("["
									+ userTitle
									+ "]"
									+ " @cr4@ "
									+ (Misc.optimizeText(c.playerName))
									+ ": "
									+ Misc.optimizeText(playerCommand
											.substring(5)) + "");

						} else {
							all.sendMessage("["
									+ userTitle
									+ "]"
									+ " "
									+ (Misc.optimizeText(c.playerName))
									+ ": "
									+ Misc.optimizeText(playerCommand
											.substring(5)) + "");
						}
					}
				}
				return;
			}
		}

		/**
		 * Loads Moderator Commands here
		 */

		if (canMod(c)) {
			switch (command[0]) {
			
			case "jail":
				try {
					String[] args = playerCommand.split(" ", 2);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						Player o = PlayerHandler.players[i];
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(args[1])) {
								o.getPA().movePlayer(2743, 5359, 0);
								o.isJailed = true;
								o.sendMessage("You are jailed please appeal at the forums.");
								o.sendMessage(""+Config.FORUM_URL);
								c.sendMessage("You have jailed: "+o.playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player must be online.");
				}
				return;
				
			case "unjail":
				try {
					String[] args = playerCommand.split(" ", 2);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						Player o = PlayerHandler.players[i];
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(args[1])) {
								o.isJailed = false;
								o.getPA().movePlayer(2741, 5363, 0);
								o.sendMessage("You are unjailed dont make the same mistake again!");
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player must be online.");
				}
				return;

			case "checkinv":
				try {
					String[] args = playerCommand.split(" ", 2);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						Player o = PlayerHandler.players[i];
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(args[1])) {
								c.getPA().otherInv(c, o);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				return;

			case "checkbank":
				try {
					String[] args = playerCommand.split(" ", 2);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						Player o = PlayerHandler.players[i];
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(args[1])) {
								c.getPA().otherBank(c, o);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				return;

			case "xteleto":
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(name)) {
							c.getPA().movePlayer(
									PlayerHandler.players[i].getX(),
									PlayerHandler.players[i].getY(),
									PlayerHandler.players[i].heightLevel);
						}
					}
				}
				return;

			case "xteletome":
				try {
					String playerToTele = playerCommand.substring(10);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].properName
									.equalsIgnoreCase(playerToTele)) {
								Player c2 = PlayerHandler.players[i];
								if (c.inWild() && (c.playerRights != 3)) {
									c.sendMessage("You cannot move players when you are in the Wilderness.");
									return;
								}
								if (c2.inWild() && (c.playerRights != 3)) {
									c.sendMessage("You cannot move players when they are in the Wilderness.");
									return;
								}
								c2.sendMessage("You have been teleported to "
										+ c.properName);
								c2.getPA().movePlayer(c.getX(), c.getY(),
										c.heightLevel);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				return;

			case "mute":
				try {
					String playerToBan = playerCommand.substring(5);
					Connection.addNameToMuteList(playerToBan);
					for (int i = 0; i < PlayerHandler.players.length; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)
									&& PlayerHandler.players[i].playerRights != 3) {
								Player c2 = PlayerHandler.players[i];
								c2.sendMessage("You have been muted. Please appeal on our forums at:");
								c2.sendMessage(Config.FORUM_URL);
								c.sendMessage("You have muted: "
										+ c2.playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				return;

			case "unmute":
				try {
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
					Connection.removeNameFromMuteList(playerToBan);
					c.sendMessage("Player has been Unmuted");
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				return;

			case "daymute":
				try {
					String[] args = playerCommand.split(" ");
					if (args.length < 1) {
						c.sendMessage("Currect usage: ::daymute playername");
						return;
					}
					String playerToMute = args[1];
					int muteTimer = 86400 * 1000;
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToMute)) {
								Player c2 = PlayerHandler.players[i];
								c2.sendMessage("You have been muted by: "
										+ c.playerName + " for 1 day");
								c2.muteEnd = System.currentTimeMillis()
										+ muteTimer;
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				return;

			case "threedaymute":
				try {
					String[] args = playerCommand.split(" ");
					if (args.length < 1) {
						c.sendMessage("Currect usage: ::threedaymute playername");
						return;
					}
					String playerToMute = args[1];
					int muteTimer = 259200 * 1000;
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToMute)) {
								Player c2 = PlayerHandler.players[i];
								c2.sendMessage("You have been muted by: "
										+ c.playerName + " for 3 day");
								c2.muteEnd = System.currentTimeMillis()
										+ muteTimer;
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				return;

			case "weekmute":
				try {
					String[] args = playerCommand.split(" ");
					if (args.length < 1) {
						c.sendMessage("Currect usage: ::weekmute playername");
						return;
					}
					String playerToMute = args[1];
					int muteTimer = 604800 * 1000;
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToMute)) {
								Player c2 = PlayerHandler.players[i];
								c2.sendMessage("You have been muted by: "
										+ c.playerName + " for 7 day");
								c2.muteEnd = System.currentTimeMillis()
										+ muteTimer;
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				return;

			case "ipmute":
				try {
					String playerToBan = playerCommand.substring(7);
					for (int i = 0; i < PlayerHandler.players.length; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)
									&& PlayerHandler.players[i].playerRights != 3) {
								Connection
										.addIpToMuteList(PlayerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP Muted the user: "
										+ PlayerHandler.players[i].playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				return;

			case "unipmute":
				try {
					String playerToBan = playerCommand.substring(9);
					for (int i = 0; i < PlayerHandler.players.length; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Connection
										.unIPMuteUser(PlayerHandler.players[i].connectedFrom);
								c.sendMessage("You have Un Ip-Muted the user: "
										+ PlayerHandler.players[i].playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				return;

			case "dayban":
				try {
					String[] args = playerCommand.split(" ");
					if (args.length < 1) {
						c.sendMessage("Correct usage: ::dayban playername");
						return;
					}
					String playerToBan = args[1];
					int secondsToBan = 86400 * 1000;
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Player c2 = PlayerHandler.players[i];
								c2.banStart = System.currentTimeMillis();
								c2.banEnd = System.currentTimeMillis()
										+ secondsToBan;
								c2.disconnected = true;
								Connection.addNameToBanList(playerToBan);
								Connection.addNameToFile(playerToBan);
								break;
							}
						}
					}
					c.sendMessage("You banned the player: " + playerToBan
							+ " for 1 day");
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				return;
			}
		}

		/**
		 * Loads Administrator Commands here
		 */

		if (canAdmin(c)) {
			switch (command[0]) {

			case "master":
				for (int i = 0; i < 24; i++) {
					c.playerLevel[i] = 99;
					c.playerXP[i] = c.getPA().getXPForLevel(100);
					c.getPA().refreshSkill(i);
					c.getPA().requestUpdates();
				}
				return;
			
			case "kick":
				try {
					String[] args = playerCommand.split(" ", 2);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						Player o = PlayerHandler.players[i];
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(args[1])) {
								o.logout();
								c.sendMessage("Kicked player: "+o.playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player must be online.");
				}
				return;
			
			case "ban":
				try {
					String playerToBan = playerCommand.substring(4);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for (int i = 0; i < PlayerHandler.players.length; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)
									&& PlayerHandler.players[i].playerRights != 3) {
								PlayerHandler.players[i].disconnected = true;
							}
						}
					}
				} catch (Exception ignored) {
				}
				return;

			case "masterroshiftw":
				for (int i = 0; i < 6; i++) {
					c.playerLevel[i] = 99;
					c.playerXP[i] = c.getPA().getXPForLevel(100);
					c.getPA().refreshSkill(i);
					c.getPA().requestUpdates();
				}
				return;

			case "unban":
				try {
					String playerToBan = playerCommand.substring(6);
					Connection.removeNameFromBanList(playerToBan);
					c.sendMessage(playerToBan + " has been unbanned.");
				} catch (Exception ignored) {
				}
				return;

			case "threedayban":
				try {
					String[] args = playerCommand.split(" ");
					if (args.length < 1) {
						c.sendMessage("Correct usage: ::threedayban playername");
						return;
					}
					String playerToBan = args[1];
					int secondsToBan = 259200 * 1000;
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Player c2 = PlayerHandler.players[i];
								c2.banStart = System.currentTimeMillis();
								c2.banEnd = System.currentTimeMillis()
										+ secondsToBan;
								c2.disconnected = true;
								Connection.addNameToBanList(playerToBan);
								Connection.addNameToFile(playerToBan);
								break;
							}
						}
					}
					c.sendMessage("You banned the player: " + playerToBan
							+ " for 3 days");
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				return;

			case "tele4admin":
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),
							Integer.parseInt(arg[2]), Integer.parseInt(arg[3]));
				else if (arg.length == 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),
							Integer.parseInt(arg[2]), c.heightLevel);
				return;

			case "weekban":
				try {
					String[] args = playerCommand.split(" ");
					if (args.length < 1) {
						c.sendMessage("Correct usage: ::weekban playername");
						return;
					}
					String playerToBan = args[1];
					int secondsToBan = 604800 * 1000;
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Player c2 = PlayerHandler.players[i];
								c2.banStart = System.currentTimeMillis();
								c2.banEnd = System.currentTimeMillis()
										+ secondsToBan;
								c2.disconnected = true;
								Connection.addNameToBanList(playerToBan);
								Connection.addNameToFile(playerToBan);
								break;
							}
						}
					}
					c.sendMessage("You banned the player: " + playerToBan
							+ " for 7 days");
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				return;
			}
		}

		/**
		 * Loads Owner Commands here
		 */

		if (canOwner(c)) {
			switch (command[0]) {
			
			case "rivo":
				WeaponGame.giveWeapon(c, 14484);
				return;
			
			case "spec":
				c.specAmount += 100;
				return;
			
			case "deflect":
				String[] argsm = playerCommand.split(" ", 2);
				int damage = Integer.parseInt(argsm[1]);
				c.handleDeflect(damage);
				return;
			
			case "demote":
				try {
					String[] args = playerCommand.split(" ", 2);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						Player o = PlayerHandler.players[i];
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(args[1])) {
								o.playerRights = 0;
								c.sendMessage("Deranked player: "+o.playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player must be online.");
				}
				return;
			
			case "setextremedonator":
				try {
					String[] args = playerCommand.split(" ", 2);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						Player o = PlayerHandler.players[i];
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(args[1])) {
								o.playerRights = 6;
								c.sendMessage("Ranked player: "+o.playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player must be online.");
				}
				return;
			
			case "setdonator":
				try {
					String[] args = playerCommand.split(" ", 2);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						Player o = PlayerHandler.players[i];
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(args[1])) {
								o.playerRights = 5;
								c.sendMessage("Ranked player: "+o.playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player must be online.");
				}
				return;
			
			case "setmod":
				try {
					String[] args = playerCommand.split(" ", 2);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						Player o = PlayerHandler.players[i];
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(args[1])) {
								o.playerRights = 1;
								c.sendMessage("Ranked player: "+o.playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player must be online.");
				}
				return;
				
			case "setadmin":
				try {
					String[] args = playerCommand.split(" ", 2);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						Player o = PlayerHandler.players[i];
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(args[1])) {
								o.playerRights = 2;
								c.sendMessage("Ranked player: "+o.playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player must be online.");
				}
				return;
			
			case "ipban":
				try {
					String playerToBan = playerCommand.substring(6);
					for (int i = 0; i < PlayerHandler.players.length; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)
									&& PlayerHandler.players[i].playerRights != 3) {
								Connection
										.addIpToBanList(PlayerHandler.players[i].connectedFrom);
								Connection
										.addIpToFile(PlayerHandler.players[i].connectedFrom);
								if (c.playerRights == 3) {
									c.sendMessage("@red@["
											+ PlayerHandler.players[i].playerName
											+ "] has been IP Banned with the host: "
											+ PlayerHandler.players[i].connectedFrom);
								} else {
									c.sendMessage("@red@["
											+ PlayerHandler.players[i].playerName
											+ "] has been IP Banned.");
								}
								PlayerHandler.players[i].disconnected = true;
							}
						}
					}
				} catch (Exception ignored) {
				}
				return;

			case "testing":
				c.getPA().showInterface(23000);

				c.getPA().sendFrame126("Testing", 23003);

				c.getPA().sendFrame126("Testing", 23004);
				c.getPA().sendFrame126("Testing", 23005);
				c.getPA().sendFrame126("Testing", 23006);
				c.getPA().sendFrame126("Testing", 23007);

				return;

			case "unipban":
				try {
					String UNIP = playerCommand.substring(8);
					Connection.removeIpFromBanList(UNIP);
					c.sendMessage("You have now unipbanned" + UNIP);
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				return;

			case "master":
				for (int i = 0; i < 24; i++) {
					c.playerLevel[i] = 99;
					c.playerXP[i] = c.getPA().getXPForLevel(100);
					c.getPA().refreshSkill(i);
					c.getPA().requestUpdates();
				}
				return;

			case "item":
				try {
					final String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						final int newItemID = Integer.parseInt(args[1]);
						final int newItemAmount = Integer.parseInt(args[2]);
						if (newItemID <= 21000 && newItemID >= 0) {
							c.getItems().addItem(newItemID, newItemAmount);
							System.out.println("Spawned: " + newItemID
									+ " by: " + c.playerName);
						} else {
							c.sendMessage("No such item.");
						}
					} else if (args.length == 2) {
						final int newItemID = Integer.parseInt(args[1]);
						if (newItemID <= 25000 && newItemID >= 0) {
							c.getItems().addItem(newItemID, 1);
							System.out.println("Spawned: " + newItemID
									+ " by: " + c.playerName);
						} else {
							c.sendMessage("No such item.");
						}
					} else {
						c.sendMessage("You need atleast one ID to use this command.");
					}

				} catch (final Exception e) {
				}
				return;

			case "getid":
				final String a[] = playerCommand.split(" ");
				String name = "";
				int results = 0;
				for (int i = 1; i < a.length; i++) {
					name = name + a[i] + " ";
				}
				name = name.substring(0, name.length() - 1);
				c.sendMessage("Searching: " + name);
				for (final ItemList element : Server.itemHandler.ItemList) {
					if (element != null) {
						if (element.itemName.replace("_", " ").toLowerCase()
								.contains(name.toLowerCase())) {
							c.sendMessage("@or3@"
									+ element.itemName.replace("_", " ")
									+ " - " + element.itemId);
							results++;
						}
					}
				}
				c.sendMessage(results + " results found...");
				return;

			case "interface":
				String[] argl = playerCommand.split(" ");
				int id = Integer.parseInt(argl[1]);
				c.getPA().showInterface(id);
				return;

			case "mypos":
				c.sendMessage("X: " + c.absX + " Y: " + c.absY + " H: "
						+ c.heightLevel);
				return;

			case "anim":
				String[] args = playerCommand.split(" ");
				int anim = Integer.parseInt(args[1]);
				//c.setAnimation(Animation.create(anim));
				c.startAnimation(anim);
				return;

			case "object":
				String[] ars = playerCommand.split(" ");
				c.getPA().object(Integer.parseInt(ars[1]), c.absX, c.absY, 0,
						10);
				return;

			case "tele":
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),
							Integer.parseInt(arg[2]), Integer.parseInt(arg[3]));
				else if (arg.length == 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),
							Integer.parseInt(arg[2]), c.heightLevel);
				return;

			case "gfx":
				final String[] rgs = playerCommand.split(" ");
				final int gfx = Integer.parseInt(rgs[1]);
				c.gfx0(gfx);
				return;

			case "update":
				String[] ers = playerCommand.split(" ");
				int b = Integer.parseInt(ers[1]);
				PlayerHandler.updateSeconds = b;
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
				Server.UpdateServer = true;
				return;

			case "alltome":
				for (final Player player : PlayerHandler.players) {
					if (player != null) {
						final Player c2 = player;
						if (c2.inDung()) {
							return;
						}
						c2.teleportToX = c.absX;
						c2.teleportToY = c.absY;
						c2.sendMessage("Everyone has been teleported to "
								+ c.playerName + ".");
					}
				}
				return;

			case "npc":
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(4));
					if (newNPC > 0) {
						NPCHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0,
								120, 7, 70, 70, false, false);
						c.sendMessage("You spawn a Npc.");
					} else {
						c.sendMessage("No such NPC.");
					}
				} catch (Exception e) {

				}
				return;

			case "spawn":
				try {
					final String[] al = playerCommand.split(" ");
					final int spawnedNPC = Integer.parseInt(al[1]);
					final FileWriter fstream = new FileWriter(
							"data/cfg/spawns.cfg", true);
					final BufferedWriter out = new BufferedWriter(fstream);
					out.newLine();
					out.write("spawn = ");
					out.write(spawnedNPC + "\t");
					out.write(c.absX + "\t");
					out.write(c.absY + "\t");
					out.write(c.heightLevel + "\t");
					out.write("1\t");
					final String npcName = NPCHandler
							.getNpcListName(spawnedNPC).replaceAll("_", " ");
					out.write("0\t0\t0\t" + npcName);
					out.close();
					c.sendMessage("Saved npc " + spawnedNPC + " (" + npcName
							+ ") at x:" + c.absX + " y: " + c.absY + " h: "
							+ c.heightLevel + ".");
					NPCHandler.spawnNpc(c, spawnedNPC, c.absX, c.absY,
							c.heightLevel, 0, 0, 0, 0, 0, false, false);
				} catch (final IOException e) {
					c.sendMessage("Failed to write to file.");
				}
				return;
			}
		}

		/**
		 * Loads Donator Commands here
		 */

		if (canDonator(c)) {
			switch (command[0]) {
			case "donatorzone":
				c.getPA().spellTeleport(222, 222, 0);
				return;
			}
		}

		/**
		 * Loads Extreme Donator Commands here
		 */

		if (canExtremeDonator(c)) {
			switch (command[0]) {
			case "donatorzone":
				c.getPA().spellTeleport(222, 222, 0);
				return;
			}
		}

		/**
		 * Default message if no command is found.
		 */

		c.sendMessage("The command " + playerCommand + " doesn't exist.");

	}

}