package game.skill.crafting;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.entity.player.Player;

public class JewelryMaking extends CraftingData {

	public static void jewelryMaking(final Player c, final String type, final int itemId, final int amount) {
		switch (type) {
		case "RING":
			for (int i = 0; i < jewelryData.RINGS.item.length; i++) {
				if (itemId == jewelryData.RINGS.item[i][1]) {
					mouldJewelry(c, jewelryData.RINGS.item[i][0], itemId, amount, jewelryData.RINGS.item[i][2], jewelryData.RINGS.item[i][3]);
				}
			}
			break;
		case "NECKLACE":
			for (int i = 0; i < jewelryData.NECKLACE.item.length; i++) {
				if (itemId == jewelryData.NECKLACE.item[i][1]) {
					mouldJewelry(c, jewelryData.NECKLACE.item[i][0], itemId, amount, jewelryData.NECKLACE.item[i][2], jewelryData.NECKLACE.item[i][3]);
				}
			}
			break;
		case "AMULET":
			for (int i = 0; i < jewelryData.AMULETS.item.length; i++) {
				if (itemId == jewelryData.AMULETS.item[i][1]) {
					mouldJewelry(c, jewelryData.AMULETS.item[i][0], itemId, amount, jewelryData.AMULETS.item[i][2], jewelryData.AMULETS.item[i][3]);
				}
			}
			break;
		}
	}
	private static int time;

	@SuppressWarnings("unused")
	private static void mouldJewelry(final Player c, final int required,
			final int itemId, final int amount, final int level, final int xp) {
		if (c.isSkilling[12] == true) {
			return;
		}
		if (c.playerLevel[12] < level) {
			c.sendMessage("You need a crafting level of " + level
					+ " to mould this item.");
			return;
		}
		if (!c.getItems().playerHasItem(2357)) {
			c.sendMessage("You need a gold bar to mould this item.");
			return;
		}
		final String itemRequired = c.getItems().getItemName(required);
		if (!c.getItems().playerHasItem(required)) {
			c.sendMessage("You need "
					+ ((itemRequired.startsWith("A")
							|| itemRequired.startsWith("E") || itemRequired
								.startsWith("O")) ? "an" : "a") + " "
					+ itemRequired.toLowerCase() + " to mould this item.");
			return;
		}
		time = amount;
		c.getPA().removeAllWindows();
		final String itemName = c.getItems().getItemName(itemId);
		//c.setAnimation(Animation.create(899));
		c.startAnimation(899);
		c.isSkilling[12] = true;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.isSkilling[12] == true) {
					time--;
					if (time == 0) {
						container.stop();
					}
					if (c.getItems().playerHasItem(2357)
							&& c.getItems().playerHasItem(required)) {
						c.getItems().deleteItem(2357, 1);
						if (required != 2357) {
							c.getItems().deleteItem(required, 1);
						}
						c.getItems().addItem(itemId, 1);
						//c.setAnimation(Animation.create(899));
						c.startAnimation(899);
						c.getPA().addSkillXP(xp * 20, 12);
						c.sendMessage("You make "
								+ ((itemName.startsWith("A")
										|| itemName.startsWith("E") || itemName
											.startsWith("O")) ? "an" : "a")
								+ " " + itemName.toLowerCase());
					} else {
						container.stop();
					}
				} else {
					container.stop();
				}
			}

			@Override
			public void stop() {
				c.isSkilling[12] = false;
			}
		}, 4);
	}

	public static void stringAmulet(final Player c, final int itemUsed,
			final int usedWith) {
		final int amuletId = (itemUsed == 1759 ? usedWith : itemUsed);
		for (final amuletData a : amuletData.values()) {
			if (amuletId == a.getAmuletId()) {
				c.getItems().deleteItem(1759, 1);
				c.getItems().deleteItem(amuletId, 1);
				c.getItems().addItem(a.getProduct(), 1);
				c.getPA().addSkillXP(4 * 20, 12);
			}
		}
	}

	private final static int[][] MOULD_INTERFACE_IDS = {
	/* Rings */
	{ 1635, 1637, 1639, 1641, 1643, 1645, 6583 },
	/* Neclece */
	{ 1654, 1656, 1658, 1660, 1662, 1664, 11128 },
	/* amulet */
	{ 1673, 1675, 1677, 1679, 1681, 1683, 6581 }

	};

	public static void jewelryInterface(final Player c) {
		c.getPA().showInterface(4161);
		/* Rings */
		if (c.getItems().playerHasItem(1592, 1)) {
			for (int i = 0; i < MOULD_INTERFACE_IDS[0].length; i++) {
				c.getPA().sendFrame34(MOULD_INTERFACE_IDS[0][i], i, 4233, 1);
			}
			c.getPA().sendFrame34(1643, 4, 4233, 1);
			c.getPA().sendFrame126("", 4230);
			c.getPA().sendFrame246(4229, 0, -1);
		} else {
			c.getPA().sendFrame246(4229, 120, 1592);
			for (int i = 0; i < MOULD_INTERFACE_IDS[0].length; i++) {
				c.getPA().sendFrame34(-1, i, 4233, 1);
			}
			c.getPA().sendFrame126("You need a ring mould to craft rings.",
					4230);
		}
		/* Necklace */
		if (c.getItems().playerHasItem(1597, 1)) {
			for (int i = 0; i < MOULD_INTERFACE_IDS[1].length; i++) {
				c.getPA().sendFrame34(MOULD_INTERFACE_IDS[1][i], i, 4239, 1);
			}
			c.getPA().sendFrame34(1662, 4, 4239, 1);
			c.getPA().sendFrame246(4235, 0, -1);
			c.getPA().sendFrame126("", 4236);
		} else {
			c.getPA().sendFrame246(4235, 120, 1597);
			c.getPA().sendFrame126(
					"You need a necklace mould to craft necklaces", 4236);
			for (int i = 0; i < MOULD_INTERFACE_IDS[1].length; i++) {
				c.getPA().sendFrame34(-1, i, 4239, 1);
			}
		}
		/* Amulets */
		if (c.getItems().playerHasItem(1595, 1)) {
			for (int i = 0; i < MOULD_INTERFACE_IDS[2].length; i++) {
				c.getPA().sendFrame34(MOULD_INTERFACE_IDS[2][i], i, 4245, 1);
			}
			c.getPA().sendFrame34(1681, 4, 4245, 1);
			c.getPA().sendFrame246(4241, 0, -1);
			c.getPA().sendFrame126("", 4242);
		} else {
			c.getPA().sendFrame246(4241, 120, 1595);
			c.getPA().sendFrame126(
					"You need a amulet mould to craft necklaces", 4242);
			for (int i = 0; i < MOULD_INTERFACE_IDS[2].length; i++) {
				c.getPA().sendFrame34(-1, i, 4245, 1);
			}
		}
	}
}