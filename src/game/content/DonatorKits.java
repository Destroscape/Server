package game.content;

import engine.util.Misc;
import game.entity.player.Player;

public class DonatorKits {

	public static final int KIT_ID = 3062;

	private static final int[] RARE_KIT = { 
		1037, 1050, 1050, 1050, 1038, 1037,
		1040, 1037, 1042, 1050, 1044, 2581, 
		1050, 1046, 1037, 1048, 1053,1055, 
		1053, 1055, 1057, 1057, 1037, 2581, 
		1050, 1053, 1055, 1057, 1050, 1050 };

	public static void openKit(final Player player, final int item) {
		int count = Misc.random(RARE_KIT.length);
		if (count == 30)
			count = 29;
		if (!player.getItems().playerHasItem(item) && item != KIT_ID)
			return;
		player.getItems().deleteItem2(KIT_ID, 1);
		player.getItems().addItem(RARE_KIT[count], 1);
	}

}
