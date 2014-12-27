package game.content;

import game.entity.player.Player;

public class CompletionistCape {

	public static int BLACK = 14001;
	public static int GRAY = 14002;
	public static int WHITE = 14003;
	public static int BLUE = 14004;
	public static int GREEN = 14005;
	public static int CYAN = 14006;
	public static int RED = 14007;
	public static int PURPLE = 14008;
	public static int YELLOW = 14009;
	public static int ORANGE = 14010;

	public static boolean canComplete(Player c) {
		for(int i = 0; i < 23; i++) {
			if (c.getPA().getLevelForXP(c.playerXP[i]) != 99) {
				return false;
			}
			if (c.getPA().getLevelForXP(c.playerXP[23]) != 120) {
				return false;
			}
			if (c.totalNPCKO < 6000) {
				return false;
			}
			if (c.completeAchievements < 28) {
				return false;
			}
		}
		return true;
	}

	public static void giveCompletionist(Player c) {
		if (canComplete(c)) {
			if (c.getItems().playerHasItem(995, 10000000)) {
				c.getItems().addItem(14001, 1);
				c.getItems().addItem(20770, 1);
				c.getItems().deleteItem2(995, 10000000);
			} else {
				c.sendMessage("You need <col=255>10M</col> Coins to obtain the <col=255>Completionist Cape</col>.");
				return;
			}
		} else {
			c.getDH().sendDialogues(141, 0);
			c.sendMessage("Come back when you match the requirements!");
		}
	}

	public static boolean hasCape(Player c) {
		if (c.getItems().playerHasItem(BLACK)
				|| c.getItems().playerHasItem(GRAY)
				|| c.getItems().playerHasItem(WHITE)
				|| c.getItems().playerHasItem(BLUE)
				|| c.getItems().playerHasItem(GREEN)
				|| c.getItems().playerHasItem(CYAN)
				|| c.getItems().playerHasItem(RED)
				|| c.getItems().playerHasItem(PURPLE)
				|| c.getItems().playerHasItem(YELLOW)
				|| c.getItems().playerHasItem(ORANGE)) {
			return true;
		}
		return false;
	}

	public static void changeColor(Player c, int color) {
		if (hasCape(c)) {
			deleteCapes(c);
			c.getItems().addItem(color, 1);
			c.getPA().closeAllWindows();
			c.sendMessage("You have changed the color of your completionist cape!");
		} else {
			c.sendMessage("You need to have a completionist cape in your inventory to do this.");
		}
	}

	public static void deleteCapes(Player c) {
		if (c.getItems().playerHasItem(BLACK)) {
			c.getItems().deleteItem(BLACK, 1);
		}
		if (c.getItems().playerHasItem(GRAY)) {
			c.getItems().deleteItem(GRAY, 1);
		}
		if (c.getItems().playerHasItem(WHITE)) {
			c.getItems().deleteItem(WHITE, 1);
		}
		if (c.getItems().playerHasItem(BLUE)) {
			c.getItems().deleteItem(BLUE, 1);
		}
		if (c.getItems().playerHasItem(GREEN)) {
			c.getItems().deleteItem(GREEN, 1);
		}
		if (c.getItems().playerHasItem(CYAN)) {
			c.getItems().deleteItem(CYAN, 1);
		}
		if (c.getItems().playerHasItem(RED)) {
			c.getItems().deleteItem(RED, 1);
		}
		if (c.getItems().playerHasItem(PURPLE)) {
			c.getItems().deleteItem(PURPLE, 1);
		}
		if (c.getItems().playerHasItem(YELLOW)) {
			c.getItems().deleteItem(YELLOW, 1);
		}
		if (c.getItems().playerHasItem(ORANGE)) {
			c.getItems().deleteItem(ORANGE, 1);
		}
	}

}
