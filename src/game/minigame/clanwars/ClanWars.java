package game.minigame.clanwars;

/**
 * @author Karma
 * @param Divine Fate
 */

import game.entity.player.Player;

public class ClanWars {
	
	public static Player p;
	
	public long startTimer = 60000;
	
	public static boolean food = false, drink = false, prayer = false;
	
	public void process() {
		if (startTimer > 0) {
			startTimer--;
			return;
		} else {
			startTimer = 60000;
		}
		if (startTimer == 0) {
			startClanWars();
		}
	}
	
	public static void getRules() {
		if (food == false) {
			//TODO
		} else if (food == true) {
			//TODO
		}
		if (drink == false) {
			//TODO
		} else if (drink == true) {
			//TODO
		}
		if (prayer = false) {
			//TODO
		} else if (prayer == true) {
			
		}
	}
	
	public static void startClanWars() {
		//TODO
	}
	
	public static void endClanWars() {
		//TODO
	}
}