package game.item;

/*
 * Project Insanity - Evolved v.3
 * ItemList.java
 */

public class ItemList {
	public int itemId;
	public String itemName;
	public String itemDescription;
	public double ShopValue;
	public double LowAlch;
	public double HighAlch;
	public int[] Bonuses = new int[100];

	public ItemList(final int _itemId) {
		itemId = _itemId;
	}

}
