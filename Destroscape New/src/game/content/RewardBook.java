package game.content;

import engine.event.Event;
import engine.event.EventContainer;
import engine.event.EventManager;
import engine.util.Misc;
import game.entity.player.Player;

public class RewardBook {

	public static int[] 
			common = {4587,5698, //Dragon 
		
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
	};

	public static int[][] chumpChange = {
		{995,10000000},//Cash
		{12158,40},//Gold Charm
		{12163,40},//Blue Charm
		{12160,40},//Crimson Charm
		{12159,40},//Green Charm
		{9244,100},//Dragon Bolts(e)
		{537,10}, //Dragon Bones
		{9144, 200}, //Runite Bolts
		{1514, 100},//Magic Logs
		{2364, 50},//Rune Bars
		{452, 25},//Runite Ore
		{9193,50},//Dragon Bolt Tips
		{12183, 100},//Spirit Shards
		{15273,500},//Rocktails
	};

	public static final int BOOK = 20960;

	private static final int DRAGONSTONE = 1631;

	private static final int OPEN_ANIMATION = 1350;

	public static boolean canOpen(final Player c) {
		if (c.getItems().playerHasItem(RewardBook.BOOK)) {
			return true;
		} else {
			c.sendMessage("You do not have a Reward Book!");
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
			c.sendMessage("You dont have enough space in your inventory.");
			return;
		}
		if (RewardBook.canOpen(c)) {
		switch(getRandom(c)) {
		case 1:
			//random = Misc.random(rare.length - 1);
			//c.getItems().addItem(rare[random],1);
			c.getItems().addItem(995, 1000000);
			c.sendMessage("You recieve a <col=255>1M Coins</col> from the Reward Book!");
			c.doubleXP = System.currentTimeMillis();
			c.sendMessage(c.getPA().getTimeLeftForDX());
			c.sendMessage("@red@Note: These books do not addon to the double XP timer.");
			break;
		case 2:
			//c.getItems().addItem(RewardBook.DRAGONSTONE, 1);
			//random = Misc.random(uncommon.length - 1);
			//c.getItems().addItem(uncommon[random],1);
			//c.sendMessage("You recieve a <col=255>"+c.getItems().getItemName(uncommon[random])+"</col> from the Box!");
			c.getItems().addItem(995, 1000000);
			c.sendMessage("You recieve a <col=255>1M Coins</col> from the Reward Book!");
			c.doubleXP = System.currentTimeMillis();
			c.sendMessage(c.getPA().getTimeLeftForDX());
			c.sendMessage("@red@Note: These books do not addon to the double XP timer.");
			break;
		case 3:
			//c.getItems().addItem(RewardBook.DRAGONSTONE, 1);
			//random = Misc.random(common.length - 1);
			//c.getItems().addItem(common[random],1);
			//c.sendMessage("You recieve a <col=255>"+c.getItems().getItemName(common[random])+"</col> from the Box!");
			c.getItems().addItem(995, 1000000);
			c.sendMessage("You recieve a <col=255>1M Coins</col> from the Reward Book!");
			c.doubleXP = System.currentTimeMillis();
			c.sendMessage(c.getPA().getTimeLeftForDX());
			c.sendMessage("@red@Note: These books do not addon to the double XP timer.");
			break;
		case 4:
			//c.getItems().addItem(RewardBook.DRAGONSTONE, 1);
			//random = Misc.random(chumpChange.length - 1);
			//c.getItems().addItem(chumpChange[random][0],Misc.random(chumpChange[random][1]) + 1);
			//c.sendMessage("You recieve some <col=255>"+c.getItems().getItemName(chumpChange[random][0])+"</col> from the Box!");
			c.getItems().addItem(995, 1000000);
			c.sendMessage("You recieve a <col=255>1M Coins</col> from the Reward Book!");
			c.doubleXP = System.currentTimeMillis();
			c.sendMessage(c.getPA().getTimeLeftForDX());
			c.sendMessage("@red@Note: These books do not addon to the double XP timer.");
			break;
		}
			c.getItems().deleteItem(RewardBook.BOOK, 1);
			c.startAnimation(RewardBook.OPEN_ANIMATION);
	}
}
}

