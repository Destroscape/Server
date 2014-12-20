package game.content;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.util.Misc;
import game.entity.player.Player;
import game.content.achievement.*;

public class DonatorChest {

	public static int[] 
			common = {987,985, //Loop,Tooth Halves
		11133, //Regen Bracelet
		21435, //Herald cape
		4224,4212, //Crystal
		10378,10382, //Guthix D'hide
		4587, //Dragon scimitar
	},
	uncommon = {15503,15505,15507,15509, //Royal
		13734, //Normal Spirit Shield
		10334,10336, //Third-age Range
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
	rare = {4716,4718,4720,4722,4724,4726,4728,4730,4732,4734,4736,4738,4740,4742,4744,4746,4748,4750,4751, // Barrows
		19333,19356,19350,19352, //Ornamental Kits
		18336, //Imbue Scroll
		1959, // Pumpkin
		13754, //Holy Elixer
		6585, //Fury
		4151, //Whip
		10330,10332, //Third-age range
		10338,10340, //Third-age mage
		10352,10348, //Third-age melee
		19447,19443, //Ancient D'hide
		19453,19455, //Bandos D'hide
		19368,19465, //Armadyl D'hide
		6737, //B Ring
		8838,8840,8842,11664,11665,11663, //Void
		6889,6916,6918,6920,6922,6924, //Inifinity and Magic's book
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
		{1961, 100},//Easter eggs
		{15273,50},//Rocktails
		{989,3},//Crystal key
		{11951,15},//Snowballs
	};

	private static final int OPEN_ANIMATION = 881;

	public static boolean canOpen(final Player c) {
		if (c.chestChance >= 1) {
			return true;
		} else {
			c.sendMessage("You do not seem to have any chest chances!");
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

	public static void searchChest(final Player c, final int id, final int x,
			final int y) {
		int random = 0;
		if (c.getItems().freeSlots() < 2) {
			c.sendMessage("You dont have enough spce in your inventory.");
			return;
		}
		if (DonatorChest.canOpen(c)) {
			c.startAnimation(DonatorChest.OPEN_ANIMATION);
		switch(getRandom(c)) {
		case 1:
			random = Misc.random(rare.length - 1);
			c.getItems().addItem(rare[random],1);
			c.sendMessage("You recieve a "+c.getItems().getItemName(rare[random])+" from the chest!");
			break;
		case 2:
			random = Misc.random(uncommon.length - 1);
			c.getItems().addItem(uncommon[random],1);
			c.sendMessage("You recieve a "+c.getItems().getItemName(uncommon[random])+" from the chest!");
			break;
		case 3:
			random = Misc.random(common.length - 1);
			c.getItems().addItem(common[random],1);
			c.sendMessage("You recieve a "+c.getItems().getItemName(common[random])+" from the chest!");
			break;
		case 4:
			random = Misc.random(chumpChange.length - 1);
			c.getItems().addItem(chumpChange[random][0],Misc.random(chumpChange[random][1]) + 1);
			c.sendMessage("You recieve some "+c.getItems().getItemName(chumpChange[random][0])+" from the chest!");
			break;
		}
			c.chestChance -= 1;
		}
}
}

