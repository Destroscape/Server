package game.content;

import game.entity.player.Player;

public class GodBooks {

	public static void fillBook(Player c, int oldBook, int newBook, int page1, int page2, int page3, int page4) {
		if (c.getItems().playerHasItem(oldBook, 1) && c.getItems().playerHasItem(page1, 1) && c.getItems().playerHasItem(page2, 1) && c.getItems().playerHasItem(page3, 1) && c.getItems().playerHasItem(page4, 1)) {
			c.getItems().deleteItem(oldBook, c.getItems().getItemSlot(oldBook), 1);
			c.getItems().deleteItem(page1, c.getItems().getItemSlot(page1), 1);
			c.getItems().deleteItem(page2, c.getItems().getItemSlot(page2), 1);
			c.getItems().deleteItem(page3, c.getItems().getItemSlot(page3), 1);
			c.getItems().deleteItem(page4, c.getItems().getItemSlot(page4), 1);
			c.getItems().addItem(newBook, 1);
		} else {
			c.sendMessage("You need all 4 pages to fill the book!");
		}
	}
	
	public static void itemOnItemHandle(Player c, int useWith, int itemUsed)
	{		
		if ((itemUsed == 19608) || (itemUsed == 19609) || (itemUsed == 19610) || (useWith == 19611)) { // ancient
			fillBook(c, 19616, 19617, 19608, 19609, 19610, 19611);
		}	
		if ((itemUsed == 19608) || (itemUsed == 19609) || (itemUsed == 19610) || (useWith == 19611)) { // ancient
			fillBook(c, 19616, 19617, 19608, 19609, 19610, 19611);
		}
		if ((useWith == 19600) || (useWith == 19601) || (useWith == 19602) && (itemUsed == 19603)) { // bandos
			fillBook(c, 19612, 19613, 19600, 19601, 19602, 19603);
		}		
		if ((useWith == 19600) || (useWith == 19601) || (useWith == 19602) && (itemUsed == 19603)) { // bandos
			fillBook(c, 19612, 19613, 19600, 19601, 19602, 19603);
		}
		if ((useWith == 19604) || (useWith == 19605) || (useWith == 19606) && (itemUsed == 19607)) { // armadyl
			fillBook(c, 19614, 19615, 19604, 19605, 19606, 19607);
		}		
		if ((useWith == 19604) || (useWith == 19605) || (useWith == 19606) && (itemUsed == 19607)) { // armadyl
			fillBook(c, 19614, 19615, 19604, 19605, 19606, 19607);
		}	
		if ((useWith == 3827) || (useWith == 3827) || (useWith == 3827) && (itemUsed == 3839)) { // sara
			fillBook(c, 3839, 3840, 3827, 3828, 3829, 3830);
		}		
		if ((itemUsed == 3827) || (itemUsed == 3828) || (itemUsed == 3829) || (useWith == 3839)) {// sara
			fillBook(c, 3839, 3840, 3827, 3828, 3829, 3830);
		}		
		if ((useWith == 3831) || (useWith == 3832) || (useWith == 3833) && (itemUsed == 3841)) { // zammy
			fillBook(c, 3841, 3842, 3831, 3832, 3833, 3834);
		}		
		if ((itemUsed == 3831) || (itemUsed == 3832) || (itemUsed == 3833) || (useWith == 3841)) { // zammy
			fillBook(c, 3841, 3842, 3831, 3832, 3833, 3834);
		}		
		if ((useWith == 3835) || (useWith == 3836) || (useWith == 3837) && (itemUsed == 3843)) { // guthix
			fillBook(c, 3843, 3844, 3835, 3836, 3837, 3838);
		}		
		if ((itemUsed == 3835) || (itemUsed == 3836) || (itemUsed == 3837) || (useWith == 3843)) { // guthix
			fillBook(c, 3843, 3844, 3835, 3836, 3837, 3838);
		}
	}

}
