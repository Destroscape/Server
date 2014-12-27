package game.content;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.entity.player.Player;
import game.skill.SkillHandler;

public class TeleTabCreation {

	private Player p;

	public TeleTabCreation(Player p) {
		this.p = p;
	}

	public enum TABS {

		VARROCK(8007, 25, 557, 1, 556, 3, 563, 1),
		LUMBRIDGE(8008, 31, 557, 1, 556, 3, 563, 1),
		FALADOR(8009, 43, 555, 1, 556, 3, 563, 1),
		CAMELOT(8010, 55, 556, 5, 563, 1, -1, -1),
		ARDOUGNE(8011, 63, 555, 2, 556, 3, 563, 2),
		HOME(8013, 90, 557, 3, 556, 5, 563, 2);

		int ItemID;
		int SkillReq;
		int Rune1;
		int Amount1;
		int Rune2;
		int Amount2;
		int Rune3;
		int Amount3;

		TABS(int ItemID, int SkillReq, int Rune1, int Amount1, int Rune2, int Amount2, int Rune3, int Amount3) {
			this.ItemID = ItemID;
			this.SkillReq = SkillReq;
			this.Rune1 = Rune1;
			this.Amount1 = Amount1;
			this.Rune2 = Rune2;
			this.Amount2 = Amount2;
			this.Rune3 = Rune3;
			this.Amount3 = Amount3;
		}
		public int getItemId() {
			return ItemID;
		}
		public int getMageReq() {
			return SkillReq;
		}
		public int getRune1() {
			return Rune1;
		}
		public int getAmount1() {
			return Amount1;
		}
		public int getRune2() {
			return Rune2;
		}
		public int getAmount2() {
			return Amount2;
		}
		public int getRune3() {
			return Rune3;
		}
		public int getAmount3() {
			return Amount3;
		}
	}

	public void createTab(final int tabId) {
		for (final TABS t : TABS.values())
			if(tabId == t.getItemId()) {
				if(p.playerLevel[p.playerMagic] >= t.getMageReq()) { 
					CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer craftTabs) {
							if(p.getItems().playerHasItem(t.getRune1(), t.getAmount1()) && p.getItems().playerHasItem(t.getRune2(), t.getAmount2()) && p.getItems().playerHasItem(t.getRune3(), t.getAmount3()) && p.getItems().playerHasItem(1761, 1)) {
								if (p.craftingTeletabs == true) {
									p.getItems().deleteItem(t.getRune1(), t.getAmount1());p.getItems().deleteItem(t.getRune2(), t.getAmount2());p.getItems().deleteItem(t.getRune3(), t.getAmount3());p.getItems().deleteItem(1761, 1);
									p.getItems().addItem(tabId, 1);
									p.getPA().addSkillXP(500 * SkillHandler.CRAFTING_XP, p.playerCrafting);
									p.startAnimation(713);
									p.sendMessage("You create a " +p.getItems().getItemName(tabId)+".");
								}
							} else 
								craftTabs.stop();
						}
						@Override
						public void stop() {
							p.craftingTeletabs = false;	
							p.sendMessage("You do not have the required materials to make this.");
							p.sendMessage("You need: "+ t.getAmount1() +" "+ p.getItems().getItemName(t.getRune1()) +", "+ t.getAmount2() +" "+ p.getItems().getItemName(t.getRune2()) +", "+ t.getAmount3() +" "+ p.getItems().getItemName(t.getRune3()) +" and 1 soft clay.");
						}
					}, 4);
				} else {
					p.sendMessage("You need a Magic level of "+t.getMageReq()+" to craft this.");
				}
			}
	}
}