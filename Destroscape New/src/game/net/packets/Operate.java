package game.net.packets;

import game.entity.player.Player;
import game.item.ItemDegradation;
/* Come back to this class to look why they were disabling this. */
public class Operate {


	public static void handleClick(Player c, final int itemId) {
		switch (itemId) {

		case 11283:
		case 11284:
			if (c.playerIndex > 0) {
				c.getCombat().handleDfs(c);				
			} else if (c.npcIndex > 0) {
				c.getCombat().handleDfsNPC(c);
			}
			c.sendMessage("Your "+c.getItems().getItemName(itemId) +" currently has "+ c.dfsCount +" charges left.");
			break;
			
		case 22494:
			c.sendMessage("You have " + c.ppsLeft + " charges left!");
			break;
			
		case 18355:
		case 18349:
		case 18351:;	
		case 18353:	
		case 18357:
		case 18359:
		case 18363:
		case 18361:
			ItemDegradation.chaoticCheckCharges(c, itemId);
			break;

		}
	}

}
