package game.net.packets.clickobject.areas;

import engine.util.Misc;
import game.Config;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.net.packets.DialogueHandler;

public class DuelArena extends DialogueHandler {

	public DuelArena(Player Player) {
		super(Player);
	}

	public static void handleDuelArenaObjects(final Player c,
			final int objectId, final int x, final int y, final int face) {
		c.clickObjectType = 0;
		c.turnPlayerTo(x, y);
		switch (objectId) {
		case 3203: // dueling forfeit
			if (c.duelCount > 0) {
				c.sendMessage("You may not forfeit yet.");
				break;
			}
			Player o = (Player) PlayerHandler.players[c.duelingWith];
			if (o == null) {
				c.getTradeAndDuel().resetDuel();
				c.getPA().movePlayer(
						Config.DUELING_RESPAWN_X
						+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						Config.DUELING_RESPAWN_Y
						+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						0);
				break;
			}
			if (c.duelRule[0]) {
				c.sendMessage("Forfeiting the duel has been disabled!");
				break;
			}
			if (o != null) {
				o.getPA().movePlayer(
						Config.DUELING_RESPAWN_X
						+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						Config.DUELING_RESPAWN_Y
						+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						0);
				c.getPA().movePlayer(
						Config.DUELING_RESPAWN_X
						+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						Config.DUELING_RESPAWN_Y
						+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						0);
				o.duelStatus = 6;
				o.getTradeAndDuel().duelVictory();
				c.getTradeAndDuel().resetDuel();
				c.getTradeAndDuel().resetDuelItems();
				o.sendMessage("The other player has forfeited the duel!");
				c.sendMessage("You forfeit the duel!");
				break;
			}
		case 27854:
			c.getPA().movePlayer(3348, 3280, 0);
			break;
		case 27669:
			c.getPA().movePlayer(3232, 5090, 0);
			break;
		case 27668:
			c.getPA().movePlayer(3366, 3267, 0);
			break;

		}
	}
}