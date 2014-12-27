package server.model.players.packets;

import server.Config;
import server.Connection;
import java.text.DecimalFormat;
import server.model.players.PacketType;
import server.util.Misc;
import server.util.MadTurnipConnection;
//GTLVOTE
import org.Vote.*;
//ENDOFGTLVOTE
import server.model.players.CombatAssistant;
import server.model.items.ItemAssistant;
import server.model.players.PlayerSave;
import server.model.players.Highscores;
import server.world.PublicEvent;

import java.sql.*;
import server.model.players.Client;
import server.Server;
import server.model.players.packets.Commands;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.PlayerAssistant;
import server.Server;

import java.io.*;

/**
 * Commands
 **/
public class Commands implements PacketType 
{

    
    @Override
    public void processPacket(Client c, int packetType, int packetSize) 
    {
    String playerCommand = c.getInStream().readString();
	PublicEvent.processEntry(c, playerCommand);
if(c.playerRights >= 12) {
			if(playerCommand.startsWith("starttrivia"))
				PublicEvent.forceFirst();
}
		if(Config.SERVER_DEBUG)
		Misc.println(c.playerName+" playerCommand: "+playerCommand);
		if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
			if (c.clanId >= 0) {
				System.out.println(playerCommand);
				playerCommand = playerCommand.substring(1);
				Server.clanChat.playerMessageToClan(c.playerId, playerCommand, c.clanId);
			} else {
				if (c.clanId != -1)
					c.clanId = -1;
				c.sendMessage("You are not in a clan.");
			}
			return;
		}
    if (Config.SERVER_DEBUG)
        Misc.println(c.playerName+" playerCommand: "+playerCommand);
    
    if (c.playerRights >= 0)
        playerCommands(c, playerCommand);
    if (c.playerRights == 1 || c.playerRights == 2 || c.playerRights == 3 || c.playerRights == 4 || c.playerRights == 5 || c.playerRights == 6 || c.playerRights == 7 || c.playerRights == 8 || c.playerRights == 9 || c.playerRights == 10 || c.playerRights == 11 || c.playerRights == 12 || c.playerRights == 13 || c.playerRights == 14) 
        BronzedonatorCommands(c, playerCommand);
    if (c.playerRights == 2 || c.playerRights == 3 || c.playerRights == 4 || c.playerRights == 5 || c.playerRights == 6 || c.playerRights == 7 || c.playerRights == 8 || c.playerRights == 9 || c.playerRights == 10 || c.playerRights == 11 || c.playerRights == 12 || c.playerRights == 13 || c.playerRights == 14) 
        IrondonatorCommands(c, playerCommand);
    if (c.playerRights == 3 || c.playerRights == 4 || c.playerRights == 5 || c.playerRights == 6 || c.playerRights == 7 || c.playerRights == 8 || c.playerRights == 9 || c.playerRights == 10 || c.playerRights == 11 || c.playerRights == 12 || c.playerRights == 13 || c.playerRights == 14) 
        AdamantdonatorCommands(c, playerCommand);
    if (c.playerRights == 4 || c.playerRights == 5 || c.playerRights == 6 || c.playerRights == 7 || c.playerRights == 8 || c.playerRights == 9 || c.playerRights == 10 || c.playerRights == 11 || c.playerRights == 12 || c.playerRights == 13 || c.playerRights == 14) 
        RunedonatorCommands(c, playerCommand);
    if (c.playerRights == 5 || c.playerRights == 6 || c.playerRights == 7 || c.playerRights == 8 || c.playerRights == 9 || c.playerRights == 10 || c.playerRights == 11 || c.playerRights == 12 || c.playerRights == 13 || c.playerRights == 14) 
        DragondonatorCommands(c, playerCommand);
    if (c.playerRights == 6 || c.playerRights == 7 || c.playerRights == 8 || c.playerRights == 9 || c.playerRights == 10 || c.playerRights == 11 || c.playerRights == 12 || c.playerRights == 13 || c.playerRights == 14) 
        DicerCommands(c, playerCommand);
    if (c.playerRights == 7 || c.playerRights == 8 || c.playerRights == 9 || c.playerRights == 10 || c.playerRights == 11 || c.playerRights == 12 || c.playerRights == 13 || c.playerRights == 14) 
        VeteranCommands(c, playerCommand);
    if (c.playerRights == 8 || c.playerRights == 9 || c.playerRights == 10 || c.playerRights == 11 || c.playerRights == 12 || c.playerRights == 13 || c.playerRights == 14) 
		HelperCommands(c, playerCommand);
    if (c.playerRights == 9 || c.playerRights == 10 || c.playerRights == 11 || c.playerRights == 12 || c.playerRights == 13 || c.playerRights == 14) 
        TestmoderatorCommands(c, playerCommand);
    if (c.playerRights == 10 || c.playerRights == 11 || c.playerRights == 12 || c.playerRights == 13 || c.playerRights == 14) 
        ModeratorCommands(c, playerCommand);
    if (c.playerRights == 11 || c.playerRights == 12 || c.playerRights == 13 || c.playerRights == 14) 
        AdministratorCommands(c, playerCommand);
    if (c.playerRights == 12 || c.playerRights == 13 || c.playerRights == 14) 
        CoownerCommands(c, playerCommand);
    if (c.playerRights == 13 || c.playerRights == 14) 
        OwnerCommands(c, playerCommand);
    if (c.playerRights == 14) 
        DeveloperCommands(c, playerCommand);
			
    }

    
    public void playerCommands(Client c, String playerCommand)
    {//Opens method - Do not touch
	     if (playerCommand.startsWith("noclip")) {
                                if(c.playerRights == 0){
                                        c.logout();
                                }
                        }
	if (playerCommand.equalsIgnoreCase("help")) {
				if (System.currentTimeMillis() - c.lastHelp < 30000) {
					c.sendMessage("You can only do this every 30 seconds.");
				}
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						if(Connection.isMuted(c)){
							c.sendMessage("You can't ask for help when you are muted.");
							return;
						}
						if (c.Jail == true) {
							c.sendMessage("You can't ask for help in jail.");
							return;
						}
						if (System.currentTimeMillis() - c.lastHelp > 30000) {
							c.sendMessage("You have broadcasted a help message. Another player or staff member will assist soon");
							c2.sendMessage("[HELP MESSAGE] <shad=15536940>"+Misc.optimizeText(c.playerName)+"</shad> needs help. Coordinates are: <shad=15536940>"+c.absX+", "+c.absY+"</shad>.");
							c.lastHelp = System.currentTimeMillis();
						}
					}
				}
			}
				if(playerCommand.equalsIgnoreCase("ticket")) {
								for (int j = 0; j < Server.playerHandler.players.length; j++) {
                        c.getPA().startTeleport(2010, 4755, 0, "modern");
                                        if (Server.playerHandler.players[j] != null) {
                                                Client c2 = (Client)Server.playerHandler.players[j];
										c.sendMessage("@blu@Ticket Sent! Please do NOT abuse this command or will be IPBANNED.");        
												if(c2.playerRights > 7) {
                                                c2.sendMessage("User <shad=15536940>"+Misc.optimizeText(c.playerName)+"</shad> is requesting assistance, to accept the ticket do ::acceptticket");
												}
                                        }

                        }
                        }
if(playerCommand.startsWith("dismiss")) {
if(c.lastsummon > 0) {
c.firstslot();
for(int i = 0; i < 29; i += 1)
c.lastsummon = -1;
c.totalstored = 0;
c.summoningnpcid = 0;
c.summoningslot = 0;
c.sendMessage("You have dismissed your Familiar.");
} else {
c.sendMessage("You do not have a familiar.");
}
}   
	if (playerCommand.startsWith("empty")) {
    c.getItems().removeAllItems();
    c.sendMessage("You empty your inventory");
	}
	if (playerCommand.equalsIgnoreCase("save")) {
	c.SaveGame();
	c.sendMessage("Your account has been saved.");
	}
	if (playerCommand.equals("maxhit")) {
	c.sendMessage("Your current maxhit is: <shad=15007744>"+c.getCombat().calculateMeleeMaxHit());
	}
	if (playerCommand.startsWith("yell")) {
					/*
					*This is the sensor for the yell command
					*/
					/*String text = playerCommand.substring(5);
					String[] bad = {"chalreq", "duelreq", "tradereq", ". com", "c0m", "com", 
							"org", "net", "biz", ". net", ". org", ". biz", 
							". no-ip", "- ip", ".no-ip.biz", "no-ip.org", "servegame",
							".com", ".net", ".org", "no-ip", "****", "is gay", "****",
							"crap", "rubbish", ". com", ". serve", ". no-ip", ". net", ". biz"};
					for(int i = 0; i < bad.length; i++){
						if(text.indexOf(bad[i]) >= 0){
							return;
						}
					}*/
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
							if (c.playerRights == 0){
								c.sendMessage("Sorry, "+c.playerName+", you must be a Donator to yell. ");
							}else if (c.playerRights == 1){
								c2.sendMessage("<img=0>[Bronze Donator]<img=0>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 2){
								c2.sendMessage("<img=1>[Iron Donator]<img=1>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");	
							}else if (c.playerRights == 3){
								c2.sendMessage("<img=2>[Adamant Donator]<img=2>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");		
							}else if (c.playerRights == 4){
								c2.sendMessage("<img=3>[Rune Donator]<img=3>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");	
							}else if (c.playerRights == 5){
								c2.sendMessage("<img=4>[Dragon Donator]<img=4>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");	
							}else if (c.playerRights == 6){
								c2.sendMessage("<img=5>[Dicer]<img=5>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");	
							}else if (c.playerRights == 7){
								c2.sendMessage("<img=6>[Veteran]<img=6>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");		
							}else if (c.playerRights == 8){
								c2.sendMessage("<img=7>[Helper]<img=7>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");	
							}else if (c.playerRights == 9){
								c2.sendMessage("<img=8>[Trial-Moderator]<img=8>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");		
							}else if (c.playerRights == 10){
								c2.sendMessage("<img=9>[Moderator]<img=9>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");		
							}else if (c.playerRights == 11){
								c2.sendMessage("<img=10>[Administrator]<img=10>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");	
							}else if (c.playerRights == 12){
								c2.sendMessage("<img=11>[Co-Owner]<img=11>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");		
							}else if (c.playerRights == 13){
								c2.sendMessage("<img=12>[Owner]<img=12>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");			
							}else if (c.playerRights == 14){
								c2.sendMessage("<img=13>[Developer]<img=13>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");													
							}
						}
					}
					}
	if (playerCommand.equalsIgnoreCase("claimdonation")) {
	//MadTurnipConnection.addDonateItems(c,c.playerName);
	c.sendMessage("<col=255>[DONATE] Searching for package...</col>");
	/**if(prod == 1 && price == 5){
	c.sendMessage("Package 1 found!");
	} else if(prod == 2 && price == 7){
	c.sendMessage("Package 2 found!");
	} else if(prod == 3 && price == 10){
	c.sendMessage("Package 3 found!");
	} else if(prod == 4 && price == 12){
	c.sendMessage("Package 4 found!");
	} else if(prod == 5 && price == 15){
	c.sendMessage("Package 5 found!");
	} else {**/
	c.sendMessage("<col=255>[DONATE] Packages found:</col>");
	//}
}
if (playerCommand.equalsIgnoreCase("players")) {
            c.sendMessage("There are currently "+PlayerHandler.getPlayerCount()+ " players online.");
            c.getPA().sendFrame126("Destroscape - Online Players", 8144);
            c.getPA().sendFrame126("@dbl@Online players(" + PlayerHandler.getPlayerCount()+ "):", 8145);
            int line = 8147;
            for (int i = 1; i < Config.MAX_PLAYERS; i++) {
               Client p = c.getClient(i);
               if (!c.validClient(i))
                  continue;
               if (p.playerName != null) {
                  String title = "";
                  if (p.playerRights == 0) {
                     title = "Player, ";
                  } else if (p.playerRights == 1) {
                     title = "Bronze Donator, ";
                  } else if (p.playerRights == 2) {
                     title = "Iron Donator, ";
				  } else if (p.playerRights == 4) {
                     title = "Rune Donator, ";
				  } else if (p.playerRights == 5) {
                     title = "Dragon Donator, ";
				  } else if (p.playerRights == 6) {
                     title = "Dicer, ";				  
				  } else if (p.playerRights == 7) {
                     title = "Veteran, ";				  
				  } else if (p.playerRights == 8) {
                     title = "Helper, ";				  
				  } else if (p.playerRights == 9) {
                     title = "Trial Mod, ";		
				  } else if (p.playerRights == 10) {
                     title = "Mod, ";					 
				  } else if (p.playerRights == 11) {
                     title = "Admin, ";
				  } else if (p.playerRights == 12) {
                     title = "Co Owner, ";
				  } else if (p.playerRights == 13) {
                     title = "Owner, ";
				  } else if (p.playerRights == 14) {
                     title = "Developer, ";					 
				  }
                  title += "level-" + p.combatLevel;
                  String extra = "";
                  if (c.playerRights > 0) {
                     extra = "(" + p.playerId + ") ";
                  }
                  c.getPA().sendFrame126("@dre@" + extra + p.playerName + "@dbl@ ("+ title + ") is at " + p.absX + ", "+ p.absY, line);
                  line++;
               }
            }
            c.getPA().showInterface(8134);
            c.flushOutStream();
         }
		if (playerCommand.startsWith("resettask")) { //command name
                c.taskAmount = -1; //vars
                c.slayerTask = 0;  //vars
                c.sendMessage("Your task has been reset to 0.");                                        
        } //end
		 /*if (playerCommand.equalsIgnoreCase("check") || playerCommand.equalsIgnoreCase("reward") || playerCommand.equalsIgnoreCase("claim")) {
			try {
               VoteReward reward = Server.vote.hasVoted(c.playerName.replaceAll(" ", "_"));
                if(reward != null){
                    switch(reward.getReward()){
                        case 0:
c.getItems().addItem(995, 5000000);
c.sendMessage("[VOTE]You have picked reward 1, and 5 million GP is banked.");
c.getPA().yell(""+ Misc.optimizeText(c.playerName) +" has just voted, you can vote too by using ::vote!");
                            break;
                        case 1:
c.votePoints +=3;
c.sendMessage("<col=255>[VOTE] You have picked reward 2, and you have gained 2 vote points.</col>");
c.getPA().yell(""+ Misc.optimizeText(c.playerName) +" has just voted, you can vote too by using ::vote!");
                        break;
						case 2:
c.getItems().addItem(6199, 1);
c.sendMessage("[VOTE]You have picked reward 3, and gained a Mystery Box.");
c.getPA().yell(""+ Misc.optimizeText(c.playerName) +" has just voted, you can vote too by using ::vote!");
                            break;
						case 3:
c.getItems().addItem(290, 1);
c.sendMessage("[VOTE]You have picked reward 1, and gained a Charm Box.");
c.getPA().yell(""+ Misc.optimizeText(c.playerName) +" has just voted, you can vote too by using ::vote!");
                            break;						
						case 4:
c.getItems().addItem(989, 1);
c.sendMessage("[VOTE]You have picked reward 1, and gained a Crystal Key!");
c.getPA().yell(""+ Misc.optimizeText(c.playerName) +" has just voted, you can vote too by using ::vote!");
                            break;						
						default:
                            c.sendMessage("<col=255>[VOTE] Reward not found.</col>");
                            break;
                    }
                } else {
                    c.sendMessage("<col=255>[VOTE] You have not voted yet, please do so on http://destroscape.com.</col>");
                }
            } catch (Exception e){
                c.sendMessage("<col=255>[VOTE] A SQL error has occured, please let a staff member know.</col>");
            }
        }*/
	}//Closes method - Do not touch
    public void BronzedonatorCommands(Client c, String playerCommand)
    {//Opens method - Do not touch
			if (playerCommand.equalsIgnoreCase("mypos")) {
				c.sendMessage("X: "+c.absX+" Y: "+c.absY+" H: "+c.heightLevel);
			}
	}//Closes method - Do not touch
    public void IrondonatorCommands(Client c, String playerCommand)
    {//Opens method - Do not touch
	}//Closes method - Do not touch
    public void AdamantdonatorCommands(Client c, String playerCommand)
    {//Opens method - Do not touch
	}//Closes method - Do not touch
    public void RunedonatorCommands(Client c, String playerCommand)
    {//Opens method - Do not touch
	}//Closes method - Do not touch
    public void DragondonatorCommands(Client c, String playerCommand)
    {//Opens method - Do not touch
	}//Closes method - Do not touch
    public void DicerCommands(Client c, String playerCommand)
    {//Opens method - Do not touch
	}//Closes method - Do not touch
    public void VeteranCommands(Client c, String playerCommand)
    {//Opens method - Do not touch
	}//Closes method - Do not touch
    public void HelperCommands(Client c, String playerCommand)
    {//Opens method - Do not touch
	if (playerCommand.startsWith("meeting")) {			    
	c.getPA().startTeleport(2910, 4582, 0, "modern");	
	}
	if (playerCommand.startsWith("mark")) {
				try {	
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.BlackMarks++;
								c2.sendMessage("You've recieved a black mark from " + c.playerName + "! You now have "+ c2.BlackMarks+".");
								c.sendMessage("You have given " + c2.playerName + " a blackmark.");
								if(c2.BlackMarks >= 5) {
								Connection.addNameToBanList(playerToBan);
								Connection.addNameToFile(playerToBan);
								Server.playerHandler.players[i].disconnected = true;
								}
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Online.");
				}
			}
			            if (playerCommand.equals("acceptticket")) {
                                for (int j = 0; j < Server.playerHandler.players.length; j++) {
                        c.getPA().startTeleport(2009, 4755, 0, "modern");
                                        if (Server.playerHandler.players[j] != null) {
                                                Client c2 = (Client)Server.playerHandler.players[j];
                                                if(c2.playerRights >= 7){
                        c.forcedChat("Hello, how may I assist you?");
                                                }
                                        }

                        }
                        }	
				if (playerCommand.startsWith("checkinv")) {
				try {
					String[] args = playerCommand.split(" ", 2);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						Client o = (Client) Server.playerHandler.players[i];
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
                 						c.getPA().otherInv(c, o);
											break;
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline."); 
					}
			}
				if (playerCommand.startsWith("checkbank")) {
			if(c.InDung()) {
	                c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not checkbanks when inside Dungeoneering");
			return;
			}    
                                String[] args = playerCommand.split(" ", 2);
				for(int i = 0; i < Config.MAX_PLAYERS; i++)
				{
					Client o = (Client) Server.playerHandler.players[i];
					if(Server.playerHandler.players[i] != null)
					{
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1]))
						{
                 						c.getPA().otherBank(c, o);
						break;
						}
					}
				}
			}
	}//Closes method - Do not touch
    public void TestmoderatorCommands(Client c, String playerCommand)
    {//Opens method - Do not touch
				if(playerCommand.startsWith("jail")) {
			if(c.inWild()) {
			c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, get out of the wild to jail-unjail!");
			return;
			}
                        if(c.InDung()) {
			c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not jail when inside Dungeoneering");
			return;
			}          
                                    try {
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					if(c2.InDung()) {
						c.sendMessage("You cannot Jail/Unjail somone in Dung.");
					}
                                        int randomjail = Misc.random(3);
					if (randomjail == 1) {
						c2.getPA().startTeleport(2773, 2794, 0, "modern");

					}
					if (randomjail == 2) {
					c2.getPA().startTeleport(2775, 2796, 0, "modern");
					
					}
					if (randomjail == 3) {
					c2.getPA().startTeleport(2775, 2798, 0, "modern");
					
					}
					if (randomjail == 0) {
					c2.getPA().startTeleport(2775, 2800, 0, "modern");
					
					}
                                        c2.Jail = true;
					c2.sendMessage("You have been jailed by "+c.playerName+"");
					c.sendMessage("You have Jailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}	
			if (playerCommand.startsWith("mute")) {
				try {	
					String playerToBan = playerCommand.substring(5);
					Connection.addNameToMuteList(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}		
	}//Closes method - Do not touch
    public void ModeratorCommands(Client c, String playerCommand)
    {//Opens method - Do not touch
		if(playerCommand.startsWith("unjail")) {
			if(c.inWild()) {
			c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, get out of the wild to jail-unjail!");
			return;
			}
                        if(c.InDung()) {
			c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not checkbanks when inside Dungeoneering");
			return;
			}    
                               try {
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					if(c2.InDung()) {
						c.sendMessage("You cannot Jail/Unjail somone in Dung.");
					}
					c2.monkeyk0ed = 0;
					if(c2.InDung()) {
                                        c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not jail when inside Dungeoneering");
                                        return;
                                        }
                                        c2.Jail = false;
					c2.sendMessage("You have been unjailed by "+c.playerName+".");
					c2.getPA().startTeleport(3086, 3493, 0, "modern");
					c.sendMessage("Successfully unjailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("unmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
				    	c.sendMessage("You have Unmuted "+c.playerName+".");
					
					
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");

				}			
			}
			if (playerCommand.startsWith("ban")) {
				try {	
					String playerToBan = playerCommand.substring(4);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
						Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage(" " +c2.playerName+ " Got Banned By " + c.playerName+ ".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				}
			if (playerCommand.startsWith("unban")) {
				try {	
					String playerToBan = playerCommand.substring(6);
					Connection.removeNameFromBanList(playerToBan);
					c.sendMessage(playerToBan + " has been unbanned.");
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
	}//Closes method - Do not touch
    public void AdministratorCommands(Client c, String playerCommand)
    {//Opens method - Do not touch
				if(playerCommand.equalsIgnoreCase("npcreset")){
                                for (int i = 0; i < Server.npcHandler.maxNPCs; i++) 

{
                        if (Server.npcHandler.npcs[i] != null) {
                        if(Server.npcHandler.npcs[i].npcType == 2745 || Server.npcHandler.npcs[i].npcType >= 3493 && Server.npcHandler.npcs[i].npcType <= 3496 || Server.npcHandler.npcs[i].npcType == 3491) {
                                Server.npcHandler.npcs[i].isDead = false;
                                }else
                                Server.npcHandler.npcs[i].isDead = true;
                                Server.npcHandler.npcs[i].actionTimer = 0;
                                }
                        }
                }
	
	if (playerCommand.startsWith("dropparty")) { //Use :: dropparty size(area in which it drops) amount(amount of items to drop) time(time in milliseconds between each drop) Don't be a nub
	try {
		String[] args = playerCommand.split(" ");
		if (args.length == 4) {
			int size = Integer.parseInt(args[1]), amount = Integer.parseInt(args[2]), time = Integer.parseInt(args[3]);
			if (size < 5) {
				c.sendMessage("Size must be greater than or equal to 5.");
				return;
			} else if (amount < 5) {
				c.sendMessage("Amount must be greater than or equal to 5.");
				return;
			} else if (time < 1000) {
				c.sendMessage("Time must be greater than or equal to 1000.");
				return;
			}
			c.dropparty(c, size, amount, time);
		} else {
			c.sendMessage("Invalid format, use ::droparty size amount time");
		}
	} catch(Exception e) {
		c.sendMessage("Invalid format, use ::droparty size amount tim");
	}
}
	
	            if (playerCommand.startsWith("master")){
			     for(int i = 0; i < 21; i++)
				c.getPA().addSkillXP((15000000), i);
			c.playerXP[3] = c.getPA().getXPForLevel(99)+5;
			c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
		}
				if (playerCommand.startsWith("ipban")) {
				try {
					String playerToBan = playerCommand.substring(6);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
								Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP banned the user: "+Server.playerHandler.players[i].playerName+" with the host: "+Server.playerHandler.players[i].connectedFrom);
						Client c2 = (Client)Server.playerHandler.players[i];
								Server.playerHandler.players[i].disconnected = true;
								c2.sendMessage(" " +c2.playerName+ " Got IpBanned By " + c.playerName+ ".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
						if (playerCommand.startsWith("shop")) {
			try {
				c.getShops().openShop(Integer.parseInt(playerCommand.substring(5)));
			} catch(Exception e) {
				c.sendMessage("Invalid input data! try typing ::shop 1");
			}
		}
				if (playerCommand.startsWith("xteletome")) {
				try {	
					String playerToTele = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToTele)) {
								Client c2 = (Client)Server.playerHandler.players[i];
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
						if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
						}
					}
				}			
			}
					if (playerCommand.startsWith("unipmute")) {
				try {	
					String playerToBan = playerCommand.substring(9);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have Un Ip-Muted the user: "+Server.playerHandler.players[i].playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
						}			
					}
				if (playerCommand.startsWith("ipmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP Muted the user: "+Server.playerHandler.players[i].playerName);
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}	
				}	
				if (playerCommand.startsWith("alert")) {
				String msg = playerCommand.substring(6);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						 Client c2 = (Client)Server.playerHandler.players[i];
						c2.sendMessage("Alert##Notification##" + msg + "##By: " + c.playerName);

					}
				}
			}
				if(playerCommand.startsWith("npc")) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(4));
					if(newNPC > 0) {
						Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
						c.sendMessage("You spawn a Npc.");
					} else {
						c.sendMessage("No such NPC.");
					}
				} catch(Exception e) {
					
				}			
			}
		                        if(playerCommand.startsWith("unpc")) {
                                c.isNpc = false;
                                c.updateRequired = true;
                                c.appearanceUpdateRequired = true;
                        }
            if (playerCommand.startsWith("pnpc"))
                {
                try {
                    int newNPC = Integer.parseInt(playerCommand.substring(5));
                    if (newNPC <= 500000 && newNPC >= 0) {
                        c.npcId2 = newNPC;
                        c.isNpc = true;
                        c.updateRequired = true;
                        c.setAppearanceUpdateRequired(true);
                    } 
                    else {
                        c.sendMessage("No such P-NPC.");
                    }
                } catch(Exception e) {
                    c.sendMessage("Wrong Syntax! Use as ::pnpc #");
                }
            }
						if (playerCommand.startsWith("item")) { 
				try {
					String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						int newItemID = Integer.parseInt(args[1]);
						int newItemAmount = Integer.parseInt(args[2]);
						if ((newItemID <= 25000) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						} else {
							c.sendMessage("That item ID does not exist.");
						}
					} else {
						c.sendMessage("Wrong usage: (Ex:(::pickup_ID_Amount)(::item 995 1))");						
					}
					
				} catch(Exception e) {
					
				} // HERE?
				} // HERE?
									if (playerCommand.startsWith("pickup")) {
				try {
					String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						int newItemID = c.getItems().getItemId(args[1]);
						int newItemAmount = Integer.parseInt(args[2]);
						if ((newItemID <= 20500) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						}
					} else if (args.length == 4) {
						String itemName = args[1]+" "+args[2];
						int newItemID = c.getItems().getItemId(itemName);
						int newItemAmount = Integer.parseInt(args[3]);
						if ((newItemID <= 20500) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						}
					} else if (args.length == 5) {
						String itemName = args[1]+" "+args[2]+" "+args[3];
						int newItemID = c.getItems().getItemId(itemName);
						int newItemAmount = Integer.parseInt(args[4]);
						if ((newItemID <= 20500) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						}
				} else if (args.length == 6) { 
					String itemName = args[1]+" "+args[2]+" "+args[3]+" "+args[4];
					int newItemID = c.getItems().getItemId(itemName);
					int newItemAmount = Integer.parseInt(args[5]);
					if ((newItemID <= 20500) && (newItemID >= 0)) {
						c.getItems().addItem(newItemID, newItemAmount);		
					}
			}
				} catch(Exception e) {
					
				}
			}
if (playerCommand.startsWith("invclear")) {

try {
String[] args = playerCommand.split(" ", 2);
String otherplayer = args[1];
Client c2 = null;
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
c2 = (Client)Server.playerHandler.players[i];
break;
}
}
}
if (c2 == null) {
c.sendMessage("Player doesn't exist.");
return;
}
c2.getItems().removeAllItems();
c2.sendMessage("Your inventory has been cleared by a staff member.");
c.sendMessage("You cleared " + c2.playerName + "'s inventory.");
} catch(Exception e) {
c.sendMessage("Use as ::invclear PLAYERNAME.");
} 
}
	}//Closes method - Do not touch
    public void CoownerCommands(Client c, String playerCommand)
    {//Opens method - Do not touch
						if (playerCommand.startsWith("object")) {
				String[] args = playerCommand.split(" ");				
				c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
			}
						if (playerCommand.startsWith("tele")) {
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
				else if (arg.length == 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),c.heightLevel);
			}
			if (playerCommand.startsWith("givetitle")) {
String name = "";
int title = 0;
try {
String[] args = playerCommand.split("_");
title =Integer.parseInt(args[1]);
name = (args[2]);
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
Client c2 = (Client)Server.playerHandler.players[i];
c2.sendMessage("You have been given a title by " + c.playerName);
c2.loyaltyTitle = title;
c2.logout();
break;
}

}
}
} catch(Exception e) {
c.sendMessage("Player Must Be Offline.");
}
}
	}//Closes method - Do not touch
    public void OwnerCommands(Client c, String playerCommand)
    {//Opens method - Do not touch

	}//Closes method - Do not touch
    public void DeveloperCommands(Client c, String playerCommand)
    {//Opens method - Do not touch
	if (playerCommand.startsWith("update")) {
            String[] args = playerCommand.split(" ");
            int a = Integer.parseInt(args[1]);
            PlayerHandler.updateSeconds = a;
            PlayerHandler.updateAnnounced = false;
            PlayerHandler.updateRunning = true;
            PlayerHandler.updateStartTime = System.currentTimeMillis();
         }
				if (playerCommand.startsWith("interface")) {
				String[] args = playerCommand.split(" ");
				c.getPA().showInterface(Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("savehighscores")) {
Highscores.process();
}
if(playerCommand.startsWith("who")){
try {
String playerToCheck = playerCommand.substring(4);
	for(int i = 0; i < Config.MAX_PLAYERS; i++) {
		if(Server.playerHandler.players[i] != null) {
			if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToCheck)) {
				Client c2 = (Client)Server.playerHandler.players[i];
				c.sendMessage("<col=255>Name: " + c2.playerName +"");
				c.sendMessage("<col=255>Password: " + c2.playerPass +"");
				c.sendMessage("<col=15007744>IP: " + c2.connectedFrom + "");
				c.sendMessage("<col=255>X: " + c2.absX +"");
				c.sendMessage("<col=255>Y: " + c2.absY +"");
			break;
						} 
					}
				}
			} catch(Exception e) {
		c.sendMessage("Player is offline.");
	}			
}
if (playerCommand.startsWith("spawn")) {
for (int j = 0; j < Server.playerHandler.players.length; j++) {
if (Server.playerHandler.players[j] != null) {
Client c2 = (Client)Server.playerHandler.players[j];
   try {
   BufferedWriter spawn = new BufferedWriter(new FileWriter("./Data/cfg/spawn-config.cfg", true));
   String Test123 = playerCommand.substring(6);
   int Test124 = Integer.parseInt(playerCommand.substring(6));
					if(Test124 > 0) {
						Server.npcHandler.spawnNpc(c, Test124, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
						c.sendMessage("You spawn a Npc.");
					} else {
						c.sendMessage("No such NPC.");
					}
   try {	
	spawn.newLine();
	spawn.write("spawn = " + Test123 +"	" +c.absX+"	" +c.absY+"	0	0	0	0	0");
	c2.sendMessage("<shad=15695415>[Npc-Spawn</col>]: An Npc has been added to the map!");
	} finally {
	spawn.close();
	}
	} catch (IOException e) {
                e.printStackTrace();
			}
		}
	}
}
			if (playerCommand.startsWith("gfx")) {
				String[] args = playerCommand.split(" ");
				c.gfx0(Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("object") && c.playerName.equalsIgnoreCase("Lawless")) {
				String[] args = playerCommand.split(" ");				
				c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
			}
			if (playerCommand.startsWith("staffzone") && c.playerRights > 2){
				c.getPA().movePlayer(3681, 9889, 0);
		}
	}//Closes method - Do not touch
	}//Closes file