package game.item;

/*
 * Project Insanity - Evolved v.3
 * GroundItem.java
 */

public class GroundItem {

	public int itemId;
	public int itemX;
	public int itemY;
	public int itemAmount;
	public int itemController;
	public int hideTicks;
	public int removeTicks;
	public int heightLevel;
	public String ownerName;

	public GroundItem(int id, int x, int y, int height, int amount,
			int controller, int hideTicks, String name) {
		this.itemId = id;
		this.itemX = x;
		this.itemY = y;
		this.heightLevel = height;
		this.itemAmount = amount;
		this.itemController = controller;
		this.hideTicks = hideTicks;
		this.ownerName = name;
	}

	public GroundItem(int id, int x, int y, int amount, int controller, int hideTicks, String name) {
		this.itemId = id;
		this.itemX = x;
		this.itemY = y;
		this.itemAmount = amount;
		this.itemController = controller;
		this.hideTicks = hideTicks;
		this.ownerName = name;
	}

	public int getItemId() {
		return this.itemId;
	}

	public int getItemX() {
		return this.itemX;
	}

	public int getItemY() {
		return this.itemY;
	}

	public int getHeightLevel() {
		return this.heightLevel;
	}

	public int getItemAmount() {
		return this.itemAmount;
	}

	public int getItemController() {
		return this.itemController;
	}

	public String getName() {
		return this.ownerName;
	}
}