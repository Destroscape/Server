package game.skill.runecrafting;

import game.entity.player.Player;

public class Pouches {
	private static final int RUNE_ESS = 1436;
	private static final int PURE_ESS = 7936;

	public static final int SMALL_POUCH = 5509;
	public static final int MEDIUM_POUCH = 5510;
	public static final int LARGE_POUCH = 5512;
	public static final int GIANT_POUCH = 5514;

	public static final int BROKEN_MEDIUM_POUCH = 5511;
	public static final int BROKEN_LARGE_POUCH = 5513;
	public static final int BROKEN_GIANT_POUCH = 5515;
	
	public static String pouchSize(int pouch){
		if(pouch == SMALL_POUCH)
			return "small";
		if(pouch == MEDIUM_POUCH)
			return "medium";
		if(pouch == LARGE_POUCH)
			return "large";
		if(pouch == GIANT_POUCH)
			return "giant";
		return "";
	}
	
	public static int pouchCapacity(Player c,String pouch){
		if(pouch.equals("small"))
			return 3;
		else if(pouch.equals("medium")){
			if (c.mediumPouchDecay < 0)
				return 3;
			else
				return 6;
		} else if (pouch.equals("large")){
			if (c.largePouchDecay < 0)
				return 6;
			else
				return 9;
		} else if (pouch.equals("giant")){
			if (c.giantPouchDecay < 0)
				return 9;
			else
				return 12;
		}
		return -1;
	}
	
	public static int getPureEss(Player c){
		return c.getItems().getItemCount(PURE_ESS);
	}
	
	public static int getEss(Player c){
		return c.getItems().getItemCount(RUNE_ESS);
	}
	
	public static void addToPouch(Player c,int pouch){
		String size = pouchSize(pouch),essType = "";
		int capacity = pouchCapacity(c,size),essAmt = -1,essId;
		if(size.equals(""))
			return;
		if(getPureEss(c) >= getEss(c)){
			essAmt = getPureEss(c);
			essType = "pure";
			essId = PURE_ESS;
		}else{
			essAmt = getEss(c);
			essType = "rune";
			essId = RUNE_ESS;
		}
		if(essAmt > 0){
			switch(size){
			case "small":
				if(c.smallPouchAmt < capacity){
					if(c.smallPouch.equals("null") || c.smallPouch.equals(essType)){
						c.smallPouch = essType;
						for (int i = 0; i < essAmt; i++) {
							c.smallPouchAmt++;
							c.getItems().deleteItem(essId, 1);
							if (c.smallPouchAmt >= capacity)
								return;
						}
					} else {
						c.sendMessage("You can fill this pouch with only one type of essence.");
					}
				} else {
					c.sendMessage("This pouch is already full.");
				}
				break;
			case "medium":
				if(c.mediumPouchAmt < capacity){
					if(c.mediumPouch.equals("null") || c.mediumPouch.equals(essType)){
						c.mediumPouch = essType;
						for (int i = 0; i < essAmt; i++) {
							c.mediumPouchAmt++;
							c.getItems().deleteItem(essId, 1);
							if (c.mediumPouchAmt >= capacity)
								return;
						}
					} else {
						c.sendMessage("You can fill this pouch with only one type of essence.");
					}
				} else {
					c.sendMessage("This pouch is already full.");
				}
				break;
			case "large":
				if(c.largePouchAmt < capacity){
					if(c.largePouch.equals("null") || c.largePouch.equals(essType)){
						c.largePouch = essType;
						for (int i = 0; i < essAmt; i++) {
							c.largePouchAmt++;
							c.getItems().deleteItem(essId, 1);
							if (c.largePouchAmt >= capacity)
								return;
						}
					} else {
						c.sendMessage("You can fill this pouch with only one type of essence.");
					}
				} else {
					c.sendMessage("This pouch is already full.");
				}
				break;
			case "giant":
				if(c.giantPouchAmt < capacity){
					if(c.giantPouch.equals("null") || c.giantPouch.equals(essType)){
						c.giantPouch = essType;
						for (int i = 0; i < essAmt; i++) {
							c.giantPouchAmt++;
							c.getItems().deleteItem(essId, 1);
							if (c.giantPouchAmt >= capacity)
								return;
						}
					} else {
						c.sendMessage("You can fill this pouch with only one type of essence.");
					}
				} else {
					c.sendMessage("This pouch is already full.");
				}
				break;
			}
		} else {
			c.sendMessage("You need some Rune Essence or Pure Essence to put in the pouch.");
		}
	}
	
	public static void removeFromPouch(Player c,int pouch){
		String size = pouchSize(pouch);
		int amt = 0,essId;
		switch(size){
		case "small":
			amt = c.smallPouchAmt;
			if(c.smallPouch.equals("rune"))
				essId = RUNE_ESS;
			else
				essId = PURE_ESS;
			for (int i = 0; i < amt; i++) {
				int invSlots = c.getItems().freeSlots();
				if (invSlots > 0) {
					c.getItems().addItem(essId, 1);
					c.smallPouchAmt--;
					if(c.smallPouchAmt == 0)
						c.smallPouch = "null";
				} else {
					c.sendMessage("You have run out of free inventory slots.");
					return;
				}
			}
			break;
		case "medium":
			amt = c.mediumPouchAmt;
			if(c.mediumPouch.equals("rune"))
				essId = RUNE_ESS;
			else
				essId = PURE_ESS;
			for (int i = 0; i < amt; i++) {
				int invSlots = c.getItems().freeSlots();
				if (invSlots > 0) {
					c.getItems().addItem(essId, 1);
					c.mediumPouchAmt--;
					if(c.mediumPouchAmt == 0)
						c.mediumPouch = "null";
				} else {
					c.sendMessage("You have run out of free inventory slots.");
					return;
				}
			}
			break;
		case "large":
			amt = c.largePouchAmt;
			if(c.largePouch.equals("rune"))
				essId = RUNE_ESS;
			else
				essId = PURE_ESS;
			for (int i = 0; i < amt; i++) {
				int invSlots = c.getItems().freeSlots();
				if (invSlots > 0) {
					c.getItems().addItem(essId, 1);
					c.largePouchAmt--;
					if(c.largePouchAmt == 0)
						c.largePouch = "null";
				} else {
					c.sendMessage("You have run out of free inventory slots.");
					return;
				}
			}
			break;
		case "giant":
			amt = c.giantPouchAmt;
			if(c.giantPouch.equals("rune"))
				essId = RUNE_ESS;
			else
				essId = PURE_ESS;
			for (int i = 0; i < amt; i++) {
				int invSlots = c.getItems().freeSlots();
				if (invSlots > 0) {
					c.getItems().addItem(essId, 1);
					c.giantPouchAmt--;
					if(c.giantPouchAmt == 0)
						c.giantPouch = "null";
				} else {
					c.sendMessage("You have run out of free inventory slots.");
					return;
				}
			}
			break;
		}
	}
	
	public static int getAmount(Player c,int pouch){
		String size = pouchSize(pouch);
		if(size.equals("small"))
			return c.smallPouchAmt;
		else if (size.equals("medium"))
			return c.mediumPouchAmt;
		else if (size.equals("large"))
			return c.largePouchAmt;
		else if (size.equals("giant"))
			return c.giantPouchAmt;
		return 0;
	}
}