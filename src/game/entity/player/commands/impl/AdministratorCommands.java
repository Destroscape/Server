package game.entity.player.commands.impl;

import engine.network.Connection;
import engine.util.Misc;
import game.Config;
import game.Server;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.entity.player.PlayerSave;
import game.entity.player.commands.CommandParent;

public class AdministratorCommands implements CommandParent {

	@Override
	public void handleCommand(Player c, String cmd) {
if (c.playerRights == 3 || c.playerRights == 2) {
		if (cmd.startsWith("ipban")) { 
			try {
				String playerToBan = cmd.substring(6);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Connection.addIpToBanList(PlayerHandler.players[i].connectedFrom);
							Connection.addIpToFile(PlayerHandler.players[i].connectedFrom);
							c.sendMessage("You have IP banned the user: "+PlayerHandler.players[i].playerName+" with the host: "+PlayerHandler.players[i].connectedFrom);
							PlayerHandler.players[i].disconnected = true;

						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline."); 
			}
		}

		if (cmd.startsWith("getip")) { 
			try {
				String iptoget = cmd.substring(6);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {

						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(iptoget)) {
							c.sendMessage("Ip:"+PlayerHandler.players[i].connectedFrom);
						}
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Online.");
			}
		}
		if (cmd.equalsIgnoreCase("bank")) {
			c.getPA().openUpBank(0);
		}

		if (cmd.startsWith("zteleto")) {
			String name = cmd.substring(8);
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				if (PlayerHandler.players[i] != null) {
					if (PlayerHandler.players[i].playerName.equalsIgnoreCase(name)) {
						c.getPA().movePlayer(PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), PlayerHandler.players[i].heightLevel);
					}
				}
			}			
		}


		/**
		 * Command that sets the user used on's skill levels
		 */
		if (cmd.startsWith("setlevel")) {
			try {
				String[] args = cmd.split("_");
				int skill = Integer.parseInt(args[1]);
				int level = Integer.parseInt(args[2]);
				String otherplayer = args[3];
				Player target = null;
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
							target = (Player) PlayerHandler.players[i];
							break;
						}
					}
				}
				if (target == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				c.sendMessage("You have just set one of " + Misc.ucFirst(target.playerName) + "'s skills.");
				target.sendMessage("" + Misc.ucFirst(c.playerName) + " has just set one of your skills.");
				target.playerXP[skill] = target.getPA().getXPForLevel(level) + 5;
				target.playerLevel[skill] = target.getPA().getLevelForXP(target.playerXP[skill]);
				target.getPA().refreshSkill(skill);
			} catch (Exception e) {
				c.sendMessage("Use as ::setlevel SKILLID LEVEL PLAYERNAME.");
			}
		}

		/**
		 * Command the empties your inventory
		 */
		if (cmd.startsWith("empty")) {
			c.getItems().removeAllItems();
		}
		if (cmd.startsWith("item")) {
			try {
				String[] args = cmd.split(" ");
				if (args.length == 3) {
					int newItemID = Integer.parseInt(args[1]);
					int newItemAmount = Integer.parseInt(args[2]);
					if ((newItemID <= 300000) && (newItemID >= 0)) {
						c.getItems().addItem(newItemID, newItemAmount);    
						//c.getDH().ssm1("You spawned <col=255>x" + newItemAmount + " " + c.getItems().getItemName(newItemID) +"</col>.", newItemID);            
					} else {
						c.sendMessage("No such item.");
					}
				} else {
					c.sendMessage("Use as ::item 995 200 for example 200 gp");
				}
			} catch(Exception e) {

			}
		}

		if (cmd.startsWith("kick")) {
			String playerToBan = cmd.substring(5);
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(PlayerHandler.players[i] != null) {
					if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
						Player c2 = (Player)PlayerHandler.players[i];
						c2.logout();
						break;
					} 
				}
			}			
		}

		if (cmd.equals("dropparty")) {
			for (int item = 0; item < c.playerItems.length; item++) {
				int itemId = c.playerItems[item];
				int itemAmount = c.playerItemsN[item];
				Server.itemHandler.createGroundItem(c, itemId-1, 3175-Misc.random(14), 3446+Misc.random(13), 1, c.playerId);
				c.getItems().deleteItem(itemId-1, itemAmount);
			}
		} 

		if (cmd.startsWith("master")) {
			for (int i = 0; i < 24; i++) {
				c.playerLevel[i] = 99;
				c.playerXP[i] = c.getPA().getXPForLevel(100);
				c.getPA().refreshSkill(i);
				c.getPA().requestUpdates();
			}
		} 

		if (cmd.startsWith("getid")) {
			String a[] = cmd.split(" ");
			String name = "";
			int results = 0;
			for (int i = 1; i < a.length; i++) {
				name = name + a[i] + " ";
			}
			name = name.substring(0, name.length() - 1);
			c.sendMessage("Searching: " + name);
			for (int j = 0; j < Server.itemHandler.ItemList.length; j++) {
				if (Server.itemHandler.ItemList[j] != null) {
					if (Server.itemHandler.ItemList[j].itemName.replace("_", " ").toLowerCase().contains(name.toLowerCase())) {
						c.sendMessage("<col=255>" + Server.itemHandler.ItemList[j].itemName.replace("_", " ") + " - " + Server.itemHandler.ItemList[j].itemId);
						results++;
					}
				}
			}
			c.sendMessage(results + " results found...");
		}

		if (cmd.equalsIgnoreCase("mypos")) {
			c.sendMessage("X: "+c.absX+" Y: "+c.absY+" H: "+c.heightLevel);
		}

		if (cmd.startsWith("interface")) {
			String[] args = cmd.split(" ");
			c.getPA().showInterface(Integer.parseInt(args[1]));
		}
		/**
		 * Displays gfx of specified gfx id
		 */
		if (cmd.startsWith("gfx")) {
			String[] args = cmd.split(" ");
			c.gfx0(Integer.parseInt(args[1]));
		}
		/**
		 * Teleports specified user
		 */
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
		/**
		 * Unban player
		 */
		if (cmd.startsWith("ban") && cmd.charAt(3) == ' ') {
			try {	
				String playerToBan = cmd.substring(4);
				Connection.addNameToBanList(playerToBan);
				Connection.addNameToFile(playerToBan);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							PlayerHandler.players[i].disconnected = true;
							Player c2 = (Player)PlayerHandler.players[i];
							c2.sendMessage(" " +c2.playerName+ " Got Banned By " + c.playerName+ ".");
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		/**
		 * Un-ip mute player
		 */
		if (cmd.startsWith("unipmute")) {
			try {	
				String playerToBan = cmd.substring(9);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Connection.unIPMuteUser(PlayerHandler.players[i].connectedFrom);
							c.sendMessage("You have Un Ip-Muted the user: "+PlayerHandler.players[i].playerName);
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}			
		}
		/**
		 * Unban Player
		 */
		if (cmd.startsWith("unban")) {
			try {	
				String playerToBan = cmd.substring(6);
				Connection.removeNameFromBanList(playerToBan);
				c.sendMessage(playerToBan + " has been unbanned.");
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		/**
		 * Check player inventories
		 */
		if (cmd.startsWith("checkinv") && c.playerRights == 3) {
			try {
				String[] args = cmd.split(" ", 2);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					Player o = (Player) PlayerHandler.players[i];
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
							c.getPA().otherInv(c, o);
							break;
						}
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline."); 
			}
		}
		/**
		 * Save all players
		 */
		if (cmd.startsWith("saveall")) {
			for(Player p : PlayerHandler.players) {
				if(p == null)
					continue;
				PlayerSave.saveGame((Player)p);
			}
			c.sendMessage("All players saved.");
		}
		
		if (cmd.startsWith("mute")) {
			try {
				String playerToBan = cmd.substring(5);
				Connection.addNameToMuteList(playerToBan);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToBan)) {
							Player c2 = (Player) PlayerHandler.players[i];
							c2.sendMessage("You have been muted by: "
									+ c.playerName);
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}

		if (cmd.startsWith("unmute")) {
			try {	
				String playerToBan = cmd.substring(7);
				Connection.unMuteUser(playerToBan);
				Connection.removeNameFromMuteList(playerToBan);
				c.sendMessage("The nigger has been unmuted.");
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		/**
		 * Mute player's through their ip
		 */
		if (cmd.startsWith("ipmute")) {
			try {	
				String playerToBan = cmd.substring(7);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Connection.addIpToMuteList(PlayerHandler.players[i].connectedFrom);
							c.sendMessage("You have IP Muted the user: "+PlayerHandler.players[i].playerName);
							Player c2 = (Player)PlayerHandler.players[i];
							c2.sendMessage("You have been muted by: " + c.playerName);
							c2.sendMessage(" " +c2.playerName+ " Got IpMuted By " + c.playerName+ ".");
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}	
		}
	}
	}

}