package game.net.packets.clickobject;

import engine.util.Misc;
import game.Config;
import game.entity.player.Player;

public class ThirdClickObject {

	public static void handleClick(Player c, int objectType, int obX, int obY) {
		c.clickObjectType = 0;
		if (Config.SERVER_DEBUG = true) {
			if (c.playerRights >= 4) {
				Misc.println("Object type: " + objectType);
			}
		}
		switch (objectType) {

		/**
		 * Third Click Object Actions
		 */

		}
	}

}
