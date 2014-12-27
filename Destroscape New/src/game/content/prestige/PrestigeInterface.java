package game.content.prestige;

import game.entity.player.Player;
import game.entity.player.PlayerAssistant;

public class PrestigeInterface {
	/**
	 * Button Data
	 */
	public static int[] buttons = {
			133171,133172,133157,133159,133160,133161,133162,133163,133164,//Ability Page
			133191,133192,133178,133179,133180,133181,133182,133183,133184,//Wholesale Page
			133211,133212,133198,133199,133200,133201,133202,133203,133204,//Items Page
	};
	
	/**
	 * Exchange Prices
	 */
	public static int[][] prices = {
		{133157,10},{133159,10},{133160,15},{133161,15},{133162,5},{133163,10},{133164,15}, //Ability Page
		{133178,5},{133179,2},{133180,3},{133181,2},{133182,5},{133183,2},{133184,2}, //Wholesale Page
		{133198,5},{133199,40},{133200,20},{133201,35},{133202,30},{133203,15},{133204,75} //Items Page
	};
	
	/**
	 * Gets the exchange price
	 */
	public static int getPrice(int button) {
		for(int i = 0;i < prices.length;i++) {
			if(prices[i][0] == button)
				return prices[i][1];
		}
		return 2147000000;
	}
	
	/**
	 * Refreshes Points on Interface
	 */
	public static void refresh(Player c,int page) {
		c.getItems().resetItems(5064);
		if(page == 0) {
			c.getPA().sendFrame248(c.onInterface[page], 5063);
			c.getPA().sendFrame126(""+c.prestigeTokens, 34218);
		} else if (page == 1) {
			c.getPA().sendFrame248(c.onInterface[page], 5063);
			c.getPA().sendFrame126(""+c.prestigeTokens, 34238);
		} else if (page == 2) {
			c.getPA().sendFrame248(c.onInterface[page], 5063);
			c.getPA().sendFrame126(""+c.prestigeTokens, 34258);
		}
	}
	
	/**
	 * Handles Button Clicks
	 */
	public static void handleClick(Player c,int button) {
		switch(button) {
		case 133171: case 133212: case 133191: case 133211: case 133172: case 133192:
			changePage(c,button);
			break;
		case 133157:
			doubleExp(c,button);
			break;
		case 133159:
			raiseDropRate(c,button);
			break;
		case 133160:
			undrainPrayer(c,button);
			break;
		case 133161:
			instantKill(c,button);
			break;
		case 133162:
			raiseCharmRate(c,button);
			break;
		case 133163:
			crystalChest(c,button);
			break;
		case 133164:
			prestigeChest(c,button);
			break;
		case 133178:
			purchaseWholesale(c,button,990,10); //Crystal Keys
			break;
		case 133179:
			runePack(c,button);
			break;
		case 133180:
			charmPack(c,button);
			break;
		case 133181:
			purchaseWholesale(c,button,9244,250); //Dragon Bolts
			break;
		case 133182:
			purchaseWholesale(c,button,15271,100); //Raw Rocktail
			break;
		case 133183:
			purchaseWholesale(c,button,1514,150); //Magic Logs
			break;
		case 133184:
			purchaseWholesale(c,button,220,100); //Grimy Torstol
			break;
		case 133198:
			purchaseItem(c,getPrice(button),15084);//Dice Bag
			break;
		case 133199:
			purchaseItem(c,getPrice(button),19780);//Korasi
			break;
		case 133200:
			purchaseItem(c,getPrice(button),24);//Kiln Cape
			break;
		case 133201:
			purchaseItem(c,getPrice(button),23675);//Royal Crossbow
			break;
		case 133202:
			purchaseItem(c,getPrice(button),1391);//Armadyl Battlestaff
			break;
		case 133203:
			purchaseItem(c,getPrice(button),12637);//Mystery Egg
			break;
		case 133204:
			purchaseItem(c,getPrice(button),1051);//Santa Hat
			break;
		}
	}
	
	/**
	 * Item Purchases
	 */
	public static void purchaseItem(Player c,int price,int itemId) {
		if(c.getItems().freeSlots() > 0) {
			if(c.prestigeTokens >= price) {
				c.prestigeTokens -= price;
				c.getItems().addItem(itemId, 1);
				c.sendMessage("You purchase a <col=250>"+c.getItems().getItemName(itemId)+"</col> for <col=250>"+price+" Tokens.");
				refresh(c,2);
			} else {
				c.sendMessage("You need <col=250>"+price+"</col> Tokens to buy this pack.");
			}
		} else {
			c.sendMessage("You need at least 1 free inventory slot.");
		} 
	}
	
	/**
	 * Wholesale Purchases
	 */
	public static void purchaseWholesale(Player c,int button,int itemId,int amt) {
		int price = getPrice(button);
		if(c.getItems().freeSlots() > 0) {
			if(c.prestigeTokens >= price) {
				c.prestigeTokens -= price;
				c.getItems().addItem(itemId, amt);
				c.sendMessage("You purchase <col=250>"+amt+" "+c.getItems().getItemName(itemId)+"</col> for <col=250>"+price+" Tokens.");
				refresh(c,1);
			} else {
				c.sendMessage("You need <col=250>"+price+"</col> Tokens to buy this pack.");
			}
		} else {
			c.sendMessage("You need at least 1 free inventory slot.");
		}
	}
	
	/**
	 * Barrage and Vengence Runes
	 */
	public static void runePack(Player c,int button) {
		int price = getPrice(button);
		if(c.prestigeTokens >= price ) {
			if(c.getItems().freeSlots() >= 6) {
				c.prestigeTokens -= price;
				int[][] runes = {{555,250},{557,250},{560,500},{565,250},{566,250},{9075,250}};
				for(int i = 0;i < runes.length;i++) {
					c.getItems().addItem(runes[i][0], runes[i][1]);
				}
				c.sendMessage("You purchase <col=250>"+250+" Barrage and Vengence Runes</col> for <col=250>"+price+" Tokens.");
				refresh(c,1);
			} else {
				c.sendMessage("You need 6 free inventory slots!");
			}
		} else {
			c.sendMessage("You need <col=250>"+price+"</col> Tokens to buy this pack.");
		}
	}
	
	/**
	 * Summoning Charm Pack
	 */
	public static void charmPack(Player c,int button) {
		int price = getPrice(button);
		if(c.prestigeTokens >= price) {
			if(c.getItems().freeSlots() >= 4) {
				c.prestigeTokens -= price;
				int[][] charms = {{12158,100},{12159,100},{12160,100},{12163,100}};
				for(int i = 0;i < charms.length;i++) {
					c.getItems().addItem(charms[i][0], charms[i][1]);
				}
				c.sendMessage("You purchase <col=250>"+100+" of each Summoning Charm</col> for <col=250>"+price+" Tokens.");
				refresh(c,1);
			} else {
				c.sendMessage("You need 4 free inventory slots!");
			}
		} else {
			c.sendMessage("You need <col=250>"+price+"</col> Tokens to buy this pack.");
		}
	}
	
	/**
	 * Appends Double Exp Ability
	 */
	public static void doubleExp(Player c,int button) {
		int price = getPrice(button);
		if(c.prestigeTokens >= price) {
			if(!c.doubleExp) {
				c.prestigeTokens -= price;
				c.doubleExp = true;
				c.doubleExpTime = 0;
				PlayerAssistant.startAbilityCycle(c);
				c.sendMessage("Your double exp is now active!");
				c.saveGame();
				refresh(c,0);
			} else {
				c.sendMessage("You already have double exp enabled.");
			}
		} else {
			c.sendMessage("You need "+price+" tokens to purchase this ability.");
		}
	}
	
	/**
	 * Appends Increased Drop Rate Ability
	 */
	public static void raiseDropRate(Player c,int button) {
		int price = getPrice(button);
		if(c.prestigeTokens >= price) {
			if(!c.raiseDropRate) {
				c.prestigeTokens -= price;
				c.raiseDropRate = true;
				c.dropRateTime = 0;
				PlayerAssistant.startAbilityCycle(c);
				c.sendMessage("Your increased drop rates are now active!");
				c.saveGame();
				refresh(c,0);
			} else {
				c.sendMessage("You already have increased rates enabled.");
			}
		} else {
			c.sendMessage("You need "+price+" tokens to purchase this ability.");
		}
	}
	
	/**
	 * Appends Undraining Prayer Ability
	 */
	public static void undrainPrayer(Player c,int button) {
		int price = getPrice(button);
		if(c.prestigeTokens >= price) {
			if(!c.undrainPrayer) {
				c.prestigeTokens -= price;
				c.undrainPrayer = true;
				c.undrainPrayerTime = 0;
				PlayerAssistant.startAbilityCycle(c);
				c.sendMessage("Your undraining prayer is now active!");
				c.saveGame();
				refresh(c,0);
			} else {
				c.sendMessage("You already have undraining prayer enabled.");
			}
		} else {
			c.sendMessage("You need "+price+" tokens to purchase this ability.");
		}
	}
	
	/**
	 * Appends Increased Charm Rate Ability
	 */
	public static void raiseCharmRate(Player c,int button) {
		int price = getPrice(button);
		if(c.prestigeTokens >= price) {
			if(!c.raiseCharmRate) {
				c.prestigeTokens -= price;
				c.raiseCharmRate = true;
				c.charmRateTime = 0;
				PlayerAssistant.startAbilityCycle(c);
				c.sendMessage("Your increased charm rates are now active!");
				c.saveGame();
				refresh(c,0);
			} else {
				c.sendMessage("You already have increased charm rates enabled.");
			}
		} else {
			c.sendMessage("You need "+price+" tokens to purchase this ability.");
		}
	}
	
	/**
	 * Appends Instant Kill Ability
	 */
	public static void instantKill(Player c,int button) {
		int price = getPrice(button);
		if(c.prestigeTokens >= price) {
			if(!c.instantKill) {
				c.prestigeTokens -= price;
				c.instantKill = true;
				c.instantKills = 5;
				c.sendMessage("Your 5 instant kills are now active and ready to use!");
				c.saveGame();
				refresh(c,0);
			} else {
				c.sendMessage("You already have instant kill enabled.");
			}
		} else {
			c.sendMessage("You need "+price+" tokens to purchase this ability.");
		}
	}
	
	/**
	 * Appends Increased Crystal Chest Ability
	 */
	public static void crystalChest(Player c,int button) {
		int price = getPrice(button);
		if(c.prestigeTokens >= price) {
			if(!c.raiseCrysRate) {
				c.prestigeTokens -= price;
				c.raiseCrysRate = true;
				c.crystalKeyAmt = 10;
				c.sendMessage("Your 10% chance raise is active and ready to use!");
				c.saveGame();
				refresh(c,0);
			} else {
				c.sendMessage("You already have raised crystal chest chances enabled.");
			}
		} else {
			c.sendMessage("You need "+price+" tokens to purchase this ability.");
		}
	}
	
	/**
	 * Appends Increased Prestige Chest Ability
	 */
	public static void prestigeChest(Player c,int button) {
		int price = getPrice(button);
		if(c.prestigeTokens >= price) {
			if(!c.raisePresRate) {
				c.prestigeTokens -= price;
				c.raisePresRate = true;
				c.presChestAmt = 5;
				c.sendMessage("Your 5% chance raise is active and ready to use!");
				c.saveGame();
				refresh(c,0);
			} else {
				c.sendMessage("You already have raised prestige chest chances enabled.");
			}
		} else {
			c.sendMessage("You need "+price+" tokens to purchase this ability.");
		}
	}
	
	/**
	 * Handles page switching
	 */
	public static void changePage(Player c,int button) {
		switch(button) {
		case 133171:
		case 133212:
			c.getPA().showInterface(34222);
			c.currentPage = 1;
			break;
		case 133191:
		case 133211:
			c.getPA().showInterface(34200);
			c.currentPage = 0;
			break;
		case 133172:
		case 133192:
			c.getPA().showInterface(34242);
			c.currentPage = 2;
			break;
		}
		refresh(c,c.currentPage);
	}
}