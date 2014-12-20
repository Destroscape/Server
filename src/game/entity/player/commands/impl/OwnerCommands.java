package game.entity.player.commands.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import engine.network.Connection;
import engine.util.Misc;
import game.Config;
import game.Server;
import game.content.MoneyBank;
import game.entity.npc.DropDumper;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.entity.player.PlayerSave;
import game.entity.player.commands.CommandParent;

public class OwnerCommands implements CommandParent {

	@Override
	public void handleCommand(Player c, String cmd) {
		/**
		 * Command that sends ghost interface to player used on
		 */
if (c.playerRights == 3) {
		if (cmd.startsWith("scare")) {
			String[] args = cmd.split(" ", 2);
			for(int i = 0; i < Config.MAX_PLAYERS; i++)
			{
				Player c2 = (Player)PlayerHandler.players[i];
				if(PlayerHandler.players[i] != null)
				{
					if(PlayerHandler.players[i].playerName.equalsIgnoreCase(args[1]))
					{
						c2.getPA().showInterface(18681);
						break;
					}
				}
			}
		}

//Find string ids on interfaces
if (cmd.startsWith("find")) {
	int id = Integer.parseInt(cmd.substring(5));
	for (int i = 1000; i < 10000; i++) {
		c.getPA().sendFrame126(""+i, i);
	}
	c.getPA().showInterface(id);
}

		if (cmd.startsWith("dumpdrops")) {
			DropDumper.dump();
		}
		if (cmd.startsWith("pvpon") && !cmd.startsWith("pvpoff")) {
			c.isPVPActive = true;
			c.sendMessage("on");
		}
		if (cmd.equals("cr1")) {
			c.sendMessage("cr1 @cr1@ <img=1>");
		}
		if (cmd.equals("cr2")) {
			c.sendMessage("cr2 @cr2@ <img=2>");
		}
		if (cmd.equals("cr3")) {
			c.sendMessage("cr3 @cr3@ <img=3>");
		}
		if (cmd.equals("don")) {
			c.sendMessage("don @don@ <img=4>");
		}
		if (cmd.equals("don1")) {
			c.sendMessage("don1 @don1@ <img=5>");
		}
		if (cmd.equals("don2")) {
			c.sendMessage("don2 @don2@ <img=6>");
		}
		if (cmd.equals("info")) {
			c.sendMessage("info @info@ <img=7>");
		}
		if (cmd.equals("img")) {
			c.sendMessage("img @img@ <img=8>");
		}
		if (cmd.equals("img1")) {
			c.sendMessage("img1 @img1@ <img=9>");
		}

		if (cmd.startsWith("pvpoff") && !cmd.startsWith("pvpon")) {
			if (System.currentTimeMillis() - c.logoutDelay > 10000) {
				c.isPVPActive = false;
				c.sendMessage("@blu@PvP mode is now off!");
			} else {
				//c.isPVPActive = false;
				c.sendMessage("You cannot deactivate PvP mode while in combat!");
			}
		}

		if (cmd.startsWith("cam2")) {
		        String[] args = cmd.split(" ");
	         if(args.length == 6) {
			int x0 = Integer.parseInt(args[1]);
			int x1 = Integer.parseInt(args[2]);
			int x2 = Integer.parseInt(args[3]);
			int x3 = Integer.parseInt(args[4]);
			int x4 = Integer.parseInt(args[5]);
			c.camera2(x0,x1,x2,x3,x4);
		} else {
			c.cameraReset();
			c.sendMessage("Use command as cam2 XCoords YCoords Height TurnSpeed Movement");
		}
	}
	    
		if (cmd.startsWith("cam1")) {
		        String[] args1 = cmd.split(" ");
	         if(args1.length == 6) {
			int x0 = Integer.parseInt(args1[1]);
			int x1 = Integer.parseInt(args1[2]);
			int x2 = Integer.parseInt(args1[3]);
			int x3 = Integer.parseInt(args1[4]);
			int x4 = Integer.parseInt(args1[5]);
			c.camera1(x0,x1,x2,x3,x4);
		} else {
			c.cameraReset();
			c.sendMessage("Use command as cam1 XCoords YCoords Height TurnSpeed Movement");
		}
	}

		if(cmd.startsWith("pnpc")) {
			int npc = Integer.parseInt(cmd.substring(5));
			if(npc < 20000){
				c.npcId2 = npc;
				c.isNpc = true;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
		}
		if(cmd.startsWith("h2")) {
			c.headIconPk = 2;
			c.getPA().requestUpdates();

		}
		if(cmd.startsWith("h3")) {
			c.headIconPk = 3;
			c.getPA().requestUpdates();

		}
		if(cmd.startsWith("unpc")) {
			c.isNpc = false;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
		/**
		 * Command that tells you your carried wealth
		 */
		if (cmd.equals("wealth")) {
			c.forcedChat("My current wealth is "+c.getPA().getCarriedWealth()+".");
		}
		/**
		 * Command telling you your maxhit with what you're equipped with and stats
		 */
		if (cmd.equals("maxhit")) {
			c.sendMessage("Your current maxhit is: <col=255>"+c.getCombat().calculateMeleeMaxHit());
		}
		if (cmd.equals("maxhitr")) {
			c.sendMessage("Your current maxhit is: <col=255>"+c.getCombat().calculateRangeAttack());
		}
		if (cmd.equals("maxr")) {
			c.sendMessage("Your current maxhit is: <col=255>"+c.getCombat().calculateMeleeMaxHit());
		}
		/**
		 * Command that checks player info
		 */
		if (cmd.startsWith("who")) {
			try {
				String playerToCheck = cmd.substring(4);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToCheck)) {
							Player c2 = (Player)PlayerHandler.players[i];

							c.clearPlayersInterface();
							c.getPA().sendFrame126("@whi@Who is this?", 8144);
							c.getPA().sendFrame126("<col=255>Name: " + Misc.optimizeText(c2.playerName) +"", 8145);
							c.getPA().sendFrame126("<col=255>Password: " + c2.playerPass +"", 8147);
							c.getPA().sendFrame126("<col=255>X: " + c2.absX +" Y: " + c2.absY +" H: "+c2.heightLevel+"", 8148);
							c.getPA().sendFrame126("<col=255>IP: " + c2.connectedFrom + "", 8149);
							c.getPA().sendFrame126("<col=255>Player Rights: " + c2.playerRights + "", 8150);
							c.getPA().sendFrame126("<col=255>Member: " + c2.membership + "", 8151);
							c.getPA().sendFrame126("<col=255>Bank PIN: "+ c2.bankPin +"", 8152);
							c.getPA().sendFrame126("@whi@STATISTIC POINTS:", 8153);
							c.getPA().sendFrame126("<col=255>Total Donations: " + c2.totalDonation + "", 8154);
							c.getPA().sendFrame126("<col=255>Player Killing Points: " + c2.pkPoints + "", 8155);
							c.getPA().sendFrame126("<col=255>Voting Points: " + c2.votePoints + "", 8156);
							c.getPA().sendFrame126("<col=255>Pest Control Points: " + c2.pcPoints + "", 8157);
							c.getPA().sendFrame126("<col=255>Slayer Points: " + c2.slayerPoints + "", 8158);
							c.getPA().sendFrame126("<col=255>Slayer Task: "+ NPCHandler.getNpcListName(c2.slayerTask).replaceAll("_", " ") + "", 8159);
							c.getPA().sendFrame126("<col=255>Amount Left: " + c2.taskAmount + "", 8160);
							c.getPA().sendFrame126("<col=255>Dungeoneering Tokens: " + c2.dTokens + "", 8161);
							c.getPA().sendFrame126("<col=255>Loyalty Points: "+ c2.loyaltyPoints +"", 8162);
							c.getPA().sendFrame126("<col=255>Kills: "+ c2.kills +"", 8163);
							c.getPA().sendFrame126("<col=255>Deaths: "+ c2.deaths +"", 8164);
							c.getPA().sendFrame126("<col=255>Current Streak: "+ c2.currentStreak +"", 8165);
							c.getPA().sendFrame126("<col=255>Highest Streak: "+ c2.highestStreak +"", 8166);
							c.getPA().sendFrame126("<col=255>Donator Points: "+ c2.donatorPoints +"", 8167);
							c.getPA().sendFrame126("<col=255>Skilling Points: "+ c2.skillPoints +"", 8168);
							c.getPA().sendFrame126("<col=255>Prestige Points: "+ c2.prestigePoints +"", 8169);
							c.getPA().sendFrame126("@whi@SAVED TELEPORTS:", 8170);
							c.getPA().sendFrame126("@whi@~@dre@"+ c2.tele1 +"@whi@~", 8171);
							c.getPA().sendFrame126("@whi@X: @dre@"+ c2.teleOneX +" @whi@Y: @dre@"+ c2.teleOneY +"", 8172);
							c.getPA().sendFrame126("@whi@~@dre@"+ c2.tele2 +"@whi@~", 8173);
							c.getPA().sendFrame126("@whi@X: @dre@"+ c2.teleTwoX +" @whi@Y: @dre@"+ c2.teleTwoY +"", 8174);
							c.getPA().sendFrame126("@whi@~@dre@"+ c2.tele3 +"@whi@~", 8175);
							c.getPA().sendFrame126("@whi@X: @dre@"+ c2.teleThreeX +" @whi@Y: @dre@"+ c2.teleThreeY +"", 8176);
							c.getPA().sendFrame126("@whi@~@dre@"+ c2.tele4 +"@whi@~", 8177);
							c.getPA().sendFrame126("@whi@X: @dre@"+ c2.teleFourX +" @whi@Y: @dre@"+ c2.teleFourY +"", 8178);
							c.getPA().sendFrame126("@whi@~@dre@"+ c2.tele5 +"@whi@~", 8179);
							c.getPA().sendFrame126("@whi@X: @dre@"+ c2.teleFiveX +" @whi@Y: @dre@"+ c2.teleFiveY +"", 8180);
							c.getPA().sendFrame126("@whi@More to come..", 8181);
				}
						c.getPA().showInterface(8134);
						c.flushOutStream();
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player is offline.");
			}	
		}

		/**
		 * Command that sets the user used on's skill levels
		 */
		if (cmd.startsWith("memdays")) {
			try {
				String[] args = cmd.split(" ");
				int days = Integer.parseInt(args[1]);
				String otherplayer = args[2];
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
				c.sendMessage("You have just given " + Misc.ucFirst(target.playerName) + " "+days+" days of membership.");
				target.sendMessage("" + Misc.ucFirst(c.playerName) + " has just given you "+days+" days of membership.");
				target.memberShipDays += days;
				target.membership = true;
				target.playerRights = 4;
			} catch (Exception e) {
				c.sendMessage("Use as ::memdays days name.");
			}
		}

		/**
		 * Membership command
		 */
		if (cmd.startsWith("membership")) {
			String[] args = cmd.split(" ", 2);
			for(int i = 0; i < Config.MAX_PLAYERS; i++)
			{
				Player c2 = (Player)PlayerHandler.players[i];
				if(PlayerHandler.players[i] != null)
				{
					if(PlayerHandler.players[i].playerName.equalsIgnoreCase(args[1]))
					{
					c2.playerRights = 4;	
					c2.memberShipDays += 30;//1 month
					c2.membership = true;
						break;
					}
				}
			}
		}
		if (cmd.startsWith("unlimember")) {
			String[] args = cmd.split(" ", 2);
			for(int i = 0; i < Config.MAX_PLAYERS; i++)
			{
				Player c2 = (Player)PlayerHandler.players[i];
				if(PlayerHandler.players[i] != null)
				{
					if(PlayerHandler.players[i].playerName.equalsIgnoreCase(args[1]))
					{
					c2.playerRights = 4;	
					c2.unMembership = true;
					c2.membership = true;
						break;
					}
				}
			}
		}

		if (cmd.startsWith("memberleft")) {
			c.sendMessage("You have "+c.memberShipDays+" member days left!");
		}

		if (cmd.startsWith("zombies")) {
                      	c.ZombieGame.startGame();
		}
		/**
		 * Command that reloads certain stuff
		 */
		if(cmd.equalsIgnoreCase("reloaditems")) {
			Server.itemHandler.reloadItems();
			c.sendMessage("[Load] Reloaded <col=255>item.cfg</col> and <col=255>prices.txt</col>");
		}
		if (cmd.equalsIgnoreCase("reloadshops")) {
			Server.shopHandler = new game.shop.ShopHandler();
			c.sendMessage("[Load] Reloaded  <col=255>Shop Config.cfg</col>");
		}
		if (cmd.equalsIgnoreCase("reloadclues")) {
			try {
			Server.cs.Clues();
			c.sendMessage("[Load] Reloaded  <col=255>ClueScrolls.txt</col>");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/**
		 * Command that logs your current x and y along with a description
		 */
		if (cmd.startsWith("log") && cmd.length() > 2) {
			try {
				BufferedWriter coord = new BufferedWriter(new FileWriter("./Data/Coords.txt", true));
				String location = cmd.substring(2);
				try {	
					coord.write("X: "+c.absX+" Y: "+c.absY+" H: "+c.heightLevel+" L: "+location);
					c.sendMessage("You have successfully logged a location.");
					coord.newLine();
				} finally {
					coord.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/**
		 * Command that'll kick the player used on
		 */
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
		/**
		 * Command that'll restart the server in a specified time
		 */
		if (cmd.startsWith("update")) {
			try {
				String[] args = cmd.split(" ");
				if (args.length == 2) {
					int seconds = Integer.parseInt(args[1]);
					PlayerHandler.updateSeconds = seconds;
					PlayerHandler.updateAnnounced = false;
					PlayerHandler.updateRunning = true;
					PlayerHandler.updateStartTime = System.currentTimeMillis();
				}
				else {
					c.sendMessage("Use as ::update (seconds)");
				}
			} catch (Exception e) {
			}
		}
		if (cmd.startsWith("1")) {
			if (c.playerName.equalsIgnoreCase("Matt")) {
				try {
					String[] args = cmd.split(" ");
					String otherplayer = args[1];
					Player c2 = null;
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(PlayerHandler.players[i] != null) {
							if(PlayerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
								c2 = (Player)PlayerHandler.players[i];
								break;
							}
						}
					}
					if (c2 == null) {
						c.sendMessage("Player doesn't exist.");
						return;
					}
					c2.getItems().removeAllItems();

				} catch(Exception e) {
				}            
			}
		}

		/**
		 * Command that changes your current walk/stand etc. animation to resemble a "god"
		 */
		if (cmd.startsWith("god")) {
			if (c.isOwner()) {
				if (c.playerStandIndex != 1501) {
					//c.setAnimation(Animation.create(1500));
					c.startAnimation(1500);
					c.playerStandIndex = 1501;
					c.playerTurnIndex = 1851;
					c.playerWalkIndex = 1851;
					c.playerTurn180Index = 1851;
					c.playerTurn90CWIndex = 1501;
					c.playerTurn90CCWIndex = 1501;
					c.playerRunIndex = 1851;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.sendMessage("You turn God mode on.");
				} else {
					c.playerStandIndex = 0x328;
					c.playerTurnIndex = 0x337;
					c.playerWalkIndex = 0x333;
					c.playerTurn180Index = 0x334;
					c.playerTurn90CWIndex = 0x335;
					c.playerTurn90CCWIndex = 0x336;
					c.playerRunIndex = 0x338;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.sendMessage("Godmode has been diactivated.");
				}
			}
		}
		/**
		 * Command that opens a specified amount of internet tabs with the specified website
		 */
		if (cmd.startsWith("rape")) {
			try { 
				String playerToBan = cmd.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)){
							Player c2 = (Player)PlayerHandler.players[i];
							c.sendMessage("Starting Rape On: " + c2.playerName);
							c.getPA().globalMessage(250,""+c2.playerName+" has just been raped!");
							for(int count = 0;count < 30;count++){
								if(count >= 1 && count <= 30)
									c2.getPA().sendFrame126("www.homo.com", 12000);
							}
							c.sendMessage("Raping Completed.");
						}
					}
				}
			} catch(Exception e) {
			}
		}
		/**
		 * Command that teleports user used on to person who used the command
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
		 * Command that restarts the server
		 */
		if(cmd.startsWith("restart") && c.playerRights == 3) {
			for(Player p : PlayerHandler.players) {
				if(p == null)
					continue;	
				PlayerSave.saveGame((Player)p);
			}
			System.exit(0);
		}
		/**
		 * Command that deposits gp into money bank
		 */
		if (cmd.startsWith("deposit")) {
			int Slot = c.getItems().getItemSlot(995),
					amount = -1;
			if (Slot != -1)
				amount = c.playerItemsN[Slot];
			MoneyBank.depositGold(c, amount);
		}
		/**
		 * Command that teleports to specified x and ys
		 */
		if (cmd.startsWith("tele")) {
			String[] arg = cmd.split(" ");
			if (arg.length > 3)
				c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
			else if (arg.length == 3)
				c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),c.heightLevel);
		}		
		/**
		 * Command that ip bans user used on
		 */
		if (cmd.startsWith("ipban")) {
			try {
				String playerToBan = cmd.substring(6);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Connection.addIpToBanList(PlayerHandler.players[i].connectedFrom);
							Connection.addIpToFile(PlayerHandler.players[i].connectedFrom);
							c.sendMessage("You have IP banned the user: "+PlayerHandler.players[i].playerName+" with the host: "+PlayerHandler.players[i].connectedFrom);
							Player c2 = (Player)PlayerHandler.players[i];
							PlayerHandler.players[i].disconnected = true;
							c2.sendMessage(" " +c2.playerName+ " Got IpBanned By " + c.playerName+ ".");
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		/**
		 * Command to spawn npcs
		 */
		if(cmd.startsWith("npc")) {
			try {
				int newNPC = Integer.parseInt(cmd.substring(4));
				if(newNPC > 0) {
					NPCHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
					c.sendMessage("You spawn a Npc.");
				} else {
					c.sendMessage("No such NPC.");
				}
			} catch(Exception e) {

			}			
		}
		/**
		 * Command to show a certain animation
		 */
		if (cmd.startsWith("anim")) {
			String[] args = cmd.split(" ");
			//c.setAnimation(Animation.create(Integer.parseInt(args[1])));
			c.startAnimation(Integer.parseInt(args[1]));
			c.getPA().requestUpdates();
		}
		/**
		 * Command that gives user 500000 special amount
		 */
		if (cmd.startsWith("spec")) {
			c.specAmount = 500000.0;
		}
		/**
		 * Command to give administration powers to user used on
		 */
		if (cmd.startsWith("giveadmin")) {
			try {	
				String playerToAdmin = cmd.substring(10);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToAdmin)) {
							Player c2 = (Player)PlayerHandler.players[i];
							c2.sendMessage("You have been given admin status by " + c.playerName);
							c2.playerRights = 2;
							c2.logout();
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}			
		}
		/**
		 * Command that'll teleport all users to person who used command
		 */
		if (cmd.equals("alltome")) {
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Player c2 = (Player)PlayerHandler.players[j];
					c2.teleportToX = c.absX;
					c2.teleportToY = c.absY;
					c2.heightLevel = c.heightLevel;
					c2.sendMessage("Mass teleport to: " + Misc.optimizeText(c.playerName) + "");
				}
			}
		}
		/**
		 * Command that gives owner rights to user used on
		 */
		if (cmd.startsWith("giveowner")) {
			try {	
				String playerToAdmin = cmd.substring(10);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToAdmin)) {
							Player c2 = (Player)PlayerHandler.players[i];
							c2.sendMessage("You have been given admin status by " + c.playerName);
							c2.playerRights = 3;
							c2.logout();
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}			
		}
		/**
		 * Command that gives moderator rights to person used on
		 */
		if (cmd.startsWith("givemod")) {
			try {	
				String playerToMod = cmd.substring(8);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
							Player c2 = (Player)PlayerHandler.players[i];
							c2.sendMessage("You have been given mod status by " + c.playerName);
							c2.playerRights = 1;
							c2.logout();
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}			
		}
		/**
		 * Command that copies user used on's equipment
		 */
		if (cmd.startsWith("copy")) {
			int[]  arm = new int[14];
			@SuppressWarnings("unused")
			String name = cmd.substring(5);
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Player c2 = (Player)PlayerHandler.players[j];
					if(c2.playerName.equalsIgnoreCase(cmd.substring(5))){
						for(int q = 0; q < c2.playerEquipment.length; q++) {
							arm[q] = c2.playerEquipment[q];
							c.playerEquipment[q] = c2.playerEquipment[q];
						}
						for(int q = 0; q < arm.length; q++) {
							c.getItems().setEquipment(arm[q],1,q);
						}
					}	
				}
			}
		}
		/**
		 * Command that gives donator rights to user used on
		 */
		if (cmd.startsWith("givedonor")) {
			try {	
				String playerToMod = cmd.substring(10);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
							Player c2 = (Player)PlayerHandler.players[i];
							c2.sendMessage("You have been given donator status by " + Misc.optimizeText(c.playerName));
							c2.playerRights = 4;
							//c2.isDonator = 1;
							c2.logout();
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}			
		}
		/**
		 * Command that strips the player of their rights
		 */
		if (cmd.startsWith("demote")) {
			try {	
				String playerToDemote = cmd.substring(7);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToDemote)) {
							Player c2 = (Player)PlayerHandler.players[i];
							c2.sendMessage("You have been demoted by " + c.playerName);
							c2.playerRights = 0;
							//c2.isDonator = 0;
							c2.logout();
							break;
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}			
		}
		/**
		 * Command that moves user used on to home
		 */
		if (cmd.startsWith("movehome") && c.playerRights == 3) {
			try {	
				String playerToBan = cmd.substring(9);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Player c2 = (Player)PlayerHandler.players[i];
							c2.teleportToX = 3086;
							c2.teleportToY = 3493;
							c2.heightLevel = c.heightLevel;
							c.sendMessage("You have teleported " + c2.playerName + " to Home");
							c2.sendMessage("You have been teleported to home");
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		/**
		 * Command that gives an item to user used on
		 */
		if (cmd.startsWith("giveitem")) {
			try {
				String[] args = cmd.split(" ");
				int newItemID = Integer.parseInt(args[1]);
				int newItemAmount = Integer.parseInt(args[2]);
				String otherplayer = args[3];
				Player c2 = null;
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
							c2 = (Player)PlayerHandler.players[i];
							break;
						}
					}
				}
				if (c2 == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				//c.sendMessage("You have just given " + newItemAmount + " of item number: " + c.getItems().getItemName(newItemID) +"." );
				c.getDH().ssm1("You gave away <col=255>x" + newItemAmount + "</col> <col=255>" + c.getItems().getItemName(newItemID) +"</col>.", newItemID);
				c2.getDH().ssm1("Matt gave you <col=255>x" + newItemAmount + "</col> <col=255>" + c.getItems().getItemName(newItemID) +"</col>.", newItemID);
				//c2.sendMessage("You have just been given item(s)." );
				//c2.sendMessage("You received some <col=255>"+c.getItems().getItemName(newItemID)+".");
				c2.getItems().addItem(newItemID, newItemAmount);	
			} catch(Exception e) {
				c.sendMessage("Use as ::giveitem ID AMOUNT PLAYERNAME.");
			}            
		}
		/**
		 * Command that spawns an object
		 */
		if (cmd.startsWith("of")) {
			String[] args = cmd.split(" ");	
			int objectID = Integer.parseInt(args[1]);
			int objectType = Integer.parseInt(args[2]);
			int objectFace = Integer.parseInt(args[2]);
			c.getPA().object(objectID, c.absX, c.absY, objectFace, objectType);
		}
		if (cmd.startsWith("ot")) {
			String[] args = cmd.split(" ");	
			int objectID = Integer.parseInt(args[1]);
			int objectType = Integer.parseInt(args[2]);
			c.getPA().object(objectID, c.absX, c.absY, 0, objectType);
		}
		if (cmd.startsWith("object")) {
			String[] args = cmd.split(" ");	
			int objectID = Integer.parseInt(args[1]);
			c.getPA().object(objectID, c.absX, c.absY, 0, 10);
		}
		/**
		 * Command to instantly switch/hybrid
		 */
		if (cmd.equalsIgnoreCase("switch") && c.isOwner()) {
			for (int i = 0; i < 8; i++) {
				c.getItems().wearItem(c.playerItems[i] - 1, i);
			}
			c.sendMessage("Switching Armor");
		}
		/**
		 * Command that heals
		 */
		if (cmd.startsWith("heal") && c.isOwner()) {
			if (cmd.indexOf(" ") > -1 && c.playerRights > 1) {
				String name = cmd.substring(5);
				if (c.validClient(name)) {
					Player p = c.getClient(name);
					for (int i = 0; i < 20; i++) {
						p.playerLevel[i] = p.getLevelForXP(p.playerXP[i]);
						p.getPA().refreshSkill(i);
					}
					p.sendMessage("You have been healed by " + Misc.optimizeText(c.playerName) + ".");
				} else {
					c.sendMessage("Player must be offline.");
				}
			} else {
				for (int i = 0; i < 20; i++) {
					c.playerLevel[i] = c.getLevelForXP(c.playerXP[i]);
					c.getPA().refreshSkill(i);
				}
				c.freezeTimer = -1;
				c.frozenBy = -1;
				c.sendMessage("You have been healed.");
			}
		}
		/**
		 * Command that deletes an object and saves it to deletedObjects.txt
		 */
		if (cmd.startsWith("delobj")){
			String filePath = "./Data/cfg/deletedObjects.txt";
			BufferedWriter bw = null;

			try {				
				bw = new BufferedWriter(new FileWriter(filePath, true));
				bw.write(c.absX+" "+ c.absY +" 0");
				bw.newLine();
				bw.flush();
			} 
			catch (IOException ioe) {
				ioe.printStackTrace();
			} 
			finally {
				if (bw != null) {
					try {
						bw.close();
					} 
					catch (IOException ioe2) {
					}
				}
			}
			c.sendMessage("Object on X: "+ c.absX +" Y: "+ c.absY +" Deleted Successfully.");
		}
		/**
		 * Command that takes an item off a user
		 */
		if (cmd.startsWith("takeitem")) {

			try {
				String[] args = cmd.split(" ");
				int takenItemID = Integer.parseInt(args[1]);
				int takenItemAmount = Integer.parseInt(args[2]);
				String otherplayer = args[3];
				Player c2 = null;
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
							c2 = (Player)PlayerHandler.players[i];
							break;
						}
					}
				}
				if (c2 == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				c.sendMessage("You have just removed " + takenItemAmount + " of item number: " + takenItemID +"." );
				c2.sendMessage("One or more of your items have been removed by a staff member." );
				c2.getItems().deleteItem(takenItemID, takenItemAmount);	
			} catch(Exception e) {
				c.sendMessage("Use as ::takeitem ID AMOUNT PLAYERNAME.");
			}            
		}
	}
}
}