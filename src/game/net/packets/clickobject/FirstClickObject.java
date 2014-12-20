package game.net.packets.clickobject;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.event.Event;
import engine.event.EventContainer;
import engine.event.EventManager;
import engine.util.Misc;
import game.Config;
import game.Server;
import game.content.CompletionistCape;
import game.content.CrystalChest;
import game.content.DonatorChest;
import game.content.partyroom.PartyRoom;
import game.content.prestige.PrestigeChest;
import game.content.travel.Lodestone;
import game.content.travel.ShipSailing;
import game.content.achievement.*;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.minigame.barrows.Barrows;
import game.minigame.barrows.Chest;
import game.minigame.barrows.Dungeon;
import game.minigame.bountyhunter.BountyHunter;
import game.minigame.fightpits.FightPits;
import game.minigame.pestcontrol.PestControl;
import game.minigame.warriorsguild.WarriorsGuildObjects;
import game.minigame.weapongame.WeaponGame;
import game.net.packets.clickobject.areas.*;
import game.object.Object;
import game.skill.SkillHandler;
import game.skill.agility.BarbarianCourse;
import game.skill.agility.GnomeCourse;
import game.skill.agility.WildernessCourse;
import game.skill.dungeoneering.DungHandler;
import game.skill.dungeoneering.Dungeoneering;
import game.skill.dungeoneering.FloorData;
import game.skill.mining.Mining;
import game.skill.smithing.Smelting;
import game.skill.thieving.WallSafe;
import game.skill.crafting.JewelryMaking;
import game.skill.woodcutting.Woodcutting;

public class FirstClickObject {
	
	protected static int i;

	public static void handleClick(final Player c, int objectType, int obX, int obY) {
		c.clickObjectType = 0;
		if (c.playerRights == 3) {
			c.sendMessage("ObjectId: " + objectType);
		}
		if (c.agilityEmote)
			return;
		if (Woodcutting.chopTree(c, objectType, obX, obY)) {
			return;
		}
		if (Config.SERVER_DEBUG = true) {
			if (c.playerRights >= 1 && c.playerRights <= 3) {
				Misc.println("Object type: " + objectType);
			}
		}
		if (Mining.mineOre(c, objectType, obX, obY) && 
				SkillHandler.noInventorySpace(c, "mining")) {
			if (System.currentTimeMillis() - c.clickDelay > 200) {
				
			return;
		}
		}
		//}
		/*
		 * Party Room Objects
		 */
		if (c.inPartyRoom()) {
			for (int i = 0; i < Server.partyRoom.BALLOON_IDS.length; i++) {
				if (objectType == Server.partyRoom.BALLOON_IDS[i][0]) {
					Server.partyRoom.popBalloon(c, obX, obY);
					return;
				}
			}
			if (objectType == Server.partyRoom.CHEST) {
				PartyRoom.open(c);
				return;
			}

			if (objectType == Server.partyRoom.LEVER) {
				final Player client = c;
				EventManager.getSingleton().addEvent(new Event() {
					@Override
					public void execute(EventContainer x) {
						Server.partyRoom.startTimer(client);
						x.stop();
					}
				}, 700);
				return;
			}
		}
		/*
		 * Party Room Objects End
		 */
		RellekkaDungeon.handleObjects(c, objectType, obX, obY, 0);
		Stronghold.handleObjects(c, objectType, obX, obY, 0);
		DuelArena.handleDuelArenaObjects(c, objectType, obX, obY, 0);
		DagLair.handleObjects(c, objectType, obX, obY, i);
		ChaosTunnels.handleObjects(c, objectType, obX, obY, 0);
		GnomeCourse.handleObject(objectType, c);
		BarbarianCourse.handleObject(objectType, c);
		WildernessCourse.handleObject(objectType, c);
		WarriorsGuildObjects.handleObjects(c, objectType);
		switch (objectType) {
		case 172:
			if (c.getItems().playerHasItem(989, 1)) {
				CrystalChest.searchChest(c, 172, 2914, 3452);
			} else {
				c.sendMessage("You need a crystal key to open this chest.");
			}
			break;

				case 7:
				case 8:
				case 9:
					c.getCannon().pickUpCannon();
					break;

				case 6:
					if(!c.cannonIsShooting) {
						c.getCannon().shootCannon();
					}
					break;

		case 13291:
			if (c.chestChance >= 1) {
				DonatorChest.searchChest(c, 13291, 2914, 3452);
			} else {
				c.sendMessage("You don't seem to have any chest chances!");
			}
			break;

		case 13642: //Teletab Creation
			c.getDH().sendDialogues(150, 0);
			c.craftingTeletabs = true;
			break;

		case 12003: //Lodestone
			Lodestone.drawInterface(c);
			break;

			case 26814:
				if (c.absX == 3107) {
					JewelryMaking.jewelryInterface(c);
				}
				break;

		case 4008: //Spec Altar
			if (c.specAmount >= 10) {
				c.sendMessage("You special is already full.");
				return;
			}
			if(System.currentTimeMillis() - c.lastSpecRestore < 180000) {
				c.sendMessage("You can only use this once every 3 minutes.");
				return;
			}
			if (c.specAmount < 10) {
				c.specAmount += 10;
				c.lastSpecRestore = System.currentTimeMillis();	
				c.sendMessage("You feel your special attack regenerate.");
			}
			break;

		case 28120://bounty hunter entrances
		case 28119:
		case 28121:
			BountyHunter.enterBH(c, c.objectId);
			break;

		case 7257: //Rogues Den Entrance
			c.getPA().movePlayer(3061, 4985, 1);
			break;

		case 7258: //Rogues Den Exit
			c.getPA().movePlayer(2906, 3537, 0);
			break;

		case 2573: //Prestige Chest
			PrestigeChest.openChest(c);
			break;

		case 31363:
			if (c.getX() >= 3294) {
				c.getPA().movePlayer(3288, 4293, 0);
			}
			if (c.getX() <= 3288) {
				c.getPA().movePlayer(3294, 4293, 0);
			}
			break;

		case 12356: //RFD Minigame Portal
			if (c.Culin == true) {
				c.sendMessage("You have already finished this minigame!");
				return;
			}
			if (c.getY() == 9620 || c.getY() == 9621) {
				c.getPA().enterRFD();
				c.sendMessage("Note: this is not a Safe Minigame, you'll lose your items on death!");
				for (int p = 0; p < c.PRAYER.length; p++) { // reset prayer
					// glows
					c.prayerActive[p] = false;
					c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);
				}
			} else {
				c.getPA().resetRFD();
			}
			break;

			/*
			 * Grandexchange Shortcut
			 */
		case 9311:
			//c.setAnimation(Animation.create(2589));
			c.startAnimation(2589);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer e) {
					c.getPA().movePlayer(3144, 3514, 0);
					e.stop();
				}

				public void stop() {}	
			}, 2);
			break;

		case 9312:
			//c.setAnimation(Animation.create(2589));
			c.startAnimation(2589);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer e) {
					c.getPA().movePlayer(3138, 3516, 0);
					e.stop();
				}

				public void stop() {}	
			}, 2);
			break;
			/*
			 * Grandexchange Shortcut End
			 */

		case 30141: // Weapon Game Death Chamber
			if (c.canLeaveDeath) {
				c.getPA().movePlayer(1663,5692,0);
				c.canLeaveDeath = false;
			} else {
				c.sendMessage("You need to wait 10 seconds before you can leave the death chamber.");
			}
			break;

		case 411:
		case 61: //Curse Alter
			if (c.curses == false) {
				c.curses = true;
				c.altarPrayed = 1;
				c.setSidebarInterface(5, 22500);
				c.sendMessage("You sense a surge of power flow through your body!");
				c.getPA().sendFrame126(":prayer:" + (c.altarPrayed == 1 ? "curses" : "prayers"), -1);
			} else {
				c.curses = false;
				c.altarPrayed = 0;
				c.setSidebarInterface(5, 5608);
				c.sendMessage("You sense a surge of purity flow through your body!");
				c.getCurse().resetCurse();
				c.getPA().sendFrame126(":prayer:" + (c.altarPrayed == 1 ? "curses" : "prayers"), -1);
			}
			c.startAnimation(645);
			break;

		case 30145: // Weapon Game Exit
		case 30144:
			WeaponGame.leaveCave(c);
			break;

		case 30224: // Weapon Game Entrance
			c.currentClass = 0;
			c.getPA().sendFrame126("Current Class: None", 49107);
			c.getPA().sendFrame126("Class Level: 0", 49108);
			c.getPA().sendFrame126("Level Potential: "+c.lvlPotential+"%", 49109);
			c.getPA().showInterface(49100);
			break;

		case 2308:
		case 2307:
			c.getPA().movePlayer(Config.HOME_X, Config.HOME_Y, 0);
			break;

		case 31814:
		case 31815:
			if (c.absY == 9697)
				c.getPA().movePlayer(2907, 9698, 0);
			else
				c.getPA().movePlayer(2907, 9697, 0);
			break;

		case 13996: // Completionist Cape Stand
			CompletionistCape.giveCompletionist(c);
			break;

			/*case 28121:
			c.getPA().movePlayer(3163, 3696, 0);
			break;*/

		case 26933:
			c.getPA().movePlayer(3097, 9868, 0);
			break;
		case 29355:
			c.getPA().movePlayer(3096, 3468, 0);
			break;

			/**
			 * First Click Object Actions
			 */

		case 6552:
			//if(c.DT == 6) {
				c.getDH().sendDialogues(31, 0);
			//} else {
				//c.sendMessage("You have no idea what this can do.");
			//}
			break;

		case 1757:
			c.sendMessage("This ladder leads to nothing!");
			break;

		case 5170:
			if (c.absX < 3511) {
				c.getPA().movePlayer(3511, 9957, 0);
			} else {
				c.getPA().movePlayer(3510, 9957, 0);
			}
			break;

		case 23216:
			if (c.absX > 2726) {
				c.getPA().movePlayer(2725, 10168, 0);
			} else {
				c.getPA().movePlayer(2726, 10168, 0);
			}
			break;

		case 26294:
			c.getPA().movePlayer(2912, 5300, 2);
			break;

		case 26427:
			if (c.absX > 2907) {
				if (c.godwarsKillCount[2] >= 10) {
					c.godwarsKillCount[2] -= 10;
					c.getPA().sendFrame126("@cya@" + c.godwarsKillCount[2],
							16218);
					c.getPA().movePlayer(2907, 5265, 0);
				} else {
					c.sendMessage("You must have defeated 10 Saradomin followers before you can enter this door.");
				}
			} else {
				c.getPA().movePlayer(2908, 5265, 0);
			}
			break;

		case 26445:
			c.getPA().movePlayer(2919, 5274, 0);
			break;

		case 26298:
			c.getPA().movePlayer(2920, 5276, 1);
			break;

		case 26444:
			if (c.getPA().getLevelForXP(c.playerXP[c.playerAgility]) < 70) {
				c.sendMessage("You must have an agility level of 70 or better, to climb this.");
				return;
			}
			c.getPA().movePlayer(2915, 5300, 1);
			c.sendMessage("You enter the Saradomin realm of the godwars dungeon!");
			break;
			/**
			 * Godwars
			 */
		case 26303:
			if (c.absY > 5275) {
				if (c.getPA().getLevelForXP(c.playerXP[4]) < 70) {
					c.sendMessage("You must have a range level of 70 or better, to go across this.");
					return;
				}
				if (c.getItems().playerHasItem(9419)) {
					c.getPA().movePlayer(2871, 5269, 2);
				} else {
					c.sendMessage("You must have a mith grapple to get across this.");
					return;
				}
				c.sendMessage("You enter the Armadyl realm of the godwars dungeon!");
			} else {
				if (c.getItems().playerHasItem(9419)) { //change to 9419
					c.getPA().movePlayer(2871, 5279, 2);
				} else {
					c.sendMessage("You must have a mith grapple to get across this.");
					return;
				}
			}
			break;

		case 26426:
			if (c.absY < 5296) {
				if (c.godwarsKillCount[3] >= 10) {
					c.godwarsKillCount[3] -= 10;
					c.getPA().sendFrame126("@cya@" + c.godwarsKillCount[3],
							16216);
					c.getPA().movePlayer(2839, 5296, 2);
				} else {
					c.sendMessage("You must have defeated 10 Armadyl followers before you can enter this door.");
				}
			} else {
				c.getPA().movePlayer(2839, 5295, 2);
			}
			break;

			// Bandos
		case 26387:
		case 26384:
			if (c.absX > 2850) {
				if (c.getPA().getLevelForXP(c.playerXP[2]) < 70) {
					c.sendMessage("You must have a strength level of 70 or better, to open this door.");
					return;
				}
				if (c.getItems().playerHasItem(2347)) {
					c.getPA().movePlayer(2849, 5333, 2);
				} else {
					c.sendMessage("You must have a hammer to open this door.");
					return;
				}
				c.sendMessage("You enter the Bandos realm of the godwars dungeon!");
			} else {
				c.getPA().movePlayer(2852, 5333, 2);
			}
			break;

		case 26425:
			if (c.absX < 2864) {
				if (c.godwarsKillCount[0] >= 10) {
					c.godwarsKillCount[0] -= 10;
					c.getPA().sendFrame126("@cya@" + c.godwarsKillCount[0],
							16217);
					c.getPA().movePlayer(2864, 5354, 2);
				} else {
					c.sendMessage("You must have defeated 10 Bandos followers before you can enter this door.");
				}
			} else {
				c.getPA().movePlayer(2863, 5354, 2);
			}
			break;

		case 26428:
			if (c.absY > 5331) {
				if (c.godwarsKillCount[1] >= 10) {
					c.godwarsKillCount[1] -= 10;
					c.getPA().sendFrame126("@cya@" + c.godwarsKillCount[1],
							16219);
					c.getPA().movePlayer(2925, 5331, 2);
				} else {
					c.sendMessage("You must have defeated 10 Zamorak followers before you can enter this door.");
				}
			} else {
				c.getPA().movePlayer(2925, 5332, 2);
			}
			break;

			/**
			 * End of Godwars
			 */

		case 26293:
			c.sendMessage("Climbing this would kill you!");
			break;

		case 9369:
			if (c.teleTimer > 0) {
				return;
			} else if (c.teleTimer2 > 0) {
				return;
			}
			if (c.getY() > 5175) {
				FightPits.addPlayer(c);
			} else {
				FightPits.removePlayer(c, false);
			}
			break;

		case 26289:
		case 26288:
		case 26286:
		case 26287:
			if (System.currentTimeMillis() - c.lastPray > 600000) {
				if (c.playerId != c.underAttackBy && c.underAttackBy != 0) {
					c.sendMessage("You can't do this in combat.");
					return;
				}
				if (c.prayer < c.getMaxPrayer()) {
					//c.setAnimation(Animation.create(645));
					c.startAnimation(645);
					c.prayer = c.getMaxPrayer();
					c.sendMessage("You recharge your prayer points.");
					c.getPA().refreshSkill(5);
				} else {
					c.sendMessage("You already have full prayer points.");
				}
			} else {
				c.sendMessage("You can only restory your prayer once every 10 minutes.");
			}
			break;

		case 9368:
			if (c.getY() < 5169) {
				FightPits.removePlayer(c, false);
			}
			break;

		case 6517:
		case 6516:
			if (c.dungClick3 == false) {
				c.dungClick3 = true;
				if (c.complexity == 1) {
					NPCHandler.spawnBoss(c, 10736, c.absX, c.absY,
							c.playerId * 4, 0, 30, 1, 50, 25, true);
					NPCHandler.spawnBoss(c, 10837, c.absX, c.absY,
							c.playerId * 4, 0, 40, 3, 50, 25, true);
					NPCHandler.spawnBoss(c, 10262, c.absX, c.absY,
							c.playerId * 4, 0, 25, 2, 50, 25, true);
				} else if (c.complexity == 2) {
					NPCHandler.spawnBoss(c, 10419, c.absX, c.absY,
							c.playerId * 4, 0, 90, 5, 96, 75, true);
					NPCHandler.spawnBoss(c, 10448, c.absX, c.absY,
							c.playerId * 4, 0, 66, 5, 96, 75, true);
					NPCHandler.spawnBoss(c, 10837, c.absX, c.absY,
							c.playerId * 4, 0, 82, 5, 96, 75, true);
				} else if (c.complexity == 3) {
					NPCHandler.spawnBoss(c, 10699, c.absX, c.absY,
							c.playerId * 4, 0, 129 - 15, 2, 142, 125, true);
					NPCHandler.spawnBoss(c, 10465, c.absX, c.absY,
							c.playerId * 4, 0, 129 - 15, 2, 142, 125, true);
					NPCHandler.spawnBoss(c, 10707, c.absX, c.absY,
							c.playerId * 4, 0, 129 - 15, 2, 142, 125, true);
				} else if (c.complexity == 4) {
					NPCHandler.spawnBoss(c, 10534, c.absX, c.absY,
							c.playerId * 4, 0, 27 + 125, 3, 44 + 125, 25 + 125,
							true);
					NPCHandler.spawnBoss(c, 10522, c.absX, c.absY,
							c.playerId * 4, 0, 27 + 125, 3, 44 + 125, 25 + 125,
							true);
					NPCHandler.spawnBoss(c, 10534, c.absX, c.absY,
							c.playerId * 4, 0, 27 + 125, 3, 44 + 125, 25 + 125,
							true);
				}
			} else {
				c.sendMessage("This tomb is empty.");
			}
			break;

		case 6555:// keyboss
			if (c.canOpenDoor(c)) {
				if (c.getItems().playerHasItem(18324, 1)) {
					c.getItems().deleteItem2(18324, 1);
					c.getPA().movePlayer(3234, 9323, c.playerId * 4);
				} else {
					c.sendMessage("You still need to search for the key!");
					return;
				}
				if (!c.hasSpawnedBoss) {
					FloorData.handleBoss(c);
					c.hasSpawnedBoss = true;
				}
			}
			break;

		case 6513:
			if (c.dungClick4 == false) {
				c.dungClick4 = true;
				if (c.complexity == 1) {
					NPCHandler.spawnBoss(c, 10736, c.absX, c.absY,
							c.playerId * 4, 0, 30, 1, 50, 25, true);
					NPCHandler.spawnBoss(c, 10837, c.absX, c.absY,
							c.playerId * 4, 0, 40, 3, 50, 25, true);
				} else if (c.complexity == 2) {
					NPCHandler.spawnBoss(c, 10445, c.absX, c.absY,
							c.playerId * 4, 0, 85, 6, 98, 75, true);
					NPCHandler.spawnBoss(c, 10801, c.absX, c.absY,
							c.playerId * 4, 0, 85, 6, 98, 75, true);
				} else if (c.complexity == 3) {
					NPCHandler.spawnBoss(c, 10738, c.absX, c.absY,
							c.playerId * 4, 0, 135 - 15, 1, 148, 125, true);
					NPCHandler.spawnBoss(c, 10418, c.absX, c.absY,
							c.playerId * 4, 0, 135 - 15, 1, 148, 125, true);
				} else if (c.complexity == 4) {
					NPCHandler.spawnBoss(c, 10532, c.absX, c.absY,
							c.playerId * 4, 0, 32 + 125, 2, 46 + 125, 25 + 125,
							true);
					NPCHandler.spawnBoss(c, 10522, c.absX, c.absY,
							c.playerId * 4, 0, 32 + 125, 2, 46 + 125, 25 + 125,
							true);
				}
			} else {
				c.sendMessage("This tomb is empty.");
			}
			break;

		case 6512:
		case 6514:
			if (c.dungClick1 == false) {
				c.dungClick1 = true;
				if (c.complexity == 1) {
					NPCHandler.spawnBoss(c, 10736, c.absX, c.absY,
							c.playerId * 4, 0, 30, 1, 50, 25, true);
					NPCHandler.spawnBoss(c, 10837, c.absX, c.absY,
							c.playerId * 4, 0, 40, 3, 50, 25, true);
					NPCHandler.spawnBoss(c, 10262, c.absX, c.absY,
							c.playerId * 4, 0, 25, 2, 50, 25, true);
				} else if (c.complexity == 2) {
					NPCHandler.spawnBoss(c, 10262, c.absX, c.absY,
							c.playerId * 4, 0, 85, 6, 90, 75, true);
					NPCHandler.spawnBoss(c, 10445, c.absX, c.absY,
							c.playerId * 4, 0, 85, 6, 90, 75, true);
					NPCHandler.spawnBoss(c, 10414, c.absX, c.absY,
							c.playerId * 4, 0, 85, 6, 90, 75, true);
				} else if (c.complexity == 3) {
					NPCHandler.spawnBoss(c, 10442, c.absX, c.absY,
							c.playerId * 4, 0, 124 - 15, 4, 147, 125, true);
					NPCHandler.spawnBoss(c, 10506, c.absX, c.absY,
							c.playerId * 4, 0, 124 - 15, 4, 147, 125, true);
					NPCHandler.spawnBoss(c, 10699, c.absX, c.absY,
							c.playerId * 4, 0, 124 - 15, 4, 147, 125, true);
				} else if (c.complexity == 4) {
					NPCHandler.spawnBoss(c, 10522, c.absX, c.absY,
							c.playerId * 4, 0, 40 + 125, 4, 46 + 125, 25 + 125,
							true);
					NPCHandler.spawnBoss(c, 10520, c.absX, c.absY,
							c.playerId * 4, 0, 40 + 125, 4, 46 + 125, 25 + 125,
							true);
					NPCHandler.spawnBoss(c, 10415, c.absX, c.absY,
							c.playerId * 4, 0, 40 + 125, 4, 46 + 125, 25 + 125,
							true);
				}
			} else {
				c.sendMessage("This tomb is empty.");
			}
			break;

		case 6515:
			if (!c.getItems().playerHasItem(18324, 1)) {
				if (c.getItems().freeSlots() > 0) {
					c.getItems().addItem(18324, 1);
				} else {
					c.sendMessage("You must empty 1 slot in your backpack to take the key.");
				}
			} else {
				return;
			}
			break;

			/*case 2491:
			Mining.mineEss(c, objectType);
			break;*/

		case 2255:
		case 2256:
			c.getPA().movePlayer(2928, 9516, c.playerId * 4);
			break;
		case 2257:
			c.getPA().movePlayer(2929, 9510, c.playerId * 4);
			break;

		case 2243:
		case 2242:
			c.getPA().movePlayer(2927, 9531, c.playerId * 4);
			break;

		case 2098:
			c.sendMessage("The rock looks like there is no more ore in it.");
			break;

		case 9358:
			c.objectDistance = 3;
			c.sendMessage("The entrance to the tunnel looks like it has been sealed with magma.");
			break;

		case 823:
			c.sendMessage("This is not yours to train on.");
			break;

			// Runecrafting - abyss teleports
		case 7129:
			if (c.objectX == 3029 && c.objectY == 4830) {
				c.getPA().movePlayer(2584, 4836, 0);
			}
			break;
		case 7132:
			if (c.objectX == 3028 && c.objectY == 4837) {
				c.getPA().movePlayer(2162, 4833, 0);
			}
			break;
		case 2318:
			if (c.dungKills < 2) {
				c.sendMessage("You must kill all of the creatures in order to continue through the floor.");
				return;
			}
			c.getPA().movePlayer(2621, 9495, c.playerId * 4);
			break;
		case 2321:
			if (c.dungKills < 6) {
				c.sendMessage("You must kill all of the creatures in order to continue through the floor.");
				return;
			}
			c.getPA().movePlayer(2598, 9494, c.playerId * 4);
			break;
		case 2303:
			if (c.dungKills < 10) {
				c.sendMessage("You must kill all of the creatures in order to continue through the floor.");
				return;
			}
				if (!c.hasSpawnedBoss) {
					FloorData.handleBoss(c);
					c.hasSpawnedBoss = true;
				}
			c.getPA().movePlayer(2580, 9521, c.playerId * 4);
			break;

		case 2606:
			if (c.hasOpened) {
				c.getPA().movePlayer(2836, 9600, c.playerId * 4);
				return;
			}
			if (System.currentTimeMillis() - c.lastThieve < 2000) {
				return;
			}
			if (!c.getItems().playerHasItem(18324, 1)) {
				c.sendMessage("You must unlock this door with a special key.");
				return;
			}
			if (c.dungKills < 6) {
				c.sendMessage("You must kill all of the creatures in order to continue through the floor.");
				return;
			}
			c.hasOpened = true;
			c.getPA().movePlayer(2836, 9600, c.playerId * 4);
			c.getItems().deleteItem(18324, 1);
			c.lastThieve = System.currentTimeMillis();
			FloorData.spawnMoreNPC1(c);
			c.sendMessage("The door vanishes in thin air!");
			c.getItems().addItem(DungHandler.chooseDFF(), 3);
			break;

		case 1995:
			if (c.dungKills < 2) {
				c.sendMessage("You must kill all of the creatures in order to continue through the floor.");
				return;
			}
			if (System.currentTimeMillis() - c.lastThieve < 2000) {
				return;
			}
			c.lastThieve = System.currentTimeMillis();
			FloorData.spawnMoreNPC(c);
			c.getPA().checkObjectSpawn(-1, 2851, 9577, 1, 10); // Dungeoneering
			// Chest
			c.sendMessage("The chest fades into nothing as you loot the goods.");
			c.getItems().addItem(DungHandler.chooseDFF(), 2);
			break;

		case 4881:
		case 1764:
		case 6501:
		case 2610:
			c.getDH().sendStatement(
					"Are you sure you want to abandon this dungeon?");
			c.nextChat = 2017;
			break;
		case 3221:
		case 3220:
		case 32270:
			if (c.canOpenDoor(c)) {
				if (!c.hasSpawnedBoss) {
					FloorData.handleBoss(c);
					c.hasSpawnedBoss = true;
					return;
				}
			}
			if (c.inDung()) {
				if (c.bossDead) {
					if (!c.hasRecievedRewards) {
						c.getPA().addSkillXP(c.addDungXP(c), 23);
						c.dTokens += c.addTokens(c);
						c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
						c.getPA().refreshSkill(5);
						c.getPA().sendFrame126(
								"@or2@Dungeoneering Tokens: " + c.dTokens + "",
								16032);

			c.sendMessage("You have completed the floor, and you gained "
					+ c.addDungXP(c)
					+ " XP, and "
					+ c.addTokens(c) + " tokens.");
						AchievementManager.increase(c, Achievements.DUNGEONEERER);
						c.hasRecievedRewards = true;
					}
					Dungeoneering.abandonDung(c);
				}
			}
			break;

		case 4800:
		case 2607:
		case 2246:
		case 2247:
		case 2608:
		case 6553:
		case 25161:
			if (c.canOpenDoor(c)) {
				if (c.floor == 1) {
					c.getPA()
					.movePlayer(c.getX() - 1, c.getY(), c.playerId * 4);
				} else if (c.floor == 2) {
					c.getPA()
					.movePlayer(c.getX() + 1, c.getY(), c.playerId * 4);
				} else if (c.floor == 3) {
					c.getPA()
					.movePlayer(c.getX(), c.getY() + 1, c.playerId * 4);
				}
				if (!c.hasSpawnedBoss) {
					FloorData.handleBoss(c);
					c.hasSpawnedBoss = true;
				}
			}
			break;
		case 2721:
			c.sendMessage("The staircase is broken and impossible to climb.");
			break;
		case 2814:
			c.getPA().startTeleport(2661, 3307, 0, "modern");
			break;
		case 7133:
			if (c.objectX == 3035 && c.objectY == 4842) {
				c.getPA().movePlayer(2162, 4833, 0);
			}
			break;
		case 7134:
			if (c.objectX == 3044 && c.objectY == 4842) {
				c.getPA().movePlayer(2269, 4843, 0);
			}
			break;
		case 7135:
			if (c.objectX == 3049 && c.objectY == 4839) {
				c.getPA().movePlayer(2464, 4834, 0);
			}
			break;
		case 7136:
			if (c.objectX == 3050 && c.objectY == 4837) {
				c.getPA().movePlayer(2207, 4836, 0);
			}
			break;

		case 4869:
			c.getPA().startTeleport(2661, 3307, 0, "modern");
			break;

		case 7137:
			if (c.objectX == 3051 && c.objectY == 4833) {
				c.getPA().movePlayer(2714, 4836, 0);
			}
			break;

			// Flax Picking
		case 2646:
			if (System.currentTimeMillis() - c.buryDelay > 1500) {
				c.getItems().addItem(1779, 1);
				c.turnPlayerTo(c.objectX, c.objectY);
				//c.setAnimation(Animation.create(2292));
				c.startAnimation(2292);
				c.getPA().object(-1, 2739, 3444, 0, 10);
				c.sendMessage("You pick some flax.");
				c.buryDelay = System.currentTimeMillis();

			}
			break;

		case 4787:
		case 4788:
			if (c.getY() < 2767) {
				if (c.playerLevel[16] >= 99
						&& c.playerLevel[c.playerThieving] >= 99) {
					c.getPA().movePlayer(2720, 2767, 0);
					c.sendMessage("You have been allowed to enter Ape Atoll.");
				} else {
					c.sendMessage("You must have an agility and thieving level of 99 to access Ape Atoll.");
				}
			} else {
				c.getPA().movePlayer(2720, 2765, 0);
			}
			break;

			/**
			 * Crates
			 **/

		case 5100:
			if (c.playerLevel[16] >= 45) {
				if (c.getX() == 2654 && c.getY() == 9567 || c.getX() == 2655
						&& c.getY() == 9566 || c.getX() == 2656
						&& c.getY() == 9567) {
					c.getPA().movePlayer(2655, 9573, 0);
				} else if (c.getX() == 2655 && c.getY() == 9573
						|| c.getX() == 2654 && c.getY() == 9572) {
					c.getPA().movePlayer(2655, 9566, 0);
				}
			} else {
				c.sendMessage("You must have an agility level of 45 to use this shortcut.");
			}
			break;

		case 5110:
			if (c.playerLevel[16] >= 65) {
				c.getPA().movePlayer(2647, 9557, 0);
			} else {
				c.sendMessage("You must have an agility level of 65 to use this shortcut.");
			}
			break;

		case 5111:
			if (c.playerLevel[16] >= 25) {
				c.getPA().movePlayer(2649, 9562, 0);
			} else {
				c.sendMessage("You must have an agility level of 25 to use this shortcut.");
			}
			break;

		case 5088:
		case 5090:
			if (c.playerLevel[16] >= 58) {
				if (c.getX() == 2687 && c.getY() == 9506) {
					c.getPA().movePlayer(2682, 9506, 0);
				} else if (c.getX() == 2682 && c.getY() == 9506) {
					c.getPA().movePlayer(2687, 9506, 0);
				}
			} else {
				c.sendMessage("You must have an agility level of 55 to use this shortcut.");
			}
			break;

		case 5099:
			if (c.playerLevel[16] >= 55) {
				if (c.getX() == 2698 && c.getY() == 9492 || c.getX() == 2697
						&& c.getY() == 9493 || c.getX() == 2699
						&& c.getY() == 9493) {
					c.getPA().movePlayer(2698, 9500, 0);
				} else if (c.getX() == 2697 && c.getY() == 9499) {
					c.getPA().movePlayer(2698, 9492, 0);
				}
			} else {
				c.sendMessage("You must have an agility level of 55 to use this shortcut.");
			}
			break;

		case 5084:
		case 32738:
			c.getPA().movePlayer(Config.HOME_X, Config.HOME_Y, 0);
			break;

		case 5103:
		case 5104:
		case 5105:
		case 5106:
		case 5107:
			if (Woodcutting.hasAxe(c) == true || c.hatchet > 0) {
				if (c.getX() == 2695 && c.getY() == 9482) {
					c.startAnimation(872);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer e) {
							c.getPA().movePlayer(2693, 9482, 0);
							e.stop();
						}

						public void stop() {}	
					}, 7);
				} else if (c.getX() == 2693 && c.getY() == 9482) {
					//c.setAnimation(Animation.create(872));
					c.startAnimation(872);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer e) {
							c.getPA().movePlayer(2695, 9482, 0);
							e.stop();
						}

						public void stop() {}	
					}, 7);
				} else if (c.getX() == 2676 && c.getY() == 9479) {
					c.startAnimation(872);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer e) {
							c.getPA().movePlayer(2674, 9479, 0);
							e.stop();
						}

						public void stop() {}	
					}, 7);
				} else if (c.getX() == 2674 && c.getY() == 9479) {
					c.startAnimation(872);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer e) {
							c.getPA().movePlayer(2676, 9479, 0);
							e.stop();
						}

						public void stop() {}	
					}, 7);
				} else if (c.getX() == 2683 && c.getY() == 9570) {
					c.startAnimation(872);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer e) {
							c.getPA().movePlayer(2683, 9568, 0);
							e.stop();
						}

						public void stop() {}	
					}, 7);
				} else if (c.getX() == 2683 && c.getY() == 9568) {
					c.startAnimation(872);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer e) {
							c.getPA().movePlayer(2683, 9570, 0);
							e.stop();
						}

						public void stop() {}	
					}, 7);
				} else if (c.getX() == 2689 && c.getY() == 9564) {
					c.startAnimation(872);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer e) {
							c.getPA().movePlayer(2691, 9564, 0);
							e.stop();
						}

						public void stop() {}	
					}, 7);
				} else if (c.getX() == 2691 && c.getY() == 9564) {
					c.startAnimation(872);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer e) {
							c.getPA().movePlayer(2689, 9564, 0);
							e.stop();
						}

						public void stop() {}	
					}, 7);
				} else if (c.getX() == 2674 && c.getY() == 9499) {
					c.startAnimation(872);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer e) {
							c.getPA().movePlayer(2672, 9499, 0);
							e.stop();
						}

						public void stop() {}	
					}, 7);
				} else if (c.getX() == 2672 && c.getY() == 9499) {
					c.startAnimation(872);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer e) {
							c.getPA().movePlayer(2674, 9499, 0);
							e.stop();
						}

						public void stop() {}	
					}, 7);
				}
			} else {
				c.sendMessage("You need an axe in order to get through these vines.");
			}
			break;

		case 28716:
		case 28728:
		case 28734:
			c.getPA().showInterface(39700);
			break;

		case 4871:
			c.sendMessage("You do not know how to operate this device.");
			break;

		case 3404:
			c.sendMessage("You hear water flowing through the pipes.");
			break;

			// Cow Milking
		case 8689:
			if (System.currentTimeMillis() - c.buryDelay > 1500) {
				if (c.getItems().playerHasItem(1925, 1)) {
					c.turnPlayerTo(c.objectX, c.objectY);
					//c.setAnimation(Animation.create(2292));
					c.startAnimation(2292);
					c.getItems().addItem(1927, 1);
					c.getItems().deleteItem(1925, 1);
					c.buryDelay = System.currentTimeMillis();
				} else {
					c.sendMessage("You need a bucket to milk a cow!");
				}
			}
			break;

			// Sailing - Gang Planks
		case 2085:
			if (c.aGoodStartStatus == 20) {
				ShipSailing.startTravel(c, 1);
			} else {
				c.getDH().sendDialogues(500, 518);
			}
			break;
		case 2414:
			ShipSailing.startTravel(c, 2);
			break;
		case 2083:
			ShipSailing.startTravel(c, 5);
			break;
		case 2081:
			ShipSailing.startTravel(c, 6);
			break;
		case 14304:
			ShipSailing.startTravel(c, 14);
			break;
		case 14306:
			ShipSailing.startTravel(c, 15);
			break;

		case 7236: //Thieving/WallSafes
			if (System.currentTimeMillis() - c.lastThieve < 2500)
				return;
			c.lastThieve = System.currentTimeMillis();
			c.turnPlayerTo(c.objectX, c.objectY);
			WallSafe.getRandomReward(c);
			break;

			/**
			 * Mining Rocks
			 **/

			// God Wars Teleport
		case 2492:
			if (c.killCount >= 20) {
				c.getDH().sendOption4("Armadyl", "Bandos", "Saradomin",
						"Zamorak");
				c.dialogueAction = 20;
			} else {
				c.sendMessage("You need 20 kill count before teleporting to a boss chamber.");
			}
			break;

		case 1765:
			c.getPA().movePlayer(3069, 10257, 0);
			break;

		case 2882:
		case 2883:
			if (c.objectX == 3268) {
				if (c.absX < c.objectX) {
					c.getPA().walkTo(1, 0);
				} else {
					c.getPA().walkTo(-1, 0);
				}
			}
			break;
		case 272:
			c.getPA().movePlayer(c.absX, c.absY, 1);
			break;

		case 273:
			c.getPA().movePlayer(c.absX, c.absY, 0);
			break;
		case 245:
			c.getPA().movePlayer(c.absX, c.absY + 2, 2);
			break;
		case 246:
			c.getPA().movePlayer(c.absX, c.absY - 2, 1);
			break;
		case 1766:
			c.getPA().movePlayer(3016, 3849, 0);
			break;

			/*
			 * Spell Book Changing
			 */
			// Ancient
			// Lunar
		case 410:
			if (c.playerMagicBook == 1 || c.playerMagicBook == 2 /*&& c.lunarDiplomacy == 9*/) {
				c.playerMagicBook = 3;
				c.setSidebarInterface(6, 29999);
				c.sendMessage("Lunar wisdom fills your mind.");
				c.getPA().resetAutocast();
			} else if (c.playerMagicBook == 3) {
				c.setSidebarInterface(6, 1151); // modern
				c.playerMagicBook = 1;
				c.sendMessage("You feel a drain on your memory.");
				c.autocastId = -1;
				c.getPA().resetAutocast();
			} else {
				c.sendMessage("You have no idea what this altar can do.");
			}
			break;

		case 32015:
			c.getPA().movePlayer(3017, 3848, 0);
			break;

		case 1817:
			c.getPA().startTeleport(3067, 10253, 0, "modern");
			break;

			// Ardounge Leaver
		case 1814:
			c.getPA().startTeleport(3153, 3923, 0, "modern");
			break;

			// Fight Cave Enterance
		case 9356:
			c.getPA().enterCaves();
			// c.sendMessage("Temporarily removed due to bugs.");
			break;
		case 9357:
			c.getPA().resetTzhaar();
			break;

		case 8959:
		case 8958:
			if (c.getX() == 2490 && (c.getY() == 10146 || c.getY() == 10148)) {
				if (c.getPA().checkForPlayer(2490,
						c.getY() == 10146 ? 10148 : 10146)) {
					new Object(6951, c.objectX, c.objectY, c.heightLevel, 1,
							10, 8959, 15);
				}
			}
			break;

			// Banking
		case 2213:
		case 14367:
		case 11758:
		case 3193:
		case 26972:
		case 25808:
		case 22819:
		case 11402:
		case 4483:
		case 27663:
		case 16700:
		case 28089:
		case 3045:
			c.getPA().openUpBank(0);
			break;

		case 2623:
			if (c.absX >= c.objectX) {
				c.getPA().walkTo(-1, 0);
			} else {
				c.getPA().walkTo(1, 0);
			}
			break;

		case 14315:
			if (c.absX == 2657 && c.absY == 2639 && c.teleTimer <= 0)
				PestControl.addToWaitRoom(c);
			break;

			// leaving pc boat
		case 14314:
			if (c.inPcBoat())
				PestControl.leaveWaitingBoat(c);
			c.getPA().walkableInterface(-1);
			break;

		case 1596:
		case 1597:
			if (c.getX() >= c.objectX) {
				c.getPA().walkTo(-1, 0);
			} else {
				c.getPA().walkTo(1, 0);
			}
			break;

		case 14235:
		case 14233:
			if (c.objectX == 2670) {
				if (c.absX <= 2670) {
					c.absX = 2671;
				} else {
					c.absX = 2670;
				}
			}
			if (c.objectX == 2643) {
				if (c.absX >= 2643) {
					c.absX = 2642;
				} else {
					c.absX = 2643;
				}
			}
			if (c.absX <= 2585) {
				c.absY += 1;
			} else {
				c.absY -= 1;
			}
			c.getPA().movePlayer(c.absX, c.absY, 0);
			break;

			// Wilderness obelisks
		case 14829:
		case 14830:
		case 14827:
		case 14828:
		case 14826:
		case 14831:
			// Server.objectHandler.startObelisk(objectType);
			Server.objectManager.startObelisk(objectType);
			break;

			/*
			 * Barrows
			 */

		case 6742:
		case 6723:
		case 6750:
		case 6731:
		case 6717:
		case 6736:
		case 6716:
		case 6735:
			c.sendMessage("The door has been sealed shut by rust.");
			break;

		case 6747:
		case 6728:
		case 6741:
		case 6722:
		case 6740:
		case 6721:
		case 6738:
		case 6719:
		case 6737:
		case 6718:
		case 6745:
		case 6726:
		case 6748:
		case 6729:
		case 6749:
		case 6730:
			Dungeon.openDungeonDoor(c, objectType, obX, obY);
			break;

		case 6746:
		case 6727:
		case 6739:
		case 6720:
		case 6724:
		case 6743:
		case 6744:
		case 6725:
			Dungeon.puzzleDoors(c, objectType, obX, obY);
			break;

			// Chest
		case 10284:
			Chest.lootChest(c);
			break;

			// coffins
		case 6707: // verac
			c.getPA().movePlayer(3556, 3298, 0);
			c.absX = 3556;
			c.absY = 3298;
			c.getPA().walkableInterface(-1);
			break;
		case 6823:
			if (Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if (c.barrowsNpcs[0][1] == 0) {
				NPCHandler.spawnNpc(c, 2030, c.getX(), c.getY() - 1, -1, 0,
						120, 25, 200, 200, true, true);
				c.barrowsNpcs[0][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
		case 6706: // torag
			c.getPA().movePlayer(3553, 3283, 0);
			c.absX = 3553;
			c.absY = 3283;
			c.getPA().walkableInterface(-1);
			break;
		case 6772:
			if (game.minigame.barrows.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if (c.barrowsNpcs[1][1] == 0) {
				NPCHandler.spawnNpc(c, 2029, c.getX() + 1, c.getY(), -1, 0,
						120, 20, 200, 200, true, true);
				c.barrowsNpcs[1][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
		case 6705: // karil stairs
			c.getPA().movePlayer(3565, 3276, 0);
			c.absX = 3565;
			c.absY = 3276;
			c.getPA().walkableInterface(-1);
			break;
		case 6822:
			if (game.minigame.barrows.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if (c.barrowsNpcs[2][1] == 0) {
				NPCHandler.spawnNpc(c, 2028, c.getX(), c.getY() - 1, -1, 0, 90,
						17, 200, 200, true, true);
				c.barrowsNpcs[2][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
		case 6704: // guthan stairs
			c.getPA().movePlayer(3578, 3284, 0);
			c.absX = 3578;
			c.absY = 3284;
			c.getPA().walkableInterface(-1);
			break;
		case 6773:
			if (game.minigame.barrows.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if (c.barrowsNpcs[3][1] == 0) {
				NPCHandler.spawnNpc(c, 2027, c.getX(), c.getY() - 1, -1, 0,
						120, 23, 200, 200, true, true);
				c.barrowsNpcs[3][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
		case 6703: // dharok stairs
			c.getPA().movePlayer(3574, 3298, 0);
			c.absX = 3574;
			c.absY = 3298;
			c.getPA().walkableInterface(-1);
			break;
		case 6771:
			if (game.minigame.barrows.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if (c.barrowsNpcs[4][1] == 0) {
				NPCHandler.spawnNpc(c, 2026, c.getX() + 1, c.getY() + 2 - 1,
						-1, 0, 120, 45, 250, 250, true, true);
				c.barrowsNpcs[4][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
		case 6702: // ahrim stairs
			c.getPA().movePlayer(3565, 3290, 0);
			c.absX = 3565;
			c.absY = 3290;
			c.getPA().walkableInterface(-1);
			break;
		case 6821:
			if (game.minigame.barrows.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if (c.barrowsNpcs[5][1] == 0) {
				NPCHandler.spawnNpc(c, 2025, c.getX() + 1, c.getY() - 1, -1, 0,
						90, 19, 200, 200, true, true);
				c.barrowsNpcs[5][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;

			// DOORS
		case 1516:
		case 1519:
			if (c.objectY == 9698) {
				if (c.absY >= c.objectY) {
					c.getPA().walkTo(0, -1);
				} else {
					c.getPA().walkTo(0, 1);
				}
				break;
			}

		case 28589:
			if (c.objectY == 3450) {
				if (c.absX <= c.objectX) {
					c.getPA().walkTo(1, 0);
				} else {
					c.getPA().walkTo(-1, 0);
				}
				break;
			}
		/*case 1530:
		case 1531:
		case 1533:
		case 1534:
		case 11712:
		case 11711:
		case 11707:
		case 11708:
		case 3198:
		case 3197:
			Server.objectHandler.doorHandling(objectType, c.objectX, c.objectY,
					0);
			break;*/

		case 1530:
			if (c.absX == 2816 && c.absY == 3438) { //Catherby cooking door
				c.getPA().movePlayer(2816, 3439, 0);
			}
			if (c.absX == 2816 && c.absY == 3439) {
				c.getPA().movePlayer(2816, 3438, 0);
			}

			if (c.absX == 3089 && c.absY == 3258) { //Draynor chemist door
				c.getPA().movePlayer(3088, 3258, 0);
			}
			if (c.absX == 3088 && c.absY == 3258) {
				c.getPA().movePlayer(3089, 3258, 0);
			}
		break;

		//Crates order (Santas helper)
		case 7347:
		if (c.santasHelp == 4) {
			c.santasHelp = 5;
			c.sendMessage("You've searched the first correct crate!");
		} else if (c.santasHelp < 7) {
			c.sendMessage("This was not the correct order! Try again!");
			c.santasHelp = 4;
		}
		break;
		case 6911:
		if (c.santasHelp == 5) {
			c.santasHelp = 6;
			c.sendMessage("You've searched the secound correct crate!");
		} else if (c.santasHelp < 7) {
			c.sendMessage("This was not the correct order! Try again!");
			c.santasHelp = 4;
		}
		break;
		case 7348:
		if (c.santasHelp == 6) {
			c.santasHelp = 7;
			c.sendMessage("You've searched the third correct crate!");
		} else if (c.santasHelp < 7) {
			c.sendMessage("This was not the correct order! Try again!");
			c.santasHelp = 4;
		}
		break;
		case 7349:
		if (c.santasHelp == 7) {
			c.santasHelp = 8;
			c.getDH().sendStatement("You've found the third present! Bring it to Santa!");
			c.getItems().addItem(15420, 1);
		} else if (c.santasHelp < 7) {
			c.sendMessage("This was not the correct order! Try again!");
			c.santasHelp = 4;
		}
		break;

		case 21709:
			if (c.absX == 2903 && c.absY == 5470) {
				c.getPA().movePlayer(2902, 5470, 0);
			}
			if (c.absX == 2902 && c.absY == 5470) {
				c.getPA().movePlayer(2903, 5470, 0);
			}
		break;
		case 21687:
			if (c.absX == 2910 && c.absY == 5480) {
				c.getPA().movePlayer(2910, 5481, 0);
			}
			if (c.absX == 2910 && c.absY == 5481) {
				c.getPA().movePlayer(2910, 5480, 0);
			}
		break;
		case 21753:
			if (c.absX == 2920 && c.absY == 5473) {
				c.getPA().movePlayer(2921, 5473, 0);
			}
			if (c.absX == 2921 && c.absY == 5473) {
				c.getPA().movePlayer(2920, 5473, 0);
			}
		break;
		case 21731:
			if (c.absX == 2913 && c.absY == 5463) {
				c.getPA().movePlayer(2913, 5462, 0);
			}
			if (c.absX == 2913 && c.absY == 5462) {
				c.getPA().movePlayer(2913, 5463, 0);
			}
		break;
		case 21767:
			if (c.santasHelp == 2) {
				c.getItems().addItem(15420, 1);
				c.santasHelp = 3;
				c.getDH().sendStatement("You've found the secound present! Bring it to Santa!");
			}
		break;

		case 9319:
			if (c.heightLevel == 0) {
				c.getPA().movePlayer(c.absX, c.absY, 1);
			} else if (c.heightLevel == 1) {
				c.getPA().movePlayer(c.absX, c.absY, 0);
			}
			break;

		case 9320:
			if (c.heightLevel == 1) {
				c.getPA().movePlayer(c.absX, c.absY, 0);
			} else if (c.heightLevel == 2) {
				c.getPA().movePlayer(c.absX, c.absY, 1);
			}
			break;

		case 4496:
		case 4494:
			if (c.heightLevel == 2) {
				c.getPA().movePlayer(c.absX - 5, c.absY, 1);
			} else if (c.heightLevel == 1) {
				c.getPA().movePlayer(c.absX + 5, c.absY, 0);
			}
			break;

		case 4493:
			if (c.heightLevel == 0) {
				c.getPA().movePlayer(c.absX - 5, c.absY, 1);
			} else if (c.heightLevel == 1) {
				c.getPA().movePlayer(c.absX + 5, c.absY, 2);
			}
			break;

		case 4495:
			if (c.heightLevel == 1) {
				c.getPA().movePlayer(c.absX + 5, c.absY, 2);
			}
			break;

		case 5126:
			if (c.absY == 3554) {
				c.getPA().walkTo(0, 1);
			} else {
				c.getPA().walkTo(0, -1);
			}
			break;

		case 1755:
			if (c.objectX == 2884 && c.objectY == 9797) {
				c.getPA().movePlayer(c.absX, c.absY - 6400, 0);
			} else {
				c.sendMessage("This ladder leads to an unimportant place.");
			}
			break;
		case 1759:
			if (c.objectX == 2884 && c.objectY == 3397) {
				c.getPA().movePlayer(c.absX, c.absY + 6400, 0);
			}
			break;

		case 4859: // Alter recharging
		case 409:
			if(System.currentTimeMillis() - c.lastPrayed < 60000 && c.isPVPActive == true) {
				c.sendMessage("You can only pray once every minute in PvP Mode.");
				return;
			} else {
				if (c.prayer < c.getMaxPrayer()) {
					//c.setAnimation(Animation.create(645));
					c.startAnimation(645);
					c.prayer = c.getMaxPrayer();
					c.sendMessage("You recharge your prayer points.");
					c.getPA().refreshSkill(5);
					if (c.isPVPActive == true) {
						c.lastPrayed = System.currentTimeMillis();	
					}
				} else {
					c.sendMessage("You already have full prayer points.");
				}
			}
			break;

			/**
			 * Mage arena cape gifts
			 */
		case 2873:
			if (!c.getItems().ownsCape()) {
				//c.setAnimation(Animation.create(645));
				c.startAnimation(645);
				c.sendMessage("Saradomin blesses you with a cape.");
				c.getItems().addItem(2412, 1);
			} else {
				c.sendMessage("You've already been blessed by another god.");
			}
			break;
		case 2875:
			if (!c.getItems().ownsCape()) {
				//c.setAnimation(Animation.create(645));
				c.startAnimation(645);
				c.sendMessage("Guthix blesses you with a cape.");
				c.getItems().addItem(2413, 1);
			} else {
				c.sendMessage("You've already been blessed by another god.");
			}
			break;
		case 2874:
			if (!c.getItems().ownsCape()) {
				//c.setAnimation(Animation.create(645));
				c.startAnimation(645);
				c.sendMessage("Zamorak blesses you with a cape.");
				c.getItems().addItem(2414, 1);
			} else {
				c.sendMessage("You've already been blessed by another god.");
			}
			break;

			/**
			 * Mage Bank Sparkling Pool
			 */
		case 2879:
			//c.getPA().movePlayer(2538, 4716, 0);
			//c.setAnimation(Animation.create(2589));
			c.startAnimation(2589);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer e) {
					c.getPA().movePlayer(2542, 4718, 0);
					e.stop();
				}

				public void stop() {}	
			}, 2);
			break;
		case 2878:
			//c.getPA().movePlayer(2509, 4689, 0);
			//c.setAnimation(Animation.create(2589));
			c.startAnimation(2589);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer e) {
					c.getPA().movePlayer(2509, 4689, 0);
					e.stop();
				}

				public void stop() {}	
			}, 2);
			break;
			/**
			 * Mage Bank End
			 */

		case 5960:
			c.getPA().startTeleport2(3090, 3956, 0);
			break;

		case 1815:
			c.getPA().startTeleport2(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0);
			break;

		case 9706:
			c.getPA().startTeleport2(3105, 3951, 0);
			break;
		case 9707:
			c.getPA().startTeleport2(3105, 3956, 0);
			break;

		case 5959:
			c.getPA().startTeleport2(2539, 4712, 0);
			break;

		case 2558:
			c.sendMessage("This door is locked.");
			break;

		case 9294:
			if (c.absX < c.objectX) {
				c.getPA().movePlayer(c.objectX + 1, c.absY, 0);
			} else if (c.absX > c.objectX) {
				c.getPA().movePlayer(c.objectX - 1, c.absY, 0);
			}
			break;

		case 9293:
			if (c.absX < c.objectX) {
				if (c.playerLevel[16] >= 80) {
					c.getPA().movePlayer(2892, 9799, 0);
				} else {
					c.sendMessage("You must have an agility level of at least 80 to use this shortcut.");
				}
			} else {
				if (c.playerLevel[16] >= 80) {
					c.getPA().movePlayer(2886, 9799, 0);
				} else {
					c.sendMessage("You must have an agility level of at least 80 to use this shortcut.");
				}
			}
			break;

		case 4487:
		case 4490:
			if (c.absY < 3536) {
				c.getPA().movePlayer(3428, 3536, 0);
			} else {
				c.getPA().movePlayer(3428, 3535, 0);
			}
			break;

		case 1551:
			if (c.absY < 3268) {
				c.getPA().movePlayer(3252, 3268, 0);
			} else {
				c.getPA().movePlayer(3252, 3267, 0);
			}
			break;

		case 10529:
		case 10527:
			if (c.absY <= c.objectY) {
				c.getPA().walkTo(0, 1);
			} else {
				c.getPA().walkTo(0, -1);
			}
			break;

		case 3044:
			c.getSmithing().sendSmelting();
			break;

			// Web Slashing
		case 733:
			//c.setAnimation(Animation.create(15071));
			c.startAnimation(15071);
			if (Misc.random(1) == 1) {
				c.getPA().removeObject(c.objectX, c.objectY);
				c.sendMessage("You slash the web.");
			} else {
				c.sendMessage("You fail to slash the webs.");
			}
			if (c.objectX == 3158 && c.objectY == 3951) {
				new Object(734, c.objectX, c.objectY, c.heightLevel, 1, 10,
						733, 50);
			} else {
				new Object(734, c.objectX, c.objectY, c.heightLevel, 0, 10,
						733, 50);
			}
			break;

		case 11666:
		//case 26814:
		case 2781:
		case 3994:
			Smelting.startSmelting(c, objectType);
			break;

		}
	}

}
