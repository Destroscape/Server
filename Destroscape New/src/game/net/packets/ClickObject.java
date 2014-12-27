package game.net.packets;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.util.Misc;
import game.Config;
import game.entity.player.Player;
import game.minigame.bountyhunter.BountyHunter;
import game.net.packets.clickobject.areas.AncientCavern;
import game.object.SingleDoorHandler;
import game.skill.SkillHandler;
import game.skill.dungeoneering.Dungeoneering;
import game.skill.mining.Mining;
import game.skill.runecrafting.Runecrafting;
import game.skill.summoning.Summoning;
import game.skill.crafting.JewelryMaking;
import game.content.achievement.*;

/**
 * Click Object
 */
public class ClickObject implements PacketType {

	public static final int FIRST_CLICK = 132, SECOND_CLICK = 252,
			THIRD_CLICK = 70;

	@SuppressWarnings("unused")
	@Override
	public void processPacket(final Player c, int packetType, int packetSize) {
		c.clickObjectType = c.objectX = c.objectId = c.objectY = 0;
		c.objectYOffset = c.objectXOffset = 0;
		c.getPA().resetFollow();
		switch (packetType) {

		case FIRST_CLICK:
			c.objectX = c.getInStream().readSignedWordBigEndianA();
			c.objectId = c.getInStream().readUnsignedWord();
			c.objectY = c.getInStream().readUnsignedWordA();
			c.turnPlayerTo(c.objectX, c.objectY);
			c.objectDistance = 1;
			AncientCavern.handleObjects(c, c.objectId, c.objectX, c.objectY, 0);
			Runecrafting.craftRunes(c, c.objectId);
			if (c.goodDistance(c.getX(), c.getY(), c.objectX, c.objectY, 1)) {
				if (SingleDoorHandler.getSingleton().handleDoor(c.objectId,
						c.objectX, c.objectY, c.heightLevel)) {
				}
			}

			if (c.playerRights == 2) {
				c.sendMessage("ObjectId: " + c.objectId + " objectX: "
						+ c.objectX + " objectY: " + c.objectY);
			}
			if (Math.abs(c.getX() - c.objectX) > 25
					|| Math.abs(c.getY() - c.objectY) > 25) {
				c.resetWalkingQueue();
				break;
			}
			if (c.playerRights >= 3) {
				Misc.println("1ST objectId: " + c.objectId + "  ObjectX: "
						+ c.objectX + "  objectY: " + c.objectY + " Xoff: "
						+ (c.getX() - c.objectX) + " Yoff: "
						+ (c.getY() - c.objectY));
			}
			switch (c.objectId) {
			case 14929:
			case 14931:
				c.sendMessage("I'm sorry, Players are not permitted to go outside the colony!");
			break;
			case 14923:
				c.sendMessage("I'm sorry, This area has been closed!");
			break;
			case 14973:
				c.getItems().addItem(303, 1);
				c.sendMessage("You take a small fishing net...");
			break;
			/**
			 * Brimhaven Staircase
			 */
			case 5094:
				if (c.absY == 9591) {
					c.getPA().movePlayer(2643, 9595, 2);
				}
				break;

			case 5096:
				if (c.absX == 2643) {
					c.getPA().movePlayer(2649, 9591, 0);
				}
				break;

			case 5097:
				if (c.absY == 9517) {
					c.getPA().movePlayer(2637, 9510, 2);
				}
				break;

			case 5098:
				if (c.absY == 9510) {
					c.getPA().movePlayer(2636, 9517, 0);
				}
				break;

			case 26814:
				c.objectDistance = 3;
					JewelryMaking.jewelryInterface(c);
				break;
			case 28734:
				c.objectDistance = 2;
				break;
				/**
				 * Brimhaven Staircase End
				 */

			case 23271: // Wilderness Ditch
				if (c.absY == 3523) {
					c.getPA().ditchJump(c, 0, -3);
				}
				/*if (c.absY <= 3518) {
					c.getPA().ditchJump(c, 0, 5);
				}*/
				if (c.absY == 3520) {
					c.getPA().ditchJump(c, 0, 3);
				}
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						c.getPA().resetDitchJump(c);
						container.stop();
					}

					@Override
					public void stop() {

					}
				}, 4);
				break;

			case 2561:
				c.objectDistance = 400000;
				c.objectXOffset = 20;
				c.objectYOffset = 20;
				break;

			case 2287:
				c.objectDistance = 4;
				if (c.playerLevel[c.playerAgility] < 65) {
					c.sendMessage("You must have an agility level of at least 65 to enter");
					return;
				} else {
					c.getPA().movePlayer(2552, 3558, 0);
				}
				break;

			case 2491:
				if (SkillHandler.noInventorySpace(c, "mining")) {
					Mining.mineEss(c);
				} else {
					Mining.resetMining(c);
					return;
				}
				break;

			case 1816:
				if (c.getY() == 10254) {
					c.getPA().startTeleport2(2271, 4680, 0);
				}
				break;

			case 1738:
				if (c.getX() == 2841 || c.getY() == 3539) {
					c.getPA().movePlayer(2840,3539, 2);
				}
				break;

			case 28122:	
				if (c.getY() == 3701 || c.getY() == 3696 || c.getX() == 3146 || 
				c.getY() == 3669 || c.getY() == 3665 || c.getY() == 3670 || 
				c.getX() == 3101 || c.getX() == 3096 || c.getX() == 3091 ||
				c.getX() == 3086 || c.getY() == 3735 || c.getY() == 3747 ||
				c.getY() == 3754 || c.getY() == 3758 || c.getY() == 3753 ||
				c.getY() == 3746 || c.getX() == 3171 || c.getX() == 3181 ||
				c.getX() == 3180) {
					BountyHunter.leaveBH(c);
				}
				break;

				/*case 28122:
				c.getPA().movePlayer(3164, 3685, 0);
				break;*/

		case 1530:
			if (c.absY == 2816 && c.absX == 3438) { //Catherby cooking door
				c.getPA().movePlayer(2816, 3439, 0);
			}
			if (c.absY == 2816 && c.absX == 3439) {
				c.getPA().movePlayer(2816, 3438, 0);
			}

			if (c.absY == 3089 && c.absX == 3258) { //Draynor chemist door
				c.getPA().movePlayer(3088, 3258, 0);
			}
			if (c.absY == 3088 && c.absX == 3258) {
				c.getPA().movePlayer(3089, 3258, 0);
			}
		break;

			case 26288:
			case 26445:
			case 26287:
				c.objectDistance = 5;
				break;

			case 29954:
			case 29944:
				Summoning.rechargeAtObelisk(c);
				break;

			case 8959:
				c.objectDistance = 5;
				c.getPA().startTeleport(1910, 4367, 0, "modern");
				break;

			case 26303:
				c.objectDistance = 9;
				break;


			case 21767:
				c.objectDistance = 2 ;
				break;

			case 3221:
			case 3220:
				c.objectDistance = 5;
				break;

			case 2282:
				c.objectDistance = 5;
				break;

			case 26387:
			case 26384:
				c.objectDistance = 4;
				break;

			case 8143:
				c.getFarming().pickHerb();
				break;

			case 28698:
				if (c.playerLevel[20] < 70) {
					c.sendMessage("You need a runecrafting level of atleast <col=255>70</col> to create these.");
					return;
				}

						if (!c.getItems().playerHasItem(21774)) {
							c.sendMessage("You do not have any <col=255>Armadyl Dust</col> to create this.");
							return;
						}
					if (c.getItems().playerHasItem(1436)) {
						//c.setAnimation(Animation.create(791));
						c.startAnimation(791);
						c.gfx100(186);
						while (c.getItems().playerHasItem(1436)) {
							c.getItems().deleteItem(1436, 1);
							c.getItems().deleteItem(21774, 1);
							c.getItems().addItem(21773, 1);
								c.getPA().addSkillXP(500, 20);
						}
					}
				break;

			case 26969:
			case 9398:// deposit
				c.getPA().sendFrame126("The Bank of Destroscape - Deposit Box",
						7421);
				c.getPA().sendFrame248(4465, 197);//
				c.getItems().resetItems(7423);
				break;

			case 1733:
				c.objectYOffset = 2;
				break;

			case 3736:
			case 32738:
				c.objectDistance = 500;
				if (c.finishedBeg) {
					c.getPA().movePlayer(Config.HOME_X, Config.HOME_Y, 0);
					c.sendMessage(Config.WELCOME_MESSAGE);
				} else {
					c.sendMessage("You must finish talking to the beginner guide, to enter this cave.");
					return;
				}
				break;

			case 28120://bounty hunter entrances
			case 28119:
			case 28121:
				if(c.absY == 3685 || c.absX == 3158 || c.absX == 3152) {
					BountyHunter.enterBH(c, c.objectId);
				}
				break;

			case 28716:
				c.objectDistance = 3;
				break;

			case 6372:
				c.objectDistance = 3;
				c.sendMessage("The staircase is in terrible shape, making it unsafe to climb.");
				break;

			case 3044:
				c.objectDistance = 3;
				break;

			case 6310:
			case 13409:
				c.objectDistance = 2;
				if (Config.DUNGEONEERING_ACCESSIBLE == true) {
					if (c.teleTimer > 0) {
						c.sendMessage("You cannot enter a dungeon when already teleporting.");
						return;
					}
					if (!c.checkEmpty(c)) {
						return;
					}
					Dungeoneering.startDungeon(c);
					if (c.floor > 0 && c.complexity > 0) {
						c.hasClickedDung = true;
					}
				} else {
					c.sendMessage("Dungeoneering is currently disabled.");
				}
				break;

			case 245:
				c.objectYOffset = -1;
				c.objectDistance = 0;
				break;

			case 272:
				c.objectYOffset = 1;
				c.objectDistance = 0;
				break;

			case 273:
				c.objectYOffset = 1;
				c.objectDistance = 0;
				break;

			case 246:
				c.objectYOffset = 1;
				c.objectDistance = 0;
				break;

			case 11:
			case 32270:
				if (c.inDung()) {
					if (c.bossDead) {
						if (!c.hasRecievedRewards) {
							c.dTokens += c.addTokens(c);
							c.getPA().addSkillXP(c.addDungXP(c), 23);

			c.sendMessage("You have completed the floor, and you gained "
					+ c.addDungXP(c)
					+ " XP, and "
					+ c.addTokens(c) + " tokens.");
			AchievementManager.increase(c, Achievements.DUNGEONEERER);
							c.hasRecievedRewards = true;
		c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
		c.getPA().refreshSkill(5);
		for (int i = 0; i < 23; i++) {
			c.playerLevel[i] = c.getPA().getLevelForXP(c.playerXP[i]);
			c.getPA().refreshSkill(i);
		}
						}
						Dungeoneering.abandonDung(c);
					} else {
						c.sendMessage("You cannot do this yet!");
					}
				}
				c.objectDistance = 2;
				break;

			case 4493:
			case 4494:
			case 4496:
			case 4495:
				c.objectDistance = 4;
				break;

			case 10229:
			case 6522:
				c.objectDistance = 2;
				break;

			case 4417:
				if (c.objectX == 2425 && c.objectY == 3074)
					c.objectYOffset = 2;
				break;

			case 4420:
				if (c.getX() >= 2383 && c.getX() <= 2385) {
					c.objectYOffset = 1;
				} else {
					c.objectYOffset = -2;
				}
			case 6552:
			case 409:
				c.objectDistance = 2;
				break;
			case 2879:
			case 2878:
			case 2781:
				c.objectDistance = 3;
				break;
			case 2558:
				c.objectDistance = 0;
				if (c.absX > c.objectX && c.objectX == 3044)
					c.objectXOffset = 1;
				if (c.absY > c.objectY)
					c.objectYOffset = 1;
				if (c.absX < c.objectX && c.objectX == 3038)
					c.objectXOffset = -1;
				break;
			case 9356:
				c.objectDistance = 2;
				break;
			case 5959:
			case 1815:
			case 5960:

			case 9293:
				c.objectDistance = 2;
				break;
			case 4418:
				if (c.objectX == 2374 && c.objectY == 3131)
					c.objectYOffset = -2;
				else if (c.objectX == 2369 && c.objectY == 3126)
					c.objectXOffset = 2;
				else if (c.objectX == 2380 && c.objectY == 3127)
					c.objectYOffset = 2;
				else if (c.objectX == 2369 && c.objectY == 3126)
					c.objectXOffset = 2;
				else if (c.objectX == 2374 && c.objectY == 3131)
					c.objectYOffset = -2;
				break;
			case 9706:
				c.objectDistance = 0;
				c.objectXOffset = 1;
				break;
			case 9707:
				c.objectDistance = 0;
				c.objectYOffset = -1;
				break;
			case 4419:
			case 6707: // verac
				c.objectYOffset = 3;
				break;
			case 6823:
				c.objectDistance = 2;
				c.objectYOffset = 1;
				break;

			case 6706: // torag
				c.objectXOffset = 2;
				break;
			case 6772:
				c.objectDistance = 2;
				c.objectYOffset = 1;
				break;

			case 6705: // karils
				c.objectYOffset = -1;
				break;
			case 6822:
				c.objectDistance = 2;
				c.objectYOffset = 1;
				break;

			case 6704: // guthan stairs
				c.objectYOffset = -1;
				break;
			case 6773:
				c.objectDistance = 2;
				c.objectXOffset = 1;
				c.objectYOffset = 1;
				break;

			case 6703: // dharok stairs
				c.objectXOffset = -1;
				break;
			case 6771:
				c.objectDistance = 2;
				c.objectXOffset = 1;
				c.objectYOffset = 1;
				break;

			case 6702: // ahrim stairs
				c.objectXOffset = -1;
				break;
			case 6821:
				c.objectDistance = 2;
				c.objectXOffset = 1;
				c.objectYOffset = 1;
				break;
			case 1276:
			case 1278:// trees
			case 1281: // oak
			case 1308: // willow
			case 1307: // maple
			case 1309: // yew
			case 1306: // yew
				c.objectDistance = 3;
				break;
			default:
				c.objectDistance = 1;
				c.objectXOffset = 0;
				c.objectYOffset = 0;
				break;
			}
			if (c.goodDistance(c.objectX + c.objectXOffset, c.objectY
					+ c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
				c.getActions().firstClickObject(c.objectId, c.objectX,
						c.objectY);
			} else {
				c.clickObjectType = 1;
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						if (c.clickObjectType == 1
								&& c.goodDistance(c.objectX + c.objectXOffset,
										c.objectY + c.objectYOffset, c.getX(),
										c.getY(), c.objectDistance)) {
							c.getActions().firstClickObject(c.objectId,
									c.objectX, c.objectY);
							container.stop();
						}
						if (c.clickObjectType > 1 || c.clickObjectType == 0)
							container.stop();
					}

					@Override
					public void stop() {
						c.clickObjectType = 0;
					}
				}, 1);
			}
			break;

		case SECOND_CLICK:
			c.objectId = c.getInStream().readUnsignedWordBigEndianA();
			c.objectY = c.getInStream().readSignedWordBigEndian();
			c.objectX = c.getInStream().readUnsignedWordA();
			c.objectDistance = 1;
			c.turnPlayerTo(c.objectX, c.objectY);

			if (c.playerRights >= 3) {
				Misc.println("2ND objectId: " + c.objectId + "  ObjectX: "
						+ c.objectX + "  objectY: " + c.objectY + " Xoff: "
						+ (c.getX() - c.objectX) + " Yoff: "
						+ (c.getY() - c.objectY));
			}

			switch (c.objectId) {
			case 2565:
			case 2564:
			case 2563:
			case 2562:
			case 2561:
			case 2560:
			case 14011:
			case 7053:
				c.objectDistance = 4;
				break;
			case 6163:
			case 6165:
			case 6166:
			case 6164:
			case 6162:
				c.objectDistance = 2;
				break;
			case 26814:
				JewelryMaking.jewelryInterface(c);
				break;
			default:
				c.objectDistance = 1;
				c.objectXOffset = 0;
				c.objectYOffset = 0;
				break;

			}
			if (c.goodDistance(c.objectX + c.objectXOffset, c.objectY
					+ c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
				c.getActions().secondClickObject(c.objectId, c.objectX,
						c.objectY);
			} else {
				c.clickObjectType = 2;
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						if (c.clickObjectType == 2
								&& c.goodDistance(c.objectX + c.objectXOffset,
										c.objectY + c.objectYOffset, c.getX(),
										c.getY(), c.objectDistance)) {
							c.getActions().secondClickObject(c.objectId,
									c.objectX, c.objectY);
							container.stop();
						}
						if (c.clickObjectType < 2 || c.clickObjectType > 2)
							container.stop();
					}

					@Override
					public void stop() {
						c.clickObjectType = 0;
					}
				}, 1);
			}
			break;

		case THIRD_CLICK:
			c.objectX = c.getInStream().readSignedWordBigEndian();
			c.objectY = c.getInStream().readUnsignedWord();
			c.objectId = c.getInStream().readUnsignedWordBigEndianA();
			c.turnPlayerTo(c.objectX, c.objectY);
			if (c.playerRights >= 3) {
				Misc.println("objectId: " + c.objectId + "  ObjectX: "
						+ c.objectX + "  objectY: " + c.objectY + " Xoff: "
						+ (c.getX() - c.objectX) + " Yoff: "
						+ (c.getY() - c.objectY));
			}

			switch (c.objectId) {
			default:
				c.objectDistance = 1;
				c.objectXOffset = 0;
				c.objectYOffset = 0;
				break;
			}
			if (c.goodDistance(c.objectX + c.objectXOffset, c.objectY
					+ c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
				c.getActions().secondClickObject(c.objectId, c.objectX,
						c.objectY);
			} else {
				c.clickObjectType = 3;
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						if (c.clickObjectType == 3
								&& c.goodDistance(c.objectX + c.objectXOffset,
										c.objectY + c.objectYOffset, c.getX(),
										c.getY(), c.objectDistance)) {
							c.getActions().thirdClickObject(c.objectId,
									c.objectX, c.objectY);
							container.stop();
						}
						if (c.clickObjectType < 3)
							container.stop();
					}

					@Override
					public void stop() {
						c.clickObjectType = 0;
					}
				}, 1);
			}
			break;
		}

	}

	public void handleSpecialCase(Player c, int id, int x, int y) {

	}

}
