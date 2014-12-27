package game.net.packets;

import game.content.GodBooks;
import game.content.ImbueScrollHandler;
import game.content.Kits;
import game.entity.player.Player;
import game.skill.crafting.JewelryMaking;
import game.skill.crafting.LeatherMaking;
import game.skill.crafting.TipMaker;
import game.skill.firemaking.Firemaking;
import game.skill.fletching.BoltHandler;
import game.skill.fletching.BowHandler;
import game.skill.fletching.StringingHandler;
import game.skill.herblore.Herblore;

/*
 * Project Insanity - Evolved v.3
 * ItemOnItem.java
 */

public class ItemOnItem implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		final int usedWithSlot = c.getInStream().readUnsignedWord();
		final int itemUsedSlot = c.getInStream().readUnsignedWordA();
		final int useWith = c.playerItems[usedWithSlot] - 1;
		final int itemUsed = c.playerItems[itemUsedSlot] - 1;
		if (!c.getItems().playerHasItem(useWith, 1, usedWithSlot)
				|| !c.getItems().playerHasItem(itemUsed, 1, itemUsedSlot)) {
			return;
		}
		c.isSkilling[c.playerSmithing] = false;
		if (Herblore.mixPotion(c, itemUsed, useWith)) {
			Herblore.setUpUnfinished(c, itemUsed, useWith);
		} else if (Herblore.mixPotionNew(c, itemUsed, useWith)) {
			Herblore.setUpPotion(c, itemUsed, useWith);
		}
		if (itemUsed == 1759 || useWith == 1759) {
			JewelryMaking.stringAmulet(c, itemUsed, useWith);
		} else if (itemUsed == 1733 || useWith == 1733) {
			LeatherMaking.craftLeatherDialogue(c, itemUsed, useWith);
		}
		if (Firemaking.playerLogs(c, itemUsed, useWith)) {
			Firemaking.grabData(c, itemUsed, useWith);
		}
		UseItem.ItemonItem(c, itemUsed, useWith);
		GodBooks.itemOnItemHandle(c, useWith, itemUsed);
		Kits.thisItemOnItemHandle(c, useWith, itemUsed);
		ImbueScrollHandler.scrollOnRing(c, useWith, itemUsed);
		BowHandler.handleInterface(c, useWith, itemUsed);
		StringingHandler.useItemInterface(c, useWith, itemUsed);
		BoltHandler.finishBolt(c, useWith, itemUsed);
		TipMaker.makeTip(c, useWith, itemUsed);
	}

}
