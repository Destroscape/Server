package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Drop Item
 **/
public class DropItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readUnsignedWordA();
		c.getInStream().readUnsignedByte();
		c.getInStream().readUnsignedByte();
		int slot = c.getInStream().readUnsignedWordA();
		if (c.inTrade) {
			c.sendMessage("You cannot drop items in the trade screen.");
			return;
		}
		if(c.arenas()) {
			c.sendMessage("You can't drop items inside the arena!");
			return;
		}
		if (c.playerName.equalsIgnoreCase("")){
			c.sendMessage("Your dropping has been disabled by Luis.");
			return;
		}
		if(c.InDung()) {
			c.sendMessage("You can't drop items inside Dung!");
			return;
		}	
		if(!c.getItems().playerHasItem(itemId,1,slot)) {
			return;
		}
		if (System.currentTimeMillis() - c.alchDelay < 1800)
			return;
		boolean droppable = true;
		for (int i : Config.UNDROPPABLE_ITEMS) {
			if (i == itemId) {
				droppable = false;
				break;
			}
		}
		if(c.playerItemsN[slot] != 0 && itemId != -1 && c.playerItems[slot] == itemId + 1) {
			if(droppable) {
			if (c.getShops().getItemShopValue(itemId) > 0) {
					c.droppedItem = itemId;
					c.getPA().destroyInterface(itemId);
					return;
			/*if(c.playerItemsN[slot] != 0 && itemId != -1 && c.playerItems[slot] == itemId + 1) {
			if(!c.getItems().playerHasItem(itemId,1,slot)) {
			c.sendMessage("Stop cheating!");
			return;*/
				}
			}
		}
	}
}