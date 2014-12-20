package game.net.packets;

import engine.util.Misc;
import game.Config;
import game.Server;
import game.entity.player.Player;
import game.content.quests.TheStrykeWyrm;
import game.content.quests.DesertTreasure;

public class DialogueHandler {

	private final Player c;

	public DialogueHandler(final Player client) {
		c = client;
	}

	/**
	 * Handles all talking
	 * 
	 * @param dialogue
	 *            The dialogue you want to use
	 * @param npcId
	 *            The npc id that the chat will focus on during the chat
	 */
	public void sendDialogues(final int dialogue, final int npcId) {
		c.talkingNpc = npcId;
		switch (dialogue) {

		case 31:
			c.getDH().sendOption4("Normal Magic", "Ancient Magic",
					" ", "Cancel");
			c.nextChat = 0;
			c.dialogueAction = 9;
			break;

		case 0:
			c.talkingNpc = -1;
			c.getPA().removeAllWindows();
			c.nextChat = 0;
			break;
		case 1:
			sendStatement("You found a hidden tunnel! Do you want to enter it?");
			c.dialogueAction = 1;
			c.nextChat = 2;
			break;
		case 2:
			sendOption2("Yea! I'm fearless!", "No way! That looks scary!");
			c.dialogueAction = 1;
			c.nextChat = 0;
			break;
		case 3:
			sendOption2("View Tool Belt", "Withdraw from Tool Belt");
			c.nextChat = 0;
			c.dialogueAction = 3;
			break;
		case 4:
			sendOption5("Withdraw Hatchet","Withdraw Pickaxe","Withdraw Butterfly Net","Withdraw Chisel","more");
			c.dialogueAction = 4;
			c.nextChat = 0;
			break;
		case 7:
			sendNpcChat4("Hello "+Misc.optimizeText(c.playerName)+"!", "I have the power to change your appearances.", "Whether you want to be a male or female.", "Black or White, I can do it!",c.talkingNpc,"Make-over Mage");
			c.nextChat = 8;
			break;
		case 8:
			sendNpcChat1("Would you like to change your appearances?",c.talkingNpc,"Make-over Mage");
			c.nextChat = 9;
			break;
		case 9:
			sendOption2("Yes", "No, I like the way I am");
			c.dialogueAction = 9;
			c.nextChat = 0;
			break;
		case 77:
			sendNpcChat4("" + Misc.optimizeText(c.playerName) + " you have Failed.",
					"You did participate enough to take down", "the portals. ",
					"Try Harder next time.", c.talkingNpc, "Void Knight");
			c.getPA().walkableInterface(-1);
			break;
		case 78:
			sendNpcChat4("All is Lost!",
					"You could not take down the portals in time.", " ",
					"Try Harder next time.", c.talkingNpc, "Void Knight");
			c.getPA().walkableInterface(-1);
			break;
		case 79:
			sendNpcChat4("Congratulations " + Misc.optimizeText(c.playerName) + "!",
					"You took down all the portals whilst keeping",
					"the void knight alive.", "You been awarded, well done.",
					c.talkingNpc, "Void Knight");
			c.getPA().walkableInterface(-1);
			break;
		case 80:
			sendNpcChat2("Time is up! You have ran out of Warrior Guild Tokens.", "Please leave the room of Cyclopes as soon as possible.", 4289, "Kamfreena");
			break;
		case 81:
			sendNpcChat1("I said TIME'S UP! Please leave by yourself next time.", 4289, "Kamfreena");
			break;
		case 5:
			sendNpcChat4("Hello adventurer...",
					"My name is Kolodion, the master of this mage bank.",
					"Would you like to play a minigame in order ",
					"to earn points towards recieving magic related prizes?",
					c.talkingNpc, "Kolodion");
			c.nextChat = 6;
			break;
		case 6:
			sendNpcChat4("The way the game works is as follows...",
					"You will be teleported to the wilderness,",
					"You must kill mages to recieve points,",
					"redeem points with the chamber guardian.", c.talkingNpc,
					"Kolodion");
			c.nextChat = 15;
			break;
		case 69:
			sendNpcChat2("You even defeated TzTok-Jad, I am most impressed!",
					"Please accept this gift as a reward.", c.talkingNpc,
					"Tzhaar-Mej-Tal");
			c.nextChat = -1;
			break;
	//Slayer master
		case 11:
			sendNpcChat4(
					"Hello!",
					"I am a master of the slayer skill.",
					"I can assign you a slayer task suitable to your combat level.",
					"What would you like to do?", c.talkingNpc,
					"Slayer Master");
			c.nextChat = 12;
			break;
		case 12:
			sendOption4("I would like to get a slayer task.",
					"I would like to get an easier task.", "I would like to reset my Slayer Task. (<col=255>20 Slayer Points</col>)", "Nothing..");
			c.dialogueAction = 5;
			break;
		case 28:
			sendOption2("Reset my Slayer Task for <col=255>20 Slayer Points</col>.",
					"Do not reset my Slayer Task..");
			c.dialogueAction = 28;
			break;
		case 13:
			sendNpcChat4("Hello!", "I am a master of the slayer skill.",
					"I see I have already assigned you a task to complete.",
					"You must finish the task before getting a new one!",
					c.talkingNpc, "Slayer Master");
			c.nextChat = -1;
			break;
		case 14:
			sendOption2("Yes I would like an easier task.",
					"No I would like to keep my task.");
			c.dialogueAction = 6;
			break;
		case 16:
			sendNpcChat4("Hello!", "I am a master of the slayer skill.",
					"I see I have already assigned you a task to complete.",
					"Do you want an easier one?",
					c.talkingNpc, "Slayer Master");
			c.nextChat = 14;
			break;
	//End of slayer master

		case 15:
			sendOption2("Yes I would like to play",
					"No, sounds too dangerous for me.");
			c.dialogueAction = 7;
			break;
		case 100:
			sendNpcChat3(" ", "Good day. How may I help you?", " ",
					c.talkingNpc, "Banker");
			c.nextChat = 101;
			break;
		case 17:
			sendOption5("Air", "Mind", "Water", "Earth", "More");
			c.dialogueAction = 10;
			c.dialogueId = 17;
			c.teleAction = -1;
			break;
		case 18:
			sendOption5("Fire", "Body", "Cosmic", "Astral", "More");
			c.dialogueAction = 11;
			c.dialogueId = 18;
			c.teleAction = -1;
			break;
		case 19:
			sendOption5("Nature", "Law", "Death", "Blood", "More");
			c.dialogueAction = 12;
			c.dialogueId = 19;
			c.teleAction = -1;
			break;

			/**
			 * Home Shop Dialogues
			 **/

		case 20:
			sendNpcChat2("Hello, " + Misc.optimizeText(c.playerName)
					+ "! My name is Wydin.",
					"I will sell you misc items!",
					c.talkingNpc, "Wydin");
			c.nextChat = 21;
			break;

		case 21:
			sendNpcChat2("I also have a small stock of misc items.",
					"Would you like to look at my current stock?",
					c.talkingNpc, "Wydin");
			c.nextChat = 22;
			break;

		case 22:
			sendOption2("Yes, I would like to see what you have in stock.",
					"Not really in the mood right now.");
			c.dialogueAction = 13;
			c.dialogueId = 22;
			break;

		case 23:
			sendNpcChat2(
					"Welcome to the General Store "
							+ Misc.optimizeText(c.playerName) + "!",
							"Would you like to see what I currently have to sell?",
							c.talkingNpc, "Shopkeeper");
			c.nextChat = 24;
			break;

		case 24:
			sendOption2("I would love to!", "Can't right now.");
			c.dialogueAction = 14;
			c.dialogueId = 24;
			break;

		/* Enchanted gem, slayer */

		case 25:
			sendNpcChat3(
					"Hello!",
					"I am a master of the slayer skill.",
					"What can i do for you?", c.talkingNpc,
					"Slayer Master");
			c.nextChat = 26;
			break;
		case 26:
			sendOption2("What is my current task?", "I'd like to teleport to my task!");
			c.dialogueAction = 26;
			break;

		case 27: //Donator - Movario
			sendOption2("View Donator Points Shop", "View Donators Only Shop");
			c.dialogueAction = 27;
			break;

			/**
			 * Transportation Dialogues
			 **/

		case 101:
			sendOption5("Mining", "Smithing", "Fishing", "Woodcutting",
					"More...");
			c.dialogueAction = 101;
			c.dialogueId = -1;
			break;

		case 50:
			sendNpcChat2("Hello " + Misc.optimizeText(c.playerName) + "!",
					"I am one of four pilots here at the Airport.",
					c.talkingNpc, "Pilot Holden");
			c.nextChat = 51;
			break;

		case 51:
			sendNpcChat2(
					"I can fly you to several destinations across "+ Config.SERVER_NAME +".",
					"Where would you like to fly today?", c.talkingNpc,
					"Pilot Holden");
			c.nextChat = 52;
			break;

		case 52:
			sendOption4("Regular Training", "Slayer Tower",
					"Brimhaven Dungeon", "Taverly Dungeon");
			c.dialogueAction = 10001;
			c.dialogueId = 5000;
			break;

		case 5000:
			sendOption4("Rock Crabs", "Slayer Tower", "Brimhaven Dungeon",
					"Taverly Dungeon");
			c.dialogueAction = 5000;
			c.dialogueId = -1;
			break;

		case 53:
			sendNpcChat2("Greetings " + Misc.optimizeText(c.playerName) + "!",
					"I am one of four pilots here at the Airport.",
					c.talkingNpc, "Pilot Johnson");
			c.nextChat = 54;
			break;

		case 54:
			sendNpcChat2("I can take you to any minigame across "+ Config.SERVER_NAME +".",
					"Which one would you like to go to today?", c.talkingNpc,
					"Pilot Johnson");
			c.nextChat = 55;
			break;

		case 55:
			sendOption4("Pest Control", "Barrows", "Duel Arena",
					"Tz-Haar Cave");
			c.dialogueAction = 10002;
			c.dialogueId = 55;
			break;

		case 56:
			sendNpcChat2("Welcome " + Misc.optimizeText(c.playerName) + "!",
					"I am one of four pilots here at the Airport.",
					c.talkingNpc, "Pilot Scarlet");
			c.nextChat = 57;
			break;

		case 57:
			sendNpcChat2("I will fly you to any pvp spot in "+ Config.SERVER_NAME +".",
					"Which one would you like to fly to today?.", c.talkingNpc,
					"Pilot Scarlet");
			c.nextChat = 58;
			break;

		case 58:
			sendOption5("Edgeville", 
					"East Dragons", 
					"West Dragons",
					"Mage Bank",
					"More");
			c.dialogueAction = 10003;
			c.dialogueId = 58;
			break;

		case 59:
			sendNpcChat2("Beware " + Misc.optimizeText(c.playerName) + "!",
					"I am one of four pilots here at the Airport.",
					c.talkingNpc, "Pilot Scarlet");
			c.nextChat = 60;
			break;

		case 60:
			sendNpcChat2("I will fly you to the worst of bosses in "+ Config.SERVER_NAME +".",
					"Which one would you like to face to today?.",
					c.talkingNpc, "Pilot Scarlet");
			c.nextChat = 61;
			break;

		case 61:
			sendOption4("Godwars Dungeon", "Corporeal Beast",
					"Dagonnoth Cavern", "Tormented Demons");
			c.dialogueAction = 10000;
			c.dialogueId = 61;
			break;

		case 62:
			sendOption5("Chaos Tunnels",
					"Greater Demons",
					"The Gates",
					"Fun Pk",
					"Previous Page");
			c.dialogueAction = 62;
			break;

		case 66:
			sendNpcChat2("You must defeat the Culinaromancer and his minions!", "Are you ready?",
					c.talkingNpc, "Portal");
			c.nextChat = 67;
			break;

		case 67:
			sendOption2("Yes, I'm ready to smite this creature!", "No, I'm not prepared to die!");
			c.dialogueAction = 67;
			break;

			/**
			 * Summoning Shop
			 */

		case 70:
			sendNpcChat2("Hello adventurer, my name is Pikkupstix",
					"I am a master at the art of summoning.", c.talkingNpc,
					"Pikkupstix");
			c.nextChat = 71;
			break;

		case 71:
			sendNpcChat4(
					"If you are a true master at the skill of summoning,",
					"I will sell you my cape.",
					"I also have a wide variety of items used to create pouches.",
					"What would you like to do today?", c.talkingNpc,
					"Pikkupstix");
			c.nextChat = 72;
			break;

		case 72:
			sendOption4("Buy Summoning Cape", "View Shop 1", "View Shop 2",
					"Leave");
			c.dialogueId = 72;
			c.dialogueAction = 700;
			break;

			/**
			 * Starter Guide Dialogue
			 */

		case 73:
			sendNpcChat2(
					"Welcome to the World of "+ Config.SERVER_NAME +" "
							+ Misc.optimizeText(c.playerName) + "!",
							"I am here to teach ", c.talkingNpc, "Beginner Guide");
			break;

		case 500000000:
			sendNpcChat2("Vote " + Misc.optimizeText(c.playerName) + "!",
					"Go VOTE TODAY!", c.talkingNpc, "Arhein");
			break;

		case 500000001:
			sendNpcChat2("I sell many outfits that you can wear.",
					"You should see what I have in stock!", c.talkingNpc,
					"Zambo");
			break;

			/**
			 * Quest Handler
			 **/

		case 500:
			c.getDH().sendNpcChat2("Excuse Me Adventurer!",
					"You are not allowed onto my ship.", 518, "Captain Shanks");
			c.nextChat = 501;
			break;

		case 501:
			c.getDH().sendPlayerChat1(9830, "Sorry! I am new here.");
			if (c.aGoodStartStatus == 0) {
				c.nextChat = 502;
			} else {
				c.nextChat = 512;
			}
			break;

		case 502:
			c.getDH().sendNpcChat2("Oh are you now?",
					"Maybe you could help me with something.", 518,
					"Captain Shanks");
			c.nextChat = 503;
			break;

		case 503:
			c.getDH().sendPlayerChat1(9830, "What would I need to do?");
			c.nextChat = 504;
			break;

		case 504:
			c.getDH().sendNpcChat2("I need to stay at my ship for now.",
					"Can need you to deliver message to a man named Hans?",
					518, "Captain Shanks");
			c.nextChat = 505;
			break;

		case 505:
			c.getDH().sendPlayerChat1(9830,
					"Where might I be able to find this Hans?");
			c.nextChat = 506;
			break;

		case 506:
			c.getDH().sendNpcChat2(
					"You can find Hans by the lumbridge castle.",
					"Do you think you will be able to help me?", 518,
					"Captain Shanks");
			c.nextChat = 507;
			break;

		case 507:
			c.getDH().sendOption2("Yes, I would be glad to help you.",
					"No, I don't want to help you.");
			c.dialogueAction = 200;
			break;

		case 508:
			c.getDH().sendNpcChat2("Well, I do hope you come back to help me.",
					"You are still not welcome on my ship though!", 518,
					"Captain Shanks");
			c.nextChat = 512;
			break;

		case 509:
			c.getDH().sendNpcChat2("That's great!!",
					"Take this note, and bring it to Hans immediately", 518,
					"Captain Shanks");
			c.nextChat = 510;
			break;

		case 510:
			c.getDH().sendStatement("You have received a note.");
			if (c.getItems().freeSlots() == 0) {
				Server.itemHandler.createGroundItem(c, 1508, c.getX(),
						c.getY(), 1, c.playerId);
			} else {
				c.getItems().addItem(1508, 1);
			}
			if (c.aGoodStartStatus < 5) {
				c.aGoodStartStatus = 1;
				c.getQuest().getStatusFrame(c);
			}
			c.nextChat = 511;
			break;

		case 511:
			c.getDH()
			.sendPlayerChat1(9830, "I'll get this to Hans right away!");
			c.nextChat = 512;
			break;

		case 512:
			c.getPA().closeAllWindows();
			break;

		case 513:
			c.getDH().sendPlayerChat2(9830, "Hello Captain, I am new here",
					"I am seeking a quest to embark on.");
			c.nextChat = 502;
			break;

		case 514:
			c.getDH().sendNpcChat2("Why hello there adventurer.",
					"What can I do for you today?", 0, "Hans");
			c.nextChat = 515;
			break;

		case 515:
			if (c.aGoodStartStatus == 1) {
				c.getDH().sendPlayerChat2(9830, "Hello Hans!",
						"Captain Shanks sent me here to give you this note.");
				if (c.getItems().playerHasItem(1508)) {
					c.nextChat = 516;
				} else {
					c.nextChat = 517;
				}
			} else {
				c.getDH().sendPlayerChat1(9830,
						"Just wanted to say hello mate.");
				c.nextChat = 512;
			}
			break;

		case 516:
			c.getDH().sendStatement("You give Hans the note.");
			c.getItems().deleteItem(1508, 1);
			c.nextChat = 518;
			break;

		case 517:
			c.getDH()
			.sendPlayerChat2(
					9830,
					"I'm terribly sorry, but it appears that I have lost the note.",
					"I'll come back when I find it.");
			c.nextChat = 512;
			break;

		case 518:
			c.getDH().sendNpcChat2(
					"Oh Dear, he wants me to get him 3 wolf bones.",
					"I don't know how to fight, let alone kill a wolf.", 0,
					"Hans");
			c.nextChat = 519;
			break;

		case 519:
			c.getDH().sendNpcChat2("Perhaps you can get the bones for me?",
					"Would you please do that for me?", 0, "Hans");
			c.nextChat = 520;
			break;

		case 520:
			c.getDH().sendPlayerChat2(9830, "I would be honored to do it.",
					"Where might I be able to find these wolves?");
			c.nextChat = 521;
			break;

		case 521:
			c.getDH().sendNpcChat2(
					"You can find these wolves on White Wolf Mountain",
					"It is in the middle of Catherby, and Taverly.", 0, "Hans");
			c.nextChat = 522;
			break;

		case 522:
			c.getDH().sendPlayerChat2(9830, "Thanks, I'll be off now.",
					"I'll be back soon with those wolf bones.");
			c.aGoodStartStatus = 5;
			c.nextChat = 512;
			break;

		case 523:
			c.getDH().sendNpcChat2("Hello again, I see you have the bones.",
					"Thank you for getting them for me.", 0, "Hans");
			c.nextChat = 524;
			break;

		case 524:
			if (c.getItems().playerHasItem(2859, 3)) {
				c.getDH().sendStatement("You hand over 3 wolf bones to Hans.");
				c.getItems().deleteItem(2859, 3);
				c.nextChat = 525;
			} else {
				c.getDH().sendStatement("You must get 3 wolf bones.");
				c.nextChat = 512;
			}
			break;

		case 525:
			c.getDH()
			.sendNpcChat3("Thank you very much adventurer!",
					"I'll talk to the captain immediately.",
					"You'll also be granted access to use his ship.",
					0, "Hans");
			c.nextChat = 526;
			break;

		case 526:
			c.aGoodStartStatus = 20;
			c.getQuest().completeQuest(c, 0);
			break;

		case 527:
			c.getDH().sendNpcChat2("I see you have not gotten the wolf bones",
					"You can find them on White Wolf Mountain", 0, "Hans");
			c.nextChat = 512;
			break;

		case 528:
			c.getDH()
			.sendNpcChat2("Thanks again for getting those bones.",
					"I would have never been able to do them myself",
					0, "Hans");
			c.nextChat = 512;
			break;

		case 529:
			if (c.getItems().playerHasItem(1508, 1)) {
				c.getDH().sendNpcChat2(
						"Aren't you supposed to be delivering that note?",
						"Get that to Hans right away.", 518, "Captain Shanks");
				c.nextChat = 512;
			} else {
				c.getDH().sendNpcChat2("Did you lose the note?",
						"Take this one, and don't lose it.", 518,
						"Captain Shanks");
				c.nextChat = 510;
			}
			break;

		case 530:
			c.getDH().sendNpcChat2("I will take you to Crandor.",
					"Click on the plank if you wish to travel there.", 518,
					"Hans");
			c.nextChat = 512;
			break;

		case 41340:
			c.getDH().sendNpcChat2(
					"Hey are you interrested in entering the lottery?.",
					"Entering will cost you only 5 million coins.", 5778,
					"Bartak");
			c.nextChat = 41341;
			break;

		case 41341:
			c.getPA().showInterface(11000);
			c.nextChat = 0;
			break;

		case 110:
			sendStatement("Would you like to save this position?");
			c.nextChat = 111;
			break;

		case 111:
			sendOption2("Yes","No");
			c.nextChat = 0;
			c.dialogueAction = 111;
			break;

		case 112:
			sendOption2("Teleport", "Delete Teleport");
			c.nextChat = 0;
			c.dialogueAction = 112;
			break;

		case 113:
			sendNpcChat4("You're currently getting ready to prestige "+c.getPA().getSkillName(c.currentSkillInfo)+".", 
					"Doing this will completely reset your current level.", 
					"You'll have the options whether to prestige it to advance", 
					"in prestige ranks or prestige for prestige points.", c.talkingNpc, "Prestige Guide");
			c.nextChat = 114;
			break;

		case 114:
			sendOption2("Advance Prestige Rank", "Obtain Prestige Points");
			c.dialogueAction = 114;
			c.nextChat = 0;
			break;

		/*case 115:
			sendNpcChat4("Greetings "+ Misc.optimizeText(c.playerName) +".", 
					"Welcome to "+ Config.SERVER_NAME +". I'm Mandrith, the guide", 
					"of the Bounty Hunter Minigame, in which you are allocated.", 
					"What would you like to know?", c.talkingNpc, "Mandrith");
			c.nextChat = 116;
			break;*/

		case 116:
			sendOption4("What is Bounty Hunter?", 
					"What are the benefits of playing?", 
					"Access Bounty Hunter Shop.", 
					"Never mind.");
			c.nextChat = 0;
			c.dialogueAction = 116;
			break;

		case 117:
			sendNpcChat4("Bounty Hunter is one of "+ Config.SERVER_NAME +"'s many minigames.",
					"It takes place inside an arena found by going through",
					"craters west of my current position. Don't be hasty though,",
					"I suggest you prepare to fight for your life!",c.talkingNpc,"Mandrith");
			c.nextChat = 118;
			break;

		case 118:
			sendNpcChat4("Upon entering the craters and entering the arena.",
					"You, along with everyone else inside will be assigned",
					"targets in which you can hunt down for rewards.",
					"TODODODOODOODODOD",c.talkingNpc,"Mandrith");//TODO
			break;


		case 130:
			sendNpcChat4("Hello "+ Misc.optimizeText(c.playerName) +".", 
					"It seems you are in need of a Ring of Kinship", 
					"I can sell you one for 10k.", 
					"Would you like to purchase a Ring of Kinship?", c.talkingNpc, "Thok");
			c.nextChat = 131;
			break;

		case 131:
			sendOption2("Yes", "No, I can get one for cheaper!");
			c.dialogueAction = 131;
			c.nextChat = 0;
			break;

		case 132:
			sendNpcChat4("Hello there stranger!", 
					"I found a secret pathway to a horrifying monster", 
					"I hear he drops lots and lots of goodies!", 
					"I'll take you there for 50k, interested?" , c.talkingNpc, "Velrak");
			c.nextChat = 133;
			break;

		case 133:
			sendOption2("Yes, lets go!", "No thanks.");
			c.dialogueAction = 133;
			break;
			/**
			 * Combat Prestige
			 */
		case 134:
			sendNpcChat4("You are getting ready to combat prestige.", 
					"Combat prestige requires you to have 99 in all combat skills.", 
					"Doing this will reset all your combat stats to 1.", 
					"Do you accept the terms?", c.talkingNpc, "Combat Prestige");
			c.nextChat = 135;
			break;

		case 135:
			sendOption2("Yes", "No");
			c.dialogueAction = 135;
			break;

		case 136:
			sendStatement("You have the choice to gain either 7 ranks or 25 prestige points");//TODO Combat Prestige
			c.nextChat = 137;
			break;

		case 137:
			sendOption2("Advance Prestige Rank", "Obtain Prestige Points");
			c.nextChat = 0;
			c.dialogueAction = 137;
			break;
			/**
			 * Combat Prestige End
			 */
			/**
			 * Thessalia Milestones
			 */
		case 138:
			sendNpcChat4("Hello "+ Misc.optimizeText(c.playerName) +"! I am Thessalia and", 
					"you're able to change your appearances with my help.", 
					"Whether you want to be a male or female", 
					"white or black, I'll be able to provide the changes!", c.talkingNpc, "Thessalia");
			c.nextChat = 139;
			break;

		case 139:
			sendNpcChat4("I am also in charge of "+ Config.SERVER_NAME +"'s milestone cape store.", 
					"You earn more access into the store each time",
					"you get all your skills to a certain level x10.",
					"What would you like to do?", c.talkingNpc, "Thessalia");
			c.nextChat = 140;
			break;

		case 140:
			sendOption2("Change appearances", "Access milestone store");
			c.nextChat = 0;
			c.dialogueAction = 140;
			break;
			/**
			 * Thessalia End
			 */

		case 141: //Completionist Stand
			sendStatement("A mysterious cape hangs upon this stand.");
			c.nextChat = 0;
			break;
			/**
			 * Max
			 */
		case 142:
			sendNpcChat4("Greetings sire, my name is Max, I've wondered the land of",
					""+ Config.SERVER_NAME +" for centeries and beaten the best of the best!",
					"I have many followers who seek the cape, which I wield.",
					"If you think you are worthee to challenge me, come forth!", c.talkingNpc, "Max");
			c.nextChat = 143;
			break;

		case 143:
			sendStatement("Are you prepared to brawl with Max?");
			c.nextChat = 144;
			break;

		case 144:
			sendOption2("Let's do this!", "I'm currently not ready.");
			c.nextChat = 0;
			c.dialogueAction = 144;
			break;

		case 145:
			sendStatement("Max has no interest in speaking with you right now.");
			c.nextChat = 0;
			break;
			/**
			 * End Max
			 */

			/**
			 * Max Prestige Start
			 */
		case 146:
			sendNpcChat4("You are getting ready to max prestige.", 
					"Max prestige requires you to have 99 in all your skills.", 
					"Doing this will reset all your your stats to 1.", 
					"Do you accept the terms?", c.talkingNpc, "Max Prestige");
			c.nextChat = 147;
			break;

		case 147:
			sendOption2("Yes", "No");
			c.nextChat = 0;
			c.dialogueAction = 147;
			break;

		case 148:
			sendStatement("You have the choice to gain either 23 ranks or 190 prestige points");//TODO Combat Prestige
			c.nextChat = 149;
			break;

		case 149:
			sendOption2("Advance Prestige Rank", "Obtain Prestige Points");
			c.nextChat = 0;
			c.dialogueAction = 149;
			break;
			/**
			 * Max Prestige End
			 */
			/**
			 * Tab creation
			 */
		case 150:
			sendOption4("Make Home Tab", "Make Varrock Tab", "Make Falador Tab", "More");
			c.nextChat = 0;
			c.dialogueAction = 150;
			break;

		case 151:
			sendOption4("Make Lumbridge Tab", "Make Camelot Tab", "Make Ardougne Tab", "Back");
			c.nextChat = 0;
			c.dialogueAction = 151;
			break;
			/**
			 * Tab Creation End
			 */
			/**
			 * Max Dialogues 2
			 */
		case 152:
			sendNpcChat4("It seems I've underestimated you.",
					"Very well then, "+ Misc.optimizeText(c.playerName) +", it seems you deserve this.",
					"If you were to ever lose the cape, I'll be more than",
					"happy to replace it for 500,000 gold pieces.", c.talkingNpc, "Max");
			c.nextChat = 153;
			break;
			
		case 153:
			sendStatement("Max, gives you the Max Cape and Hood!");
			c.nextChat = 0;
			c.getItems().addItem(20767, 1);
			c.getItems().addItem(20768, 1);
			break;
			
		case 154:
			sendNpcChat4("Are you in need of another cape?",
					"I'll be more than happy to give you more for",
					"500,000 gold pieces.",
					"Would you like to get another Max Cape?", c.talkingNpc, "Max");
			c.nextChat = 155;
			break;
			
		case 155:
			sendOption2("Yes", "No");
			c.nextChat = 0;
			c.dialogueAction = 155;
			break;
			/**
			 * Max Dialogues 2 End
			 */
			
			/**
			 * Mage of Mastery/Mage Book Switching
			 */
		case 156:
			sendNpcChat4("Hey! I have mastered all three spell books of the world", 
					"I would be happy to teach you at least one", 
					"This is a free service for now, so better hurry!", 
					"What spell book would you like to learn?", c.talkingNpc, "Mage of Mastery");
			c.nextChat = 157;
			break;
			
		case 157:
			sendOption4("Ancients", " ", "Normal", "None");
			c.dialogueAction = 157;
			c.nextChat = 0;
			break;
			
		case 158:
			sendStatement("You have found a Starved Ancient Effigy!");
			c.nextChat = 0;
			break;
			
		case 159:
			sendOption2("I would like to fix all my barrows.", "I would like to reset all my barrows brothers.");
			c.nextChat = 0;
			c.dialogueAction = 159;
			break;
			
		case 160:
			sendOption5("Fire", "Cosmic", "Chaos", "Astral", "More");
			c.dialogueAction = 160;
			break;
			
		case 161:
			sendOption5("Nature", "Law", "Death", "Blood", "Back");
			c.dialogueAction = 161;
			break;
			
		case 162:
			sendOption5("Air", "Mind", "Water", "Earth", "More");
			c.dialogueAction = 129001;
			break;
			
		case 163:
			sendStatement("You don't have a title!");
			c.nextChat = 0;
			break;

		case 164:
			sendStatement("Once cleared, you will not be able to get it back.");
			c.nextChat = 165;
			break;

		case 165:
			sendOption2("Clear my title",  "Nevermind"); 
			c.dialogueAction = 165;
			break;
			
		case 200:
			sendOption2("Yes", "No");
			c.dialogueAction = 302;
			c.nextChat = 512;
			break;
			/**
			 * Stat resetter
			 */
		case 201:
			sendNpcChat4("Hello there "+ Misc.optimizeText(c.playerName) +"!"," I have the ability to change your XP Mode","But remember, this will reset all your stats and is irreversable!","Which XP Mode would you like to change to?", c.talkingNpc, "XP Mode Change");
			c.nextChat = 202;
			break;

		case 202:
			sendOption4("I want to change to [Novice] 5000xp","I want to change to [Expert] 500xp", "I want to change to [Legend] 500xp", "I don't want to change");
			c.dialogueAction = 202;
			break;

		case 203:
			sendNpcChat2("Congratulations!", "Your defence has been completely reset!",c.talkingNpc, "Skill Resetter");
			c.nextChat = 0;
			break;

		case 204:
			sendNpcChat2("Congratulations!", "Your attack has been completely reset!",c.talkingNpc, "Skill Resetter");
			c.nextChat = 0;
			break;

		case 205:
			sendNpcChat2("Congratulations!", "Your combat stats have been completely reset!",c.talkingNpc, "Skill Resetter");
			c.nextChat = 0;
			break;

		case 206:
			sendNpcChat2("Congratulations!","Your prayer have been completely reset!",c.talkingNpc, "Skill Resetter");
			c.nextChat = 0;
			break;
			/**
			 * Stat reset End
			 */

			/**
			 * Quests start at 2000
			 */

			/**
			 * The Knight's
			 */

		case 2000:
			sendNpcChat2("Oh dear, where, where, where!",
					"God I can't find it!", 2253, "Wise Old Man");
			c.nextChat = 2001;
			break;

		case 2001:
			sendPlayerChat1(9830, "What's wrong?");
			c.nextChat = 2002;
			break;

		case 2002:
			sendNpcChat2("I, I, uh.. I lost a very special ore!",
					"There is no way I can get another one!", 2253,
					"Wise Old Man");
			c.nextChat = 2003;
			break;

		case 2003:
			sendPlayerChat1(9830, "I bet I could get another one for you.");
			c.nextChat = 2004;
			break;

		case 2004:
			sendNpcChat3("You do not understand, young adventurer.",
					"This ore can only be obtained by defeating Chronozon,",
					"a demon that will show no mercy!", 2253, "Wise Old Man");
			c.nextChat = 2005;
			break;

		case 2005:
			if (c.playerLevel[c.playerMining] >= 75
			&& c.playerLevel[c.playerMagic] >= 50) {
				sendPlayerChat2(9830,
						"Please, I think I will be able to defeat it.",
						"How hard can a little demon be anyways?");
				c.nextChat = 2006;
			} else {
				sendNpcChat2(
						"It seems you do not have the requirements for this quest.",
						"Come back with the correct requirements.", 2253,
						"Wise Old Man");
				c.nextChat = 512;
			}
			break;

		case 2006:
			sendNpcChat3("Seems that you are a very confident warrior.",
					"Maybe you could help me obtain another orb.",
					"At my age, I cannot do it myself.", 2253, "Wise Old Man");
			c.nextChat = 2007;
			break;

		case 2007:
			sendOption2("I think I am brave enough for this quest.",
					"I don't think I am ready for it.");
			c.dialogueAction = 2000;
			break;

		case 2008:
			sendNpcChat3("Ok, you should speak to a woman named Alice.",
					"She knows where to find the demon.",
					"She works at the falador pub, you could find her there.",
					2253, "Wise Old Man");
			c.nextChat = 2009;
			break;

		case 2009:
			sendPlayerChat1(9830, "I'll go find her right now!");
			c.nextChat = 2010;
			break;

		case 2010:
			c.TKVStatus = 1;
			c.getQuest().getStatusFrame(c);
			c.getPA().closeAllWindows();
			break;

		case 2011:
			sendNpcChat2(
					"Shouldn't you be finding out information from Alice?",
					"Hurry up and find her.", 2253, "Wise Old Man");
			c.nextChat = 512;
			break;

			/**
			 * Dungeoneering Lobby
			 */

		case 2012:
			this.sendNpcChat2(
					"I will sell you these powerful weapons for some tokens.",
					"Are you interested in buying anything?.", 9711,
					"Rewards Trader");
			c.nextChat = 2013;
			break;

		case 2013:
			this.sendOption2("View Shop", "No Thank You");
			c.dialogueAction = 2001;
			break;

		case 2014:
			this.sendNpcChat2("Hello there adventurer, my name is Thok.",
					"I am a master at the skill of dungeoneering!", 9713,
					"Thok");
			c.nextChat = 2015;
			break;

		case 2015:
			this.sendNpcChat1(
					"If you are a true master, I will sell you these skillcapes.",
					9713, "Thok");
			c.nextChat = 2016;
			break;

		case 2016:
			this.sendOption2("Buy the Skillcape of Dungeoneering",
					"Buy the Masters Skillcape of Dungeoneering");
			c.dialogueAction = 2002;
			break;

		case 2017:
			this.sendOption2("Leave Dungeon!", "Nevermind, I'll stay!");
			c.dialogueAction = 2003;
			break;

		case 3499:
			this.sendOption2("Yes, empty my inventory", "Nevermind, I'll keep my items!");
			c.dialogueAction = 3499;
			break;

			/**
			 * Beginner Guide 2018 - 2040
			 */

		case 2040:
			this.sendOption2("Finish the tutorial! @bla@(@lre@Starter Pack@bla@)", "I'll do it next time i login");
			c.dialogueAction = 2034;
			break;

		case 2041:
			this.sendOption4("Novice - 5000xp", "Expert - 500xp", "Legend - 50xp", "");
			c.dialogueAction = 2042;
			break;

		case 2042:
			this.sendNpcChat2(
					"Welcome " + Misc.optimizeText(c.playerName) + ",",
					"Please select a game mode!",
					949, "Beginner Guide");
			c.nextChat = 2043;
			break;

		case 2043:
			this.sendNpcChat2(
					"So you chose <col=255>"+c.xpTitle+"</col>!",
					"Now to receive your starter please finish the tutorial!",
					949, "Beginner Guide");
			c.nextChat = 2040;
			break;

		case 2018:
			this.sendNpcChat2(
					"Hello there " + Misc.optimizeText(c.playerName) + ",",
					"And welcome to <col=255>"+ Config.SERVER_NAME +"</col>!",
					949, "Beginner Guide");
			c.nextChat = 2019;
			break;

		case 2019:
			this.sendNpcChat2(
					"You have entered a world much different then others",
					"And i am here to teach you the basics of the game!",
					949, "Beginner Guide");
			c.nextChat = 2020;
			break;

		case 2020:
			this.sendNpcChat4("Alright, lets get started!",
			 "First off, our home is right where we are now, in <col=255>Edgeville</col>", 
			 "Here you can enjoy the company of others, or", 
			 "Trade others on the market", 949, "Beginner Guide");
			c.nextChat = 2021;
			break;

		case 2021:
			c.getPA().movePlayer(3094, 3504, 0);
			this.sendNpcChat3("If you are after some starting gear or food", 
			"Make sure to visit our many shops which is", 
			"Filled with numberous of items for you to spend money on!", 949, "Beginner Guide");
			c.nextChat = 2022;
			break;

		case 2022:
			c.getPA().movePlayer(3094, 3504, 0);
			this.sendNpcChat3("If you are after some starting gear or food", 
			"Make sure to visit our many shops which is", 
			"Filled with numberous of items for you to spend money on!", 949, "Beginner Guide");
			c.nextChat = 2023;
			break;

		case 2023:
			c.getPA().movePlayer(3089, 3499, 0);
			this.sendNpcChat3("Next up right here is the crystal chest", 
			"Collect crystal keys from Npc's or voting and open the chest", 
			"For some <col=255>good rewards</col>!", 949, "Beginner Guide");
			c.nextChat = 2024;
			break;

		case 2024:
			c.getPA().movePlayer(3085, 3495, 0);
			this.sendNpcChat3("Use this altar to restore your prayer", 
			"And you can also sacrifice bones by using them on the altar", 
			"For <col=255>bonus XP</col>!", 949, "Beginner Guide");
			c.nextChat = 2025;
			break;

		case 2025:
			c.getPA().movePlayer(3087, 3487, 0);
			this.sendNpcChat3("Here about is where our <col=255>Slayer Master</col>", 
			"Is walking around handing around Slayer Tasks", 
			"For which you can recieve slayer points", 949, "Beginner Guide");
			c.nextChat = 2026;
			break;

		case 2026:
			c.getPA().movePlayer(3088, 3484, 0);
			this.sendNpcChat3("Here we have 2 Point shops which are", 
			"Used for Vote Points and Prestige Points", 
			"Both grant nice items!", 949, "Beginner Guide");
			c.nextChat = 2027;
			break;

		case 2027:
			c.getPA().movePlayer(3089, 3488, 0);
			this.sendNpcChat3("If you plan on maxing out your skills", 
			"This spot is the place for you", 
			"Become the best and grab a <col=255>Completionist Cape</col>!", 949, "Beginner Guide");
			c.nextChat = 2028;
			break;

		case 2028:
			c.getPA().movePlayer(3089, 3489, 0);
			this.sendNpcChat3("Ongoing on becoming the best", 
			"This old fellow can sell you skillcapes to showoff", 
			"When acquiring a 99 Skill", 949, "Beginner Guide");
			c.nextChat = 2029;
			break;

		case 2029:
			this.sendNpcChat2("Now as for the next step, theres <col=255>Teleportation</col>", 
			"Lets head over to the <col=255>Traveler</col> so i can explain further!", 949, "Beginner Guide");
			c.nextChat = 2030;
			break;

		case 2030:
			c.getPA().movePlayer(3088, 3504, 0);
			this.sendNpcChat3("Alright, here we got our 2 <col=255>Teleportations</col>", 
			"The first one being the <col=255>Traveler</col> which can teleport you", 
			"To various monsters, minigames and such!", 949, "Beginner Guide");
			c.nextChat = 2031;
			break;

		case 2031:
			c.getPA().movePlayer(3087, 3504, 0);
			this.sendNpcChat2("The secound being the <col=255>Fairy Ring</col> which holds the", 
			"Teleports to all the various citys!", 949, "Beginner Guide");
			c.nextChat = 2032;
			break;

		case 2032:
			this.sendNpcChat3("That is edgeville well explained", 
			"Now lets get over to another main place", 
			"The <col=255>Skilling Area</col>", 949, "Beginner Guide");
			c.nextChat = 2033;
			break;

		case 2033:
			c.getPA().movePlayer(2335, 3689, 0);
			this.sendNpcChat3("Here we have the <col=255>Skilling Area</col>", 
			"Containing some fishing spots, thieving stalls and", 
			"3 Important Guys", 949, "Beginner Guide");
			c.nextChat = 2034;
			break;

		case 2034:
			c.getPA().movePlayer(2339, 3685, 0);
			this.sendNpcChat3("The first one out here being <col=255>Flynn</col>", 
			"Who can let you onto <col=255>Skilling Tasks</col> and gain", 
			"<col=255>Skilling Points</col> To spend in his shop", 949, "Beginner Guide");
			c.nextChat = 2035;
			break;

		case 2035:
			c.getPA().movePlayer(2330, 3689, 0);
			this.sendNpcChat3("The secound and third being <col=255>Skill Master</col> and <col=255>Banker</col>", 
			"The <col=255>Skill Master</col> Can sell you skilling supplys and teleport you", 
			"To different skilling locations over the world", 949, "Beginner Guide");
			c.nextChat = 2036;
			break;

		case 2036:
			this.sendNpcChat3("And ofcourse, you might want to be able to get home?!", 
			"To get home, simply use the <col=255>Home Teleport</col>", 
			"Which exists in all of the magic spellbooks!", 949, "Beginner Guide");
			c.nextChat = 2037;
			break;

		case 2037:
			this.sendNpcChat3("If you experience anythin unusual or need some help", 
			"Either go to our forums located at:", 
			"<col=255>www..com</col>", 949, "Beginner Guide");
			c.nextChat = 2038;
			break;

		case 2038:
			this.sendNpcChat2("Or '<col=255>Call For Staff</col>' ", 
			"Which can be accessed on the bottom of the quest tab.", 949, "Beginner Guide");
			c.nextChat = 2039;
			break;

		case 2039:
			this.sendNpcChat3("That was the very basics of this game my friend!", 
			"We hope you enjoy your stay here with us", 
			"Have a great day!", 949, "Beginner Guide");
			c.nextChat = -1;
			c.finishedBeg = true;
			c.getPA().addStarter();
			break;
		//End of newcomer

		case 3000:
			sendNpcChat3("Greetings adventurer, I have a large stock of",
					"armour that you may find useful on your journey.",
					"Would you like to see what I have?", 556, "Grum");
			c.nextChat = 3001;
			break;

		case 3001:
			sendOption2("Yes!", "No.");
			c.dialogueAction = 3001;
			c.nextChat = -1;
			break;

		case 3002:
			sendNpcChat3("Greetings adventurer, I have a large stock of",
					"magic supplies that you may find useful on your journey.",
					"Would you like to see what I have?", 541, "Zeke");
			c.nextChat = 3003;
			break;

		case 3003:
			sendOption2("Yes please.", "No thanks.");
			c.dialogueAction = 3003;
			c.nextChat = -1;
			break;

		case 3004:
			sendNpcChat3("Greetings adventurer, I have a large stock of",
					"range supplies that you may find useful on your journey.",
					"Would you like to see what I have?", 550, "Lowe");
			c.nextChat = 3005;
			break;

		case 3005:
			sendOption2("Yes please.", "No thanks.");
			c.dialogueAction = 3005;
			c.nextChat = -1;
			break;

		case 3006:
			sendNpcChat3("Greetings adventurer, I have a large stock of",
					"supplies that you may find useful on your journey.",
					"Would you like to see what I have?", 545, "Dommik");
			c.nextChat = 3007;
			break;

		case 3007:
			sendOption2("Yes please.", "No thanks.");
			c.dialogueAction = 3007;
			c.nextChat = -1;
			break;
			/**
			 * Summoning Dialogue
			 **/
		case 3332:
			sendOption2("View PK Supply Shop", "Sell Statuettes");
			c.dialogueAction = 3332;
			c.nextChat = -1;
			break;
		case 3333:
			sendNpcChat3("Greetings adventurer, I am looking to purchase",
					"all statuettes and artifacts, would you care",
					"to sell me any of yours?", 6537, "Mandrith");
			c.nextChat = 3334;
			break;
		case 3334:
			sendOption2("Yes please.", "No thanks.");
			c.dialogueAction = 3333;
			c.nextChat = -1;
			break;
		case 1000:
			sendOption4("Air", "Water", "Earth", "Mind");
			c.dialogueAction = 1000;
			c.dialogueId = 1000;
			break;

		case 3400:
			sendNpcChat4("Greetings adventurer!", "I'm the head skillmaster for the skillers out there.", "and i have teleports to various skilling areas for you.", "Where would you like to go?", 519, "Skill Master");
			c.nextChat = 3401;
			break;
		case 3401: 
			sendOption5("Mining Locations", "Runecrafting Altars", "Agility Courses", "Hunting Location", "Next Page");
			c.dialogueAction = 3401;
			c.dialogueId = 3401;
			break;
		case 3402: //Crafting 2932, 3285  - Summoning 2209, 5348
			sendOption5("Crafting Location", "Summoning Location", "", "", "Previous Page");
			c.dialogueAction = 3402;
			c.dialogueId = 3402;
			break;
		case 3403://Mining
			sendOption5("Essence Location", "Novice Location", "Intermediate Location", "", "Previous Page");
			c.dialogueAction = 3403;
			c.dialogueId = 3403;
			break;
		case 3404://Mining
			sendOption5("Gnome Course", "Barbarian Course", "Wilderness Course", "", "Previous Page");
			c.dialogueAction = 3404;
			c.dialogueId = 3404;
			break;
		case 3405:
			sendOption5("Air Altar", "Mind Altar", "Water Altar", "Earth Altar", "Next Page");
			c.dialogueAction = 3405;
			c.dialogueId = 3405;
			break;
		case 3406:
			sendOption5("Fire Altar", "Cosmic Altar", "Chaos Altar", "Astral Altar", "Next Page");
			c.dialogueAction = 3406;
			c.dialogueId = 3406;
			break;
		case 3407:
			sendOption5("Nature Altar", "Law Altar", "Death Altar", "Blood Altar", "First Page");
			c.dialogueAction = 3407;
			c.dialogueId = 3407;
			break;

		//QUEST- The Strykewyrm
		case 3410:
			sendNpcChat4("Hello there "+ Misc.optimizeText(c.playerName) +"", "I'm the Quester around here and i can tell you", "one thing or another about very much!", "So what are you after?", 945, "Quester");
			c.nextChat = 3411;
			break;
		case 3411:
			sendOption2("I'm looking for a quest", "Nothing special..");
			c.dialogueAction = 3411;
			c.dialogueId = 3411;
			break;
		case 3412:
			sendPlayerChat1(9830, "I'm here looking for a quest.");
			c.nextChat = 3413;
			break;
		case 3413:
			sendNpcChat3("Okey "+ Misc.optimizeText(c.playerName) +"", "A Quest you say, i have a few ones in store", "so it's up to you to choose one you can handle.", 945, "Quester");
			c.nextChat = 3414;
			break;
		case 3414:
			sendPlayerChat1(9830, "Okey thank you, i will!");
			c.nextChat = 3415;
			break;
		case 3415:
			sendOption5("The Strykewyrm", "Lunar Diplomacy", "Desert Treasure", "-", "-");
			c.dialogueAction = 3415;
			c.dialogueId = 3415;
			break;
		case 3480:
			sendNpcChat4("So you chose The Lunar Diplomacy "+ Misc.optimizeText(c.playerName) +"", "Let me tell you something about it,", "our fellow people in the lunar isle has stumbled", "upon an illness that struck theyr island awhile ago and", 945, "Quester");
			c.nextChat = 3481;
			break;
		case 3481:
			sendNpcChat2("Meteora in lunar isle needs your help!", "You should go talk to her for more information!", 945, "Quester");
			c.nextChat = 3482;
			break;
		case 3482:
			sendPlayerChat1(9830, "Sure thing, i'll go talk to her!");
			c.nextChat = -1;
			c.lunarDiplomacy = 1;
			break;
		case 3483:
			sendNpcChat4("So you chose The Desert Treasure "+ Misc.optimizeText(c.playerName) +"", "Let me tell you something about it,", "an Archaeologist is seeking help as he recently lost", "4 of his precious diamonds to some evil bosses..", 945, "Quester");
			c.nextChat = 3484;
			break;	
		case 3484:
			sendNpcChat2("You should go and speak to him, if you want to help!", "He is located ontop of the white wolfs mountain in catherby.", 945, "Quester");
			c.nextChat = 3485;
			break;	
		case 3485:
			sendPlayerChat1(9830, "Sure thing, i'll go talk to him!");
			c.nextChat = -1;
			c.DT = 1;
			break;	
		case 3416:
			sendNpcChat3("So you chose The Strykewyrm "+ Misc.optimizeText(c.playerName) +"", "Let me tell you something about it,", "a strykewyrm is a large worm looking thing which", 945, "Quester");
			c.nextChat = 3417;
			break;
		case 3417:
			sendNpcChat3("has started to invade a few different locations", "such as some jungle areas, icey/snowy areas,", "and also some parts of the desert.", 945, "Quester");
			c.nextChat = 3418;
			break;
		case 3418:
			sendPlayerChat1(9830, "Worms? Hah, this will be easy.");
			c.nextChat = 3419;
			break;
		case 3419:
			sendNpcChat3("Yes worms, but the downside is that they", "are mutations so theyre the size of 3-4 humans,", "so they are nothing to play with.", 945, "Quester");
			c.nextChat = 3420;
			break;
		case 3420:
			sendPlayerChat1(9830, "Ugh, okey what should i do?");
			c.nextChat = 3421;
			break;
		case 3421:
			sendNpcChat3("I need you to locate atleast one", "of these strykewyrms and identify it for me", "do you think you can do that?", 945, "Quester");
			c.nextChat = 3422;
			break;
		case 3422:
			sendPlayerChat1(9830, "Umm.. yeah i guess.");
			c.nextChat = -1;
			c.theStryke = 1;
			break;
		case 3423:
			sendPlayerChat1(9830, "I have identified the strykewyrm now.");
			c.nextChat = 3424;
			break;
		case 3424:
			sendNpcChat3("Ah great!", "Then you know what your up against", "Here you go!", 945, "Quester");
			c.nextChat = 3425;
			break;
		case 3425:
			c.theStryke = 3;
			c.getQuest().completeQuest(c, 0);
			break;
			
		//QUEST - Lunar Diplomacy
		case 3426:
			sendPlayerChat2(9830, "Hello, i was told by the quester in edgeville that", "you have some problems with illness around here?");
			c.nextChat = 3427;
			break;
		case 3427:
			sendNpcChat4("I've been awaiting you "+ Misc.optimizeText(c.playerName) +"", "Yes, some sort of illness stuck our island awhile ago and", "lots of people has gotten sick and some has passed away", "and it's a serious issue for me..", 4515, "Meteora");
			c.nextChat = 3428;
			break;	
		case 3428:
			sendNpcChat2("And i have no idea about what i can do about this", "i feel really powerless and weak..", 4515, "Meteora");
			c.nextChat = 3429;
			break;	
		case 3429:
			sendOption2("Maybe i can help you?", "Haha, not my problem");
			c.dialogueAction = 3429;
			c.dialogueId = 3429;
			break;	
		case 3430:
			sendPlayerChat1(9830, "Maybe i could help you with this?");
			c.nextChat = 3431;
			break;	
		case 3431:
			sendNpcChat2("You can't understand how happy i would be if", "you could help me to solve this!", 4515, "Meteora");
			c.nextChat = 3432;
			break;		
		case 3432:
			sendPlayerChat2(9830, "I'm just glad i can help", "So what do you know about this illness?");
			c.nextChat = 3433;
			break;	
		case 3433:
			sendNpcChat4("The things i know about this illness is that", "it was brought here by an evil monster", "hes kind of a bork sort of speek", "hehe.. i ment dork", 4515, "Meteora");
			c.nextChat = 3434;
			break;	
		case 3434:
			sendNpcChat4("And i have also heard rumours that some adventurers have", "actually found the entrance to him and seen him", "but no one has ever dared to enter hes cave", "as they thing he'll beat them right away..", 4515, "Meteora");
			c.nextChat = 3435;
			break;		
		case 3435:
			sendPlayerChat2(9830, "Well i'm not like any others", "I'm barely afraid of anything..");
			c.nextChat = 3436;
			break;	
		case 3436:
			sendPlayerChat1(9830, "So what can i do to help you threw this?");
			c.nextChat = 3437;
			break;	
		case 3437:
			sendNpcChat4("I need some kind of medicine for the sick people", "here on the island, and i think i know someone who can do this!", "You should talk to the Chemist", "He should be in his house in Draynor Village", 4515, "Meteora");
			c.nextChat = 3438;
			break;	
		case 3438:
			sendPlayerChat1(9830, "Alright! I'll do what i can!");
			c.lunarDiplomacy = 2;
			c.nextChat = -1;
			break;	
		case 3439: //Start of Chemist
			sendPlayerChat4(9830, "Hello there Chemist, i came to ask you about something", "The lunar isle has gotten an serious illness", "And i need some kind of medicine to cure them", "Do you think you can help me with that?");
			c.nextChat = 3440;
			break;	
		case 3440:
			sendNpcChat4("Welcome to my simple workplace dear adventurer", "Some other adventurer came by the other day and told me", "About this illness and i started working on a medicine", "But as i cannot go out of here, i need you to..", 367, "Chemist");
			c.nextChat = 3441;
			break;		
		case 3441:
			sendNpcChat1("Grab me some simple ingredients to make this work", 367, "Chemist");
			c.nextChat = 3442;
			break;	
		case 3442:
			sendPlayerChat1(9830, "What kind of ingredients do you need?");
			c.nextChat = 3443;
			break;	
		case 3443:
			sendNpcChat2("I need the following:", "<col=255>2X of Ashes</col> - <col=255>1X Super Restore (4)</col> - <col=255>1X Pouch</col>"  , 367, "Chemist");
			c.nextChat = 3444;
			break;		
		case 3444:
			sendPlayerChat1(9830, "Alright! I'll come back with the ingredients shortly!");
			c.lunarDiplomacy = 3;
			c.nextChat = 0;
			break;			
		case 3445:
			sendPlayerChat1(9830, "I'm back! and i have the ingredients!");
			c.nextChat = 3446;
			break;	
		case 3446:
			sendNpcChat2("Welcome back!", "That's great, give me a few secound to complete this!"  , 367, "Chemist");
			c.nextChat = 3447;
			break;
		case 3447:
			sendStatement("The chemist hands you the <col=255>Medicine</col>!");
			c.getItems().deleteItem(592, 1);
			c.getItems().deleteItem(592, 1);
			c.getItems().deleteItem(3024, 1);
			c.getItems().deleteItem(12155, 1);
			c.getItems().addItem(21403, 1);
			c.lunarDiplomacy = 4;
			c.nextChat = 0;	
			break;
		case 3448: //Meteora
			sendPlayerChat1(9830, "I have the medicine you needed!");
			c.nextChat = 3449;
			break;	
		case 3449:
			sendNpcChat3("AHH, thank you so much!!", "you can't believe how happy i am right now!", "I'll give this to my villains right away!", 4515, "Meteora");
			c.nextChat = -1;
			c.lunarDiplomacy = 5;
			c.getItems().deleteItem(21403, 1);
			c.getPA().movePlayer(2079, 3902, 4);
			break;	
		case 3451:
			sendNpcChat3("I really appreciate what you have done for us", "now my villains are free of the illness!", "Now theres just one more thing i need help with..", 4515, "Meteora");
			c.nextChat = 3452;
			break;	
		case 3452:
			sendPlayerChat1(9830, "Alright, and what could that be?");
			c.nextChat = 3453;
			break;	
		case 3453:
			sendNpcChat3("The cause of all this, the evil monster whos behind it all", "I don't know much about it but the adventurer who has seen him", "Is located in the <col=255>Chaos Dungeon</col> you should talk to him.", 4515, "Meteora");
			c.nextChat = 3454;
			break;	
		case 3454:
			sendPlayerChat1(9830, "Okey, i'll go talk to him!");
			c.lunarDiplomacy = 6;
			c.nextChat = -1;
			break;	
		case 3455: //Adventurer
			sendPlayerChat2(9830, "Hello there adventurer, i've heard you've seen the entrance", "To the monster who brought the illness to Lunar Isle");
			c.nextChat = 3456;
			break;	
		case 3456:
			sendNpcChat3("Hi there, yes a couple of weeks ago i made this find", "This big guy looks real evil and he scares me", "And i have no idea what to do about him", 798, "Velrak the explorer");
			c.nextChat = 3457;
			break;	
		case 3457:
			sendPlayerChat1(9830, "Can you take me to him?");
			c.nextChat = 3458;
			break;
		case 3458:
			sendNpcChat3("Are you serious?!", "This guy is massive and he will split you into half", "Are you sure you want to go?", 798, "Velrak the explorer");
			c.nextChat = 3459;
			break;	
		case 3459:
			sendOption2("Yes i want to go, i'm not risking anything", "If he is that scary, i'm not going!");
			c.dialogueAction = 3457;
			c.dialogueId = 3457;
			break;	
		case 3460:
			sendPlayerChat1(9830, "Yes i want to go, i'm not risking anything");
			c.nextChat = 3461;
			break;
		case 3461:
			sendNpcChat3("*swallows* ugh, okey if you really want to", "But i'm not coming with you!!", "Good luck, here we go", 798, "Velrak the explorer");
			c.nextChat = -1;
			c.lunarDiplomacy = 7;
			c.getPA().movePlayer(3113, 5528, 0);	
			break;	
		case 3463: //Meteora
			sendPlayerChat1(9830, "I'm back, and i've killed the monster who caused this!");
			c.nextChat = 3464;
			break;	
		case 3464:
			sendNpcChat3("Did you?!?!", "You are a true hero "+ Misc.optimizeText(c.playerName) +"!", "Here, please accept my reward", 4515, "Meteora");
			c.nextChat = -1;
			c.lunarDiplomacy = 9;
			c.getQuest().completeQuest(c, 1);
			break;	
			
		//Archaeologist	
		case 3465:
			sendPlayerChat3(9830, "Hello there Archaeologist!", "I've heard from a safe source that you have lost", "Some important diamonds, am i correct?");
			c.nextChat = 3466;
			break;	
		case 3466:
			sendNpcChat3("Well hello there adventurer, yes that is infact correct", "And it has been sometime now but i'm not strong enough", "To beat these bosses sadly enough..", 1918, "Archaeologist");
			c.nextChat = 3467;	
			break;	
		case 3467:
			sendPlayerChat1(9830, "Maybe i could help you with that? I think i'm strong enough");
			c.nextChat = 3468;
			break;	
		case 3468:
			sendNpcChat2("Would you really want to help me with that?!", "I would be so glad if so were the case!", 1918, "Archaeologist");
			c.nextChat = 3469;	
			break;		
		case 3469:
			sendPlayerChat2(9830, "I'm not talking randomly my friend, i really can help you", "So what do you need me to do?");
			c.nextChat = 3470;
			break;	
		case 3470:
			sendNpcChat4("Very appreciated! Ok this is the deal", "Theres 4 different bosses that stole my diamonds", "And separated with 1 diamond each", "But i know where they are located so..", 1918, "Archaeologist");
			c.nextChat = 3471;	
			break;		
		case 3471:
			sendNpcChat4("The thing i need help with is to kill them", "So i can get my beloved diamonds back!", "The first one is called <col=255>Kamil</col>", "Do you want to go there now?", 1918, "Archaeologist");
			c.nextChat = 3472;	
			break;	
		case 3472:
			sendOption2("Yes, let's get this Kamil guy!", "No, not right now..");
			c.dialogueAction = 3472;
			c.dialogueId = 3472;
			break;	
		case 3473:
			sendPlayerChat1(9830, "I have killed <col=255>Kamil</col> now and got the <col=255>Ice Diamond");
			c.nextChat = 3474;
			break;	
		case 3474:
			sendNpcChat2("Thank you very much!", "The next one is Dessous, are you ready?", 1918, "Archaeologist");
			c.getItems().deleteItem2(4671, 1);
			c.nextChat = 3475;	
			break;	
		case 3475:
			sendOption2("Yes, let's get this Dessous guy!", "No, not right now..");
			c.dialogueAction = 3475;
			c.dialogueId = 3475;
			break;		
		case 3476:
			sendPlayerChat1(9830, "I have killed <col=255>Dessous</col> now and got the <col=255>Shadow Diamond");
			c.nextChat = 3477;
			break;	
		case 3477:
			sendNpcChat2("Thank you very much!", "The next one is Fareed, are you ready?", 1918, "Archaeologist");
			c.getItems().deleteItem2(4673, 1);
			c.nextChat = 3478;	
			break;	
		case 3478:
			sendOption2("Yes, let's get this Fareed guy!", "No, not right now..");
			c.dialogueAction = 3478;
			c.dialogueId = 3478;
			break;	
		case 3479:
			sendPlayerChat1(9830, "I have killed <col=255>Fareed</col> now and got the <col=255>Smoke Diamond");
			c.nextChat = 3490;
			break;	
		case 3490:
			sendNpcChat2("Thank you very much!", "The next one is Damis, are you ready?", 1918, "Archaeologist");
			c.getItems().deleteItem2(4672, 1);
			c.nextChat = 3491;	
			break;	
		case 3491:
			sendOption2("Yes, let's get this Damis guy!", "No, not right now..");
			c.dialogueAction = 3481;
			c.dialogueId = 3481;
			break;	
		case 3492:
			sendPlayerChat1(9830, "I have killed <col=255>Damis</col> now and got the <col=255>Blood Diamond");
			c.nextChat = 3493;
			break;	
		case 3493:
			sendNpcChat2("Thank you very much!", "Please take this reward!", 1918, "Archaeologist");
			c.getItems().deleteItem2(4670, 1);
			c.nextChat = -1;
			c.DT = 6;
			c.getQuest().completeQuest(c, 2);
			break;	

		//Santas little helper
		case 3494:
			sendPlayerChat1(9830, "Hello there chubby! Are you the santa everyones been talking about?");
			c.nextChat = 3495;
			break;	
		case 3495:
			sendNpcChat3("Ho.. yes infact i am", "But i don't feel like talking right now", "I've let everyone down and ruined Christmas..", 9400, "Santa");
			c.nextChat = 3496;
			break;
		case 3496:
			sendPlayerChat2(9830, "You what?! What are you saying!", "What has happend?");
			c.nextChat = 3497;
			break;
		case 3497:
			sendNpcChat3("Well, i came down here with my raindeers about", "a week ago and someone has already found my lair", "and somehow snuck out 3 presents while i was sleeping!", 9400, "Santa");
			c.nextChat = 3498;
			break;
		case 3498:
			sendPlayerChat2(9830, "That sounds really nuts!", "Who would even do something like that?!?");
			c.nextChat = 3500;
			break;
		case 3500:
			sendNpcChat2("I have a suspect who might have caused this!", "But i can't do this by myself as i am really busy", 9400, "Santa");
			c.nextChat = 3501;
			break;
		case 3501:
			sendOption2("Help Santa", "Walk away");
			c.dialogueAction = 3501;
			break;
		case 3502:
			sendPlayerChat1(9830, "I am currently free, i can help you!");
			c.nextChat = 3503;
			break;
		case 3503:
			sendNpcChat2("Really? That would mean so much to alot of people!", "Do you want me to teleport you to the christmas land?", 9400, "Santa");
			c.nextChat = 3504;
			break;
		case 3504:
			sendOption2("Yes teleport me please!", "No i don't want to do this");
			c.dialogueAction = 3504;
			break;
		case 3505:
			sendPlayerChat1(9830, "Yes teleport me there please!");
			c.nextChat = 3506;
			break;
		case 3506:
			sendNpcChat1("Alright hold on tight, here we go!", 9400, "Santa");
			c.nextChat = 3507;
			break;
		case 3507:
			c.getPA().movePlayer(3299, 4510, 0);
			sendNpcChat3("Yes i made it! I've blocked santas teleport!", "But wait, you are not santa?", "Who are you?!", 7955, "Thief");
			c.nextChat = 3508;
			break;
		case 3508:
			sendPlayerChat3(9830, "What? Why would you block santas teleport?", "I'm "+ Misc.optimizeText(c.playerName) +", santa sent me", "To get hes presents back and save christmas!");
			c.nextChat = 3509;
			break;
		case 3509:
			sendNpcChat4("I have never liked santa very much in my days", "So i am simply going to make him miserable!", "HAHA, are you gonna take the present from me?", "Good luck with that", 7955, "Thief");
			c.nextChat = 3510;
			break;
		case 3510:
			sendPlayerChat3(9830, "Why would you do that? That's a work of an idiot!", "Just tell me where the presents are and i will let you go", "without a scratch.");
			c.nextChat = 3511;
			break;
		case 3511:
			sendNpcChat4("I don't like him!", "I have burried down MY present in here somewhere haha!", "But goodluck finding it!", "Hahahahahaha!", 7955, "Thief");
			c.nextChat = 3512;
			break;
		case 3512:
			sendPlayerChat1(9830, "Then i guess i'll have to dig it up!");
			c.nextChat = -1;
			break;
		case 3513:
			sendPlayerChat1(9830, "I'm back i have one of the three presents!");
			c.nextChat = 3514;
			break;
		case 3514:
		if (c.getItems().playerHasItem(15420, 1)) {
			sendNpcChat2("Hoho, thank you very much! Now we only got two more to obtain!", "Would you like to go to the secound suspect?", 9400, "Santa");
			c.getItems().deleteItem(15420, 1);
			c.nextChat = 3515;
			c.santasHelp = 2;
		} else {
			sendNpcChat1("You don't have my present?", 9400, "Santa");
		}
			break;
		case 3515:
			sendOption2("Yes teleport me please!", "No i don't want to do this");
			c.dialogueAction = 3515;
			break;
		case 3516:
			sendPlayerChat1(9830, "Yes teleport me there please!");
			c.nextChat = 3517;
			break;
		case 3517:
			sendNpcChat1("Alright hold on tight, here we go!", 9400, "Santa");
			c.nextChat = 3518;
			break;
		case 3518:
			c.getPA().movePlayer(2909, 5473, 0);
			sendNpcChat3("HA! gotcha!", "But wait, you are not santa?", "Who are you?!", 13656, "Snow Imp");
			c.nextChat = 3519;
			break;
		case 3519:
			sendPlayerChat3(9830, "Wait what?", "I'm "+ Misc.optimizeText(c.playerName) +", santa sent me", "To get hes presents back and save christmas!");
			c.nextChat = 3520;
			break;
		case 3520:
			sendNpcChat4("I'm trying to capture santa, but i just got you..", "You want MY present? HA! I've hid it in something", "In the end of one of these mazes!", "Goodluck finding it HA!", 13656, "Snow Imp");
			c.nextChat = 3521;
			break;
		case 3521:
			sendPlayerChat1(9830, "Well that shouldn't be too hard!");
			c.nextChat = -1;
			break;
		case 3522:
			sendPlayerChat1(9830, "I'm back and i have the secound of the three presents!");
			c.nextChat = 3523;
			break;
		case 3523:
		if (c.getItems().playerHasItem(15420, 1)) {
			sendNpcChat2("Hoho, thank you very much! Now we only got one more to obtain!", "Would you like to go to the third suspect?", 9400, "Santa");
			c.getItems().deleteItem(15420, 1);
			c.nextChat = 3524;
			c.santasHelp = 4;
		} else {
			sendNpcChat1("You don't have my present?", 9400, "Santa");
		}
			break;
		case 3524:
			sendOption2("Yes teleport me please!", "No i don't want to do this");
			c.dialogueAction = 3524;
			break;
		case 3525:
			sendPlayerChat1(9830, "Yes teleport me there please!");
			c.nextChat = 3526;
			break;
		case 3526:
			sendNpcChat1("Alright hold on tight, here we go!", 9400, "Santa");
			c.nextChat = 3527;
			break;
		case 3527:
			c.getPA().movePlayer(2658, 10385, 0);
			sendNpcChat3("There you are!", "But wait, you are not santa?", "Who are you?!", 8668, "Penguin");
			c.nextChat = 3528;
			break;
		case 3528:
			sendPlayerChat3(9830, "Wait what?", "I'm "+ Misc.optimizeText(c.playerName) +", santa sent me", "To get hes presents back and save christmas!");
			c.nextChat = 3529;
			break;
		case 3529:
			sendNpcChat4("Hmm.. Nothing!", "But wait, you want MY present?", "I have locked it in one of these crates!", "But you must search them in the correct order, good luck haha", 8668, "Penguin");
			c.nextChat = 3530;
			break;
		case 3530:
			sendPlayerChat1(9830, "I can do that!");
			c.nextChat = -1;
			break;
		case 3531:
			sendPlayerChat1(9830, "I'm back and i have the last present!");
			c.nextChat = 3532;
			break;
		case 3532:
		if (c.getItems().playerHasItem(15420, 1)) {
			sendNpcChat2("Hoho, thank you very much! You have now saved Christmas for everyone!", "Here please accept a reward!", 9400, "Santa");
			c.getItems().deleteItem(15420, 1);
			c.nextChat = 3533;
			c.santasHelp = 9;
		} else {
			sendNpcChat1("You don't have my present?", 9400, "Santa");
		}
			break;
		case 3533:
			sendOption2("Christmas Ghost Set", "A Snow Globe");
			c.dialogueAction = 3533;
			break;
			
		//Teleportation
		case 4000:
			sendNpcChat4("Hello "+ Misc.optimizeText(c.playerName) +", i'm the sailor", "I can get you anywhere you want in this world.", "From everything to small creatures to large bosses!", "So where would you like to go?", 1304, "Sailor");
			c.nextChat = 4001;
			break;
		case 4001:
			sendOption5("Monsters", "Minigames", "PK Locations", "Skilling Area", " ");
			c.dialogueAction = 4001;
			c.dialogueId = 4001;
			break;

		//* Monsters locations
		case 4002:
			sendOption5("Low Level Monsters", "Slayer Locations", "Medium Level Monsters", "Bosses", "Previous Page");
			c.dialogueAction = 4002;
			c.dialogueId = 4002; //Done
			break;
		case 4003:
			sendOption5("Rock Crabs", "Yaks", "Knight's Hall", "Desert Snakes", "Previous Page");
			c.dialogueAction = 4003;
			c.dialogueId = 4003;
			break;
		case 4004:
			sendOption5("Brimhaven Dungeon", "Taverly Dungeon", "Slayer Tower", "Dagannoth Cave", "Previous Page");
			c.dialogueAction = 4004;
			c.dialogueId = 4004;
			break;
		case 4005:
			sendOption5("Strykewyrms & Ice Cave", "Tormented Demons", "Frost Dragon Cave", "Cave Of Mutations", "Bandit Camp");
			c.dialogueAction = 4005;
			c.dialogueId = 4005;
			break;
		case 4006:
			sendOption5("Corporeal Beast", "Nex", "Godwars Dungeon", "Chaos Elemental", "Avatar Of Destruction @bla@(@red@NEW@bla@)");
			c.dialogueAction = 4006;
			c.dialogueId = 4006;
			break;
		case 4007:
			sendOption5("Ice Strykewyrms", "Jungle Strykewyrms", "Desert Strykewyrms", "Ice Cave", "Previous Page");
			c.dialogueAction = 4007;
			c.dialogueId = 4004;
			break;
		//* End of Monsters Locations
		//* Minigame Locations
		case 4008:
			sendOption5("Pest Control", "Barrows", "Duel Arena", "Tz-Haar Cave", "Previous Page");
			c.dialogueAction = 4008;
			c.dialogueId = 4008;
			break;
		//* End of Minigame Locations

		//* Banker informs about PIN

		case 4050:
			sendNpcChat2("Would you like to set a bank PIN?", "It will be used to keep your account safe from others!", 494, "Banker");
			c.nextChat = 4051;
			break;	
		case 4051:
			sendOption2("Yes i would like to set a bank PIN", "No i do not want a bank PIN");
			c.dialogueAction = 4051;
			break;

		case 4052:
			sendNpcChat2("Would you like to remove your bank PIN?", "If so your account will again be unsecure!", 494, "Banker");
			c.nextChat = 4053;
			break;	
		case 4053:
			sendOption2("Yes i would like to remove my bank PIN", "No i do not want to remove my bank PIN");
			c.dialogueAction = 4053;
			break;

		case 4054:
			sendOption2("Dismiss Familiar", "No");
			c.dialogueAction = 4054;
			break;

		//Skill Task Flynn
		case 5100:
			sendNpcChat2("Hello my fellow skiller!", "What would you like to do today?", 580, "Flynn");
			c.nextChat = 5101;
			break;	
		case 5101:
			sendOption4("I would like to have a skill task!", "What is my current skill task?", "I have the items for my skill task!", "I would like to reset my skill task!");
			c.dialogueAction = 5001;
			c.dialogueId = 5001;
			break;	
		case 5102:
			sendPlayerChat1(9830, "Alright i'll be back soon with your items!");
			c.nextChat = -1;
			break;	
		case 5103:
			sendNpcChat1("I currently need <col=255>"+c.skillAmount+" " + game.item.Item.getItemName(c.skillTask) + "</col>.", 580, "Flynn");
			c.nextChat = 5102;
			break;	
		case 5104:
			sendNpcChat1("I currently need <col=255>"+c.skillAmount1+" " + game.item.Item.getItemName(c.skillTask1) + "</col>.", 580, "Flynn");
			c.nextChat = 5102;
			break;	
		case 5105:
			sendNpcChat1("I currently need <col=255>"+c.skillAmount2+" " + game.item.Item.getItemName(c.skillTask2) + "</col>.", 580, "Flynn");
			c.nextChat = 5102;
			break;	
		case 5106:
			sendNpcChat1("I currently need <col=255>"+c.skillAmount+" " + game.item.Item.getItemName(c.skillTask) + "</col> and <col=255>"+c.skillAmount1+" " + game.item.Item.getItemName(c.skillTask1) + "</col>", 580, "Flynn");
			c.nextChat = 5102;
			break;	
		case 5107:
			sendNpcChat1("I currently need <col=255>"+c.skillAmount1+" " + game.item.Item.getItemName(c.skillTask1) + "</col> and <col=255>"+c.skillAmount2+" " + game.item.Item.getItemName(c.skillTask2) + "</col>", 580, "Flynn");
			c.nextChat = 5102;
			break;	
		case 5108:
			sendStatement("You hand Flynn the <col=255>"+c.skillAmount+" " + game.item.Item.getItemName(c.skillTask)+"</col>.");
			c.nextChat = 5109;
			break;	
		case 5109:
			sendNpcChat1("Thank you very much, here accept these 3 Skill Points!", 580, "Flynn");
			c.nextChat = -1;
			break;	
		case 5110:
			sendStatement("You hand Flynn the <col=255>"+c.skillAmount1+" " + game.item.Item.getItemName(c.skillTask1)+"</col>.");
			c.nextChat = 5109;
			break;	
		case 5111:
			sendStatement("You hand Flynn the <col=255>"+c.skillAmount2+" " + game.item.Item.getItemName(c.skillTask2)+"</col>.");
			c.nextChat = 5109;
			break;
		case 5112:
			sendStatement("You hand Flynn the <col=255>"+c.skillAmount+" " + game.item.Item.getItemName(c.skillTask)+"</col> and <col=255>"+c.skillAmount1+" " + game.item.Item.getItemName(c.skillTask1)+"</col>.");
			c.nextChat = 5114;
			break;	
		case 5113:
			sendStatement("You hand Flynn the <col=255>"+c.skillAmount1+" " + game.item.Item.getItemName(c.skillTask1)+"</col> and <col=255>"+c.skillAmount2+" " + game.item.Item.getItemName(c.skillTask2)+"</col>.");
			c.nextChat = 5114;
			break;	
		case 5114:
			sendNpcChat1("Thank you very much, here accept these 8 Skill Points!", 580, "Flynn");
			c.nextChat = -1;
			break;	

		//Title manager
		case 5115:
			sendNpcChat1("Hello there adventurer, what would you like to do today?", 884, "Title Manager");
			c.nextChat = 5116;
			break;	
		case 5116:
			sendOption4("Why are you here?", "I'd like to unlock a title", "I'd like to change my title", "Nothing..");
			c.dialogueAction = 5116;
			break;	
		case 5117:
			sendNpcChat2("The reason i am here is to hand out titles for those", "Who've earned them and like to show them off.", 884, "Title Manager");
			c.nextChat = 5116;
			break;	 
		case 5118: //Novice
			sendOption4("Unlock [Nov. PK]", "Unlock [Nov. PVM]", "", "More to come...");
			c.dialogueAction = 5118;
			break;	 
		case 5119: //Expert
			sendOption4("Unlock [Exp. PK]", "Unlock [Exp. PVM]", "", "More to come...");
			c.dialogueAction = 5119;
			break;	 
		case 5120: //Legend
			sendOption4("Unlock [Leg. PK]", "Unlock [Leg. PVM]", "", "More to come...");
			c.dialogueAction = 5120;
			break;	

		case 5121: //Novice
			sendOption4("Change to [Nov. PK]", "Change to [Nov. PVM]", "", "More to come...");
			c.dialogueAction = 5121;
			break;	 
		case 5122: //Expert
			sendOption4("Change to [Exp. PK]", "Change to [Exp. PVM]", "", "More to come...");
			c.dialogueAction = 5122;
			break;	 
		case 5123: //Legend
			sendOption4("Change to [Leg. PK]", "Change to [Leg. PVM]", "", "More to come...");
			c.dialogueAction = 5123;
			break;	

		//Charming imp
		case 5300:
			sendNpcChat4("Hello, i'm the Charming Imp", 
				"What i can do for you is the following", 
				"Collect Charm drops directly into your inventory or", 
				"Turn all charm drops into summoning XP, whatcha wanna do?", 
				708, "Charming Imp");
				c.nextChat = 5301;
			break;

		case 5301: //Choose
			sendOption2("Collect Charms", "Turn Charms Into XP");
			c.dialogueAction = 5301;
			break;	

		case 5302:
			sendNpcChat2("I will now collect all charm drops for you", 
				"And place them in your inventory.", 
				708, "Charming Imp");
				c.nextChat = -1;
			break;

		case 5303:
			sendNpcChat2("I will now collect all charm drops for you", 
				"And turn them into summoning XP.", 
				708, "Charming Imp");
				c.nextChat = -1;
			break;

		case 5304:
			sendNpcChat2("I can not yet supply you with this option", 
				"But you may check back with me another time!", 
				708, "Charming Imp");
				c.nextChat = -1;
			break;

		case 5400:
			sendNpcChat2("Hello there, would you like me to bring you to", 
				"The island of Ape Atoll?", 
				1469, "Monkey Minder");
				c.nextChat = 5401;
			break;
		case 5401: //Choose
			sendOption2("Yes", "No");
			c.dialogueAction = 5401;
			break;	


		}
	}

	/**
	 * Information Box
	 */

	public void sendNpcChat1(String s, int ChatNpc, String name) {
		c.getPA().sendFrame200(4883, 9847);
		c.getPA().sendFrame126(name, 4884);
		c.getPA().sendFrame126(s, 4885);
		c.getPA().sendFrame75(ChatNpc, 4883);
		c.getPA().sendFrame164(4882);
	}

	public void sendPlayerChat1(int face, String s) {
		c.getPA().sendFrame200(969, face);
		c.getPA().sendFrame126(Misc.optimizeText(c.playerName), 970);
		c.getPA().sendFrame126(s, 971);
		c.getPA().sendFrame185(969);
		c.getPA().sendFrame164(968);
	}

	public void sendPlayerChat2(int face, String s, String s1) {
		c.getPA().sendFrame200(974, face);
		c.getPA().sendFrame126(Misc.optimizeText(c.playerName), 975);
		c.getPA().sendFrame126(s, 976);
		c.getPA().sendFrame126(s1, 977);
		c.getPA().sendFrame185(974);
		c.getPA().sendFrame164(973);
	}

	public void sendPlayerChat3(int face, String s, String s1, String s2) {
		c.getPA().sendFrame200(980, face);
		c.getPA().sendFrame126(Misc.optimizeText(c.playerName), 981);
		c.getPA().sendFrame126(s, 982);
		c.getPA().sendFrame126(s1, 983);
		c.getPA().sendFrame126(s2, 984);
		c.getPA().sendFrame185(980);
		c.getPA().sendFrame164(979);
	}

	public void sendPlayerChat4(int face, String s, String s1, String s2,
			String s3) {
		c.getPA().sendFrame200(987, face);
		c.getPA().sendFrame126(Misc.optimizeText(c.playerName), 988);
		c.getPA().sendFrame126(s, 989);
		c.getPA().sendFrame126(s1, 990);
		c.getPA().sendFrame126(s2, 991);
		c.getPA().sendFrame126(s3, 992);
		c.getPA().sendFrame185(987);
		c.getPA().sendFrame164(986);
	}

	public void sendNpcChat2(String s, String s1, int ChatNpc, String name) {
		c.getPA().sendFrame200(4888, 9847);
		c.getPA().sendFrame126(name, 4889);
		c.getPA().sendFrame126(s, 4890);
		c.getPA().sendFrame126(s1, 4891);
		c.getPA().sendFrame75(ChatNpc, 4888);
		c.getPA().sendFrame164(4887);
	}

	public void sendNpcChat3(String s, String s1, String s2, int ChatNpc,
			String name) {
		c.getPA().sendFrame200(4894, 9847); // Was 591
		c.getPA().sendFrame126(name, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(ChatNpc, 4894);
		c.getPA().sendFrame164(4893);
	}

	public void sendNpcChat5(String s, String s1, String s2, int ChatNpc,
			String name) {
		c.getPA().sendFrame201(4894, 9847); // Was 591
		c.getPA().sendFrame126(name, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(ChatNpc, 4894);
		c.getPA().sendFrame164(4893);
	}

	private void sendNpcChat4(String s, String s1, String s2, String s3,
			int ChatNpc, String name) {
		c.getPA().sendFrame200(4901, 9847);
		c.getPA().sendFrame126(name, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4900);
	}

	/**
	 * Options
	 */
	public void sendOption2(final String s, final String s1) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame164(2459);
	}

	public void sendOption4(final String s, final String s1, final String s2,
			final String s3) {
		c.getPA().sendFrame126("Select an Option", 2481);
		c.getPA().sendFrame126(s, 2482);
		c.getPA().sendFrame126(s1, 2483);
		c.getPA().sendFrame126(s2, 2484);
		c.getPA().sendFrame126(s3, 2485);
		c.getPA().sendFrame164(2480);
	}

	public void sendOption5(final String s, final String s1, final String s2,
			final String s3, final String s4) {
		c.getPA().sendFrame126("Select an Option", 2493);
		c.getPA().sendFrame126(s, 2494);
		c.getPA().sendFrame126(s1, 2495);
		c.getPA().sendFrame126(s2, 2496);
		c.getPA().sendFrame126(s3, 2497);
		c.getPA().sendFrame126(s4, 2498);
		c.getPA().sendFrame164(2492);
	}

	/**
	 * Statements
	 */
	public void sendStartInfo(final String text, final String text1,
			final String text2, final String text3, final String title) {
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(text, 6181);
		c.getPA().sendFrame126(text1, 6182);
		c.getPA().sendFrame126(text2, 6183);
		c.getPA().sendFrame126(text3, 6184);
		c.getPA().sendFrame164(6179);
	}

	/**
	 * Npc Chatting
	 */
	public void sendStatement(final String s) { // 1 line click here to
		// continue chat box
		// interface
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}

        public void ssm2(String text1, String text2, int item1, int item2) {
		c.getPA().sendFrame126(text1, 6232);
		c.getPA().sendFrame126(text2, 6233);
		c.getPA().sendFrame246(6235, 170, item1);
		c.getPA().sendFrame246(6236, 170, item2);
		c.getPA().sendFrame164(6231);
	}
	
	public void ssm1(String text, int item) {
		c.getPA().sendFrame126(text, 308);
		c.getPA().sendFrame246(307, 200, item);
		c.getPA().sendFrame164(306);
	}

	public void sendInformationBox(String title, String line1, String line2, String line3, String line4) {// check
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(line1, 6181);
		c.getPA().sendFrame126(line2, 6182);
		c.getPA().sendFrame126(line3, 6183);
		c.getPA().sendFrame126(line4, 6184);
		c.getPA().sendFrame164(6179);
	}

}
