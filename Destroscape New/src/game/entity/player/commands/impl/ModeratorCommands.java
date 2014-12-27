package game.entity.player.commands.impl;

import engine.network.Connection;
import game.Config;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.entity.player.commands.CommandParent;

public class ModeratorCommands implements CommandParent {

	@Override
	public void handleCommand(Player c, String cmd) {
if (c.playerRights == 3 || c.playerRights == 2 || c.playerRights == 1) {

		if (cmd.equals("kick") && cmd.charAt(4) == ' ') {
			try {
				String playerToBan = cmd.substring(5);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							PlayerHandler.players[i].disconnected = true;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}

		if (cmd.startsWith("xteletome")) {
			try {	
				String playerToTele = cmd.substring(10);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToTele)) {
							Player c2 = (Player)PlayerHandler.players[i];
							c2.sendMessage("You have been teleported to " + c.playerName);
							c2.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel);
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}			
		}

		if(cmd.startsWith("unjail")) {
			try {
				String playerToBan = cmd.substring(7);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan) && !PlayerHandler.players[i].playerName.equalsIgnoreCase("")) {
							if(c.inPits) {
								c.sendMessage("You can't unjail during Fight Pits.");
								return;
							}
							if(c.getPA().inPitsWait()) {
								c.sendMessage("You can't unjail during Fight Pits.");
								return;
							}
							if(c.duelStatus == 5) {
								c.sendMessage("You can't unjail during a duel!");
								return;
							}
							if(System.currentTimeMillis() - c.logoutDelay < 3000) {
								c.sendMessage("You must wait a few seconds from being out of combat to unjail.");
								return;
							}
							Player c2 = (Player)PlayerHandler.players[i];
							c2.teleportToX = 3186;
							c2.teleportToY = 3440;//3186, 3440
							c2.heightLevel = 0;
							c2.isJailed = false;
							c2.sendMessage("You have been unjailed by "+c.playerName+"");
							c.sendMessage("You have unjailed "+c2.playerName+".");
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}

		if(cmd.startsWith("jail")) {
			try {
				String playerToBan = cmd.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Player c2 = (Player)PlayerHandler.players[i];
							c2.teleportToX = 3102;
							c2.teleportToY = 9516;
							c2.isJailed = true;
							c2.sendMessage("You have been jailed by "+c.playerName+"");
							c.sendMessage("Successfully Jailed "+c2.playerName+".");
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Online.");
			}
		}

		if (cmd.startsWith("checkbank")) {
			try {
				String[] args = cmd.split(" ", 2);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					Player o = (Player) PlayerHandler.players[i];
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
							c.getPA().otherBank(c, o);
							break;
						}
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline."); 
			}
		}
		
		if (cmd.startsWith("daymute")) {
			try {
				String[] args = cmd.split(" ");
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
		}
	}
	}
}