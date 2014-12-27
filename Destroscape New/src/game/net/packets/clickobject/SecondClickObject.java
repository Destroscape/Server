package game.net.packets.clickobject;

import engine.util.Misc;
import game.Config;
import game.entity.player.Player;
import game.skill.mining.Prospecting;
import game.skill.summoning.Summoning;
import game.skill.thieving.Thieving;

public class SecondClickObject {

	public static void handleClick(Player c, int objectType, int obX, int obY) {
		c.clickObjectType = 0;
		// c.getMining().prospect(c, objectType);
		if (Config.SERVER_DEBUG = true) {
			if (c.playerRights >= 4) {
				Misc.println("Object type: " + objectType);
			}
		}
		Prospecting.prospectRock(c , objectType);
		switch (objectType) {

		case 13996:
			c.clearPlayersInterface();
			c.getPA().sendFrame126("@whi@Completionist Requirements", 8144);
			c.getPA().sendFrame126(" ", 8145);
			c.getPA().sendFrame126("A level of atleast <col=255>99</col> in ALL skills", 8147);
			c.getPA().sendFrame126("A Dungeoneering level of atleast <col=255>120</col>", 8148);
			c.getPA().sendFrame126("And you <col=255>MUST</col> have completed the following:", 8149);
			c.getPA().sendFrame126("Kill a total of <col=255>"+ c.totalNPCKO +"</col>/<col=255>6000</col> NPCs", 8150);
			c.getPA().sendFrame126("Complete all <col=255>"+ c.completeAchievements + "</col>/<col=255>28</col> Achievements", 8151);
			c.getPA().showInterface(8134);
			c.flushOutStream();
			break;

		case 17010:
			if (c.playerMagicBook == 0) {
				c.playerMagicBook = 1;
				c.setSidebarInterface(6, 29999);
				c.sendMessage("You feel a strange wisdom fill your mind...");
				c.getPA().resetAutocast();
			} else {
				c.setSidebarInterface(6, 1151);
				c.playerMagicBook = 0;
				c.sendMessage("You feel a strange drain upon your memory...");
				c.autocastId = -1;
				c.getPA().resetAutocast();
			}
			break;


				case 6:
					c.getCannon().pickUpCannon();
					break;

		case 4483: //rfd chest shop
			if (c.Culin == true) {
				c.getShops().openShop(65);
				return;
			}
			if (c.Agrith == true && c.Flambeed == false) {
				c.getShops().openShop(61);
				return;
			}
			if (c.Flambeed == true && c.Karamel == false) {
				c.getShops().openShop(62);
				return;
			}
			if (c.Karamel == true && c.Dessourt == false) {
				c.getShops().openShop(63);
				return;
			}
			if (c.Dessourt == true && c.Culin == false) {
				c.getShops().openShop(64);
				return;
			}
			if (c.Agrith == false) {
				c.getShops().openShop(60);
			}
			break;

			/**
			 * Second Click Object Action
			 */

		case 2565: // done
			Thieving.stealFromStall(c, 995, 4211, 54, 50, objectType, obX, obY,
					2);
			break;
		case 2564: // done
			Thieving.stealFromStall(c, 995, 5257, 81, 65, objectType, obX, obY,
					0);
			break;
		case 2563: // done
			Thieving.stealFromStall(c, 995, 2834, 35, 36, objectType, obX, obY,
					0);
			break;
		case 2562: // done
			Thieving.stealFromStall(c, 995, 5722, 16, 75, objectType, obX, obY,
					3);
			break;
		case 2561: // done
			Thieving.stealFromStall(c, 995, 1522, 16, 5, objectType, obX, obY,
					obX == 2667 ? 3 : 1);
			break;
		case 2560:
			Thieving.stealFromStall(c, 995, 2250, 24, 20, objectType, obX, obY,
					obX == 2662 ? 2 : 1);
			break;

		case 14011:
			Thieving.stealFromStall(c, 995, 1342, 10, 1, objectType, obX, obY,
					3);
			break;
		case 7053:
			Thieving.stealFromStall(c, 995, 1853, 18, 10, objectType, obX, obY,
					obX == 3079 ? 2 : 1);
			break;

			/**
			 * Home Thieving Stals
			 */
		case 4874:
			Thieving.stealFromStall(c, 995, 2000, 20, 1);
			break;
		case 4875:
			Thieving.stealFromStall(c, 995, 3000, 40, 25);
			break;
		case 4876:
			Thieving.stealFromStall(c, 995, 7000, 70, 50);
			break;
		case 4877:
			Thieving.stealFromStall(c, 995, 8000, 125, 75);
			break;
		case 4878:
			Thieving.stealFromStall(c, 995, 9000, 200, 90);
			break;
		case 6163:
			Thieving.stealFromStall(c, 2503, 1, 120, 80);
			break;
			/**
			 * Stalls End
			 */

		case 28716:
			Summoning.rechargeAtObelisk(c);
			break;

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

			// Smithing
		case 11666:
		case 3044:
			c.getSmithing().sendSmelting();
			break;

			// Bank
			// Banking
		case 2213:
		case 14367:
		case 11758:
		case 3193:
		case 26972:
		case 25808:
		case 22819:
		case 11402:
		case 27663:
		case 16700:
		case 28089:
			c.getPA().openUpBank(0);
			break;

			// Lock Picking
		case 2558:
			if (System.currentTimeMillis() - c.lastLockPick < 3000
					|| c.freezeTimer > 0) {
				break;
			}
			if (c.getItems().playerHasItem(1523, 1)) {
				c.lastLockPick = System.currentTimeMillis();
				if (Misc.random(10) <= 3) {
					c.sendMessage("You fail to pick the lock.");
					break;
				}
				if (c.objectX == 3044 && c.objectY == 3956) {
					if (c.absX == 3045) {
						c.getPA().walkTo2(-1, 0);
					} else if (c.absX == 3044) {
						c.getPA().walkTo2(1, 0);
					}
				} else if (c.objectX == 3038 && c.objectY == 3956) {
					if (c.absX == 3037) {
						c.getPA().walkTo2(1, 0);
					} else if (c.absX == 3038) {
						c.getPA().walkTo2(-1, 0);
					}
				} else if (c.objectX == 3041 && c.objectY == 3959) {
					if (c.absY == 3960) {
						c.getPA().walkTo2(0, -1);
					} else if (c.absY == 3959) {
						c.getPA().walkTo2(0, 1);
					}
				}
			} else {
				c.sendMessage("I need a lockpick to pick this lock.");
			}
			break;
		}
	}

}
