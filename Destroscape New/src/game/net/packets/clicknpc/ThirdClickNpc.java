package game.net.packets.clicknpc;

import engine.util.Misc;
import game.Config;
import game.entity.player.Player;

public class ThirdClickNpc {

	public static void handleClick(Player c, int npcId) {
		c.clickNpcType = 0;
		c.npcClickIndex = 0;
			if (c.playerRights == 3) {
				Misc.println("Npc click 3: " + npcId);
			}
		switch (npcId) {
		
		case 660:
			c.sendMessage("Coming Soon");
			break;
			
		case 556:
			c.getShops().openShop(59);
			break;

		case 212:
			if (c.loyaltyTitle == 0) {
				c.getDH().sendDialogues(163, npcId);
			} else {
				c.getDH().sendDialogues(164, npcId);
			}
			break;

		case 6971:
			c.getShops().openShop(5);
			break;

		case 1597:
			c.getShops().openShop(17);
			c.sendMessage("You currently have " + c.slayerPoints
					+ " Slayer Points!");
			break;

		case 548:
			c.getPA().showInterface(3559);
			c.canChangeAppearance = true;
			break;

		case 545:
			c.getShops().openShop(24);
			break;

		default:
			if (Config.SERVER_DEBUG = true) {
				if (c.playerRights == 3) {
					Misc.println("Third Click NPC : " + npcId);
				}
			}
			break;
		}
	}

}
