package game.net.packets;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.Server;
import game.combat.magic.MagicRequirements;
import game.entity.player.Player;
import game.item.Item;

public class MagicOnFloorItems implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		final int itemY = c.getInStream().readSignedWordBigEndian();
		final int itemId = c.getInStream().readUnsignedWord();
		final int itemX = c.getInStream().readSignedWordBigEndian();
		c.getInStream().readUnsignedWordA();
		if (!Server.itemHandler.itemExists(itemId, itemX, itemY)) {
			c.stopMovement();
			return;
		}
		c.usingMagic = true;
		/*
		 * Telegrab spell
		 */
		if (!c.getItems().playerHasItem(556,
				MagicRequirements.wearingStaff(c, 556) ? 0 : 1)
				|| !c.getItems().playerHasItem(563, 1)) {
			c.sendMessage("You do not have the correct amount of runes for this spell.");
			return;
		}
		if ((c.getItems().freeSlots() >= 1 || c.getItems().playerHasItem(
				itemId, 1))
				&& Item.itemStackable[itemId]
				|| c.getItems().freeSlots() > 0
				&& !Item.itemStackable[itemId]) {
			if (c.goodDistance(c.getX(), c.getY(), itemX, itemY, 12)) {
				c.walkingToItem = true;
				final int offY = (c.getX() - itemX) * -1;
				final int offX = (c.getY() - itemY) * -1;
				c.teleGrabX = itemX;
				c.teleGrabY = itemY;
				// c.getItems().deleteItem2(556,
				// MagicRequirements.wearingStaff(c, 556) ? 0 : 4);
				// c.getItems().deleteItem2(563, 1);
				c.teleGrabItem = itemId;
				c.turnPlayerTo(itemX, itemY);
				c.teleGrabDelay = System.currentTimeMillis();
				//c.setAnimation(Animation.create(c.MAGIC_SPELLS[51][2]));
				c.startAnimation(c.MAGIC_SPELLS[51][2]);
				c.gfx100(c.MAGIC_SPELLS[51][3]);
				c.getPA().createPlayersStillGfx(144, itemX, itemY, 0, 72);
				c.getPA().createPlayersProjectile(c.getX(), c.getY(), offX,
						offY, 50, 70, c.MAGIC_SPELLS[51][4], 50, 10, 0, 50);
				c.getPA().addSkillXP(c.MAGIC_SPELLS[51][7], 6);
				c.getPA().refreshSkill(6);
				c.stopMovement();
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(final CycleEventContainer container) {
						if (!c.walkingToItem) {
							container.stop();
						}
						if (System.currentTimeMillis() - c.teleGrabDelay > 1550
								&& c.usingMagic) {
							if (Server.itemHandler.itemExists(c.teleGrabItem,
									c.teleGrabX, c.teleGrabY)
									&& c.goodDistance(c.getX(), c.getY(),
											itemX, itemY, 12)) {
								Server.itemHandler.removeGroundItem(c,
										c.teleGrabItem, c.teleGrabX,
										c.teleGrabY, true);
								c.usingMagic = false;
							}
						}
					}

					@Override
					public void stop() {
						c.walkingToItem = false;
					}
				}, 1);
			}
		} else {
			c.sendMessage("You don't have enough space in your inventory.");
			c.stopMovement();
		}
		if (!c.getCombat().checkMagicReqs(51)) {
			c.stopMovement();
			return;
		}
		if (c.goodDistance(c.getX(), c.getY(), itemX, itemY, 12)) {
			final int offY = (c.getX() - itemX) * -1;
			final int offX = (c.getY() - itemY) * -1;
			c.teleGrabX = itemX;
			c.teleGrabY = itemY;
			c.teleGrabItem = itemId;
			c.turnPlayerTo(itemX, itemY);
			c.teleGrabDelay = System.currentTimeMillis();
			//c.setAnimation(Animation.create(c.MAGIC_SPELLS[51][2]));
			c.startAnimation(c.MAGIC_SPELLS[51][2]);
			c.gfx100(c.MAGIC_SPELLS[51][3]);
			c.getPA().createPlayersStillGfx(144, itemX, itemY, 0, 72);
			c.getPA().createPlayersProjectile(c.getX(), c.getY(), offX, offY,
					50, 70, c.MAGIC_SPELLS[51][4], 50, 10, 0, 50);
			c.getPA().addSkillXP(c.MAGIC_SPELLS[51][7], 6);
			c.getPA().refreshSkill(6);
			c.stopMovement();
		}
	}

}
