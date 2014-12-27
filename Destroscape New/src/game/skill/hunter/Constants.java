package game.skill.hunter;

import engine.util.Misc;
import game.entity.player.Player;

/**
 * @author Ochroid | Scott
 */
final class Constants {

	/**
	 * Variable Declaration
	 */
	protected static final int HUNTER = 21, JAR = 11260, BUTTERFLY_NET = 10010,
			MAGIC_NET = 11259, BUTTERFLY_JAR = 10012, NPCID = 0, OBJECTID = 1,
			CATCHID = 2, ANIM = 3, ITEMID = 4, LAYANIM = 5, XP = 6, LOOT = 7,
			REQ = 8, BAIT = 9, ORIG = 10, TREE = 11;

	/**
	 * Checks if player has the magic buttefly net
	 * 
	 * @param c
	 *            Client
	 * @return Returns whether or not the player has the net
	 */
	protected static boolean hasMagicNet(final Player c) {
		return c.playerEquipment[c.playerWeapon] == Constants.MAGIC_NET;
	}

	/**
	 * Checks if player has a regular butterfly net
	 * 
	 * @param c
	 *            Client
	 * @return
	 */
	protected static boolean hasRegularNet(final Player c) {
		return c.playerEquipment[c.playerWeapon] == Constants.BUTTERFLY_NET;
	}

	/**
	 * Checks if jar will break
	 * 
	 * @param c
	 *            Client
	 * @return Wether or not the jar will break
	 */
	protected static boolean breakJar(final Player c) {
		return Misc.random(7) == 0;
	}

}
