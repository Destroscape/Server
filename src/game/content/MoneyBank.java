package game.content;

import game.entity.player.Player;

public class MoneyBank {

	/**
	 * Calculates amount of gp stored in money bank
	 */
	public static String calculateCashStored(Player p){
		String data = "";
		double bankValue = p.cashStored;
		if (bankValue < 1000) data = ("[" + (bankValue / 1000) + "] GP");
		if (bankValue >= 1000 && bankValue < 1000000) data = ("[" + (bankValue / 1000) + "K] GP");
		if (bankValue >= 1000000 && bankValue < 1000000000) data = ("[" + (bankValue / 1000000) + "MIL] GP");
		if (bankValue >= 1000000000) data = ("[" + (bankValue / 1000000000) + "BIL] GP");
		return data;
	}

	/**
	 * Handles gold depositing
	 */
	public static void depositGold(Player p, int gold) {
		if (p.myShopId > 0 || p.inMinigame()) {
			if (p.myShopId > 0)
				p.sendMessage("You cannot do this while shopping.");
			if (p.inMinigame())
				p.sendMessage("You cannot store cash while in a minigame area.");
			if (p.inTrade)
				p.sendMessage("You cannot store cash while trading.");
			return;
		}
		if (p.getItems().playerHasItem(995, gold)) {
			p.getItems().deleteItem(995, gold);
			p.cashStored += gold;
			p.sendMessage("You store " + p.getShortValue(gold) + " in your Money Bank.");
			p.getPA().sendFrame126(calculateCashStored(p), 39067);
			p.getItems().resetTempItems();
		}
	}
	
	/**
	 * Handles Money Bank Interface
	 */
	public static void openMoneyBank(Player p) {
		p.getPA().sendFrame126(MoneyBank.calculateCashStored(p), 9067);
		p.getPA().showInterface(9050);
		p.getItems().resetItems(5064);
		p.getPA().sendFrame248(9050, 5063);
		p.inMoneyBank = true;
	}
}