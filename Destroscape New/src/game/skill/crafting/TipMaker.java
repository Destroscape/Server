package game.skill.crafting;

import game.entity.player.Player;
import game.skill.SkillHandler;

public class TipMaker extends SkillHandler {

	private static int[][] tipArray = { 
		{ 1609, 45, 10 }, //opal
		{ 1611, 9187, 15 }, //jade
		{ 1613, 9188, 20 }, //red topaz
		{ 1607, 9189, 30 }, //sapphire
		{ 1605, 9190, 40 }, //emerald
		{ 1603, 9191, 50 }, //ruby
		{ 1601, 9192, 60 }, //diamond
		{ 1615, 9193, 70 }, //dragon
		{ 6573, 9194, 80 }, //onyx
		};
	
	

	public static void makeTip(final Player c, final int useWith,
			final int itemUsed) {
		for (int i = 0; i < tipArray.length; i++) {
			if (useWith == 1755 && itemUsed == tipArray[i][0]
					|| useWith == tipArray[i][0] && itemUsed == 1755) {
				c.getItems().deleteItem2(tipArray[i][0], 1);
				c.getItems().addItem(tipArray[i][1], 10);
				c.getPA().addSkillXP(
						tipArray[i][2] * FLETCHING_XP,
						c.playerFletching);
				c.sendMessage("You create 10 "
						+ c.getItems().getItemName(tipArray[i][1]) + ".");
			}
		}
	}

}
