package engine.util;

import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.entity.player.PlayerSave;

public class ShutDownHook extends Thread {

	@Override
	public void run() {
		System.out.println("Shutdown thread run.");
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Player c = PlayerHandler.players[j];
				PlayerSave.saveGame(c);
			}
		}
		System.out.println("Shutting down...");
	}

}