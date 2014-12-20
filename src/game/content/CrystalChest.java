package game.content;

import engine.event.Event;
import engine.event.EventContainer;
import engine.event.EventManager;
import engine.util.Misc;
import game.entity.player.Player;
import game.content.achievement.*;

public class CrystalChest {

	public static int[] 
			common = {987,985, //Loop,Tooth Halves
		11133, //Regen Bracelet
		3481,3483,3485,3486,3488, //Gilded Armour
		10836,10837,10838,10839,10840, //Jester
		21435, //Herald cape
		4224, //Crystal Shield
		13672,13673,13674,13675, //Ring Master
		10378,10382, //Guthix D'hide
	},
	uncommon = {15503,15505,15507,15509, //Royal
		13734, //Normal Spirit Shield
		10334,10336, //Third-age Range
		989, //Crystal Key
		19358,19354,19346,
		10342,10344, //Third-age Mage
		10350,10346, //Third-age Melee
		21506, //Skeletal Steam Staff
		19445,19449, //Ancient D'hide
		15332,15308,15312,15316,15320,15324,15328, //Extremes & Overloads
		19457,19451,19370, //Bandos D'hide
		19461,19463,19459, //Armadyl D'hide
		10376,10380,10448, //Guthix D'hide
	}, 
	rare = {14939,14938, // Agile
		19333,19356,19350,19352, //Ornamental Kits
		18336, //Imbue Scroll
		13754, //Holy Elixer
		10330,10332, //Third-age range
		10338,10340, //Third-age mage
		10352,10348, //Third-age melee
		19447,19443, //Ancient D'hide
		19453,19455, //Bandos D'hide
		19368,19465, //Armadyl D'hide
	};

	public static int[][] chumpChange = {
		{995,1000000},//Cash
		{12158,20},//Gold Charm
		{12163,20},//Blue Charm
		{12160,20},//Crimson Charm
		{12159,20},//Green Charm
		{9244,100},//Dragon Bolts(e)
		{537,10}, //Dragon Bones
		{9144, 200}, //Runite Bolts
		{1514, 100},//Magic Logs
		{2364, 50},//Rune Bars
		{452, 25},//Runite Ore
		{9193,50},//Dragon Bolt Tips
		{12183, 100},//Spirit Shards
		{15273,50},//Rocktails
	};

	public static final int[] KEY_HALVES = { 985, 987 };

	public static final int KEY = 989;

	private static final int DRAGONSTONE = 1631;

	private static final int OPEN_ANIMATION = 881;

	public static boolean canOpen(final Player c) {
		if (c.getItems().playerHasItem(CrystalChest.KEY)) {
			return true;
		} else {
			c.sendMessage("The chest is locked");
			return false;
		}
	}

	public static int loopHalf() {
		return CrystalChest.KEY_HALVES[1];
	}
	public static int toothHalf() {
		return CrystalChest.KEY_HALVES[0];
	}

	public static void makeKey(final Player c) {
		if (c.getItems().playerHasItem(CrystalChest.toothHalf(), 1)
				&& c.getItems().playerHasItem(CrystalChest.loopHalf(), 1)) {
			c.getItems().deleteItem(CrystalChest.toothHalf(), 1);
			c.getItems().deleteItem(CrystalChest.loopHalf(), 1);
			c.getItems().addItem(CrystalChest.KEY, 1);
		}
	}
	public static int getRandom(Player c) {
		int random = Misc.random(99) + 1;
		int common = 25,uncommon = 13,rare = 3;
		if(random <= rare)
			return 1;
		random = Misc.random(99) + 1;
		if(random <= uncommon)
			return 2;
		random = Misc.random(99) + 1;
		if(random <= common)
			return 3;
		return 4;
	}

	public static void searchChest(final Player c, final int id, final int x,
			final int y) {
		int random = 0;
		if (c.getItems().freeSlots() < 2) {
			c.sendMessage("You dont have enough spce in your inventory.");
			return;
		}
		if (CrystalChest.canOpen(c)) {
		switch(getRandom(c)) {
		case 1:
			c.getItems().addItem(CrystalChest.DRAGONSTONE, 1);
			random = Misc.random(rare.length - 1);
			c.getItems().addItem(rare[random],1);
			c.sendMessage("You recieve a "+c.getItems().getItemName(rare[random])+" from the chest!");
			break;
		case 2:
			c.getItems().addItem(CrystalChest.DRAGONSTONE, 1);
			random = Misc.random(uncommon.length - 1);
			c.getItems().addItem(uncommon[random],1);
			c.sendMessage("You recieve a "+c.getItems().getItemName(uncommon[random])+" from the chest!");
			break;
		case 3:
			c.getItems().addItem(CrystalChest.DRAGONSTONE, 1);
			random = Misc.random(common.length - 1);
			c.getItems().addItem(common[random],1);
			c.sendMessage("You recieve a "+c.getItems().getItemName(common[random])+" from the chest!");
			break;
		case 4:
			c.getItems().addItem(CrystalChest.DRAGONSTONE, 1);
			random = Misc.random(chumpChange.length - 1);
			c.getItems().addItem(chumpChange[random][0],Misc.random(chumpChange[random][1]) + 1);
			c.sendMessage("You recieve some "+c.getItems().getItemName(chumpChange[random][0])+" from the chest!");
			break;
		}
			c.getItems().deleteItem(CrystalChest.KEY, 1);
			c.startAnimation(CrystalChest.OPEN_ANIMATION);
			AchievementManager.increase(c, Achievements.THE_CRYSTAL_KEY);
	}
}
}

