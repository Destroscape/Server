package game.content;

import engine.util.Misc;
import game.Config;
import game.entity.player.Player;
import game.item.ItemAssistant;

public class PriceChecker {

	private static int getFramesForSlot[][] = { { 0, 18353 }, { 1, 18356 },
			{ 2, 18359 }, { 3, 18362 }, { 4, 18365 }, { 5, 18368 },
			{ 6, 18371 }, { 7, 18374 }, { 8, 18377 }, { 9, 18380 },
			{ 10, 18383 }, { 11, 18386 }, { 12, 18389 }, { 13, 18392 },
			{ 14, 18395 }, { 15, 18398 }, { 16, 18401 }, { 17, 18404 },
			{ 18, 18407 }, { 19, 18410 }, };

	public static int arraySlot(Player c, int[] array, int target) {
		int spare = -1;
		for (int x = 0; x < array.length; x++) {
			c.getItems();
			if (array[x] == target && ItemAssistant.isStackable(target)) {
				return x;
			} else if (spare == -1 && array[x] <= 0) {
				spare = x;
			}
		}
		return spare;
	}

	public static void clearConfig(Player c) {
		for (int x = 0; x < c.price.length; x++) {
			if (c.price[x] > 0)
				withdrawItem(c, c.price[x], x, c.priceN[x]);
		}
		c.getItems().updateInventory = true;
		c.getItems().resetItems(5064);
	}

	public static void depositItem(Player c, int id, int amount) {
		int slot = arraySlot(c, c.price, id);
		for (int j = 0; j < Config.ITEM_TRADEABLE.length; j++) {
			if (id == Config.ITEM_TRADEABLE[j]) {
				c.sendMessage("This item is untrade-able.");
				return;
			}
		}
		if (c.getItems().getItemAmount(id) < amount) {
			amount = c.getItems().getItemAmount(id);
		}
		if (slot == -1) {
			c.sendMessage("The price-checker is currently full.");
			return;
		}
		c.getItems();
		if (!ItemAssistant.isStackable(id)) {
			amount = 1;
		}
		if (!c.getItems().playerHasItem(id, amount)) {
			return;
		}
		c.getItems().deleteItem2(id, amount);
		if (c.price[slot] != id) {
			c.price[slot] = id;
			c.priceN[slot] = amount;
		} else {
			c.price[slot] = id;
			c.priceN[slot] += amount;
		}
		c.total += c.getShops().getItemShopValue(id) * amount;
		updateChecker(c);
	}

	public static void itemOnInterface(Player c, int frame, int slot, int id,
			int amount) {
		c.outStream.createFrameVarSizeWord(34);
		c.outStream.writeWord(frame);
		c.outStream.writeByte(slot);
		c.outStream.writeWord(id + 1);
		c.outStream.writeByte(255);
		c.outStream.writeDWord(amount);
		c.outStream.endFrameVarSizeWord();
	}

	public static void open(Player c) {
		c.isChecking = true;
		c.total = 0;
		c.getPA().sendFrame126(
				"" + Misc.insertCommasToNumber(Integer.toString(c.total)) + "",
				18351);
		c.getPA()
				.sendFrame126(
						"Click on items in your inventory to check their values",
						18352);
		updateChecker(c);
		resetFrames(c);
		c.getItems().resetItems(5064);
		c.getPA().sendFrame248(43933, 5063);
	}

	public static void resetFrames(Player c) {
		for (int x = 0; x < 20; x++) {
			if (c.price[x] <= 1) {
				setFrame(c, x, getFramesForSlot[x][1], c.price[x], c.priceN[x],
						false);
			}
		}
	}

	private static void setFrame(Player player, int slotId, int frameId,
			int itemId, int amount, boolean store) {
		int totalAmount = player.getShops().getItemShopValue(itemId) * amount;
		String total = Misc.insertCommasToNumber(Integer.toString(totalAmount));
		if (!store) {
			player.getPA().sendFrame126("", frameId);
			player.getPA().sendFrame126("", frameId + 1);
			player.getPA().sendFrame126("", frameId + 2);
			return;
		}
		player.getItems();
		if (ItemAssistant.isStackable(itemId)) {
			player.getPA().sendFrame126("" + amount + " x", frameId);
			player.getPA().sendFrame126(
					""
							+ Misc.insertCommasToNumber(Integer.toString(player
									.getShops().getItemShopValue(itemId)))
							+ " =", frameId + 1);
			player.getPA().sendFrame126("" + total + "", frameId + 2);
		} else {
			player.getPA()
					.sendFrame126(
							""
									+ Misc.insertCommasToNumber(Integer
											.toString(player.getShops()
													.getItemShopValue(itemId)))
									+ "", frameId);
			player.getPA().sendFrame126("", frameId + 1);
			player.getPA().sendFrame126("", frameId + 2);
		}
	}

	public static void updateChecker(Player c) {
		c.getItems().resetItems(5064);
		for (int x = 0; x < 20; x++) {
			if (c.priceN[x] <= 0) {
				itemOnInterface(c, 18246, x, -1, 0);
			} else {
				itemOnInterface(c, 18246, x, c.price[x], c.priceN[x]);
				c.getPA().sendFrame126("", 18352);
				for (int frames = 0; frames < getFramesForSlot.length; frames++) {
					if (x == getFramesForSlot[frames][0]) {
						setFrame(c, x, getFramesForSlot[frames][1], c.price[x],
								c.priceN[x], true);
					}
				}
			}
		}
		c.getPA().sendFrame126(
				""
						+ Misc.insertCommasToNumber(Integer
								.toString(c.total < 0 ? 0 : c.total)) + "",
				18351);
	}

	public static void withdrawItem(Player c, int removeId, int slot, int amount) {
		if (!c.isChecking)
			return;
		if (c.price[slot] != removeId) {
			return;
		}
		c.getItems();
		if (!ItemAssistant.isStackable(c.price[slot]))
			amount = 1;
		c.getItems();
		if (amount > c.priceN[slot] && ItemAssistant.isStackable(c.price[slot]))
			amount = c.priceN[slot];
		if (c.price[slot] >= 0 && c.getItems().freeSlots() > 0) {
			c.getItems().addItem(c.price[slot], amount);
			c.getItems();
			if (ItemAssistant.isStackable(c.price[slot])
					&& c.getItems().playerHasItem(c.price[slot], amount)) {
				c.priceN[slot] -= amount;
				c.price[slot] = c.priceN[slot] <= 0 ? 0 : c.price[slot];
			} else {
				c.priceN[slot] = 0;
				c.price[slot] = 0;
			}
		}
		c.total -= c.getShops().getItemShopValue(removeId) * amount;
		for (int frames = 0; frames < getFramesForSlot.length; frames++) {
			if (slot == getFramesForSlot[frames][0]) {
				if (c.priceN[slot] >= 1) {
					setFrame(c, slot, getFramesForSlot[frames][1],
							c.price[slot], c.priceN[slot], true);
				} else {
					setFrame(c, slot, getFramesForSlot[frames][1],
							c.price[slot], c.priceN[slot], false);
				}
			}
		}
		updateChecker(c);
	}

}