package game.entity.player;

import game.net.packets.clicknpc.FirstClickNpc;
import game.net.packets.clicknpc.SecondClickNpc;
import game.net.packets.clicknpc.ThirdClickNpc;
import game.net.packets.clickobject.FirstClickObject;
import game.net.packets.clickobject.SecondClickObject;
import game.net.packets.clickobject.ThirdClickObject;

public class ActionHandler {

	private final Player c;

	public ActionHandler(final Player Client) {
		c = Client;
	}

	public void firstClickNpc(final int i) {
		FirstClickNpc.handleClick(c, i);
	}

	public void firstClickObject(final int objectType, final int obX,
			final int obY) {
		FirstClickObject.handleClick(c, objectType, obX, obY);
	}

	public void secondClickNpc(final int npcId) {
		SecondClickNpc.handleClick(c, npcId);
	}

	public void secondClickObject(final int objectType, final int obX,
			final int obY) {
		SecondClickObject.handleClick(c, objectType, obX, obY);
	}

	public void thirdClickNpc(final int npcId) {
		ThirdClickNpc.handleClick(c, npcId);
	}

	public void thirdClickObject(final int objectType, final int obX,
			final int obY) {
		ThirdClickObject.handleClick(c, objectType, obX, obY);
	}

}
