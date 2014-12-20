package game.skill.summoning;

import game.entity.player.Player;

public class BoB {

	private Player c;

	public BoB(Player c) {
		this.c = c;
	}

	public void store() {
		for (int k = 0; k < 30; k++) {
			if (c.bobItems[k] > 0) {
				c.getPA().Frame34(2702, c.bobItems[k], k, c.bobItemsN[k]);
			}
			if (c.bobItems[k] <= 0) {
				c.getPA().Frame34(2702, -1, k, c.bobItemsN[k]);
			}
		}
		c.isBanking = false;
		c.usingBoB = true;
		c.getItems().resetItems(5064);
		c.getItems().resetTempItems();
		c.getOutStream().createFrame(248);
		c.getOutStream().writeWordA(2700);
		c.getOutStream().writeWord(5063);
		c.getPA().sendFrame87(286, 0);
		c.flushOutStream();
		c.ResetKeepItems();
	}

}