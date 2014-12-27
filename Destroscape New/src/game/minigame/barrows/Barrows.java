package game.minigame.barrows;

import engine.util.Misc;
import game.entity.player.Player;

/**
 * @author Scott Perretta
 */

public class Barrows {

	public static final int[][] COFFIN_AND_BROTHERS = { { 6823, 2030 },
			{ 6772, 2029 }, { 6822, 2028 }, { 6773, 2027 }, { 6771, 2026 },
			{ 6821, 2025 } };

	/**
	 * Picking the random coffin
	 */

	private static int getRandomCoffin() {
		return Misc.random(Barrows.COFFIN_AND_BROTHERS.length - 1);
	}

	/**
	 * Selects the coffin and shows the interface if coffin id matches random
	 * coffin
	 */

	public static boolean selectCoffin(final Player c, final int coffinId) {
		if (c.randomCoffin == 0) {
			c.randomCoffin = Barrows.getRandomCoffin();
		}
		if (Barrows.COFFIN_AND_BROTHERS[c.randomCoffin][0] == coffinId) {
			c.getDH().sendDialogues(1, -1);
			return true;
		}
		return false;
	}

}
