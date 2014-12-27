package game.content.travel;

import engine.event.Event;
import engine.event.EventContainer;
import engine.event.EventManager;
import engine.util.Misc;
import game.Config;
import game.entity.player.Player;
import game.skill.prayer.Constants;

public class TeleTabs {

	private static enum TeleTab {

		VARROCK(8007, 3213, 3424), 
		LUMBRIDGE(8008, 3222, 3218), 
		FALADOR(8009, 2965, 3379),
		CAMELOT(8010, 2757, 3477), 
		ARDOUGNE(8011, 2661, 3305), 
		WATCHTOWER(8012, 2549, 3112),
		HOME(8013, Config.HOME_X, Config.HOME_Y);;

		private int tab, x, y;

		private TeleTab(final int tab, final int x, final int y) {
			this.tab = tab;
			this.x = x;
			this.y = y;
		}

		private int getTab() {
			return tab;
		}

		private int getX() {
			return x;
		}

		private int getY() {
			return y;
		}

		private final String getLocation() {
			return Misc.optimizeText(toString().toLowerCase());
		}

		private static TeleTab getTabID(final int ID) {
			for (TeleTab t : TeleTab.values()) {
				if (t.getTab() == ID) {
					return t;
				}
			}
			return null;
		}
	}

	/**
	 * Handles the teleporting
	 * 
	 * @param c
	 *            Client
	 * @param item
	 *            Tab
	 */

	public static void useTeleportingTab(final Player c, final int item) {
		final TeleTab t = TeleTab.getTabID(item);
		if (t == null || item != t.getTab()) {
			return;
		}
		if ((System.currentTimeMillis() - c.tabTimer) > 1500) {
			c.getItems().deleteItem2(t.getTab(), 1);
			c.sendMessage("You break the teleport tab and go to <col=255>"
					+ t.getLocation() + ".");
			c.startAnimation(4731);
			c.gfx0(678);
			c.tabTimer = System.currentTimeMillis();
			EventManager.getSingleton().addEvent(new Event() {
				@Override
				public void execute(EventContainer e) {
					c.getPA().movePlayer(t.getX(), t.getY(), 0);
					c.startAnimation(13654);
					e.stop();
				}
			}, 1350);
		}
	}

	/**
	 * Bones to Peaches/Bananas
	 */

	private static enum BoneTabs {
		BANANAS(8014, 1963), PEACHES(8015, 6883);

		private int tab, item;

		private BoneTabs(final int tab, final int item) {
			this.tab = tab;
			this.item = item;
		}

		private int getTab() {
			return tab;
		}

		private int getItem() {
			return item;
		}

		private final String getName() {
			return Misc.optimizeText(toString().toLowerCase());
		}

		private static BoneTabs getTabID(final int ID) {
			for (BoneTabs t : BoneTabs.values()) {
				if (t.getTab() == ID) {
					return t;
				}
			}
			return null;
		}
	}

	public static void useBoneTab(final Player c, final int item) {
		final BoneTabs b = BoneTabs.getTabID(item);
		int amount = -1;
		if (b == null || !c.getItems().playerHasItem(b.getTab())) {
			return;
		}
		if ((System.currentTimeMillis() - c.tabTimer) > 1500) {
			c.tabTimer = System.currentTimeMillis();
			for (int i = 0; i < Constants.BONES.length; i++) {
				if (c.getItems().playerHasItem(Constants.BONES[i][0])) {
					amount = c.getItems().getItemAmount(Constants.BONES[i][0]);
					c.getItems().deleteItem2(b.getTab(), 1);
					c.getItems().deleteItem2(Constants.BONES[i][0], amount);
					c.sendMessage("You break the teleport tab and your bones turn to "
							+ b.getName() + ".");
					c.getItems().addItem(b.getItem(), amount);
				}
			}
			if (amount <= 0) {
				c.sendMessage("You must have bones in your inventory to use this tab!");
				return;
			}
		}
	}

}