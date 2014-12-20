package game.content.prestige;

import engine.util.Misc;
import game.entity.player.Player;

public class PrestigeChest {

	private static final int OPEN_ANIMATION = 881;
	public static int pointsCost = 1;

	public static int[] 
			common = {9096,9097,9084,9098,9099,9100,9101, //Lunar
		987,985, //Loop,Tooth Halves
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
		1959, // Pumpkin
		1037, // Bunny ears
		13754, //Holy Elixer
		10330,10332, //Third-age range
		10338,10340, //Third-age mage
		10352,10348, //Third-age melee
		19447,19443, //Ancient D'hide
		19453,19455, //Bandos D'hide
		19368,19465, //Armadyl D'hide
		1050, //Santa Hat
	};

	public static int[][] chumpChange = {
		{995,10000000},//Cash
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

	public static void giveReward(Player c) {
		int random = 0;
		if(!c.hasSpace()) {
			c.sendMessage("You dont have enough spce in your inventory.");
			return;
		}
		switch(getRandom(c)) {
		case 1:
			random = Misc.random(rare.length - 1);
			c.getItems().addItem(rare[random],1);
			c.prestigePoints -= pointsCost;
			c.sendMessage("You recieve a "+c.getItems().getItemName(rare[random])+" from the chest!");
			//DrawInterface.drawPrestigePanel(c);
			c.saveGame();
			break;
		case 2:
			random = Misc.random(uncommon.length - 1);
			c.getItems().addItem(uncommon[random],1);
			c.prestigePoints -= pointsCost;
			c.sendMessage("You recieve a "+c.getItems().getItemName(uncommon[random])+" from the chest!");
			//DrawInterface.drawPrestigePanel(c);
			c.saveGame();
			break;
		case 3:
			random = Misc.random(common.length - 1);
			c.getItems().addItem(common[random],1);
			c.prestigePoints -= pointsCost;
			c.sendMessage("You recieve a "+c.getItems().getItemName(common[random])+" from the chest!");
			//DrawInterface.drawPrestigePanel(c);
			c.saveGame();
			break;
		case 4:
			random = Misc.random(chumpChange.length - 1);
			c.getItems().addItem(chumpChange[random][0],Misc.random(chumpChange[random][1]) + 1);
			c.prestigePoints -= pointsCost;
			c.sendMessage("You recieve "+chumpChange[random][1]+" "+c.getItems().getItemName(chumpChange[random][0])+" from the chest!");
			//DrawInterface.drawPrestigePanel(c);
			c.saveGame();
			break;
		}
		c.startAnimation(OPEN_ANIMATION);
	}

	public static int getRandom(Player c) {
		int random = Misc.random(99) + 1;
		int common = 25,uncommon = 13,rare = 3;
		/*if(c.presChestAmt > 0 && c.raisePresRate){
			c.presChestAmt--;
			common = 50;
			uncommon = 25;
			rare = 5;
			c.sendMessage("Your ability greatly improves your luck with this chest.");
			if(c.presChestAmt <= 0){
				c.presChestAmt = 0;
				c.raisePresRate = false;
				c.sendMessage("Your increased luck ability with the prestige chest vanishes.");
			}
		}*/
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

	public static void openChest(Player c) {
		if(c.prestigePoints>= pointsCost)
			giveReward(c);
		else
			c.sendMessage("You do not have enough Prestige Points to do this.");
	}
}