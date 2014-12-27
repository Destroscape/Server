package server.model.players;

import server.Server;
import server.model.players.DialogueAnimation;
import server.model.players.PlayerAssistant;
import server.model.players.PlayerHandler;

public class DialogueHandler {

	private Client c;
	
	public DialogueHandler(Client client) {
		this.c = client;
	}
	
	/**
	 * Handles all talking
	 * @param dialogue The dialogue you want to use
	 * @param npcId The npc id that the chat will focus on during the chat
	 */
	public void sendDialogues(int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch(dialogue) {
		case 998:
		sendStatement("Welcome to Destroscape, if you ever need something, let us know!");
		c.nextChat = 0;
		break;
		case 216:
		sendNpcChat4("Hello, welcome to the holy army of Destroscape.", "The soldiers drop Mystery Boxes.", "It sure is a good day to kill the army.", "When you have killed one, i will respawn them for you.", c.talkingNpc, "High Priest");
		c.nextChat = 0;
		break;
		case 1599:
		sendOption2("Teleport me to Lawless", "Don't teleport me.");
		c.nextChat = 0;
		c.teleAction = 1599;
		break;
		case 3515:
		sendOption2("Obtain Completionist cape", "Don't Obtain Completionist cape");
		c.dialogueAction = 3516;
		break;
		case 1660:
		sendNpcChat4("Hello, i am the SkillCape store.", "You can buy any cape, but you will need lvl 99 in that stat.", "So go on and train, i will wait for you.", "Good luck young adventurer.", c.talkingNpc, "Skillcape shopkeeper");
					c.nextChat = 1661;
		break;
		case 1661:
		sendNpcChat2("Now, Dear adventurer,", "Do you wish to see my shop?", c.talkingNpc, "Skillcape shopkeeper");
		c.nextChat = 1662;
		break;
		case 1662:
		sendOption2("Yes, i do.", "No, thank you.");
		c.teleAction = 1662;
		c.nextChat = 0;
		break;
		case 1399:
		sendNpcChat4("Hello, i am the shopkeeper of the skills.", "I sell things from herblore, to things for woodcutting.", "There's also skills that don't require my help, like agility.", "Anyways, what shop would you like to see?", c.talkingNpc, "Bob");
		c.nextChat = 1400;
		break;
		case 1400:
		sendOption4("Regular Skills Shop", "Herblore Shop", "Summoning shop 1", "Summoning shop 2");
		c.teleAction = 1399;
		c.nextChat = 0;
		break;
		case 5757:
			sendNpcChat2("Heyah papi, i am the creator of fine weaponry.", "Do you wish to see what i have in stock, laddeh?", c.talkingNpc, "Shopkeeper");
			c.nextChat = 5792;
		break;
		case 5792:
		sendOption4("Master Shop", "PVMing store", "General Store", "No thank you.");
		c.teleAction = 5792;
		c.nextChat = 0;
		break;
		case 5758:
			sendNpcChat4("Hello.", "How are you doing?", "I sell items that can train up your archery skill.", "And ofcourse the magic skill.", c.talkingNpc, "Shopkeeper");
			c.nextChat = 5791;
		break;
		case 5791:
			sendNpcChat2("In return i ofcourse, ask a small amount of coins.", "What shop do you want to see?", c.talkingNpc, "Shopkeeper");
			c.nextChat = 5793;
		break;
		case 5793:
		sendOption2("The archery store", "The magic store");
		c.nextChat = 0;
		c.teleAction = 5791;
		break;
		case 5759:
			sendNpcChat4("Hello, i am one of the shop keepers of Destroscape,", "Would you like to see what i have in stock?", "Then right click me, and select one of the shops.", "Good luck, young adventurer.", c.talkingNpc, "Skilling shopkeeper");
			c.nextChat = 0;
		break;
		case 1336:
			sendNpcChat4("Donating& Voting really helps Destroscape.", "If you wish to find out why, please visit our website.", "It can be found at http://destroscape.com/", "Thanks you.", c.talkingNpc, "Lawless");
		c.nextChat = 1337;
		break;
		case 1337:
		sendOption2("@gre@Visit the website.", "@red@Do not visit the website.");
		c.teleAction = 1335;
		c.nextChat = 0;
		break;
		case 1338:
                        sendStatement("In order to let your new loyalty title work, you will need to relog.");
                        c.nextChat = 1339;
                break;
				case 1339:
				sendOption2("@gre@Yes, i want to relog now.", "@red@No, i do not want to relog now.");
				c.nextChat = 0;
				c.dialogueAction = 1338;
				break;
		case 110:
			sendNpcChat4("Hello, i am the RuneCrafting Mage", "Would you like to teleport to the runecrafting locations?", "I could show you the way.", "Good luck, young adventurer.", c.talkingNpc, "Runecrafting Mage");
			c.nextChat = 17;
		break;
		case 4439:
			sendNpcChat4("Hello, i am the Points-Npc.", "I will explain you how the points we got work.", "PK points can be earned by killing a player.", "Thats simply it about the PK points, haha.", c.talkingNpc, "Destroscape Points");
			c.nextChat = 1350;
		break;
		case 1350:
		sendOption4("Vote Store", "Donator Store", "PK Points Store", "Level Points Store");
		c.teleAction = 1359;
		c.nextChat = 0;
		break;
		case 360:
			sendNpcChat4("Excuse me sir, can you help us?", "We can't seem to find our parents,", "They were supposed to be back hours ago!", "Please could you help us look for them?", c.talkingNpc, "Wilough");
			c.nextChat = 361;
		break;
		
		case 361:
			sendPlayerChat1("When was the last time you spoke with them?");
			c.nextChat = 362;
		break;

                case 362:
			sendNpcChat2("Well, they said they were taking Uncle Mizgog ", 
			"to perform a ritual in Taverley.", c.talkingNpc, "Wilough");
			c.nextChat = 363;
		break;

		case 363:
			sendPlayerChat2("Ok, but don't you suppose they're just late?", "I think you should wait a while...");
			c.nextChat = 364;
		break;

                case 364:
			sendNpcChat3("You don't understand, " + c.playerName + ", they're", 
			" never late!", "Something must be up! *sniffle*", c.talkingNpc, "Wilough");
			c.nextChat = 365;
		break;

		case 365:
			sendOption2("Okay, I'll help you", "Get lost, kid. I'm far too busy.");
			c.dialogueAction = 90;
		break;

		case 366:
			sendPlayerChat2("Okay, I'll help you. ", "Wait here until I return. Dont wander off!");
			c.nextChat = 368;
		break;
		case 367:
			sendPlayerChat1("Get lost kid, I'm far too busy.");
			c.nextChat = 0;
		break;

                case 368:
			sendNpcChat2("*sniffle* Thank you very much!", "Please hurry! *sniffle*",c.talkingNpc, "Wilough");
			c.nextChat = 0;
		break;

                case 369:
			sendNpcChat2("Please hurry back with my parents!", "*sniffle* Please!",c.talkingNpc, "Wilough");
			c.nextChat = 0;
		break;

                case 370:
			sendNpcChat2("MIZGOG, where are you!", "Kardia, have you found him?",c.talkingNpc, "Hadley");
			c.nextChat = 371;
		break;

                case 371:
			sendNpcChat2("No! I'm still looking, Hadley.", "He has to be around here somewhere!",c.talkingNpc, "Kardia");
			c.nextChat = 372;
		break;
		case 372:
			sendPlayerChat2("Uhm, excuse me? Are you the parents of", "Wilough and Philop?");
			c.nextChat = 373;
		break;

                case 373:
			sendNpcChat2("Wilough? Philop? Where are my boys!", "If you've layed one finger on them!",c.talkingNpc, "Hadley");
			c.nextChat = 374;
		break;

		case 374:
			sendPlayerChat4("No, no, no. You got this all wrong, I was", "sent here by Wilough and Philop!","I noticed them wondering around Canafis","looking very scared");
			c.nextChat = 375;
		break;

		case 375:
			sendNpcChat2("Oh no, Hadley! We forgot about our boys", "we're horrible parents! Waaaaaa",c.talkingNpc, "Kardia");
			c.nextChat = 376;
		break;

		case 376:
			sendNpcChat4("Relax, Kardia, we will sort this out.", "We're sorry, " + c.playerName + ", we were just", "performing a ritual that will convert my brother", "into a real wizard and it went horribly wrong!",c.talkingNpc, "Hadley");
			c.nextChat = 377;
		break;

		case 377:
			sendNpcChat2("Could you please pass a message on to our sons?", "We will be extremely greatful! " + c.playerName + ".", c.talkingNpc, "Hadley");
			c.nextChat = 378;
		break;

		case 378:
			sendOption2("Sure, what should I tell them?", "Sorry, I'm far too busy.");
			c.dialogueAction = 91;
		break;

		case 379:
			sendPlayerChat1("Sure, what should I tell them?");
			c.nomad += 1;
			c.nextChat = 383;
		break;

		case 380:
			sendPlayerChat1("Sorry, I'm far too busy.");
			c.nextChat = 0;
		break;


		case 381:
			sendNpcChat2("Please hurry and", " pass the message on to our sons!", c.talkingNpc, "Hadley");
			c.nextChat = 382;
		break;

		case 382:
			sendPlayerChat1("Ok, I'm going now.");
			c.nextChat = 0;
		break;

		case 383:
			sendNpcChat2("Tell them to keep calm and not to panic", " We will be home very soon", c.talkingNpc, "Hadley");
			c.nextChat = 384;
		break;

		case 384:
			sendNpcChat2("And please make sure nothing happens to them.", " Let them know, we will return when we find Mizgog.", c.talkingNpc, "Hadley");
			c.nextChat = 0;
		break;

		case 385:
			sendPlayerChat4("Wilough! I found your parents! Don't worry, they're safe", "your uncle Mizgog has gone missing so they", "are trying to find him. They will return when","they've found him.");
			c.nextChat = 386;
		break;

		case 386:
			sendPlayerChat1("They said you should keep calm and don't panic!");
			c.nextChat = 387;
		break;

		case 387:
			sendNpcChat2("Oh thank you, " + c.playerName + ". We were so worried!", "What happened to uncle Mizgog?", c.talkingNpc, "Wilough");
			c.nextChat = 388;
		break;

		case 388:
			sendPlayerChat2("Something went wrong during the ritual", " but they will find him!");
			c.nextChat = 389;
		break;

		case 389:
			sendNpcChat2("Can't you help my parents find him, the sooner", "they find him, the sooner they get home!", c.talkingNpc, "Hadley");
			c.nextChat = 390;
		break;

		case 390:
			sendOption2("Sure, I'll go help them find your uncle!", "Sorry kids, I've helped enough today.");
			c.dialogueAction = 92;
		break;

		case 391:
			sendPlayerChat2("Sure, I'll go help them find your uncle!", "I'll be back soon!");
			c.nomad += 1;
			c.nextChat = 0;
		break;

		case 392:
			sendPlayerChat1("Sorry kids, I've helped enough today.");
			c.nextChat = 0;
		break;

                case 393:
			sendNpcChat2("Please hurry and help my parents", "bring back my uncle!",c.talkingNpc, "Wilough");
			c.nextChat = 0;
		break;


                case 394:
			sendNpcChat2("Thanks for passing on the message, " + c.playerName + ".", "But what brings you back here?",c.talkingNpc, "Hadley");
			c.nextChat = 395;
		break;


		case 395:
			sendPlayerChat2("Wilough has asked me to help you find Mizgog","after all, three heads are better than too!");
			c.nextChat = 396;
		break;

                case 396:
			sendNpcChat3("Oh, thank you, " + c.playerName + ". We really", "appreciate the help! You can start by", "looking around white wolf mountain.", c.talkingNpc, "Hadley");
			c.nomad += 1;
			c.nextChat = 397;
		break;

		case 397:
			sendPlayerChat1("Ok, i'll be back soon.");
			c.nextChat = 0;
		break;


                case 398:
			sendNpcChat2("Have you found anything yet?", "Well have you?",c.talkingNpc, "Hadley");
			c.nextChat = 399;
		break;

		case 399:
			sendPlayerChat1("Not yet, I'm still looking.");
			c.nextChat = 0;
		break;

		case 400:
			sendStatement("You found a boot, you should return it to Hadley");
			c.nomad += 1;
                        c.nextChat = 0;
		break;

		case 401:
			sendStatement("You found something, but you don't have enough inventory space.");
                        c.nextChat = 0;
		break;

                case 402:
			sendPlayerChat1("Hadley! I found this boot just outside a cave entrance!");
			c.nextChat = 403;
		break;

		case 403:
			sendStatement("You hand the boot over to Hadley");
			c.nomad += 1;
			c.getItems().deleteItem(685, 28);
                        c.nextChat = 404;
		break;

                case 404:
			sendNpcChat3("Oh god no. This is Mizgog's boot!", ""+ c.playerName + ", you must go into the cave and ","bring back Mizgog, please!",c.talkingNpc, "Hadley");
			c.nextChat = 405;
		break;

		case 405:
			sendOption2("Ok, Hadley, I'll bring him back for you!", "A dark creepy cave? You must be out of your mind!");
			c.dialogueAction = 93;
		break;

                case 406:
			sendPlayerChat1("Ok, Hadley, I'll bring him back for you!");
			c.nomad += 1;
			c.nextChat = 407;
		break;

                case 407:
			sendNpcChat3("Oh thank you, " + c.playerName + "", "Make sure you take Food and Armour,","It could be dangerous in there!",c.talkingNpc, "Hadley");
			c.nextChat = 0;
		break;

                case 408:
			sendPlayerChat1("A dark creepy cave? You must be out of your mind!");
			c.nextChat = 0;
		break;

                case 409:
			sendNpcChat2("Please go and find my brother Mizgog.", "He must be down in the Cavern!",c.talkingNpc, "Hadley");
			c.nextChat = 0;
		break;

                case 410:
			sendPlayerChat1("Pssst, hey! Mizgog, quick, let's get out of here!");
			c.nextChat = 411;
		break;

                case 411:
			sendNpcChat2("You don't understand! He will never let us leave!", "Flee while you can!!!",c.talkingNpc, "Wizard_Mizgog");
			c.nextChat = 412;
		break;

                case 412:
			sendNpcChat2("YOU!! He is mine!!!", "RUN OR YOU SHALL DIE!!!",c.talkingNpc, "Bork");
			Server.npcHandler.spawnNpc(c, 7133, c.getX(), c.getY()-8, 0, 0, 400, 50, 600, 600, true, true);
			c.nomad = 8;
			c.nextChat = 0;
		break;

                case 413:
			sendNpcChat2("AHHHHHHHHHHHHH", "Helppppppppppppppppppp!!!!",c.talkingNpc, "Wizard_Mizgog");
			c.nextChat = 0;
		break;

                case 414:
			sendNpcChat2("Oh my! Thankyou so much!" + c.playerName + ",", "I'm FREE!!!!!",c.talkingNpc, "Wizard_Mizgog");
			c.nextChat = 0;
		break;

               case 415:
			sendNpcChat2("Thank you so much for rescuing my brother, " + c.playerName + ",", "Here take this reward!",c.talkingNpc, "Hadley");
			c.nomad += 1;
			c.getPA().sendFrame126("@gre@The Ritual", 29162);
			c.nextChat = 417;


		break;

                case 416:
			sendNpcChat2("Thanks for rescuing my uncle and", "bringing my parents back.",c.talkingNpc, "Wilough");
			c.nextChat = 0;
		break;




                case 417:
			c.getPA().addSkillXP(300000, 22);
			c.getItems().addItem(995, 15000000);
			c.getItems().addItem(18337, 1);
			c.getItems().addItem(19314, 1);
			c.nextChat = 0;
		c.getPA().sendFrame126("You have completed The Ritual!",12144);
		c.getPA().sendFrame126("300,000 Summoning Experience" ,12150);
		c.getPA().sendFrame126("Bork's Bone Crusher" ,12151);
		c.getPA().sendFrame126("15m Cash" ,12152);
		c.getPA().sendFrame126("Third-Age Druidic Wreath" ,12153);
		c.getPA().sendFrame126("" ,12154);
		c.getPA().sendFrame126("" ,12155);
		c.getPA().showInterface(12140);
		break;
		case 252:
			sendNpcChat4("H... h... help us.", "Please, someone! help us!.", "You, you please help us!", "The gods are back!", c.talkingNpc, "Weirdo");
			c.nextChat = 253;
		break;
		case 1349:
			sendNpcChat3("If there is anything else you have to know, contact our staff.", "There will be updates weekly, check the forums for this.", "Well you lad, ill be keep going, see you later.", c.talkingNpc, "Bob the fucking faggot builder");
			c.nextChat = 1350;
		break;
                case 253:
                sendNpcChat2("Will you please listen?", "Please?", c.talkingNpc, "Weirdo");
			c.nextChat = 254;
                break;
		case 254:
			sendNpcChat4("Thank you... the gods have attacked!", "Its a hell, you have to help us!", "Bandos, Armadyl, the gods are mad at eachother!", "We cannot hold the peace any longer!", c.talkingNpc, "Wise old Warrior");
			c.nextChat = 255;
		break;
		case 255:
			sendNpcChat4("For years, we have tried to defeat Bandos!", "He is just too strong!", "Theres nothing else we can do, help us!", "Please, let me teleport you to GodWars!", c.talkingNpc, "Wise old Warrior");
			c.nextChat = 256;
		break;
		case 256:
			sendNpcChat4("But i have to warn you, it is extremely dangerous!", "They are gods, dont forget this!", "The armour and weapons you get, you may keep.", "Will you teleport to GodWars?", c.talkingNpc, "Wise old Warrior");
			c.nextChat = 257;
		break;
		case 257:
			sendNpcChat4("Then right click me and select godwars", "Help ussssssssss", "We beg you, please!", "Good luck, young adventurer.", c.talkingNpc, "Wise old Warrior");
			c.nextChat = 0;
		break;
		case 4440:
			sendNpcChat4("And Destroscape Points can be earned by", "Leveling, Finishing a slayer task, or simply killing a player aswell.", "There will be points you earned, you can spend those in my shop.", "Good luck young adventurer.", c.talkingNpc, "Destroscape Points");
			c.nextChat = 4441;
		break;
                case 4441:
                sendOption3("Destroscape Points.", "Destroscape PK Points.", "Ah, nevermind");
                c.dialogueAction = 4441;
                break;
		case 4420:
			sendNpcChat4("Hello, i can sell you your capes,", "Everytime you earn 10 more levels in all skills,", "I can sell you another cape.", "Would you like to see your current capes?", 1294, "Milestone Seller");
			c.nextChat = 4421;
		break;
		case 4421:
				sendOption2("Yes, i would like to see my capes.", "No thank you, i have to go!");
				c.dialogueAction = 4421;
								c.nextChat = 0;
				break;
		case 4443:
			sendNpcChat4("Hello, i am one of the Destroscape Teleporters.", "I can teleport you to bosses, if you want.", "Beware, some of the bosses are strong!", "Good luck, young adventurer.", 598, "Boss teleporter");
			c.nextChat = 4444;
		break;
		case 4442:
			sendNpcChat4("Hello, i am one of the Destroscape Teleporters.", "I can teleport you to bosses, if you want.", "Beware, some of the bosses are strong!", "Good luck, young adventurer.", c.talkingNpc, "Boss teleporter");
			c.nextChat = 4445;
		break;
		case 4444:
			c.getDH().sendOption5("Metal Dragons lair", "Tormented demons", "Frost dragons", "Lahkrahnaz", "Nomad");
			c.teleAction = 45;
			break;
		case 4445:
			c.getDH().sendOption5("Nex", "Glacors", "The Avatar Of Destruction", "Sea Troll Queen", "Corporal Beast");
			c.teleAction = 46;
			break;
		case 170:
			sendNpcChat4("Hello, my names is Old John.", "I have been inside this server ever since it started.", "You can see your stats by right clicking me,", "and pressing PlayerStats.", c.talkingNpc, "Old John");
			c.nextChat = 171;
		break;
		case 171:
			sendNpcChat4("This will show you how many points you have.", "You can spend these points at the point shops in the bank.", "And you can earn these points by", "Slayering, Pking, or lvling up.", c.talkingNpc, "Old John");
			c.nextChat = 172;
		break;
		case 172:
			sendNpcChat4("Destroscape is based on a 317 PI, or project insanity.", "It has been coded and owned by Lawless.", "Our website can be found at http://destroscape.com,", "or click the quest tab.", c.talkingNpc, "Old John");
			c.nextChat = 173;
		break;
		case 173:
			sendNpcChat3("If there is anything else you have to know, contact our staff.", "There will be updates weekly, check the forums for this.", "Well you lad, ill be keep going, see you later.", c.talkingNpc, "Old John");
			c.nextChat = 0;
		break;
		case 185:
			sendNpcChat4("Hello, i'm lottie, wanna enter lotterie?.", "Entering costs 2 million gp but you could win up to", "250 million gp! you can enter up to 5 times per draw.", "Would you like to enter the lottery?", c.talkingNpc, "Lottie");
			c.nextChat = 186;
		break;
		case 186:
			sendOption2("Yes i would like to enter!", "No, Id rather not.");
			c.dialogueAction = 186;
		break;
		case 9881:
        c.sendMessage("The ghost laughs and says: talk to me again if you need anything.");
			sendNpcChat4("Hello, i will explain some things.", "To enter your shop type ::myshop.", "talk to my partner over here to claim the money you earned.", "there are also a couple of ways to earn money.", c.talkingNpc, "Financial ghost");
			c.nextChat = 9882;
	    break;
				case 9882:
			sendNpcChat4("For example leveling, in the bank is a leveling shop,", "you can enter this shop by clicking on the NPC.", "You can earn level points by finishing a slayer task,", "or just skilling...", c.talkingNpc, "Financial Ghost");
			c.nextChat = 0;
	    break;
		case 106:
				sendOption5("One 6-sided die", "Two 6-sided dice", "One 4-sided die", "One 8-sided die", "More...");
				c.dialogueAction = 106;
				c.teleAction = 0;
				c.nextChat = 0;
				break;

			case 107:
				sendOption5("One 10-sided die", "One 12-sided die", "One 20-sided die", "Two 10-sided dice for 1-100", "Back...");
				c.dialogueAction = 107;
				c.teleAction = 0;
				c.nextChat = 0;
				break;
                case 200:
			sendNpcChat4("Hello there "+c.playerName+"!"," I have the ability to reset your combat stats for free!","As a fee i will take all your level points.","Remember this is irreverable, now...", c.talkingNpc, "Town Crier");
			c.nextChat = 205;
		case 205:
		    sendNpcChat2("Take off all your equipment.."," before attempting to reset your stats!",c.talkingNpc, "Town Crier");
			c.nextChat = 210;
		break;
		case 210:
			sendOption4("Reset Defence", "Reset Prayer", "Reset Attack", "Reset All Combat Stats");
			c.dialogueAction = 42;
		break;
		case 230:
			sendNpcChat2("Congratulations!", "Your defence has been completely reset!",c.talkingNpc, "Town Crier");
			c.nextChat = 0;
		break;
		case 240:
			sendNpcChat2("Congratulations!", "Your attack has been completely reset!",c.talkingNpc, "Town Crier");
			c.nextChat = 0;
		break;
		case 250:
			sendNpcChat2("Congratulations!", "Your combat stats have been completely reset!",c.talkingNpc, "Town Crier");
			c.nextChat = 0;
		break;
		case 260:
			sendNpcChat2("Congratulations!","Your prayer have been completely reset!",c.talkingNpc, "Town Crier");
			c.nextChat = 0;
		break;

		case 80:
                        sendStatement("Should I tele you ?");
                        c.nextChat = 81;
                break;

                case 81:
                        sendOption2("Yes get me out of this fucking hell hole!",  "Hell no! I love it here, I'm nuts for these monkeys!");
                        c.dialogueAction = 27;
                        c.nextChat = 0;
                break;
		
                case 38:
                        sendStatement("Congratulations, you open the chest and got a reward!");
                        c.nextChat = 0;
                break;

                case 37:
                        sendStatement("You need atleast 1 free inventory spaces! and a crystal key!");
                        c.nextChat = 0;
                break;		
		
		

		case 30:
			sendNpcChat4("Congratulations!","You have killed 20 monkeys hope you learned something..", "would you like to escape?","Do not break anymore rules!", c.talkingNpc, "Mosol Rei");
			c.dialogueAction = 26;
			c.nextChat = 31;
			break;
		case 31:
			sendOption2("Yes get me out of this fucking hell hole!",  "Hell no! I love it here, I'm nuts for these monkeys!");
			c.dialogueAction = 27;
			c.nextChat = 0;	
			break;
	case 51:
			sendOption2("Castle Pk (14)",  "Clan War Zone (34)");
			c.dialogueAction = 51;
			break;
		case 32:
			sendNpcChat4("You cannot Escape yet!","You've killed "+c.monkeyk0ed+" out of 20 monkeys!","Come back when you have killed 20","Kthxbai", c.talkingNpc, "Mosol Rei");
			c.dialogueAction = 30;
			c.nextChat = 0;
			break;
		case 0:
			c.talkingNpc = -1;
			c.getPA().removeAllWindows();
			c.nextChat = 0;
			break;
		case 20:
			sendOption4("Information", "Black Jack","Five", "Maybe later...");
			c.dialogueAction = 100;
			break;

		case 25:
			sendOption4("","Black Jack", "Five","");
			c.dialogueAction = 101;
			break;

		case 21:
			sendNpcChat4("The way we play this game is simple. The way you win is", 
					"You need to get a higher number than me and you win the", 
					"500,000 coins. You need to bet 250,000 coins per round.",
					"If you get over 22 you bust and you lose.", 
					c.talkingNpc, "~ Black Jack ~");
					c.nextChat = 22;
					break;

		case 22:
			sendNpcChat4("", 
					"If i get 22+ I bust and I lose. If you get 21 then you have black", 
					"jack and you win double of what you bet.",
					"", 
					c.talkingNpc, "~ Black Jack ~");
					c.nextChat = 0;
					break;

		case 23:
			sendNpcChat4("This is my own game which I made. It's pretty simple", 
					"and resembles poker but it's a lot different. The aim of this", 
					"game is to get the same number like the random number",
					"You got 2 numbers if both hit the same you win.", 
					c.talkingNpc, "~ Five ~");
					c.nextChat = 24;
					break;
		case 24:
			sendNpcChat4("", 
					"To play this game you need to bet 1,000,000 coins. You", 
					"can win a lot of good items but also lose a lot of cash.",
					"", 
					c.talkingNpc, "~ Five ~");
					c.nextChat = 0;
					break;
		case 1:
			sendStatement("You found a hidden tunnel! Do you want to enter it?");
			c.dialogueAction = 1;
			c.nextChat = 2;
			break;
		case 45:
			sendNpcChat2("Since you haven't shown me a defender to", "prove your prowess as a warrior.", 4289, "Kamfreena");
			c.nextChat = 46;
			break;
		case 46:
			sendNpcChat3("I'll release some Cyclopes which might drop bronze", "defenders for you to start off with, unless you show me", "another. Have fun in there.", 4289, "Kamfreena");
			c.nextChat = -1;
			break;
		case 47:
			sendNpcChat2("The cyclops will now drop:", "" + c.getWarriorsGuild().getCyclopsDrop126(c) + " defenders.", 4289, "Kamfreena");
			c.nextChat = -1;
			break;
		case 505:
			sendNpcChat2("Hello, i am one of the Dungeoneering stores.", "Tell me what you wish to do?", c.talkingNpc, "Goth Leprechaun");
			c.nextChat = 506;
			break;
		case 506:
			sendOption2("Buy dungeoneering skillcape (99k)", "Open store");
			c.nextChat = 0;
			c.teleAction = 506;
			break;
			case 1396:
			sendNpcChat4("No... Time...", "To talk... Must... Work...", "Need... To feed... Family!", "Please... Get me... A drink...", c.talkingNpc, "Hard Worker");
			c.nextChat = 1397;
			break;
			case 1397:
			c.teleAction = 1397;
			c.nextChat = 0;
			sendOption2("Give beer", "I don't have a drink, sorry.");
			break;
			case 1398:
			sendPlayerChat2("I don't have a drink, sorry...", "I will go and get you one though!");
			c.sendMessage("The worker seems rewarding... you should get him a beer!");
			c.nextChat = 0;
			break;
		case 2:
			sendOption2("Yea! I'm fearless!",  "No way! That looks scary!");
			c.dialogueAction = 1;
			c.nextChat = 0;
			break;
		case 3:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
			"Would you like a slayer task?", c.talkingNpc, "Duradel");
			c.nextChat = 4;
		break;
		case 5:
			sendNpcChat4("Hello adventurer...", "My name is Kolodion, the master of this mage bank.", "Would you like to play a minigame in order ", 
						"to earn points towards recieving magic related prizes?", c.talkingNpc, "Kolodion");
			c.nextChat = 6;
		break;
		case 6:
			sendNpcChat4("The way the game works is as follows...", "You will be teleported to the wilderness,", 
			"You must kill mages to recieve points,","redeem points with the chamber guardian.", c.talkingNpc, "Kolodion");
			c.nextChat = 15;
		break;
		case 11:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
			"Would you like a slayer task?", c.talkingNpc, "Duradel");
			c.nextChat = 12;
		break;
		case 12:
			sendOption2("Yes I would like a slayer task.", "No I would not like a slayer task.");
			c.dialogueAction = 5;
		break;
		case 13:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I see I have already assigned you a task to complete.", 
			"Would you like me to give you an easier task?", c.talkingNpc, "Duradel");
			c.nextChat = 14;
		break;
		case 14:
			sendOption2("Yes I would like an easier task.", "No I would like to keep my task.");
			c.dialogueAction = 6;
		break;
		case 15:
			sendOption2("Yes I would like to play", "No, sounds too dangerous for me.");
			c.dialogueAction = 7;
		break;
		case 16:
			sendOption2("I would like to reset my barrows brothers.", "I would like to fix all my barrows");
			c.dialogueAction = 8;
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
               case 70:
			sendNpcChat4("I have found two secret tunnels!", "I saw a giant mole and some Red Dragons!", "Help me defeat them?", 
			"Would you like me, to bring you there?", c.talkingNpc, "Bonafido");
			c.nextChat = 71;
		break;
		case 71:
			sendOption2("Giant Mole", "Red Dragons (43 Wildy)");
			c.dialogueAction = 13;
			c.dialogueId = 71;
			c.teleAction = -1;
		break;
		}
	}
	
	/*
	 * Information Box
	 */
	/*
	 * Information Box
	 */
	
	public void sendStartInfo(String text, String text1, String text2, String text3, String title) {
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(text, 6181);
		c.getPA().sendFrame126(text1, 6182);
		c.getPA().sendFrame126(text2, 6183);
		c.getPA().sendFrame126(text3, 6184);
		c.getPA().sendFrame164(6179);
	}
	
	/*
	 * Options
	 */
	
	private void sendOption(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2470);
	 	c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126("Click here to continue", 2473);
		c.getPA().sendFrame164(13758);
	}	
	
	private void sendOption2(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	private void sendOption3(String s, String s1, String s2) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame126(s2, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	public void sendOption4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame126("Select an Option", 2481);
		c.getPA().sendFrame126(s, 2482);
		c.getPA().sendFrame126(s1, 2483);
		c.getPA().sendFrame126(s2, 2484);
		c.getPA().sendFrame126(s3, 2485);
		c.getPA().sendFrame164(2480);
	}
	/**public void sendNpcChat(FacialAnimation anim, String... s) {
		int animId = anim.getAnimationId();
		for (int i = 0; i < s.length; i++) {
			player.getActionSender().sendInterfaceAnimation(
					sendNpcChatBase[s.length - 1], animId);
			player.getActionSender().sendChatboxText(
					NpcHandler.getNpcListName(c.talkingNpc),
					sendNpcChatBase[s.length - 1] + 1);
			player.getActionSender().sendChatboxText(s[i],
					sendNpcChatBase[s.length - 1] + i + 2);
			player.getActionSender().sendFrame75(c.talkingNpc,
					sendNpcChatBase[s.length - 1]);
			player.getActionSender().sendChatInterface(
					sendNpcChatBase[s.length - 1] - 1);
		}
	}**/
	public void sendOption5(String s, String s1, String s2, String s3, String s4) {
		c.getPA().sendFrame126("Select an Option", 2493);
		c.getPA().sendFrame126(s, 2494);
		c.getPA().sendFrame126(s1, 2495);
		c.getPA().sendFrame126(s2, 2496);
		c.getPA().sendFrame126(s3, 2497);
		c.getPA().sendFrame126(s4, 2498);
		c.getPA().sendFrame164(2492);
	}

	/*
	 * Statements
	 */
	
	private void sendStatement(String s) { // 1 line click here to continue chat box interface
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}
	
	/*
	 * Npc Chatting
	 */
	
	public void sendNpcChat2(String s, String s1, int ChatNpc, String name) {
		c.getPA().sendFrame200(4888, 9847);
		c.getPA().sendFrame126(name, 4889);
		c.getPA().sendFrame126(s, 4890);
		c.getPA().sendFrame126(s1, 4891);
		c.getPA().sendFrame75(ChatNpc, 4888);
		c.getPA().sendFrame164(4887);
	}
	
	public void sendNpcChat3(String s, String s1, String s2, int ChatNpc, String name) {
		c.getPA().sendFrame200(4894, 9847);	//Was 591
		c.getPA().sendFrame126(name, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(ChatNpc, 4894);
		c.getPA().sendFrame164(4893);
	}
	
	
	private void sendNpcChat4(String s, String s1, String s2, String s3, int ChatNpc, String name) {
		c.getPA().sendFrame200(4901, 9847);
		c.getPA().sendFrame126(name, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4900);
	}
	
	/*
	 * Player Chating Back
	 */
	
	private void sendPlayerChat1(String s) {
		c.getPA().sendFrame200(969, 591);
		c.getPA().sendFrame126(c.playerName, 970);
		c.getPA().sendFrame126(s, 971);
		c.getPA().sendFrame185(969);
		c.getPA().sendFrame164(968);
	}
	
	private void sendPlayerChat2(String s, String s1) {
		c.getPA().sendFrame200(974, 591);
		c.getPA().sendFrame126(c.playerName, 975);
		c.getPA().sendFrame126(s, 976);
		c.getPA().sendFrame126(s1, 977);
		c.getPA().sendFrame185(974);
		c.getPA().sendFrame164(973);
	}

	
	private void sendPlayerChat3(String s, String s1, String s2) {
		c.getPA().sendFrame200(980, 591);
		c.getPA().sendFrame126(c.playerName, 981);
		c.getPA().sendFrame126(s, 982);
		c.getPA().sendFrame126(s1, 983);
		c.getPA().sendFrame126(s2, 984);
		c.getPA().sendFrame185(980);
		c.getPA().sendFrame164(979);
	}

	public void talk(int face, String line1, String line2, String line3, String line4, int npcID) {
		c.getPA().sendFrame200(4901, face);
		c.getPA().sendFrame126(c.getPA().GetNpcName(npcID).replaceAll("_", " "), 4902);
		c.getPA().sendFrame126(""+line1, 4903);
		c.getPA().sendFrame126(""+line2, 4904);
		c.getPA().sendFrame126(""+line3, 4905);
		c.getPA().sendFrame126(""+line4, 4906);
		c.getPA().sendFrame126("Click here to continue", 4907);
		c.getPA().sendFrame75(npcID, 4901);
		c.getPA().sendFrame164(4900);
	}
	
	private void sendPlayerChat4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame200(987, 591);
		c.getPA().sendFrame126(c.playerName, 988);
		c.getPA().sendFrame126(s, 989);
		c.getPA().sendFrame126(s1, 990);
		c.getPA().sendFrame126(s2, 991);
		c.getPA().sendFrame126(s3, 992);
		c.getPA().sendFrame185(987);
		c.getPA().sendFrame164(986);
	}
}
