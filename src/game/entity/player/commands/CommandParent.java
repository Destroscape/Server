package game.entity.player.commands;

import game.entity.player.Player;

public interface CommandParent {

	public void handleCommand(final Player c, final String cmd);
	
}