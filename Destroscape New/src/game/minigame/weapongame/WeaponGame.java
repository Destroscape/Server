package game.minigame.weapongame;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import engine.util.Misc;
import game.combat.prayer.Prayer;
import game.entity.player.Player;

public class WeaponGame {

	// 1 melee, 2 ranged, 3 magic

	private static final ScheduledExecutorService worker = 
			Executors.newSingleThreadScheduledExecutor();

	/**
	 * Handles the entering of the minigame
	 * @param p  The player
	 */

	public static void enterCave(Player p) {
		if (p.currentClass == 1) {
			p.getPA().sendFrame126("Class Level: @red@"+p.meleeClassLvl,18102);
		} else if (p.currentClass == 2) {
			p.getPA().sendFrame126("Class Level: @red@"+p.rangedClassLvl,18102);
		} else if (p.currentClass == 3) {
			p.getPA().sendFrame126("Class Level: @red@"+p.magicClassLvl,18102);
		}
		p.getPA().sendFrame126("Level Potential: @red@"+p.lvlPotential+"%",18103);
		p.getPA().removeAllWindows();
		p.getPA().movePlayer(1630, 5679, 0);
		giveWeapon(p);
	}

	/**
	 * Handles dieing inside the minigame
	 * @param p The Player
	 */

	public static void handleDeath(final Player p) {
		p.specAmount = 10;
		p.sendMessage("Your special attack has been restored.");
		p.getItems().updateSpecialBar();
		Prayer.closeAllPrayers(p);
		p.prayer = p.maxPrayer();
		for (int i = 0; i < 23; i++) {
			p.playerLevel[i] = p.getPA().getLevelForXP(p.playerXP[i]);
			p.getPA().refreshSkill(i);
		}
		p.sendMessage("Your HP and Prayer have been restored.");
		p.weaponDeaths += 1;
		int random = Misc.random(4);
		switch (random) {
		case 1:
			p.getPA().movePlayer(1656, 5683, 0);
			break;
		case 2:
			p.getPA().movePlayer(1651, 5703, 0);
			break;
		case 3:
			p.getPA().movePlayer(1667, 5704, 0);
			break;
		case 4:
			p.getPA().movePlayer(1676, 5688, 0);
			break;
		}
		Runnable task = new Runnable() {
			public void run() {
				p.canLeaveDeath = true;
				p.sendMessage("You can now leave the death chamber.");
			}
		};
		worker.schedule(task, 15, TimeUnit.SECONDS);
	}

	/**
	 * Checks if the player meets the requirements to enter the cave
	 * @param p The player
	 * @return returns if he can enter
	 */

	public static boolean canEnter(Player p) {
		if((p.getItems().freeSlots() == 28 && p.currentClass >= 1 && p.playerEquipment[p.playerHat] == -1) && (p.playerEquipment[p.playerCape] == -1) && (p.playerEquipment[p.playerAmulet] == -1) && (p.playerEquipment[p.playerChest] == -1) && (p.playerEquipment[p.playerShield] == -1) && (p.playerEquipment[p.playerLegs] == -1) && (p.playerEquipment[p.playerHands] == -1) && (p.playerEquipment[p.playerFeet] == -1) && (p.playerEquipment[p.playerWeapon] == -1)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gives the player lvlpotential and checks if the player lvls up
	 * @param p	The player
	 */

	public static void checkLevel(Player p) {
		p.lvlPotential += 15 + Misc.random(20);
		if (p.lvlPotential >= 100) {
			increaseLvl(p);
		}
	}

	/**
	 * Handles what happens when you kill someone
	 * @param p The player
	 */

	public static void handleKill(Player p) {
		checkLevel(p);
		p.getPA().sendFrame126("Level Potential: @red@"+p.lvlPotential+"%",18103);
		p.specAmount = 10;
		p.sendMessage("Your special attack has been restored.");
		p.getItems().updateSpecialBar();
		p.prayer = p.maxPrayer();
		for (int i = 0; i < 23; i++) {
			p.playerLevel[i] = p.getPA().getLevelForXP(p.playerXP[i]);
			p.getPA().refreshSkill(i);
		}
		p.sendMessage("Your HP and Prayer have been restored.");
		int random = Misc.random(3);
		p.weaponKills += 1;
		switch (random) {
		case 1:
			p.getItems().addItem(385, 3 + Misc.random(5));
			p.sendMessage("You get some food!");
			break;
		case 2:
			if (p.currentClass == 1)
				p.getItems().addItem(2440, 1);
			if (p.currentClass == 2)
				p.getItems().addItem(2444, 1);
			if (p.currentClass == 3)
				p.getItems().addItem(3040, 1);

			p.sendMessage("You get a potion!");
			break;
		case 3:
			if (p.currentClass == 1)
				meleeArmor(p);
			if (p.currentClass == 2)
				rangedArmor(p);
			if (p.currentClass == 3)
				magicArmor(p);

			p.sendMessage("You get some armor!");
			break;
		}
	}

	/**
	 * Handles the random melee items someone gets when killing someone
	 * @param p
	 */

	public static void meleeArmor(Player p) {

	}

	/**
	 * Handles the random ranged items someone gets when killing someone
	 * @param p
	 */

	public static void rangedArmor(Player p) {

	}

	/**
	 * Handles the random magic items someone gets when killing someone
	 * @param p
	 */

	public static void magicArmor(Player p) {

	}

	/**
	 * Handles clicking the exit when in the game
	 * @param p The player
	 */

	public static void leaveCave(Player p) {
		resetPlayer(p);
		p.getPA().movePlayer(1718, 5599, 0);
	}

	/**
	 * Increases the class level of the player by 1
	 * @param p The player
	 */

	public static void increaseLvl(Player p) {
		if (p.currentClass == 1)
			p.meleeClassLvl += 1;
		if (p.currentClass == 2)
			p.rangedClassLvl += 1;
		if (p.currentClass == 3)
			p.magicClassLvl += 1;

		if (p.currentClass == 1) {
			p.getPA().sendFrame126("Class Level: @red@"+p.meleeClassLvl,18102);
		} else if (p.currentClass == 2) {
			p.getPA().sendFrame126("Class Level: @red@"+p.rangedClassLvl,18102);
		} else if (p.currentClass == 3) {
			p.getPA().sendFrame126("Class Level: @red@"+p.magicClassLvl,18102);
		}

		p.sendMessage("You have increased your class level!");

		p.lvlPotential = 0;
	}

	/**
	 * Rests the game constants of the player.
	 * @param p The Player
	 */

	public static void resetPlayer(Player p) {
		p.currentClass = 0;
		p.getPA().removeAllItems();
		giveWeapon(p, -1);
		giveArrows(p, -1);
	}

	/**
	 * Places a weapon in the weapon slot of the player
	 * @param p	The player
	 * @param itemId The item id of the weapon
	 */

	public static void giveWeapon(Player p, int itemId) {
		p.playerEquipment[p.playerWeapon] = -1;
		p.playerEquipmentN[p.playerWeapon] = 0;
		p.getItems().wearItem(itemId, 1, 3);
	}

	/**
	 * Places 1000 arrows in the arrow slot of the player
	 * @param p	The Player
	 * @param itemId The item id of the arrows
	 */

	public static void giveArrows(Player p, int itemId) {
		p.playerEquipment[p.playerArrows] = -1;
		p.playerEquipmentN[p.playerArrows] = 0;
		p.getItems().wearItem(itemId, 1000, 13);
	}

	/**
	 * Handles the weapon giving depending on the class lvl
	 * @param p
	 */

	public static void giveWeapon(Player p) {
		if (p.currentClass == 1) {
			if (p.meleeClassLvl >= 1 && p.meleeClassLvl <= 5) {
				giveWeapon(p, 1331);
				return;
			}
			if (p.meleeClassLvl >= 6 && p.meleeClassLvl <= 10) {
				giveWeapon(p, 1333);
				return;
			}
			if (p.meleeClassLvl >= 11 && p.meleeClassLvl <= 20) {
				giveWeapon(p, 1215);
				return;
			}
			if (p.meleeClassLvl >= 21 && p.meleeClassLvl <= 30) {
				giveWeapon(p, 4587);
				return;
			}
			if (p.meleeClassLvl >= 31 && p.meleeClassLvl <= 40) {
				giveWeapon(p, 1377);
				return;
			}
			if (p.meleeClassLvl >= 41 && p.meleeClassLvl <= 50) {
				giveWeapon(p, 7158);
				return;
			}
			if (p.meleeClassLvl >= 51 && p.meleeClassLvl <= 60) {
				giveWeapon(p, 4747);
				return;
			}
			if (p.meleeClassLvl >= 61 && p.meleeClassLvl <= 70) {
				giveWeapon(p, 4755);
				return;
			}
			if (p.meleeClassLvl >= 71 && p.meleeClassLvl <= 80) {
				giveWeapon(p, 4726);
				return;
			}
			if (p.meleeClassLvl >= 81 && p.meleeClassLvl <= 90) {
				giveWeapon(p, 4151);
				return;
			}
			if (p.meleeClassLvl >= 91 && p.meleeClassLvl <= 100) {
				giveWeapon(p, 21371);
				return;
			}
			if (p.meleeClassLvl >= 100) {
				int random = Misc.random(4);
				switch (random) {
				case 1:
					giveWeapon(p, 14484);
					break;
				case 2:
					giveWeapon(p, 18349);
					break;
				case 3:
					giveWeapon(p, 18351);
					break;
				case 4:
					giveWeapon(p, 18353);
					break;
				}
				return;
			}
		} else if (p.currentClass == 2) {
			if (p.currentClass >= 1 && p.currentClass <= 5) {
				giveWeapon(p, 857);
				giveArrows(p, 890);
				return;
			}
			if (p.currentClass >= 6 && p.currentClass <= 10) {
				giveWeapon(p, 9183);
				giveArrows(p, 9143);
				return;
			}
			if (p.currentClass >= 11 && p.currentClass <= 20) {
				giveWeapon(p, 861);
				giveArrows(p, 892);
				return;
			}
			if (p.currentClass >= 21 && p.currentClass <= 30) {
				giveWeapon(p, 9185);
				giveArrows(p, 9241);
				return;
			}
			if (p.currentClass >= 31 && p.currentClass <= 40) {
				giveWeapon(p, 9185);
				giveArrows(p, 9341);
				return;
			}
			if (p.currentClass >= 41 && p.currentClass <= 50) {
				giveWeapon(p, 4214);
				return;
			}
			if (p.currentClass >= 51 && p.currentClass <= 60) {
				giveWeapon(p, 11235);
				giveArrows(p, 892);
				return;
			}
			if (p.currentClass >= 61 && p.currentClass <= 70) {
				giveWeapon(p, 11235);
				giveArrows(p, 11212);
				return;
			}
			if (p.currentClass >= 71 && p.currentClass <= 80) {
				giveWeapon(p, 18357);
				giveArrows(p, 9144);
				return;
			}
			if (p.currentClass >= 81 && p.currentClass <= 90) {
				giveWeapon(p, 18357);
				giveArrows(p, 9241);
				return;
			}
			if (p.currentClass >= 91 && p.currentClass <= 100) {
				giveWeapon(p, 18357);
				giveArrows(p, 9244);
				return;
			}
			if (p.currentClass >= 100) {
				giveWeapon(p, 18357);
				giveArrows(p, 9245);
				return;
			}
		}
	}

}
