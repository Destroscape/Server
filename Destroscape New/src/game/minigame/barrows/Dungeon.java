package game.minigame.barrows;

import engine.util.Misc;
import game.entity.player.Player;

public class Dungeon {

	private static final int[][] dungeonSpawn = { { 3568, 9677 },
			{ 3568, 9712 }, { 3534, 9712 }, { 3534, 9677 } };

	private static final int findX() {
		int x = 0;
		int total = dungeonSpawn.length - 1;
		int length = Misc.random(total);
		x = dungeonSpawn[length][0];
		return x;
	}

	private static final int findY() {
		int y = 0;
		int total = dungeonSpawn.length - 1;
		int length = Misc.random(total);
		y = dungeonSpawn[length][1];
		return y;
	}

	public static void enterDungeon(final Player c) {
		if (System.currentTimeMillis() - c.lastThieve < 2000) {
			return;
		}
		c.lastThieve = System.currentTimeMillis();
		c.getPA().movePlayer(findX(), findY(), 0);
		c.getPA().removeAllWindows();
		c.sendMessage("You have entered the dungeon.");
	}

	public static void openDungeonDoor(final Player c, final int object,
			final int objectX, final int objectY) {
		int x = 0;
		int y = 0;
		switch (object) {
		case 6747:
		case 6728:
			if (c.absY == 9683) {
				x = objectX;
				y = objectY;
			} else if (c.absY == 9684) {
				x = objectX;
				y = objectY - 1;
			} else if (c.absY == 9688) {
				x = objectX;
				y = objectY + 1;
			} else if (c.absY == 9689) {
				x = objectX;
				y = objectY;
			} else {
				return;
			}
			break;
		case 6741:
		case 6722:
			if (c.absY == 9700) {
				x = objectX;
				y = objectY;
			} else if (c.absY == 9701) {
				x = objectX;
				y = objectY - 1;
			} else if (c.absY == 9705) {
				x = objectX;
				y = objectY + 1;
			} else if (c.absY == 9706) {
				x = objectX;
				y = objectY;
			} else {
				return;
			}
			break;
		case 6737:
		case 6718:
			if (c.absY == 9706) {
				x = objectX;
				y = objectY;
			} else if (c.absY == 9705) {
				x = objectX;
				y = objectY + 1;
			} else if (c.absY == 9701) {
				x = objectX;
				y = objectY - 1;
			} else if (c.absY == 9700) {
				x = objectX;
				y = objectY;
			} else {
				return;
			}
			break;
		case 6745:
		case 6726:
			if (c.absY == 9689) {
				x = objectX;
				y = objectY;
			} else if (c.absY == 9688) {
				x = objectX;
				y = objectY + 1;
			} else if (c.absY == 9684) {
				x = objectX;
				y = objectY - 1;
			} else if (c.absY == 9683) {
				x = objectX;
				y = objectY;
			} else {
				return;
			}
			break;
		case 6740:
		case 6721:
			if (c.absX == 3563) {
				x = objectX;
				y = objectY;
			} else if (c.absX == 3562) {
				x = objectX + 1;
				y = objectY;
			} else if (c.absX == 3558) {
				x = objectX - 1;
				y = objectY;
			} else if (c.absX == 3557) {
				x = objectX;
				y = objectY;
			} else {
				return;
			}
			break;
		case 6738:
		case 6719:
			if (c.absX == 3546) {
				x = objectX;
				y = objectY;
			} else if (c.absX == 3545) {
				x = objectX + 1;
				y = objectY;
			} else if (c.absX == 3541) {
				x = objectX - 1;
				y = objectY;
			} else if (c.absX == 3540) {
				x = objectX;
				y = objectY;
			} else {
				return;
			}
			break;
		case 6748:
		case 6729:
			if (c.absX == 3540) {
				x = objectX;
				y = objectY;
			} else if (c.absX == 3541) {
				x = objectX - 1;
				y = objectY;
			} else if (c.absX == 3545) {
				x = objectX + 1;
				y = objectY;
			} else if (c.absX == 3546) {
				x = objectX;
				y = objectY;
			} else {
				return;
			}
			break;
		case 6749:
		case 6730:
			if (c.absX == 3557) {
				x = objectX;
				y = objectY;
			} else if (c.absX == 3558) {
				x = objectX - 1;
				y = objectY;
			} else if (c.absX == 3562) {
				x = objectX + 1;
				y = objectY;
			} else if (c.absX == 3563) {
				x = objectX;
				y = objectY;
			} else {
				return;
			}
			break;
		}
		c.getPA().movePlayer(x, y, 0);
	}

	public static void puzzleDoors(final Player c, final int object,
			final int objectX, final int objectY) {
		int x = 0;
		int y = 0;
		switch (object) {
		case 6746:
		case 6727:
			if (c.absY == 9683) {
				doorPuzzle(c);
				return;
			} else if (c.absY == 9684) {
				x = objectX;
				y = objectY - 1;
			} else if (c.absY == 9688) {
				x = objectX;
				y = objectY + 1;
			} else if (c.absY == 9689) {
				x = objectX;
				y = objectY;
			} else {
				return;
			}
			break;

		case 6739:
		case 6720:
			if (c.absY == 9706) {
				doorPuzzle(c);
				return;
			} else if (c.absY == 9705) {
				x = objectX;
				y = objectY + 1;
			} else if (c.absY == 9701) {
				x = objectX;
				y = objectY - 1;
			} else if (c.absY == 9700) {
				x = objectX;
				y = objectY;
			} else {
				return;
			}
			break;

		case 6724:
		case 6743:
			if (c.absX == 3540) {
				doorPuzzle(c);
				return;
			} else if (c.absX == 3541) {
				x = objectX - 1;
				y = objectY;
			} else if (c.absX == 3545) {
				x = objectX + 1;
				y = objectY;
			} else if (c.absX == 3546) {
				x = objectX;
				y = objectY;
			} else {
				return;
			}
			break;

		case 6744:
		case 6725:
			if (c.absX == 3563) {
				doorPuzzle(c);
				return;
			} else if (c.absX == 3562) {
				x = objectX + 1;
				y = objectY;
			} else if (c.absX == 3558) {
				x = objectX - 1;
				y = objectY;
			} else if (c.absX == 3557) {
				x = objectX;
				y = objectY;
			} else {
				return;
			}
			break;
		}
		c.getPA().movePlayer(x, y, 0);
	}

	public static boolean wrongPuzzle = false;

	private static void doorPuzzle(final Player c) {
		c.randomBarrows = Misc.random(2);
		c.getPA().sendFrame246(4545, 250, 365);
		c.getPA().sendFrame126("1.", 4553);
		c.getPA().sendFrame246(4546, 250, 373);
		c.getPA().sendFrame126("2.", 4554);
		c.getPA().sendFrame246(4547, 250, 379);
		c.getPA().sendFrame126("3.", 4555);
		c.getPA().sendFrame246(4548, 250, 385);
		c.getPA().sendFrame126("4.", 4556);
		if (c.randomBarrows == 1) {
			c.getPA().sendFrame246(4550, 250, 4151);
			c.getPA().sendFrame246(4551, 250, 3244);
			c.getPA().sendFrame246(4552, 250, 391);
		} else if (c.randomBarrows == 0) {
			c.getPA().sendFrame246(4550, 250, 3244);
			c.getPA().sendFrame246(4551, 250, 391);
			c.getPA().sendFrame246(4552, 250, 4151);
		} else {
			c.getPA().sendFrame246(4550, 250, 391);
			c.getPA().sendFrame246(4551, 250, 4151);
			c.getPA().sendFrame246(4552, 250, 3244);
		}
		c.getPA().showInterface(4543);
	}

}
