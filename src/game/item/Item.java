package game.item;

import game.Config;
import game.Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Item {

	private static String[] fullbody = { "top", "chestplate", "shirt",
		"platebody", "Ahrims robetop", "White platebody", "Armdayl_Body",
		"Armadyl body", "Armadyl Body", "Elite void knight top",
		"Karils top", "brassard", "Robe top", "robetop",
		"Vesta's chainbody", "platebody (t)", "platebody (g)",
		"chestplate", "Lord Marshal Top", "Primal platebody", "torso",
		"hauberk", "Dragon chainbody", "blouse", "jacket",/*"chainbody",*/
		"Chainbody", "leather body", "Leather Body", "robe top",
		"Pernix body", "Ganodermic poncho", "Vanguard body", "Granite body",
	"Splitbark body", "robe (g)", "Varrock armour", "Ringmaster shirt"};

	private static String[] fullhat = { "med helm", "coif", "Dharok's helm",
		"Slayer helmet", "hood", "Initiate helm", "Coif",
		"Helm of neitiznot", "Armadyl helmet", "Berserker helm",
		"Bearhead", "Archer helm", "Farseer helm", "Warrior helm", "Void",
		"Lumberjack hat", "Reindeer hat", "Larupia hat", "mask",
		"headdress", "Kyatt hat", "Bomber cap", "Dwarven helmet",
		"Dragon Mask", "3rd age mage hat", "Statius's Full Helm",
		"dragon mask", "Enchanted hat", "Third-age mage hat",
		"Granite helm", "Splitbark helm", "neitiznot", "Berserker helm"};

	private static String[] fullmask = { "full helm",
		"headdress", "mask", "Torva Fullhelm", "Verac's helm",
		"Guthan's helm", "Karil's coif", "mask", "Torag's helm", "sallet",
		"Saradomin helm", "Lunar helm", "Dragon Mask",
		"Dragon full helm (or)", "Dragon full helm (sp)", "dragon mask", "Torva full helm",
		"Pernix cowl", "Vanguard helm", "Rock-shell helm", "full helm (g)", "Pernix mask", "Slayer helmet",
		"Full slayer helmet", "Dharok's helm", "neitiznot", "Berserker helm", "med helm"};

	public static boolean[] itemStackable = new boolean[Config.ITEM_LIMIT];
	public static boolean[] itemIsNote = new boolean[28000];
	public static int[] targetSlots = new int[Config.ITEM_LIMIT];

	static {
		int counter = 0;
		int c;

		try {
			final FileInputStream dataIn = new FileInputStream(new File(
					"./Data/data/stackable.dat"));
			while ((c = dataIn.read()) != -1) {
				if (c == 0) {
					Item.itemStackable[counter] = false;
				} else {
					Item.itemStackable[counter] = true;
					itemStackable[1399] = true;
				}
				counter++;
			}
			dataIn.close();
			int[] stackableItems = {21773, 21774}; //Hardcode Stackables
			for (int i = 0; i < stackableItems.length; i++) {
				itemStackable[stackableItems[i]] = true;
			}
		} catch (final IOException e) {
			System.out
			.println("Critical error while loading stackabledata! Trace:");
			e.printStackTrace();
		}
		counter = 0;
		try {
			final FileInputStream dataIn = new FileInputStream(new File(
					"./Data/data/notes.dat"));
			while ((c = dataIn.read()) != -1) {
				if (c == 0) {
					Item.itemIsNote[counter] = true;
				} else {
					Item.itemIsNote[counter] = false;
				}
				counter++;
			}
			dataIn.close();
		} catch (final IOException e) {
			System.out.println("Critical error while loading notedata! Trace:");
			e.printStackTrace();
		}
		counter = 0;
		try {
			final FileInputStream dataIn = new FileInputStream(new File(
					"./Data/data/equipment.dat"));
			while ((c = dataIn.read()) != -1) {
				Item.targetSlots[counter++] = c;
			}
			dataIn.close();
		} catch (final IOException e) {
			System.out.println("Critical error while loading notedata! Trace:");
			e.printStackTrace();
		}
	}

	public static String getItemName(final int id) {
		for (final ItemList element : Server.itemHandler.ItemList) {
			if (element != null) {
				if (element.itemId == id) {
					return element.itemName;
				}
			}
		}
		return null;
	}

	public static boolean isFullBody(final int itemId) {
		final String weapon = Item.getItemName(itemId);
		if (weapon == null) {
			return false;
		}
		for (final String element : Item.fullbody) {
			if (weapon.endsWith(element) || weapon.contains(element)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFullHelm(final int itemId) {
		final String weapon = Item.getItemName(itemId);
		if (weapon == null) {
			return false;
		}
		for (final String element : Item.fullhat) {
			if (weapon.endsWith(element) && itemId != 2631 && itemId == 20147) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFullMask(final int itemId) {
		final String weapon = Item.getItemName(itemId);
		if (weapon == null) {
			return false;
		}
		for (final String element : Item.fullmask) {
			if (weapon.endsWith(element) && itemId != 13340 && itemId != 2631) {
				return true;
			}
		}
		return false;
	}

	public static boolean playerAmulet(final int itemId) {
		final String[] data = { "amulet", "Amulet", "scarf", "Necklace",
				"necklace", "Pendant", "pendant", "Symbol", "symbol", "stole",
		"Stole" };
		final String item = Item.getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for (final String element : data) {
			if (item.endsWith(element) || item.contains(element)) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerArrows(final int itemId) {
		final String[] data = { "Arrows", "arrows", "Arrow", "arrow", "Bolts",
				"bolts", "rack", "Rack", "Shots", "shot", "Shot", "shots", };
		final String item = Item.getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for (final String element : data) {
			if (item.endsWith(element) || item.contains(element)) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerBody(final int itemId) {
		final String[] data = { "body", "top", "Priest gown", "apron", "shirt",
				"platebody", "robetop", "body(g)", "body(t)",
				"White platebody", "Wizard robe (g)", "Wizard robe (t)",
				"body", "brassard", "blouse", "tunic", "leathertop",
				"Saradomin plate", "chainbody", "Top", "Lord Marshal Top",
				"Primal platebody", "hauberk", "Shirt", "torso", "chestplate",
				"jacket", "Vesta's chainbody", "chainbody", "Chainbody",
				"leather body", "Leather Body", "Pernix body", "Ganodermic poncho",
				"Rock-shell plate", "Varrock armour",};
		final String item = Item.getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for (final String element : data) {
			if (item.endsWith(element) || item.contains(element)) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerBoots(final int itemId) {
		final String[] data = { "Shoes", "shoes", "boots", "Boots", "Flippers",
		"flippers" };
		final String item = Item.getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for (final String element : data) {
			if (item.endsWith(element) || item.contains(element)) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerCape(final int itemId) {
		final String[] data = { "cloak", "TokHaar-Kal",
				"Dungeoneering master cape", "cape", "Cape", "attractor",
				"Attractor", "Ava's", "Dungeoneering Cape",
				"Dungeoneering Cape(T)", "Ardounge Cloak", };
		final String item = Item.getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for (final String element : data) {
			if (item.endsWith(element) || item.contains(element)) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerGloves(final int itemId) {
		final String[] data = { "Gloves", "gloves", "glove", "Glove", "Vamb",
				"vamb", "gauntlets", "Gauntlets", "bracers", "Bracers",
				"Vambraces", "vambraces", "bracelet" };
		final String item = Item.getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for (final String element : data) {
			if (item.endsWith(element) || item.contains(element)) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerHats(final int itemId) {
		final String[] data = { "boater", "Royal crown", "cowl", "peg", "coif",
				"helm", "bearhead", "Coif", "mask", "hat", "headband", "hood",
				"disguise", "cavalier", "full", "tiara", "wreath", "helmet",
				"Hat", "ears", "partyhat", "helm(t)", "helm(g)", "beret",
				"facemask", "sallet", "Mask", "Dragon Mask", "dragon mask",
				"hat(g)", "hat(t)", "cap", "bandana", "Helm", "Mitre", "mitre",
				"Bomber cap", "headdress", "Top hat", "Royal crown",
				"Pernix cowl", "Ganodermic visor", "Focus sight", "Hexcrest", "Ringmaster hat"};
		final String item = Item.getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for (final String element : data) {
			if ((item.endsWith(element) || item.contains(element))
					&& itemId != 4214) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerLegs(final int itemId) {
		final String[] data = { "tassets", "chaps", "bottoms", "gown",
				"trousers", "MarshBot", "platelegs", "robe", "plateskirt",
				"legs", "leggings", "Proselyte Tasset", "Proselyte tasset",
				"shorts", "Skirt", "skirt", "cuisse", "Pantaloons", "Trousers",
				"Lord Marshal Trousers", "Elite void knight top", "Ringmaster pants", "tights"};
		final String item = Item.getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for (final String element : data) {
			if ((item.endsWith(element) || item.contains(element))
					&& !item.contains("top") && !item.contains("robe (g)")
					&& itemId != 13340 && !item.contains("robe (t)")) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerRings(final int itemId) {
		final String[] data = { "ring", "rings", "Ring", "Rings", };
		final String item = Item.getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for (final String element : data) {
			if (item.endsWith(element) || item.contains(element)) {
				item1 = true;
			}
		}
		return item1;
	}

	public static boolean playerShield(final int itemId) {
		final String[] data = { "kiteshield", "deflector", "book",
				"Kiteshield", "toktz-ket-xil", "Toktz-ket-xil", "shield",
				"Shield", "Kite", "kite", "Defender", "defender", "balance",
				"war", "Book of law"};
		final String item = Item.getItemName(itemId);
		if (item == null) {
			return false;
		}
		boolean item1 = false;
		for (final String element : data) {
			if (item.endsWith(element) || item.contains(element)) {
				item1 = true;
			}
		}
		return item1;
	}

}
