package server.model.players;

import server.model.players.Client;
import server.event.EventManager;
import server.event.Event;
import server.event.EventContainer;
import server.util.Misc;

/*
 * Author - Ferocious & Mod Loc (ZacharySaysWut@yahoo.com)
 * http://www.rune-server.org/members/ags 
 * http://www.rune-server.org/members/Ferocious
 */

public class AdvancedMysteryBox {

	public static boolean Canusebox = true;
	
	public static int Common [] = 
	{1201,1079,1093,1127,1163}; // Add more item Id's
	
	public static int Uncommon [] = 
	{6967,3140,916,6918,6920,6922,6924}; // Add more item Id's
	
	public static int Rare [] = 
	{4151,11235,6914}; // Add more item Id's
	public static int Legendary [] = 
	{1050};

	public static int GenerateMyrsteryPrize(final Client c) {
		 EventManager.getSingleton().addEvent(new Event() {
			int BoxTimer = 2;
			int Coins = 50000 + Misc.random(25000);
			public void execute(EventContainer Box) {
				Canusebox = false;
				if (BoxTimer == 2) {
					c.sendMessage("Calculating prize...");
				}
				if (BoxTimer == 0) {
					c.getItems().addItem(995, Coins);
					int Random = Misc.random(100);
					if (Random <= 64) {
						c.getItems().addItem(Common[(int) (Math.random() * Common.length)], 1);
						c.sendMessage("You have recieved a common item and "+ Coins +" coins.");
					} else if (Random >= 65 && Random <= 89) {
						c.getItems().addItem(Uncommon[(int) (Math.random() * Uncommon.length)], 1);
						c.sendMessage("You have recieved an uncommon item and "+ Coins +" coins.");
					} else if (Random >= 90 && Random <= 100) {
						c.getItems().addItem(Rare[(int) (Math.random() * Rare.length)], 1);
						c.sendMessage("You have recieved a rare item and "+ Coins +" coins.");
					} else if (Random >= 150 && Random <= 200) {
						c.getItems().addItem(Legendary[(int) (Math.random() * Legendary.length)], 1);
						c.sendMessage("You have recieved a Legendary item and "+ Coins +" coins.");
					}
				}
				if (c == null || BoxTimer <= 0) {
				   	Box.stop();
					Canusebox = true;
                    return; 
				}
				if (BoxTimer >= 0) {
					BoxTimer--;
				}
			}
		}, 1000);
		return Common[(int) (Math.random() * Common.length)];
	}
	
	public static void Open(int itemID, Client c) {
		if (itemID == 6199) {
			if (c.getItems().freeSlots() > 1) {
				if (Canusebox == true) {
					c.getItems().deleteItem(6199, 1);
					GenerateMyrsteryPrize(c);
				} else {
					c.sendMessage("Please wait while your current process is finished.");
				}
			} else {
				c.sendMessage("You need atleast 2 slots to open the Mystery box.");
			}
		}
	}
	
}