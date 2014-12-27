package game.item;

import engine.util.Misc;
import game.Config;
import game.Server;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Project Insanity - Evolved v.3
 * ItemHandler.java
 */

public class ItemHandler {

	public List<GroundItem> items = new ArrayList<GroundItem>();
	public static final int HIDE_TICKS = 100;

	/**
	 * Creates the ground item
	 **/

	public void createGroundItem(Player c, int itemId, int itemX, int itemY,
			int heightLevel, int itemAmount, int playerId) {
		if (itemId > 0) {
			if (itemId >= 2412 && itemId <= 2414 || c.playerRights == 3) {
				c.sendMessage("The item vanishes as it touches the ground.");
				return;
			}
			if (itemId == 10033 || itemId == 10034) {
				return;
			}
			if (itemId > 4705 && itemId < 4760) {
				for (int[] brokenBarrow : brokenBarrows) {
					if (brokenBarrow[0] == itemId) {
						itemId = brokenBarrow[1];
						break;
					}
				}
			}
			if (!Item.itemStackable[itemId] && itemAmount > 0) {
				for (int j = 0; j < itemAmount; j++) {
					c.getItems().createGroundItem(itemId, itemX, itemY, 1);
					GroundItem item = new GroundItem(itemId, itemX, itemY,
							heightLevel, 1, c.playerId, HIDE_TICKS,
							PlayerHandler.players[playerId].playerName);
					addItem(item);
				}
			} else {
				c.getItems().createGroundItem(itemId, itemX, itemY, itemAmount);
				GroundItem item = new GroundItem(itemId, itemX, itemY,
						heightLevel, itemAmount, c.playerId, HIDE_TICKS,
						PlayerHandler.players[playerId].playerName);
				addItem(item);
			}
		}
	}

	public int[][] brokenBarrows = { { 4708, 4860 }, { 4710, 4866 },
			{ 4712, 4872 }, { 4714, 4878 }, { 4716, 4884 }, { 4720, 4896 },
			{ 4718, 4890 }, { 4720, 4896 }, { 4722, 4902 }, { 4732, 4932 },
			{ 4734, 4938 }, { 4736, 4944 }, { 4738, 4950 }, { 4724, 4908 },
			{ 4726, 4914 }, { 4728, 4920 }, { 4730, 4926 }, { 4745, 4956 },
			{ 4747, 4926 }, { 4749, 4968 }, { 4751, 4994 }, { 4753, 4980 },
			{ 4755, 4986 }, { 4757, 4992 }, { 4759, 4998 } };

	/**
	 * Item List
	 **/
	public ItemList ItemList[] = new ItemList[Config.ITEM_LIMIT];

	public ItemHandler() {
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			ItemList[i] = null;
		}
		loadItemList("Item Config.cfg");
		loadItemPrices("Item Prices.txt");
	}

	/**
	 * Adds item to list
	 **/
	public void addItem(final GroundItem item) {
		items.add(item);
	}

	/**
	 * Shows items for everyone who is within 60 squares
	 **/
	public void createGlobalItem(final GroundItem i) {
		for (final Player p : PlayerHandler.players) {
			if (p != null) {
				final Player person = p;
				if (person != null && !p.inFightCaves() && !p.inDung()) {
					if (person.playerId != i.getItemController()) {
						person.getItems();
						if (!ItemAssistant.tradeable(i.getItemId())
								&& person.playerId != i.getItemController()) {
							continue;
						}
						if (person.distanceToPoint(i.getItemX(), i.getItemY()) <= 60) {
							person.getItems().createGroundItem(i.getItemId(),
									i.getItemX(), i.getItemY(),
									i.getItemAmount());
						}
					}
				}
			}
		}
	}

	/**
	 * Handles global item spawns - Karma
	 */

	public void createglobalitem(int id, int x, int y, int amount) {

		GroundItem item = new GroundItem(id, x, y, amount, 0, 0, "GLOBAL");
		addItem(item);

	}

	public void createglobalitems() {

		//createglobalitem(14484, 3187, 3450, 1);

	}

	public void createGroundItem(final Player c, int itemId, final int itemX,
			final int itemY, final int itemAmount, final int playerId) {
		if (itemId > 0) {
			if (itemId >= 2412 && itemId <= 2414) {
				c.sendMessage("The cape vanishes as it touches the ground.");
				return;
			}
			if (itemId > 4705 && itemId < 4760) {
				for (final int[] brokenBarrow : brokenBarrows) {
					if (brokenBarrow[0] == itemId) {
						itemId = brokenBarrow[1];
						break;
					}
				}
			}
			if (!game.item.Item.itemStackable[itemId] && itemAmount > 0) {
				for (int j = 0; j < itemAmount; j++) {
					c.getItems().createGroundItem(itemId, itemX, itemY, 1);
					final GroundItem item = new GroundItem(itemId, itemX,
							itemY, c.heightLevel, 1, c.playerId,
							ItemHandler.HIDE_TICKS,
							PlayerHandler.players[playerId].playerName);
					addItem(item);
				}
			} else {
				c.getItems().createGroundItem(itemId, itemX, itemY, itemAmount);
				final GroundItem item = new GroundItem(itemId, itemX, itemY,
						c.heightLevel, itemAmount, c.playerId,
						ItemHandler.HIDE_TICKS,
						PlayerHandler.players[playerId].playerName);
				addItem(item);
			}
		}
	}

	public ItemList getItemList(final int i) {
		for (final game.item.ItemList element : ItemList) {
			if (element != null) {
				if (element.itemId == i) {
					return element;
				}
			}
		}
		return null;
	}

	/**
	 * Item amount
	 **/
	public int itemAmount(final int itemId, final int itemX, final int itemY) {
		for (final GroundItem i : items) {
			if (i.getItemId() == itemId && i.getItemX() == itemX
					&& i.getItemY() == itemY) {
				return i.getItemAmount();
			}
		}
		return 0;
	}

	/**
	 * Item exists
	 **/
	public boolean itemExists(final int itemId, final int itemX, final int itemY) {
		for (final GroundItem i : items) {
			if (i.getItemId() == itemId && i.getItemX() == itemX
					&& i.getItemY() == itemY) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("resource")
	public boolean loadItemList(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./Data/cfg/"+FileName));
		} catch(FileNotFoundException fileex) {
			Misc.println(FileName+": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			Misc.println(FileName+": error loading file.");
			return false;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("item")) {
					int[] Bonuses = new int[12];
					for (int i = 0; i < 12; i++) {
						if (token3[(6 + i)] != null) {
							Bonuses[i] = Integer.parseInt(token3[(6 + i)]);
						} else {
							break;
						}
					}
					newItemList(Integer.parseInt(token3[0]), token3[1].replaceAll("_", " "), token3[2].replaceAll("_", " "), Double.parseDouble(token3[4]), Double.parseDouble(token3[4]), Double.parseDouble(token3[6]), Bonuses);
				}
			} else {
				if (line.equals("[ENDOFITEMLIST]")) {
					try { characterfile.close(); } catch(IOException ioexception) { }
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return false;
	}

	public void loadItemPrices(final String filename) {
		try {
			final Scanner s = new Scanner(new File("./data/cfg/" + filename));
			while (s.hasNextLine()) {
				final String[] line = s.nextLine().split(" ");
				final ItemList temp = getItemList(Integer.parseInt(line[0]));
				if (temp != null) {
					temp.ShopValue = Integer.parseInt(line[1]);
					temp.LowAlch = Integer.parseInt(line[1]);
					temp.HighAlch = Integer.parseInt(line[1]);
				}
			}
			s.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public void newItemList(final int ItemId, final String ItemName,
			final String ItemDescription, final double ShopValue,
			final double LowAlch, final double HighAlch, final int Bonuses[]) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (ItemList[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			return; // no free slot found
		}
		final ItemList newItemList = new ItemList(ItemId);
		newItemList.itemName = ItemName;
		newItemList.itemDescription = ItemDescription;
		newItemList.ShopValue = ShopValue;
		newItemList.LowAlch = LowAlch;
		newItemList.HighAlch = HighAlch;
		newItemList.Bonuses = Bonuses;
		ItemList[slot] = newItemList;
	}

	public void process() {
		final ArrayList<GroundItem> toRemove = new ArrayList<GroundItem>();
		for (int j = 0; j < items.size(); j++) {
			if (items.get(j) != null) {
				final GroundItem i = items.get(j);
				if (i.hideTicks > 0) {
					i.hideTicks--;
				}
				if (i.hideTicks == 1) { // item can now be seen by others
					i.hideTicks = 0;
					createGlobalItem(i);
					i.removeTicks = ItemHandler.HIDE_TICKS;
				}
				if (i.removeTicks > 0) {
					i.removeTicks--;
				}
				if (i.removeTicks == 1) {
					i.removeTicks = 0;
					toRemove.add(i);
					// removeGlobalItem(i, i.getItemId(), i.getItemX(),
					// i.getItemY(), i.getItemAmount());
				}
			}
		}
		for (int j = 0; j < toRemove.size(); j++) {
			final GroundItem i = toRemove.get(j);
			removeGlobalItem(i, i.getItemId(), i.getItemX(), i.getItemY(),
					i.getItemAmount());
		}
	}

	/**
	 * Reloads any items if you enter a new region
	 **/
	public void reloadItems(final Player c) {
		for (final GroundItem i : items) {
			if (c != null) {
				c.getItems();
				if (ItemAssistant.tradeable(i.getItemId())
						|| i.getName().equalsIgnoreCase(c.playerName)) {
					if (c.distanceToPoint(i.getItemX(), i.getItemY()) <= 60) {
						if (i.hideTicks > 0
								&& i.getName().equalsIgnoreCase(c.playerName)) {
							c.getItems().removeGroundItem(i.getItemId(),
									i.getItemX(), i.getItemY(),
									i.getItemAmount());
							c.getItems().createGroundItem(i.getItemId(),
									i.getItemX(), i.getItemY(),
									i.getItemAmount());
						}
						if (i.hideTicks == 0) {
							c.getItems().removeGroundItem(i.getItemId(),
									i.getItemX(), i.getItemY(),
									i.getItemAmount());
							c.getItems().createGroundItem(i.getItemId(),
									i.getItemX(), i.getItemY(),
									i.getItemAmount());
						}
					}
				}
			}
		}
	}

	/**
	 * Remove item for just the item controller (item not global yet)
	 **/
	public void removeControllersItem(final GroundItem i, final Player c,
			final int itemId, final int itemX, final int itemY,
			final int itemAmount) {
		c.getItems().removeGroundItem(itemId, itemX, itemY, itemAmount);
		removeItem(i);
	}

	/**
	 * Remove item for everyone within 60 squares
	 **/
	public void removeGlobalItem(final GroundItem i, final int itemId, final int itemX, final int itemY, final int itemAmount) {
		for (final Player p : PlayerHandler.players) {
			if (p != null) {
				final Player person = p;
				if (person != null) {
					if (person.distanceToPoint(itemX, itemY) <= 60) {
						person.getItems().removeGroundItem(itemId, itemX,
								itemY, itemAmount);
					}
				}
			}
		}
		if (i.ownerName == "GLOBAL"){
			i.hideTicks = 100;
		} else { 
			removeItem(i);
		}
	}

	/**
	 * Removing the ground item
	 **/
	public void removeGroundItem(final Player c, final int itemId,
			final int itemX, final int itemY, final boolean add) {
		for (final GroundItem i : items) {
			if (i.getItemId() == itemId && i.getItemX() == itemX
					&& i.getItemY() == itemY) {
				final int itemAmount = c.getItems().getItemAmount(itemId);
				final int itemAmount1 = Server.itemHandler.itemAmount(itemId,
						itemX, itemY);
				if (itemAmount + itemAmount1 < -1) {
					return;
				}
				if (i.hideTicks > 0
						&& i.getName().equalsIgnoreCase(c.playerName)) {
					if (add) {
						if (!c.getItems().specialCase(itemId)) {
							if (c.getItems().addItem(i.getItemId(),
									i.getItemAmount())) {
								removeControllersItem(i, c, i.getItemId(),
										i.getItemX(), i.getItemY(),
										i.getItemAmount());
								break;
							}
						} else {
							c.getItems().handleSpecialPickup(itemId);
							removeControllersItem(i, c, i.getItemId(),
									i.getItemX(), i.getItemY(),
									i.getItemAmount());
							break;
						}
					} else {
						removeControllersItem(i, c, i.getItemId(),
								i.getItemX(), i.getItemY(), i.getItemAmount());
						break;
					}
				} else if (i.hideTicks <= 0) {
					if (add) {
						if (c.getItems().addItem(i.getItemId(),
								i.getItemAmount())) {
							removeGlobalItem(i, i.getItemId(), i.getItemX(),
									i.getItemY(), i.getItemAmount());
							break;
						}
					} else {
						removeGlobalItem(i, i.getItemId(), i.getItemX(),
								i.getItemY(), i.getItemAmount());
						break;
					}
				}
			}
		}
	}

	/**
	 * Removes item from list
	 **/
	public void removeItem(final GroundItem item) {
		items.remove(item);
	}

	/**
	 * Reloads item.cfg and prices.txt
	 */
	public void reloadItems() {			
		for(int i = 0; i < Config.ITEM_LIMIT; i++) {
			ItemList[i] = null;
		}
		loadItemList("Item Config.cfg");
		loadItemPrices("Item Prices.txt");
	}

}
