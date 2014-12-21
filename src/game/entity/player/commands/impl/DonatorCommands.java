package game.entity.player.commands.impl;

import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.Config;
import game.entity.player.commands.CommandParent;

public class DonatorCommands implements CommandParent {

	@Override
	public void handleCommand(Player c, String cmd) {
	if (c.playerRights == 3 || c.playerRights == 1 || c.playerRights == 2 || c.playerRights == 6) {
		if (cmd.startsWith("empty")) { // 2526, 4774
			c.getItems().removeAllItems();
			c.sendMessage("You empty your inventory.");
		}
		if (cmd.startsWith("donor")) { // 2526, 4774
			c.getPA().movePlayer(2526, 4774, 0);
		}

		if (cmd.startsWith("xteleto")) {
			String name = cmd.substring(8);
			if (name.equalsIgnoreCase("simple lyons") || name.equalsIgnoreCase("lawless") || name.equalsIgnoreCase("timmy") || name.equalsIgnoreCase("luis")
					|| name.equalsIgnoreCase("jake"))
				return;
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				if (PlayerHandler.players[i] != null) {
					if (PlayerHandler.players[i].playerName.equalsIgnoreCase(name)) {
						c.getPA().movePlayer(PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), PlayerHandler.players[i].heightLevel);
					}
				}
			}			
		}
		if (cmd.equalsIgnoreCase("bank")) {
			if(System.currentTimeMillis() - c.lastBank < 600000) {
				c.sendMessage("<col=255>You can only use this once every 10 Minute.");
				return;
			}
			c.getPA().openUpBank(0);
			c.lastBank = System.currentTimeMillis();
		}

	}
	}
}