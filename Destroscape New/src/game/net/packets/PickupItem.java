package game.net.packets;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.Server;
import game.entity.player.Player;
import game.minigame.bountyhunter.BountyHunter;

/*
 * Project Insanity - Evolved v.3
 * PickUpItem.java
 */

public class PickupItem implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		c.pItemY = c.getInStream().readSignedWordBigEndian();
		c.pItemId = c.getInStream().readUnsignedWord();
		c.pItemX = c.getInStream().readSignedWordBigEndian();
		if (Math.abs(c.getX() - c.pItemX) > 25
				|| Math.abs(c.getY() - c.pItemY) > 25) {
			c.resetWalkingQueue();
			return;
		}
		c.getCombat().resetPlayerAttack();
		final int itemAmount = c.getItems().getItemAmount(c.pItemId);
		final int itemAmount1 = Server.itemHandler.itemAmount(c.pItemId,
				c.pItemX, c.pItemY);
		if (itemAmount + itemAmount1 < -1) {
			return;
		}
		if (c.getX() == c.pItemX && c.getY() == c.pItemY) {
			Server.itemHandler.removeGroundItem(c, c.pItemId, c.pItemX,
					c.pItemY, true);
		} else {
			c.walkingToItem = true;
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(final CycleEventContainer container) {
					if (!c.walkingToItem) {
						container.stop();
					}
					if (c.getX() == c.pItemX && c.getY() == c.pItemY) {
						Server.itemHandler.removeGroundItem(c, c.pItemId,
								c.pItemX, c.pItemY, true);
						if(BountyHunter.inBH(c)) {
							if (c.pickupPenalty > 0) {
								c.sendMessage("You should not be picking up items. Now you must wait before you can leave.");
								BountyHunter.handleCantLeave(c);
							}
						}
						container.stop();
					}
				}

				@Override
				public void stop() {
					c.walkingToItem = false;
				}
			}, 1);
		}
	}

}
