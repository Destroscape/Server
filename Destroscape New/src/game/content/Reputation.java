package game.content;

import engine.util.Misc;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;

public class Reputation {

	/**
	 * ? Can use reputation system.
	 */
	public final static boolean REP_SYSTEM_ON = true;

	/**
	 * Base reputation given upon creating an account.
	 */
	public final static int BASE_REP = 10;

	/**
	 * Reputation divider
	 */
	public final static int REP_DIVIDER = 2;

	/**
	 * Give Positive Reputation
	 */
	public static void givePositiveReputation(Player c, int playerIndex) {
		int repPower = c.reputationPoints / REP_DIVIDER;
		c.playerNames.trimToSize();
		try {
			if (playerIndex == c.getIndex() || !REP_SYSTEM_ON) {
				return;
			}
			Player o = (Player) PlayerHandler.players[playerIndex];
			if (o == null) {
				return;
			}
			if (c.playerNames.contains(o.playerName)) {
				c.sendMessage("You just passed reputation on this player, Spread reputation around a little.");
				return;
			}
			if(c.lastRep == 1) {
				resetReputationBlock(c);
			}
			c.playerNames.add(o.playerName);
			c.lastRep = 1;
			c.sendMessage("You have just gave " + o.playerName
					+ " positive reputation.");
			o.reputationPoints = +repPower;
			o.sendMessage("You have just recieved " + repPower
					+ " reputation points from " + c.playerName + ".");
		} catch (Exception e) {
			Misc.println("Error Processing Reputation @" + c.playerName);
		}
	}

	/**
	 * Give Negative Reputation
	 */
	public static void giveNegativeReputation(Player c, int playerIndex) {
		int repPower = c.reputationPoints / REP_DIVIDER;
		c.playerNames.trimToSize();
		try {
			if (playerIndex == c.getIndex() || !REP_SYSTEM_ON) {
				return;
			}
			Player o = (Player) PlayerHandler.players[playerIndex];
			if (o == null) {
				return;
			}
			if (c.playerNames.contains(o.playerName)) {
				c.sendMessage("You just passed reputation on this player, Spread reputation around a little.");
				return;
			}
			if(c.lastRep == 1) {
				resetReputationBlock(c);
			}
			c.playerNames.add(o.playerName);
			c.lastRep = 1;
			c.sendMessage("You have just gave " + o.playerName
					+ " negative reputation.");
			o.reputationPoints = -repPower;
			o.sendMessage("You have just recieved " + repPower
					+ " reputation points from " + c.playerName + ".");
		} catch (Exception e) {
			Misc.println("Error Processing Reputation @" + c.playerName);
		}
	}

	/**
	 * Clears the array list of the person at the front of the list
	 * 
	 * @param p
	 */
	public static void resetReputationBlock(Player p) {
		p.playerNames.remove(0);
	}
}