package game.net.packets.clickobject.areas;

import game.entity.player.Player;

public class RellekkaDungeon {

	public static void handleObjects(final Player p, final int objectId, final int x, final int y, final int face) {
		switch(objectId) {
		case 4499:
			if(p.absX == 2796 && p.absY == 3615) {
				p.getPA().movePlayer(2808, 10002, 0);
			}
			break;
		case 4500:
			if(p.absX == 2808 && p.absY == 10002) {
				p.getPA().movePlayer(2796, 3615, 0);
			}
			break;
		}
	}
}
