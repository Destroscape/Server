package server.model.minigames;

import server.event.Event;
import server.event.EventContainer;
import server.event.EventManager;
import server.model.players.Client;
import server.util.Misc;

/*
 * @author Liberty / Robbie
 */

public class CrystalChest {

	/*
	 * Array to store the chest rewards
	 */
	private static final int[] CHEST_REWARDS = { 989, 985, 987, 7509, 4716, 4718, 4720, 4722, 4745, 4747, 4749, 4751, 4732, 4734, 4736, 4738, 229, 526, 1917, 6585, 4724, 4726, 4728, 4730, 4708, 4710, 4712, 4714, 11283, 4753, 4755, 4757, 4759, 7806, 7807, 7808, 7809 };

	/*
	 * Array to store the parts of the key
	 */
	public static final int[] KEY_HALVES = { 985, 987 };

	/*
	 * Integer to identify the key
	 */
	public static final int KEY = 989;

	/*
	 * Integar to identify the Dragonstone
	 */
	private static final int DRAGONSTONE = 1631;

	/*
	 * Integar to define the opening animation
	 */
	private static final int OPEN_ANIMATION = 881;

	/*
	 * A method to replace the key halves with the key
	 */
	public static void makeKey(Client c) {
		if (c.getItems().playerHasItem(toothHalf(), 1)
				&& c.getItems().playerHasItem(loopHalf(), 1)) {
			c.getItems().deleteItem(toothHalf(), 1);
			c.getItems().deleteItem(loopHalf(), 1);
			c.getItems().addItem(KEY, 1);
		}
	}

	/*
	 * A boolean to check if you can open the chest
	 */
	public static boolean canOpen(Client c) {
		if (c.getItems().playerHasItem(KEY)) {
			return true;
		} else {
			c.sendMessage("The chest is locked");
			return false;
		}
	}

	/*
	 * A event that resets the object and give the reward after 3 cycles
	 */
	public static void searchChest(final Client c, final int id, final int x,
			final int y) {
		if (canOpen(c)) {
			c.sendMessage("You unlock the chest with your key.");
			c.getItems().deleteItem(KEY, 1);
			c.startAnimation(OPEN_ANIMATION);
			c.getPA().checkObjectSpawn(id + 1, x, y, 3, 10);
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer e) {
					c.getItems().addItem(DRAGONSTONE, 1);
					c.getItems().addItem(995, Misc.random(8230));
					c.getItems().addItem(
							CHEST_REWARDS[Misc.random(getLength() - 1)], 1);
					c.sendMessage("You find some treasure in the chest.");
					c.getPA().checkObjectSpawn(id, x, y, 3, 10);
					e.stop();
				}
			}, 1800);
		}
	}

	/*
	 * Identifiers
	 */

	public static int getLength() {
		return CHEST_REWARDS.length;
	}

	public static int toothHalf() {
		return KEY_HALVES[0];
	}

	public static int loopHalf() {
		return KEY_HALVES[1];
	}
}
