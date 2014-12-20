package game.item;

/*
 * Project Insanity - Evolved v.3
 * GameItem.java
 */

public class GameItem {

	public int id, amount;
	public boolean stackable = false;

	public GameItem(final int id, final int amount) {
		if (Item.itemStackable[id]) {
			stackable = true;
		}
		this.id = id;
		this.amount = amount;
	}

}
