package game.net.packets;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.event.Event;
import engine.event.EventContainer;
import engine.event.EventManager;
import engine.network.Connection;
import engine.util.Misc;
import game.Config;
import game.Server;
import game.combat.Specials;
import game.combat.magic.Lunar;
import game.combat.prayer.Prayer;
import game.content.achievement.*;
import game.content.CompletionistCape;
import game.content.ItemsOnDeath;
import game.content.MoneyBank;
import game.content.News;
import game.content.QuickCurses;
import game.content.QuickPrayers;
import game.content.Resting;
import game.content.quests.AFreshStart;
import game.content.quests.TheStrykeWyrm;
import game.content.quests.LunarDiplomacy;
import game.content.quests.DesertTreasure;
import game.content.travel.GnomeGlider;
import game.content.travel.Lodestone;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.entity.player.PlayerAssistant;
import game.entity.player.PlayerHandler;
import game.item.GameItem;
import game.minigame.barrows.Dungeon;
import game.minigame.bountyhunter.BountyHunter;
import game.minigame.weapongame.WeaponGame;
import game.net.packets.dialoguebuttons.FiveOptions;
import game.net.packets.dialoguebuttons.FourOptions;
import game.net.packets.dialoguebuttons.TwoOptions;
import game.skill.cooking.Cooking;
import game.skill.crafting.LeatherMaking;
import game.skill.crafting.Tanning;
import game.skill.dungeoneering.Dungeoneering;
import game.skill.fletching.ArrowHandler;
import game.skill.fletching.BowHandler;
import game.skill.fletching.StringingHandler;
import game.skill.herblore.Herblore;
import game.skill.prayer.Altar;
import game.skill.prayer.Constants;
import game.skill.smithing.Smelting;
import game.skill.summoning.PouchCreation;
import game.skill.summoning.Summoning;
import game.skill.summoning.SummoningSpecials;

public class ClickingButtons implements PacketType {

	public int regMisc = Misc.random(5);

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		final int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0,
				packetSize);
		if (c.isDead) {
			return;
		}
		if (Config.SERVER_DEBUG = true) {
			if (c.playerRights == 3) {
				Misc.println(c.playerName + " - actionbutton: "
						+ actionButtonId);
			}
		}
		if (c.playerRights == 3) {
			c.sendMessage("ActionbuttonID: " + actionButtonId);
		}
		for (int i = 0; i < c.skillButtons.length; i++) {
			if (actionButtonId == c.skillButtons[i][0]) {
				c.drawInterface().drawSkillInfo(c, c.skillButtons[i][1]);
			}
		}
		c.checkResponse(actionButtonId);
		GnomeGlider.flightButtons(c, actionButtonId);
		if (actionButtonId > 34100 && actionButtonId < 34220) {
			BowHandler.handleFletchingButtons(c, actionButtonId);
		}
		if (actionButtonId > 90100 && actionButtonId < 90202) {
			AchievementManager.sendInformation(c, actionButtonId);
		}
		if (actionButtonId >= 20000 && actionButtonId <= 22000
				|| actionButtonId > 2170 & actionButtonId < 2174
				|| actionButtonId > 70000 && actionButtonId < 70100
				/* || actionButtonId >= 87231 && actionButtonId <= 88013 */)
			Prayer.activatePrayer(c, actionButtonId, false);
		if (c.isResting) {
			Resting.stopResting(c);
			return;
		}
		if (actionButtonId >= 67050 && actionButtonId <= 67075) {
			if (c.altarPrayed == 0)
				QuickPrayers.clickPray(c, actionButtonId);
			else
				QuickCurses.clickCurse(c, actionButtonId);
		}
		Smelting.getBar(c, actionButtonId);
		Lunar.Button(c, actionButtonId);
		Dungeoneering.getDButtons(c, actionButtonId);
		Lodestone.handleButtons(c, actionButtonId);
		if (actionButtonId >= 150000 && actionButtonId <= 151999) {
			PouchCreation.makeSummoningScroll(c, actionButtonId);
		} else if (actionButtonId >= 152000 && actionButtonId <= 152030) {
			PouchCreation.makeSummoningScroll2(c, actionButtonId);
		} else if (actionButtonId >= 57200 && actionButtonId <= 57300) {
			Tanning.tanHide(c, actionButtonId);
		} else if (actionButtonId >= 33185 && actionButtonId <= 35010) {
			LeatherMaking.craftLeather(c, actionButtonId);
		}
		c.getSI().click(actionButtonId);
		int[] spellIds = {4128,4130,4132,4134,4136,4139,4142,4145,4148,4151,4153,4157,4159,4161,4164,4165,4129,4133,4137,6006,6007,6026,6036,6046,6056,
				4147,6003,47005,4166,4167,4168,48157,50193,50187,50101,50061,50163,50211,50119,50081,50151,50199,50111,50071,50175,50223,50129,50091};
		for(int i=0; i < spellIds.length; i++) {
			if(actionButtonId == spellIds[i]) {
				c.autocasting = true;
				c.autocastId = i;	
			}
		}
		switch (actionButtonId) {
		/**
		 * Loyalty Title Shop
		 */
		case 67060:
			if (c.loyaltyPoints >= 10) { 
				c.loyaltyPoints -= 10;
				c.loyaltyTitle = 1;
				c.sendMessage("You successfully change your Loyalty Title.");
				c.logout();
			} else {
				c.sendMessage("You have not earned enough Loyalty Points for this title.");
				c.getPA().removeAllWindows();
			}
			break;

		case 67062:
			if (c.loyaltyPoints >= 10) { 
				c.loyaltyPoints -= 10;
				c.loyaltyTitle = 11;
				c.sendMessage("You successfully change your Loyalty Title.");
				c.logout();
			} else {
				c.sendMessage("You have not earned enough Loyalty Points for this title.");
				c.getPA().removeAllWindows();
			}
			break;

		case 67064:
			if (c.loyaltyPoints >= 15) { 
				c.loyaltyPoints -= 15;
				c.loyaltyTitle = 3;
				c.sendMessage("You successfully change your Loyalty Title.");
				c.logout();
			} else {
				c.sendMessage("You have not earned enough Loyalty Points for this title.");
				c.getPA().removeAllWindows();
			}
			break;

		case 67066:
			if (c.loyaltyPoints >= 15) { 
				c.loyaltyPoints -= 15;
				c.loyaltyTitle = 18;
				c.sendMessage("You successfully change your Loyalty Title.");
				c.logout();
			} else {
				c.sendMessage("You have not earned enough Loyalty Points for this title.");
				c.getPA().removeAllWindows();
			}
			break;

		case 67068:
			if (c.loyaltyPoints >= 20) { 
				c.loyaltyPoints -= 20;
				c.loyaltyTitle = 29;
				c.sendMessage("You successfully change your Loyalty Title.");
				c.logout();
			} else {
				c.sendMessage("You have not earned enough Loyalty Points for this title.");
				c.getPA().removeAllWindows();
			}
			break;

		case 67070:
			if (c.loyaltyPoints >= 25) { 
				c.loyaltyPoints -= 25;
				c.loyaltyTitle = 13;
				c.sendMessage("You successfully change your Loyalty Title.");
				c.logout();
			} else {
				c.sendMessage("You have not earned enough Loyalty Points for this title.");
				c.getPA().removeAllWindows();
			}
			break;

		case 67072:
			if (c.loyaltyPoints >= 30) { 
				c.loyaltyPoints -= 30;
				c.loyaltyTitle = 30;
				c.sendMessage("You successfully change your Loyalty Title.");
				c.logout();
			} else {
				c.sendMessage("You have not earned enough Loyalty Points for this title.");
				c.getPA().removeAllWindows();
			}
			break;

		case 67074:
			if (c.loyaltyPoints >= 50) { 
				c.loyaltyPoints -= 50;
				c.loyaltyTitle = 28;
				c.sendMessage("You successfully change your Loyalty Title.");
				c.logout();
			} else {
				c.sendMessage("You have not earned enough Loyalty Points for this title.");
				c.getPA().removeAllWindows();
			}
			break;

		case 67058:
			c.getPA().removeAllWindows();
			break;
			/**
			 * Loyalty Title End
			 */


            /*case 19136:
                //Toggle quick prayers
                if (c.quickPray || c.quickCurse) {
                    QuickCurses.turnOffQuicks(c);
                    return;
                }
                QuickCurses.turnOnQuicks(c);
                break;

            case 19137:
                //Select quick prayers
                QuickCurses.selectQuickInterface(c);
                break;

            case 67089:
                //quick curse confirm
                QuickCurses.clickConfirm(c);
                break;

            case 70082:
                //select your quick prayers/curses
                QuickCurses.selectQuickInterface(c);
                c.getPA().sendFrame106(5);
                break;*/
		case 23132:
			c.setSidebarInterface(1, 3917);
			c.setSidebarInterface(2, 638);
			c.setSidebarInterface(3, 3213);
			c.setSidebarInterface(4, 1644);
			//c.setSidebarInterface(5, 5608);
			c.usedRingOfStone = false;
			c.usedEasterRing = false;
			c.setSidebarInterface(7, 18128);
			c.setSidebarInterface(8, 5065);
			c.setSidebarInterface(9, 5715); 
			c.setSidebarInterface(10, 2449);
			c.setSidebarInterface(11, 904);
			c.setSidebarInterface(12, 147);
			c.setSidebarInterface(13, 962);
			c.setSidebarInterface(0, 2423);
			if (c.playerEquipment[c.playerRing] == 7927) {
				c.getItems().deleteEquipment(c.playerEquipment[c.playerRing], c.playerRing);
				c.getItems().addItem(7927,1);
			}
			if (c.playerEquipment[c.playerRing] == 6583) {
				c.getItems().deleteEquipment(c.playerEquipment[c.playerRing], c.playerRing);
				c.getItems().addItem(6583,1);
			}
			c.isNpc = false;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : 
				c.playerMagicBook == 1 ? 12855 : 
					c.playerMagicBook == 2 ? 29999 : 12855);
			break;
			/**
			 * Dragonkin Interface
			 */
		case 203035: //Confirm
			if (c.getItems().playerHasItem(18782, 1)) {
				if (c.dragonkinSkill == 0) {
					c.getPA().removeAllWindows();
					return;
				}
				switch (c.dragonkinSkill) {
				case 1: //Attack
			c.getPA().addSkillXP(300000, c.playerAttack);
					break;
				case 2: //Strength
			c.getPA().addSkillXP(300000, c.playerStrength);
					break;
				case 3: //Range
			c.getPA().addSkillXP(300000, c.playerRanged);
					break;
				case 4: //Magic
			c.getPA().addSkillXP(300000, c.playerMagic);
					break;
				case 5: //Defence
			c.getPA().addSkillXP(300000, c.playerDefence);
					break;
				case 6: //Crafting
			c.getPA().addSkillXP(300000, c.playerCrafting);
					break;
				case 7: //Hitpoints
			c.getPA().addSkillXP(300000, c.playerHitpoints);
					break;
				case 8: //Prayer
			c.getPA().addSkillXP(300000, c.playerPrayer);
					break;
				case 9: //Agility
			c.getPA().addSkillXP(300000, c.playerAgility);
					break;
				case 10: //Herblore
			c.getPA().addSkillXP(300000, c.playerHerblore);
					break;
				case 11: //Thieving
			c.getPA().addSkillXP(300000, c.playerThieving);
					break;
				case 12: //Fishing
			c.getPA().addSkillXP(300000, c.playerFishing);
					break;
				case 13: //Runecrafting
			c.getPA().addSkillXP(300000, c.playerRunecrafting);
					break;
				case 14: //Slayer
			c.getPA().addSkillXP(300000, c.playerSlayer);
					break;
				case 15: //Farming
			c.getPA().addSkillXP(300000, c.playerFarming);
					break;
				case 16: //Mining
			c.getPA().addSkillXP(300000, c.playerMining);
					break;
				case 17: //Smithing
			c.getPA().addSkillXP(300000, c.playerSmithing);
					break;
				case 18: //Hunter
			c.getPA().addSkillXP(300000, c.playerHunter);
					break;
				case 19: //Cooking
			c.getPA().addSkillXP(300000, c.playerCooking);
					break;
				case 20: //Firemaking
			c.getPA().addSkillXP(300000, c.playerFiremaking);
					break;
				case 21: //Woodcutting
			c.getPA().addSkillXP(300000, c.playerWoodcutting);
					break;
				case 22: //Fletching
			c.getPA().addSkillXP(300000, c.playerFletching);
					break;
				case 23: //Summoning
			c.getPA().addSkillXP(300000, c.playerSummoning);
					break;
				case 24: //Dungeoneering
			c.getPA().addSkillXP(300000, c.playerDungeoneering);
					break;
				}
				c.getItems().deleteItem(18782, 1);
				c.getPA().removeAllWindows();
			} else {
				c.sendMessage("You need a Dragonkin Lamp in your inventory in order to do this.");
			}
			break;
		case 203038: //Attack
			c.dragonkinSkill = 1;
			c.sendMessage("You've chosen <col=255>Attack.");
			break;
		case 203039: //Strength
			c.dragonkinSkill = 2;
			c.sendMessage("You've chosen <col=255>Strength.");
			break;
		case 203040: //Range
			c.dragonkinSkill = 3;
			c.sendMessage("You've chosen <col=255>Range.");
			break;
		case 203041: //Magic
			c.dragonkinSkill = 4;
			c.sendMessage("You've chosen <col=255>Magic.");
			break;
		case 203042: //Defence
			c.dragonkinSkill = 5;
			c.sendMessage("You've chosen <col=255>Defence.");
			break;
		case 203043: //Crafting
			c.dragonkinSkill = 6;
			c.sendMessage("You've chosen <col=255>Crafting.");
			break;
		case 203044: //Hitpoints
			c.dragonkinSkill = 7;
			c.sendMessage("You've chosen <col=255>Hitpoints.");
			break;
		case 203045: //Prayer
			c.dragonkinSkill = 8;
			c.sendMessage("You've chosen <col=255>Prayer.");
			break;
		case 203046: //Agility
			c.dragonkinSkill = 9;
			c.sendMessage("You've chosen <col=255>Agility.");
			break;
		case 203047: //Herblore
			c.dragonkinSkill = 10;
			c.sendMessage("You've chosen <col=255>Herblore.");
			break;
		case 203048: //Thieving
			c.dragonkinSkill = 11;
			c.sendMessage("You've chosen <col=255>Thieving.");
			break;
		case 203049: //Fishing
			c.dragonkinSkill = 12;
			c.sendMessage("You've chosen <col=255>Fishing.");
			break;
		case 203050: //Runecrafting
			c.dragonkinSkill = 13;
			c.sendMessage("You've chosen <col=255>Runecrafting.");
			break;
		case 203051: //Slayer
			c.dragonkinSkill = 14;
			c.sendMessage("You've chosen <col=255>Slayer.");
			break;
		case 203052: //Farming
			c.dragonkinSkill = 15;
			c.sendMessage("You've chosen <col=255>Farming.");
			break;
		case 203053: //Mining
			c.dragonkinSkill = 16;
			c.sendMessage("You've chosen <col=255>Mining.");
			break;
		case 203054: //Smithing
			c.dragonkinSkill = 17;
			c.sendMessage("You've chosen <col=255>Smithing.");
			break;
		case 203055: //Hunter
			c.dragonkinSkill = 18;
			c.sendMessage("You've chosen <col=255>Hunter.");
			break;
		case 203056: //Cooking
			c.dragonkinSkill = 19;
			c.sendMessage("You've chosen <col=255>Cooking.");
			break;
		case 203057: //Firemaking
			c.dragonkinSkill = 20;
			c.sendMessage("You've chosen <col=255>Firemaking.");
			break;
		case 203058: //Woodcutting
			c.dragonkinSkill = 21;
			c.sendMessage("You've chosen <col=255>Woodcutting.");
			break;
		case 203059: //Fletching
			c.dragonkinSkill = 22;
			c.sendMessage("You've chosen <col=255>Fletching.");
			break;
		case 203060: //Construction
			c.sendMessage("The <col=255>Construction</col> Skill is not available.");
			break;
		case 203061: //Summoning
			c.dragonkinSkill = 23;
			c.sendMessage("You've chosen <col=255>Summoning.");
			break;
		case 203062: //Dungeoneering
			c.dragonkinSkill = 24;
			c.sendMessage("You've chosen <col=255>Dungeoneering.");
			break;
			/**
			 * Dragonkin Interface End
			 */

			/**
			 * Bank Tabs
			 */
		case 86008: 
			c.getPA().openUpBank(0); 
			break;
		case 86009: 
			if(c.bankItems1[0] > 0) 
				c.getPA().openUpBank(1); 
			break;
		case 86010: 
			if(c.bankItems2[0] > 0) 
				c.getPA().openUpBank(2); 
			break;
		case 86011: 
			if(c.bankItems3[0] > 0) 
				c.getPA().openUpBank(3); 
			break;
		case 86012: 
			if(c.bankItems4[0] > 0) 
				c.getPA().openUpBank(4); 
			break;
		case 86013: 
			if(c.bankItems5[0] > 0) 
				c.getPA().openUpBank(5); 
			break;
		case 86014: 
			if(c.bankItems6[0] > 0) 
				c.getPA().openUpBank(6); 
			break;
		case 86015: 
			if(c.bankItems7[0] > 0) 
				c.getPA().openUpBank(7); 
			break;
		case 86016: 
			if(c.bankItems8[0] > 0) 
				c.getPA().openUpBank(8); 
			break;
			/**
			 * Bank Tabs End
			 */


			/**
			 * Skill Info Buttons
			 */
		case 101184:
			c.setSidebarInterface(1, 3917);
			break;

		case 101175:
			PlayerAssistant.sendQC(c, c.currentSkillInfo);
			break;

		case 101174:
			c.getDH().sendDialogues(113, 0);
			break;
			/**
			 * Skill Info Buttons End
			 */

			/**
			 * Money Bank
			 */
		case 35092:
			int Slot = c.getItems().getItemSlot(995),
			amount = -1;
			if (Slot != -1)
				amount = c.playerItemsN[Slot];
			MoneyBank.depositGold(c, amount);
			break;

		case 35095:
			c.sendMessage("Please type ::withdraw [amount]");
			break;

		case 35100:
			c.sendMessage("Please type ::transfer [amount] [name]");
			break;
			/**
			 * Money Bank End
			 */

			/**
			 * WeaponGame Interface
			 */

		case 191206: //close
			c.getPA().closeAllWindows();
			c.currentClass = 0;
			break;

		case 191210: //enter cave
			if (WeaponGame.canEnter(c)) {
				WeaponGame.enterCave(c);
			} else if (c.currentClass == 0) {
				c.sendMessage("You need to choose a class first.");
			} else {
				c.sendMessage("You can't take any items into the weapon game, please bank them.");
			}
			break;

		case 136189: // PvP mode enabled.
			/*if (c.isPVPActive && (c.playerIndex == 0 || c.npcIndex == 0)) {
				c.isPVPActive = false;
				c.sendMessage("@blu@ PvP mode is now off!");
			} else if (c.isPVPActive && c.playerIndex > 0 || c.npcIndex > 0) {
				c.sendMessage("@red@ You must be out of combat to turn off PvP mode!");
			} else if (!c.isPVPActive) {
				if (System.currentTimeMillis() - c.toggledPvP < 30000) {
					c.sendMessage("You can only do this every 30 seconds.");
					return;
				}
				if (PlayerHandler.getPlayerCount() == 1) {
					c.sendMessage("You can't do this when no other players are present!");
					return;
				}
				c.isPVPActive = true;
				c.sendMessage("@blu@ PvP mode is now on!");
				c.getPA().sendYell(1, 14687013, c.playerName , "has toggled PvP mode on!");
				c.getPA().createPlayerHints(10, c.playerId);
				c.toggledPvP = System.currentTimeMillis();
			}*/
				c.sendMessage("This does not work yet.");
			break;

		case 191207: //melee
			c.currentClass = 1;
			c.getPA().sendFrame126("Current Class: Melee", 49107);
			c.getPA().sendFrame126("Class Level: "+c.meleeClassLvl, 49108);
			break;

		case 191208: //ranged
			c.currentClass = 2;
			c.getPA().sendFrame126("Current Class: Ranged", 49107);
			c.getPA().sendFrame126("Class Level: "+c.rangedClassLvl, 49108);
			break;

		case 191209: //magic
			c.currentClass = 3;
			c.getPA().sendFrame126("Current Class: Magic", 49107);
			c.getPA().sendFrame126("Class Level: "+c.magicClassLvl, 49108);
			break;

		case 8198: //Party Room Chest
			Server.partyRoom.deposit(c);
			break;

			/**
			 * Dialogue Handling
			 */

		case 9157:
			TwoOptions.handleOption1(c);
			break;

		case 9158:
			TwoOptions.handleOption2(c);
			break;

		case 9178:
			FourOptions.handleOption1(c);
			break;

		case 9179:
			FourOptions.handleOption2(c);
			break;

		case 9180:
			FourOptions.handleOption3(c);
			break;

		case 9181:
			FourOptions.handleOption4(c);
			break;

		case 9190:
			FiveOptions.handleOption1(c);
			break;

		case 9191:
			FiveOptions.handleOption2(c);
			break;

		case 9192:
			FiveOptions.handleOption3(c);
			break;

		case 9193:
			FiveOptions.handleOption4(c);
			break;

		case 9194:
			FiveOptions.handleOption5(c);
			break;

			/**
			 * Monster teleport interface
			 */

		case 87130:
			c.getPA().closeAllWindows();
			break;

			// First row

		case 87131:
			c.getPA().spellTeleport(2677, 3714, 0);
			break;
		case 87132:
			c.getPA().spellTeleport(3253, 2785, 0);
			break;
		case 87133:
			c.getPA().spellTeleport(2913, 4385, 0);
			break;
		case 87134:
			c.getPA().spellTeleport(2739, 5086, 0);
			break;
		case 87135:
			c.getPA().spellTeleport(2559, 4958, 0);
			break;
		case 87136:
			break;
		case 87137:
			break;
		case 87138:
			break;
		case 87139:
			break;

			// Second row

		case 87140:
			c.getPA().spellTeleport(2442, 10147, 0);
			break;
		case 87141:
			c.getPA().spellTeleport(3297, 9825, 0);
			break;
		case 87142:
			c.getPA().spellTeleport(3297, 9825, 0);
			break;
		case 87143:
			c.getPA().spellTeleport(2654, 3991, 1);
			break;
		case 87144:
			break;
		case 87145:
			break;
		case 87146:
			break;
		case 87147:
			break;

			// Third row

		case 87148:
			c.getPA().spellTeleport(2907, 9714, 0);
			break;
		case 87149:
			c.getPA().spellTeleport(3164, 3685, 0);
			break;
		case 87150:
			c.getPA().spellTeleport(3429, 3538, 0);
			break;
		case 87151:
			c.getPA().spellTeleport(2906, 9810, 0);
			break;
		case 87152:
			c.getPA().spellTeleport(2909, 9737, 0);
			break;
		case 87153:
			c.getPA().spellTeleport(2883, 5309, 2);
			break;
		case 87154:
			c.getPA().spellTeleport(2141, 5263, 0);
			break;
		case 87155:
			break;
			/**
			 * PvM Teleports
			 */
		case 31081://rock crabs
			c.getPA().spellTeleport(2677, 3714, 0);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 31084://yaks
			c.getPA().spellTeleport(2326, 3804, 0);//yaks
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 31087://knights hall
			c.getPA().spellTeleport(2907, 9714, 0);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 31090://snakes
			c.getPA().spellTeleport(3253, 2785, 0);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;

		case 73126://brim
			c.getPA().spellTeleport(2713, 9564, 0);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 73129://daga
			c.getPA().spellTeleport(2442, 10147, 0);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 73132://slayer tower
			c.getPA().spellTeleport(3429, 3538, 0);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 73135://taverly
			c.getPA().spellTeleport(2884, 9799, 0);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 31145://glacor
			c.getPA().spellTeleport(3297, 9824, 0);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 31148://tormented demons
			c.getPA().spellTeleport(2540, 5777, 0);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 31151://jadinko
			c.getPA().spellTeleport(3297, 9824, 0);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 31154://frost drags
			c.getPA().spellTeleport(2654, 3991, 1);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 31157://revanents
			c.getPA().spellTeleport(3222, 3682, 0);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 31160://jadinko
			c.getPA().spellTeleport(2141, 5263, 0);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 110002:// Corporeal Beast
			c.getPA().spellTeleport(2913, 4385, 0);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 110005:// NEX
			c.getPA().spellTeleport(2902, 5204, 0);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
		case 110008:// God Wars
			c.getPA().spellTeleport(2883, 5309, 2);
			if (c.playerMagicBook == 0)//normal magebook
				c.setSidebarInterface(6, 1151);
			else if (c.playerMagicBook == 1)//ancient magebook
				c.setSidebarInterface(6, 12855);
			else if (c.playerMagicBook == 2)//lunar magebook
				c.setSidebarInterface(6, 29999);
			break;
			//END OF PvM TELES
		case 31066:
			c.setSidebarInterface(6, 8040);
			break;
		case 31070:
			c.setSidebarInterface(6, 8811);
			break;
		case 31074:
			c.setSidebarInterface(6, 8120);
			break;
		case 31078:
			c.setSidebarInterface(6, 8560);
			break;
		case 31093:
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : 
				c.playerMagicBook == 1 ? 12855 : 
					c.playerMagicBook == 2 ? 29999 : 12855);
			/*if (c.playerMagicBook == 0) {//normal magebook
				c.setSidebarInterface(6, 1151);
			} else if (c.playerMagicBook == 1) {//ancient magebook
				c.setSidebarInterface(6, 12855);
			} else if (c.playerMagicBook == 2){ //lunar magebook
				c.setSidebarInterface(6, 29999);
			}*/
			break;
		case 31096:
			c.setSidebarInterface(6, 8000);
			break;
			/**
			 * Congrats interface
			 */

		case 203034:
		case 89218:
			c.getPA().closeAllWindows();
			break;

			/**
			 * Curse Prayers
			 */
		case 87231: // thick skin
			c.getCurse().activateCurse(0);
			return;
		case 87233: // burst of str
			c.getCurse().activateCurse(1);
			break;
		case 87235: // charity of thought
			c.getCurse().activateCurse(2);
			break;
		case 87237: // range
			c.getCurse().activateCurse(3);
			break;
		case 87239: // mage
			c.getCurse().activateCurse(4);
			break;
		case 87241: // rockskin
			c.getCurse().activateCurse(5);
			break;
		case 87243: // super human
			c.getCurse().activateCurse(6);
			break;
		case 87245: // defmage
			if (c.curseActive[7]) {
				c.curseActive[7] = false;
				c.getPA().sendFrame36(88, 0);
				c.headIcon = -1;
				c.getPA().requestUpdates();
			} else {
				c.getCurse().activateCurse(7);
				c.getPA().sendFrame36(90, 0); // defmellee
				c.getPA().sendFrame36(89, 0);// defrang
				c.getPA().sendFrame36(97, 0);// soulsplit
				c.getPA().sendFrame36(96, 0);// warth
				c.getPA().sendFrame36(88, 1);// deflmag
			}
			break;
		case 87247: // defrng
			if (c.curseActive[8]) {
				c.getPA().sendFrame36(89, 0);
				c.curseActive[8] = false;
				c.headIcon = -1;
				c.getPA().requestUpdates();
			} else {
				c.getCurse().activateCurse(8);
				c.getPA().sendFrame36(90, 0); // defmellee
				c.getPA().sendFrame36(89, 1);// defrang
				c.getPA().sendFrame36(88, 0);// deflmag
				c.getPA().sendFrame36(97, 0);// soulsplit
				c.getPA().sendFrame36(96, 0);// warth
			}
			break;
		case 87249:// defmel
			if (c.curseActive[9]) {
				c.getPA().sendFrame36(90, 0);
				c.curseActive[9] = false;
				c.headIcon = -1;
				c.getPA().requestUpdates();
			} else {
				c.getCurse().activateCurse(9);
				c.getPA().sendFrame36(90, 1); // defmellee
				c.getPA().sendFrame36(89, 0);// defrang
				c.getPA().sendFrame36(88, 0);// deflmag
				c.getPA().sendFrame36(97, 0);// soulsplit
				c.getPA().sendFrame36(96, 0);// warth
			}
			break;

		case 87251: // leeech attack
			if (c.curseActive[10]) {
				c.getPA().sendFrame36(91, 0); // str
				c.curseActive[10] = false;
			} else {
				c.getCurse().activateCurse(10);
				c.curseActive[19] = false;
				c.getPA().sendFrame36(91, 1); // attack leech
				c.getPA().sendFrame36(105, 0);// turmoil
			}
			break;
		case 87253: // leech range
			if (c.curseActive[11]) {
				c.getPA().sendFrame36(103, 0); // str
				c.curseActive[11] = false;
			} else {
				c.getCurse().activateCurse(11);
				c.curseActive[19] = false;
				c.getPA().sendFrame36(105, 0);// turmoil
				c.getPA().sendFrame36(103, 1); // range
			}
			break;
		case 87255: // leech magic
			if (c.curseActive[12]) {
				c.getPA().sendFrame36(104, 0); // str
				c.curseActive[12] = false;
			} else {
				c.getCurse().activateCurse(12);
				c.curseActive[19] = false;
				c.getPA().sendFrame36(105, 0);// turmoil
				c.getPA().sendFrame36(104, 1); // mage
			}
			break;
		case 88001: // leech def
			if (c.curseActive[13]) {
				c.getPA().sendFrame36(92, 0); // str
				c.curseActive[13] = false;
			} else {
				c.getCurse().activateCurse(13);
				c.curseActive[19] = false;
				c.getPA().sendFrame36(105, 0);// turmoil
				c.getPA().sendFrame36(92, 1); // def
			}
			break;
		case 88003: // leech str
			if (c.curseActive[14]) {
				c.getPA().sendFrame36(93, 0); // str
				c.curseActive[14] = false;
			} else {
				c.getCurse().activateCurse(14);
				c.curseActive[19] = false;
				c.getPA().sendFrame36(105, 0);// turmoil
				c.getPA().sendFrame36(93, 1); // str
			}
			break;

		case 88007: // protect from magic
			if (c.curseActive[16]) {
				c.getPA().sendFrame36(95, 0); // str
				c.curseActive[16] = false;
			} else {
				c.getCurse().activateCurse(16);
				c.curseActive[19] = false;
				c.getPA().sendFrame36(105, 0);// turmoil
				c.getPA().sendFrame36(95, 1); // def
			}
			return;
		case 88009: // protect from range
			if (c.curseActive[17]) {
				c.getPA().sendFrame36(96, 0);
				c.curseActive[17] = false;
				c.headIcon = -1;
				c.getPA().requestUpdates();
			} else {
				c.getCurse().activateCurse(17);
				c.getPA().sendFrame36(90, 0); // defmellee
				c.getPA().sendFrame36(89, 0);// defrang
				c.getPA().sendFrame36(88, 0);// deflmag
				c.getPA().sendFrame36(97, 0);// soulsplit
				c.getPA().sendFrame36(96, 1);// warth
			}
			break;
		case 88011: // protect from melee
			if (c.curseActive[18]) {
				c.getPA().sendFrame36(97, 0);
				c.curseActive[18] = false;
				c.headIcon = -1;
				c.getPA().requestUpdates();
			} else {
				c.getCurse().activateCurse(18);
				c.getPA().sendFrame36(90, 0); // defmellee
				c.getPA().sendFrame36(89, 0);// defrang
				c.getPA().sendFrame36(88, 0);// deflmag
				c.getPA().sendFrame36(97, 1);// soulsplit
				c.getPA().sendFrame36(96, 0);// warth
			}
			break;
		case 88013: // 44 range
			if (c.curseActive[19]) {
				c.getPA().sendFrame36(105, 0); // str
				c.curseActive[19] = false;
			} else {
				c.getCurse().activateCurse(19);
				c.curseActive[10] = false;
				c.curseActive[11] = false;
				c.curseActive[12] = false;
				c.curseActive[13] = false;
				c.curseActive[14] = false;
				c.getPA().sendFrame36(91, 0); // attack leech
				c.getPA().sendFrame36(105, 1);// turmoil
				c.getPA().sendFrame36(93, 0); // str
				c.getPA().sendFrame36(92, 0); // def
				c.getPA().sendFrame36(104, 0); // mage
				c.getPA().sendFrame36(103, 0); // range
				c.getPA().sendFrame36(95, 0);// spec
				c.getPA().sendFrame36(96, 0);// run
			}
			break;

			/** End of curse prayers **/

			/**
			 * Cape customizing
			 */

		case 113074:
			c.getPA().showInterface(14000);
			break;

		case 113075:
			c.getPA().closeAllWindows();
			break;

		case 54178:
			CompletionistCape.changeColor(c, CompletionistCape.BLACK);
			break;

		case 54179:
			CompletionistCape.changeColor(c, CompletionistCape.GRAY);
			break;

		case 54180:
			CompletionistCape.changeColor(c, CompletionistCape.WHITE);
			break;

		case 54181:
			CompletionistCape.changeColor(c, CompletionistCape.BLUE);
			break;

		case 54182:
			CompletionistCape.changeColor(c, CompletionistCape.GREEN);
			break;

		case 54183:
			CompletionistCape.changeColor(c, CompletionistCape.CYAN);
			break;

		case 54184:
			CompletionistCape.changeColor(c, CompletionistCape.RED);
			break;

		case 54185:
			CompletionistCape.changeColor(c, CompletionistCape.PURPLE);
			break;

		case 54186:
			CompletionistCape.changeColor(c, CompletionistCape.YELLOW);
			break;

		case 54187:
			CompletionistCape.changeColor(c, CompletionistCape.ORANGE);
			break;

		case 54188:
			c.getPA().closeAllWindows();
			break;

			/**
			 * Pking teleport interface
			 */

		case 21026: // closes interface
			c.getPA().closeAllWindows();
			break;
		case 21028: // teleports to varrock pk
			c.getPA().spellTeleport(3243, 3513, 0);
			break;
		case 21030: // teleports to Mage Bank
			c.getPA().spellTeleport(2539, 4716, 0);
			c.getPA().closeAllWindows();
			break;
		case 21032: // teleports to FunPk
			c.getPA().spellTeleport(3333, 3333, 0); // Change the coordinates to
			// your desired funpk
			c.getPA().closeAllWindows();
			break;
		case 21034: // teleports to east dragons
			c.getPA().spellTeleport(3339, 3659, 0);
			c.getPA().closeAllWindows();
			break;
		case 21036: // teleports to the gates
			c.getPA().spellTeleport(3158, 3952, 0);
			c.getPA().closeAllWindows();
			break;
		case 21038: // teleports to GDZ
			c.getPA().spellTeleport(3288, 3886, 0);
			c.getPA().closeAllWindows();
			break;

			/**
			 * Runecrafting interface
			 */

		case 105122: // Air
			c.getPA().spellTeleport(2846, 4834, 0);
			c.getPA().closeAllWindows();
			break;
		case 105123: // Mind
			c.getPA().spellTeleport(2786, 4838, 0);
			c.getPA().closeAllWindows();
			break;
		case 105124: // Water
			c.getPA().spellTeleport(3482, 4838, 0);
			c.getPA().closeAllWindows(); //TODO
			break;
		case 105125: // Earth
			c.getPA().spellTeleport(2660, 4839, 0);
			c.getPA().closeAllWindows();
			break;
		case 105126: // Fire
			c.getPA().spellTeleport(2584, 4836, 0);
			c.getPA().closeAllWindows();
			break;
		case 105127: // Cosmic
			c.getPA().spellTeleport(2162, 4833, 0);
			c.getPA().closeAllWindows();
			break;
		case 105128: // Chaos
			c.getPA().spellTeleport(2268, 4842, 0);
			c.getPA().closeAllWindows();
			break;
		case 105129: // Astral
			c.getPA().spellTeleport(2160, 3862, 0);
			c.getPA().closeAllWindows();
			break;
		case 105130: // Nature
			c.getPA().spellTeleport(2397, 4841, 0);
			c.getPA().closeAllWindows();
			break;
		case 105131: // Law
			c.getPA().spellTeleport(2464, 4834, 0);
			c.getPA().closeAllWindows();
			break;
		case 105132: // Death
			c.getPA().spellTeleport(2205, 4834, 0);
			c.getPA().closeAllWindows();
			break;
		case 105133: // Blood
			c.getPA().spellTeleport(2463, 4890, 1);
			c.getPA().closeAllWindows();
			break;
		case 105134: // Close Button
			c.getPA().closeAllWindows();
			break;

			/**
			 * Shop back button
			 */

		case 182014:
			//c.getPA().showInterface(26400); //TODO
			break;

			/**
			 * Skill teleport interface
			 */

		case 128237: // fishing
			c.getDH().sendOption4("Fishing Guild", "Catherby", "Fishing Colony", "Cancel");
			c.dialogueAction = 128237;
			break;
		case 128239: // cooking
			c.getPA().spellTeleport(3047, 4973, 1); // rogues den fire
			break;
		case 128241: // mining
			c.getDH().sendOption4("Essence Mine", "Novice Mining Area", "Intermediate Mining Area", "Wilderness Mining Area");
			c.dialogueAction = 128241;
			//c.getPA().spellTeleport(3022, 9739, 0); // mining guild
			//c.getPA().spellTeleport(3082, 9507, 0); // tutorial mining area
			break;
		case 128243: // smithing
			c.getPA().spellTeleport(2974, 3369, 0); // falador furnace
			break;
		case 128255: // hunter
			c.getPA().spellTeleport(2552, 2913, 0); // butterflys,implings in
			break;
		case 128245: // woodcutting
			c.getPA().spellTeleport(2725, 3491, 0); // camelot bank
			break;
		case 128247: // firemaking
			c.getPA().spellTeleport(3185, 3436, 0); // varrock west bank
			break;
		case 128249: // farming
			c.getPA().spellTeleport(2811, 3463, 0); // catherby at the patches
			break;
		case 128251: // herblore
			c.getPA().spellTeleport(2809, 3441, 0); // catherby bank
			break;
		case 129001: // runecrafting
			//c.getPA().showInterface(27000); // runecrafting interface
			c.getDH().sendDialogues(162, 0);
			break;
		case 128253: // agility
			c.getDH().sendOption4("Gnome Course", "Barbarian Course", "Wilderness Course", "Close");
			c.dialogueAction = 3938;
			break;
		case 129003: // fletching
			c.getPA().spellTeleport(2809, 3441, 0); // catherby bank
			break;
		case 129005: // crafting
			c.getPA().spellTeleport(2932, 3285, 0); // crafting guild
			break;
		case 129007: // summoning
			c.getPA().spellTeleport(2209, 5348, 0); // summoning under taverly
			break;
		case 129011: // dungeoneering
			Dungeoneering.kinshipTele(c);
			c.sendMessage("You need to buy a ring of kinship to start a dungeon.");
			break;
		case 129013: // thieving
			c.getPA().spellTeleport(2662, 3309, 0); // ardougne center
			break;
		case 129015: // slayer
			c.getPA().spellTeleport(3429, 3538, 0); // slayer tower
			break;

			/**
			 * Shop interface
			 */

		case 103034: // close
			c.getPA().closeAllWindows();
			break;

		case 103035:// general store
			c.getShops().openShop(1);
			break;

		case 103036:// weapon shop
			c.getShops().openShop(2);
			break;

		case 103037:// voting shop
			c.getShops().openShop(19);
			break;

		case 103038:// summ 1
			c.getShops().openShop(4);
			break;

		case 103039:// summ 2
			c.getShops().openShop(5);
			break;

		case 103040:// dung rewards
			c.getShops().openShop(6);
			break;

		case 103041:// armour shop
			c.getShops().openShop(7);
			break;

		case 103042:// magic supplies
			c.getShops().openShop(8);
			break;

		case 103043:// range supplies
			c.getShops().openShop(9);
			break;

		case 103044:// supply shop
			c.getShops().openShop(10);
			break;

		case 103045:// skilling shop
			c.getShops().openShop(11);
			break;

		case 103046:// slayer shop
			c.getShops().openShop(17);
			break;

		case 103047:// outfit shop
			c.getShops().openShop(20);
			break;

		case 103048:// skillcape shop
			c.getShops().openShop(14);
			break;

		case 103049:// donator shop
			c.getShops().openShop(3);
			c.sendMessage("Currently you have " + c.donatorPoints
					+ " donator points.");
			break;

			/**
			 * Action Buttons
			 */

		case 154:
			Resting.startResting(c);
			break;

		case 118098:
			c.getPA().castVeng();
			break;

		case 150:
			if (c.autoRet == 0) {
				c.autoRet = 1;
			} else {
				c.autoRet = 0;
			}
			break;

		case 59100: // items on death
			ItemsOnDeath.open(c);
			break;

		case 59103:
			if (c.xpLock == true) {
				c.xpLock = false;
				c.sendMessage("[@red@Server@bla@] You will now gain experience.");
				c.getPA().sendFrame126("@gre@Unlocked", 15226);
			} else {
				c.xpLock = true;
				c.sendMessage("[@red@Server@bla@] You will no longer gain experience.");
				c.getPA().sendFrame126("@gre@Locked", 15226);
			}
		break;

			/**
			 * Summoning
			 **/

		case 53152:
			Cooking.getAmount(c, 1);
			break;
		case 53151:
			Cooking.getAmount(c, 5);
			break;
		case 53150:
			Cooking.getAmount(c, 10);
			break;
		case 53149:
			Cooking.getAmount(c, 28);
			break;

		case 9118:
			c.getPA().removeAllWindows();
			break;

			//case 180: // Special Attack
		case 70098:
		case 70103:
			if (c.familiarID > 0) {
				c.getSSpec();
				SummoningSpecials.castFamiliarSpecial(c);
				c.getSSpec().castFamiliarCombatSpecial(c,
						NPCHandler.npcs[c.familiarIndex]);
			} else {
				c.sendMessage("You must have a familiar to use this.");
			}
			break;
		case 70115: // Summoning - Call follower
		case 183:
			if (c.familiarID > 0) {
				Summoning.callFamiliar(c, NPCHandler.npcs[c.familiarIndex]);
			} else {
				c.sendMessage("You must have a familiar to use this.");
			}
			break;

		case 182:
			if (c.familiarID == 6806 || c.familiarID == 6994
			|| c.familiarID == 6867 || c.familiarID == 6794
			|| c.familiarID == 6815 || c.familiarID == 6873
			|| c.familiarID == 6818 || c.familiarID == 6820) {
				c.getBOB().store();
			} else {
				c.sendMessage("You must have a beast of burden familiar to use this.");
			}
			break;

			/** Dueling **/
		case 26065: // no forfeit
		case 26040:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(0);
			break;

		case 26066: // no movement
		case 26048:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(1);
			break;

		case 26069: // no range
		case 26042:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(2);
			break;

		case 26070: // no melee
		case 26043:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(3);
			break;

		case 26071: // no mage
		case 26041:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(4);
			break;

		case 26072: // no drinks
		case 26045:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(5);
			break;

		case 26073: // no food
		case 26046:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(6);
			break;

		case 26074: // no prayer
		case 26047:
			Prayer.closeAllPrayers(c);
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(7);
			break;

		case 26076: // obsticals
		case 26075:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(8);
			break;

		case 2158: // fun weapons
		case 2157:
			c.sendMessage("There are no fun weapons!");
			break;

		case 30136: // sp attack
		case 30137:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(10);
			break;

		case 53245: // no helm
			c.duelSlot = 0;
			c.getTradeAndDuel().selectEquipmentRule(11);
			c.getTradeAndDuel().selectRule(11);
			break;

		case 53246: // no cape
			c.duelSlot = 1;
			c.getTradeAndDuel().selectEquipmentRule(12);
			c.getTradeAndDuel().selectRule(12);
			break;

		case 53247: // no ammy
			c.duelSlot = 2;
			c.getTradeAndDuel().selectEquipmentRule(13);
			c.getTradeAndDuel().selectRule(13);
			break;

		case 53249: // no weapon.
			c.duelSlot = 3;
			c.getTradeAndDuel().selectEquipmentRule(14);
			c.getTradeAndDuel().selectRule(14);
			break;

		case 53250: // no body
			c.duelSlot = 4;
			c.getTradeAndDuel().selectEquipmentRule(15);
			c.getTradeAndDuel().selectRule(15);
			break;

		case 53251: // no shield
			c.duelSlot = 5;
			c.getTradeAndDuel().selectEquipmentRule(16);
			c.getTradeAndDuel().selectRule(16);
			break;

		case 53252: // no legs
			c.duelSlot = 7;
			c.getTradeAndDuel().selectEquipmentRule(17);
			c.getTradeAndDuel().selectRule(17);
			break;

		case 53255: // no gloves
			c.duelSlot = 9;
			c.getTradeAndDuel().selectEquipmentRule(18);
			c.getTradeAndDuel().selectRule(18);
			break;

		case 53254: // no boots
			c.duelSlot = 10;
			c.getTradeAndDuel().selectEquipmentRule(19);
			c.getTradeAndDuel().selectRule(19);
			break;

		case 53253: // no rings
			c.duelSlot = 12;
			c.getTradeAndDuel().selectEquipmentRule(20);
			c.getTradeAndDuel().selectRule(20);
			break;

		case 53248: // no arrows
			c.duelSlot = 13;
			c.getTradeAndDuel().selectEquipmentRule(21);
			c.getTradeAndDuel().selectRule(21);
			break;

		case 70109: // Summoning - Withdraw BoB
		case 185: // Summoning - Take BoB
			if (c.familiarID > 0) {
				switch (c.familiarID) {
				case 6806: // thorny snail
				case 6807:
				case 6994: // spirit kalphite
				case 6995:
				case 6867: // bull ant
				case 6868:
				case 6794: // spirit terrorbird
				case 6795:
				case 6815: // war tortoise
				case 6816:
				case 6873: // pack yak
				case 6874:
				case 6818: // abyssal thing
				case 6819:
				case 6820: // another abyssaly
				case 6821:
					for (int i = 0; i < c.bobSlotCount; i++) {
						if (c.bobItems[i] > 0) {
							c.getPA().removeBoBItems(i, 1);
							//c.setAnimation(Animation.create(827));
							c.startAnimation(827);
							c.stopMovement();
						}
					}
					break;
				}
			} else {
				c.sendMessage("You must have a familiar to use this.");
			}
			break;

		case 70118: // Summoning - Dismiss familiar
		case 181:

			c.getDH().sendDialogues(4054, 1);
			break;

		case 186: // Follower Details
			if (c.familiarID > 0) {
				c.getPA().sendFrame106(16);
			} else {
				c.sendMessage("You must have a familiar to use this.");
			}
			break;

		case 70112: // Summoning - Renew familiar
		case 184:
			if (c.familiarID > 0) {
				Summoning.renewFamiliar(c);
			} else {
				c.sendMessage("You must have a familiar to use this.");
			}
			break;

		case 89223: // Deposit Inventory
		case 82020:
		case 85252:
			if (c.isBanking)
				for (int i = 0; i < c.playerItems.length; i++) {
					if (c.playerItems[i] > 0 && c.playerItemsN[i] > 0)
						c.getItems().bankItem(c.playerItems[i], i,
								c.playerItemsN[i]);
				}
			break;

		case 86004: // Summoning - Withdraw BoB to bank
			if (c.familiarID > 0) {
				switch (c.familiarID) {
				case 6806: // thorny snail
				case 6807:
				case 6994: // spirit kalphite
				case 6995:
				case 6867: // bull ant
				case 6868:
				case 6794: // spirit terrorbird
				case 6795:
				case 6815: // war tortoise
				case 6816:
				case 6873: // pack yak
				case 6874:
				case 6818: // abyssal thing
				case 6819:
				case 6820: // another abyssaly
				case 6821:
					for (int i = 0; i < c.bobSlotCount; i++) {
						if (c.isBanking)
						if (c.bobItems[i] > 0) {
							c.getPA().removeBoBItemsToBank(i, 1);
							//c.setAnimation(Animation.create(827));
							c.startAnimation(827);
							c.stopMovement();
						}
					}
					break;
				}
			} else {
				c.sendMessage("You must have a familiar to use this.");
			}
			break;

		case 89227:
		case 82024:
		case 86000:
			c.getItems().bankEquipment();
			break;

		case 85244: //Search Bank
			c.searchTerm = "N/A";
			if (!c.searchingBank) {
				c.searchingBank = true;
				c.getPA().sendFrame126("Enter the name of the item you wish to search for:", 9695);
				c.getOutStream().createFrame(187);
				c.flushOutStream();
			} else {
				c.searchingBank = false;
				c.bankingTab = 0;
				c.getPA().openUpBank(c.bankingTab);
			}
			break;

	case 58074:
		c.getBankPin().close();
		break;

	case 58025:
	case 58026:
	case 58027:
	case 58028:
	case 58029:
	case 58030:
	case 58031:
	case 58032:
	case 58033:
	case 58034:
		c.getBankPin().pinEnter(actionButtonId);
		break;

		case 85240://SwapItem
		case 82028:
			//TODO
		break;

		case 20174://BankPin
			if (c.wantsPIN == 0) {
				c.getDH().sendDialogues(4050, 494);
			}
			if (c.wantsPIN == 1) {
				c.getDH().sendDialogues(4052, 494);
			}
		break;

			/**
			 * Home Teleport
			 */
		case 50056:
		case 117048:
		case 4171:
			c.getPA().spellTeleport(Config.HOME_X, Config.HOME_Y, 0);
			break;

			/**
			 * Pouch Creation
			 **/
		case 155031:
		case 155034:
		case 155037:
		case 155040:
		case 155043:
		case 155046:
		case 155049:
		case 155052: // Spirit scorpion
		case 155055: // spirit tz-kih
		case 155058: // albino rat
		case 155061: // spirit kalphite
		case 155064: // compost mound
		case 155067: // giant chinchompa
		case 155070: // vampire bat
		case 155073: // honey badger
		case 155076: // beaver
		case 155079: // void ravager
		case 155082: // void spinner
		case 155085: // void torcher
		case 155088: // void shifter
		case 155091: // bronze minotaur
		case 155094: // bull ant
		case 155097: // macaw
		case 155100: // evil turnip
		case 155103: // Spirit Cockatrice
		case 155106: // Spirit Guthatrice
		case 155109: // Spirit Saratrice
		case 155112: // Spirit Zamatrice
		case 155115: // Spirit Pengatrice
		case 155118: // Spirit Coraxatrice
		case 155121: // Spirit Vulatrice
		case 155124: // Iron Minotaur
		case 155127: // pyrelord
		case 155130: // magpie
		case 155133: // bloated leech
		case 155136: // spirit terrorbird
		case 155139: // abyssal parasite
		case 155142: // spirit jelly
		case 155145: // steel minotaur
		case 155148: // ibis
		case 155151: // spirit kyatt
		case 155154: // spirit larupia
		case 155157: // spirit graahk
		case 155160: // karamthulhu overlord
		case 155163: // smoke devil
		case 155166: // abyssal lurker
		case 155169: // spirit cobra
		case 155172: // stranger plant
		case 155175: // mithril minotaur
		case 155178: // barker toad
		case 155181: // war tortoise
		case 155184: // bunyip
		case 155187: // fruit bat
		case 155190: // ravenous locust
		case 155193: // arctic bear
		case 155196: // phoenix
		case 155199: // obby golem
		case 155202: // granite crab
		case 155205: // praying mantis
		case 155208: // forge regent
		case 155211: // addy minotaur
		case 155214: // talon beast
		case 155217: // giant ent
		case 155220: // fire titan
		case 155223: // moss titan
		case 155226: // ice titan
		case 155229: // hydra
		case 155232: // spirit dagannoth
		case 155235: // lava titan
		case 155238: // swamp titan
		case 155241: // rune minotaur
		case 155244: // unicorn stallion
		case 155247: // geyser titan
		case 155250: // wolpertinger
		case 155253: // abyssal titan
			PouchCreation.makeSummoningPouch(c, actionButtonId);
			break;

		case 156000: // iron titan
		case 156003: // pack yak
		case 156006: // steel titan
			PouchCreation.makeMorePouches(c, actionButtonId);
			break;

			/**
			 * Spellbook Teleports
			 */

		case 4140: // varock tele
		case 50235:
		case 117112:
			//c.setSidebarInterface(6, 8000);
			//c.getDH().sendOption5("Ice Strykewyrms", "Jungle Strykewyrms", "Desert Strykewyrms", "Ice Cave", "");
			//c.dialogueAction = 117112;
			// c.getPA().spellTeleport(2675, 3710, 0);
			break;

		case 4143: // lumby tele
		case 50245:
		case 117123:
			//c.getDH().sendDialogues(55, 1);
			// c.getPA().startTeleport(3222, 3218, 0, "modern");
			break;

		case 4146: // fally tele
		case 50253:
		case 117131:
			//c.getPA().showInterface(5400);
			//c.getDH().sendDialogues(58, 1);
			//c.getPA().startTeleport(2964, 3378, 0, "modern");
			break;

		case 4150: // cammy tele
		case 51005:
		case 117154:
			//c.getPA().showInterface(33000);
			//c.getPA().startTeleport(2335, 3689, 0, "modern");
			break;

		case 6004: // ardougne tele
			// c.getPA().showInterface(33000);
			// c.getDH().sendDialogues(101, 1);
			//c.getPA().startTeleport(2662, 3305, 0, "modern");
			break;

		case 6005:
			//c.getPA().startTeleport(3087, 3500, 0, "modern");
			break;

		case 29031: //Bounty Hunter Target Teleport
			//BountyHunter.targetTeleport(c);
			break;

		case 72038:
			//c.getPA().startTeleport(2720, 2763, 0, "modern");
			break;

		case 51013: // D in ancient
			//c.getPA().startTeleport(3161, 3671, 0, "ancient");
			break;

		case 51023: //
			//c.getPA().startTeleport(3156, 3666, 0, "ancient");
			break;

		case 51031:
			//c.getPA().startTeleport(3288, 3886, 0, "ancient");
			break;

		case 51039:
			//c.getPA().startTeleport(2977, 3873, 0, "ancient");
			break;

		case 55095:
			if (c.destroy == 1) {
				c.getPA().destroyBind(c.droppedItem);
				c.droppedItem = -1;
				c.destroy = 0;
			} else if (c.destroy == 2) {
				c.getPA().dropItem(c.droppedItem);
				c.droppedItem = -1;
				c.destroy = 0;
				break;
			}
			break;

		case 55096:
			c.getPA().removeAllWindows();
			c.droppedItem = -1;
			c.destroy = 0;
			break;

		case 30088: // Dclaws
		case 9125: // Accurate
		case 6221: // range accurate
		case 48010: // flick (whip)
		case 21200: // spike (pickaxe)
		case 1080: // bash (staff)
		case 6168: // chop (axe)
		case 6236: // accurate (long bow)
		case 17102: // accurate (darts)
		case 8234: // stab (dagger)
		case 22230: // unarmed
			c.fightMode = 0;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;

		case 30089: // Dclaws
		case 9126: // Defensive
		case 48008: // deflect (whip)
		case 21201: // block (pickaxe)
		case 1078: // focus - block (staff)
		case 6169: // block (axe)
		case 33019: // fend (hally)
		case 18078: // block (spear)
		case 8235: // block (dagger)
			// case 22231: //unarmed
		case 22228: // unarmed
			c.fightMode = 1;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;

		case 9127: // Controlled
		case 48009: // lash (whip)
		case 33018: // jab (hally)
		case 6234: // longrange (long bow)
		case 6219: // longrange
		case 18077: // lunge (spear)
		case 18080: // swipe (spear)
		case 18079: // pound (spear)
		case 17100: // longrange (darts)
			c.fightMode = 3;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;

		case 30090: // Dclaws
		case 30091: // Dclaws
		case 9128: // Aggressive
		case 6220: // range rapid
		case 21203: // impale (pickaxe)
		case 21202: // smash (pickaxe)
		case 1079: // pound (staff)
		case 6171: // hack (axe)
		case 6170: // smash (axe)
		case 33020: // swipe (hally)
		case 6235: // rapid (long bow)
		case 17101: // repid (darts)
		case 8237: // lunge (dagger)
		case 8236: // slash (dagger)
		case 22229: // unarmed
			c.fightMode = 2;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;

		case 10239:
			if (c.isArrowing) {
				ArrowHandler.fletchArrow(c, 1);
				return;
			} else if (c.isStringing) {
				StringingHandler.stringBow(c, c.stringu, 1);
				return;
			} else if (Constants.playerBones(c, c.boneId)) {
				Altar.sacrificeBone(c, 1);
				return;
			} else if (c.secondHerb) {
				Herblore.finishPotion(c, 1);
				return;
			} else if (!c.secondHerb) {
				Herblore.finishUnfinished(c, 1);
			}
			break;
		case 10238:
			if (c.isArrowing) {
				ArrowHandler.fletchArrow(c, 5);
				return;
			} else if (c.isStringing) {
				StringingHandler.stringBow(c, c.stringu, 5);
				return;
			} else if (Constants.playerBones(c, c.boneId)) {
				Altar.sacrificeBone(c, 5);
				return;
			} else if (c.secondHerb) {
				Herblore.finishPotion(c, 5);
				return;
			} else if (!c.secondHerb) {
				Herblore.finishUnfinished(c, 5);
			}
			break;
		case 6212:
			if (c.isArrowing && !c.isStringing) {
				ArrowHandler.fletchArrow(c, 10);
				return;
			} else if (c.isStringing && !c.isArrowing) {
				StringingHandler.stringBow(c, c.stringu, 10);
				return;
			} else if (Constants.playerBones(c, c.boneId)) {
				Altar.sacrificeBone(c, 10);
				return;
			} else if (c.secondHerb) {
				Herblore.finishPotion(c, c.getItems().getItemAmount(c.newItem));
			} else {
				Herblore.finishUnfinished(c,
						c.getItems().getItemAmount(c.doingHerb));
			}
			break;
		case 6211:
			if (c.isArrowing) {
				ArrowHandler.fletchArrow(c, 25);
				c.sendMessage("You will fletch until you can't fletch no more.");
				return;
			} else if (c.isStringing) {
				StringingHandler.stringBow(c, c.stringu, 25);
				c.sendMessage("You will only fletch this up to 25 times.");
				return;
			} else if (Constants.playerBones(c, c.boneId)) {
				Altar.sacrificeBone(c, 100);
				return;
			} else if (c.secondHerb) {
				Herblore.finishPotion(c, c.getItems().getItemAmount(c.newItem));
			} else {
				Herblore.finishUnfinished(c,
						c.getItems().getItemAmount(c.doingHerb));
			}
			break;

			/**
			 * 
			 * Smithing - Smelting interfaces
			 */
		case 15147:
			if (c.smeltInterface) {
				c.smeltType = 2349;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 15151:
			if (c.smeltInterface) {
				c.smeltType = 2351;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 15159:
			if (c.smeltInterface) {
				c.smeltType = 2353;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 29017:
			if (c.smeltInterface) {
				c.smeltType = 2359;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 29022:
			if (c.smeltInterface) {
				c.smeltType = 2361;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 29026:
			if (c.smeltInterface) {
				c.smeltType = 2363;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
			break;

		case 151045:
			c.getPA().showInterface(39700);
			break;

		case 59111: // customize
			//c.sendMessage("This button is still under construction.");
			break;

		case 155026:
			c.getPA().showInterface(38700);
			break;

			// Equipment Bonuses interface?
		case 59097:
			c.getPA().showInterface(15106);
			c.getItems().writeBonus();
			break;

		case 59004:
			c.getPA().removeAllWindows();
			break;

			// Clan Chat - Join Clan
		case 62137:
			if (c.clanId >= 0) {
				c.sendMessage("You are already in a clan.");
				break;
			}
			if (c.getOutStream() != null) {
				c.getOutStream().createFrame(187);
				c.flushOutStream();
			}
			break;

			// Auto Casting
		case 1093:
		case 1094:
		case 1097:
			if (c.autocastId > 0) {
				c.getPA().resetAutocast();
			} else {
				if (c.playerMagicBook == 1) {
					if (c.playerEquipment[c.playerWeapon] == 4675) {
						c.setSidebarInterface(0, 1689);
					} else {
						c.sendMessage("You can't autocast ancients without an ancient staff.");
					}
				} else if (c.playerMagicBook == 0) {
					if (c.playerEquipment[c.playerWeapon] == 4170) {
						c.setSidebarInterface(0, 12050);
					} else {
						c.setSidebarInterface(0, 1829);
					}
				}

			}
			break;

		case 26010:
			c.getPA().resetAutocast();
			break;

		case 17200:
			if (c.randomBarrows == 0 || c.randomBarrows == 2) {
				c.getPA().removeAllWindows();
				c.sendMessage("You got the riddle wrong, and it locks back up.");
				Dungeon.wrongPuzzle = true;
				break;
			} else {
				if (c.absY == 9683) {
					c.sendMessage("You hear the doors locking mechanism grind open.");
					c.getPA().object(6727, c.objectX, c.objectY, -2, 0);
					c.getPA().removeAllWindows();
					c.getPA().walkTo(0, 1);
					EventManager.getSingleton().addEvent(new Event() {
						@Override
						public void execute(EventContainer p) {
							c.getPA().object(6727, c.objectX, c.objectY, -1, 0);
							p.stop();
						}
					}, 500);
				} else if (c.absY == 9706) {
					c.sendMessage("You hear the doors locking mechanism grind open.");
					c.getPA().object(6727, c.objectX, c.objectY, 2, 0);
					c.getPA().removeAllWindows();
					c.getPA().walkTo(0, -1);
					EventManager.getSingleton().addEvent(new Event() {
						@Override
						public void execute(EventContainer p) {
							c.getPA().object(6727, c.objectX, c.objectY, 1, 0);
							p.stop();
						}
					}, 500);
				} else if (c.absX == 3540) {
					c.sendMessage("You hear the doors locking mechanism grind open.");
					c.getPA().object(6727, c.objectX, c.objectY, 1, 0);
					c.getPA().removeAllWindows();
					c.getPA().walkTo(1, 0);
					EventManager.getSingleton().addEvent(new Event() {
						@Override
						public void execute(EventContainer p) {
							c.getPA().object(6743, c.objectX, c.objectY, 0, 0);
							p.stop();
						}
					}, 500);
				} else {
					c.sendMessage("You hear the doors locking mechanism grind open.");
					c.getPA().object(6727, c.objectX, c.objectY, -1, 0);
					c.getPA().removeAllWindows();
					c.getPA().walkTo(-1, 0);
					EventManager.getSingleton().addEvent(new Event() {
						@Override
						public void execute(EventContainer p) {
							c.getPA().object(6743, c.objectX, c.objectY, 2, 0);
							p.stop();
						}
					}, 500);
				}
			}
			break;

		case 17199:
			if (c.randomBarrows == 1 || c.randomBarrows == 2) {
				c.getPA().removeAllWindows();
				c.sendMessage("You got the riddle wrong, and it locks back up.");
				Dungeon.wrongPuzzle = true;
				break;
			} else {
				if (c.absY == 9683) {
					c.sendMessage("You hear the doors locking mechanism grind open.");
					c.getPA().object(6727, c.objectX, c.objectY, -2, 0);
					c.getPA().removeAllWindows();
					c.getPA().walkTo(0, 1);
					EventManager.getSingleton().addEvent(new Event() {
						@Override
						public void execute(EventContainer p) {
							c.getPA().object(6727, c.objectX, c.objectY, -1, 0);
							p.stop();
						}
					}, 500);
				} else if (c.absY == 9706) {
					c.sendMessage("You hear the doors locking mechanism grind open.");
					c.getPA().object(6727, c.objectX, c.objectY, 2, 0);
					c.getPA().removeAllWindows();
					c.getPA().walkTo(0, -1);
					EventManager.getSingleton().addEvent(new Event() {
						@Override
						public void execute(EventContainer p) {
							c.getPA().object(6727, c.objectX, c.objectY, 1, 0);
							p.stop();
						}
					}, 500);
				} else if (c.absX == 3540) {
					c.sendMessage("You hear the doors locking mechanism grind open.");
					c.getPA().object(6727, c.objectX, c.objectY, 1, 0);
					c.getPA().removeAllWindows();
					c.getPA().walkTo(1, 0);
					EventManager.getSingleton().addEvent(new Event() {
						@Override
						public void execute(EventContainer p) {
							c.getPA().object(6743, c.objectX, c.objectY, 0, 0);
							p.stop();
						}
					}, 500);
				} else {
					c.sendMessage("You hear the doors locking mechanism grind open.");
					c.getPA().object(6727, c.objectX, c.objectY, -1, 0);
					c.getPA().removeAllWindows();
					c.getPA().walkTo(-1, 0);
					EventManager.getSingleton().addEvent(new Event() {
						@Override
						public void execute(EventContainer p) {
							c.getPA().object(6743, c.objectX, c.objectY, 2, 0);
							p.stop();
						}
					}, 500);
				}
			}
			break;
		case 17198:
			if (c.randomBarrows == 0 || c.randomBarrows == 1) {
				c.getPA().removeAllWindows();
				c.sendMessage("You got the riddle wrong, and it locks back up.");
				Dungeon.wrongPuzzle = true;
				break;
			} else {
				if (c.absY == 9683) {
					c.sendMessage("You hear the doors locking mechanism grind open.");
					c.getPA().object(6727, c.objectX, c.objectY, -2, 0);
					c.getPA().removeAllWindows();
					c.getPA().walkTo(0, 1);
					EventManager.getSingleton().addEvent(new Event() {
						@Override
						public void execute(EventContainer p) {
							c.getPA().object(6727, c.objectX, c.objectY, -1, 0);
							p.stop();
						}
					}, 500);
				} else if (c.absY == 9706) {
					c.sendMessage("You hear the doors locking mechanism grind open.");
					c.getPA().object(6727, c.objectX, c.objectY, 2, 0);
					c.getPA().removeAllWindows();
					c.getPA().walkTo(0, -1);
					EventManager.getSingleton().addEvent(new Event() {
						@Override
						public void execute(EventContainer p) {
							c.getPA().object(6727, c.objectX, c.objectY, 1, 0);
							p.stop();
						}
					}, 500);
				} else if (c.absX == 3540) {
					c.sendMessage("You hear the doors locking mechanism grind open.");
					c.getPA().object(6727, c.objectX, c.objectY, 1, 0);
					c.getPA().removeAllWindows();
					c.getPA().walkTo(1, 0);
					EventManager.getSingleton().addEvent(new Event() {
						@Override
						public void execute(EventContainer p) {
							c.getPA().object(6743, c.objectX, c.objectY, 0, 0);
							p.stop();
						}
					}, 500);
				} else {
					c.sendMessage("You hear the doors locking mechanism grind open.");
					c.getPA().object(6727, c.objectX, c.objectY, -1, 0);
					c.getPA().removeAllWindows();
					c.getPA().walkTo(-1, 0);
					EventManager.getSingleton().addEvent(new Event() {
						@Override
						public void execute(EventContainer p) {
							c.getPA().object(6743, c.objectX, c.objectY, 2, 0);
							p.stop();
						}
					}, 500);
				}
			}
			break;

		case 72254:
			if (!c.skillCapeEquipped()) {
				c.sendMessage("You need a skill cape equipped to perform this animation.");
				return;
			}
			int capes[] = { 9784, 9763, 9793, 9796, 9766, 9781, 9799, 9790,
					9802, 9808, 9748, 9754, 9811, 9778, 9787, 9775, 9760, 9757,
					9805, 9772, 9769, 9751, 9949, 9813, 12170 };
			int emotes[] = { 4937, 4939, 4941, 4943, 4947, 4949, 4951, 4953,
					4955, 4957, 4959, 4961, 4963, 4965, 4967, 4969, 4979, 4973,
					4975, 4977, 4971, 4981, 5158, 4945, 8525 };
			int gfx[] = { 812, 813, 814, 815, 817, 818, 819, 820, 821, 822,
					823, 824, 825, 826, 827, 835, 829, 832, 831, 830, 833, 828,
					907, 816, 1515 };
			for (int i = 0; i < capes.length; i++) {
				if (c.playerEquipment[c.playerCape] == capes[i]
						|| c.playerEquipment[c.playerCape] == capes[i] - 1) { // -1
					// allows
					// the
					// nontrimmed
					// capes
					// to
					// have
					// emotes
					// too.
					//c.setAnimation(Animation.create(emotes[i]));
					c.startAnimation(emotes[i]);
					c.gfx0(gfx[i]);
				}
}
if(c.playerEquipment[c.playerCape] == 19710) {
			c.getPA().dungemote(c);
}
			break;

		case 10252:
			c.sendMessage("You select Attack");
			c.antiqueSelect = 0;
			break;
		case 10253:
			c.sendMessage("You select Strength");
			c.antiqueSelect = 2;
			break;
		case 10254:
			c.sendMessage("You select Ranged");
			c.antiqueSelect = 4;
			break;
		case 10255:
			c.sendMessage("You select Magic");
			c.antiqueSelect = 6;
			break;
		case 11000:
			c.sendMessage("You select Defence");
			c.antiqueSelect = 1;
			break;
		case 11001:
			c.sendMessage("You select Hitpoints");
			c.antiqueSelect = 3;
			break;
		case 11002:
			c.sendMessage("You select Prayer");
			c.antiqueSelect = 5;
			break;
		case 11003:
			c.sendMessage("You select Agility");
			c.antiqueSelect = c.playerAgility;
			break;
		case 11004:
			c.sendMessage("You select Herblore");
			c.antiqueSelect = c.playerHerblore;
			break;
		case 11005:
			c.sendMessage("You select Thieving");
			c.antiqueSelect = c.playerThieving;
			break;
		case 11006:
			c.sendMessage("You select Crafting");
			c.antiqueSelect = c.playerCrafting;
			break;
		case 11007:
			c.sendMessage("You select Runecrafting");
			c.antiqueSelect = c.playerRunecrafting;
			break;
		case 47002:
			c.sendMessage("You select Slayer");
			c.antiqueSelect = c.playerSlayer;
			break;
		case 54090:
			c.sendMessage("You select Farming");
			c.antiqueSelect = c.playerFarming;
			break;
		case 11008:
			c.sendMessage("You select Mining");
			c.antiqueSelect = c.playerMining;
			break;
		case 11009:
			c.sendMessage("You select Smithing");
			c.antiqueSelect = c.playerSmithing;
			break;
		case 11010:
			c.sendMessage("You select Fishing");
			c.antiqueSelect = c.playerFishing;
			break;
		case 11011:
			c.sendMessage("You select Cooking");
			c.antiqueSelect = c.playerCooking;
			break;
		case 11012:
			c.sendMessage("You select Firemaking");
			c.antiqueSelect = c.playerFiremaking;
			break;
		case 11013:
			c.sendMessage("You select Woodcutting");
			c.antiqueSelect = c.playerWoodcutting;
			break;
		case 11014:
			c.sendMessage("You select Fletching");
			c.antiqueSelect = c.playerFletching;
			break;
		case 11015:
			if (!c.getItems().playerHasItem(2528)) {
				return;
			}
			c.sendMessage("You rubbed the lamp and got your Experience.");
			c.getPA().addSkillXP(Misc.random(150000), c.antiqueSelect);
			c.getItems().deleteItem(2528, 1);
			c.getPA().closeAllWindows();
			break;

			/** Specials **/
		case 29188:
			c.specBarId = 7636;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
		case 30108: // Claws
			c.specBarId = 7812;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
		case 29163:
			c.specBarId = 7611;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
		case 33033:
			c.specBarId = 8505;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
		case 29038:
			if (c.playerEquipment[c.playerWeapon] == 4153) {
				c.specBarId = 7486;
				Specials.handleGmaul(c);
			} else {
				c.specBarId = 7486;
				c.usingSpecial = !c.usingSpecial;
				c.getItems().updateSpecialBar();
			}
			break;

		case 29063:
			if (c.getCombat()
					.checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
				c.gfx0(246);
				c.forcedChat("Raarrrrrgggggghhhhhhh!");
				//c.setAnimation(Animation.create(1056));
				c.startAnimation(1056);
				c.playerLevel[2] = c.getLevelForXP(c.playerXP[2])
						+ c.getLevelForXP(c.playerXP[2]) * 15 / 100;
				c.getPA().refreshSkill(2);
				c.getItems().updateSpecialBar();
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}
			break;

		case 48023:
			c.specBarId = 12335;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
		case 29138:
			if (c.playerEquipment[c.playerWeapon] == 15486) {
				if (c.getCombat().checkSpecAmount(
						c.playerEquipment[c.playerWeapon])) {
					c.gfx0(1958);
					c.solProtect = 120;
					//c.setAnimation(Animation.create(10518));
					c.startAnimation(10518);
					c.getItems().updateSpecialBar();
					c.usingSpecial = !c.usingSpecial;
					c.sendMessage("All damage will be split into half for 1 minute.");
					c.forcedChat("I am Protected By the Light!");
					c.getPA().sendFrame126("@bla@S P E C I A L  A T T A C K",
							7562);
				} else {
					c.sendMessage("You don't have the required special energy to use this attack.");
				}
			}
			c.specBarId = 7586;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			c.specOn = !c.specOn;
			break;
		case 29113:
			c.specBarId = 7561;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
		case 29238:
			c.specBarId = 7686;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
		case 26018:
			final Player o = PlayerHandler.players[c.duelingWith];
			if (o == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}
			if (c.duelRule[0] && c.duelRule[1]) {
				c.sendMessage("Either turn off No Forfeit or No Movement!");
				break;
			}
			if (c.duelRule[2] && c.duelRule[3] && c.duelRule[4]) {
				c.sendMessage("You won't be able to attack the player with the rules you have set.");
				break;
			}
			c.duelStatus = 2;
			if (c.duelStatus == 2) {
				c.getPA().sendFrame126("Waiting for other player...", 6684);
				o.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			if (o.duelStatus == 2) {
				o.getPA().sendFrame126("Waiting for other player...", 6684);
				c.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			if (c.duelStatus == 2 && o.duelStatus == 2) {
				c.canOffer = false;
				o.canOffer = false;
				c.duelStatus = 3;
				o.duelStatus = 3;
				c.getTradeAndDuel().confirmDuel();
				o.getTradeAndDuel().confirmDuel();
			}
			break;
		case 25120:
			if (c.duelStatus == 5) {
				break;
			}
			final Player o1 = PlayerHandler.players[c.duelingWith];
			if (o1 == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}
			c.duelStatus = 4;
			if (o1.duelStatus == 4 && c.duelStatus == 4) {
				c.getTradeAndDuel().startDuel();
				o1.getTradeAndDuel().startDuel();
				o1.duelCount = 4;
				c.duelCount = 4;
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(final CycleEventContainer container) {
						if (System.currentTimeMillis() - c.duelDelay > 800
								&& c.duelCount > 0) {
							if (c.duelCount != 1) {
								c.forcedChat("" + --c.duelCount);
								c.duelDelay = System.currentTimeMillis();
							} else {
								c.damageTaken = new int[Config.MAX_PLAYERS];
								c.forcedChat("FIGHT!");
								c.duelCount = 0;
							}
						}
						if (c.duelCount == 0) {
							container.stop();
						}
					}

					@Override
					public void stop() {
					}
				}, 1);
				CycleEventHandler.getSingleton().addEvent(o1, new CycleEvent() {
					@Override
					public void execute(final CycleEventContainer container) {
						if (System.currentTimeMillis() - o1.duelDelay > 800
								&& o1.duelCount > 0) {
							if (o1.duelCount != 1) {
								o1.forcedChat("" + --o1.duelCount);
								o1.duelDelay = System.currentTimeMillis();
							} else {
								o1.damageTaken = new int[Config.MAX_PLAYERS];
								o1.forcedChat("FIGHT!");
								o1.duelCount = 0;
							}
						}
						if (o1.duelCount == 0) {
							container.stop();
						}
					}

					@Override
					public void stop() {
					}
				}, 1);
				c.duelDelay = System.currentTimeMillis();
				o1.duelDelay = System.currentTimeMillis();
			} else {
				c.getPA().sendFrame126("Waiting for other player...", 6571);
				o1.getPA().sendFrame126("Other player has accepted", 6571);
			}
			break;

			// god spell charge
		case 4169:
			c.usingMagic = true;
			if (!c.getCombat().checkMagicReqs(48)) {
				break;
			}
			if (System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
				c.sendMessage("You still feel the charge in your body!");
				break;
			}
			c.godSpellDelay = System.currentTimeMillis();
			c.sendMessage("You feel charged with a magical power!");
			c.gfx100(c.MAGIC_SPELLS[48][3]);
			//c.setAnimation(Animation.create(c.MAGIC_SPELLS[48][2]));
			c.startAnimation(c.MAGIC_SPELLS[48][2]);
			c.usingMagic = false;
			break;

		case 152:
		case 153:
			c.isRunning2 = !c.isRunning2;
			final int frame = c.isRunning2 == true ? 1 : 0;
			c.getPA().sendFrame36(173, frame);
			break;

		case 9154:
			c.logout();
			break;

		case 21010:
		case 82016:
		case 85248:
			if (c.takeAsNote == false) {
				c.takeAsNote = true;
			} else if (c.takeAsNote == true) {
				c.takeAsNote = false;
			}
			break;

		case 21011:
			c.takeAsNote = false;
			break;

		case 13092:
			if (System.currentTimeMillis() - c.lastButton < 400) {
				c.lastButton = System.currentTimeMillis();
				break;
			} else {
				c.lastButton = System.currentTimeMillis();
			}
			final Player ot = PlayerHandler.players[c.tradeWith];
			if (ot == null) {
				c.getTradeAndDuel().declineTrade();
				c.sendMessage("Trade declined as the other player has disconnected.");
				break;
			}
			c.getPA().sendFrame126("Waiting for other player...", 3431);
			ot.getPA().sendFrame126("Other player has accepted", 3431);
			c.goodTrade = true;
			ot.goodTrade = true;
			for (final GameItem item : c.getTradeAndDuel().offeredItems) {
				if (item.id > 0) {
					if (ot.getItems().freeSlots() < c.getTradeAndDuel().offeredItems
							.size()) {
						c.sendMessage(ot.playerName
								+ " only has "
								+ ot.getItems().freeSlots()
								+ " free slots, please remove "
								+ (c.getTradeAndDuel().offeredItems.size() - ot
										.getItems().freeSlots()) + " items.");
						ot.sendMessage(c.playerName
								+ " has to remove "
								+ (c.getTradeAndDuel().offeredItems.size() - ot
										.getItems().freeSlots())
										+ " items or you could offer them "
										+ (c.getTradeAndDuel().offeredItems.size() - ot
												.getItems().freeSlots()) + " items.");
						c.goodTrade = false;
						ot.goodTrade = false;
						c.getPA().sendFrame126("Not enough inventory space...",
								3431);
						ot.getPA().sendFrame126(
								"Not enough inventory space...", 3431);
						break;
					} else {
						c.getPA().sendFrame126("Waiting for other player...",
								3431);
						ot.getPA().sendFrame126("Other player has accepted",
								3431);
						c.goodTrade = true;
						ot.goodTrade = true;
					}
				}
			}
			if (c.inTrade && !c.tradeConfirmed && ot.goodTrade && c.goodTrade) {
				c.tradeConfirmed = true;
				if (ot.tradeConfirmed) {
					c.getTradeAndDuel().confirmScreen();
					ot.getTradeAndDuel().confirmScreen();
					break;
				}
			}
			break;

		case 94138:
			if (c.lunarDiplomacy == 0) {
				c.forcedChat("You must start <col=255>Lunar Diplomacy</col> to enter Lunar Isle.");
			}
			if (c.lunarDiplomacy > 0 && c.lunarDiplomacy < 5) {
				c.getPA().startTeleport(2107, 3918, 0, "lunar");
			}
			if (c.lunarDiplomacy >= 5) {
				c.getPA().startTeleport(2107, 3918, 4, "lunar");
			}
		break;

		case 13218:
			if (System.currentTimeMillis() - c.lastButton < 400) {
				c.lastButton = System.currentTimeMillis();
				break;
			} else {
				c.lastButton = System.currentTimeMillis();
			}
			c.tradeAccepted = true;
			final Player ot1 = PlayerHandler.players[c.tradeWith];
			if (ot1 == null) {
				c.getTradeAndDuel().declineTrade();
				c.sendMessage("Trade declined as the other player has disconnected.");
				break;
			}
			if (c.inTrade && c.tradeConfirmed && ot1.tradeConfirmed
					&& !c.tradeConfirmed2) {
				c.tradeConfirmed2 = true;
				if (ot1.tradeConfirmed2) {
					c.acceptedTrade = true;
					ot1.acceptedTrade = true;
					c.getTradeAndDuel().giveItems();
					ot1.getTradeAndDuel().giveItems();
					break;
				}
				ot1.getPA().sendFrame126("Other player has accepted.", 3535);
				c.getPA().sendFrame126("Waiting for other player...", 3535);
			}
			break;

			/**
			 * Quest Buttons
			 */
		case 62162:
			if (c.currentPanel.equals("statistics")) {
				c.forcedChat("I currently have "+ c.kills +" Kills.");
			} else if (c.currentPanel.equals("main")) {
			if (c.membership == false) {
				c.sendMessage("This is only for membership users.");
				return;
			}
				c.drawInterface().memberShipTab();
				c.currentPanel = "membership";
			}
			break;

		case 62155:
			if (c.currentPanel.equals("quest")) {
				if (c.theStryke == 0) {
					c.getQuest().getStartingInterface(0);
				} else if (c.theStryke >= 1 && c.theStryke <= 2) {
					TheStrykeWyrm.getDuringInterface(c);
				} else if (c.theStryke == 3) {
					c.getQuest().getCompletionInterface(0);
				}}
			c.questButton = 1;
			 if (c.currentPanel.equals("main")) {
				c.getPA().sendFrame126(""+ Config.UPDATES_URL +"", 12000);
			} else if (c.currentPanel.equals("teleports")) {
				if (c.savedTele1 == false) {
					c.getDH().sendDialogues(110, 0);
				} else if (c.savedTele1 == true) {
					c.getDH().sendDialogues(112, 0);
				}
			} else if (c.currentPanel.equals("statistics")) {
				c.forcedChat("I currently have "+ c.votePoints +" Vote Points.");
			} else if (c.currentPanel.equals("membership")) {
			if (c.membership == false) {
				c.forcedChat("This is only for membership users.");
				return;
			}
				c.getPA().spellTeleport(2526, 4774, 0);
			} else if (c.currentPanel.equals("redirect")) {
				c.getPA().sendFrame126(""+ Config.FORUM_URL +"", 12000);
			}
			break;

		case 62156:
			if (c.currentPanel.equals("main")) {
				c.drawInterface().drawPointsTab();
				c.currentPanel = "statistics";
			} else if (c.currentPanel.equals("prestige")) {
				c.getDH().sendDialogues(134, 0);
			} else if (c.currentPanel.equals("statistics")) {
				c.forcedChat("I currently have "+ c.pcPoints +" PC Points.");
			} else if (c.currentPanel.equals("quest")) {
				if (c.lunarDiplomacy == 0) {
					c.getQuest().getStartingInterface(1);
				} else if (c.lunarDiplomacy >= 1 && c.lunarDiplomacy <= 8) {
					LunarDiplomacy.getDuringInterface(c);
				} else if (c.lunarDiplomacy == 9) {
					c.getQuest().getCompletionInterface(1);
			}
			} else if (c.currentPanel.equals("membership")) {
			if (c.membership == false) {
				c.forcedChat("This is only for membership users.");
				return;
			}
				c.slayerTask = -1;
				c.taskAmount = -1;
				c.taskType = -1;
				c.easierTask = 0;
				c.sendMessage("You've resetted your slayer task.");
			} else if (c.currentPanel.equals("redirect")) {
				c.getPA().sendFrame126(""+ Config.VOTE_URL +"", 12000);
			} else if (c.currentPanel.equals("total")) {
				c.forcedChat("Total Playtime: " + c.getPlaytime() + ".");
			}
			break;

		case 62157:
			c.questButton = 2;
			if (c.currentPanel.equals("main")) {
				c.drawInterface().drawQuestTab();
				c.currentPanel = "quest";
			} else if (c.currentPanel.equals("teleports")) {
				if (c.savedTele2 == false) {
					c.getDH().sendDialogues(110, 0);
				} else if (c.savedTele2 == true) {
					c.getDH().sendDialogues(112, 0);
				}
			} else if (c.currentPanel.equals("prestige")) {
				c.getDH().sendDialogues(146, 0);
			} else if (c.currentPanel.equals("statistics")) {
				c.forcedChat("I currently have "+ c.slayerPoints +" Slayer Points.");
			} else if (c.currentPanel.equals("quest")) {
				if (c.DT == 0) {
					c.getQuest().getStartingInterface(2);
				} else if (c.DT >= 1 && c.DT <= 5) {
					DesertTreasure.getDuringInterface(c);
				} else if (c.DT == 6) {
					c.getQuest().getCompletionInterface(2);
				}
			} else if (c.currentPanel.equals("membership")) {
			if (c.membership == false) {
				c.sendMessage("This is only for membership users.");
				return;
			}
					c.getDH().sendDialogues(4000, 1304);
			} else if (c.currentPanel.equals("redirect")) {
				c.getPA().sendFrame126(""+ Config.DONATE_URL +"", 12000);
			}
			break;

		case 62158:
			if (c.currentPanel.equals("main")) {
				c.drawInterface().drawSavedTeleports();
				c.currentPanel = "teleports";
			} else if (c.currentPanel.equals("prestige")) {
				//c.drawInterface().drawPrestigeGuide();
			} else if (c.currentPanel.equals("membership")) {
			if (c.membership == false) {
				c.sendMessage("This is only for membership users.");
				return;
			}
				c.getPA().spellTeleport(3286, 4293, 0);
			} else if (c.currentPanel.equals("redirect")) {
				c.getPA().sendFrame126(""+ Config.SUGGESTIONS_URL +"", 12000);
			} else if (c.currentPanel.equals("total")) {
				c.forcedChat("I've killed a total of " + c.totalNPCKO + " NPCS.");
			}
			break;

		case 62159:
			c.questButton = 3;
			if (c.currentPanel.equals("main")) {
				c.drawInterface().drawPrestigePanel();
				c.currentPanel = "prestige";
			} else if (c.currentPanel.equals("redirect")) {
				c.getPA().sendFrame126(""+ Config.HIGHSCORES_URL +"", 12000);
			} else if (c.currentPanel.equals("membership")) {
			if (c.membership == false) {
				c.sendMessage("This is only for membership users.");
				return;
			}
			if(System.currentTimeMillis() - c.lastGWDKC < 1800000) {
				c.sendMessage("<col=255>You can only use this once a half hour.");
				return;
			}
				c.godwarsKillCount[0] += 20;
				c.godwarsKillCount[1] += 20;
				c.godwarsKillCount[2] += 20;
				c.godwarsKillCount[3] += 20;
				c.getPA().sendFrame126("@cya@" + c.godwarsKillCount[0], 16217);
				c.getPA().sendFrame126("@cya@" + c.godwarsKillCount[1], 16219);
				c.getPA().sendFrame126("@cya@" + c.godwarsKillCount[2], 16218);
				c.getPA().sendFrame126("@cya@" + c.godwarsKillCount[3], 16216);
				c.sendMessage("<col=255>You feel something strange as your Killcounts rises by 20.");
				c.lastGWDKC = System.currentTimeMillis();
			} else if (c.currentPanel.equals("teleports")) {
				if (c.savedTele3 == false) {
					c.getDH().sendDialogues(110, 0);
				} else if (c.savedTele3 == true) {
					if (c.membership == false) {
						c.sendMessage("As a regular user you only get 2 saved teleports.");
						return;
					}		
					c.getDH().sendDialogues(112, 0);
				}
			}
			break;

		case 62160:
			if (c.currentPanel.equals("statistics")) {
				c.forcedChat("I currently have "+ c.dTokens +" Dungeoneering Tokens.");
			} else if (c.currentPanel.equals("main")) {
				//c.drawInterface().drawRankTab(c);
				//c.currentPanel = "ranks";
				AchievementManager.writeInterface(c);
			}
			break;

		case 62161:
			c.questButton = 4;
			if (c.currentPanel.equals("teleports")) {
				if (c.savedTele4 == false) {
					c.getDH().sendDialogues(110, 0);
				} else if (c.savedTele4 == true) {
					if (c.membership == false) {
						c.sendMessage("As a regular user you only get 2 saved teleports.");
						return;
					}
					c.getDH().sendDialogues(112, 0);
				}
			} else if (c.currentPanel.equals("statistics")) {
				c.forcedChat("I currently have "+ c.loyaltyPoints +" Loyalty Points.");
			} else if (c.currentPanel.equals("main")) {
				c.drawInterface().totalTab(c);
				c.currentPanel = "total";
			}
			break;

		case 62163:
			c.questButton = 5;
			if (c.currentPanel.equals("teleports")) {
				if (c.savedTele5 == false) {
					c.getDH().sendDialogues(110, 0);
				} else if (c.savedTele5 == true) {
					if (c.membership == false) {
						c.sendMessage("As a regular user you only get 2 saved teleports.");
						return;
					}		
					c.getDH().sendDialogues(112, 0);
				}
			} else if (c.currentPanel.equals("statistics")) {
				c.forcedChat("I currently have "+ c.deaths +" Deaths.");
			}
			break;

		case 62164:
			if (c.currentPanel.equals("statistics")) {
				c.forcedChat("I am currently on a "+ c.currentStreak +" Kill Streak.");
			}
			break;

		case 62165:
			if (c.currentPanel.equals("statistics")) {
				c.forcedChat("My Highest Kill Streak is "+ c.highestStreak +".");
			} else if (c.currentPanel.equals("main")) {
				if (c.playerRights >= 1 && c.playerRights <= 3) {
					c.sendMessage("Staff can't request for help!");
					return;
				}
				if (System.currentTimeMillis() - c.lastHelp < 30000) {
					c.sendMessage("You can only do this every 30 seconds.");
					return;
				}
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Player c2 = (Player)PlayerHandler.players[j];
						if(Connection.isMuted(c)) {
							c.sendMessage("You can't ask for help when you are muted.");
							return;
						}
						if (c.jailed == 1) {
							c.sendMessage("You can't ask for help in jail.");
							return;
						}
						if (PlayerHandler.players[j].playerRights > 0 && PlayerHandler.players[j].playerRights < 4 && System.currentTimeMillis() - c.lastHelp > 30000) {
							c2.sendMessage("[HELP MESSAGE] @red@"+(Misc.optimizeText(c.playerName))+"@bla@ needs help. Coordinates are: @red@"+c.absX+", "+c.absY+"@bla@.");
							c.lastHelp = System.currentTimeMillis();
						}
					}
				}
			}
			break;

		case 62166:
			if (c.currentPanel.equals("main")) {
				c.drawInterface().redirectTab();
				c.currentPanel = "redirect";
			}
			break;

		case 62167:
			if (c.currentPanel.equals("main")) {
				c.forcedChat("My current wealth is "+c.getPA().getCarriedWealth()+".");
			}
			break;

		case 62168: //Last Button
			if (!c.currentPanel.equals("main")) {
				c.drawInterface().drawMainTab();
				c.currentPanel = "main";
			}
			break;
			/**
			 * Quest Buttons End
			 */

			/* Rules Interface Buttons */
		case 125011: // Click agree
			if (!c.ruleAgreeButton) {
				c.ruleAgreeButton = true;
				c.getPA().sendFrame36(701, 1);
			} else {
				c.ruleAgreeButton = false;
				c.getPA().sendFrame36(701, 0);
			}
			break;
		case 125003:// Accept
			if (c.ruleAgreeButton) {
				c.getPA().showInterface(3559);
				c.newPlayer = false;
			} else if (!c.ruleAgreeButton) {
				c.sendMessage("You need to click on you agree before you can continue on.");
			}
			break;
		case 125006:// Decline
			c.sendMessage("You have chosen to decline, Client will be disconnected from the server.");
			break;

			/* Player Options */
		case 74176:
			if (!c.mouseButton) {
				c.mouseButton = true;
				c.getPA().sendFrame36(500, 1);
				c.getPA().sendFrame36(170, 1);
			} else if (c.mouseButton) {
				c.mouseButton = false;
				c.getPA().sendFrame36(500, 0);
				c.getPA().sendFrame36(170, 0);
			}
			break;
			/*case 74184:
			if (!c.splitChat) {
				c.splitChat = true;
				c.getPA().sendFrame36(502, 1);
				c.getPA().sendFrame36(287, 1);
			} else {
				c.splitChat = false;
				c.getPA().sendFrame36(502, 0);
				c.getPA().sendFrame36(287, 0);
			}
			break;*/
		case 3189: //Split Chat Button
			if (c.splitChat == false) {
				c.getPA().sendFrame36(502, 1);
				c.getPA().sendFrame36(287, 1);
				c.splitChat = true;
			} else if (c.splitChat == true){
				c.getPA().sendFrame36(502, 0);
				c.getPA().sendFrame36(287, 0);
				c.splitChat = false;
			}
			break;
		case 74180:
			if (!c.chatEffects) {
				c.chatEffects = true;
				c.getPA().sendFrame36(501, 1);
				c.getPA().sendFrame36(171, 0);
			} else {
				c.chatEffects = false;
				c.getPA().sendFrame36(501, 0);
				c.getPA().sendFrame36(171, 1);
			}
			break;
		case 74188:
			if (!c.acceptAid) {
				c.acceptAid = true;
				c.getPA().sendFrame36(503, 1);
				c.getPA().sendFrame36(427, 1);
			} else {
				c.acceptAid = false;
				c.getPA().sendFrame36(503, 0);
				c.getPA().sendFrame36(427, 0);
			}
			break;
		case 74192:
			if (!c.isRunning2) {
				c.isRunning2 = true;
				c.getPA().sendFrame36(504, 1);
				c.getPA().sendFrame36(173, 1);
			} else {
				c.isRunning2 = false;
				c.getPA().sendFrame36(504, 0);
				c.getPA().sendFrame36(173, 0);
			}
			break;
		case 74201:// brightness1
			c.getPA().sendFrame36(505, 1);
			c.getPA().sendFrame36(506, 0);
			c.getPA().sendFrame36(507, 0);
			c.getPA().sendFrame36(508, 0);
			c.getPA().sendFrame36(166, 1);
			break;
		case 74203:// brightness2
			c.getPA().sendFrame36(505, 0);
			c.getPA().sendFrame36(506, 1);
			c.getPA().sendFrame36(507, 0);
			c.getPA().sendFrame36(508, 0);
			c.getPA().sendFrame36(166, 2);
			break;

		case 74204:// brightness3
			c.getPA().sendFrame36(505, 0);
			c.getPA().sendFrame36(506, 0);
			c.getPA().sendFrame36(507, 1);
			c.getPA().sendFrame36(508, 0);
			c.getPA().sendFrame36(166, 3);
			break;

		case 74205:// brightness4
			c.getPA().sendFrame36(505, 0);
			c.getPA().sendFrame36(506, 0);
			c.getPA().sendFrame36(507, 0);
			c.getPA().sendFrame36(508, 1);
			c.getPA().sendFrame36(166, 4);
			break;
		case 74206:// area1
			c.getPA().sendFrame36(509, 1);
			c.getPA().sendFrame36(510, 0);
			c.getPA().sendFrame36(511, 0);
			c.getPA().sendFrame36(512, 0);
			break;
		case 74207:// area2
			c.getPA().sendFrame36(509, 0);
			c.getPA().sendFrame36(510, 1);
			c.getPA().sendFrame36(511, 0);
			c.getPA().sendFrame36(512, 0);
			break;
		case 74208:// area3
			c.getPA().sendFrame36(509, 0);
			c.getPA().sendFrame36(510, 0);
			c.getPA().sendFrame36(511, 1);
			c.getPA().sendFrame36(512, 0);
			break;
		case 74209:// area4
			c.getPA().sendFrame36(509, 0);
			c.getPA().sendFrame36(510, 0);
			c.getPA().sendFrame36(511, 0);
			c.getPA().sendFrame36(512, 1);
			break;

			/*
			 * Emote animations
			 */
		case 168:
			//c.setAnimation(Animation.create(855));
			c.startAnimation(855);
			break;
		case 169:
			//c.setAnimation(Animation.create(856));
			c.startAnimation(856);
			break;
		case 162:
			//c.setAnimation(Animation.create(857));
			c.startAnimation(857);
			break;
		case 164:
			//c.setAnimation(Animation.create(858));
			c.startAnimation(858);
			break;
		case 165:
			//c.setAnimation(Animation.create(859));
			c.startAnimation(859);
			break;
		case 161:
			//c.setAnimation(Animation.create(860));
			c.startAnimation(860);
			break;
		case 170:
			//c.setAnimation(Animation.create(861));
			c.startAnimation(861);
			break;
		case 171:
			//c.setAnimation(Animation.create(862));
			c.startAnimation(862);
			break;
		case 163:
			//c.setAnimation(Animation.create(863));
			c.startAnimation(863);
			break;
		case 167:
			//c.setAnimation(Animation.create(864));
			c.startAnimation(864);
			break;
		case 172:
			//c.setAnimation(Animation.create(865));
			c.startAnimation(865);
			break;
		case 166:
			//c.setAnimation(Animation.create(866));
			c.startAnimation(866);
			break;
		case 52050:
			c.startAnimation(2105);
			//c.setAnimation(Animation.create(2105));
			break;
		case 52051:
			c.startAnimation(2106);
			//c.setAnimation(Animation.create(2106));
			break;
		case 52052:
			c.startAnimation(2107);
			//c.setAnimation(Animation.create(2107));
			break;
		case 52053:
			c.startAnimation(2108);
			//c.setAnimation(Animation.create(2108));
			break;
		case 52054:
			c.startAnimation(2109);
			//c.setAnimation(Animation.create(2109));
			break;
		case 52055:
			c.startAnimation(2110);
			//c.setAnimation(Animation.create(2110));
			break;
		case 52056:
			c.startAnimation(2111);
			//c.setAnimation(Animation.create(2111));
			break;
		case 52057:
			c.startAnimation(2112);
			//c.setAnimation(Animation.create(2112));
			break;
		case 52058:
			c.startAnimation(2113);
			//c.setAnimation(Animation.create(2113));
			break;
		case 43092:
			c.startAnimation(1374);
			c.gfx0(1702);
			//c.setAnimation(Animation.create(0x558));
			break;
		case 2155:
			c.startAnimation(0x46B);
			//c.setAnimation(Animation.create(0x46B));
			break;
		case 25103:
			c.startAnimation(0x46A);
			//c.setAnimation(Animation.create(0x46A));
			break;
		case 25106:
			c.startAnimation(0x469);
			//c.setAnimation(Animation.create(0x469));
			break;
		case 2154:
			c.startAnimation(0x468);
			//c.setAnimation(Animation.create(0x468));
			break;
		case 52071:
			c.startAnimation(0x84F);
			//c.setAnimation(Animation.create(0x84F));
			break;
		case 52072:
			c.startAnimation(0x850);
			//c.setAnimation(Animation.create(0x850));
			break;
		case 59062:
			c.startAnimation(2836);
			//c.setAnimation(Animation.create(2836));
			break;
		case 72032:
			c.startAnimation(3544);
			//c.setAnimation(Animation.create(3544));
			break;
		case 72033:
			c.startAnimation(3543);
			//c.setAnimation(Animation.create(3543));
			break;

		case 47130:
			c.forcedText = "I must slay another " + c.taskAmount + " "
					+ NPCHandler.getNpcListName(c.slayerTask) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;

		case 24017:
			c.getPA().resetAutocast();
			c.getItems()
			.sendWeapon(
					c.playerEquipment[c.playerWeapon],
					c.getItems().getItemName(
							c.playerEquipment[c.playerWeapon]));
			break;
		}
		if (c.isAutoButton(actionButtonId)) {
			c.assignAutocast(actionButtonId);
		}
	}

}
