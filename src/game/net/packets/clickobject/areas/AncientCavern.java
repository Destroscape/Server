package game.net.packets.clickobject.areas;

import game.entity.player.Player;

public class AncientCavern {

	public static void handleObjects(final Player p, final int objectId, final int x, final int y, final int face) {
		switch(objectId) {
		
		case 25336:
			if(p.absX == 1772 && p.absY == 5366 && p.heightLevel == 0) {
				p.getPA().movePlayer(1768, 5366, 1);
			}
			break;
			
		case 25338:
			if(p.absX == 1768 && p.absY == 5366 && p.heightLevel == 1) {
				p.getPA().movePlayer(1772, 5366, 0);
			}
			break;
			
		case 25339:
			if(p.absX == 1778 && p.absY == 5346 && p.heightLevel == 0) {
				p.getPA().movePlayer(1778, 5346, 1);
			}
			break;
			
		case 25340:
			if(p.absX == 1778 && p.absY == 5343 && p.heightLevel == 1) {
				p.getPA().movePlayer(1768, 5346, 0);
			}
			break;
		}
	}
}
