package game.entity.player.commands.impl;

import org.Vote.MainLoader;
import org.Vote.VoteReward;

import engine.util.Misc;
import game.Config;
import game.content.MoneyBank;
import game.content.Reputation;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.entity.player.commands.CommandParent;
import game.sql.MadTurnipConnection;

public class DefaultCommands implements CommandParent {

	@Override
	public void handleCommand(Player c, String cmd) {
		String[] command = cmd.split(" ");
		@SuppressWarnings("unused")
		String[] args = cmd.split(" ");

		if (cmd.startsWith("clan")) {
			if (c.clan != null) {
				String message = cmd.substring(6);
				c.clan.sendChat(c, message);
			}
		}

		if (cmd.startsWith("memberclaim")) {
			MadTurnipConnection.addDonateItems(c,c.playerName);
		}
		
		if (cmd.equalsIgnoreCase("check") || cmd.equalsIgnoreCase("reward") || cmd.equalsIgnoreCase("claim")) {
            try {
                VoteReward reward = MainLoader.hasVoted(c.playerName.replaceAll(" ", "_"));
                if(reward != null){
                    switch(reward.getReward()){
                        case 0:
	                  for (int j1 = 0; j1 < PlayerHandler.players.length; j1++) {
	                  if (PlayerHandler.players[j1] != null) {
	                  	Player c2 = (Player)PlayerHandler.players[j1];
	                        c2.sendMessage("@red@[Voting]@bla@ " + Misc.ucFirst(c.playerName) + " has just voted and recieved x1 Reward Book @ ::vote");
	                   }
	                }
			  c.sendMessage("Your reward has been placed in your bank!");
			  //c.getDH().ssm1("You recieved <col=255>x1 Reward Book</col>.", 20960);
                          c.getItems().addItemToBank(20960, 1);
                        break;
                        case 1:
	                  for (int j1 = 0; j1 < PlayerHandler.players.length; j1++) {
	                  if (PlayerHandler.players[j1] != null) {
	                  	Player c2 = (Player)PlayerHandler.players[j1];
	                        c2.sendMessage("@red@[Voting]@bla@ " + Misc.ucFirst(c.playerName) + " has just voted and recieved 2 Crystal keys @ ::vote");
	                   }
	                }
				c.sendMessage("Your reward has been placed in your bank!");
				//c.getDH().ssm1("You recieved <col=255>x2 Crystal keys</col>.", 990);
                        	c.getItems().addItemToBank(990, 2);
                        	break;
                        default:
                            c.sendMessage("Reward not found.");
                            break;
                    }
		if (c.x2Points == true) {
			c.votePoints += 2;
                    	c.sendMessage("Thank you for voting.");
		}
		if (c.x2Points == false) {
			c.votePoints += 1;
                    	c.sendMessage("Thank you for voting.");
		}
                } else {
                    c.sendMessage("You have no items waiting for you.");
                }
            } catch (Exception e){
                c.sendMessage("A SQL error has occured, contact an admin.");
            }
        }

		if (cmd.equalsIgnoreCase("players")) {
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
						title = "@cr2@Mod, ";
					} else if (p.playerRights == 2) {
						title = "@cr2@@blu@Admin, ";
					} else if (p.playerRights == 3) {
						title = "@cr2@@whi@Owner, ";
					} else if (p.playerRights == 4) {
						title = "@cr2@Donator, ";
					} else if (p.playerRights == 5) {
						title = "@cr2@[S]Donator, ";
					} else if (p.playerRights == 6) {
						title = "@cr2@[E]Donator, ";
					}
					title += "level-" + p.combatLevel;
					String extra = "";
					if (c.playerRights > 0) {
						extra = "(" + p.playerId + ") ";
					}
					c.getPA().sendFrame126(
							"@dre@" + extra + Misc.optimizeText(p.playerName) + " (" + title
									+ "@dre@)", line);
					line++;
				}
			}
			c.getPA().showInterface(8134);
			c.flushOutStream();
		}

		if (cmd.startsWith("rep+")) {
			String playerName = cmd.substring(5);
			for (int playerIndex = 0; playerIndex < PlayerHandler.players.length; playerIndex++) {
				if (PlayerHandler.players[playerIndex] != null) {
					if (PlayerHandler.players[playerIndex].playerName
							.equalsIgnoreCase(playerName)) {
						Reputation.givePositiveReputation(c, playerIndex);
						return;
					}
				}
			}
			c.sendMessage("No such player online.");
		}

		if (cmd.startsWith("rep-")) {
			String playerName = cmd.substring(5);
			for (int playerIndex = 0; playerIndex < PlayerHandler.players.length; playerIndex++) {
				if (PlayerHandler.players[playerIndex] != null) {
					if (PlayerHandler.players[playerIndex].playerName
							.equalsIgnoreCase(playerName)) {
						Reputation.giveNegativeReputation(c, playerIndex);
						return;
					}
				}
			}
			c.sendMessage("No such player online.");
		}

		if (cmd.startsWith("rep")) {
			if (c.reputationPoints >= 1000) {
				c.forcedChat("I have "+c.reputationPoints+" reputation points, so you can **** off.");
			} else
				c.forcedChat("I currently have "+c.reputationPoints+" reputation point(s).");
		}

		if (cmd.startsWith("vote")) {
			c.getPA().sendFrame126(""+ Config.VOTE_URL +"", 12000);
		}

		if (cmd.startsWith("forums")) {
			c.getPA().sendFrame126(""+ Config.FORUM_URL +"", 12000);
		}

		if (cmd.startsWith("store")) {
			c.getPA().sendFrame126(""+ Config.DONATE_URL +"", 12000);
		}

		if (cmd.startsWith("highscores")) {
			c.getPA().sendFrame126(""+ Config.HIGHSCORES_URL +"", 12000);
		}

		/*if (cmd.equalsIgnoreCase("glory")) {
			c.getItems().addItem(1705, 200);
		}*/

		if (cmd.startsWith("kdr")) {
			double KDR = ((double)c.kills)/((double)c.deaths);
			//c.forcedChat("My Kill/Death ratio is "+c.KC+"/"+c.DC+"; "+KDR+".");
			c.forcedChat("My Kill/Death ratio is "+c.kills+"/"+c.deaths+"; "+KDR+".");
		}

		if (cmd.startsWith("thread") && cmd.length() > 7) {
			try {
				String thread = cmd.substring(7);
				c.getPA().sendFrame126("www.sweprime.com/forum/showthread.php?tid="+thread+"", 12000);
				c.sendMessage("Opening Thread #"+thread);
			} catch (Exception e) {
				c.sendMessage("You must enter a thread number.");
			}
		} 

		if (cmd.startsWith("changepassword") && cmd.length() > 15) {
			try {
				String password = cmd.substring(15);
				if (password.length() < 4) {
					c.sendMessage("Your password needs to be bigger than 3 characters.");
					return;
				}
				c.playerPass = password;
				c.sendMessage("Your password is now: " + password);
			} catch (Exception e) {
				c.sendMessage("You must enter a password.");
			}
		}     

		if (cmd.startsWith("yell") && !cmd.startsWith("yelltag")) {
			try {
				String message = cmd.substring(5);
				if (System.currentTimeMillis() - c.lastYell < 30000 && c.playerRights <= 0) {
					c.sendMessage("You can only yell once per 30 seconds. Donators get to yell without a timer.");
					return;
				}
				if (System.currentTimeMillis() - c.lastYell < 4000 && c.playerRights > 0)
					return;		
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Player c2 = (Player)PlayerHandler.players[j];
						c.lastYell = System.currentTimeMillis();
						c2.sendMessage(""+c.yellTag+" " + Misc.optimizeText(c.playerName) + ": " + Misc.optimizeText(message));
					}
				}
			} catch(Exception e) {
				c.sendMessage("Wrong syntax use as ::yell message");
			}
		}
		if (cmd.startsWith("tele1")) {
			try {
				String tele1 = Misc.optimizeText(cmd.substring(6));
				if (tele1.length() > 15){
					c.sendMessage("Max length for teleport naming is 15 characters!");
					return;
				}
				if (tele1.contains("@")){
					c.sendMessage("Color codes not supported");
					return;
				}
				c.tele1 =tele1;
				c.sendMessage("Successfully changed teleport 1 name to: "+c.tele1);

			} catch(Exception e) {
				c.sendMessage("Wrong syntax use as ::tele1 name");
			}
		}
		if (cmd.startsWith("tele2")) {
			try {
				String tele2 = Misc.optimizeText(cmd.substring(6));
				if (tele2.length() > 15){
					c.sendMessage("Max length for teleport naming is 15 characters!");
					return;
				}
				if (tele2.contains("@")){
					c.sendMessage("Color codes not supported");
					return;
				}
				c.tele2 =tele2;
				c.sendMessage("Successfully changed teleport 2 name to: "+c.tele2);

			} catch(Exception e) {
				c.sendMessage("Wrong syntax use as ::tele2 name");
			}
		}
		if (cmd.startsWith("tele3")) {
			try {
				String tele3 = Misc.optimizeText(cmd.substring(6));
				if (tele3.length() > 15){
					c.sendMessage("Max length for teleport naming is 15 characters!");
					return;
				}
				if (tele3.contains("@")){
					c.sendMessage("Color codes not supported");
					return;
				}
				c.tele3 =tele3;
				c.sendMessage("Successfully changed teleport 3 name to: "+c.tele3);

			} catch(Exception e) {
				c.sendMessage("Wrong syntax use as ::tele3 name");
			}
		}
		if (cmd.startsWith("tele4")) {
			try {
				String tele4 = Misc.optimizeText(cmd.substring(6));
				if (tele4.length() > 15){
					c.sendMessage("Max length for teleport naming is 15 characters!");
					return;
				}
				if (tele4.contains("@")){
					c.sendMessage("Color codes not supported");
					return;
				}
				c.tele4 =tele4;
				c.sendMessage("Successfully changed teleport 4 name to: "+c.tele4);

			} catch(Exception e) {
				c.sendMessage("Wrong syntax use as ::tele4 name");
			}
		}
		if (cmd.startsWith("tele5")) {
			try {
				String tele5 = Misc.optimizeText(cmd.substring(6));
				if (tele5.length() > 15){
					c.sendMessage("Max length for teleport naming is 15 characters!");
					return;
				}
				if (tele5.contains("@")){
					c.sendMessage("Color codes not supported");
					return;
				}
				c.tele5 =tele5;
				c.sendMessage("Successfully changed teleport 5 name to: "+c.tele5);

			} catch(Exception e) {
				c.sendMessage("Wrong syntax use as ::tele5 name");
			}
		}

		if (cmd.startsWith("yelltag") && c.playerRights >= 1) {
			try {
				String newYellTag = Misc.optimizeText(cmd.substring(8));
				for (int i = 0; i < Config.BADTAGS.length; i++){
					if (newYellTag.startsWith(Config.BADTAGS[i])) {
						c.sendMessage("You cannot set your yell tag to that!");
						return;
					}
				}
				if (newYellTag.length() > 10){
					c.sendMessage("Max length for yell tags is 8 characters!");
					return;
				}

				if (newYellTag.contains("@")){
					c.sendMessage("Color codes not supported");
					return;
				}
				if (newYellTag.startsWith("reset") || newYellTag.startsWith("default")) {
					c.customYell = false;
					c.getYellTag();
					return;
				}
				else if (c.playerRights == 1)
					c.yellTag = "<col=40000><shad=65297>["+newYellTag+"]</col>@cr1@<shad=65297> ";
				else if (c.playerRights == 3)
					c.yellTag = "<color=194298><shad=194298>["+newYellTag+"]</col>@cr2@<col=255><shad=194298>";
				else if (c.playerRights == 4)
					c.yellTag = "<shad=5893412>["+newYellTag+"]</col>@cr3@<shad=392218>";
				else if (c.playerRights == 5)
					c.yellTag = "<shad=5893412>["+newYellTag+"]@cr4@<shad=3882542>";
				c.sendMessage("Successfully changed your yell tag to: "+c.yellTag);

			} catch(Exception e) {
				c.sendMessage("Wrong syntax use as ::yelltag youryelltag");
			}
		}
		switch (command[0]) {
		case "test":
			c.sendMessage("I'm a pro");
			return;
		}
	}
}