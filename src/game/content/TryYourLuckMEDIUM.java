package game.content;

import engine.event.Event;
import engine.event.EventContainer;
import engine.event.EventManager;
import engine.util.Misc;
import game.entity.player.Player;

public class TryYourLuckMEDIUM {

	public static int[] 
			common = {11133, //Regen Bracelet
		3481,3483,3485,3486,3488, //Gilded Armour
		4224, //Crystal Shield
	},
	uncommon = {13734, //Normal Spirit Shield
		21506, //Skeletal Steam Staff
		15332,15308,15312,15316,15320,15324,15328, //Extremes & Overloads
	}, 
	rare = {14939,14938, // Agile
		19333,19356,19350,19352, //Ornamental Kits
		18336, //Imbue Scroll
		1959, // Pumpkin
		13754, //Holy Elixer
		4151, 6585, //Whip and Fury
		1050, //Santa hat
		11696, 11698, // BGS and SGS
	};

	public static int[][] chumpChange = {
		{995,20000000},//Cash
		{12158,100},//Gold Charm
		{12163,100},//Blue Charm
		{12160,100},//Crimson Charm
		{12159,100},//Green Charm
		{9244,300},//Dragon Bolts(e)
		{537,100}, //Dragon Bones
		{9144, 400}, //Runite Bolts
		{1514, 400},//Magic Logs
		{2364, 120},//Rune Bars
		{452, 85},//Runite Ore
		{9193,90},//Dragon Bolt Tips
		{12183, 1000},//Spirit Shards
		{15273,2000},//Rocktails
	};

	public static final int BOX = 13077;

	private static final int DRAGONSTONE = 1631;

	private static final int OPEN_ANIMATION = 11661;

	public static boolean canOpen(final Player c) {
		if (c.getItems().playerHasItem(TryYourLuckMEDIUM.BOX)) {
			return true;
		} else {
			c.sendMessage("You do not have a Try your luck box!");
			return false;
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

	public static void searchChest(final Player c) {
		int random = 0;
		if (c.getItems().freeSlots() < 2) {
			c.sendMessage("You dont have enough spce in your inventory.");
			return;
		}
		if (TryYourLuckMEDIUM.canOpen(c)) {
		switch(getRandom(c)) {
		case 1:
			//c.getItems().addItem(TryYourLuckMEDIUM.DRAGONSTONE, 1);
			random = Misc.random(rare.length - 1);
			c.getItems().addItem(rare[random],1);
			c.sendMessage("You recieve a "+c.getItems().getItemName(rare[random])+" from the Box!");
			break;
		case 2:
			//c.getItems().addItem(TryYourLuckMEDIUM.DRAGONSTONE, 1);
			random = Misc.random(uncommon.length - 1);
			c.getItems().addItem(uncommon[random],1);
			c.sendMessage("You recieve a "+c.getItems().getItemName(uncommon[random])+" from the Box!");
			break;
		case 3:
			//c.getItems().addItem(TryYourLuckMEDIUM.DRAGONSTONE, 1);
			random = Misc.random(common.length - 1);
			c.getItems().addItem(common[random],1);
			c.sendMessage("You recieve a "+c.getItems().getItemName(common[random])+" from the Box!");
			break;
		case 4:
			//c.getItems().addItem(TryYourLuckMEDIUM.DRAGONSTONE, 1);
			random = Misc.random(chumpChange.length - 1);
			c.getItems().addItem(chumpChange[random][0],Misc.random(chumpChange[random][1]) + 1);
			c.sendMessage("You recieve some "+c.getItems().getItemName(chumpChange[random][0])+" from the Box!");
			break;
		}
			c.getItems().deleteItem(TryYourLuckMEDIUM.BOX, 1);
			c.startAnimation(TryYourLuckMEDIUM.OPEN_ANIMATION);
	}
}
}

