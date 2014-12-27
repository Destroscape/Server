package game.net.packets;

import game.Config;
import game.Server;
import game.entity.player.Player;
import game.entity.player.PlayerSave;
import game.minigame.bountyhunter.BountyHunter;
import game.skill.dungeoneering.Binding;

/*
 * Project Insanity - Evolved v.3
 * DropItem.java
 */

public class DropItem implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		final int itemId = c.getInStream().readUnsignedWordA();
		c.getInStream().readUnsignedByte();
		c.getInStream().readUnsignedByte();
		final int slot = c.getInStream().readUnsignedWordA();
		if (System.currentTimeMillis() - c.alchDelay < 1800) {
			return;
		}
		if (c.arenas()) {
			c.sendMessage("You can't drop items inside the arena!");
			return;
		}
		if (c.inTrade) {
			c.sendMessage("You can't drop items while trading!");
			return;
		}
		boolean droppable = true;
		for (final int i : Config.UNDROPPABLE_ITEMS) {
			if (i == itemId) {
				droppable = false;
				break;
			}
		}
		if (c.playerItemsN[slot] != 0 && itemId != -1
				&& c.playerItems[slot] == itemId + 1) {
			if (droppable) {
				for (int i = 0; i < Binding.bindedItem.length; i++) {
					if (itemId == Binding.bindedItem[i]) {
						c.droppedItem = itemId;
						c.getPA().destroyBindInterface(itemId);
						c.destroy = 1;
						return;
					}
				}
				if (c.underAttackBy > 0) {
					if (c.getShops().getItemShopValue(itemId) > 1000) {
						c.sendMessage("You may not drop items worth more than 1000 while in combat.");
						return;
					}
				}
				for (int i = 0; i < Binding.bindedItem.length; i++) {
					if (itemId == Binding.bindedItem[i]) {
						c.droppedItem = itemId;
						c.getPA().destroyInterface1(itemId);
						c.destroy = 1;
						return;
					}
				}
				if (c.getShops().getItemShopValue(itemId) > 5000000) {
					c.droppedItem = itemId;
					c.getPA().destroyInterface(itemId);
					c.destroy = 2;
					return;
				}
				Server.itemHandler.createGroundItem(c, itemId, c.getX(),
						c.getY(), c.playerItemsN[slot], c.getId());
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
		PlayerSave.saveGame(c);
				if(BountyHunter.inBH(c)) {
					c.cantLeavePenalty = 300;
					c.headIconPk = BountyHunter.getPlayerSkull(c);
					c.getPA().requestUpdates();
				}
				/*
				 * if(Config.SOUND){ c.sendSound(c.getSound().DROPITEM); }
				 */
				//PlayerSave.saveGame(c);
			} else {
				c.sendMessage("This item cannot be dropped.");
			}
		}
	}

}
